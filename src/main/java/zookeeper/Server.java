package zookeeper;

/**
 * 发布服务
 */
public class Server {

	public static void main(String[] args) throws Exception {
		/*if (args.length != 2) {
			System.err.println("please using command: java Server <rmi_host> <rmi_port>");
			System.exit(-1);
		}
		String host = args[0];*/
		String host = "localhost";
		//int port = Integer.parseInt(args[1]);
		int port = 8081;
		ServiceProvider provider = new ServiceProvider();
		
		HelloServiceImpl helloService = new HelloService();
		
		provider.publish(helloService, host, port);
		
		Thread.sleep(Long.MAX_VALUE);
	}
}
