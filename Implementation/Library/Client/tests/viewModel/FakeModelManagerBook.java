package viewModel;

import mediator.ModelBook;
import model.Book;
import model.GenreList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FakeModelManagerBook implements ModelBook
{
  private ArrayList<Book> list;
  private PropertyChangeSupport support;
  private GenreList genres;

  public FakeModelManagerBook()
  {
    list=new ArrayList<>();
    support=new PropertyChangeSupport(this);
    genres=new GenreList();
  }

  @Override public void addBook(Book book) throws RemoteException, SQLException
  {
    list.add(book);
    support.firePropertyChange("newBook",null,book);
  }

  @Override public void removeBook(int id) throws RemoteException, SQLException
  {
    for (int i=0;i<list.size();i++)
    {
      if (list.get(i).getId()==id)
      {
        list.remove(list.get(i));
      }
    }
    support.firePropertyChange("removeBook",null,id);
  }

  @Override public ArrayList<Book> getBookList()
      throws RemoteException, SQLException
  {
    return list;
  }

  @Override public GenreList getGenreList() throws RemoteException, SQLException
  {
    return genres;
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name,listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
support.removePropertyChangeListener(name,listener);
  }
}
