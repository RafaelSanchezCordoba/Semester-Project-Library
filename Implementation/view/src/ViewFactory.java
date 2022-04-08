import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOError;
import java.io.IOException;

public class ViewFactory
{
  private ViewModelFactory viewModelFactory;
  private ViewHandler viewHandler;
  private AddRemoveBookViewController bookController;
  private AddRemoveMagazineViewController magazineController;


  public ViewFactory(ViewHandler viewHandler,ViewModelFactory viewModelFactory)
  {
    this.viewHandler=viewHandler;
    this.viewModelFactory=viewModelFactory;
    bookController=null;
    magazineController=null;
  }

  public Region loadAddRemoveBookView()
  {if (bookController == null) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("add_remove book.fxml"));
    try {
      Region root = loader.load();
      bookController = loader.getController();
      bookController.init(viewHandler, viewModelFactory.getAddRemoveBookViewModel(), root);
    } catch (IOException e) {
      throw new IOError(e);
    }
  }
    bookController.reset();
    return bookController.getRoot();
  }

  public Region loadAddRemoveMagazineView()
  {if (magazineController == null) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("add_remove magazine.fxml"));
    try {
      Region root = loader.load();
      magazineController = loader.getController();
      magazineController.init(viewHandler, viewModelFactory.getAddRemoveMagazineViewModel(), root);
    } catch (IOException e) {
      throw new IOError(e);
    }
  }
    magazineController.reset();
    return magazineController.getRoot();
  }
}
