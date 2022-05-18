package persistance.adapters;

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
        }
        return null;
    }
}
