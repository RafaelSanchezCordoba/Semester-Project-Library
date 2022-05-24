package view;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
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
        viewModel.bindUserListView(userTextListView.itemsProperty());
        viewModel.bindErrorLabel(errorLabel.textProperty());

        viewModel.setUserList();
    }

    @FXML public void logOutButtonPressed(){
        viewHandler.closeView();
    }

    @FXML public void addUserButtonPressed() throws RemoteException, SQLException{
        LibraryUser libraryUser = new LibraryUser(ssnTextField.getText(),firstNameTextField.getText(),
                lastNameTextField.getText(), passwordTextField.getText());
        viewModel.addLibraryUser(libraryUser);
        reset();
        System.out.println("Button pressed");
        
    }

    @FXML public void removeUserButtonPressed() throws RemoteException, SQLException{
        viewModel.removeLibraryUser(userTextListView.getSelectionModel().getSelectedItem().getSSN());
    }

    @FXML public void multimediaItemMenuButtonPressed()
        throws SQLException, RemoteException
    {
        viewHandler.openView(viewHandler.ITEM);
    }

    @FXML public void homeMenuButtonPressed()
        throws SQLException, RemoteException
    {
        viewHandler.openView(viewHandler.HOME);
    }



    @FXML public void onAddLibraryUserButtonPressed()
        throws SQLException, RemoteException
    {
        viewHandler.openView(viewHandler.LIBRARY_USER);
    }

    @FXML public void onLoanButtonPressed()
        throws SQLException, RemoteException
    {
        viewHandler.openView(viewHandler.LENDMULTIMEDIAITEM);
    }

    @FXML public void onReturnButtonPressed()
        throws SQLException, RemoteException
    {
        viewHandler.openView(viewHandler.RETURNMULTIMEDIAITEM);
    }

    public void reset() throws SQLException, RemoteException{
        viewModel.reset();
    }

    public Region getRoot(){
        return root;
    }

}
