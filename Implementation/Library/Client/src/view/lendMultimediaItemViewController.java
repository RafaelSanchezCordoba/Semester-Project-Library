package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.MultimediaItem;
import viewModel.AddRemoveLibrarianViewModel;
import viewModel.lendMultimediaItemViewModel;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class lendMultimediaItemViewController {
    private ViewHandler viewHandler;
    private Region root;
    private lendMultimediaItemViewModel viewModel;

    @FXML private Label multimediaItemLabel;
    @FXML private Label errorLabel;
    @FXML private Label selectedMultimediaItemLabel;
    @FXML private Label selectedLibraryUserLabel;
    @FXML private TextField multimediaItemSearchTextField;
    @FXML private TextField ssnTextField;
    @FXML private ListView<MultimediaItem> multimediaItemListView;


    public void init(ViewHandler viewHandler, lendMultimediaItemViewModel viewModel, Region root) throws SQLException, RemoteException {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
    }

    @FXML
    public void multimediaItemSearchButtonPressed() {

    }

    @FXML
    public void showBookListButtonPressed() {
        
    }

}
