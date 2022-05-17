package client;

import model.LoanMagazine;
import model.Magazine;

import java.io.Closeable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LoanMagazineClient extends Closeable {
    void addMagazineLoan(LoanMagazine loanMagazine) throws SQLException, RemoteException;
    ArrayList<Magazine> getAvailableMagazineList() throws SQLException, RemoteException;
}
