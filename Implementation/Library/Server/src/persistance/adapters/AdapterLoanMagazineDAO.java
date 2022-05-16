package persistance.adapters;

import model.LoanMagazine;
import model.Magazine;
import persistance.DAO.LoanMagazineDAO;
import server.storage.LoanMagazineStorage;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdapterLoanMagazineDAO implements LoanMagazineStorage {
    private LoanMagazineDAO loanMagazineDAO;

    public AdapterLoanMagazineDAO(LoanMagazineDAO loanMagazineDAO) {
        this.loanMagazineDAO = loanMagazineDAO;
    }

    @Override
    public void addMagazineLoan(LoanMagazine loanMagazine) throws SQLException {
        loanMagazineDAO.addLoanMagazine(loanMagazine);
    }

    @Override
    public ArrayList<Magazine> getAvailableMagazineList() throws SQLException {
        return loanMagazineDAO.getAvailableMagazineList();
    }
}
