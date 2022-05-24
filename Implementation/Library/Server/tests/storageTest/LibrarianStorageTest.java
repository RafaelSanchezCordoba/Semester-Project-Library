package storageTest;

import model.Librarian;
import server.storage.LibrarianStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The Librarian storage test implemented as a singleton.
 * @author Rafael Sánchez Córdoba.
 * @version 1.2 09/05/22
 */
public class LibrarianStorageTest implements LibrarianStorage {
    private ArrayList<Librarian> librarians;
    private static LibrarianStorageTest instance;

    /**
     * Private constructor that create an array list of librarians
     */
    private LibrarianStorageTest() {
        librarians = new ArrayList<>();
    }

    /**
     * Get Instance method
     * @return
     * The librarian storage test instance
     */
    public static synchronized LibrarianStorageTest getInstance() {
        if(instance==null){
            instance=  new LibrarianStorageTest();
        }
        return instance;
    }

    /**
     * Add a Librarian
     * @param librarian
     * Librarian object
     * @throws RemoteException
     */
    @Override
    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
        System.out.println("Librarian added");
    }

    /**
     * Remove a Librarian by the SSN
     * @param SSN
     * The Social Security Number
     * @throws RemoteException
     */
    @Override
    public void removeLibrarian(String SSN) {
        for (int i = 0; i < librarians.size(); i++) {
            if (librarians.get(i).getSsn() == SSN) {
                librarians.remove(librarians.get(i));
                break;
            }
        }
    }

    /**
     * Return a list with all the librarians added
     * @return
     * Librarian List
     * @throws RemoteException
     * @throws SQLException
     */
    @Override
    public ArrayList<Librarian> getLibrarianList() {
        return librarians;
    }
}
