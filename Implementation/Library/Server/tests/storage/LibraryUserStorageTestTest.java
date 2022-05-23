package storage;

import model.LibraryUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistance.storageTest.LibraryUserStorageTest;

import java.rmi.RemoteException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryUserStorageTestTest {
    private static LibraryUserStorageTest storage;
    private static LibraryUser libraryUser, libraryUser2, libraryUser3;

    @BeforeAll
    public static void setUpVariables() {
        libraryUser = new LibraryUser("1231231230111", "Rafa", "Sanchez", "1111");
        libraryUser2 = new LibraryUser("2342342340111", "Rosa", "Briales", "2222");
        libraryUser3 = new LibraryUser("3453453450111", "Maria", "Planchuelo", "3333");
    }

    @BeforeAll
    public static void setUpStorage() {
        storage = LibraryUserStorageTest.getInstance();
    }

    //Zero
    @Test
    public void new_storage_is_empty() throws SQLException, RemoteException {
        storage.clear();
        assertTrue(storage.getLibraryUserList().isEmpty());
    }

    //One
    @Test
    public void storage_has_size_1_after_adding_library_user() throws SQLException, RemoteException {
        storage.clear();
        storage.addLibraryUser(libraryUser);
        assertEquals(1, storage.getLibraryUserList().size());
    }

    @Test
    public void adding_an_element_to_empty_storage_put_it_in_0() throws SQLException, RemoteException {
        storage.clear();
        storage.addLibraryUser(libraryUser);
        assertEquals(libraryUser, storage.getLibraryUserList().get(0));
    }

    @Test
    public void storage_has_size_0_after_removing_library_user() throws SQLException, RemoteException {
        storage.clear();
        storage.addLibraryUser(libraryUser);
        storage.removeLibraryUser(libraryUser.getSSN());
        assertEquals(0, storage.getLibraryUserList().size());
    }

    //Many
    @Test
    public void adding_a_second_library_user_gives_size_2() throws SQLException, RemoteException {
        storage.clear();
        storage.addLibraryUser(libraryUser);
        storage.addLibraryUser(libraryUser2);
        assertEquals(2, storage.getLibraryUserList().size());
    }

    @Test
    public void adding_a_second_library_user_puts_it_at_1() throws SQLException, RemoteException {
        storage.clear();
        storage.addLibraryUser(libraryUser);
        storage.addLibraryUser(libraryUser2);
        assertEquals(libraryUser2, storage.getLibraryUserList().get(1));
    }

    @Test
    public void adding_multiple_library_users_no_add_the_same_library_user_twice() throws SQLException, RemoteException {
        storage.addLibraryUser(libraryUser);
        storage.addLibraryUser(libraryUser2);

        assertNotEquals(libraryUser, storage.getLibraryUserList().get(0).equals(storage.getLibraryUserList().get(1)));
    }

    @Test
    public void storage_has_size_1_after_removing_library_users() throws SQLException, RemoteException {
        storage.clear();
        storage.addLibraryUser(libraryUser);
        storage.addLibraryUser(libraryUser2);
        storage.addLibraryUser(libraryUser3);
        storage.removeLibraryUser(libraryUser2.getSSN());
        storage.removeLibraryUser(libraryUser3.getSSN());
        assertEquals(1, storage.getLibraryUserList().size());
    }

    @Test
    public void removing_first_library_user_puts_the_second_in_position_0() throws SQLException, RemoteException {
        storage.clear();
        storage.addLibraryUser(libraryUser);
        storage.addLibraryUser(libraryUser2);
        storage.removeLibraryUser(libraryUser.getSSN());
        assertEquals(libraryUser2, storage.getLibraryUserList().get(0));
    }

    //Boundary
    @Test
    public void index_size_minus_2_gives_last_library_user() throws SQLException, RemoteException {
        storage.addLibraryUser(libraryUser);
        storage.addLibraryUser(libraryUser2);
        assertEquals(libraryUser, storage.getLibraryUserList().get(storage.getLibraryUserList().size() - 2));
    }

    //Exceptions
    @Test
    public void negative_indices_throws_IndexOutOfBounds() throws SQLException, RemoteException {
        storage.addLibraryUser(libraryUser);
        assertThrows(IndexOutOfBoundsException.class, () -> storage.getLibraryUserList().get(-1));
    }

    @Test
    public void index_equal_to_size_throws_IndexOutOfBounds() throws SQLException, RemoteException {
        storage.addLibraryUser(libraryUser);
        storage.addLibraryUser(libraryUser2);
        assertThrows(IndexOutOfBoundsException.class, () -> storage.getLibraryUserList().get(storage.getLibraryUserList().size()));
    }

    @Test
    public void get_on_empty_collection_throws_IndexOutOfBounds() {
        storage.clear();
        assertThrows(IndexOutOfBoundsException.class, () -> storage.getLibraryUserList().get(0));
    }
}
