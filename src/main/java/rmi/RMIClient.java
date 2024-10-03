package rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.Naming;

public class RMIClient {

    private static final Logger logger = LoggerFactory.getLogger(RMIClient.class);

    public static void main(String[] args) {
        try {
            String url = "rmi://localhost:1099/rmi.HelloService";
            HelloService helloService = (HelloService) Naming.lookup(url);

            String result = helloService.sayHello("hanmeimei");
            logger.info("Received response: {}", result);
            System.out.println(result);
        } catch (Exception e) {
            logger.error("Exception occurred in RMIClient: {}", e.getMessage(), e);
        }
    }
}
