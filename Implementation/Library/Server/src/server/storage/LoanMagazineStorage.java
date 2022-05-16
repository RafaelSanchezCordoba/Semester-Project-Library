package server.storage;

import model.LoanMagazine;
import model.Magazine;

import java.util.ArrayList;

public interface LoanMagazineStorage {
    void addMagazineLoan(LoanMagazine loanMagazine);
    ArrayList<Magazine> getAvailableMagazineList();
}
