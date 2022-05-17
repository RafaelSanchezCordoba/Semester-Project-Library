package mediator;

import client.LoanMagazineClient;
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
    public ArrayList<Magazine> getAvailableMagazineList() throws SQLException, RemoteException {

        return client.getAvailableMagazineList();
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
