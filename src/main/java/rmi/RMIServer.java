package rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
/**
 * 服务端
 * @author Ezreal
 */
public class RMIServer {

	public static void main(String[] args) throws Exception {
		int port = 1099;
		String url = "rmi://localhost:1099/rmi.HelloService";
		LocateRegistry.createRegistry(port);
		Naming.rebind(url, new HelloService());
	}
}
