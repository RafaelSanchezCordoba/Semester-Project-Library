package server;//package Client.server.Server.src;

import persistance.AdapterMagazine;
import persistance.MagazineDAO;
import persistance.MagazineDAOImplementation;
import persistance.MagazineStorage;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args)
        throws RemoteException, AlreadyBoundException, SQLException
    {

        Registry registry= LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

        MagazineDAO magazineDAO= MagazineDAOImplementation.getInstance();
        MagazineStorage magazineStorage= new AdapterMagazine(magazineDAO);
        RemoteBook book = new Communicator(magazineStorage);
        RemoteMagazine magazine=new Communicator(magazineStorage);
        registry.bind("book",book);
        registry.bind("magazine",magazine);
        System.out.println("server.Server running on " + Registry.REGISTRY_PORT);

    }

}
