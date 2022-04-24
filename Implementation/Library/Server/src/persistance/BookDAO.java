package persistance;

import java.util.ArrayList;

public interface BookDAO
{
  void removeBook(int id);
  ArrayList<Object[]> getBookList();
  void addBook(int id,int isbn,String publisher,String title, int year_published,String author,int edition,int librarian_ssn);
  void start();
}
