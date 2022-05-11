package server;//package Client.server.Server.src;
import persistance.DAO_implementation.BookDAOImplementation;
import persistance.DAO_implementation.LibrarianDAOImplementation;
import persistance.DAO_implementation.MagazineDAOImplementation;
import persistance.adapters.AdapterBookDAO;
import persistance.adapters.AdapterLibrarianDAO;
import persistance.adapters.AdapterMagazineDAO;
import persistance.storageTest.BookStorageTest;
import persistance.storageTest.LibrarianStorageTest;
import persistance.storageTest.MagazineStorageTest;
import server.storage.BookStorage;
import server.storage.LibrarianStorage;
import server.storage.MagazineStorage;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class Server {
  public static void main(String[] args) throws RemoteException, AlreadyBoundException, SQLException {

    Registry registry= LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
//    MagazineStorage magazineStorage = MagazineStorageTest.getInstance();
//    BookStorage bookStorage = BookStorageTest.getInstance();
//    LibrarianStorage librarianStorage = LibrarianStorageTest.getInstance();
    BookDAOImplementation bookDAO = BookDAOImplementation.getInstance();
    MagazineDAOImplementation magazineDAO = MagazineDAOImplementation.getInstance();
    LibrarianDAOImplementation librarianDAO = LibrarianDAOImplementation.getInstance();
    AdapterBookDAO adapterBookDAO = new AdapterBookDAO(bookDAO);
    AdapterMagazineDAO adapterMagazineDAO = new AdapterMagazineDAO(magazineDAO);
    AdapterLibrarianDAO adapterLibrarianDAO = new AdapterLibrarianDAO(librarianDAO);

    RemoteBook book = new Communicator(adapterMagazineDAO,adapterBookDAO, adapterLibrarianDAO);
    RemoteMagazine magazine=new Communicator(adapterMagazineDAO, adapterBookDAO, adapterLibrarianDAO);
    RemoteLibrarian librarian = new Communicator(adapterMagazineDAO, adapterBookDAO, adapterLibrarianDAO);

    registry.bind("book",book);
    registry.bind("magazine",magazine);
    registry.bind("librarian", librarian);
    
    System.out.println("server.Server running on " + Registry.REGISTRY_PORT);

  }

}