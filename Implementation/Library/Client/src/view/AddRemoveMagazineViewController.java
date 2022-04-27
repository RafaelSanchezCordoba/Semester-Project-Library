package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Magazine;
import viewModel.AddRemoveMagazineViewModel;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;

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
  @FXML private ListView<Magazine> magazineListView;
  @FXML private Label errorLabel;

  public void init(ViewHandler viewHandler, AddRemoveMagazineViewModel viewModel, Region root) throws
      RemoteException, SQLException
  {
    this.viewHandler = viewHandler;
    this.viewModel=viewModel;
    this.root = root;

    viewModel.setMagazineList();
    //include all the bind from viewModel
    viewModel.bindErrorLabel(errorLabel.textProperty());
    viewModel.bindGenreTextField(genreTextField.textProperty());
    viewModel.bindPublisherTextField(publisherTextField.textProperty());
    viewModel.bindSearchTextField(searchTextField.textProperty());
    viewModel.bindTitleTextField(titleTextField.textProperty());
    viewModel.bindYearTextField(yearTextField.textProperty());
    viewModel.bindMontTextField(monthTextField.textProperty());
    viewModel.bindDayTextField(dayTextField.textProperty());
    viewModel.bindVolumeTextField(volumeTextField.textProperty());
    viewModel.bindMagazineListView(magazineListView.itemsProperty());
    viewModel.reset();
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
  public void addMagazineButtonPressed() throws RemoteException, SQLException {
    Date date= new Date(Integer.parseInt(yearTextField.getText()),Integer.parseInt(monthTextField.getText()),Integer.parseInt(dayTextField.getText()));
    Magazine magazine = new Magazine(titleTextField.getText(),publisherTextField.getText(),Integer.parseInt(volumeTextField.getText()),genreTextField.getText(),date);

    viewModel.addMagazine(magazine);
  }

  @FXML
  public void removeMagazineButtonPressed() throws RemoteException, SQLException {
    Magazine selectedMagazine = magazineListView.getSelectionModel().getSelectedItem();
    viewModel.removeMagazine(selectedMagazine.getId());
  }

  @FXML
  public void homeMenuButtonPressed() throws SQLException, RemoteException
  {
    viewHandler.openView(viewHandler.HOME);
  }

  @FXML
  public void bookMenuButtonPressed() throws SQLException, RemoteException
  {
    viewHandler.openView(viewHandler.BOOK);
  }

  @FXML
  public void magazinesMenuButtonPressed() throws SQLException, RemoteException
  {
    viewHandler.openView(viewHandler.MAGAZINE);
  }


  void reset() throws SQLException, RemoteException
  {
    viewModel.reset();
  }

  public Region getRoot() {
    return root;
  }




}
