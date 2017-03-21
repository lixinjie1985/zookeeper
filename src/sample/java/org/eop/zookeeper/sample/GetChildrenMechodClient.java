package org.eop.zookeeper.sample;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author lixinjie
 */
public class GetChildrenMechodClient {

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 3000, new DefaultWatcher());
		Thread.sleep(3000);
		zk.create("/parent", "你好".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println("-----------------父节点已创建----------------");
		Thread.sleep(15000);
		zk.create("/parent/getChildren", "你好".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		System.out.println("-----------------节点已创建----------------");
		Thread.sleep(15000);
		zk.setData("/parent/getChildren", "我好".getBytes(), -1);
		System.out.println("-----------------节点已更新----------------");
		Thread.sleep(15000);
		zk.delete("/parent/getChildren", -1);
		System.out.println("-----------------节点已删除----------------");
		Thread.sleep(15000);
		zk.setData("/parent", "大家好".getBytes(), -1);
		System.out.println("-----------------父节点已更新----------------");
		Thread.sleep(15000);
		zk.delete("/parent", -1);
		System.out.println("-----------------父节点已删除----------------");
		Thread.sleep(15000);
	}

}
