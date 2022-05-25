package server.storage;

import model.Magazine;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface MagazineStorage
{
  void addMagazine(Magazine magazine) throws  RemoteException;
  void removeMagazine(int id) throws RemoteException;
  ArrayList<Magazine> getMagazineList() throws  RemoteException;
}
