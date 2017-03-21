package org.eop.zookeeper.sample;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author lixinjie
 */
public class ExistsMechodClient {

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 3000, new DefaultWatcher());
		Thread.sleep(3000);
		zk.create("/exists", "你好".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		Thread.sleep(3000);
		zk.setData("/exists", "我好".getBytes(), -1);
		Thread.sleep(3000);
		zk.delete("/exists", -1);
		Thread.sleep(3000);
	}

}
