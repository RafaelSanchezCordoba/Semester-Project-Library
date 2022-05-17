package server;//package Client.server.Server.src;

import model.LoanMagazine;
import persistance.DAO.LoanMagazineDAO;
import persistance.DAO_implementation.BookDAOImplementation;
import persistance.DAO_implementation.LibrarianDAOImplementation;
import persistance.DAO_implementation.LoanMagazineDAOImplementation;
import persistance.DAO_implementation.MagazineDAOImplementation;
import persistance.adapters.AdapterBookDAO;
import persistance.adapters.AdapterLibrarianDAO;
import persistance.adapters.AdapterLoanMagazineDAO;
import persistance.adapters.AdapterMagazineDAO;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLData;
import java.sql.SQLException;

public class Server {
  public static void main(String[] args) throws RemoteException, AlreadyBoundException, SQLException {

    Registry registry= LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
    BookDAOImplementation bookDAO = BookDAOImplementation.getInstance();
    MagazineDAOImplementation magazineDAO = MagazineDAOImplementation.getInstance();
    LibrarianDAOImplementation librarianDAO = LibrarianDAOImplementation.getInstance();
    LoanMagazineDAOImplementation loanMagazineDAO = LoanMagazineDAOImplementation.getInstance();
    AdapterBookDAO adapterBookDAO = new AdapterBookDAO(bookDAO);
    AdapterMagazineDAO adapterMagazineDAO = new AdapterMagazineDAO(magazineDAO);
    AdapterLoanMagazineDAO adapterLoanMagazineDAO = new AdapterLoanMagazineDAO(loanMagazineDAO);
//    MagazineStorage magazineStorage = MagazineStorageTest.getInstance();
//    BookStorage bookStorage = BookStorageTest.getInstance();
    AdapterLibrarianDAO adapterLibrarianDAO = new AdapterLibrarianDAO(librarianDAO);
    LoanMagazineDAOImplementation loanMagazineDAOImplementation = LoanMagazineDAOImplementation.getInstance();






    RemoteBook book = new Communicator(adapterMagazineDAO,adapterBookDAO, adapterLibrarianDAO, adapterLoanMagazineDAO);
    RemoteMagazine magazine=new Communicator(adapterMagazineDAO, adapterBookDAO, adapterLibrarianDAO, adapterLoanMagazineDAO);
    RemoteLibrarian librarian = new Communicator(adapterMagazineDAO, adapterBookDAO, adapterLibrarianDAO, adapterLoanMagazineDAO);
    RemoteLoanMagazine loanMagazine = new Communicator(adapterMagazineDAO, adapterBookDAO, adapterLibrarianDAO, adapterLoanMagazineDAO);

    registry.bind("book",book);
    registry.bind("magazine",magazine);
    registry.bind("librarian", librarian);
    registry.bind("loanMagazine", loanMagazine);

    System.out.println("server.Server running on " + Registry.REGISTRY_PORT);

  }

}