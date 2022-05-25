package view;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;


public class LibrarianHomeViewController
{
  private ViewHandler viewHandler;
  private Region root;


  public void init(ViewHandler viewHandler,Region root) throws
      RemoteException
  {
    this.viewHandler = viewHandler;
    this.root = root;
  }


  @FXML
  public void logOutButtonPressed()
  {
    viewHandler.closeView();
  }


  @FXML public void multimediaItemMenuButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(viewHandler.ITEM);
  }

  @FXML public void homeMenuButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(viewHandler.HOME);
  }

  @FXML public void onAddLibraryUserButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(viewHandler.LIBRARY_USER);
  }
  @FXML public void onLoanButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(viewHandler.LENDMULTIMEDIAITEM);
  }

  @FXML public void onReturnButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(viewHandler.RETURNMULTIMEDIAITEM);
  }


  public Region getRoot()
  {
    return root;
  }

}
