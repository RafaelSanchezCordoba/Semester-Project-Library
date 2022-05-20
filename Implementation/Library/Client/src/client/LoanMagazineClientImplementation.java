package client;

import model.LibraryUser;
import model.LoanMagazine;
import model.Magazine;
import server.RemoteBook;
import server.RemoteLoanMagazine;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanMagazineClientImplementation extends UnicastRemoteObject implements LoanMagazineClient{
    private RemoteLoanMagazine remoteLoanMagazine;

    public LoanMagazineClientImplementation(String  host, int port) throws IOException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(host,port);
        remoteLoanMagazine = (RemoteLoanMagazine) registry.lookup("loanMagazine");

    }

    @Override
    public void addMagazineLoan(LoanMagazine loanMagazine) throws SQLException, RemoteException {
        remoteLoanMagazine.addMagazineLoan(loanMagazine);
    }

    @Override
    public ArrayList<Magazine> getAvailableMagazineList() throws  RemoteException,SQLException {
        return remoteLoanMagazine.getAvailableMagazineList();
    }

    @Override public LibraryUser getUser(String ssn)
        throws RemoteException
    {
        return remoteLoanMagazine.getUser(ssn);
    }

    @Override
    public ArrayList<LoanMagazine> getLoans(String ssn) throws RemoteException {
        return remoteLoanMagazine.getLoans(ssn);
    }

    @Override public void returnMagazine(int loan_id) throws RemoteException
    {
            remoteLoanMagazine.returnMagazine(loan_id);
    }

    @Override
    public void close() throws IOException {
        UnicastRemoteObject.unexportObject(this,true);
    }
}
