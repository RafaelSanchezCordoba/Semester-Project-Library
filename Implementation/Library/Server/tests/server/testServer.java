package server;

import persistance.storageTest.BookStorageTest;
import persistance.storageTest.LibrarianStorageTest;
import persistance.storageTest.LibraryUserStorageTest;
import persistance.storageTest.MagazineStorageTest;
import server.storage.BookStorage;
import server.storage.LibrarianStorage;
import server.storage.LibraryUserStorage;
import server.storage.MagazineStorage;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class testServer
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException, SQLException
  {

    Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

    LibraryUserStorage libraryUserStorage = LibraryUserStorageTest.getInstance();
    MagazineStorage magazineStorage = MagazineStorageTest.getInstance();
    BookStorage bookStorage = BookStorageTest.getInstance();
    LibrarianStorage librarianStorage= LibrarianStorageTest.getInstance();

    RemoteBook book = new Communicator(magazineStorage, bookStorage,librarianStorage,
        libraryUserStorage);
    RemoteMagazine magazine = new Communicator(magazineStorage, bookStorage,librarianStorage,
        libraryUserStorage);
    RemoteLibrarian librarian = new Communicator(magazineStorage, bookStorage,librarianStorage, libraryUserStorage);
    RemoteLibraryUser libraryUser = new Communicator(magazineStorage, bookStorage, librarianStorage, libraryUserStorage);

    registry.bind("book", book);
    registry.bind("magazine", magazine);
    registry.bind("librarian", librarian);
    registry.bind("libraryUser", libraryUser);


    System.out.println("server.Server running on " + Registry.REGISTRY_PORT);
  }
}
