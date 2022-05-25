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
  @FXML private TextField ssnTextField;
  @FXML private ListView<LoanMagazine> magazineListView;
  @FXML private ListView<LoanBook> bookListView;


  public void init(ViewHandler viewHandler, ReturnMagazineViewModel magazineViewModel, ReturnBookViewModel returnBookViewModel, Region root)
      throws  RemoteException {
    this.viewHandler = viewHandler;
    this.magazineViewModel = magazineViewModel;
    this.bookViewModel= returnBookViewModel;
    this.root = root;

      bookListView.setVisible(false);
      magazineListView.setVisible(false);

    magazineViewModel.bindErrorLabel(errorLabelMagazine.textProperty());
    magazineViewModel.bindMultimediaItemLabel(multimediaItemLabel.textProperty());
    magazineViewModel.bindSelectedLibraryUserLabel(selectedLibraryUserLabel.textProperty());
    magazineViewModel.bindSsnTextField(ssnTextField.textProperty());
    magazineViewModel.bindLoanedMagazines(magazineListView.itemsProperty());


    bookViewModel.bindMultimediaItemLabel(multimediaItemLabel.textProperty());
    bookViewModel.bindSelectedLibraryUserLabel(selectedLibraryUserLabel.textProperty());
    bookViewModel.bindErrorLabel(errorLabelBook.textProperty());
    bookViewModel.bindSsnTextField(ssnTextField.textProperty());
    bookViewModel.bindLoanedBooks(bookListView.itemsProperty());
  }


  @FXML
  public  void homeMenuButtonPressed() throws  RemoteException
  {
    viewHandler.openView(ViewHandler.HOME);
  }
  @FXML
  public void multimediaItemMenuButtonPressed() throws RemoteException {
    viewHandler.openView(ViewHandler.ITEM);
  }

  @FXML
  public void  logOutButtonPressed() {
    viewHandler.closeView();
  }


  @FXML
  public void okButtonPressed() {
    resetErrorLabels();
    magazineViewModel.getUser(ssnTextField.getText());
    bookViewModel.getUser(ssnTextField.getText());

    ssnTextField.setText("");
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
      errorLabelMagazine.setText("No magazine selected");
    }
  }


  @FXML
  public void  onReturnBook(){
    try {
      int id_bookLoan= bookListView.getSelectionModel().getSelectedItem().getId();

      try {
        bookViewModel.returnBook(id_bookLoan);
      } catch (RemoteException e) {
        e.printStackTrace();
      }

    }catch (NullPointerException e){
      errorLabelBook.setText("no book selected");
    }

  }
  public void reset() throws  RemoteException {
    bookViewModel.reset();
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
  public void showBookListButtonPressed() throws  RemoteException
  {
    multimediaItemLabel.setText("BOOKS");
    resetErrorLabels();
    bookListView.setVisible(true);
    magazineListView.setVisible(false);
    bookViewModel.setLoanedBooks();

  }
  @FXML
  void showMagazineListButtonPressed() throws  RemoteException {
    multimediaItemLabel.setText("MAGAZINES");
    resetErrorLabels();
    bookListView.setVisible(false);
    magazineListView.setVisible(true);
    magazineViewModel.setLoanedMagazines();
  }

  @FXML public void onAddLibraryUserButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.LIBRARY_USER);
  }

  @FXML public void onLoanButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.LENDMULTIMEDIAITEM);
  }

  @FXML public void onReturnButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.RETURNMULTIMEDIAITEM);
  }
}
