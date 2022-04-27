package viewModel;

import mediator.ModelBook;
import mediator.ModelLibrarian;
import mediator.ModelMagazine;

import java.rmi.RemoteException;

public class ViewModelFactory {
    private final AddRemoveBookViewModel bookViewModel;
    private final AddRemoveMagazineViewModel magazineViewModel;
    private final AddRemoveLibrarianViewModel librarianViewModel;

    public ViewModelFactory(ModelBook modelBook, ModelMagazine modelMagazine,
        ModelLibrarian modelLibrarian)
        throws RemoteException
    {
        this.bookViewModel = new AddRemoveBookViewModel(modelBook);
        this.magazineViewModel = new AddRemoveMagazineViewModel(modelMagazine);
        this.librarianViewModel = new AddRemoveLibrarianViewModel(modelLibrarian);
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
}
