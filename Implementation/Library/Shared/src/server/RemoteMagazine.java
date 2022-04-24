package server;

import dk.via.remote.observer.RemotePropertyChangeListener;
import model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface RemoteMagazine extends Remote {
    void addMagazine(Magazine magazine) throws RemoteException, SQLException;
    void removeMagazine(int id) throws RemoteException, SQLException;

}
