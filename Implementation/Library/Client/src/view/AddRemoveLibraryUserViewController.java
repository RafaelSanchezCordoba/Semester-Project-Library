package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.LibraryUser;
import viewModel.AddRemoveLibraryUserViewModel;

import javafx.scene.layout.Region;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class AddRemoveLibraryUserViewController
{
    private ViewHandler viewHandler;
    private Region root;
    private AddRemoveLibraryUserViewModel viewModel;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField ssnTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField searchTextField;
    @FXML private ListView<LibraryUser> userTextListView;
    @FXML private Label errorLabel;


    public void init(ViewHandler viewHandler, AddRemoveLibraryUserViewModel viewModel, Region root) throws SQLException, RemoteException{
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        viewModel.bindFirstNameTextField(firstNameTextField.textProperty());
        viewModel.bindLastNameTextField(lastNameTextField.textProperty());
        viewModel.bindSSNTextField(ssnTextField.textProperty());
        viewModel.bindPasswordTextField(passwordTextField.textProperty());
        viewModel.bindSearchTextField(searchTextField.textProperty());
        viewModel.bindUserListView(userTextListView.itemsProperty());
        viewModel.bindErrorLabel(errorLabel.textProperty());

        viewModel.setUserList();
    }

    @FXML public void logOutButtonPressed(){
        viewHandler.closeView();
    }
    @FXML public void searchButtonPressed(){
        viewModel.search();
    }
    @FXML public void addUserButtonPressed() throws RemoteException, SQLException{
        LibraryUser libraryUser = new LibraryUser(firstNameTextField.getText(), lastNameTextField.getText(),
                ssnTextField.getText(), passwordTextField.getText());
        viewModel.addLibraryUser(libraryUser);
    }

    @FXML public void removeUserButtonPressed() throws RemoteException, SQLException{
        viewModel.removeLibraryUser(userTextListView.getSelectionModel().getSelectedItem().getSsn());
    }

    @FXML public void homeMenuButtonPressed() throws SQLException, RemoteException{}

    @FXML public void bookMenuButtonPressed() throws SQLException, RemoteException{
        viewHandler.openView(viewHandler.BOOK);
    }

    public void reset() throws SQLException, RemoteException{
        viewModel.reset();
    }

    public Region getRoot(){
        return root;
    }

}
