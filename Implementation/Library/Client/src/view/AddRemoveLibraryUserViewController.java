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


    public void init(ViewHandler viewHandler, AddRemoveLibraryUserViewModel viewModel, Region root) throws  RemoteException{
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

    @FXML public void addUserButtonPressed() throws RemoteException{
        LibraryUser libraryUser = new LibraryUser(ssnTextField.getText(),firstNameTextField.getText(),
                lastNameTextField.getText(), passwordTextField.getText());
        viewModel.addLibraryUser(libraryUser);
        reset();
    }

    @FXML public void removeUserButtonPressed() throws RemoteException {
        try
        {
            viewModel.removeLibraryUser(userTextListView.getSelectionModel().getSelectedItem().getSSN());
        }catch (NullPointerException e){
            errorLabel.setText("no library user to be removed selected ");
        }

    }

    @FXML public void multimediaItemMenuButtonPressed()
        throws  RemoteException
    {
        viewHandler.openView(viewHandler.ITEM);
    }

    @FXML public void homeMenuButtonPressed()
        throws  RemoteException
    {
        viewHandler.openView(viewHandler.HOME);
    }



    @FXML public void onAddLibraryUserButtonPressed()
        throws  RemoteException
    {
        viewHandler.openView(viewHandler.LIBRARY_USER);
    }

    @FXML public void onLoanButtonPressed()
        throws  RemoteException
    {
        viewHandler.openView(viewHandler.LENDMULTIMEDIAITEM);
    }

    @FXML public void onReturnButtonPressed()
        throws  RemoteException
    {
        viewHandler.openView(viewHandler.RETURNMULTIMEDIAITEM);
    }

    public void reset() throws  RemoteException{
        viewModel.reset();
    }

    public Region getRoot(){
        return root;
    }

}
