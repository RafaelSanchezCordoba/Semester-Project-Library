import server.RemoteBook;
import server.RemoteMagazine;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Registry registry= LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

        RemoteBook book = new Comunicator();
        RemoteMagazine magazine=new Comunicator();
        registry.bind("book",book);
        registry.bind("magazine",magazine);
        System.out.println("Server running on " + Registry.REGISTRY_PORT);
    }

}
