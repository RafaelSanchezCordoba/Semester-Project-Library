package persistance.adapters;

import model.LibraryUser;
import model.LoanMagazine;
import model.Magazine;
import persistance.DAO.LoanMagazineDAO;
import server.storage.LoanMagazineStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Public class AdapterLoanMagazineDAO implementing LoanMagazineStorage interface
 * This adapter's purpose is to minimize the direct dependency between
 * application code and data access code.
 */
public class AdapterLoanMagazineDAO implements LoanMagazineStorage {
    private LoanMagazineDAO loanMagazineDAO;

    /**
     * AdapterLoanMagazineDAO one parameter constructor
     * @param loanMagazineDAO
     * Parameter from interface LoanMagazineDAO
     */
    public AdapterLoanMagazineDAO(LoanMagazineDAO loanMagazineDAO) {
        this.loanMagazineDAO = loanMagazineDAO;
    }

    /**
     * Method adding a magazine to loan
     * @param loanMagazine
     * MagazineBook
     * @throws RemoteException
     * SQL exception with the message "No keys generated" can be caught
     * from LoanMagazineDAOImplementation class
     */
    @Override
    public void addMagazineLoan(LoanMagazine loanMagazine) throws
        RemoteException
    {
        try
        {
            loanMagazineDAO.addLoanMagazine(loanMagazine);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * Method returning ArrayList of all magazines to loan
     * @return
     * ArrayList of magazines available to loan
     * @throws RemoteException
     */
    @Override
    public ArrayList<Magazine> getAvailableMagazineList() throws RemoteException
    {
        try
        {
            return loanMagazineDAO.getAvailableMagazineList();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RemoteException(e.getMessage());
        }

    }

    /**
     * Method returning a user after it's ssn
     * @param ssn
     * Social security number
     * @return
     * User with given ssn number
     * @throws RemoteException
     */
    @Override public LibraryUser getUser(String ssn) throws RemoteException
    {
        try

     {
      return loanMagazineDAO.getLibraryUser(ssn);
     }catch (SQLException e){
            e.printStackTrace();
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * Method returning ArrayList of magazine that are loaned
     * @param ssn
     * Social security number
     * @return
     * ArrayList of loaned magazines
     * @throws RemoteException
     */
    @Override public ArrayList<Magazine> getLoanedMagazines(String ssn)
        throws RemoteException
    {
        try
        {
            return loanMagazineDAO.getLoanedMagazines(ssn);
        }catch (SQLException e){
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * Method returning loans of user after it's ssn
     * @param ssn
     * Social security number
     * @return
     * Loans of user after it's ssn
     * @throws RemoteException
     */
    @Override public ArrayList<LoanMagazine> getUsersLoans(String ssn)
        throws RemoteException
    {
        try
        {
          return   loanMagazineDAO.getUserLoans(ssn);
        }catch (SQLException e){
            throw  new RemoteException(e.getMessage());
        }
    }

    /**
     * Method returning a magazine from being loaned to the library
     * @param id_magazine
     * ID number of loan
     * @throws RemoteException
     */
    @Override public void returnMagazine(int id_magazine) throws RemoteException
    {
        try
        {
            loanMagazineDAO.returnMagazine(id_magazine);
        }catch (SQLException e){
            throw new RemoteException(e.getMessage());
        }
    }
}
