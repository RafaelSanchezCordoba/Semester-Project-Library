package server;

import storageTest.*;
import server.storage.*;

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
    LoanMagazineStorage magazineLoanStorage= LoanMagazineStorageTest.getInstance();
    LoanBookStorage bookLoanStorage= LoanBookStorageTest.getInstance();

    RemoteBook book = new Communicator(magazineStorage, bookStorage,librarianStorage,
        libraryUserStorage, magazineLoanStorage, bookLoanStorage);
    RemoteMagazine magazine = new Communicator(magazineStorage, bookStorage,librarianStorage,
        libraryUserStorage, magazineLoanStorage, bookLoanStorage);
    RemoteLibrarian librarian = new Communicator(magazineStorage, bookStorage,librarianStorage, libraryUserStorage, magazineLoanStorage, bookLoanStorage);
    RemoteLibraryUser libraryUser = new Communicator(magazineStorage, bookStorage, librarianStorage, libraryUserStorage, magazineLoanStorage, bookLoanStorage);

    registry.bind("book", book);
    registry.bind("magazine", magazine);
    registry.bind("librarian", librarian);
    registry.bind("libraryUser", libraryUser);


    System.out.println("server.Server running on " + Registry.REGISTRY_PORT);
  }
}
