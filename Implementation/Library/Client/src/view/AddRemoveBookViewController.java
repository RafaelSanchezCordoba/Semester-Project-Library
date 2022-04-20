package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
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
  @FXML private TextField genreTextField;
  @FXML private TextField editionTextField;
  @FXML private TextField searchTextField;
  @FXML private ListView<String> bookListView;
  @FXML private Label errorLabel;



  public void init(ViewHandler viewHandler, AddRemoveBookViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel=viewModel;
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


        for (int i = 0;i<viewModel.getList().size();i++){
      bookListView.getItems().add(viewModel.getList().get(i).toString());
    }

  }

  @FXML
  public void logOutButtonPressed()
  {
    viewHandler.closeView();
  }

  @FXML
  public void searchButtonPressed()
  {
    viewModel.search();
  }

  @FXML
  public void addBookButtonPressed()
  {
    viewModel.addBook();
    bookListView.getItems().add(viewModel.getLastAddedBook());

   //bookListView.getItems().add(viewModel.getList().get(0).toString());

  }

  //there is probably a better way
  @FXML
  public void removeBookButtonPressed() throws RemoteException
  {

    //function to get the book id ?????????
    String index = bookListView.getSelectionModel().getSelectedItem();
    int trueIndex = Integer.parseInt(index.substring(0,4).trim());
    System.out.println(index.substring(0,4)+"  "+trueIndex);


    viewModel.removeBook(trueIndex);
    bookListView.getItems().remove(bookListView.getSelectionModel().getSelectedIndex());
  }

  @FXML
  public void homeMenuButtonPressed()
  {
    viewHandler.openView("home");
  }

  @FXML
  public void testDataPressed(){viewModel.dummy();}

  @FXML
  public void bookMenuButtonPressed() {

  }

  @FXML
  public void magazinesMenuButtonPressed() {
      viewHandler.openView(viewHandler.MAGAZINE);
  }

  public void reset() {

  }

  public Region getRoot() {
    return root;
  }
}
