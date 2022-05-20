package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.LoanBook;
import model.LoanMagazine;
import model.MultimediaItem;
import viewModel.LoanBookViewModel;
import viewModel.ReturnBookViewModel;
import viewModel.ReturnMagazineViewModel;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ReturnMultimediaItemViewController
{
  private ViewHandler viewHandler;
  private Region root;
  private ReturnMagazineViewModel magazineViewModel;
  private ReturnBookViewModel bookViewModel;


  @FXML private Label multimediaItemLabel;
  @FXML private Label errorLabelBook;
  @FXML private Label errorLabelMagazine;
  @FXML private Label selectedLibraryUserLabel;
  @FXML private TextField multimediaItemSearchTextField;
  @FXML private TextField ssnTextField;
  @FXML private ListView<LoanMagazine> magazineListView;
  @FXML private ListView<LoanBook> bookListView;


  public void init(ViewHandler viewHandler,ReturnMagazineViewModel magazineViewModel
                   /*ReturnBookViewModel bookViewModel*/,Region root  ) throws SQLException, RemoteException {
    this.viewHandler = viewHandler;
    this.magazineViewModel = magazineViewModel;
    this.bookViewModel=bookViewModel;
    this.root = root;

    bookListView.setVisible(false);
    magazineListView.setVisible(false);

    magazineViewModel.bindErrorLabel(errorLabelMagazine.textProperty());
    magazineViewModel.bindMultimediaItemLabel(multimediaItemLabel.textProperty());
    magazineViewModel.bindSelectedLibraryUserLabel(selectedLibraryUserLabel.textProperty());
    magazineViewModel.bindMultimediaItemSearchTextField(multimediaItemSearchTextField.textProperty());
    magazineViewModel.bindSsnTextField(ssnTextField.textProperty());
    magazineViewModel.bindLoanedMagazines(magazineListView.itemsProperty());


//    bookViewModel.bindMultimediaItemLabel(multimediaItemLabel.textProperty());
//    bookViewModel.bindSelectedLibraryUserLabel(selectedLibraryUserLabel.textProperty());
//    bookViewModel.bindMultimediaItemSearchTextField(multimediaItemSearchTextField.textProperty());
//    bookViewModel.bindErrorLabel(errorLabelBook.textProperty());
//    bookViewModel.bindSsnTextField(ssnTextField.textProperty());
//    bookViewModel.bindAvailableBooksList(bookListView.itemsProperty());
  }

  @FXML
  public void multimediaItemSearchButtonPressed() {
    //Not now
  }
  @FXML
  public  void homeMenuButtonPressed() {

  }
  @FXML
  public void multimediaItemMenuButtonPressed() throws RemoteException,SQLException{
    viewHandler.openView(viewHandler.HOME);
  }

  @FXML
  public void  logOutButtonPressed() {
    viewHandler.closeView();
  }

  @FXML
  public void  cancelButtonPressed() throws SQLException, RemoteException{
    reset();
  }

  @FXML
  public void onReturnMagazine() {
    try {
      int id_magazineLoan= magazineListView.getSelectionModel().getSelectedItem().getId();

      try {
        magazineViewModel.returnMagazine(id_magazineLoan);
      } catch (RemoteException e) {
        e.printStackTrace();
      }

    }catch (NullPointerException e){
      errorLabelMagazine.setText("No magazineSelected");
    }
  }

  @FXML
  public void okButtonPressed() {
    resetErrorLabels();
    magazineViewModel.getUser(ssnTextField.getText());
    //bookViewModel.getUser(ssnTextField.getText());

    ssnTextField.setText("");
  }

  @FXML
  public void  onReturnBook(){
//    resetErrorLabels();
//    try
//    {
//      int id_bookLoaned = bookListView.getSelectionModel().getSelectedItem().getId();
//      try
//      {
//        //
//      }
//      catch (SQLException e)
//      {
//        e.printStackTrace();
//      }
//      catch (RemoteException e)
//      {
//        e.printStackTrace();
//      }
//    }
//    catch (NullPointerException e){
//      errorLabelBook.setText("No book selected");
//    }

  }
  public void reset() throws SQLException, RemoteException {
    //bookViewModel.reset();
    magazineViewModel.reset();
  }

  public Region getRoot(){
    return root;
  }

  public void resetErrorLabels(){
    errorLabelBook.setText("");
    errorLabelMagazine.setText("");
  }
  @FXML
  public void showBookListButtonPressed() throws SQLException, RemoteException
  {
//    resetErrorLabels();
//    bookListView.setVisible(true);
//    magazineListView.setVisible(false);
//    bookViewModel.setBookList();
  }
  @FXML void showMagazineListButtonPressed() throws SQLException, RemoteException {
    resetErrorLabels();
    bookListView.setVisible(false);
    magazineListView.setVisible(true);
    magazineViewModel.setLoanedMagazines();
  }
}
