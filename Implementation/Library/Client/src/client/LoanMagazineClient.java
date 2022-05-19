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
}
