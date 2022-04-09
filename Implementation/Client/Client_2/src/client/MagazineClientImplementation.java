package client;

import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import model.Magazine;
import server.RemoteMagazine;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MagazineClientImplementation extends UnicastRemoteObject implements MagazineClient{

  private RemoteMagazine magazine;
  private PropertyChangeSupport  support;
  public MagazineClientImplementation(String  host, int port) throws IOException,
          NotBoundException
  {
    
    Registry registry = LocateRegistry.getRegistry(host, port);
    magazine = (RemoteMagazine) registry.lookup("magazine");
    
  }

  @Override public void addMagazine(Magazine magazine)
  {
    magazine.add
  }

  @Override public void removeMagazine(int id)
  {

  }


  @Override public void close() throws IOException
  {

  }


}
