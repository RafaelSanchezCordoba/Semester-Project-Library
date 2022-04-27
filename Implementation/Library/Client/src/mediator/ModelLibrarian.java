package mediator;

import model.Librarian;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ModelLibrarian extends PropertyChangeSubject
{
  void addLibrarian(Librarian librarian) throws RemoteException;
  void removeLibrarian(Librarian librarian) throws  RemoteException;
  ArrayList<Librarian> getLibrarianList() throws RemoteException;
  String toStringArray() throws RemoteException;
}
