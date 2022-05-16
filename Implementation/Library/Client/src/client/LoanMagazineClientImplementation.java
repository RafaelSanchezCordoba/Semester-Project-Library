package client;

import model.LoanMagazine;
import model.Magazine;
import server.RemoteBook;
import server.RemoteLoanMagazine;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class LoanMagazineClientImplementation extends UnicastRemoteObject implements LoanMagazineClient{
    private RemoteLoanMagazine remoteLoanMagazine;

    public LoanMagazineClientImplementation(String  host, int port) throws IOException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(host,port);
        remoteLoanMagazine = (RemoteLoanMagazine) registry.lookup("book");

    }

    @Override
    public void addMagazineLoan(LoanMagazine loanMagazine) {
        remoteLoanMagazine.addMagazineLoan(loanMagazine);
    }

    @Override
    public ArrayList<Magazine> getAvailableMagazineList() {
        return remoteLoanMagazine.getAvailableMagazineList();
    }

    @Override
    public void close() throws IOException {
        UnicastRemoteObject.unexportObject(this,true);
    }
}
