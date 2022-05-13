package viewModel;

import mediator.ModelBook;
import mediator.ModelLibraryUser;
import mediator.ModelMagazine;

import java.rmi.RemoteException;

public class ViewModelFactory {
    private final AddRemoveBookViewModel bookViewModel;
    private final AddRemoveMagazineViewModel magazineViewModel;
    private final AddRemoveLibraryUserViewModel userViewModel;

    public ViewModelFactory(ModelBook modelBook, ModelMagazine modelMagazine, ModelLibraryUser modelLibraryUser) throws RemoteException {
        this.bookViewModel = new AddRemoveBookViewModel(modelBook);
        this.magazineViewModel = new AddRemoveMagazineViewModel(modelMagazine);
        this.userViewModel = new AddRemoveLibraryUserViewModel(modelLibraryUser);
    }

    public AddRemoveBookViewModel getBookViewModel() {
        return bookViewModel;
    }

    public AddRemoveMagazineViewModel getMagazineViewModel() {
        return magazineViewModel;
    }

    public AddRemoveLibraryUserViewModel getUserViewModel(){
        return userViewModel;
    }
}
