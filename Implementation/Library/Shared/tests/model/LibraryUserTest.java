package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryUserTest {
    private LibraryUser libraryUser;

    //Create libraryUser
    @Test
    public void create_new_libraryUser_with_all_the_correct_parameters() {
        libraryUser = new LibraryUser("1234567890", "Rafa", "Sanchez", "1234");
        assertEquals("LibraryUser{SSN: 1234567890, Last Name: Sanchez, First Name: Rafa}", libraryUser.toString());
    }

    //Create libraryUser with null values
    @Test
    public void create_new_LibraryUser_with_all_null_parameters() {
        libraryUser = new LibraryUser(null, null, null, null);
        assertEquals("LibraryUser{SSN: null, Last Name: null, First Name: null}", libraryUser.toString());
    }


    @Test
    public void create_new_libraryUser_with_null_password() {
        libraryUser = new LibraryUser("1234567890", "Rafa", "Sanchez", null);
        assertEquals("LibraryUser{SSN: 1234567890, Last Name: Sanchez, First Name: Rafa}", libraryUser.toString());
    }

    @Test
    public void create_new_libraryUser_with_null_first_name() {
        libraryUser = new LibraryUser("1234567890", null, "Sanchez", "1234");
        assertEquals("LibraryUser{SSN: 1234567890, Last Name: Sanchez, First Name: null}", libraryUser.toString());
    }

    @Test
    public void create_new_libraryUser_with_null_last_name() {
        libraryUser = new LibraryUser("1234567890", "Rafa", null, "1234");
        assertEquals("LibraryUser{SSN: 1234567890, Last Name: null, First Name: Rafa}", libraryUser.toString());
    }

    //Get methods
    @Test
    public void get_ssn_returns_ssn() {
        libraryUser = new LibraryUser("1234567890", "Rafa", "Sanchez", "1234");
        assertEquals("1234567890", libraryUser.getSSN());
    }

    @Test
    public void get_password_returns_password() {
        libraryUser = new LibraryUser("1234567890", "Rafa", "Sanchez", "1234");
        assertEquals("1234", libraryUser.getPassword());
    }

    @Test
    public void get_first_name_returns_first_name() {
        libraryUser = new LibraryUser("1234567890", "Rafa", "Sanchez", "1234");
        assertEquals("Rafa", libraryUser.getFirstName());
    }

    @Test
    public void get_last_name_returns_last_name() {
        libraryUser = new LibraryUser("1234567890", "Rafa", "Sanchez", "1234");
        assertEquals("Sanchez", libraryUser.getLastName());
    }

}
