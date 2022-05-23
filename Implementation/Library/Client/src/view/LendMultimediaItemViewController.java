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

import java.rmi.RemoteException;
import java.sql.SQLException;

public class LendMultimediaItemViewController {
    private ViewHandler viewHandler;
    private Region root;
    private LoanMagazineViewModel magazineViewModel;
    private LoanBookViewModel bookViewModel;


    @FXML private Label multimediaItemLabel;
    @FXML private Label errorLabelBook;
    @FXML private Label errorLabelMagazine;
    @FXML private Label selectedLibraryUserLabel;
    @FXML private TextField multimediaItemSearchTextField;
    @FXML private TextField ssnTextField;
    @FXML private ListView<MultimediaItem> magazineListView;
    @FXML private ListView<MultimediaItem> bookListView;



    public void init(ViewHandler viewHandler, LoanMagazineViewModel magazineViewModel, LoanBookViewModel bookViewModel, Region root) throws SQLException, RemoteException {
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
        magazineViewModel.bindAvailableMagazineList(magazineListView.itemsProperty());


        bookViewModel.bindMultimediaItemLabel(multimediaItemLabel.textProperty());
        bookViewModel.bindSelectedLibraryUserLabel(selectedLibraryUserLabel.textProperty());
        bookViewModel.bindMultimediaItemSearchTextField(multimediaItemSearchTextField.textProperty());
        bookViewModel.bindErrorLabel(errorLabelBook.textProperty());
        bookViewModel.bindSsnTextField(ssnTextField.textProperty());
        bookViewModel.bindAvailableBooksList(bookListView.itemsProperty());


    }

    @FXML
    public void multimediaItemSearchButtonPressed(){
        //Not now
    }

    @FXML
    public void showBookListButtonPressed() throws SQLException, RemoteException
    {
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

    @FXML void showMagazineListButtonPressed() throws SQLException, RemoteException {
      resetErrorLabels();
        bookListView.setVisible(false);
        magazineListView.setVisible(true);
        magazineViewModel.setMagazineList();
    }

    @FXML void cancelButtonPressed() throws SQLException, RemoteException {
        reset();
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
        catch (SQLException | RemoteException e)
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
       catch (SQLException | RemoteException e)
       {
         e.printStackTrace();
       }
     }
     catch (NullPointerException e){
       errorLabelBook.setText("No book selected");
     }


    }

    @FXML void homeMenuButtonPressed() throws SQLException, RemoteException {
        viewHandler.openView(ViewHandler.HOME);
    }

    @FXML void multimediaItemMenuButtonPressed() throws SQLException, RemoteException {
        viewHandler.openView(ViewHandler.ITEM);
    }
  @FXML public void onAddLibraryUserButtonPressed()
      throws SQLException, RemoteException
  {
    viewHandler.openView(ViewHandler.LIBRARY_USER);
  }

  @FXML public void onLoanButtonPressed()
      throws SQLException, RemoteException
  {
    viewHandler.openView(ViewHandler.LENDMULTIMEDIAITEM);
  }

  @FXML public void onReturnButtonPressed()
      throws SQLException, RemoteException
  {
    viewHandler.openView(ViewHandler.RETURNMULTIMEDIAITEM);
  }

    @FXML void logOutButtonPressed(){
            viewHandler.closeView();
    }

    public void reset() throws SQLException, RemoteException {
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
