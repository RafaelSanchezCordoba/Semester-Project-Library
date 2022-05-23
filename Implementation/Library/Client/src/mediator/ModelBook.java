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
     * @throws SQLException
     */
    void addBook(Book book) throws RemoteException, SQLException;

    /**
     * Remove a book with a specific id passed as an argument
     * @param id
     * The unique identification number
     * @throws RemoteException
     * @throws SQLException
     */
    void removeBook(int id) throws RemoteException, SQLException;

    /**
     * Get book list method
     * @return
     * All the books in the list
     * @throws RemoteException
     * @throws SQLException
     */
    ArrayList<Book> getBookList() throws RemoteException, SQLException;

    /**
     * Get genre list method
     * @return
     * The genre list
     * @throws RemoteException
     * @throws SQLException
     */
    GenreList getGenreList() throws RemoteException, SQLException;
}
