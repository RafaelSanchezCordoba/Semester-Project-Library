package persistance.storageTest;

import model.LoanMagazine;
import model.Magazine;
import server.storage.LoanMagazineStorage;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoanMagazineStorageTest implements LoanMagazineStorage
{

  private int counter = 0;
  private ArrayList<LoanMagazine> loanMagazinesList;
  private ArrayList<Magazine> magazineList;

  private static LoanMagazineStorageTest instance;

  private LoanMagazineStorageTest (){
    loanMagazinesList = new ArrayList<>();
    magazineList = new ArrayList<>();
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
}
