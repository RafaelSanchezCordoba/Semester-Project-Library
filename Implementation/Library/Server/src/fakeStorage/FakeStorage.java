package fakeStorage;

import model.Librarian;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface FakeStorage
{
  void addLibrarian(Librarian librarian);
  void removeLibrarian(Librarian librarian);
  ArrayList<Librarian> getLibrarianList();
}
