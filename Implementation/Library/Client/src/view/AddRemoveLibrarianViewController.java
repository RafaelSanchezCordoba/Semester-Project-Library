package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Librarian;
import viewModel.AddRemoveBookViewModel;
import viewModel.AddRemoveLibrarianViewModel;

import java.util.Arrays;

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
  @FXML private ListView<String> librarianTextListView;
  private ObservableSet<String> observableSet;

  public  void  init(ViewHandler viewHandler, AddRemoveLibrarianViewModel viewModel, Region root){
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    librarianTextListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    this.observableSet = FXCollections.observableSet();

    viewModel.bindFirstNameTextField(firstNameTextField.textProperty());
    viewModel.bindLastNameTextField(lastNameTextField.textProperty());
    viewModel.bindSsnTextField(ssnTextField.textProperty());
    viewModel.bindPasswordTextField(passwordTextField.textProperty());
    viewModel.bindErrorLabelTextField(errorLabel.textProperty());


   /* observableSet.addListener((SetChangeListener.Change<?extends String> c)->{
      if(c.wasAdded()){
        librarianTextListView.getItems().add(c.getElementAdded());
      }
      if (c.wasRemoved()){
        librarianTextListView.getItems().remove(c.getElementRemoved());

      }
    });
    librarianTextListView.getItems().setAll(viewModel.getOptions());

    observableSet.addAll(viewModel.getOptions());*/
  }

  @FXML public void addLibrarianButtonPressed(ActionEvent event){

    observableSet.add(viewModel.getInstance().toString());
    viewModel.addLibrarian();


  }
  @FXML public void removeLibrarianButtonPressed(){
    observableSet.removeAll(librarianTextListView.getSelectionModel().getSelectedItems());
  }
  public void reset(){

  }
  public Region getRoot(){
    return root;
  }

  @FXML
  public void homeMenuButtonPressed()
  {
  }

  @FXML
  public void bookMenuButtonPressed()
  {
  }

  @FXML
  public void logOutButtonPressed()
  {
  }

  public void searchButtonPressed(ActionEvent actionEvent)
  {
  }
}
