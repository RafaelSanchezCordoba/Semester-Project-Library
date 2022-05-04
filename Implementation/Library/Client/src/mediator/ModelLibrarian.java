package mediator;

import model.Librarian;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * The model Interface for Librarian.
 * @author Alexandru Dulghier.
 * @version 1.0 27/04/22
 */
public interface ModelLibrarian extends PropertyChangeSubject
{
  void addLibrarian(Librarian librarian) throws RemoteException;
  void removeLibrarian(Librarian librarian) throws  RemoteException;
  ArrayList<Librarian> getLibrarianList() throws RemoteException;
  String toStringArray() throws RemoteException;
}
