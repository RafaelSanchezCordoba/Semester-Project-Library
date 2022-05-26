package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelLoanMagazine;
import model.LibraryUser;
import model.LoanMagazine;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

import java.util.ArrayList;

public class ReturnMagazineViewModel implements PropertyChangeListener {
    private final ModelLoanMagazine model;
    private LibraryUser user;

    private final StringProperty multimediaItemLabel;
    private final StringProperty errorLabel;
    private final StringProperty selectedMultimediaItemLabel;
    private final StringProperty selectedLibraryUserLabel;
    private final StringProperty ssnTextField;
    private SimpleListProperty<LoanMagazine> loanedMagazines ;

    /**
     * ReturnMagazineViewModel constructor that: set the model
     * ,add the property change listener for adding and removing a book
     * and set JavaFX variables
     * @param model
     * The model loan magazine
     */
    public ReturnMagazineViewModel(ModelLoanMagazine model) {
        this.model= model;
        this.multimediaItemLabel = new SimpleStringProperty("");
        this.errorLabel = new SimpleStringProperty("");
        this.selectedLibraryUserLabel = new SimpleStringProperty("");
        this.selectedMultimediaItemLabel = new SimpleStringProperty("");
        this.ssnTextField = new SimpleStringProperty();
        ObservableList<LoanMagazine> observableList = FXCollections.observableArrayList( new ArrayList<LoanMagazine>());
        this.loanedMagazines = new SimpleListProperty<>(observableList);

        model.addPropertyChangeListener("removeLoanMagazine", this);
    }

    /**
     * Bind the multimedia item label
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
     * Bind the loaned magazines list
     * @param property
     * Object property, loan magazine observable list
     */
    public void bindLoanedMagazines(ObjectProperty<ObservableList<LoanMagazine>> property){
        property.bind(loanedMagazines);
    }

    /**
     * Reset all the JavaFX variables
     */
    public void reset() throws  RemoteException {
        multimediaItemLabel.set("");
        errorLabel.set("");
        selectedMultimediaItemLabel.set("");
        //  selectedLibraryUserLabel.set("");
         ssnTextField.set("");
    }

    /**
     * Set the magazine list with all the magazines
     */
    public void setLoanedMagazines() throws RemoteException{
        loanedMagazines.clear();
        loanedMagazines.addAll(model.getUserLoans(user.getSSN()));

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
                errorLabel.set("");
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
     * Return magazine method
     * @param id_loan
     * The unique identification number of the loan
     */
    public void returnMagazine(int id_loan) throws RemoteException {
        if(user==null)
        {
            errorLabel.set("First fill the ssn");
        }
        else
        {
            model.returnMagazine(id_loan);
        }
    }

    /**
     * Property change method that call different methods depends on the event name
     * @param evt
     * A book object passed as an event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            reset();
            setLoanedMagazines();
        } catch ( RemoteException e) {
            e.printStackTrace();
        }
    }
}
