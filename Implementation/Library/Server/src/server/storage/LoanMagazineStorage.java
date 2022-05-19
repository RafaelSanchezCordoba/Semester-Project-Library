package server.storage;

import model.LibraryUser;
import model.LoanMagazine;
import model.Magazine;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LoanMagazineStorage {
    void addMagazineLoan(LoanMagazine loanMagazine) throws SQLException,
        RemoteException;
    ArrayList<Magazine> getAvailableMagazineList() throws SQLException,
        RemoteException;
    LibraryUser getUser(String ssn) throws RemoteException;
    ArrayList<Magazine> getLoanedMagazines(String ssn)throws RemoteException;
    ArrayList<LoanMagazine> getUsersLoans(String ssn) throws RemoteException;
    void returnMagazine(int id_magazine) throws RemoteException;
}
