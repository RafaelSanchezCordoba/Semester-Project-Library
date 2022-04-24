package persistance;

import java.sql.*;
import java.util.ArrayList;

public class BookDAOImplementation implements BookDAO {

  private String driver, url, user, password;

 private   Connection connection;
 private PreparedStatement insBookStatement;
 private PreparedStatement remBookStatement;



  //Pre-made sql to use in the methods
 private String insertBookSql = "INSERT INTO \"library\".book(id,isbn,publisher,title,year_published,author,edition,librarian_ssn)"
      + "VALUES( ?,?,?,?,?,?,?,?)";
 private String removeBookSql = "DELETE FROM \"library\".book "
     +"WHERE id=?";

 private String insertMagazineSql = "INSERT INTO \"library\".magazine(id,publisher,title,volume,date,librarian_ssn)"
     +"VALUES(?,?,?,?,?,?)";
 private String removeMagazineSql = "DELETE FROM\"library\".magazine "
     +"WHERE id = ?";

  public BookDAOImplementation(String driver,String url,String user,String password){
    this.driver = driver;
    this.url = url;
    this.user = user;
    this.password = password;
    connection = null;
    insBookStatement = null;
    remBookStatement = null;

  }

  //methods for book to use argument to in the presaved query

  public void removeBook(int id){
    try
    {
      remBookStatement.setInt(1,id);
      remBookStatement.executeUpdate();
    }catch (SQLException e){
      e.printStackTrace();
    }

  }



  public ArrayList<Object[]> getBookList(){
    ArrayList<Object[]> result = new ArrayList();
    try
    {
      String query = "SELECT * FROM \"library\".book ORDER BY id DESC";
      PreparedStatement queryStatement = connection.prepareStatement(query);
      ResultSet resultSet = queryStatement.executeQuery();

      while (resultSet.next()){
        Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
        for (int i = 0; i<row.length;i++){
          row[i] = resultSet.getObject(i+1);
        }
        result.add(row);
      }
      resultSet.close();
      queryStatement.close();




    }catch (SQLException e){
      e.printStackTrace();
    }

    return result;
  }





  public void addBook(int id,int isbn,String publisher,String title,
      int year_published,String author,int edition,int librarian_ssn){
    try
    {
      insBookStatement.setInt(1,id);
      insBookStatement.setInt(2,isbn);
      insBookStatement.setString(3,publisher);
      insBookStatement.setString(4,title);
      insBookStatement.setInt(5,year_published);
      insBookStatement.setString(6,author);
      insBookStatement.setInt(7,edition);
      insBookStatement.setInt(8,librarian_ssn);
      insBookStatement.executeUpdate();

    }catch (SQLException e){
      e.printStackTrace();
    }
  }
  public void start(){
    try
    {
      Class.forName(driver);
    }catch (ClassNotFoundException e){
      e.printStackTrace();
    }
    try{
      connection = DriverManager.getConnection(url,user,password);
    }catch (SQLException e){
      e.printStackTrace();
    }

    // Saves the query to remove and add book to be used later
    try
    {
      insBookStatement = connection.prepareStatement(insertBookSql);
    }catch (SQLException e){
      e.printStackTrace();
    }
    try
    {
      remBookStatement = connection.prepareStatement(removeBookSql);
    }catch (SQLException e){
      e.printStackTrace();
    }
    //Saves the query for magazine

  }

}
