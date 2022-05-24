package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import mediator.ModelLoanMagazine;

import model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanMagazineViewModel implements PropertyChangeListener {
    private final ModelLoanMagazine model;
    private LibraryUser user;

    private final StringProperty multimediaItemLabel;
    private final StringProperty errorLabel;
    private final StringProperty selectedMultimediaItemLabel;
    private final StringProperty selectedLibraryUserLabel;
    private final StringProperty ssnTextField;
    private SimpleListProperty<MultimediaItem> availableMagazines ;

    public LoanMagazineViewModel(ModelLoanMagazine model){
     this.model= model;
     this.multimediaItemLabel = new SimpleStringProperty("");
     this.errorLabel = new SimpleStringProperty("");
     this.selectedLibraryUserLabel = new SimpleStringProperty("");
     this.selectedMultimediaItemLabel = new SimpleStringProperty("");
     this.ssnTextField = new SimpleStringProperty();
     ObservableList<MultimediaItem> observableList = FXCollections.observableArrayList( new ArrayList<MultimediaItem>());
     this.availableMagazines = new SimpleListProperty<>(observableList);


        model.addPropertyChangeListener("newLoanMagazine", this);
    }

    public void bindMultimediaItemLabel(StringProperty property){
        property.bindBidirectional(multimediaItemLabel);
    }

    public void bindErrorLabel(StringProperty property){
        property.bindBidirectional(errorLabel);
    }



    public void bindSelectedLibraryUserLabel(StringProperty property){
        property.bindBidirectional(selectedLibraryUserLabel);
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
     //  selectedLibraryUserLabel.set("");
      ssnTextField.set("");
    }

    public void getUser(String ssn){
      try
      {
        user = model.getUser(ssn);
        if(user==null){
          errorLabel.set("Library User does not exist");
        }

        else{
        selectedLibraryUserLabel.set(user.getFirstName() + " " + user.getLastName());
        }

      }catch (RemoteException e){
        System.out.println("error");
        errorLabel.set(e.getMessage());
      }
    }

    public void setMagazineList() throws RemoteException, SQLException{
        availableMagazines.clear();
        availableMagazines.addAll(model.getAvailableMagazineList());
    }

    public void createLoan(int  idMagazine) throws SQLException,  RemoteException {

      if(user==null)
      {
        errorLabel.set("First fill the ssn");
      }
      else
      {
        model.addMagazineLoan(new LoanMagazine(idMagazine, user.getSSN()));
      }


    }




    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            reset();
        } catch (SQLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
