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
    public void addMagazineLoan(LoanMagazine loanMagazine) throws SQLException, RemoteException {
        client.addMagazineLoan(loanMagazine);
        support.firePropertyChange("newLoanMagazine",null,loanMagazine);
    }

    @Override
    public ArrayList<Magazine> getAvailableMagazineList()
        throws RemoteException, SQLException
    {

        return client.getAvailableMagazineList();
    }

    @Override public LibraryUser getUser(String ssn)
        throws  RemoteException
    {

        return client.getUser(ssn);
    }

    @Override
    public ArrayList<LoanMagazine> getLoans(String ssn) throws RemoteException {
        return client.getLoans(ssn);
    }

    @Override
    public void returnMagazine(int loan_id) throws RemoteException {
        client.returnMagazine(loan_id);
    }

    /**
     *
     * @param listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     *
     * @param name
     * @param listener
     */
    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }

    /**
     *
     * @param listener
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }


    /**
     *
     * @param name
     * @param listener
     */
    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        support.removePropertyChangeListener(name, listener);
    }
}
