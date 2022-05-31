import client.*;
import javafx.stage.Stage;
import mediator.*;
import view.ViewHandler;
import viewModel.ViewModelFactory;

import java.rmi.registry.Registry;

public class Application extends javafx.application.Application {
    /**
     * Start method that set the clients, models and the views
     * @param primaryStage
     * The primary stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        BookClient bookClient = new BookClientImplementation("localhost", Registry.REGISTRY_PORT);
        MagazineClient magazineClient = new MagazineClientImplementation("localhost", Registry.REGISTRY_PORT);
        LibrarianClient librarianClient = new LibrarianClientImplementation("localhost",Registry.REGISTRY_PORT);
        LibraryUserClient libraryUserClient = new LibraryUserClientImplementation("localhost", Registry.REGISTRY_PORT);
        LoanMagazineClient loanMagazineClient = new LoanMagazineClientImplementation("localhost", Registry.REGISTRY_PORT);
        LoanBookClient loanBookClient=new LoanBookClientImplementation("localhost",Registry.REGISTRY_PORT);

        ModelBook modelBook = new ModelManagerBook(bookClient);
        ModelMagazine modelMagazine = new ModelManagerMagazine(magazineClient);
        ModelLibrarian modelLibrarian = new ModelManagerLibrarian(librarianClient);
        ModelLibraryUser modelLibraryUser = new ModelManagerLibraryUser(libraryUserClient);
        ModelLoanMagazine modelLoanMagazine = new ModelManagerLoanMagazine(loanMagazineClient);
        ModelLoanBook modelLoanBook=new ModelManagerLoanBook(loanBookClient);

        ViewModelFactory viewModelFactory = new ViewModelFactory(modelBook, modelMagazine,modelLibrarian,modelLibraryUser,modelLoanMagazine, modelLoanBook);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(primaryStage);
    }

    /**
     * Main method that lunch the application
     */
    public static void main(String[] args) {
        Application.launch();
    }
}
