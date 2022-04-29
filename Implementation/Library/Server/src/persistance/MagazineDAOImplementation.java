package persistance;

import model.Genre;
import model.Magazine;

import java.sql.*;
import java.util.ArrayList;

public class MagazineDAOImplementation implements MagazineDAO {

  private String insertMagazineSql = "INSERT INTO \"library\".magazine(id,publisher,title,volume,date,librarian_ssn)"
      +"VALUES(?,?,?,?,?,?)";
  private String removeMagazineSql = "DELETE FROM\"library\".magazine "
      +"WHERE id = ?";

  private String getMagazineListSql = "SELECT * FROM \"library\".magazine ORDER BY id DESC";

  private static MagazineDAOImplementation instance;

  private MagazineDAOImplementation() throws SQLException {

    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized MagazineDAOImplementation getInstance() throws SQLException {
    if (instance == null) {
      instance = new MagazineDAOImplementation();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://tai.db.elephantsql.com/naeoxool",
        "naeoxool","1eiSjWkSFVXj15hc0j47p_js1irgaDWr");
  }

  @Override public void removeMagazine(int id) throws SQLException
  {
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement(removeMagazineSql);
      statement.setInt(1, id);
      statement.executeUpdate();
    }

  }

  @Override public void addMagazine(Magazine magazine) throws SQLException{

    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement((insertMagazineSql), PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, magazine.getTitle());
      statement.setString(2, magazine.getPublisher());
      statement.setInt(3, magazine.getVolume());
      statement.setString(4, magazine.getGenre());
      java.sql.Date date = Date.valueOf(magazine.getDate());
      statement.setDate(5, date);
      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next()) {
        magazine.setId(keys.getInt(1));
      } else {
        throw new SQLException("No keys generated");
      }


    }
  }

  @Override public ArrayList<Magazine> getMagazineList() throws SQLException{
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement(getMagazineListSql);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Magazine> result = new ArrayList<Magazine>();
      while (resultSet.next()) {
        int id = resultSet.getInt(1);
        String title = resultSet.getString(2);
        String publisher = resultSet.getString(3);
        int volume = resultSet.getInt(4);
        String genre = resultSet.getString(5);
        java.sql.Date date = resultSet.getDate(6);
        Magazine magazine = new Magazine(title, publisher, volume, genre, date);
        magazine.setId(id);
        result.add(magazine);
      }
      return result;
    }
  }
}
