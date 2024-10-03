package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloService extends Remote {

    /**
     * Say hello to the user with a message.
     * @param name the name of the user
     * @return the greeting message
     * @throws RemoteException if the remote method call fails
     */
    String sayHello(String name) throws RemoteException;
}
