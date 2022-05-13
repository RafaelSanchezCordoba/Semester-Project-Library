package viewModel;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.FakeModelManagerLibraryUser;
import mediator.ModelLibraryUser;
import model.LibraryUser;

import model.Magazine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AddRemoveLibraryUserViewModelTest {
    private AddRemoveLibraryUserViewModel viewModel;
    private ModelLibraryUser model;
    private StringProperty firstNameTextField;
    private StringProperty lastNameTextField;
    private StringProperty ssnTextField;
    private StringProperty passwordTextField;
    private StringProperty searchTextField;
    private SimpleListProperty<LibraryUser> userList;
    private StringProperty errorLabel;

    @BeforeEach
    public void setUp() throws RemoteException {
        model = new FakeModelManagerLibraryUser();
        viewModel = new AddRemoveLibraryUserViewModel(model);
        this.firstNameTextField = new SimpleStringProperty("");
        this.lastNameTextField = new SimpleStringProperty("");
        this.ssnTextField = new SimpleStringProperty("");
        this.passwordTextField = new SimpleStringProperty("");
        this.searchTextField = new SimpleStringProperty("");
        this.errorLabel = new SimpleStringProperty("");
        ObservableList<LibraryUser> observableList = FXCollections.observableArrayList(new ArrayList<>());
        this.userList = new SimpleListProperty<>(observableList);

        viewModel.bindErrorLabel(errorLabel);
        viewModel.bindSearchTextField(searchTextField);
        viewModel.bindFirstNameTextField(firstNameTextField);
        viewModel.bindLastNameTextField(lastNameTextField);
        viewModel.bindSSNTextField(ssnTextField);
        viewModel.bindPasswordTextField(passwordTextField);
        viewModel.bindLibraryUserListViewForTest(userList);
    }

    @Test
    public void new_object_is_blank() {
        assertEquals("", errorLabel.get());
        assertEquals("", searchTextField.get());
        assertEquals("", firstNameTextField.get());
        assertEquals("", lastNameTextField.get());
        assertEquals("", passwordTextField.get());
        assertEquals("", ssnTextField.get());
        assertEquals("[]", userList.getValue().toString());
    }

    @Test
    public void setting_the_labels_doesnt_change_list_or_error() {
        firstNameTextField.set("Rafa");
        lastNameTextField.set("Sanchez");
        passwordTextField.set("1234");
        ssnTextField.set("1234567890123");
        assertEquals("", errorLabel.get());
        assertEquals("[]", userList.get().toString());
    }

    @Test void add_adds_the_library_user() throws SQLException, RemoteException {
        firstNameTextField.set("Rafa");
        lastNameTextField.set("Sanchez");
        passwordTextField.set("1234");
        ssnTextField.set("1234567890123");
        viewModel.addLibraryUser(new LibraryUser(ssnTextField.getValue(), firstNameTextField.getValue(), lastNameTextField.getValue(), passwordTextField.getValue()));
        assertEquals("[LibraryUser{ssn=1234567890123, firstName=Rafa, lastName=Sanchez, password=1234}]", userList.get().toString());
    }

    @Test void add_two_magazines() throws SQLException, RemoteException {
        firstNameTextField.set("Rafa");
        lastNameTextField.set("Sanchez");
        passwordTextField.set("1234");
        ssnTextField.set("1234567890123");
        viewModel.addLibraryUser(new LibraryUser(ssnTextField.getValue(), firstNameTextField.getValue(), lastNameTextField.getValue(), passwordTextField.getValue()));
        firstNameTextField.set("Rosa");
        lastNameTextField.set("Briales");
        passwordTextField.set("1111");
        ssnTextField.set("1234567890111");
        viewModel.addLibraryUser(new LibraryUser(ssnTextField.getValue(), firstNameTextField.getValue(), lastNameTextField.getValue(), passwordTextField.getValue()));
        assertEquals("[LibraryUser{ssn=1234567890123, firstName=Rafa, lastName=Sanchez, password=1234}, LibraryUser{ssn=1234567890111, firstName=Rosa, lastName=Briales, password=1111}]", userList.get().toString());
    }



}
