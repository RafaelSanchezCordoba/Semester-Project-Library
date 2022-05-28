package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelLibraryUser;
import model.LibraryUser;
import model.Magazine;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

import java.util.ArrayList;

public class AddRemoveLibraryUserViewModel implements PropertyChangeListener {

    private final ModelLibraryUser model;
    private final StringProperty firstNameTextField;
    private final StringProperty lastNameTextField;
    private final StringProperty ssnTextField;
    private final StringProperty passwordTextField;
    private final SimpleListProperty<LibraryUser> userList;
    private final StringProperty errorLabel;

    /**
     * AddRemoveLibraryUserViewModel constructor that:
     * set the model
     * ,add the property change listener for adding and removing a book
     * and set JavaFX variables
     * @param model
     * The model library user
     */
    public AddRemoveLibraryUserViewModel(ModelLibraryUser model) throws RemoteException{
        this.model = model;
        this.firstNameTextField = new SimpleStringProperty("");
        this.lastNameTextField = new SimpleStringProperty("");
        this.ssnTextField = new SimpleStringProperty("");
        this.passwordTextField = new SimpleStringProperty("");
        ObservableList<LibraryUser> observableListUserList = FXCollections.observableArrayList(new ArrayList<>());
        this.userList = new SimpleListProperty<>(observableListUserList);
        this.errorLabel = new SimpleStringProperty("");

        model.addPropertyChangeListener("addLibraryUser",this);
        model.addPropertyChangeListener("removeLibraryUser",this);
    }

    /**
     * Not implemented
     */
    public void search(){}

    /**
     * Check if the information passed is correct if not print out a specific error message
     * @return
     * True if there are any error, false if not
     */
    public boolean errorCheck(){
        if(firstNameTextField.get().equals("")){
            errorLabel.set("First name can't be empty");
            return true;
        }else if(lastNameTextField.get().equals("")){
            errorLabel.set("Last name can't be empty");
            return true;
        }else if(ssnTextField.get().equals("")){
            errorLabel.set("Ssn can't be empty");
            return true;
        }else if(passwordTextField.get().equals("")){
            errorLabel.set("Password can't be empty");
            return true;
        }else if(passwordTextField.get().length() > 20){
            errorLabel.set("Password can't be longer than 20 characters");
        }
        else if(ssnFormatCheck())
        {
            errorLabel.set("The ssn must be 13 digits");
            return true;
        }
        else if (ssnDuplicateCheck())
        {
            errorLabel.set("There is already a library user with that ssn in the system");
            return true;
        }
        return  false;
    }

<<<<<<< Updated upstream
    /**
     * Add library user method
     * @param libraryUser
     * The library user passed as an argument
     */
    public void addLibraryUser(LibraryUser libraryUser) throws RemoteException{
        try {
        if(!errorCheck()){
            model.addLibraryUser(libraryUser);
=======

    public void addLibraryUser() {

        if(!errorCheck())
        {
            model.addLibraryUser(new LibraryUser(ssnTextField.get(),firstNameTextField.get(),lastNameTextField.get(),
                passwordTextField.get()));
>>>>>>> Stashed changes
            clearErrorLabel();
        }
        reset();
    }

    /**
     * Remove a library user with a specific social security number
     * @param ssn
     * The social security number passed as an argument
     */
    public void removeLibraryUser(String ssn) throws RemoteException{
        model.removeLibraryUser(ssn);
    }

    /**
     * Set the library user list
     */
    public void setUserList() throws RemoteException{
        userList.clear();
        userList.addAll(model.getLibraryUserList());
    }

<<<<<<< Updated upstream
    /**
     * Reset all the JavaFX variables
     */
    public void reset() throws  RemoteException{
        setUserList();
=======
    //reset
    public void reset() {
        try
        {
            setUserList();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
>>>>>>> Stashed changes

        firstNameTextField.set("");
        lastNameTextField.set("");
        ssnTextField.set("");
        passwordTextField.set("");
    }

<<<<<<< Updated upstream
    /**
     * Seat blank the error label
     */
    public void clearErrorLabel() throws RemoteException{
=======
    public void clearErrorLabel() {
>>>>>>> Stashed changes
        errorLabel.set("");
    }

    /**
     * Bind the first name text field
     * @param property
     * A String property
     */
    public void bindFirstNameTextField(StringProperty property){
        property.bindBidirectional(firstNameTextField);
    }

    /**
     * Bind the last name text field
     * @param property
     * A String property
     */
    public void bindLastNameTextField(StringProperty property){
        property.bindBidirectional(lastNameTextField);
    }

    /**
     * Bind the social security number
     * @param property
     * A String property
     */
    public void bindSSNTextField(StringProperty property){
        property.bindBidirectional(ssnTextField);
    }

    /**
     * Bind the password text field
     * @param property
     * A String property
     */
    public void bindPasswordTextField(StringProperty property){
        property.bindBidirectional(passwordTextField);
    }

    /**
     * Bind the library user list
     * @param property
     * Object property, library user observable list property
     */
    public void bindUserListView(ObjectProperty<ObservableList<LibraryUser>> property){
        property.bind(userList);
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
     * Bind the
     * @param property
     */
    public void bindLibraryUserListViewForTest(SimpleListProperty<LibraryUser> property)
    {
        property.bind(userList);
    }

    /**
     * Property change method that call different methods depends on the event name
     * @param evt
     * A library user object passed as an event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("addLibraryUser"))
        {
            userList.add((LibraryUser) evt.getNewValue());
        }
        else if(evt.getPropertyName().equals("removeLibraryUser")){
            for (int i = 0; i < userList.size(); i++) {
                if(userList.get(i).getSSN().equals(evt.getNewValue())){
                    userList.remove(userList.get(i));
                    break;
                }
            }
        }
    }

    /**
     * Check if the social security number format is correct
     * @return
     * True if it is, false if not
     */
    private boolean ssnFormatCheck()
    {
        if (ssnTextField.get().length()==13)
        {
            try
            {
                Long.parseLong(ssnTextField.get());
            }
            catch (NumberFormatException e)
            {
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * Check if the ssn number is unique
     * @return
     * True if it is not, false if it is
     */
    private boolean ssnDuplicateCheck()
    {
        for (int i=0;i<userList.getSize();i++)
        {
            if (userList.get(i).getSSN().equals(ssnTextField.get()))
            {
                return true;
            }
        }
        return false;
    }

}