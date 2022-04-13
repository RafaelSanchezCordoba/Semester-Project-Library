package mediator;

import client.BookClient;
import model.Book;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
<<<<<<< HEAD
import java.util.ArrayList;

=======
/**
 * The model manager for book.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 09/04/22
 */
>>>>>>> 140f6a0c353514d0b2e9badd881b8031c2386050
public class ModelManagerBook implements ModelBook {
    private final BookClient client;
    private final PropertyChangeSupport support;

    public ModelManagerBook(BookClient client) {
        this.client = client;
        this.support = new PropertyChangeSupport(this);
    }

    public ArrayList<Object[]> getBookList(){
        try
        {
            return client.getBookList();
        }catch (RemoteException e){
            e.printStackTrace();
        }
       return null;
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
