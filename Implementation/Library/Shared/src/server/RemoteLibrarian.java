package server;

import model.Librarian;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteLibrarian extends Remote
{
  void  addLibrarian(Librarian librarian) throws RemoteException, SQLException;
  void removeLibrarian(long SSN) throws RemoteException, SQLException;
  ArrayList<Librarian> getLibrarianList() throws RemoteException, SQLException;
}
