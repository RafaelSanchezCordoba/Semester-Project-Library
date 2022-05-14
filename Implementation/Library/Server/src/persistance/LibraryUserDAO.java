package persistance;

import model.LibraryUser;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LibraryUserDAO
{
  void addLibraryUser(LibraryUser libraryUser) throws SQLException;
  void removeLibraryUser(String ssn)throws SQLException;
  ArrayList<LibraryUser> getLibraryUserList() throws SQLException;
}
