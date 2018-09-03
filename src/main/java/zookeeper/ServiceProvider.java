package zookeeper;

import java.rmi.Remote;
import java.util.concurrent.CountDownLatch;

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
	}
	
	//发布 RMI 服务
	private String publishService(Remote remote, String host, int port) {
		String url = null;
		try {
		} catch (Exception e) {
			// TODO: handle exception
		}
		return url;
	}
}
