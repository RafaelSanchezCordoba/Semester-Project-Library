package server;//package Client.server.Server.src;


import persistance.DAO_implementation.*;
import persistance.adapters.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class Server {
  /**
   * Main method that run the server
   */
  public static void main(String[] args) throws RemoteException, AlreadyBoundException, SQLException {

    Registry registry= LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
    BookDAOImplementation bookDAO = BookDAOImplementation.getInstance();
    MagazineDAOImplementation magazineDAO = MagazineDAOImplementation.getInstance();
    LibrarianDAOImplementation librarianDAO = LibrarianDAOImplementation.getInstance();
    LibraryUserDAOImplementation userDAOImplementation = LibraryUserDAOImplementation.getInstance();
    LoanMagazineDAOImplementation loanMagazineDAO = LoanMagazineDAOImplementation.getInstance();
    LoanBookDAOImplementation loanBookDAO= LoanBookDAOImplementation.getInstance();

    AdapterBookDAO adapterBookDAO = new AdapterBookDAO(bookDAO);
    AdapterMagazineDAO adapterMagazineDAO = new AdapterMagazineDAO(magazineDAO);
    AdapterLibrarianDAO adapterLibrarianDAO = new AdapterLibrarianDAO(librarianDAO);
    AdapterLibraryUserDAO adapterLibraryUserDAO = new AdapterLibraryUserDAO(userDAOImplementation);
    AdapterLoanMagazineDAO adapterLoanMagazineDAO = new AdapterLoanMagazineDAO(loanMagazineDAO);
    AdapterLoanBookDAO adapterLoanBookDAO=new AdapterLoanBookDAO(loanBookDAO);

    RemoteBook book = new Communicator(adapterMagazineDAO,adapterBookDAO, adapterLibrarianDAO,adapterLibraryUserDAO,adapterLoanMagazineDAO,adapterLoanBookDAO);
    RemoteMagazine magazine=new Communicator(adapterMagazineDAO, adapterBookDAO, adapterLibrarianDAO,adapterLibraryUserDAO,adapterLoanMagazineDAO,adapterLoanBookDAO);
    RemoteLibrarian librarian = new Communicator(adapterMagazineDAO, adapterBookDAO, adapterLibrarianDAO,adapterLibraryUserDAO,adapterLoanMagazineDAO,adapterLoanBookDAO);
    RemoteLibraryUser libraryUser  = new Communicator(adapterMagazineDAO, adapterBookDAO, adapterLibrarianDAO, adapterLibraryUserDAO,adapterLoanMagazineDAO,adapterLoanBookDAO);
    RemoteLoanMagazine loanMagazine = new Communicator(adapterMagazineDAO, adapterBookDAO, adapterLibrarianDAO, adapterLibraryUserDAO,adapterLoanMagazineDAO,adapterLoanBookDAO);
    RemoteLoanBook loanBook = new Communicator(adapterMagazineDAO, adapterBookDAO, adapterLibrarianDAO, adapterLibraryUserDAO,adapterLoanMagazineDAO,adapterLoanBookDAO);


    registry.bind("book",book);
    registry.bind("magazine",magazine);
    registry.bind("librarian", librarian);
    registry.bind("libraryUser",libraryUser);
    registry.bind("loanMagazine", loanMagazine);
    registry.bind("loanBook",loanBook);


    System.out.println("server.Server running on " + Registry.REGISTRY_PORT);
  }
}