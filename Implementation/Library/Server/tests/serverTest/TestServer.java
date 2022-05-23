package serverTest;

import persistance.storageTest.LoanBookStorageTest;
import persistance.storageTest.LoanMagazineStorageTest;
import server.Communicator;
import server.RemoteLoanBook;
import server.RemoteLoanMagazine;
import server.Server;
import server.storage.LoanBookStorage;
import server.storage.LoanMagazineStorage;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TestServer {

  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException
  {
    Registry registry= LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
    LoanMagazineStorage loanMagazineStorage = LoanMagazineStorageTest.getInstance();
    LoanBookStorage loanBookStorage = LoanBookStorageTest.getInstance();

    RemoteLoanMagazine loanMagazine = new TestComunicator(loanBookStorage,loanMagazineStorage);
    RemoteLoanBook loanBook = new TestComunicator(loanBookStorage,loanMagazineStorage);

    registry.bind("loanMagazine", loanMagazine);
    registry.bind("loanBook",loanBook);


    System.out.println("server running on port:"+Registry.REGISTRY_PORT);


  }

}

