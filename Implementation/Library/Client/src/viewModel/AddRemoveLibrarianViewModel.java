package viewModel;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelLibrarian;
import model.Librarian;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListenerProxy;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class AddRemoveLibrarianViewModel implements PropertyChangeListener
{

    private final ModelLibrarian model;

    private final StringProperty firstNameTextField;
    private final StringProperty lastNameTextField;
    private final StringProperty passwordTextField;
    private final StringProperty ssnTextField;
    private final StringProperty errorLabel;
    private final ObservableList<Librarian> options;

    public AddRemoveLibrarianViewModel(ModelLibrarian model){

        this.model = model;
        this.firstNameTextField=new SimpleStringProperty("");
        this.lastNameTextField = new SimpleStringProperty("");
        this.passwordTextField = new SimpleStringProperty("");
        this.ssnTextField = new SimpleStringProperty("");
        this.errorLabel = new SimpleStringProperty("");
        this.options = FXCollections.observableArrayList();
        try
        {
            options.addAll(model.getLibrarianList());
        }catch (RemoteException e){
            e.printStackTrace();
            errorLabel.set("unable to access librarian List");
        }
        model.addPropertyChangeListener(this);

    }

        public void removeLibrarian(Librarian librarian){
        try
        {
            options.remove(librarian);
            model.removeLibrarian(librarian);
        }catch (RemoteException e){
            e.printStackTrace();
        }

        }
    public ObservableList<Librarian> getOptions(){
        return options;
    }

    public Librarian getInstance(){
        return new Librarian(firstNameTextField.getValue(),
            lastNameTextField.getValue(), passwordTextField.getValue(),
            ssnTextField.getValue());
    }

    public void addLibrarian(){
        try
        {
            Librarian librarian=new Librarian(ssnTextField.getValue(), passwordTextField.getValue(),firstNameTextField.getValue(),
                lastNameTextField.getValue());
            options.add(librarian);
            model.addLibrarian(librarian);

        }catch (RemoteException e){
            e.printStackTrace();
            errorLabel.set("unable to add Librarian");
        }

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
        property.bindBidirectional(errorLabel);
    }

    public void reset()
    {
        firstNameTextField.set("");
        lastNameTextField.set("");
        ssnTextField.set("");
        passwordTextField.set("");
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        reset();
    }
}


