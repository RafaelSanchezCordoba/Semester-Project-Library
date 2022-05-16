package server;

import model.LoanMagazine;
import model.Magazine;

import java.util.ArrayList;

public interface RemoteLoanMagazine {
    void addMagazineLoan(LoanMagazine loanMagazine);
    ArrayList<Magazine> getAvailableMagazineList();
}
