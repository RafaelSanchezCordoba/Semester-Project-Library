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
import java.sql.SQLException;

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

  public  void  init(ViewHandler viewHandler, AddRemoveLibrarianViewModel viewModel, Region root) throws SQLException, RemoteException {
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

  @FXML
  public void addLibrarianButtonPressed() throws SQLException, RemoteException {
    Librarian librarian;
    if (ssnTextField.getText().equals(""))
    {
     librarian = new Librarian(1, passwordTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText());
    }
    else
    {
      librarian = new Librarian(Integer.parseInt(ssnTextField.getText()), passwordTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText());
    }
    viewModel.addLibrarian(librarian);
  }
  @FXML
  public void removeLibrarianButtonPressed() throws SQLException, RemoteException {
    viewModel.removeLibrarian(librarianTextListView.getSelectionModel().getSelectedItem().getSsn());
  }
  public void reset() throws SQLException, RemoteException {
    viewModel.reset();
  }
  public Region getRoot(){
    return root;
  }

  @FXML
  public void logOutButtonPressed()
  {
    viewHandler.closeView();
  }

  @FXML
  public void homeMenuButtonPressed() throws SQLException, RemoteException {
    viewHandler.openView(viewHandler.HOME);
  }

  @FXML
  public void magazineMenuButtonPressed() throws SQLException, RemoteException {
    viewHandler.openView(viewHandler.MAGAZINE);
  }

  @FXML
  public void bookMenuButtonPressed() throws SQLException, RemoteException {
    viewHandler.openView(viewHandler.BOOK);
  }

  @FXML
  public void librarianMenuButtonPressed() throws SQLException, RemoteException {
    viewHandler.openView(viewHandler.LIBRARIAN);
  }

  public void searchButtonPressed() {
    viewModel.search();
  }
}
