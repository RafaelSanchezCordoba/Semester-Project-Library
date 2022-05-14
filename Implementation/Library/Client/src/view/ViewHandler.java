package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewHandler
{
  public static final String HOME = "home";
  public static final String BOOK = "addRemoveBook";
  public static final String MAGAZINE = "addRemoveMagazine";
  public static final String LIBRARY_USER = "addRemoveLibraryUser";


  private Stage primaryStage;
  private final Scene currentScene;
  private final ViewFactory viewFactory;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewFactory=new ViewFactory(this,viewModelFactory);
    this.currentScene = new Scene(new Region());
  }



  public void start(Stage primaryStage) throws SQLException, RemoteException
  {
    this.primaryStage = primaryStage;
    openView(MAGAZINE);
//    openView(LIBRARY_USER);
  }


  public void openView(String id) throws SQLException, RemoteException
  {
    Region root;
    switch (id)
    {
      case BOOK:
        root = viewFactory.loadAddRemoveBookView();
        break;
      case MAGAZINE:
        root = viewFactory.loadAddRemoveMagazineView();
        break;
      case LIBRARY_USER:
        root = viewFactory.loadAddRemoveLibraryUserView();
        break;


    //  case "home":
    //    root = factory.loadHomeView();
    //    break;
      default:
        throw new IllegalArgumentException("Unknown view: " + id);
    }
    currentScene.setRoot(root);
    if (root.getUserData() == null) {
      primaryStage.setTitle("");
    } else {
      primaryStage.setTitle(root.getUserData().toString());
    }
    primaryStage.setScene(currentScene);
    primaryStage.sizeToScene();
    primaryStage.show();
  }


  public void closeView() {
    primaryStage.close();
  }



}
