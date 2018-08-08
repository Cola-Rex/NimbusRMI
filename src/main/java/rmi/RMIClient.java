package rmi;

import java.rmi.Naming;
/**
 * 客户端
 * @author Ezreal
 */
public class RMIClient {

	public static void main(String[] args) throws Exception{
		String url = "rmi://localhost:1099/rmi.HelloService";
		HelloServiceImpl helloServiceImpl = (HelloServiceImpl) Naming.lookup(url);
		String result = helloServiceImpl.sayHello("hanmeimei");
		System.out.println(result);
	}

}
