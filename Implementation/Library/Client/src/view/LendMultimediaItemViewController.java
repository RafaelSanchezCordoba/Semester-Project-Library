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


    @FXML
    public void showBookListButtonPressed() throws  RemoteException
    {
      multimediaItemLabel.setText("BOOKS");
        resetErrorLabels();
        bookListView.setVisible(true);
        magazineListView.setVisible(false);
        bookViewModel.setBookList();
    }

    @FXML void okButtonPressed()
    {
      resetErrorLabels();
      magazineViewModel.getUser(ssnTextField.getText());
      bookViewModel.getUser(ssnTextField.getText());

      ssnTextField.setText("");


    }

    @FXML void showMagazineListButtonPressed() throws RemoteException {
      multimediaItemLabel.setText("MAGAZINES");
      resetErrorLabels();
        bookListView.setVisible(false);
        magazineListView.setVisible(true);
        magazineViewModel.setMagazineList();
    }


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

    @FXML void homeMenuButtonPressed() throws  RemoteException {
        viewHandler.openView(ViewHandler.HOME);
    }

    @FXML void multimediaItemMenuButtonPressed() throws  RemoteException {
        viewHandler.openView(ViewHandler.ITEM);
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

    @FXML void logOutButtonPressed(){
            viewHandler.closeView();
    }

    public void reset() throws  RemoteException {
        bookViewModel.setBookList();
        magazineViewModel.setMagazineList();

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
}
