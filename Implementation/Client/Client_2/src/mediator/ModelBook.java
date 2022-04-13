package mediator;

import model.Book;

import java.rmi.RemoteException;

/**
 * The model interface for book.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 09/04/22.
 */
public interface ModelBook extends PropertyChangeSubject{
    /**
     * Add the book given as an argument.
     * @param book
     * The book to add.
     * @throws RemoteException
     */
    void addBook(Book book) throws RemoteException;

    /**
     *
     * @param id
     * @throws RemoteException
     */
    void removeBook(int id) throws RemoteException;
}
