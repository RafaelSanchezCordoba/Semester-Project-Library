package client;

import model.Librarian;
import server.RemoteLibrarian;

import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class LibrarianClientImplementation extends UnicastRemoteObject implements LibrarianClient
{
  private final RemoteLibrarian remoteLibrarian;

  public LibrarianClientImplementation(String host, int port) throws IOException,
      NotBoundException
  {
    Registry registry = LocateRegistry.getRegistry(host,port);
    remoteLibrarian= (RemoteLibrarian) registry.lookup("librarian");
  }


  public void addLibrarian(Librarian librarian) throws RemoteException
  {
    remoteLibrarian.addLibrarian(librarian);
  }


  public void removeLibrarian(Librarian librarian) throws RemoteException
  {
    remoteLibrarian.removeLibrarian(librarian);
  }


  public ArrayList<Librarian> getLibrarianList() throws RemoteException
  {
    return remoteLibrarian.getLibrarianList();
  }

  public void removeLibrarianBySsn(int ssn) throws RemoteException{
    remoteLibrarian.removeLibrarianBySsn(ssn);
  }
  public String toStringArray() throws RemoteException{
    return remoteLibrarian.getLibrarianList().toString();
  }


  public void close(){
    try
    {
      UnicastRemoteObject.unexportObject(this,true);
    }
    catch (NoSuchObjectException e)
    {
      e.printStackTrace();
    }
  }
}
