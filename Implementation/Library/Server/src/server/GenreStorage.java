package server;

import model.Genre;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GenreStorage {
    void addGenre(Genre genre) throws SQLException, RemoteException;
    void removeGenre(int id) throws SQLException, RemoteException;
    ArrayList<Genre> getGenreList() throws SQLException, RemoteException;
}
