package mediator;

import client.BookClient;
import model.Book;
import model.Genre;
import model.GenreList;
import model.LibraryUser;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * The model manager for book.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 09/04/22
 */

public class ModelManagerBook implements ModelBook {
    private final BookClient client;
    private final PropertyChangeSupport support;

    /**
     * Public constructor that set the client and a property change support
     * @param client
     * The book client
     */
    public ModelManagerBook(BookClient client) {
        this.client = client;
        this.support = new PropertyChangeSupport(this);
    }

    /**
     * Get book list method
     * @return
     * All the books in the list
     * @throws RemoteException
     * @throws SQLException
     */
    public ArrayList<Book> getBookList() throws SQLException, RemoteException {
        return client.getBookList();
    }

    /**
     * Get genre list method
     * @return
     * The genre list
     * @throws RemoteException
     * @throws SQLException
     */
    @Override public GenreList getGenreList() throws RemoteException, SQLException {
        return client.getGenreList();
    }

    /**
     * Add book method
     * @param book
     * The book passed as an argument
     * @throws RemoteException
     * @throws SQLException
     */
    @Override
    public void addBook(Book book) throws RemoteException, SQLException {
        client.addBook(book);
        support.firePropertyChange("newBook", null, book);
    }

    /**
     * Remove a book with a specific id passed as an argument
     * @param id
     * The unique identification number
     * @throws RemoteException
     * @throws SQLException
     */
    @Override
    public void removeBook(int id) throws RemoteException, SQLException {
        client.removeBook(id);
        support.firePropertyChange("removeBook", null, id);
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
