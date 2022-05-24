package view;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class LibrarianHomeViewController
{
  private ViewHandler viewHandler;
  private Region root;


  public void init(ViewHandler viewHandler,Region root) throws SQLException,
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
      throws SQLException, RemoteException
  {
    viewHandler.openView(viewHandler.ITEM);
  }

  @FXML public void homeMenuButtonPressed()
      throws SQLException, RemoteException
  {
    viewHandler.openView(viewHandler.HOME);
  }

  @FXML public void onAddLibraryUserButtonPressed()
      throws SQLException, RemoteException
  {
    viewHandler.openView(viewHandler.LIBRARY_USER);
  }
  @FXML public void onLoanButtonPressed()
      throws SQLException, RemoteException
  {
    viewHandler.openView(viewHandler.LENDMULTIMEDIAITEM);
  }

  @FXML public void onReturnButtonPressed()
      throws SQLException, RemoteException
  {
    viewHandler.openView(viewHandler.RETURNMULTIMEDIAITEM);
  }


  public Region getRoot()
  {
    return root;
  }

}
