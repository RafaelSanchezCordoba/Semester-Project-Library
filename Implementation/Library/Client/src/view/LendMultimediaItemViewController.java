package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.LibraryUser;
import model.LoanBook;
import model.LoanMagazine;
import model.MultimediaItem;
import viewModel.LoanBookViewModel;
import viewModel.LoanMagazineViewModel;

import java.lang.management.MemoryUsage;
import java.rmi.RemoteException;


public class LendMultimediaItemViewController {
    private ViewHandler viewHandler;
    private Region root;
    private LoanMagazineViewModel magazineViewModel;
    private LoanBookViewModel bookViewModel;


    @FXML private Label multimediaItemLabel;
    @FXML private Label errorLabelBook;
    @FXML private Label errorLabelMagazine;
    @FXML private Label selectedLibraryUserLabel;
    @FXML private TextField ssnTextField;
    @FXML private ListView<MultimediaItem> magazineListView;
    @FXML private ListView<MultimediaItem> bookListView;

    /**
     * A predefine method to initialize an object after its creation
     * @param viewHandler
     * The view handler
     * @param magazineViewModel
     * The loan magazine view model
     * @param bookViewModel
     * The loan book view model
     * @param root
     * The root
     */
    public void init(ViewHandler viewHandler, LoanMagazineViewModel magazineViewModel, LoanBookViewModel bookViewModel, Region root) throws RemoteException {
        this.viewHandler = viewHandler;
        this.magazineViewModel = magazineViewModel;
        this.bookViewModel=bookViewModel;
        this.root = root;

        bookListView.setVisible(false);
        magazineListView.setVisible(false);

        magazineViewModel.bindErrorLabel(errorLabelMagazine.textProperty());
        magazineViewModel.bindMultimediaItemLabel(multimediaItemLabel.textProperty());
        magazineViewModel.bindSelectedLibraryUserLabel(selectedLibraryUserLabel.textProperty());
        magazineViewModel.bindSsnTextField(ssnTextField.textProperty());
        magazineViewModel.bindAvailableMagazineList(magazineListView.itemsProperty());


        bookViewModel.bindMultimediaItemLabel(multimediaItemLabel.textProperty());
        bookViewModel.bindSelectedLibraryUserLabel(selectedLibraryUserLabel.textProperty());
        bookViewModel.bindErrorLabel(errorLabelBook.textProperty());
        bookViewModel.bindSsnTextField(ssnTextField.textProperty());
        bookViewModel.bindAvailableBooksList(bookListView.itemsProperty());
    }

    /**
     * Makes visible the book list view and shows the book list when the button is pressed
     */
    @FXML
    public void showBookListButtonPressed() throws  RemoteException
    {
      multimediaItemLabel.setText("BOOKS");
        resetErrorLabels();
        bookListView.setVisible(true);
        magazineListView.setVisible(false);
        bookViewModel.setBookList();
    }

    /**
     * Gets the user when the button is pressed
     */
    @FXML void okButtonPressed()
    {
      resetErrorLabels();
      magazineViewModel.getUser(ssnTextField.getText());
      bookViewModel.getUser(ssnTextField.getText());

      ssnTextField.setText("");


    }

    /**
     * Makes visible the magazine list view and shows the magazine list when the button is pressed
     */
    @FXML void showMagazineListButtonPressed() throws RemoteException {
      multimediaItemLabel.setText("MAGAZINES");
      resetErrorLabels();
        bookListView.setVisible(false);
        magazineListView.setVisible(true);
        magazineViewModel.setMagazineList();
    }

    /**
     * Calls the create loan method in the view model with the selected magazine in the magazine list view
     */
    @FXML void onLendMagazine()  {
      resetErrorLabels();
      try
      {
        int id_magazine = magazineListView.getSelectionModel().getSelectedItem()
            .getId();
        try
        {
          magazineViewModel.createLoan(id_magazine);
        }
        catch ( RemoteException e)
        {
          e.printStackTrace();
        }
      }
      catch (NullPointerException e){
        errorLabelMagazine.setText("No magazine selected");
      }
    }

    /**
     * Calls the create loan method in the view model with the selected book in the book list view
     */
    @FXML void onLendBook(){
     resetErrorLabels();

     try
     {

       int id_book = bookListView.getSelectionModel().getSelectedItem().getId();
       try
       {
         bookViewModel.createLoan(id_book);
       }
       catch ( RemoteException e)
       {
         e.printStackTrace();
       }
     }
     catch (NullPointerException e){
       errorLabelBook.setText("No book selected");
     }
    }

    /**
     * Opens the Home view when the button is pressed
     */
    @FXML void homeMenuButtonPressed() throws  RemoteException {
        viewHandler.openView(ViewHandler.HOME);
    }

    /**
     * Opens the chose item view when the button is pressed
     */
    @FXML void multimediaItemMenuButtonPressed() throws  RemoteException {
        viewHandler.openView(ViewHandler.ITEM);
    }

    /**
     * Opens the library user view when the button is pressed
     */
    @FXML public void onAddLibraryUserButtonPressed() throws  RemoteException
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
     * @throws RemoteException
     */
    @FXML public void onReturnButtonPressed()
        throws  RemoteException
    {
        viewHandler.openView(ViewHandler.RETURNMULTIMEDIAITEM);
    }

    /**
     * Closes the actual view when the button is pressed
     */
    @FXML void logOutButtonPressed(){
            viewHandler.closeView();
    }

    /**
     * Calls the method reset in the view model
     */
    public void reset() throws  RemoteException {
        bookViewModel.setBookList();
        magazineViewModel.setMagazineList();

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
     * Sets as blank the error label
     */
    public void resetErrorLabels(){
      errorLabelBook.setText("");
      errorLabelMagazine.setText("");
    }
}
