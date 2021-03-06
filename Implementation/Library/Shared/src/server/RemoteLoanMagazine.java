package server;

import model.LibraryUser;
import model.LoanMagazine;
import model.Magazine;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteLoanMagazine extends Remote {
    void addMagazineLoan(LoanMagazine loanMagazine) throws  RemoteException;
    ArrayList<Magazine> getAvailableMagazineList() throws  RemoteException;
    LibraryUser getUser(String ssn) throws RemoteException;
    ArrayList<LoanMagazine> getUserLoans(String ssn) throws RemoteException;
    void returnMagazine(int loan_id) throws RemoteException;

}
