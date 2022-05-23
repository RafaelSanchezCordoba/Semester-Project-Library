package viewModel;

import mediator.*;

import java.rmi.RemoteException;

public class ViewModelFactory {
    private final AddRemoveBookViewModel bookViewModel;
    private final AddRemoveMagazineViewModel magazineViewModel;
    private final AddRemoveLibrarianViewModel librarianViewModel;
    private final AddRemoveLibraryUserViewModel userViewModel;
    private final LoanMagazineViewModel loanMagazineViewModel;
    private final LoanBookViewModel loanBookViewModel;
    private final ReturnMagazineViewModel returnMagazineViewModel;
    private final ReturnBookViewModel returnBookViewModel;

    public ViewModelFactory(ModelBook modelBook, ModelMagazine modelMagazine, ModelLibrarian modelLibrarian,
        ModelLibraryUser modelLibraryUser, ModelLoanMagazine modelLoanMagazine, ModelLoanBook modelLoanBook) throws RemoteException {
        this.bookViewModel = new AddRemoveBookViewModel(modelBook);
        this.magazineViewModel = new AddRemoveMagazineViewModel(modelMagazine);
        this.librarianViewModel = new AddRemoveLibrarianViewModel(modelLibrarian);
        this.userViewModel = new AddRemoveLibraryUserViewModel(modelLibraryUser);
        this.returnMagazineViewModel = new ReturnMagazineViewModel(modelLoanMagazine);
        this.returnBookViewModel = new ReturnBookViewModel(modelLoanBook);
        this.loanMagazineViewModel = new LoanMagazineViewModel(modelLoanMagazine);
        this.loanBookViewModel=new LoanBookViewModel(modelLoanBook);

    }

    public AddRemoveBookViewModel getBookViewModel() {
        return bookViewModel;
    }

    public AddRemoveMagazineViewModel getMagazineViewModel() {
        return magazineViewModel;
    }
    public  AddRemoveLibrarianViewModel getLibrarianViewModel(){
        return librarianViewModel;
    }

    public AddRemoveLibraryUserViewModel getUserViewModel(){
        return userViewModel;
    }
    public LoanMagazineViewModel getLoanMagazineViewModel() {
        return loanMagazineViewModel;
    }
    public LoanBookViewModel getLoanBookViewModel() {
        return loanBookViewModel;
    }
    public ReturnMagazineViewModel getReturnMagazineViewModel() {
        return returnMagazineViewModel;
    }
    public ReturnBookViewModel getReturnBookViewModel(){
        return returnBookViewModel;
    }
}
