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

    /**
     * A predefine method to initialize an object after its creation
     * @param viewHandler
     * The view handler
     * @param viewModel
     * The view model
     * @param root
     * The root
     */
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

    /**
     * Closes the actual view when the button is pressed
     */
    @FXML public void logOutButtonPressed(){
        viewHandler.closeView();
    }

    /**
     * Calls the method addLibraryUser from the view model when the button is pressed
     */
    @FXML public void addUserButtonPressed() throws RemoteException{
        LibraryUser libraryUser = new LibraryUser(ssnTextField.getText(),firstNameTextField.getText(),
                lastNameTextField.getText(), passwordTextField.getText());
        viewModel.addLibraryUser(libraryUser);
        reset();
    }

    /**
     * Calls the method removeLibraryUser from the view model when the button is pressed
     */
    @FXML public void removeUserButtonPressed() throws RemoteException {
        try
        {
            viewModel.removeLibraryUser(userTextListView.getSelectionModel().getSelectedItem().getSSN());
        }catch (NullPointerException e){
            errorLabel.setText("no library user to be removed selected ");
        }

    }

    /**
     * Opens the chose item view when the button is pressed
     */
    @FXML public void multimediaItemMenuButtonPressed()
        throws  RemoteException
    {
        viewHandler.openView(viewHandler.ITEM);
    }

    /**
     * Opens the home view when the button is pressed
     */
    @FXML public void homeMenuButtonPressed()
        throws  RemoteException
    {
        viewHandler.openView(viewHandler.HOME);
    }

    /**
     * Opens the library user view when the button is pressed
     */
    @FXML public void onAddLibraryUserButtonPressed()
        throws  RemoteException
    {
        viewHandler.openView(viewHandler.LIBRARY_USER);
    }

    /**
     * Opens the lent multimedia item view when the button is pressed
     */
    @FXML public void onLoanButtonPressed()
        throws  RemoteException
    {
        viewHandler.openView(viewHandler.LENDMULTIMEDIAITEM);
    }

    /**
     * Opens the return multimedia item view when the button is pressed
     */
    @FXML public void onReturnButtonPressed()
        throws  RemoteException
    {
        viewHandler.openView(viewHandler.RETURNMULTIMEDIAITEM);
    }

    /**
     * Calls the reset method from the view
     */
    public void reset() throws  RemoteException{
        viewModel.reset();
    }

    /**
     * Get root method
     * @return
     * The root
     */
    public Region getRoot(){
        return root;
    }

}
