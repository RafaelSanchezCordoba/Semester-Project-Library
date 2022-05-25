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

    public ModelManagerLoanMagazine(LoanMagazineClient client) {
        this.client = client;
        this.support = new PropertyChangeSupport(this);
    }

    @Override
    public void addMagazineLoan(LoanMagazine loanMagazine) throws  RemoteException {
        client.addMagazineLoan(loanMagazine);
        support.firePropertyChange("newLoanMagazine",null,loanMagazine);
    }

    @Override
    public ArrayList<Magazine> getAvailableMagazineList()
        throws RemoteException
    {

        return client.getAvailableMagazineList();
    }

    @Override public LibraryUser getUser(String ssn)
        throws  RemoteException
    {

        return client.getUser(ssn);
    }

    @Override
    public ArrayList<LoanMagazine> getUserLoans(String ssn) throws RemoteException {
        return client.getUserLoans(ssn);
    }

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
