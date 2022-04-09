package client;


import model.Magazine;

import java.io.Closeable;
import java.rmi.RemoteException;

public interface MagazineClient extends Closeable
{
  void addMagazine(Magazine magazine) throws RemoteException;
  void removeMagazine(int id) throws RemoteException;

}
