package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Book;
import model.Genre;
import model.GenreList;
import viewModel.AddRemoveBookViewModel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

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
  @FXML private TextField editionTextField;
  @FXML private ListView<Book> bookListView;
  @FXML private ListView<Genre> genreListView;
  @FXML private ListView<Genre> selectedGenreListView;
  @FXML private Label errorLabel;

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

  @FXML public void logOutButtonPressed()
  {
//    JFrame frame = new JFrame("close");
//    frame.setSize(300,300);
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    frame.setVisible(true);
//    frame.pack();
    viewHandler.closeView();
  }

//  public void checkIfKeyIsPressed(KeyEvent e){
//    switch (e.getKeyCode()){
//      case KeyEvent.KEY_PRESSED:
//        if(e.getKeyCode() == Key.)
//    }
//  }

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

  @FXML public void multimediaItemMenuButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.ITEM);
  }

  @FXML public void homeMenuButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.HOME);
  }

  @FXML public void onAddLibraryUserButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.LIBRARY_USER);
  }
  @FXML public void onLoanButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.LENDMULTIMEDIAITEM);
  }

  @FXML public void onReturnButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.RETURNMULTIMEDIAITEM);
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


  public void reset() throws  RemoteException
  {
    viewModel.reset();
  }

  public Region getRoot()
  {
    return root;
  }
}
