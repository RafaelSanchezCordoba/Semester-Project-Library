package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelLibrarian;
import model.Librarian;
import model.Magazine;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

import java.util.ArrayList;

public class AddRemoveLibrarianViewModel implements PropertyChangeListener {
    private final ModelLibrarian model;
    private final StringProperty firstNameTextField;
    private final StringProperty lastNameTextField;
    private final StringProperty passwordTextField;
    private final StringProperty ssnTextField;
    private final StringProperty errorLabel;
    private final SimpleListProperty<Librarian> librarianList;

    /**
     * AddRemoveLibrarianViewModel constructor that: set the model
     * ,add the property change listener for adding and removing a book
     * and set JavaFX variables
     * @param model
     * The librarian model
     */
    public AddRemoveLibrarianViewModel(ModelLibrarian model){
        this.model = model;
        this.firstNameTextField=new SimpleStringProperty("");
        this.lastNameTextField = new SimpleStringProperty("");
        this.passwordTextField = new SimpleStringProperty("");
        this.ssnTextField = new SimpleStringProperty("");
        this.errorLabel = new SimpleStringProperty("");
        ObservableList<Librarian> observableList = FXCollections.observableList(new ArrayList<>());
        this.librarianList = new SimpleListProperty<>(observableList);

        model.addPropertyChangeListener("newLibrarian", this);
        model.addPropertyChangeListener("removeLibrarian", this);
    }

    /**
     * Bind the First name text field
     * @param property
     * A String property
     */
    public void bindFirstNameTextField(StringProperty property) {
        property.bindBidirectional(firstNameTextField);
    }

    /**
     * Bind the last name text field
     * @param property
     * A String property
     */
    public void bindLastNameTextField(StringProperty property ){
        property.bindBidirectional(lastNameTextField);
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
     * Bind the ssn text field
     * @param property
     * A String property
     */
    public void bindSsnTextField(StringProperty property){
        property.bindBidirectional(ssnTextField);
    }

    /**
     * Bind the error label
     * @param property
     * A String property
     */
    public void bindErrorLabelTextField(StringProperty property){
        property.bind(errorLabel);
    }

    /**
     * Bind the librarian list for testing
     * @param property
     * A librarian simple list property
     */
    public void bindLibrarianListViewForTest(SimpleListProperty<Librarian> property){
        property.bind(librarianList);
    }

    /**
     * Bind the librarian list
     * @param property
     * Object property, librarian observable list property
     */
    public void bindLibrarianListView(
        ObjectProperty<ObservableList<Librarian>> property){
        property.bindBidirectional(librarianList);
    }

    /**
     * Add a Librarian
     * @param librarian
     * Librarian object
     * @throws RemoteException
     */
    public void addLibrarian(Librarian librarian) throws RemoteException {
        if (!errorsCheck())
        {
            model.addLibrarian(librarian);
        }
        try
        {
            reset();
        }
        catch ( RemoteException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Remove a Librarian by the SSN
     * @param SSN
     * The Social Security Number
     * @throws RemoteException
     */
    public void removeLibrarian(String SSN) throws  RemoteException
    {
        model.removeLibrarian(SSN);
    }

    /**
     * Set the librarian list with all the librarians
     * @throws RemoteException
     */
    public void setLibrarianList() throws RemoteException{
        librarianList.clear();
        librarianList.addAll(model.getLibrarianList());
    }

    /**
     * Set as blank all the javaFX variables and set the librarian list
     * @throws RemoteException
     */
    public void reset() throws  RemoteException
    {
        setLibrarianList();

        ssnTextField.set("");
        passwordTextField.set("");
        firstNameTextField.set("");
        lastNameTextField.set("");
    }

    /**
     * Check if the information passed is correct if not print out a specific error message
     * @return
     * True if there are any error, false if not
     */
    private boolean errorsCheck()
    {
        if(firstNameTextField.get().equals(""))
        {
            errorLabel.set("First name can't be null");
            return true;
        }
        else if (ssnTextField.get().equals(""))
        {
            errorLabel.set("Social security number can't be null");
            return true;
        }
        else if (lastNameTextField.get().equals(""))
        {
            errorLabel.set("Last name can't be null");
            return true;
        }
        else if (passwordTextField.get().equals(""))
        {
            errorLabel.set("Password can't be null");
            return true;
        }
        else if (passwordTextField.get().length()>20)
        {
            errorLabel.set("Password must be less than 20 characters");
            return true;
        }
        else if (firstNameTextField.get().length()>50)
        {
            errorLabel.set("First name must be less than 50 characters");
            return true;
        }
        else if (lastNameTextField.get().length()>50)
        {
            errorLabel.set("Last name must be less than 50 characters");
            return true;
        }
        else if(ssnFormatCheck())
        {
            errorLabel.set("The ssn must be 13 digits");
            return true;
        }
        else if (ssnDuplicateCheck())
        {
            errorLabel.set("There is already a librarian with that ssn in the system");
            return true;
        }
        return false;
    }

    /**
     * Check if the ssn format is correct
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
        for (int i=0;i<librarianList.getSize();i++)
        {
            if (librarianList.get(i).getSsn().equals(ssnTextField.get()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Property change method that call different methods depends on the event name
     * @param evt
     * A librarian object passed as an event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("newLibrarian"))
        {
            librarianList.add((Librarian) evt.getNewValue());
        }
        else if (evt.getPropertyName().equals("removeLibrarian"))
        {
            for (int i = 0; i < librarianList.size(); i++)
            {
                if (librarianList.get(i).getSsn().equals(evt.getNewValue()))
                {
                    librarianList.remove(librarianList.get(i));
                    break;
                }
            }
        }
    }
}


