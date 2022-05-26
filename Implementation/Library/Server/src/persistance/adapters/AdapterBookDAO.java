package persistance.adapters;

import model.Book;
import model.GenreList;
import persistance.DAO.BookDAO;
import server.storage.BookStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Public class AdapterBook for DAO implementing BookStorage interface
 * This adapter's purpose is to minimize the direct dependency between
 * application code and data access code.
 */
public class AdapterBookDAO implements BookStorage {

  private BookDAO bookDAO;

  /**
   * AdapterBookDAO one parameter constructor.
   * @param bookDAO
   * Parameter from interface BookDAO
   */
  public AdapterBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }

  /**
   * Method adding a book.
   * @param book
   * Book
   * @throws RemoteException
   * SQL exception with the message "No keys generated" can be caught
   * from BookDAOImplementation class
   */
  @Override public void addBook(Book book) throws  RemoteException {
    try
    {
      bookDAO.addBook(book);
    }
    catch (SQLException e)
    {
      throw new RemoteException(e.getMessage(), e);
    }
  }

  /**
   * Method removing a book after its id.
   * @param id
   * Id of the book
   * @throws RemoteException
   * SQL exception with the message "no delete because the book is currently lent"
   * can be caught from BookDAOImplementation
   */
  @Override public void removeBook(int id) throws RemoteException {
    try
    {
      bookDAO.removeBook(id);
    }
    catch (SQLException e)
    {
      throw new RemoteException(e.getMessage(), e);
    }
  }

  /**
   * Method returning list of the books as ArrayList
   * @return
   * ArrayList of books
   * @throws RemoteException
   */
  @Override public ArrayList<Book> getBookList() throws  RemoteException {
    try
    {
      return bookDAO.getBookList();
    }
    catch (SQLException e)
    {
      throw new RemoteException(e.getMessage(), e);
    }
  }

  /**
   * Method returning list of the genres as
   * ArrayList from GenreList class
   * @return
   * List of genres as ArrayList
   * @throws RemoteException
   */
  @Override public GenreList getGenreList()
      throws  RemoteException
  {
    try
    {
      return bookDAO.getGenreList();
    } catch ( SQLException e){
      throw new RemoteException(e.getMessage(), e);
    }

  }
}
