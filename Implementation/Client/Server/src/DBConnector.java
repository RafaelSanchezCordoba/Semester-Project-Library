import java.util.ArrayList;

public interface DBConnector
{
  void removeBook(String id);
  ArrayList<Object[]> getBookList();
  void addBook(String id,String publisher,String title,String isbn,
      String year_published,String genre,String author,String edition,String librarian_ssn);
  void start();
}
