package server;

import model.LoanMagazine;
import model.Magazine;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteLoanMagazine {
    void addMagazineLoan(LoanMagazine loanMagazine) throws SQLException;
    ArrayList<Magazine> getAvailableMagazineList() throws SQLException;
}
