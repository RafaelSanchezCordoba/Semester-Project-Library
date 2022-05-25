package mediator;

import client.LoanMagazineClient;
import model.LibraryUser;
import model.LoanMagazine;
import model.Magazine;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManagerLoanMagazine implements ModelLoanMagazine {
    private final LoanMagazineClient client;
    private final PropertyChangeSupport support;

    /**
     * Public constructor that set the client and a property change support
     * @param client
     * The loan magazine client
     */
    public ModelManagerLoanMagazine(LoanMagazineClient client) {
        this.client = client;
        this.support = new PropertyChangeSupport(this);
    }

    /**
     * Add loan magazine method
     * @param loanMagazine
     * The loan magazine passed as an argument
     * @throws SQLException
     * @throws RemoteException
     */
    @Override
    public void addMagazineLoan(LoanMagazine loanMagazine) throws  RemoteException {
        client.addMagazineLoan(loanMagazine);
        support.firePropertyChange("newLoanMagazine",null,loanMagazine);
    }

    /**
     * Get the available magazines
     * @return
     * All the available magazines
     * @throws SQLException
     * @throws RemoteException
     */
    @Override
    public ArrayList<Magazine> getAvailableMagazineList()
        throws RemoteException
    {

        return client.getAvailableMagazineList();
    }

    /**
     * Get a user with a specific social security number
     * @param ssn
     * The social security number passed as an argument
     * @return
     * @throws RemoteException
     */
    @Override public LibraryUser getUser(String ssn)
        throws  RemoteException
    {

        return client.getUser(ssn);
    }

    /**
     * Get the loan of the user with the social security number passed as an argument
     * @param ssn
     * The social security number
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<LoanMagazine> getUserLoans(String ssn) throws RemoteException {
        return client.getUserLoans(ssn);
    }

    /**
     * Return magazine method
     * @param loan_id
     * The unique identification number of the loan
     * @throws RemoteException
     */
    @Override
    public void returnMagazine(int loan_id) throws RemoteException {
        client.returnMagazine(loan_id);
        support.firePropertyChange("removeLoanMagazine", null, loan_id);
    }

    /**
     * Add property change listener, just with the listener
     * @param listener
     * The listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Add property change listener with a name and the listener
     * @param name
     * The name
     * @param listener
     * The listener
     */
    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }

    /**
     * Remove property change listener, just with the listener
     * @param listener
     * The listener
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Remove property change listener with a name and the listener
     * @param name
     * The name
     * @param listener
     * The listener
     */
    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        support.removePropertyChangeListener(name, listener);
    }
}
