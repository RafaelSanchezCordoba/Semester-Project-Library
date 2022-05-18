package persistance.DAO_implementation;

import model.LibraryUser;
import model.LoanMagazine;
import model.Magazine;
import persistance.DAO.LoanMagazineDAO;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class from data access object for the loan magazine in the database
 * @author Alexandru Dulghier
 * @version 1.0 16/05/22.
 */

public class LoanMagazineDAOImplementation implements LoanMagazineDAO {

    /**
     * sql to insert an magazine loan in the database
     */
    private String createMagazineLoanSql = "INSERT INTO \"library\".loan_magazine(magazine_id,start_of_loan,end_of_loan,library_user)"
            +"VALUES(?,?,?,?)";

    private String getLibraryUser = "SELECT * FROM \"library\".library_user WHERE ssn = ? ";


    private String setAvailable = "UPDATE \"library\".magazine SET is_available = false WHERE id = ?";

    /**
     * sql to get all the availables magazines that can be lended from the database
     */
    private String getAvailableMagazines = "SELECT * FROM \"library\".magazine WHERE is_available = TRUE ";



    private static LoanMagazineDAOImplementation instance;

    /**
     * private constructor
     * @throws SQLException
     */
    private LoanMagazineDAOImplementation()throws SQLException{
        DriverManager.registerDriver(new org.postgresql.Driver());
    }


    /**
     * Singleton pattern for the instance of LoanMagazineDAOImplementation
     * @return LoanMagazineDAOImplementation
     * @throws SQLException
     */


    public static synchronized LoanMagazineDAOImplementation getInstance() throws SQLException{
        if (instance==null){

                instance = new LoanMagazineDAOImplementation();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:postgresql://tai.db.elephantsql.com/naeoxool",
                "naeoxool","1eiSjWkSFVXj15hc0j47p_js1irgaDWr");
    }

    public LibraryUser getLibraryUser(String ssn) throws SQLException{
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(getLibraryUser);
            statement.setString(1,ssn);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()){
                String socialnumber = resultSet.getString("ssn");
                String password = resultSet.getString("password");
                String f_name = resultSet.getString("f_name");
                String l_name = resultSet.getString("l_name");

               LibraryUser result = new LibraryUser(socialnumber,f_name,l_name,password);

               return result;
            }
        }
        return null;
    }
    
    /**
     * retruns an Array List of all the available magazines
     * @return ArrayList<Magazine></>
     * @throws SQLException
     */
    public ArrayList<Magazine> getAvailableMagazineList() throws SQLException{
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(getAvailableMagazines);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Magazine> result = new ArrayList<Magazine>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("publisher");
                String publisher = resultSet.getString("title");
                int volume = resultSet.getInt("volume");
                java.sql.Date date = resultSet.getDate("date");
                String genre = resultSet.getString("genre");
                Magazine magazine = new Magazine(title, publisher, volume, genre, date);
                magazine.setId(id);
                result.add(magazine);
            }
            return result;
        }
    }

    /**
     * Insert a loan magazine object in to the database
     * @param loanMagazine
     * @throws SQLException
     */
    @Override
    public void addLoanMagazine(LoanMagazine loanMagazine) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement((createMagazineLoanSql), PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, loanMagazine.getId_magazine());
            statement.setDate(2,loanMagazine.getStartDate());
            statement.setDate(3, loanMagazine.getEndDate());
            statement.setString(4, loanMagazine.getSsn());

            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                loanMagazine.setId(keys.getInt(1));
            } else {
                throw new SQLException("No keys generated");
            }
            PreparedStatement statementSetFalse = connection.prepareStatement(setAvailable);

            statementSetFalse.setInt(1,loanMagazine.getId_magazine());
            statementSetFalse.executeUpdate();
            statementSetFalse.close();

        }
    }


}
