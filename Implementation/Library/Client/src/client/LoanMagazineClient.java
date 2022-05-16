package client;

import model.LoanMagazine;
import model.Magazine;

import java.io.Closeable;
import java.util.ArrayList;

public interface LoanMagazineClient extends Closeable {
    void addMagazineLoan(LoanMagazine loanMagazine);
    ArrayList<Magazine> getAvailableMagazineList();
}
