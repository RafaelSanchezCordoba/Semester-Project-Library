package server;//package Client.server.Server.src;

import model.Librarian;
import persistance.MagazineStorageTest;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        Registry registry= LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        MagazineStorage storage = new MagazineStorageTest();



        RemoteBook book = new Communicator(storage);
        RemoteMagazine magazine=new Communicator(storage);
        RemoteLibrarian librarian = new Communicator(storage);
        registry.bind("book",book);
        registry.bind("magazine",magazine);
        registry.bind("librarian",librarian);
        System.out.println("server.Server running on " + Registry.REGISTRY_PORT);

    }

}
