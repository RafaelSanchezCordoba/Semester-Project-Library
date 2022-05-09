package persistance.adapters;

import model.Genre;
import persistance.DAO.GenreDAO;
import server.storage.GenreStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdapterGenreDAO implements GenreStorage {
  private GenreDAO genreDAO;
  
  public AdapterGenreDAO(GenreDAO genreDAO) {
    this.genreDAO = genreDAO;
  }
  
  @Override public void addGenre(Genre genre) throws SQLException, RemoteException {
    try
    {
      genreDAO.addGenre(genre);
    }
    catch (SQLException e)
    {
      throw new RemoteException(e.getMessage(), e);
    }
  }

  @Override public void removeGenre(int id) throws SQLException, RemoteException {
    try
    {
      genreDAO.removeGenre(id);
    }
    catch (SQLException e)
    {
      throw new RemoteException(e.getMessage(), e);
    }
  }

  @Override public ArrayList<Genre> getGenreList() throws SQLException, RemoteException {
    try
    {
      return genreDAO.getGenreList();
    }
    catch (SQLException e)
    {
      throw new RemoteException(e.getMessage(), e);
    }
  }
}
