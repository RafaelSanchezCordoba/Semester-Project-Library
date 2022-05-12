package view;


import javafx.fxml.FXMLLoader;

import javafx.scene.layout.Region;
import viewModel.ViewModelFactory;

import java.io.IOError;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewFactory
{
  private ViewModelFactory viewModelFactory;
  private ViewHandler viewHandler;
  private AddRemoveBookViewController bookController;
  private AddRemoveMagazineViewController magazineController;



  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler=viewHandler;
    this.viewModelFactory=viewModelFactory;
    bookController=null;
    magazineController=null;

  }

  public Region loadAddRemoveBookView() throws SQLException, RemoteException
  {
    if (bookController == null) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("addRemoveBook.fxml"));
    try {
      Region root = loader.load();
      bookController = loader.getController();
      bookController.init(viewHandler, viewModelFactory.getBookViewModel(), root);
    } catch (IOException e) {
      throw new IOError(e);
    }
  }
    bookController.reset();
    return bookController.getRoot();
  }

  public Region loadAddRemoveMagazineView() throws SQLException, RemoteException
  {if (magazineController == null) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("addRemoveMagazine.fxml"));
    try {
      Region root = loader.load();
      magazineController = loader.getController();
      magazineController.init(viewHandler, viewModelFactory.getMagazineViewModel(), root);
    } catch (IOException | SQLException e) {
      throw new IOError(e);

    }
  }
    magazineController.reset();
    return magazineController.getRoot();
  }


}
