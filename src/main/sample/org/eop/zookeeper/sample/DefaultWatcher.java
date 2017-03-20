package org.eop.zookeeper.sample;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author lixinjie
 */
public class DefaultWatcher implements Watcher {

	@Override
	public void process(WatchedEvent event) {
		System.out.println("事件线程：" + Thread.currentThread().getId());
		System.out.println(this.getClass().getName());
		System.out.print("路径：" + event.getPath());
		System.out.print("，事件：" + event.getType());
		System.out.print("，状态：" + event.getState());
		System.out.println();
	}

}
