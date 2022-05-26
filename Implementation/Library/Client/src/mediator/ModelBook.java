package mediator;

import model.Book;
import model.Genre;
import model.GenreList;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The model interface for book.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 09/04/22.
 */
public interface ModelBook extends PropertyChangeSubject{
    /**
     * Add book method
     * @param book
     * The book passed as an argument
     * @throws RemoteException
     */
    void addBook(Book book) throws RemoteException;

    /**
     * Remove a book with a specific id passed as an argument
     * @param id
     * The unique identification number
     * @throws RemoteException
     */
    void removeBook(int id) throws RemoteException;

    /**
     * Get book list method
     * @return
     * All the books in the list
     * @throws RemoteException
     */
    ArrayList<Book> getBookList() throws RemoteException;

    /**
     * Get genre list method
     * @return
     * The genre list
     * @throws RemoteException
     */
    GenreList getGenreList() throws RemoteException;
}
