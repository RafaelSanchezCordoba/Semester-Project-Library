package view;

import javafx.event.ActionEvent;
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
  @FXML private ListView<String> genreListView;
  @FXML private Label errorLabel;

  public void init(ViewHandler viewHandler, AddRemoveMagazineViewModel viewModel, Region root) throws SQLException, RemoteException
  {
    this.viewHandler = viewHandler;
    this.viewModel=viewModel;
    this.root = root;

    viewModel.bindTitleTextField(titleTextField.textProperty());
    viewModel.bindPublisherTextField(publisherTextField.textProperty());
    viewModel.bindVolumeTextField(volumeTextField.textProperty());
    viewModel.bindDayTextField(dayTextField.textProperty());
    viewModel.bindYearTextField(yearTextField.textProperty());
    viewModel.bindMontTextField(monthTextField.textProperty());
    viewModel.bindGenreTextField(genreTextField.textProperty());
    viewModel.bindSearchTextField(searchTextField.textProperty());
    viewModel.bindErrorLabel(errorLabel.textProperty());
    viewModel.bindMagazineListView(magazineListView.itemsProperty());

    viewModel.setMagazineList();
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
  public void addMagazineButtonPressed() throws RemoteException, SQLException
  {
    if (!errorsCheck()){
    Date date = new Date(Integer.parseInt(yearTextField.getText())-1900, Integer.parseInt(monthTextField.getText())-1, Integer.parseInt(dayTextField.getText()));
    Magazine magazine = new Magazine(titleTextField.getText(), publisherTextField.getText(), Integer.parseInt(volumeTextField.getText()), genreTextField.getText(),date);
    viewModel.addMagazine(magazine);}
  }

  public boolean errorsCheck()
  {
    if (dayTextField.getText().equals(""))
  {
    errorLabel.setText("Day can't be null");
    return true;
  }
  else if (monthTextField.getText().equals(""))
  {
    errorLabel.setText("Month can't be null");
    return true;
  }
  else if(yearTextField.getText().equals(""))
  {
    errorLabel.setText("Year can't be null");
    return true;
  }
  return  false;
  }

  @FXML
  public void removeMagazineButtonPressed() throws SQLException, RemoteException
  {
    viewModel.removeMagazine(magazineListView.getSelectionModel().getSelectedItem().getId());
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
  public void magazinesMenuButtonPressed() throws SQLException, RemoteException {
    viewHandler.openView(viewHandler.MAGAZINE);
  }

  @FXML
  public void addGenreButtonPressed() {

  }

  public void reset() throws SQLException, RemoteException{
    viewModel.reset();
  }

  public Region getRoot() {
    return root;
  }

}
