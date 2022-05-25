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

  @FXML
  public void logOutButtonPressed()
  {
    viewHandler.closeView();
  }


  @FXML
  public void addMagazineButtonPressed() throws RemoteException
  {
    int volume;
    if (!errorsCheck())
      {
        if (volumeTextField.getText().equals(""))
        {
        volume= 1;
        }
        else
        {
          volume=Integer.parseInt(volumeTextField.getText());
        }
        Date date = new Date(Integer.parseInt(yearTextField.getText()) - 1900,
            Integer.parseInt(monthTextField.getText()) - 1, Integer.parseInt(dayTextField.getText()));
        Magazine magazine = new Magazine(titleTextField.getText(),
            publisherTextField.getText(), volume,
            genreTextField.getText(), date);
        viewModel.addMagazine(magazine);
      }
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
  else if(!volumeTextField.getText().equals(""))
    {
      if (volumeFormatCheck())
      {
        errorLabel.setText("The volume must be a number");
        return true;
      }
    }
  return  false;
  }

  private boolean volumeFormatCheck()
  {
    try
    {
      Integer.parseInt(volumeTextField.getText());
    }
    catch (NumberFormatException e)
    {
      return true;
    }
    return false;
  }

  @FXML
  public void removeMagazineButtonPressed() throws RemoteException
  {
    viewModel.removeMagazine(magazineListView.getSelectionModel().getSelectedItem().getId());
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

  public void reset() throws  RemoteException{
    viewModel.reset();
  }

  public Region getRoot() {
    return root;
  }

}
