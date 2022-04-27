import client.*;
import javafx.stage.Stage;
import mediator.*;
import view.ViewHandler;
import viewModel.ViewModelFactory;

import java.rmi.registry.Registry;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BookClient bookClient = new BookClientImplementation("localhost", Registry.REGISTRY_PORT);
        MagazineClient magazineClient = new MagazineClientImplementation("localhost", Registry.REGISTRY_PORT);
        LibrarianClient librarianClient = new LibrarianClientImplementation("localhost",Registry.REGISTRY_PORT);


        ModelBook modelBook = new ModelManagerBook(bookClient);
        ModelMagazine modelMagazine = new ModelManagerMagazine(magazineClient);
        ModelLibrarian modelLibrarian = new ModelManagerLibrarian(librarianClient);

        ViewModelFactory viewModelFactory = new ViewModelFactory(modelBook, modelMagazine,modelLibrarian);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(primaryStage);
    }
    
    public static void main(String[] args) {
        Application.launch();
    }
}
