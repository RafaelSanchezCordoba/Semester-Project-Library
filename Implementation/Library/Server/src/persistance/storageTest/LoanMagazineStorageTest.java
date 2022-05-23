package persistance.storageTest;

import model.LibraryUser;
import model.LoanMagazine;
import model.Magazine;
import server.storage.LoanMagazineStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanMagazineStorageTest implements LoanMagazineStorage
{

  private int counter = 0;
  private ArrayList<LoanMagazine> loanMagazinesList;
  private ArrayList<Magazine> magazineList;
  private  LibraryUser libraryUser;

  private static LoanMagazineStorageTest instance;

  private LoanMagazineStorageTest (){
    loanMagazinesList = new ArrayList<>();
    magazineList = new ArrayList<>();
    libraryUser = new LibraryUser("12456789909","gianni","nove","palla");

  }

  public  static synchronized LoanMagazineStorageTest getInstance(){
    if (instance==null){
      instance = new LoanMagazineStorageTest();
    }
    return instance;
  }
  @Override public void addMagazineLoan(LoanMagazine loanMagazine)
      throws SQLException
  {
    loanMagazine.setId(counter);
    loanMagazinesList.add(loanMagazine);
    counter++;

  }

  @Override public ArrayList<Magazine> getAvailableMagazineList()
      throws SQLException
  {
    return magazineList;
  }

  @Override public LibraryUser getUser(String ssn) throws RemoteException
  {
    if (ssn.equals(libraryUser.getSSN()))
    {
      return libraryUser;
    }
    else
    {
      return null;
    }
  }
}
