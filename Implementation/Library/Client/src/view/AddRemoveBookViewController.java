package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Book;
import model.Genre;
import model.GenreList;
import viewModel.AddRemoveBookViewModel;
import java.rmi.RemoteException;

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
  @FXML private ListView<Book> bookListView;
  @FXML private ListView<Genre> genreListView;
  @FXML private ListView<Genre> selectedGenreListView;
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
  public void init(ViewHandler viewHandler, AddRemoveBookViewModel viewModel,
      Region root) throws RemoteException
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
    viewModel.bindYearTextField(yearTextField.textProperty());
    viewModel.bindBookListView(bookListView.itemsProperty());
    viewModel.bindGenreList(genreListView.itemsProperty());
    viewModel.bindSelectedGenreList(selectedGenreListView.itemsProperty());

    viewModel.setBookList();
    viewModel.setGenreList();
  }

  /**
   * Closes the actual view when the button is pressed
   */
  @FXML public void logOutButtonPressed() {
    viewHandler.closeView();
  }

  /**
   * Calls the method addBook from the view model when the button is pressed
   */
  @FXML public void addBookButtonPressed() throws RemoteException
  {
    if (!errorsCheck())
    {
      String edition;
      GenreList genres;
      genres = viewModel.getGenreList();
      if (editionTextField.getText().equals(""))
      {
        edition = "1";
      }
      else
      {
        edition = editionTextField.getText();
      }
      if (authorTextField.getText().equals(""))
      {
        Book book = new Book(titleTextField.getText(), publisherTextField.getText(),
            isbnTextField.getText(), Integer.parseInt(edition), Integer.parseInt(yearTextField.getText()), genres);
        viewModel.addBook(book);
      }
      else
      {
        Book book = new Book(authorTextField.getText(), titleTextField.getText(), publisherTextField.getText(),
            isbnTextField.getText(), Integer.parseInt(edition), Integer.parseInt(yearTextField.getText()), genres);
        viewModel.addBook(book);
      }
      reset();
    }
  }

  /**
   * Checks if the input information is correct
   * @return
   * False if it is, true if not
   */
  private boolean errorsCheck()
  {
    if (yearTextField.getText().equals(""))
    {
      errorLabel.setText("Year of publication can't be empty");
      return true;
    }
    else
    {
      try
      {
        Integer.parseInt(yearTextField.getText());
      }
      catch (NumberFormatException e)
      {
        errorLabel.setText("Invalid year: not number");
        return true;
      }
    }
    return false;
  }

  /**
   * Calls the method removeBook from the view model when the button is pressed
   */
  @FXML public void removeBookButtonPressed()
      throws RemoteException
  {
    try
    {
      viewModel.removeBook(bookListView.getSelectionModel().getSelectedItem().getId());
    }
    catch (NullPointerException e)
    {
      errorLabel.setText("No book selected");
    }
  }

  /**
   * Opens the chose item view when the button is pressed
   */
  @FXML public void multimediaItemMenuButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.ITEM);
  }

  /**
   * Opens the home view when the button is pressed
   */
  @FXML public void homeMenuButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.HOME);
  }

  /**
   * Opnes the library user view when the button is pressed
   */
  @FXML public void onAddLibraryUserButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.LIBRARY_USER);
  }

  /**
   * Opens the lent multimedia item view when the button is pressed
   */
  @FXML public void onLoanButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.LENDMULTIMEDIAITEM);
  }

  /**
   * Opens the return multimedia item view when the button is pressed
   */
  @FXML public void onReturnButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.RETURNMULTIMEDIAITEM);
  }

  /**
   * Calls the method addGenreToSelectedGenreList from the view model when the button is pressed
   */
  @FXML public void addGenreButtonPressed()
  {
    //selectedGenreListView.getItems().add(genreListView.getSelectionModel().getSelectedItem());
    viewModel.addGenreToSelectedGenreList(genreListView.getSelectionModel().getSelectedItem());
  }

  /**
   * Calls the method removeFromSelectedGenreList from the view model when the button is pressed
   */
  @FXML
  public void removeGenreButtonPressed() {
    //selectedGenreListView.getItems().remove(selectedGenreListView.getSelectionModel().getSelectedItem());
    viewModel.removeFromSelectedGenreList(selectedGenreListView.getSelectionModel().getSelectedItem());
  }

  /**
   * Calls the reset method from the view model
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
}
