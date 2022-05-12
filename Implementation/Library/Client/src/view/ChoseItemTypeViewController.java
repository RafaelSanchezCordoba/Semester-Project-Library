package view;

import javafx.fxml.FXML;

import javafx.scene.layout.Region;


import java.rmi.RemoteException;
import java.sql.SQLException;

public class ChoseItemTypeViewController
{
  private ViewHandler viewHandler;
  private Region root;


  public void init(ViewHandler viewHandler,Region root) throws SQLException, RemoteException
  {
    this.viewHandler = viewHandler;
    this.root = root;
  }


  @FXML
  public void homeMenuButtonPressed() throws SQLException, RemoteException
  {
    viewHandler.openView(viewHandler.HOME);
  }

  @FXML
  public void logOutButtonPressed()
  {
    viewHandler.closeView();
  }

  @FXML
  public void bookMenuButtonPressed() throws SQLException, RemoteException
  {
    viewHandler.openView(viewHandler.BOOK);
  }

  @FXML
  public void magazinesMenuButtonPressed() throws SQLException, RemoteException {
    viewHandler.openView(viewHandler.MAGAZINE);
  }

  public Region getRoot()
  {
    return root;
  }
}
