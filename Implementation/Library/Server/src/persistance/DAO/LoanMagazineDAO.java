package persistance.DAO;

import model.LibraryUser;
import model.LoanMagazine;
import model.Magazine;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Public LoanMagazineDAO interface that is implemented by
 * LoanMagazineDAOImplementation class
 * The purpose of this class is to provide abstract
 * interface with all method for class AdapterLoanMagazineDAO
 */
public interface LoanMagazineDAO {
    /**
     * Method adding a magazine to loan
     * @param loanMagazine
     * MagazineBook
     * @throws SQLException
     * SQL exception with the message "No keys generated" can be caught
     * from LoanMagazineDAOImplementation class
     */
    void addLoanMagazine(LoanMagazine loanMagazine) throws SQLException;

    /**
     * Method returning ArrayList of all magazines to loan
     * @return
     * ArrayList of magazines available to loan
     * @throws SQLException
     */
    ArrayList<Magazine> getAvailableMagazineList() throws SQLException;

    /**
     * Method returning library user after it's ssn
     * @param ssn
     * Social security number
     * @return
     * Library user
     * @throws SQLException
     */
    LibraryUser getLibraryUser(String ssn)throws SQLException;

    /**
     * Method returning ArrayList of magazine that are loaned
     * @param ssn
     * Social security number
     * @return
     * ArraList of loaned magazines
     * @throws SQLException
     */
    ArrayList<Magazine> getLoanedMagazines(String ssn)throws SQLException;

    /**
     * Method returning loans of user after it's ssn
     * @param ssn
     * Social security number
     * @return
     * Loans of user after it's ssn
     * @throws SQLException
     */
    ArrayList<LoanMagazine> getUserLoans(String ssn) throws SQLException;

    /**
     * Method returning a magazine from being loaned to the library
     * @param id_magazine
     * ID number of loan
     * @throws SQLException
     */
    void returnMagazine(int id_magazine) throws SQLException;
}
