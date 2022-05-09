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
        property.bindBidirectional(errorLabel);
    }
    public void bindLibrarianListView(ObjectProperty<ObservableList<Librarian>> property){
        property.bindBidirectional(librarianList);
    }

    public void search() {

    }

    public void addLibrarian(Librarian librarian) throws RemoteException, SQLException {
        model.addLibrarian(librarian);
    }

    public void removeLibrarian(int SSN) throws SQLException, RemoteException
    {
        model.removeLibrarian(SSN);
    }

    public void setLibrarianList() throws RemoteException, SQLException{
        for (int i = 0; i < model.getLibrarianList().size(); i++)
        {
            librarianList.add(model.getLibrarianList().get(i));
        }
    }

    public void reset() throws SQLException, RemoteException
    {
        setLibrarianList();

        ssnTextField.set("");
        passwordTextField.set("");
        firstNameTextField.set("");
        lastNameTextField.set("");

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
                if (librarianList.get(i).getSnn() == (int) evt.getNewValue())
                {
                    librarianList.remove(librarianList.get(i));
                    break;
                }
            }
        }
    }
}


