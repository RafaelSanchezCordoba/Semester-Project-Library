package client;

import model.Librarian;

import java.io.Closeable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface LibrarianClient extends Closeable
{
  void addLibrarian(Librarian librarian) throws RemoteException;
  void removeLibrarian(Librarian librarian) throws RemoteException;
  ArrayList<Librarian> getLibrarianList() throws RemoteException;
  void removeLibrarianBySsn(int ssn) throws RemoteException;
  String toStringArray() throws RemoteException;
}
