package server;

import model.Librarian;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteLibrarian extends Remote
{
  void  addLibrarian(Librarian librarian) throws RemoteException;
  void removeLibrarian(Librarian librarian) throws RemoteException;
  ArrayList<Librarian> getLibrarianList() throws RemoteException;
  void removeLibrarianBySsn(int ssn) throws RemoteException;
}
