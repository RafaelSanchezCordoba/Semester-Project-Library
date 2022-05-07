package server;

import model.Genre;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteGenre extends Remote {
  void addGenre(Genre genre) throws SQLException, RemoteException;
  void removeGenre(int id) throws SQLException, RemoteException;
  ArrayList<Genre> getGenreList() throws SQLException, RemoteException;
}
