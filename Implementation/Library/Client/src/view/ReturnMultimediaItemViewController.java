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

  /**
   * A predefine method to initialize an object after its creation
   * @param viewHandler
   * The view handler
   * @param magazineViewModel
   * The return magazine view model
   * @param returnBookViewModel
   * The return book view model
   * @param root
   * The root
   */
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

  /**
   * Opens the Home view when the button is pressed
   */
  @FXML
  public  void homeMenuButtonPressed() throws  RemoteException
  {
    viewHandler.openView(ViewHandler.HOME);
  }

  /**
   * Opens the MultimediaItem view when the button is pressed
   */
  @FXML
  public void multimediaItemMenuButtonPressed() throws RemoteException {
    viewHandler.openView(ViewHandler.ITEM);
  }

  /**
   * Close the actual view
   */
  @FXML
  public void  logOutButtonPressed() {
    viewHandler.closeView();
  }

  /**
   * Get the user when the button is pressed
   */
  @FXML
  public void okButtonPressed() {
    resetErrorLabels();
    magazineViewModel.getUser(ssnTextField.getText());
    bookViewModel.getUser(ssnTextField.getText());

    ssnTextField.setText("");
  }

  /**
   * Calls the method when the button is pressed
   */
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

  /**
   * Calls the method return book
   */
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

  /**
   * Calls the method reset in the view model
   */
  public void reset() throws  RemoteException {
    bookViewModel.reset();
    magazineViewModel.reset();
  }

  /**
   * Get root method
   * @return
   * The root
   */
  public Region getRoot(){
    return root;
  }

  /**
   * Set as blank the error label
   */
  public void resetErrorLabels(){
    errorLabelBook.setText("");
    errorLabelMagazine.setText("");
  }


  /**
   * Shows the loaned book list when the button is pressed
   */
  @FXML
  public void showBookListButtonPressed() throws  RemoteException
  {
    multimediaItemLabel.setText("BOOKS");
    resetErrorLabels();
    bookListView.setVisible(true);
    magazineListView.setVisible(false);
    bookViewModel.setLoanedBooks();

  }

  /**
   * Shows the loaned magazine list when the button is pressed
   */
  @FXML
  void showMagazineListButtonPressed() throws  RemoteException {
    multimediaItemLabel.setText("MAGAZINES");
    resetErrorLabels();
    bookListView.setVisible(false);
    magazineListView.setVisible(true);
    magazineViewModel.setLoanedMagazines();
  }

  /**
   * Opens the library user view when the button is pressed
   */
  @FXML public void onAddLibraryUserButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.LIBRARY_USER);
  }

  /**
   * Opens the lent multimedia item view when the button is pressed
   */
  @FXML public void onLoanButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.LENDMULTIMEDIAITEM);
  }

  /**
   * Opens the return multimedia item view when the button is pressed
   */
  @FXML public void onReturnButtonPressed()
      throws  RemoteException
  {
    viewHandler.openView(ViewHandler.RETURNMULTIMEDIAITEM);
  }
}
