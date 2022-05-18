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
  private AddRemoveLibrarianViewController librarianViewController;
  private LendMultimediaItemViewController lendMultimediaItemViewController;
  private HomeViewController homeController;
  private ChoseItemTypeViewController itemViewController;



  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler=viewHandler;
    this.viewModelFactory=viewModelFactory;
    bookController=null;
    magazineController=null;
    librarianViewController = null;
    itemViewController=null;
    homeController=null;
    lendMultimediaItemViewController = null;
  }



  public Region loadAddRemoveLibrarian() throws SQLException, RemoteException {
    if (librarianViewController== null){
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("addRemoveLibrarian.fxml"));
      try
      {
        Region root = loader.load();
        librarianViewController = loader.getController();
        librarianViewController.init(viewHandler,viewModelFactory.getLibrarianViewModel(),root);
      }catch (IOException e){
        throw  new IOError(e);
      }
    }
    librarianViewController.reset();
    return librarianViewController.getRoot();
  }

  public Region loadLendMultimediaItem() throws SQLException, RemoteException {
    if (lendMultimediaItemViewController== null){
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("lendMultimediaItem.fxml"));
      try
      {
        Region root = loader.load();
        lendMultimediaItemViewController = loader.getController();
        lendMultimediaItemViewController.init(viewHandler,viewModelFactory.getLoanMagazineViewModel(),viewModelFactory.getLoanBookViewModel(),root);
      }catch (IOException e){
        throw  new IOError(e);
      }
    }
    lendMultimediaItemViewController.reset();
    return lendMultimediaItemViewController.getRoot();
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



  public Region loadChoseItemTypeView() throws SQLException, RemoteException
  {if (itemViewController == null) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("choseItemType.fxml"));
    try {
      Region root = loader.load();
      itemViewController = loader.getController();
      itemViewController.init(viewHandler, root);
    } catch (IOException | SQLException e) {
      throw new IOError(e);

    }
  }
    return itemViewController.getRoot();
  }

  public Region loadHomeView() throws SQLException, RemoteException {
    if (homeController== null){
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("home.fxml"));
      try
      {
        Region root = loader.load();
        homeController = loader.getController();
        homeController.init(viewHandler,root);
      }catch (IOException e){
        throw  new IOError(e);
      }
    }
    return homeController.getRoot();
  }
}
