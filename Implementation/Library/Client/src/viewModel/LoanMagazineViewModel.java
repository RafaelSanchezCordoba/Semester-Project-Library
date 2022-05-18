package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import mediator.ModelLoanMagazine;

import model.LoanMagazine;
import model.Magazine;
import model.MultimediaItem;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanMagazineViewModel implements PropertyChangeListener {
    private final ModelLoanMagazine model;

    private final StringProperty multimediaItemLabel;
    private final StringProperty errorLabel;
    private final StringProperty selectedMultimediaItemLabel;
    private final StringProperty selectedLibraryUserLabel;
    private final StringProperty  multimediaItemSearchTextField;
    private final StringProperty ssnTextField;
    private SimpleListProperty<MultimediaItem> availableMagazines ;

    public LoanMagazineViewModel(ModelLoanMagazine model){
     this.model= model;
     this.multimediaItemLabel = new SimpleStringProperty("");
     this.errorLabel = new SimpleStringProperty("");
     this.selectedLibraryUserLabel = new SimpleStringProperty("");
     this.selectedMultimediaItemLabel = new SimpleStringProperty("");
     this.multimediaItemSearchTextField = new SimpleStringProperty("");
     this.ssnTextField = new SimpleStringProperty();
     ObservableList<MultimediaItem> observableList = FXCollections.observableArrayList( new ArrayList<MultimediaItem>());
     this.availableMagazines = new SimpleListProperty<>(observableList);

        model.addPropertyChangeListener("newLoanMagazine", this);
        model.addPropertyChangeListener("removeLoanMagazine", this);
    }

    public void bindMultimediaItemLabel(StringProperty property){
        property.bindBidirectional(multimediaItemLabel);
    }

    public void bindErrorLabel(StringProperty property){
        property.bind(errorLabel);
    }

    public void bindSelectedMultimediaItemLabel(StringProperty property){
        property.bindBidirectional(selectedMultimediaItemLabel);
    }

    public void bindSelectedLibraryUserLabel(StringProperty property){
        property.bindBidirectional(selectedLibraryUserLabel);
    }

    public void bindMultimediaItemSearchTextField(StringProperty property){
        property.bindBidirectional(multimediaItemLabel);
    }

    public void bindSsnTextField(StringProperty property){
        property.bindBidirectional(ssnTextField);
    }

    public void bindAvailableMagazineList(ObjectProperty<ObservableList<MultimediaItem>> property){
        property.bind(availableMagazines);
    }
    public void reset() throws SQLException, RemoteException{
       setMagazineList();
       multimediaItemLabel.set("");
       errorLabel.set("");
       selectedMultimediaItemLabel.set("");
       selectedLibraryUserLabel.set("");
       multimediaItemSearchTextField.set("");
       ssnTextField.set("");
    }

    public void setMagazineList() throws RemoteException, SQLException{
        availableMagazines.clear();
        availableMagazines.addAll(model.getAvailableMagazineList());
    }

    public void createLoan(LoanMagazine loanMagazine) throws SQLException, RemoteException {
        model.addMagazineLoan(loanMagazine);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            reset();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
