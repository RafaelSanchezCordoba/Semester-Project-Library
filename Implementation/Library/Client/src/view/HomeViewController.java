package view;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class HomeViewController
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
  public void managerButtonPressed() throws SQLException, RemoteException
  {
    viewHandler.openView(viewHandler.LIBRARIAN);
  }

  @FXML
  public void logOutButtonPressed()
  {
    viewHandler.closeView();
  }

  @FXML
  public void librarianButtonPressed() throws SQLException, RemoteException
  {
    viewHandler.openView(viewHandler.LIBRARIAN_HOME);
  }


  public Region getRoot()
  {
    return root;
  }
}