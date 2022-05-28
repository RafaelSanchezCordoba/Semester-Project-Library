package server.storage;

import model.LibraryUser;
import model.LoanMagazine;
import model.Magazine;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Public LoanMagazineStorage interface that is
 * implemented by AdapterLoanMagazineDAO class
 * The purpose of this class is to provide abstract
 * interface with all methods for class AdapterLoanMagazineDAO
 */
public interface LoanMagazineStorage {

    /**
     * Method adding a magazine to loan
     * @param loanMagazine
     * LoanMagazine
     * @throws RemoteException
     * SQL exception with the message "No keys generated" can be caught
     * from LoanMagazineDAOImplementation class
     */
    void addMagazineLoan(LoanMagazine loanMagazine) throws RemoteException;

    /**
     * Method returning ArrayList of all magazine to loan
     * @return
     * ArrayList of magazines available to loan
     * @throws RemoteException
     */
    ArrayList<Magazine> getAvailableMagazineList() throws RemoteException;

    /**
     * Method returning a user after it's ssn
     * @param ssn
     * Social security number
     * @return
     * User with given ssn number
     * @throws RemoteException
     */
    LibraryUser getUser(String ssn) throws RemoteException;

    /**
     * Method returning ArrayList of magazine that are loaned
     * @param ssn
     * Social security magazines
     * @return
     * ArrayList of loaned magazines
     * @throws RemoteException
     */
    ArrayList<Magazine> getLoanedMagazines(String ssn)throws RemoteException;

    /**
     * Method returning loans of user after it's ssn
     * @param ssn
     * Social security number
     * @return
     * Loans of user after it's ssn
     * @throws RemoteException
     */
    ArrayList<LoanMagazine> getUsersLoans(String ssn) throws RemoteException;

    /**
     * Method returning a magazine from being loaned to the library
     * @param id_magazine
     * ID number of loan
     * @throws RemoteException
     */
    void returnMagazine(int id_magazine) throws RemoteException;
}
