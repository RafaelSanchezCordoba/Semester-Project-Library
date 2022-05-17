package mediator;

import model.LoanMagazine;
import model.Magazine;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ModelLoanMagazine extends PropertyChangeSubject{
    void addMagazineLoan(LoanMagazine loanMagazine) throws SQLException, RemoteException;
    ArrayList<Magazine> getAvailableMagazineList() throws SQLException, RemoteException;
}
