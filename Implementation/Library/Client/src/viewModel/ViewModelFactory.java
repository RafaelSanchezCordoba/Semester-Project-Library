package viewModel;

import mediator.ModelBook;
import mediator.ModelMagazine;

import java.rmi.RemoteException;

public class ViewModelFactory {
    private final AddRemoveBookViewModel bookViewModel;
    private final AddRemoveMagazineViewModel magazineViewModel;

    public ViewModelFactory(ModelBook modelBook, ModelMagazine modelMagazine)
        throws RemoteException
    {
        this.bookViewModel = new AddRemoveBookViewModel(modelBook);
        this.magazineViewModel = new AddRemoveMagazineViewModel(modelMagazine);
    }

    public AddRemoveBookViewModel getBookViewModel() {
        return bookViewModel;
    }

    public AddRemoveMagazineViewModel getMagazineViewModel() {
        return magazineViewModel;
    }
}
