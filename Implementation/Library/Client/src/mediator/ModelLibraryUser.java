package mediator;

import model.LibraryUser;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The model interface for book
 * @author Franciszek Jan Nurkiewicz
 * @version 12.05.2022
 */
public interface ModelLibraryUser extends PropertyChangeSubject
{
  void addLibraryUser(LibraryUser libraryUser) throws RemoteException, SQLException;

  void removeLibraryUser(String ssn) throws RemoteException, SQLException;

  ArrayList<LibraryUser> getLibraryUserList() throws RemoteException, SQLException;

}
