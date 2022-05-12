package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianTest {
  private Librarian librarian;

  @BeforeEach
  void setUp() {
  }

  @Test
  void create_new_librarian_with_all_corrects_parameters() {
    librarian = new Librarian("1234567890", "password", "Pepito", "Perez");
    //using toString to check that the librarian is created correctly
    assertEquals("Librarian{ssn:'1234567890', first Name:'Pepito', last Name:'Perez'}", librarian.toString());
  }

  //null values

  @Test
  void null_password() {
    librarian = new Librarian("1234567890", null, "Pepito", "Perez");
    assertEquals("Librarian{ssn:'1234567890', first Name:'Pepito', last Name:'Perez'}", librarian.toString());
  }

  @Test
  void null_first_name() {
    librarian = new Librarian("1234567890", "password", null, "Perez");
    assertEquals("Librarian{ssn:'1234567890', first Name:'null', last Name:'Perez'}", librarian.toString());
  }

  @Test
  void null_last_name() {
    librarian = new Librarian("1234567890", "password", "Pepito", null);
    assertEquals("Librarian{ssn:'1234567890', first Name:'Pepito', last Name:'null'}", librarian.toString());
  }
  //get methods

  @Test
  void get_ssn_returns_ssn() {
    librarian = new Librarian("1234567890", "password", "Pepito", "Perez");
    assertEquals("1234567890", librarian.getSsn());
  }

  @Test
  void get_password_returns_password() {
    librarian = new Librarian("1234567890", "password", "Pepito", "Perez");
    assertEquals("password", librarian.getPassword());
  }

  @Test
  void get_first_name_returns_first_name() {
    librarian = new Librarian("1234567890", "password", "Pepito", "Perez");
    assertEquals("Pepito", librarian.getFirstName());
  }


  @Test
  void get_last_name_returns_last_name() {
    librarian = new Librarian("1234567890", "password", "Pepito", "Perez");
    assertEquals("Perez", librarian.getLastName());
  }

  @Test
  void get_date_of_employment_returns_the_date() {
    assertEquals(new CurrentTime().getFormattedIsoDate(), new Librarian("1234567890", "password", "Pepito", "Perez").getDateOfEmployment());
  }

  @Test
  void set_date_of_employment_sets_the_date() {
    librarian = new Librarian("1234567890", "password", "Pepito", "Perez");
    Date date = new Date(2022, 5, 7);
    librarian.setDate(date);
    assertEquals(date.toString(), librarian.getDateOfEmployment());
  }


  @Test
  void set_date_of_employment_to_a_future_date_sets_the_date_normally() {
    librarian = new Librarian("1234567890", "password", "Pepito", "Perez");
    Date date = new Date(2023, 5, 7);
    librarian.setDate(date);
    assertEquals(date.toString(), librarian.getDateOfEmployment());
  }
}
