package client;

import model.LibraryUser;
import model.LoanMagazine;
import model.Magazine;
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

    /**
     * The constructor that create and set the Registry.
     * @param host
     * The name of the host
     * @param port
     * The port number
     * @throws IOException
     * @throws NotBoundException
     */
    public LoanMagazineClientImplementation(String  host, int port) throws IOException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(host,port);
        remoteLoanMagazine = (RemoteLoanMagazine) registry.lookup("loanMagazine");

    }

    /**
     * Add loan magazine method
     * @param loanMagazine
     * The loan magazine passed as an argument
     * @throws SQLException
     * @throws RemoteException
     */
    @Override
    public void addMagazineLoan(LoanMagazine loanMagazine) throws SQLException, RemoteException {
        remoteLoanMagazine.addMagazineLoan(loanMagazine);
    }

    /**
     * Get the available magazines
     * @return
     * All the available magazines
     * @throws SQLException
     * @throws RemoteException
     */
    @Override
    public ArrayList<Magazine> getAvailableMagazineList() throws  RemoteException,SQLException {
        return remoteLoanMagazine.getAvailableMagazineList();
    }

    /**
     * Get a user with a specific social security number
     * @param ssn
     * The social security number passed as an argument
     * @return
     * @throws RemoteException
     */
    @Override public LibraryUser getUser(String ssn)
        throws RemoteException
    {
        return remoteLoanMagazine.getUser(ssn);
    }

    /**
     * Get the loan of the user with the social security number passed as an argument
     * @param ssn
     * The social security number
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<LoanMagazine> getUserLoans(String ssn) throws RemoteException {
        return remoteLoanMagazine.getUserLoans(ssn);
    }

    /**
     * Return magazine method
     * @param loan_id
     * The unique identification number of the loan
     * @throws RemoteException
     */
    @Override public void returnMagazine(int loan_id) throws RemoteException
    {
            remoteLoanMagazine.returnMagazine(loan_id);
    }

    /**
     * Close the remote object
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        UnicastRemoteObject.unexportObject(this,true);
    }
}
