package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
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
    }

    @FXML
    public void multimediaItemSearchButtonPressed(){

    }

    @FXML
    public void showBookListButtonPressed(){
        
    }

    @FXML void okButtonPressed(){

    }

    @FXML void showMagazineListButtonPressed(){

    }
    @FXML  void selectMultimediaItemButtonPressed(){

    }
    @FXML void cancelButtonPressed(){

    }
    @FXML void lendMultimediaItemButtonPressed(){

    }
    @FXML void homeMenuButtonPressed(){}

    @FXML void multimediaItemMenuButtonPressed(){
    }
    @FXML void logOutButtonPressed(){

    }


}
