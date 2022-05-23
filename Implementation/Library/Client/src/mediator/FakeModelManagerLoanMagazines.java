package mediator;

import model.LibraryUser;
import model.LoanMagazine;
import model.Magazine;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FakeModelManagerLoanMagazines implements ModelLoanMagazine
{
  private LibraryUser user;
  private ArrayList<LoanMagazine> loanMagazines;
  private ArrayList<Magazine> availableMagazines;
  private PropertyChangeSupport support;

  public FakeModelManagerLoanMagazines(){
    loanMagazines = new ArrayList<>();
    availableMagazines = new ArrayList<>();
    user=  new LibraryUser("1234567891234","Rosa","Lopez","dfghhjmkkmj");
    support = new PropertyChangeSupport(this);
  }

  @Override public void addMagazineLoan(LoanMagazine loanMagazine) throws
      SQLException, RemoteException
  {
    loanMagazines.add(loanMagazine);
    support.firePropertyChange("newLoanMagazine",null,loanMagazine);
  }

  @Override
  public ArrayList<Magazine> getAvailableMagazineList() throws SQLException, RemoteException {
    return availableMagazines;
  }

  @Override
  public LibraryUser getUser(String ssn) throws RemoteException {
    return user;
  }

  @Override public ArrayList<LoanMagazine> getUserLoans(String ssn)
      throws RemoteException
  {
    return loanMagazines;
  }

  @Override public void returnMagazine(int loan_id) throws RemoteException
  {
    for (int i = 0; i <loanMagazines.size() ; i++)
    {
      if(loan_id==loanMagazines.get(i).getId()){
        loanMagazines.remove(loanMagazines.get(i).getId());
        break;
      }
    }
    support.firePropertyChange("removeLoanBook",null,loan_id);
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  @Override
  public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
    support.addPropertyChangeListener(name, listener);
  }

  @Override
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }

  @Override
  public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
    support.removePropertyChangeListener(name, listener);
  }
}
