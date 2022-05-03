package mediator;

import model.Librarian;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ModelLibrarian extends PropertyChangeSubject{

    void addLibrarian(Librarian librarian) throws RemoteException;

    void removeLibrarian(String ssn) throws RemoteException;

    ArrayList<Object[]> getLibrarianList() throws RemoteException;
}
