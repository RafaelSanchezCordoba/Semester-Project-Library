package server.storage;

import model.LoanMagazine;
import model.Magazine;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoanMagazineStorage {
    void addMagazineLoan(LoanMagazine loanMagazine) throws SQLException;
    ArrayList<Magazine> getAvailableMagazineList() throws SQLException;
}
