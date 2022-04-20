import java.sql.*;
import java.util.ArrayList;

public class DBConnectorImplementation implements DBConnector
{
  private String driver;
  private String url;

 private String user;
 private String password;

 private   Connection connection;
 private PreparedStatement insBookStatement;
 private PreparedStatement remBookStatement;



  //Pre-made sql to use in the methods
 private String insertBookSql = "INSERT INTO \"library\".book(id,publisher,title,isbn,year_published,genre,author,edition,librarian_ssn)"
      + "VALUES( ?,?,?,?,?,?,?,?,?)";
 private String removeBookSql = "DELETE FROM \"library\".book "
     +"WHERE id=?";

 private String insertMagazineSql = "INSERT INTO \"library\".magazine(id,publisher,title,volume,date,librarian_ssn)"
     +"VALUES(?,?,?,?,?,?)";
 private String removeMagazineSql = "DELETE FROM\"library\".magazine "
     +"WHERE id = ?";

  public DBConnectorImplementation(String driver,String url,String user,String password){
    this.driver = driver;
    this.url = url;
    this.user = user;
    this.password = password;
    connection = null;
    insBookStatement = null;
    remBookStatement = null;

  }

  //methods for book to use argument to in the presaved query

  public void removeBook(String id){
    try
    {
      remBookStatement.setString(1,id);
      remBookStatement.executeUpdate();
    }catch (SQLException e){
      e.printStackTrace();
    }

  }



  public ArrayList<Object[]> getBookList(){
    ArrayList<Object[]> result = new ArrayList();
    try
    {
      String query = "SELECT id,publisher,title,isbn,author FROM \"library\".book ORDER BY id DESC";
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





  public void addBook(String id,String publisher,String title,String isbn,
      String year_published,String genre,String author,String edition,String librarian_ssn){
    try
    {
      insBookStatement.setString(1,id);
      insBookStatement.setString(2,publisher);
      insBookStatement.setString(3,title);
      insBookStatement.setString(4,isbn);
      insBookStatement.setString(5,year_published);
      insBookStatement.setString(6,author);
      insBookStatement.setString(7,genre);
      insBookStatement.setString(8,edition);
      insBookStatement.setString(9,librarian_ssn);
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
