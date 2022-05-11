package persistance.DAO_implementation;

import model.Librarian;
import persistance.DAO.LibrarianDAO;

import java.sql.*;
import java.util.ArrayList;

public class LibrarianDAOImplementation implements LibrarianDAO {

    private String insertLibrarianSql = "INSERT INTO \"library\".librarian(ssn,password,f_name,l_name,dateOfEmployment)"
            +"VALUES(?,?,?,?,?)";
    private String removeLibrarianSql = "DELETE FROM \"library\".librarian WHERE ssn = ?";

    private String getLibrarianListSql = "SELECT * FROM \"library\".librarian ORDER BY l_name DESC";

    private static LibrarianDAOImplementation instance;

    private LibrarianDAOImplementation() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized LibrarianDAOImplementation getInstance() throws SQLException {
        if (instance == null) {
            instance = new LibrarianDAOImplementation();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:postgresql://tai.db.elephantsql.com/naeoxool",
                "naeoxool","1eiSjWkSFVXj15hc0j47p_js1irgaDWr");
    }

    @Override
    public void addLibrarian(Librarian librarian) throws SQLException {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insertLibrarianSql);
            statement.setLong(1, librarian.getSsn());
            statement.setString(2, librarian.getPassword());
            statement.setString(3, librarian.getFirstName());
            statement.setString(4, librarian.getLastName());
            statement.setDate(5, librarian.getDateOfEmployment());
            statement.executeUpdate();
        }
    }

    @Override
    public void removeLibrarian(long SSN) throws SQLException {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(removeLibrarianSql);
            statement.setLong(1, SSN);
            statement.executeUpdate();
        }
    }

    @Override
    public ArrayList<Librarian> getLibrarianList() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(getLibrarianListSql);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Librarian> result = new ArrayList<Librarian>();
            while (resultSet.next()) {
                int SSN = resultSet.getInt("ssn");
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
