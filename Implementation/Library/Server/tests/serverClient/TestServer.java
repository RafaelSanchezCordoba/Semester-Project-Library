package serverClient;

import persistance.storageTest.BookStorageTest;
import persistance.storageTest.LibrarianStorageTest;
import persistance.storageTest.MagazineStorageTest;
import server.Communicator;
import server.RemoteBook;
import server.RemoteLibrarian;
import server.RemoteMagazine;
import server.storage.BookStorage;
import server.storage.LibrarianStorage;
import server.storage.MagazineStorage;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TestServer
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException
  {
    Registry registry= LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
    MagazineStorage magazineStorage = MagazineStorageTest.getInstance();
    BookStorage bookStorage = BookStorageTest.getInstance();
    LibrarianStorage librarianStorage = LibrarianStorageTest.getInstance();




    RemoteBook book = new Communicator(magazineStorage,bookStorage, librarianStorage);
    RemoteMagazine magazine=new Communicator(magazineStorage, bookStorage, librarianStorage);
    RemoteLibrarian librarian = new Communicator(magazineStorage, bookStorage, librarianStorage);
    registry.bind("book",book);
    registry.bind("magazine",magazine);
    registry.bind("librarian", librarian);
    System.out.println("server.Server running on " + Registry.REGISTRY_PORT);

  }
}
