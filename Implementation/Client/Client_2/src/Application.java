import client.BookClient;
import client.BookClientImplementation;
import client.MagazineClient;
import client.MagazineClientImplementation;
import javafx.stage.Stage;
import mediator.ModelBook;
import mediator.ModelMagazine;
import mediator.ModelManagerBook;
import mediator.ModelManagerMagazine;
import view.ViewHandler;
import viewModel.ViewModelFactory;

import java.rmi.registry.Registry;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BookClient bookClient = new BookClientImplementation("localhost", Registry.REGISTRY_PORT);
        MagazineClient magazineClient = new MagazineClientImplementation("localhost", Registry.REGISTRY_PORT);
        ModelBook modelBook = new ModelManagerBook(bookClient);
        ModelMagazine modelMagazine = new ModelManagerMagazine(magazineClient);
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelBook, modelMagazine);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(primaryStage);
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
