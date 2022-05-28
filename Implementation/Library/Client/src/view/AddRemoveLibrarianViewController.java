package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Librarian;
import viewModel.AddRemoveLibrarianViewModel;

import java.rmi.RemoteException;


public class AddRemoveLibrarianViewController
{
  private ViewHandler viewHandler;
  private Region root;
  private AddRemoveLibrarianViewModel viewModel;

  @FXML private TextField firstNameTextField;
  @FXML private TextField lastNameTextField;
  @FXML private TextField ssnTextField;
  @FXML private TextField passwordTextField;
  @FXML private Label errorLabel;
  @FXML private ListView<Librarian> librarianTextListView;

  /**
   * A predefine method to initialize an object after its creation
   * @param viewHandler
   * The view handler
   * @param viewModel
   * The view model
   * @param root
   * The root
   */
  public void init(ViewHandler viewHandler, AddRemoveLibrarianViewModel viewModel, Region root)
      throws  RemoteException
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    viewModel.bindFirstNameTextField(firstNameTextField.textProperty());
    viewModel.bindLastNameTextField(lastNameTextField.textProperty());
    viewModel.bindSsnTextField(ssnTextField.textProperty());
    viewModel.bindPasswordTextField(passwordTextField.textProperty());
    viewModel.bindErrorLabelTextField(errorLabel.textProperty());
    viewModel.bindLibrarianListView(librarianTextListView.itemsProperty());

    viewModel.setLibrarianList();
  }

  /**
   * Calls the method addLibrarian from the view model when the button is pressed
   */
  @FXML public void addLibrarianButtonPressed()
      throws  RemoteException
  {
    Librarian librarian = new Librarian(ssnTextField.getText(),
        passwordTextField.getText(), firstNameTextField.getText(),
        lastNameTextField.getText());
    viewModel.addLibrarian(librarian);
  }

  /**
   * Calls the method removeLibrarian from the view model when the button is pressed
   */
  @FXML public void removeLibrarianButtonPressed()
      throws  RemoteException
  {
    viewModel.removeLibrarian(
        librarianTextListView.getSelectionModel().getSelectedItem().getSsn());
  }

  /**
   * Calls the method reset from the view model when the button is pressed
   */
  public void reset() throws  RemoteException
  {
    viewModel.reset();
  }

  /**
   * Get root method
   * @return
   * The root
   */
  public Region getRoot()
  {
    return root;
  }

  /**
   * Closes the actual view when the button is pressed
   */
  @FXML public void logOutButtonPressed()
  {
    viewHandler.closeView();
  }

  /**
   * Opens the home view when the button is pressed
   */
  @FXML public void homeMenuButtonPressed() throws  RemoteException
  {
    viewHandler.openView(viewHandler.HOME);
  }

}
