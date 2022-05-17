package viewModel;

import mediator.ModelBook;
import mediator.ModelLibrarian;
import mediator.ModelLoanMagazine;
import mediator.ModelMagazine;

import java.rmi.RemoteException;

public class ViewModelFactory {
    private final AddRemoveBookViewModel bookViewModel;
    private final AddRemoveMagazineViewModel magazineViewModel;
    private final AddRemoveLibrarianViewModel librarianViewModel;
    private final LoanMagazineViewModel loanMagazineViewModel;

    public ViewModelFactory(ModelBook modelBook, ModelMagazine modelMagazine, ModelLibrarian modelLibrarian, ModelLoanMagazine modelLoanMagazine) throws RemoteException {
        this.bookViewModel = new AddRemoveBookViewModel(modelBook);
        this.magazineViewModel = new AddRemoveMagazineViewModel(modelMagazine);
        this.librarianViewModel = new AddRemoveLibrarianViewModel(modelLibrarian);
        this.loanMagazineViewModel = new LoanMagazineViewModel(modelLoanMagazine);
    }


    public AddRemoveBookViewModel getBookViewModel() {
        return bookViewModel;
    }
    public LoanMagazineViewModel getLoanMagazineViewModel() {
        return loanMagazineViewModel;
    }
    public AddRemoveMagazineViewModel getMagazineViewModel() {
        return magazineViewModel;
    }
    public  AddRemoveLibrarianViewModel getLibrarianViewModel(){
        return librarianViewModel;
    }
}
