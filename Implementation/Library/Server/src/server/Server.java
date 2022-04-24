package server;//package Client.server.Server.src;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        Registry registry= LocateRegistry.createRegistry(Registry.REGISTRY_PORT);



        RemoteBook book = new Communicator();
        RemoteMagazine magazine=new Communicator();
        registry.bind("book",book);
        registry.bind("magazine",magazine);
        System.out.println("server.Server running on " + Registry.REGISTRY_PORT);

    }

}
