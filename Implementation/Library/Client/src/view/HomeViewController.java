package view;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;


public class HomeViewController
{
  private ViewHandler viewHandler;
  private Region root;

  /**
   * A predefine method to initialize an object after its creation
   * @param viewHandler
   * The view handler
   * @param root
   * The root
   */
  public void init(ViewHandler viewHandler,Region root) throws
      RemoteException
  {
    this.viewHandler = viewHandler;
    this.root = root;
  }

  /**
   * Opens the librarian view when the button is pressed
   */
  @FXML
  public void managerButtonPressed() throws  RemoteException
  {
    viewHandler.openView(viewHandler.LIBRARIAN);
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
   * Opens the librarian home when the button is pressed
   */
  @FXML
  public void librarianButtonPressed() throws  RemoteException
  {
    viewHandler.openView(viewHandler.LIBRARIAN_HOME);
  }

  /**
   * Get root method
   * @return
   * The root
   */
  public Region getRoot()
  {
    return root;
  }
}