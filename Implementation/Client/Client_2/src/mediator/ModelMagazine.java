package mediator;

import model.Magazine;

import java.rmi.RemoteException;

public interface ModelMagazine extends PropertyChangeSubject{
    void addMagazine(Magazine magazine) throws RemoteException;
    void removeMagazine(int id) throws RemoteException;
}
