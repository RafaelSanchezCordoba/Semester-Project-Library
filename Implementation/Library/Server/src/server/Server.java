package server;//package Client.server.Server.src;
import fakeStorage.FakeStorage;
import fakeStorage.FakeStorageImplementation;
import persistance.storageTest.BookStorageTest;
import persistance.storageTest.MagazineStorageTest;
import server.storage.BookStorage;
import server.storage.MagazineStorage;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
  public static void main(String[] args) throws RemoteException, AlreadyBoundException {

    Registry registry= LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
    MagazineStorage magazineStorage = MagazineStorageTest.getInstance();
    BookStorage bookStorage = BookStorageTest.getInstance();
    FakeStorage librarianStorage = new FakeStorageImplementation();




    RemoteBook book = new Communicator(magazineStorage,bookStorage, librarianStorage);
    RemoteMagazine magazine=new Communicator(magazineStorage, bookStorage, librarianStorage);
    RemoteLibrarian librarian = new Communicator(magazineStorage, bookStorage, librarianStorage);
    registry.bind("book",book);
    registry.bind("magazine",magazine);
    registry.bind("librarian", librarian);
    System.out.println("server.Server running on " + Registry.REGISTRY_PORT);

  }

}