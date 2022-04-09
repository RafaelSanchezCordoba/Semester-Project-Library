package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.ViewModelFactory;

public class ViewHandler
{
  private static final String HOME = "home";
  private static final String BOOK = "addRemoveBook";
  private static final String MAGAZINE = "addRemoveMagazine";

  private Stage primaryStage;
  private final Scene currentScene;
  private final ViewFactory viewFactory;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewFactory=new ViewFactory(this,viewModelFactory);
    this.currentScene = new Scene(new Region());
  }



  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    openView(BOOK);
  }


  public void openView(String id)
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
