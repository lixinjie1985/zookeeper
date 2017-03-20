package org.eop.zookeeper.sample;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author lixinjie
 */
public class GetDataMechodClient {

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 3000, new DefaultWatcher());
		Thread.sleep(3000);
		zk.create("/getData", "你好".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		System.out.println("-----------------节点已创建----------------");
		Thread.sleep(15000);
		zk.setData("/getData", "我好".getBytes(), -1);
		System.out.println("-----------------节点已更新----------------");
		Thread.sleep(15000);
		zk.delete("/getData", -1);
		System.out.println("-----------------节点已删除----------------");
		Thread.sleep(3000);
	}

}
