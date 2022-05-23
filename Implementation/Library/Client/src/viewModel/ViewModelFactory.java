package viewModel;

import mediator.ModelBook;
import mediator.ModelLibrarian;
import mediator.ModelLibraryUser;
import mediator.ModelMagazine;

import java.rmi.RemoteException;

public class ViewModelFactory {
    private final AddRemoveBookViewModel bookViewModel;
    private final AddRemoveMagazineViewModel magazineViewModel;
    private final AddRemoveLibrarianViewModel librarianViewModel;
    private final AddRemoveLibraryUserViewModel userViewModel;

    public ViewModelFactory(ModelBook modelBook, ModelMagazine modelMagazine, ModelLibrarian modelLibrarian,
        ModelLibraryUser modelLibraryUser) throws RemoteException {
        this.bookViewModel = new AddRemoveBookViewModel(modelBook);
        this.magazineViewModel = new AddRemoveMagazineViewModel(modelMagazine);
        this.librarianViewModel = new AddRemoveLibrarianViewModel(modelLibrarian);
        this.userViewModel = new AddRemoveLibraryUserViewModel(modelLibraryUser);
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
}
