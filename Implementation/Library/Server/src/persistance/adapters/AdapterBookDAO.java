package persistance.adapters;

import model.Book;
import model.GenreList;
import persistance.DAO.BookDAO;
import server.storage.BookStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdapterBookDAO implements BookStorage {

  private BookDAO bookDAO;
  
  public AdapterBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }
  
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
