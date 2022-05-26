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

    /**
     * View model factory constructor that take the model and create a new view model
     * @param modelBook
     * The model of the book
     * @param modelMagazine
     * The model of the magazine
     * @param modelLibrarian
     * The model of the librarian
     * @param modelLibraryUser
     * The model of the library user
     * @param modelLoanMagazine
     * The model of the loan magazine
     * @param modelLoanBook
     * The model of the loan book
     * @throws RemoteException
     */
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

    /**
     * Get book view model method
     * @return
     * The book view model
     */
    public AddRemoveBookViewModel getBookViewModel() {
        return bookViewModel;
    }

    /**
     * Get magazine view model method
     * @return
     * The magazine view model
     */
    public AddRemoveMagazineViewModel getMagazineViewModel() {
        return magazineViewModel;
    }

    /**
     * Get librarian view model method
     * @return
     * The librarian view model
     */
    public  AddRemoveLibrarianViewModel getLibrarianViewModel(){
        return librarianViewModel;
    }

    /**
     * Get library user view model method
     * @return
     * The library user view model
     */
    public AddRemoveLibraryUserViewModel getUserViewModel(){
        return userViewModel;
    }

    /**
     * Get loan magazine view model method
     * @return
     * The loan magazine view model method
     */
    public LoanMagazineViewModel getLoanMagazineViewModel() {
        return loanMagazineViewModel;
    }

    /**
     * Get loan book view model method
     * @return
     * The loan book view model
     */
    public LoanBookViewModel getLoanBookViewModel() {
        return loanBookViewModel;
    }

    /**
     * Get return magazine view model method
     * @return
     * The return magazine view model
     */
    public ReturnMagazineViewModel getReturnMagazineViewModel() {
        return returnMagazineViewModel;
    }

    /**
     * Get return book view model method
     * @return
     * The return book view model
     */
    public ReturnBookViewModel getReturnBookViewModel(){
        return returnBookViewModel;
    }
}
