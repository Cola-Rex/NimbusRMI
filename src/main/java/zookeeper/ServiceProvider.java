package zookeeper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RMI服务提供者
 */
public class ServiceProvider {
	
	private static final Logger log = LoggerFactory.getLogger(ServiceProvider.class);
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	//发布 RMI 服务并注册 RMI 地址到 Zookeeper 中
	public void publish(Remote remote, String host, int port) {
		String url = publishService(remote, host, port); // 发布 RMI 服务并返回 RMI 地址
		if (url != null) {
			ZooKeeper zk = connectServer(); // 连接 ZooKeeper 服务器并获取 ZooKeeper 对象
			if (zk != null) {
				createNode(zk, url); // 创建 ZNode 并将 RMI 地址放入 ZNode 上
			}
		}
	}
	
	//发布 RMI 服务
	private String publishService(Remote remote, String host, int port) {
		String url = null;
		try {
			url = String.format("rmi://%s:%d/%s", host, port, remote.getClass().getName());
			LocateRegistry.createRegistry(port);
			Naming.rebind(url, remote);
			log.debug("pulish rmi service (url: {})", url);
		} catch (RemoteException | MalformedURLException e) {
			log.error("", e);
		}
		return url;
	}
	
	//连接 zookeeper 服务器
	private ZooKeeper connectServer()  {
		ZooKeeper zk = null;
		try {
			zk = new ZooKeeper(Constant.ZK_CONNECTION_STRING, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					if(event.getState() == Event.KeeperState.SyncConnected) {
						latch.countDown(); //唤醒当前正在执行的线程
					}
				}
			});
		} catch (IOException e) {
			log.error("", e);
		}
		return zk;
	}
	
	//创建 ZNode
	private void createNode(ZooKeeper zk, String url) {
		try {
			byte[] data = url.getBytes();
			String path = zk.create(Constant.ZK_PROVIDER_PATH, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL); //创建一个临时性且有序的 ZNode
			log.debug("create zookeeper node ({} => {})", path, url);
		} catch (KeeperException | InterruptedException e) {
			log.error("", e);
		}
	}
}
