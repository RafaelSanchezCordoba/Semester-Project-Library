package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.AddRemoveMagazineViewModel;

public class AddRemoveMagazineViewController
{
  private ViewHandler viewHandler;
  private Region root;
  private AddRemoveMagazineViewModel viewModel;
  @FXML private TextField titleTextField;
  @FXML private TextField publisherTextField;
  @FXML private TextField volumeTextField;
  @FXML private TextField dayTextField;
  @FXML private TextField yearTextField;
  @FXML private TextField monthTextField;
  @FXML private TextField genreTextField;
  @FXML private TextField searchTextField;
  @FXML private ListView<String> magazineListView;
  @FXML private ListView<String> genreListView;
  @FXML private Label errorLabel;

  public void init(ViewHandler viewHandler, AddRemoveMagazineViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel=viewModel;
    this.root = root;

    //include all the bind from viewModel
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
  public void addMagazineButtonPressed()
  {
    viewModel.addMagazine();
  }

  @FXML
  public void removeBookButtonPressed()
  {
    viewModel.removeMagazine();
  }

  @FXML
  public void homeMenuButtonPressed()
  {
    viewHandler.openView(viewHandler.HOME);
  }

  @FXML
  public void bookMenuButtonPressed() {
    viewHandler.openView(viewHandler.BOOK);
  }

  @FXML
  public void magazinesMenuButtonPressed() {

  }

  @FXML
  public void addGenreButtonPressed() {

  }

  public void reset() {

  }

  public Region getRoot() {
    return root;
  }




}
