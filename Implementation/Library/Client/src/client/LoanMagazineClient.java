package client;

import model.LibraryUser;
import model.LoanMagazine;
import model.Magazine;

import java.io.Closeable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LoanMagazineClient extends Closeable {
    void addMagazineLoan(LoanMagazine loanMagazine) throws SQLException, RemoteException;
    ArrayList<Magazine> getAvailableMagazineList() throws SQLException, RemoteException;
    LibraryUser getUser(String ssn) throws RemoteException;
    ArrayList<Magazine> getLoanedMagazines(String ssn) throws RemoteException;
    void returnMagazine(int loan_id) throws RemoteException;
    LoanMagazine getLoan(int loan_id) throws RemoteException;
}
