package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    public HelloServiceImpl() throws RemoteException {
        super();
        logger.info("HelloServiceImpl instance created.");
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        if (name == null || name.trim().isEmpty()) {
            logger.warn("Invalid name received.");
            throw new RemoteException("Name cannot be null or empty.");
        }

        String message = String.format("Hello, %s!", name);
        logger.info("Greeting generated: {}", message);
        return message;
    }
}
