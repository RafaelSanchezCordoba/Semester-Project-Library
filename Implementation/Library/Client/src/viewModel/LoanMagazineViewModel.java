package viewModel;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import mediator.ModelLoanMagazine;

import model.Magazine;

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
    private SimpleListProperty<Magazine> availableMagazines ;

    public LoanMagazineViewModel(ModelLoanMagazine model){
     this.model= model;
     this.multimediaItemLabel = new SimpleStringProperty("");
     this.errorLabel = new SimpleStringProperty("");
     this.selectedLibraryUserLabel = new SimpleStringProperty("");
     this.selectedMultimediaItemLabel = new SimpleStringProperty("");
     this.multimediaItemSearchTextField = new SimpleStringProperty("");
     this.ssnTextField = new SimpleStringProperty();
     ObservableList<Magazine> observableList = FXCollections.observableArrayList( new ArrayList<Magazine>());
     this.availableMagazines = new SimpleListProperty<>(observableList);

        model.addPropertyChangeListener("newLoanMagazine", this);
        model.addPropertyChangeListener("removeLoanMagazine", this);
    }

    public void bindMultimediaItemLabel(StringProperty property){
        property.bindBidirectional(multimediaItemLabel);
    }
    public void bindErrorLabel(StringProperty property){
        property.bindBidirectional(errorLabel);
    }
    public void bindSelectedMultimediaItemLabel(StringProperty property){
        property.bindBidirectional(selectedLibraryUserLabel);
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
    public void bindMagazineList(SimpleListProperty<Magazine> property){
        property.bindBidirectional(availableMagazines);
    }
    public void reset() throws SQLException, RemoteException{
       setMagazineList();
    }
    public void setMagazineList() throws RemoteException, SQLException{
        availableMagazines.clear();
        availableMagazines.addAll(model.getAvailableMagazineList());
    }

    public void showBookListButtonPressed(){

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
