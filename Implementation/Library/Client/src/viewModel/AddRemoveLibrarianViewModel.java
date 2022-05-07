package viewModel;

import com.sun.javafx.collections.ObservableListWrapper;
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

public class AddRemoveLibrarianViewModel implements PropertyChangeListener
{

    private final ModelLibrarian model;

    private final StringProperty firstNameTextField;
    private final StringProperty lastNameTextField;
    private final StringProperty passwordTextField;
    private final StringProperty ssnTextField;
    private final StringProperty errorLabel;
    private final ObservableList options ;
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
    }

    public ObservableList getOptions(){
        return options;
    }

    public void removeLibrarian(Librarian librarian){
        try
        {
            model.removeLibrarian(librarian);
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    public void addLibrarian(){
        try
        {

            model.addLibrarian(new Librarian(firstNameTextField.getValue(),
                    lastNameTextField.getValue(), passwordTextField.getValue(),
                    ssnTextField.getValue()));

        }catch (RemoteException e){
            e.printStackTrace();
            errorLabel.set("unable to add Librarian");
        }

    }

    public Librarian getInstance(){
        return new Librarian(firstNameTextField.getValue(),
                lastNameTextField.getValue(), passwordTextField.getValue(),
                ssnTextField.getValue());
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

    @Override public void propertyChange(PropertyChangeEvent evt)
    {

    }
}


