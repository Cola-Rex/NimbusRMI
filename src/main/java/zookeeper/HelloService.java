package zookeeper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloService extends UnicastRemoteObject implements HelloServiceImpl{

	private static final long serialVersionUID = 1L;

	protected HelloService() throws RemoteException {
	}
	
	public String sayHello(String name) throws RemoteException {
		return String.format("Hello %s", name);
	}
}
