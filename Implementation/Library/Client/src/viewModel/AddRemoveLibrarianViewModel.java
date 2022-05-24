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
import java.sql.SQLException;
import java.util.ArrayList;

public class AddRemoveLibrarianViewModel implements PropertyChangeListener {
    private final ModelLibrarian model;
    private final StringProperty firstNameTextField;
    private final StringProperty lastNameTextField;
    private final StringProperty passwordTextField;
    private final StringProperty ssnTextField;
    private final StringProperty errorLabel;
    private final SimpleListProperty<Librarian> librarianList;

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

    public void bindFirstNameTextField(StringProperty property) {
        property.bindBidirectional(firstNameTextField);
    }
    public void bindLastNameTextField(StringProperty property ){
        property.bindBidirectional(lastNameTextField);
    }
    public void bindPasswordTextField(StringProperty property){
        property.bindBidirectional(passwordTextField);
    }
    public void bindSsnTextField(StringProperty property){
        property.bindBidirectional(ssnTextField);
    }
    public void bindErrorLabelTextField(StringProperty property){
        property.bind(errorLabel);
    }
    public void bindLibrarianListViewForTest(SimpleListProperty<Librarian> property){
        property.bind(librarianList);
    }
    public void bindLibrarianListView(
        ObjectProperty<ObservableList<Librarian>> property){
        property.bindBidirectional(librarianList);
    }


    public void addLibrarian(Librarian librarian) throws RemoteException, SQLException {
        if (!errorsCheck())
        {
            model.addLibrarian(librarian);
        }
        try
        {
            reset();
        }
        catch (SQLException | RemoteException e)
        {
            e.printStackTrace();
        }
    }

    public void removeLibrarian(String SSN) throws SQLException, RemoteException
    {
        model.removeLibrarian(SSN);
    }

    public void setLibrarianList() throws RemoteException, SQLException{
        librarianList.clear();
        librarianList.addAll(model.getLibrarianList());
    }

    public void reset() throws SQLException, RemoteException
    {
        setLibrarianList();

        ssnTextField.set("");
        passwordTextField.set("");
        firstNameTextField.set("");
        lastNameTextField.set("");

    }


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


