package rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {

    private static final Logger logger = LoggerFactory.getLogger(RMIServer.class);

    public static void main(String[] args) {
        try {
            int port = 1099;
            String url = "rmi://localhost:" + port + "/rmi.HelloService";

            // 启动 RMI 注册表
            LocateRegistry.createRegistry(port);
            HelloService helloService = new HelloServiceImpl();

            // 绑定服务
            Naming.rebind(url, helloService);
            logger.info("RMI Server is running on port: {}", port);
        } catch (Exception e) {
            logger.error("Exception occurred in RMIServer: {}", e.getMessage(), e);
        }
    }
}
