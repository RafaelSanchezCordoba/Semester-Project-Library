package mediator;

import model.LoanMagazine;
import model.Magazine;

import java.util.ArrayList;

public interface ModelLoanMagazine extends PropertyChangeSubject{
    void addMagazineLoan(LoanMagazine loanMagazine);
    ArrayList<Magazine> getAvailableMagazineList();
}
