import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewFactory factory;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.factory=new ViewFactory(this,viewModelFactory);
    this.currentScene = new Scene(new Region());
  }

  //cambiar el openView en start!!!!

  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    openView("!!!");
  }


  public void openView(String id)
  {
    Region root;
    switch (id)
    {
      case "addRemoveBook":
        root = factory.loadAddRemoveBookView();
        break;
      case "addRemoveMagazine":
        root = factory.loadAddRemoveMagazineView();
        break;
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
