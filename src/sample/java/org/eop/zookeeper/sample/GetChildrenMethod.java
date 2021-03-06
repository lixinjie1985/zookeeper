package org.eop.zookeeper.sample;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author lixinjie
 */
public class GetChildrenMethod implements Watcher {
	
	public static void main(String[] args) throws Exception {
		System.out.println("主线程：" + Thread.currentThread().getId());
		
		GetChildrenMethod e = new GetChildrenMethod();
		synchronized (e.getClass()) {
			e.getClass().wait();
		}
	}
	
	private ZooKeeper zk;
	
	public GetChildrenMethod() throws IOException, InterruptedException {
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
			zk.getChildren("/parent", true);//此方法注册的Watcher可以收到本节点的删除事件和子节点的创建和删除事件，不能收到本节点的更新和子节点的更新事件，且使用此方法时本节点必须已经存在，否则无法注册成功且抛异常
			System.out.println("已使用注册getChildren注册Watcher");
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
