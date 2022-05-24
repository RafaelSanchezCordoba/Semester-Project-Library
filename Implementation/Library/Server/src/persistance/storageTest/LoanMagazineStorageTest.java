package persistance.storageTest;

import model.*;
import server.storage.LoanMagazineStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanMagazineStorageTest implements LoanMagazineStorage
{

  private int counter = 0;
  private ArrayList<LoanMagazine> loanMagazinesList;

  private  LibraryUser libraryUser;
  private ArrayList<Magazine> availableMagazines;
  ArrayList<LoanMagazine> userLoanMagazines;


  private static LoanMagazineStorageTest instance;

  private LoanMagazineStorageTest (){
    loanMagazinesList = new ArrayList<>();
    availableMagazines = new ArrayList<>();
    userLoanMagazines = new ArrayList<>();

    libraryUser = new LibraryUser("12456789909","gianni","nove","palla");
    loanMagazinesList.add(new LoanMagazine(2,"7778"));
    availableMagazines.add(new Magazine("prisma","ahse",4,"pan",null));
    userLoanMagazines.add(new LoanMagazine(2,"7778"));

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
    return availableMagazines;
  }

  @Override public LibraryUser getUser(String ssn)
  {
    return libraryUser;
  }

  @Override public ArrayList<Magazine> getLoanedMagazines(String ssn)
      throws RemoteException
  {
    return null;
  }

  @Override public ArrayList<LoanMagazine> getUsersLoans(String ssn)
      throws RemoteException
  {
    return userLoanMagazines;
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
