package view;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;


public class LibrarianHomeViewController
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
   * Closes the actual view when the button is pressed
   */
  @FXML
  public void logOutButtonPressed()
  {
    viewHandler.closeView();
  }

  /**
   * Opens the chose item view when the button is pressed
   */
  @FXML public void multimediaItemMenuButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(viewHandler.ITEM);
  }

  /**
   * Opens the Home view when the button is pressed
   */
  @FXML public void homeMenuButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(viewHandler.HOME);
  }

  /**
   * Opens the library user view when the button is pressed
   */
  @FXML public void onAddLibraryUserButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(viewHandler.LIBRARY_USER);
  }

  /**
   * Opens the lent multimedia item view when the button is pressed
   */
  @FXML public void onLoanButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(viewHandler.LENDMULTIMEDIAITEM);
  }

  /**
   * Opens the return multimedia item view when the button is pressed
   */
  @FXML public void onReturnButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(viewHandler.RETURNMULTIMEDIAITEM);
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
