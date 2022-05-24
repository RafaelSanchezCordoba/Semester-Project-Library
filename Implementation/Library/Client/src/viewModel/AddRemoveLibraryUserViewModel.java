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
import java.sql.SQLException;
import java.util.ArrayList;

public class AddRemoveLibraryUserViewModel implements PropertyChangeListener {

    private final ModelLibraryUser model;
    private final StringProperty firstNameTextField;
    private final StringProperty lastNameTextField;
    private final StringProperty ssnTextField;
    private final StringProperty passwordTextField;
    private final SimpleListProperty<LibraryUser> userList;
    private final StringProperty errorLabel;

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

    public void search(){}

    //error check - LABEL
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
        }else if(passwordTextField.get().length() > 36){
            errorLabel.set("Password can't be longer than 36 characters");
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


    public void addLibraryUser(LibraryUser libraryUser) throws RemoteException, SQLException{
        if(!errorCheck()){
            System.out.println(libraryUser.getFirstName());
            model.addLibraryUser(libraryUser);

        }
        reset();
    }

    public void removeLibraryUser(String ssn) throws RemoteException, SQLException{
        model.removeLibraryUser(ssn);
    }

    public void setUserList() throws SQLException, RemoteException{
        userList.clear();
        userList.addAll(model.getLibraryUserList());
    }

    //reset
    public void reset() throws SQLException, RemoteException{
        setUserList();

        firstNameTextField.set("");
        lastNameTextField.set("");
        ssnTextField.set("");
        passwordTextField.set("");
    }

    public void bindFirstNameTextField(StringProperty property){
        property.bindBidirectional(firstNameTextField);
    }
    public void bindLastNameTextField(StringProperty property){
        property.bindBidirectional(lastNameTextField);
    }
    public void bindSSNTextField(StringProperty property){
        property.bindBidirectional(ssnTextField);
    }
    public void bindPasswordTextField(StringProperty property){
        property.bindBidirectional(passwordTextField);
    }

    public void bindUserListView(ObjectProperty<ObservableList<LibraryUser>> property){
        property.bind(userList);
    }
    public void bindErrorLabel(StringProperty property){
        property.bind(errorLabel);
    }

    public void bindLibraryUserListViewForTest(SimpleListProperty<LibraryUser> property)
    {
        property.bind(userList);
    }

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