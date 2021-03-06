package mediator;

import model.LibraryUser;
import model.LoanMagazine;
import model.Magazine;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ModelLoanMagazine extends PropertyChangeSubject {
    /**
     * Add loan magazine method
     * @param loanMagazine
     * The loan magazine passed as an argument
     * @throws RemoteException
     */
    void addMagazineLoan(LoanMagazine loanMagazine) throws  RemoteException;

    /**
     * Get the available magazines
     * @return
     * All the available magazines
     * @throws RemoteException
     */
    ArrayList<Magazine> getAvailableMagazineList() throws  RemoteException;

    /**
     * Get a user with a specific social security number
     * @param ssn
     * The social security number passed as an argument
     */
    LibraryUser getUser(String ssn) throws RemoteException;

    /**
     * Get the loan of the user with the social security number passed as an argument
     * @param ssn
     * The social security number
     * @return
     * @throws RemoteException
     */
    ArrayList<LoanMagazine> getUserLoans(String ssn) throws RemoteException;

    /**
     * Return magazine method
     * @param loan_id
     * The unique identification number of the loan
     * @throws RemoteException
     */
    void returnMagazine(int loan_id) throws RemoteException;
}
