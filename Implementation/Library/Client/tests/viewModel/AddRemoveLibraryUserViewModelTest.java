package viewModel;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelLibraryUser;
import model.LibraryUser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

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
        this.errorLabel = new SimpleStringProperty("");
        ObservableList<LibraryUser> observableList = FXCollections.observableArrayList(new ArrayList<>());
        this.userList = new SimpleListProperty<>(observableList);

        viewModel.bindErrorLabel(errorLabel);
         viewModel.bindFirstNameTextField(firstNameTextField);
        viewModel.bindLastNameTextField(lastNameTextField);
        viewModel.bindSSNTextField(ssnTextField);
        viewModel.bindPasswordTextField(passwordTextField);
        viewModel.bindLibraryUserListViewForTest(userList);
    }

    @Test
    public void new_object_is_blank() {
        assertEquals("", errorLabel.get());
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
        viewModel.addLibraryUser();
        assertEquals("[LibraryUser{ssn=1234567890123, firstName=Rafa, lastName=Sanchez, password=1234}]", userList.get().toString());
    }

    @Test void add_two_library_users() throws SQLException, RemoteException {
        firstNameTextField.set("Rafa");
        lastNameTextField.set("Sanchez");
        passwordTextField.set("1234");
        ssnTextField.set("1234567890123");
        viewModel.addLibraryUser(); firstNameTextField.set("Rosa");
        lastNameTextField.set("Briales");
        passwordTextField.set("1111");
        ssnTextField.set("1234567890111");
        viewModel.addLibraryUser();assertEquals("[LibraryUser{ssn=1234567890123, firstName=Rafa, lastName=Sanchez, password=1234}, LibraryUser{ssn=1234567890111, firstName=Rosa, lastName=Briales, password=1111}]", userList.get().toString());
    }

    @Test
    public void adding_clear_fields() throws SQLException, RemoteException {
        firstNameTextField.set("Rafa");
        lastNameTextField.set("Sanchez");
        passwordTextField.set("1234");
        ssnTextField.set("1234567890123");
        viewModel.addLibraryUser();assertEquals("", firstNameTextField.get());
        assertEquals("", lastNameTextField.get());
        assertEquals("", passwordTextField.get());
        assertEquals("", ssnTextField.get());
    }

    //Errors
    @Test
    public void null_first_name_gives_error_and_does_not_add() throws SQLException, RemoteException {
        lastNameTextField.set("Sanchez");
        passwordTextField.set("1234");
        ssnTextField.set("1234567890123");
        viewModel.addLibraryUser();
        assertEquals("First name can't be null", errorLabel.get());
        assertEquals("[]", userList.get().toString());
    }

    @Test
    public void null_last_name_gives_error_and_does_not_add() throws SQLException, RemoteException {
        firstNameTextField.set("Rafa");
        passwordTextField.set("1234");
        ssnTextField.set("1234567890123");
        viewModel.addLibraryUser();
        assertEquals("Last name can't be null", errorLabel.get());
        assertEquals("[]", userList.get().toString());
    }

    @Test
    public void null_password_gives_error_and_does_not_add() throws SQLException, RemoteException {
        firstNameTextField.set("Rafa");
        lastNameTextField.set("Sanchez");
        ssnTextField.set("1234567890123");
        viewModel.addLibraryUser();
        assertEquals("Password can't be null", errorLabel.get());
        assertEquals("[]", userList.get().toString());
    }

    @Test
    public void null_ssn_gives_error_and_does_not_add() throws SQLException, RemoteException {
        firstNameTextField.set("Rafa");
        lastNameTextField.set("Sanchez");
        passwordTextField.set("1234");
        viewModel.addLibraryUser();
        assertEquals("SSN can't be null", errorLabel.get());
        assertEquals("[]", userList.get().toString());
    }

    @Test
    public void first_name_51_character_gives_error_and_does_not_add() throws SQLException, RemoteException {
        firstNameTextField.set("fffffffffffffffffffffffffffffffffffffffffffffffffff");
        lastNameTextField.set("Sanchez");
        passwordTextField.set("1234");
        ssnTextField.set("1234567890123");
        viewModel.addLibraryUser();
        assertEquals("First name should be less than 50 characters", errorLabel.get());
        assertEquals("[]", userList.get().toString());
    }

    @Test
    public void last_name_51_character_gives_error_and_does_not_add() throws SQLException, RemoteException {
        firstNameTextField.set("Rafa");
        lastNameTextField.set("fffffffffffffffffffffffffffffffffffffffffffffffffff");
        passwordTextField.set("1234");
        ssnTextField.set("1234567890123");
        viewModel.addLibraryUser();
        assertEquals("Last name should be less than 50 characters", errorLabel.get());
        assertEquals("[]", userList.get().toString());
    }

    @Test
    public void password_21_character_gives_error_and_does_not_add() throws SQLException, RemoteException {
        firstNameTextField.set("Rafa");
        lastNameTextField.set("Sanchez");
        passwordTextField.set("fffffffffffffffffffff");
        ssnTextField.set("1234567890123");
        viewModel.addLibraryUser();
        assertEquals("Password can't be longer than 20 characters", errorLabel.get());
        assertEquals("[]", userList.get().toString());
    }

    @Test
    public void ssn_no_13_character_gives_error_and_does_not_add() throws SQLException, RemoteException {
        firstNameTextField.set("Rafa");
        lastNameTextField.set("Sanchez");
        passwordTextField.set("1234");
        ssnTextField.set("ffffffffffffff");
        viewModel.addLibraryUser();
        assertEquals("ssn should be 13 characters", errorLabel.get());
        assertEquals("[]", userList.get().toString());
    }

    @Test
    public void errors_clear_fields() throws SQLException, RemoteException {
        firstNameTextField.set("fffffffffffffffffffffffffffffffffffffffffffffffffff");
        lastNameTextField.set("Sanchez");
        passwordTextField.set("1234");
        ssnTextField.set("1234567890123");
        viewModel.addLibraryUser();
        assertEquals("", firstNameTextField.get());
        assertEquals("", lastNameTextField.get());
        assertEquals("", passwordTextField.get());
        assertEquals("", ssnTextField.get());
    }

    @Test
    public void correctly_adding_clear_errors() throws SQLException, RemoteException {
        firstNameTextField.set("fffffffffffffffffffffffffffffffffffffffffffffffffff");
        lastNameTextField.set("Sanchez");
        passwordTextField.set("1234");
        ssnTextField.set("1234567890123");
        viewModel.addLibraryUser();
        assertEquals("First name should be less than 50 characters", errorLabel.get());
        firstNameTextField.set("Rafa");
        lastNameTextField.set("Sanchez");
        passwordTextField.set("1234");
        ssnTextField.set("1234567890123");
        viewModel.addLibraryUser();
        assertEquals("", errorLabel.get());
    }

    @Test
    public void error_cannot_be_set_outside_viewmodel() {
        assertThrows(RuntimeException.class, () -> errorLabel.set("Error"));
    }


}
