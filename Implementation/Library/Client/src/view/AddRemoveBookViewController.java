package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Book;
import model.Genre;
import model.GenreList;
import viewModel.AddRemoveBookViewModel;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddRemoveBookViewController
{

  private ViewHandler viewHandler;
  private Region root;
  private AddRemoveBookViewModel viewModel;
  @FXML private TextField titleTextField;
  @FXML private TextField publisherTextField;
  @FXML private TextField authorTextField;
  @FXML private TextField isbnTextField;
  @FXML private TextField yearTextField;
  @FXML private TextField genreTextField;
  @FXML private TextField editionTextField;
  @FXML private TextField searchTextField;
  @FXML private ListView<Book> bookListView;
  @FXML private ListView<String> genreListView;
  @FXML private Label errorLabel;

  public void init(ViewHandler viewHandler, AddRemoveBookViewModel viewModel,
      Region root) throws SQLException, RemoteException
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    viewModel.bindTitleTextField(titleTextField.textProperty());
    viewModel.bindAuthorTextField(authorTextField.textProperty());
    viewModel.bindEditionTextField(editionTextField.textProperty());
    viewModel.bindErrorLabel(errorLabel.textProperty());
    viewModel.bindGenreTextField(genreTextField.textProperty());
    viewModel.bindISBNTextField(isbnTextField.textProperty());
    viewModel.bindPublisherTextField(publisherTextField.textProperty());
    viewModel.bindSearchTextField(searchTextField.textProperty());
    viewModel.bindYearTextField(yearTextField.textProperty());

    viewModel.setBookList();
  }

  @FXML public void logOutButtonPressed()
  {
    viewHandler.closeView();
  }

  @FXML public void searchButtonPressed()
  {
    viewModel.search();
  }

  @FXML public void addBookButtonPressed() throws SQLException, RemoteException
  {

    Book book = new Book(titleTextField.getText(), publisherTextField.getText(),
        Integer.parseInt(isbnTextField.getText()));
    viewModel.addBook(book);

  }

  @FXML public void removeBookButtonPressed()
      throws RemoteException, SQLException
  {
    viewModel.removeBook(
        bookListView.getSelectionModel().getSelectedItem().getId());
  }

  @FXML public void homeMenuButtonPressed() throws SQLException, RemoteException
  {

  }

  @FXML public void testDataPressed()
  {

  }

  @FXML public void addGenreButtonPressed()
  {

  }

  @FXML public void bookMenuButtonPressed()
  {

  }

  @FXML public void magazinesMenuButtonPressed()
      throws SQLException, RemoteException
  {
    viewHandler.openView(viewHandler.MAGAZINE);
  }

  public void reset() throws SQLException, RemoteException
  {
    viewModel.reset();
  }

  public Region getRoot()
  {
    return root;
  }
}
