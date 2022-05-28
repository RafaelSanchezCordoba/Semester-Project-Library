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
  @FXML private ListView<Magazine> magazineListView;
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
  public void init(ViewHandler viewHandler, AddRemoveMagazineViewModel viewModel, Region root) throws  RemoteException
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
    viewModel.bindErrorLabel(errorLabel.textProperty());
    viewModel.bindMagazineListView(magazineListView.itemsProperty());

    viewModel.setMagazineList();
  }

  /**
   * Closes the actual view when the button is pressed
   */
  @FXML
  public void logOutButtonPressed()
  {
    viewHandler.closeView();
  }

  /**
   * Calls the method add magazine from the view when the button is pressed
   */
  @FXML
  public void addMagazineButtonPressed() throws RemoteException
  {
    viewModel.addMagazine();
  }





  /**
   * Calls the method remove magazine from the view when the button is pressed
   */
  @FXML
  public void removeMagazineButtonPressed() throws RemoteException
  {
    viewModel.removeMagazine(magazineListView.getSelectionModel().getSelectedItem().getId());
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
   * Opens the library user view when the button is pressed
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
   * Calls the reset method in the viewModel
   * @throws RemoteException
   */
  public void reset() throws  RemoteException{
    viewModel.reset();
  }

  /**
   * Get root method
   * @return
   * The root
   */
  public Region getRoot() {
    return root;
  }

}
