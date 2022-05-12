package server;//package Client.server.Server.src;

import model.Magazine;
import persistance.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class Server {
  public static void main(String[] args) throws RemoteException, AlreadyBoundException, SQLException {

    Registry registry= LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
    BookDAOImplementation bookDAO = BookDAOImplementation.getInstance();
    MagazineDAOImplementation magazineDAO = MagazineDAOImplementation.getInstance();
    AdapterBookDAO adapterBookDAO = new AdapterBookDAO(bookDAO);
    AdapterMagazineDAO adapterMagazineDAO = new AdapterMagazineDAO(magazineDAO);
    LibraryUserStorage libraryUserStorage = LibraryUserStorageTest.getInstance();
//    MagazineStorage magazineStorage = MagazineStorageTest.getInstance();
//    BookStorage bookStorage = BookStorageTest.getInstance();





    RemoteBook book = new Communicator(adapterMagazineDAO,adapterBookDAO,libraryUserStorage);
    RemoteMagazine magazine=new Communicator(adapterMagazineDAO, adapterBookDAO,libraryUserStorage);
    RemoteLibraryUser libraryUser  = new Communicator(adapterMagazineDAO, adapterBookDAO,libraryUserStorage);

    registry.bind("book",book);
    registry.bind("magazine",magazine);
    registry.bind("libraryUser",libraryUser);

    System.out.println("server.Server running on " + Registry.REGISTRY_PORT);

  }

}