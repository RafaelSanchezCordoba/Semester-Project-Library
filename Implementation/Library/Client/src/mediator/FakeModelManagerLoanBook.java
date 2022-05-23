package mediator;

import model.Book;
import model.LibraryUser;
import model.LoanBook;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FakeModelManagerLoanBook implements ModelLoanBook{
    private ArrayList<LoanBook> list;
    private ArrayList<Book> availaibleBooks;
    private PropertyChangeSupport support;

    public FakeModelManagerLoanBook(){
        list = new ArrayList<>();
        availaibleBooks = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }

    @Override public void addLoanBook(LoanBook loanBook){
        list.add(loanBook);
        support.firePropertyChange("newLoanBook",null, loanBook);
    }

    @Override
    public ArrayList<Book> getAvailableBooks() throws SQLException, RemoteException {
        return availaibleBooks;
    }


    @Override
    public LibraryUser getUser(String ssn) throws RemoteException {
        return null;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name,listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        support.removePropertyChangeListener(name,listener);
    }
}