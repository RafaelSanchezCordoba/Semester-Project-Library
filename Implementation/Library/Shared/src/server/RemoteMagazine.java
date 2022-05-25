package server;

//import dk.via.remote.observer.RemotePropertyChangeListener;
import model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteMagazine extends Remote {
    void addMagazine(Magazine magazine) throws RemoteException;
    void removeMagazine(int id) throws RemoteException;
    ArrayList<Magazine> getMagazineList() throws  RemoteException;

}
