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


  private static LoanMagazineStorageTest instance;

  private LoanMagazineStorageTest (){
    loanMagazinesList = new ArrayList<>();


  }

  public  static synchronized LoanMagazineStorageTest getInstance(){
    if (instance==null){
      instance = new LoanMagazineStorageTest();
    }
    return instance;
  }
  @Override public void addMagazineLoan(LoanMagazine loanMagazine)

  {
    loanMagazine.setId(counter);
    loanMagazinesList.add(loanMagazine);

    counter++;

  }

  @Override public ArrayList<Magazine> getAvailableMagazineList()

  {
    throw new IllegalArgumentException();
  }

  @Override public LibraryUser getUser(String ssn)
  {
    throw new IllegalArgumentException();
  }

  @Override public ArrayList<Magazine> getLoanedMagazines(String ssn)
      throws RemoteException
  {
    return null;
  }

  @Override public ArrayList<LoanMagazine> getUsersLoans(String ssn)
      throws RemoteException
  {
    return null;
  }

  @Override public void returnMagazine(int id_magazine) throws RemoteException
  {
    for (int i = 0; i <loanMagazinesList.size() ; i++)
    {
      if(id_magazine == loanMagazinesList.get(i).getId()){
        loanMagazinesList.remove(loanMagazinesList.get(i));
        break;
      }
    }
  }
}
