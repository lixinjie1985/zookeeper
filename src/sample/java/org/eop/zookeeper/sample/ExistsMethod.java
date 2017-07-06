package org.eop.zookeeper.sample;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author lixinjie
 */
public class ExistsMethod implements Watcher {
	
	public static void main(String[] args) throws Exception {
		System.out.println("主线程：" + Thread.currentThread().getId());
		
		ExistsMethod e = new ExistsMethod();
		synchronized (e.getClass()) {
			e.getClass().wait();
		}
	}
	
	private ZooKeeper zk;
	
	public ExistsMethod() throws IOException, InterruptedException {
		//ExistsMethod类实现了Watcher接口，这样自己就是一个Watcher
		//在构造ZooKeeper时将自己传进去，当ZooKeeper连接成功时
		//自己就可以收到通知，即process方法会被执行
		zk = new ZooKeeper("127.0.0.1:2181", 3000, this);
	}

	@Override
	public void process(WatchedEvent event) {
		//该方法首次执行接收的是连接成功事件
		System.out.println("事件线程：" + Thread.currentThread().getId());
		System.out.println(this.getClass().getName());
		System.out.print("路径：" + event.getPath());
		System.out.print("，事件：" + event.getType());
		System.out.print("，状态：" + event.getState());
		System.out.println();
		exists();//由于Watcher只能触发一次，这里每次都要重新设置一遍
	}
	
	public void exists() {
		try {
			zk.exists("/exists", true);//此方法注册的Watcher可以收到节点的创建、更新和删除事件
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
