package mediator;

import model.LoanMagazine;
import model.Magazine;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FakeModelManagerLoanMagazine implements ModelLoanMagazine{
    private ArrayList<LoanMagazine> list;
    private ArrayList<Magazine> availailbleMagazines;
    private PropertyChangeSupport support;

    public FakeModelManagerLoanMagazine(){
        list = new ArrayList<>();
        availailbleMagazines = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }

    @Override public void addMagazineLoan(LoanMagazine loanMagazine) throws SQLException, RemoteException {
        list.add(loanMagazine);
        support.firePropertyChange("newLoanMagazine",null,loanMagazine);
    }

    @Override
    public ArrayList<Magazine> getAvailableMagazineList() throws SQLException, RemoteException {
        return availailbleMagazines;
    }


    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        support.removePropertyChangeListener(name, listener);
    }
}
