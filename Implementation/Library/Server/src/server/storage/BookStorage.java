package server.storage;

import model.Book;
import model.Genre;
import model.GenreList;
import model.Magazine;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookStorage
{
  void addBook(Book book) throws SQLException, RemoteException;
  void removeBook(int id) throws SQLException, RemoteException;
  ArrayList<Book> getBookList() throws SQLException, RemoteException;
  GenreList getGenreList() throws SQLException, RemoteException;
}
