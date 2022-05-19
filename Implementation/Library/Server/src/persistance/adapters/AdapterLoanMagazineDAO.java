package persistance.adapters;

import model.LibraryUser;
import model.LoanMagazine;
import model.Magazine;
import persistance.DAO.LoanMagazineDAO;
import server.storage.LoanMagazineStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdapterLoanMagazineDAO implements LoanMagazineStorage {
    private LoanMagazineDAO loanMagazineDAO;

    public AdapterLoanMagazineDAO(LoanMagazineDAO loanMagazineDAO) {
        this.loanMagazineDAO = loanMagazineDAO;
    }

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

    @Override public ArrayList<LoanMagazine> getUsersLoans(String ssn)
        throws RemoteException
    {
        try
        {
          return   loanMagazineDAO.getUsersLoans(ssn);
        }catch (SQLException e){
            throw  new RemoteException(e.getMessage());
        }
    }

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
