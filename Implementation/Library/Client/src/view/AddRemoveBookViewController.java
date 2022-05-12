package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Book;
import model.Genre;
import model.GenreList;
import viewModel.AddRemoveBookViewModel;

import java.rmi.RemoteException;
import java.sql.SQLException;

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
  @FXML private TextField editionTextField;
  @FXML private TextField searchTextField;
  @FXML private ListView<Book> bookListView;
  @FXML private ListView<Genre> genreListView;
  @FXML private ListView<Genre> selectedGenreListView;
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
    viewModel.bindISBNTextField(isbnTextField.textProperty());
    viewModel.bindPublisherTextField(publisherTextField.textProperty());
    viewModel.bindSearchTextField(searchTextField.textProperty());
    viewModel.bindYearTextField(yearTextField.textProperty());
    viewModel.bindBookListView(bookListView.itemsProperty());
    viewModel.bindGenreList(genreListView.itemsProperty());
    viewModel.bindSelectedGenreList(selectedGenreListView.itemsProperty());

    viewModel.setBookList();
    viewModel.setGenreList();
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
    GenreList genres;
    genres = viewModel.getGenreList();
    if (authorTextField.equals("")) {
      Book book = new Book(titleTextField.getText(), publisherTextField.getText(),
          isbnTextField.getText(), Integer.parseInt(editionTextField.getText()),
          Integer.parseInt(yearTextField.getText()), genres);
      viewModel.addBook(book);

    }
    else{
      Book book = new Book(authorTextField.getText(), titleTextField.getText(), publisherTextField.getText(),
          isbnTextField.getText()
          , Integer.parseInt(editionTextField.getText()),
          Integer.parseInt(yearTextField.getText()), genres);
      viewModel.addBook(book);
    }
    reset();



  }

  @FXML public void removeBookButtonPressed()
      throws RemoteException, SQLException
  {
    viewModel.removeBook(bookListView.getSelectionModel().getSelectedItem().getId());
  }

  @FXML public void homeMenuButtonPressed() throws SQLException, RemoteException
  {

  }

  @FXML public void testDataPressed()
  {

  }

  @FXML public void addGenreButtonPressed()
  {
    //selectedGenreListView.getItems().add(genreListView.getSelectionModel().getSelectedItem());
    viewModel.addGenreToSelectedGenreList(genreListView.getSelectionModel().getSelectedItem());
  }

  @FXML
  public void removeGenreButtonPressed() {
    //selectedGenreListView.getItems().remove(selectedGenreListView.getSelectionModel().getSelectedItem());
    viewModel.removeFromSelectedGenreList(selectedGenreListView.getSelectionModel().getSelectedItem());
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
