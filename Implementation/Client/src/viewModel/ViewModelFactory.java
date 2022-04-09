package viewModel;

import mediator.Model;

public class ViewModelFactory {
    private final AddRemoveBookViewModel bookViewModel;
    private final AddRemoveMagazineViewModel magazineViewModel;

    public ViewModelFactory(Model model) {
        this.bookViewModel = new AddRemoveBookViewModel(model);
        this.magazineViewModel = new AddRemoveMagazineViewModel(model);
    }

    public AddRemoveBookViewModel getBookViewModel() {
        return bookViewModel;
    }

    public AddRemoveMagazineViewModel getMagazineViewModel() {
        return magazineViewModel;
    }
}
