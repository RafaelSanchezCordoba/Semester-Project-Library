package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.LoanMagazine;
import model.MultimediaItem;
import viewModel.LoanMagazineViewModel;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class LendMultimediaItemViewController {
    private ViewHandler viewHandler;
    private Region root;
    private LoanMagazineViewModel viewModel;

    @FXML private Label multimediaItemLabel;
    @FXML private Label errorLabel;
    @FXML private Label selectedMultimediaItemLabel;
    @FXML private Label selectedLibraryUserLabel;
    @FXML private TextField multimediaItemSearchTextField;
    @FXML private TextField ssnTextField;
    @FXML private ListView<MultimediaItem> multimediaItemListView;


    public void init(ViewHandler viewHandler, LoanMagazineViewModel viewModel, Region root) throws SQLException, RemoteException {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        viewModel.bindErrorLabel(errorLabel.textProperty());
        viewModel.bindMultimediaItemLabel(multimediaItemLabel.textProperty());
        viewModel.bindSelectedMultimediaItemLabel(selectedMultimediaItemLabel.textProperty());
        viewModel.bindSelectedLibraryUserLabel(selectedLibraryUserLabel.textProperty());
        viewModel.bindMultimediaItemSearchTextField(multimediaItemSearchTextField.textProperty());
        viewModel.bindSsnTextField(ssnTextField.textProperty());
        viewModel.bindAvailableMagazineList(multimediaItemListView.itemsProperty());

    }

    @FXML
    public void multimediaItemSearchButtonPressed(){
        //Not now
    }

    @FXML
    public void showBookListButtonPressed(){
        //Not now
    }

    @FXML void okButtonPressed(){
        //We need the library User
    }

    @FXML void showMagazineListButtonPressed() throws SQLException, RemoteException {
        viewModel.setMagazineList();
    }
    @FXML  void selectMultimediaItemButtonPressed(){
        MultimediaItem selectedMultimediaItem;
        selectedMultimediaItem = multimediaItemListView.getSelectionModel().getSelectedItem();
        selectedMultimediaItemLabel.setText(selectedMultimediaItem.toString());
    }
    @FXML void cancelButtonPressed() throws SQLException, RemoteException {
        viewModel.reset();
    }

    @FXML void lendMultimediaItemButtonPressed() throws SQLException, RemoteException {
        int id_magazine = multimediaItemListView.getSelectionModel().getSelectedItem().getId();
        LoanMagazine loanMagazine = new LoanMagazine(id_magazine, ssnTextField.getText());
        viewModel.createLoan(loanMagazine);
    }

    @FXML void homeMenuButtonPressed() throws SQLException, RemoteException {
        viewHandler.openView(ViewHandler.HOME);
    }

    @FXML void multimediaItemMenuButtonPressed() throws SQLException, RemoteException {
        viewHandler.openView(ViewHandler.LENDMULTIMEDIAITEM);
    }

    @FXML void logOutButtonPressed(){
            viewHandler.closeView();
    }

    public void reset() throws SQLException, RemoteException {
        viewModel.reset();
    }

    public Region getRoot(){
        return root;
    }


}
