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
import viewModel.LoanMagazineViewModel;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class LendMultimediaItemViewController {
    private ViewHandler viewHandler;
    private Region root;
    private LoanMagazineViewModel magazineViewModel;
    private LoanBookViewModel bookViewModel;
    private String currentlyShowing;

    @FXML private Label multimediaItemLabel;
    @FXML private Label errorLabel;
    @FXML private Label selectedMultimediaItemLabel;
    @FXML private Label selectedLibraryUserLabel;
    @FXML private TextField multimediaItemSearchTextField;
    @FXML private TextField ssnTextField;
    @FXML private ListView<MultimediaItem> multimediaItemListView;


    public void init(ViewHandler viewHandler, LoanMagazineViewModel magazineViewModel, LoanBookViewModel bookViewModel, Region root) throws SQLException, RemoteException {
        this.viewHandler = viewHandler;
        this.magazineViewModel = magazineViewModel;
        this.bookViewModel=bookViewModel;
        this.root = root;
        currentlyShowing="books";

        magazineViewModel.bindErrorLabel(errorLabel.textProperty());
        magazineViewModel.bindMultimediaItemLabel(multimediaItemLabel.textProperty());
        magazineViewModel.bindSelectedMultimediaItemLabel(selectedMultimediaItemLabel.textProperty());
        magazineViewModel.bindSelectedLibraryUserLabel(selectedLibraryUserLabel.textProperty());
        magazineViewModel.bindMultimediaItemSearchTextField(multimediaItemSearchTextField.textProperty());
        magazineViewModel.bindSsnTextField(ssnTextField.textProperty());
        magazineViewModel.bindAvailableMagazineList(multimediaItemListView.itemsProperty());

        bookViewModel.bindAvailableBooksList(multimediaItemListView.itemsProperty());
        bookViewModel.bindMultimediaItemLabel(multimediaItemLabel.textProperty());
        bookViewModel.bindSelectedLibraryUserLabel(selectedLibraryUserLabel.textProperty());
        bookViewModel.bindSelectedMultimediaItemLabel(selectedMultimediaItemLabel.textProperty());
        bookViewModel.bindMultimediaItemSearchTextField(multimediaItemSearchTextField.textProperty());
        bookViewModel.bindErrorLabel(errorLabel.textProperty());
        bookViewModel.bindSsnTextField(ssnTextField.textProperty());
    }

    @FXML
    public void multimediaItemSearchButtonPressed(){
        //Not now
    }

    @FXML
    public void showBookListButtonPressed() throws SQLException, RemoteException
    {
        currentlyShowing="books";
        bookViewModel.setBookList();
    }

    @FXML void okButtonPressed() throws SQLException, RemoteException
    {
        selectedLibraryUserLabel.setText(ssnTextField.getText());
        ssnTextField.setText("");
    }

    @FXML void showMagazineListButtonPressed() throws SQLException, RemoteException {
       currentlyShowing="magazines";
        magazineViewModel.setMagazineList();
    }
    @FXML  void selectMultimediaItemButtonPressed(){
        MultimediaItem selectedMultimediaItem;
        selectedMultimediaItem = multimediaItemListView.getSelectionModel().getSelectedItem();
        selectedMultimediaItemLabel.setText(selectedMultimediaItem.toString());
    }
    @FXML void cancelButtonPressed() throws SQLException, RemoteException {
        reset();
    }

    @FXML void lendMultimediaItemButtonPressed() throws SQLException, RemoteException {
        if (currentlyShowing.equals("magazines"))
        {
            int id_magazine = multimediaItemListView.getSelectionModel().getSelectedItem().getId();
            LoanMagazine loanMagazine = new LoanMagazine(id_magazine, ssnTextField.getText());
            magazineViewModel.createLoan(loanMagazine);
        }
        else if (currentlyShowing.equals("books"))
        {
            int id_book = multimediaItemListView.getSelectionModel().getSelectedItem().getId();
            LoanBook loanBook = new LoanBook(id_book, ssnTextField.getText());
            bookViewModel.createLoan(loanBook);
        }

    }

    @FXML void homeMenuButtonPressed() throws SQLException, RemoteException {
        viewHandler.openView(ViewHandler.HOME);
    }

    @FXML void multimediaItemMenuButtonPressed() throws SQLException, RemoteException {
        viewHandler.openView(ViewHandler.ITEM);
    }

    @FXML void logOutButtonPressed(){
            viewHandler.closeView();
    }

    public void reset() throws SQLException, RemoteException {
        if (currentlyShowing.equals("books"))
        {
            bookViewModel.reset();
        }
        else if (currentlyShowing.equals("magazines"))
        {
            magazineViewModel.reset();
        }
    }

    public Region getRoot(){
        return root;
    }


}
