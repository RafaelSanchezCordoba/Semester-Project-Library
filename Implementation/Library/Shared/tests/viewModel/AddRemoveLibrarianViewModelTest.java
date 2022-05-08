package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.FakeLibrarianModel;
import mediator.ModelLibrarian;
import model.LibrarianList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddRemoveLibrarianViewModelTest
{
  private AddRemoveLibrarianViewModel viewModel;
  private StringProperty firstName;
  private StringProperty lastName;
  private StringProperty password;
  private StringProperty ssn;
  private StringProperty error;
  private ObservableList options ;


  @BeforeEach void setUp()
  {
    ModelLibrarian fakeModel=new FakeLibrarianModel(new LibrarianList());
    viewModel=new AddRemoveLibrarianViewModel(fakeModel);
    firstName=new SimpleStringProperty();
    lastName=new SimpleStringProperty();
    password=new SimpleStringProperty();
    ssn=new SimpleStringProperty();
    error=new SimpleStringProperty();
    this.options = FXCollections.observableArrayList();
    viewModel.bindErrorLabelTextField(error);
    viewModel.bindFirstNameTextField(firstName);
    viewModel.bindLastNameTextField(lastName);
    viewModel.bindPasswordTextField(password);
    viewModel.bindSsnTextField(ssn);
  }

  // Zero - testing a new object.
  @Test
  public void a_new_view_model_is_blank() {
    assertEquals("", firstName.get());
    assertEquals("", lastName.get());
    assertEquals("", password.get());
    assertEquals("", ssn.get());
    assertEquals("", error.get());
    assertEquals("[]",options.toString());
  }

  @Test
  public void setting_the_labels_doesnt_change_list_or_error() {
    firstName.setValue("Rosa");
    lastName.setValue("Briales");
    password.setValue("password");
    ssn.setValue("1234567890");
    assertEquals("", error.get());
    assertEquals("[]",options.toString());
  }

  @Test
  public void add_adds_the_librarian() {
    firstName.set("Rosa");
    lastName.set("Briales");
    password.set("password");
    ssn.set("1234567890");
    viewModel.addLibrarian();
    assertEquals("", error.get());
    assertEquals("[Librarian{ssn:'1234567890', first Name:'Rosa', last Name:'Briales'}]",options.toString());
  }

  @Test
  public void remove_removes_the_librarian()
{
  firstName.set("Rosa");
  lastName.set("Briales");
  password.set("password");
  ssn.set("1234567890");
  viewModel.addLibrarian();
  firstName.set("Rafa");
  lastName.set("Sanchez");
  password.set("password");
  ssn.set("22222222");
  viewModel.addLibrarian();
  firstName.set("Maria");
  lastName.set("Ortiz");
  password.set("password");
  ssn.set("333333333");
  viewModel.addLibrarian();
  viewModel.removeLibrarian(viewModel.getOptions().get(1));
  assertEquals("[Librarian{ssn:'1234567890', first Name:'Rosa', last Name:'Briales'},Librarian{ssn:'333333333', first Name:'Maria', last Name:'Ortiz'}]",options.toString());
  }

@Test
  void null_first_name_gives_error()
{
  lastName.set("Briales");
  password.set("password");
  ssn.set("1234567890");
  viewModel.addLibrarian();
  assertEquals("First name can't be null", error.get());
  assertEquals("[]",options.toString());
}

@Test void null_last_name_gives_error()
{
  firstName.set("Rosa");
  password.set("password");
  ssn.set("1234567890");
  viewModel.addLibrarian();
  assertEquals("Last name can't be null", error.get());
  assertEquals("[]",options.toString());
}

  @Test void null_ssn_gives_error()
  {
    firstName.set("Rosa");
    lastName.set("Briales");
    password.set("password");
    viewModel.addLibrarian();
    assertEquals("Social security number can't be null", error.get());
    assertEquals("[]",options.toString());
  }

  @Test void null_password_gives_error()
  {
    firstName.set("Rosa");
    lastName.set("Briales");
    ssn.set("1234567890");
    viewModel.addLibrarian();
    assertEquals("Password can't be null", error.get());
    assertEquals("[]",options.toString());
  }

  @Test void password_too_long_gives_error()
  {
    firstName.set("Rosa");
    lastName.set("Briales");
    password.set("passwordddddddddddddd");
    ssn.set("1234567890");
    viewModel.addLibrarian();
    assertEquals("Password must be less than 20 characters", error.get());
    assertEquals("[]",options.toString());
  }

  @Test void first_name_too_long_gives_error()
  {
    firstName.set("Rosaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    lastName.set("Briales");
    password.set("password");
    ssn.set("1234567890");
    viewModel.addLibrarian();
    assertEquals("First name must be less than 50 characters", error.get());
    assertEquals("[]",options.toString());
  }

  @Test void last_name_too_long_gives_error()
  {
    firstName.set("Rosa");
    lastName.set("Brialessssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
    password.set("password");
    ssn.set("1234567890");
    viewModel.addLibrarian();
    assertEquals("Last name must be less than 50 characters", error.get());
    assertEquals("[]",options.toString());
  }


  @Test void adding_a_librarian_with_ssn_already_in_the_list_gives_error()
  {
    firstName.set("Rosa");
    lastName.set("Briales");
    password.set("password");
    ssn.set("1234567890");
    viewModel.addLibrarian();
    ssn.set("1234567890");
    firstName.set("Rafa");
    lastName.set("Sanchez");
    password.set("password");
    viewModel.addLibrarian();
    assertEquals("There is already a librarian with that ssn in the system", error.get());
    assertEquals("[Librarian{ssn:'1234567890', first Name:'Rosa', last Name:'Briales'}]",options.toString());
  }

  @Test void errors_clear_fields()
  {
    firstName.set("Rosa");
    lastName.set("Briales");
    ssn.set("1234567890");
    viewModel.addLibrarian();
    assertEquals("", firstName.get());
    assertEquals("", lastName.get());
    assertEquals("", password.get());
    assertEquals("", ssn.get());
    assertEquals("Password can't be null", error.get());
    assertEquals("[]",options.toString());
  }

  public void successful_add_clears_errors() {
    firstName.set("Rosa");
    lastName.set("Briales");
    ssn.set("1234567890");
    password.set("password");
    viewModel.addLibrarian();
    assertEquals("", error.get());
  }

  @Test
  public void error_cannot_be_set_outside_viewmodel() {
    assertThrows(RuntimeException.class, () -> error.set("Error"));
  }



}
