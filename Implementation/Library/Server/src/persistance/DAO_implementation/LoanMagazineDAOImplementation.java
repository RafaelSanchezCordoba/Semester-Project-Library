package persistance.DAO_implementation;

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
    /**
     * sql to set an end date to the loan magazine in the db
     */
    private String setEndDateLoan = "UPDATE \"library\".loan_magazine"
            +"SET is_available=?"
            +"WHERE id = ?";

    private String setAvailable = "UPDATE \"library\".magazine"
        +"SET available=?"
        +"WHERE id = ?";

    /**
     * sql to get all the availables magazines to
     */
    private String getAvailableMagazines = "SELECT*"+
            "FROM \"library\".magazine"+
            "WHERE is_available = true "+
            "ORDER BY id DESC";

    private static LoanMagazineDAOImplementation instance;

    private LoanMagazineDAOImplementation()throws SQLException{
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public LoanMagazineDAOImplementation getInstance() throws SQLException{
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

    @Override
    public void addLoanMagazine(LoanMagazine loanMagazine) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement((createMagazineLoanSql), PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, loanMagazine.getId_magazine());
            statement.setDate(2,loanMagazine.getStartDate());
            statement.setDate(3, loanMagazine.getEndDate());
            statement.setString(4, loanMagazine.getSsn());
            //java.sql.Date date = Date.valueOf(magazine.getDate());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                loanMagazine.setId(keys.getInt(1));
            } else {
                throw new SQLException("No keys generated");
            }
            PreparedStatement statement1 = connection.prepareStatement(setAvailable);
            statement.setBoolean(1,false);
            statement.setInt(2,loanMagazine.getId_magazine());


        }
    }
}
