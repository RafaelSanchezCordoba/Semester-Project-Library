package server;

import model.Librarian;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteLibrarian extends Remote
{
  void  addLibrarian(Librarian librarian) throws RemoteException, SQLException;
<<<<<<< Updated upstream
  void removeLibrarian(int SSN) throws RemoteException, SQLException;
=======
<<<<<<< HEAD
  void removeLibrarian(String SSN) throws RemoteException, SQLException;
=======
  void removeLibrarian(int SSN) throws RemoteException, SQLException;
>>>>>>> 7e718619a38eaa66df02ddfe9864aa4893dd4e7c
>>>>>>> Stashed changes
  ArrayList<Librarian> getLibrarianList() throws RemoteException, SQLException;
}
