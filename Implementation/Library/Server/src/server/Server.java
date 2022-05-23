package server;//package Client.server.Server.src;


import persistance.adapters.AdapterLibraryUserDAO;
import persistance.DAO_implementation.BookDAOImplementation;
import persistance.DAO_implementation.LibrarianDAOImplementation;
import persistance.DAO_implementation.MagazineDAOImplementation;
import persistance.DAO_implementation.LibraryUserDAOImplementation;
import persistance.adapters.AdapterBookDAO;
import persistance.adapters.AdapterLibrarianDAO;
import persistance.adapters.AdapterMagazineDAO;

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
    LibrarianDAOImplementation librarianDAO = LibrarianDAOImplementation.getInstance();
    LibraryUserDAOImplementation userDAOImplementation = LibraryUserDAOImplementation.getInstance();


    AdapterBookDAO adapterBookDAO = new AdapterBookDAO(bookDAO);
    AdapterMagazineDAO adapterMagazineDAO = new AdapterMagazineDAO(magazineDAO);
    AdapterLibrarianDAO adapterLibrarianDAO = new AdapterLibrarianDAO(librarianDAO);
    AdapterLibraryUserDAO adapterLibraryUserDAO = new AdapterLibraryUserDAO(userDAOImplementation);


    //    MagazineStorage magazineStorage = MagazineStorageTest.getInstance();
//    BookStorage bookStorage = BookStorageTest.getInstance();


    RemoteBook book = new Communicator(adapterMagazineDAO,adapterBookDAO, adapterLibrarianDAO,adapterLibraryUserDAO);
    RemoteMagazine magazine=new Communicator(adapterMagazineDAO, adapterBookDAO, adapterLibrarianDAO,adapterLibraryUserDAO);
    RemoteLibrarian librarian = new Communicator(adapterMagazineDAO, adapterBookDAO, adapterLibrarianDAO,adapterLibraryUserDAO);
    RemoteLibraryUser libraryUser  = new Communicator(adapterMagazineDAO, adapterBookDAO, adapterLibrarianDAO, adapterLibraryUserDAO);


    registry.bind("book",book);
    registry.bind("magazine",magazine);
    registry.bind("librarian", librarian);
    registry.bind("libraryUser",libraryUser);

    System.out.println("server.Server running on " + Registry.REGISTRY_PORT);

  }

}