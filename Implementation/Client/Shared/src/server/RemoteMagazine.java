package server;

import dk.via.remote.observer.RemotePropertyChangeListener;
import model.Magazine;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteMagazine extends Remote {
    void addMagazine(Magazine magazine) throws RemoteException;
    void removeMagazine(int id) throws RemoteException;

}
