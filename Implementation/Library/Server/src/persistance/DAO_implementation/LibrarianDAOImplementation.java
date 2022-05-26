package persistance.DAO_implementation;

import model.Librarian;
import persistance.DAO.LibrarianDAO;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;

/**
 * Public class LibrarianDAOImplementation implementing interface LibrarianDAO
 * This class is opening and closing connection with database.
 */
public class LibrarianDAOImplementation implements LibrarianDAO {

    private String insertLibrarianSql = "INSERT INTO \"library\".librarian(ssn,password,f_name,l_name,dateOfEmployment)"
            +"VALUES(?,?,?,?,?)";
    private String removeLibrarianSql = "DELETE FROM \"library\".librarian WHERE ssn = ?";

    private String getLibrarianListSql = "SELECT * FROM \"library\".librarian ORDER BY l_name DESC";

    private static LibrarianDAOImplementation instance;

    /**
     * LibrarianDAOImplementation constructor with zero parameters
     * Driver manager inside the constructor will attempt to
     * connect to the database
     * @throws SQLException
     */
    private LibrarianDAOImplementation() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    /**
     * Method returning an instance of LibrarianDAPImplementation
     * @return
     * instance of LibrarianDAOImplementation
     * @throws SQLException
     */
    public static synchronized LibrarianDAOImplementation getInstance() throws SQLException {
        if (instance == null) {
            instance = new LibrarianDAOImplementation();
        }
        return instance;
    }

    /**
     * getConnection method
     * @return
     * Driver manager connecting using url,user and password
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:postgresql://tai.db.elephantsql.com/naeoxool",
                "naeoxool","1eiSjWkSFVXj15hc0j47p_js1irgaDWr");
    }

    /**
     * Method adding a librarian
     * @param librarian
     * Librarian object
     * @throws SQLException
     */
    @Override
    public void addLibrarian(Librarian librarian) throws SQLException {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insertLibrarianSql);
            statement.setString(1, librarian.getSsn());
            statement.setString(2, librarian.getPassword());
            statement.setString(3, librarian.getFirstName());
            statement.setString(4, librarian.getLastName());
            statement.setDate(5, librarian.getDateOfEmployment());
            statement.executeUpdate();
        }
    }

    /**
     * Method removing a librarian after it's SSN
     * @param SSN
     * The Social Security Number
     * @throws SQLException
     */
    @Override
    public void removeLibrarian(String SSN) throws SQLException {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(removeLibrarianSql);
            statement.setString(1, String.valueOf(SSN));
            statement.executeUpdate();
        }
    }

    /**
     * Method returning librarians in an ArrayList
     * @return
     * ArrayList of an librarian
     * @throws SQLException
     */
    @Override
    public ArrayList<Librarian> getLibrarianList() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(getLibrarianListSql);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Librarian> result = new ArrayList<Librarian>();
            while (resultSet.next()) {
                String SSN = resultSet.getString("ssn");
                String password = resultSet.getString("password");
                String f_name = resultSet.getString("f_name");
                String l_name = resultSet.getString("l_name");
                Date date = resultSet.getDate("dateofemployment");
                Librarian librarian = new Librarian(SSN, password, f_name, l_name);
                librarian.setDate(date);
                result.add(librarian);
            }
            return result;
        }
    }
}
