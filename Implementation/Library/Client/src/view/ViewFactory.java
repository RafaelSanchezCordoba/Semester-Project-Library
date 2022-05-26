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
  private HomeViewController homeController;
  private ChoseItemTypeViewController itemViewController;
  private AddRemoveLibraryUserViewController libraryUserController;
  private ReturnMultimediaItemViewController returnMultimediaItemViewController;
  private LendMultimediaItemViewController lendMultimediaItemViewController;
  private LibrarianHomeViewController librarianHomeViewController;

  /**
   * View factory constructor that set the viewHandler and the viewModelFactory
   * @param viewHandler
   * The view handler
   * @param viewModelFactory
   * The view model factory
   */
  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler=viewHandler;
    this.viewModelFactory=viewModelFactory;
    bookController=null;
    magazineController=null;
    librarianViewController = null;
    itemViewController=null;
    homeController=null;
    libraryUserController = null;
    lendMultimediaItemViewController = null;
    returnMultimediaItemViewController=null;
    librarianHomeViewController=null;
  }

  /**
   * Load and return the AddRemoveLibrarian root
   * @return
   * The AddRemoveLibrarian root
   * @throws RemoteException
   */
  public Region loadAddRemoveLibrarian() throws  RemoteException {
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

  /**
   * Load and return the LibrarianHome root
   * @return
   * The LibrarianHome root
   * @throws RemoteException
   */
  public Region loadLibrarianHome() throws  RemoteException {
    if (librarianHomeViewController== null){
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("librarianHome.fxml"));
      try
      {
        Region root = loader.load();
        librarianHomeViewController = loader.getController();
        librarianHomeViewController.init(viewHandler,root);
      }catch (IOException e){
        throw  new IOError(e);
      }
    }
    return librarianHomeViewController.getRoot();
  }

  /**
   * Load and return the AddRemoveBook root
   * @return
   * The AddRemoveBook root
   * @throws RemoteException
   */
  public Region loadAddRemoveBookView() throws RemoteException
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

  /**
   * Load and return the AddRemoveLibraryUser root
   * @return
   * The AddRemoveLibraryUser root
   * @throws RemoteException
   */
  public Region loadAddRemoveLibraryUserView() throws  RemoteException{
    if(libraryUserController == null){
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("addRemoveUser.fxml"));
      try{
        Region root = loader.load();
        libraryUserController = loader.getController();
        libraryUserController.init(viewHandler, viewModelFactory.getUserViewModel(), root);
      }catch (IOException e){
        throw new IOError(e);
      }
    }
    libraryUserController.reset();
    return libraryUserController.getRoot();
  }

  /**
   * Load and return the AddRemoveMagazine root
   * @return
   * The AddRemoveMagazine root
   * @throws RemoteException
   */
  public Region loadAddRemoveMagazineView() throws RemoteException
  {if (magazineController == null) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("addRemoveMagazine.fxml"));
    try {
      Region root = loader.load();
      magazineController = loader.getController();
      magazineController.init(viewHandler, viewModelFactory.getMagazineViewModel(), root);
    } catch (IOException  e) {
      throw new IOError(e);

    }
  }
    magazineController.reset();
    return magazineController.getRoot();
  }

  /**
   * Load and return the ChoseItemType root
   * @return
   * The ChoseItemType root
   * @throws RemoteException
   */
  public Region loadChoseItemTypeView() throws  RemoteException
  {if (itemViewController == null) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("choseItemType.fxml"));
    try {
      Region root = loader.load();
      itemViewController = loader.getController();
      itemViewController.init(viewHandler, root);
    } catch (IOException  e) {
      throw new IOError(e);

    }
  }
    return itemViewController.getRoot();
  }

  /**
   * Load and return the Home rot
   * @return
   * The home root
   * @throws RemoteException
   */
  public Region loadHomeView() throws  RemoteException {
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

  /**
   * Load and return the LendMultimediaItem root
   * @return
   * The LendMultimediaItem root
   * @throws RemoteException
   */
  public Region loadLendMultimediaItem() throws  RemoteException {
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

  /**
   * Load and return the ReturnMultimediaItem root
   * @return
   * The ReturnMultimediaItem root
   * @throws RemoteException
   */
  public Region loadReturnMultimediaView() throws RemoteException {
    if (returnMultimediaItemViewController== null){
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("returnMultimediaItem.fxml"));
      try
      {
        Region root = loader.load();
        returnMultimediaItemViewController = loader.getController();
        returnMultimediaItemViewController.init(viewHandler,viewModelFactory.getReturnMagazineViewModel(),viewModelFactory.getReturnBookViewModel(),root);
      }catch (IOException e){
        throw  new IOError(e);
      }
    }
    returnMultimediaItemViewController.reset();
    return returnMultimediaItemViewController.getRoot();
  }

}
