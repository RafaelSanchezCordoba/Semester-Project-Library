package mediator;

import client.BookClient;
import model.Book;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class ModelManagerBook implements ModelBook {
    private final BookClient client;
    private final PropertyChangeSupport support;

    public ModelManagerBook(BookClient client) {
        this.client = client;
        this.support = new PropertyChangeSupport(this);
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        client.addBook(book);
    }

    @Override
    public void removeBook(int id) throws RemoteException {
        client.removeBook(id);
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
