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

    /**
     * AddRemoveLibraryUserViewModel constructor that:
     * set the model
     * ,add the property change listener for adding and removing a book
     * and set JavaFX variables
     * @param model
     * The model library user
     */
    public LoanMagazineViewModel(ModelLoanMagazine model) {
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

    /**
     * Bind multimedia item label
     * @param property
     * A String property
     */
    public void bindMultimediaItemLabel(StringProperty property){
        property.bindBidirectional(multimediaItemLabel);
    }

    /**
     * Bind the error label
     * @param property
     * A String property
     */
    public void bindErrorLabel(StringProperty property){
        property.bindBidirectional(errorLabel);
    }

    /**
     * Bind the selected library user label
     * @param property
     * A String property
     */
    public void bindSelectedLibraryUserLabel(StringProperty property){
        property.bindBidirectional(selectedLibraryUserLabel);
    }

    /**
     * Bind the social security number text field
     * @param property
     * A String property
     */
    public void bindSsnTextField(StringProperty property){
        property.bindBidirectional(ssnTextField);
    }

    /**
     * Bind the available magazine list
     * @param property
     * Object property, multimedia item observable list
     */
    public void bindAvailableMagazineList(ObjectProperty<ObservableList<MultimediaItem>> property){
        property.bind(availableMagazines);
    }
    public void reset() throws  RemoteException{
       setMagazineList();
       multimediaItemLabel.set("");
       errorLabel.set("");
       selectedMultimediaItemLabel.set("");
     //  selectedLibraryUserLabel.set("");
      ssnTextField.set("");
    }

    /**
     * Get a user with a specific social security number
     * @param ssn
     * The social security number passed as an argument
     */
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

    /**
     * Set the magazine list with the available magazines
     * @throws RemoteException
     */
    public void setMagazineList() throws RemoteException{
        availableMagazines.clear();
        availableMagazines.addAll(model.getAvailableMagazineList());
    }

    /**
     * Create a new magazine loan
     * @param idMagazine
     * The unique magazine identification number
     */
    public void createLoan(int idMagazine) throws   RemoteException {

      if(user==null)
      {
        errorLabel.set("First fill the ssn");
      }
      else
      {
        model.addMagazineLoan(new LoanMagazine(idMagazine, user.getSSN()));
      }


    }

    /**
     * Property change method that call different methods depends on the event name
     * @param evt
     * A library user object passed as an event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            reset();
        } catch ( RemoteException e) {
            e.printStackTrace();
        }
    }
}
