package org.eop.zookeeper.sample;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @author lixinjie
 */
public class GetDataMethod implements Watcher {
	
	public static void main(String[] args) throws Exception {
		System.out.println("主线程：" + Thread.currentThread().getId());
		
		GetDataMethod e = new GetDataMethod();
		synchronized (e.getClass()) {
			e.getClass().wait();
		}
	}
	
	private ZooKeeper zk;
	
	public GetDataMethod() throws IOException, InterruptedException {
		zk = new ZooKeeper("127.0.0.1:2181", 3000, this);
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("事件线程：" + Thread.currentThread().getId());
		System.out.println(this.getClass().getName());
		System.out.print("路径：" + event.getPath());
		System.out.print("，事件：" + event.getType());
		System.out.print("，状态：" + event.getState());
		System.out.println();
		exists();
	}
	
	public void exists() {
		try {
			zk.getData("/getData", true, new Stat());//此方法注册的Watcher可以收到节点的更新和删除事件，因为使用此方法时节点必须已经存在，否则无法注册成功且抛异常
			System.out.println("已使用注册getData注册Watcher");
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
