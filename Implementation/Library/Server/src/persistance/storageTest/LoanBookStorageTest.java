package persistance.storageTest;

import model.LoanBook;
import model.Book;
import model.LibraryUser;
import model.LoanBook;
import server.storage.LoanBookStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanBookStorageTest implements LoanBookStorage
{
  private ArrayList<LoanBook> loanBooks;
  private static LoanBookStorageTest instance;
  private  LibraryUser libraryUser;
  private ArrayList<Book> availableBooks;
  private int counter;



  private LoanBookStorageTest (){
    loanBooks = new ArrayList<>();
    libraryUser = new LibraryUser("12456789909","gianni","nove","palla");
    availableBooks = new ArrayList<>();
    availableBooks.add(new Book("Laini Taylor" ,"Daughter of Smoke and Bone","Hodder & Stoughton","978-0-316-13402-6",2,2011));
    counter = 0;


  }

  public static synchronized LoanBookStorageTest getInstance(){
    if (instance == null){
      instance = new  LoanBookStorageTest();
    }
    return instance;
  }
  @Override public void addLoanBook(LoanBook loanBook)
      throws SQLException, RemoteException
  {
    counter++;
    loanBook.setId(counter);
    loanBooks.add(loanBook);
    System.out.println(loanBooks.size());
  }

  @Override public ArrayList<Book> getAvailableBooks()
      throws SQLException, RemoteException
  {
    return availableBooks;
  }

  @Override public LibraryUser getUser(String ssn) throws RemoteException
  {
    if (ssn.equals(libraryUser.getSSN())){
      return libraryUser;
    }else {
      return null;
    }
  }

  @Override public ArrayList<LoanBook> getUserBookLoans(String ssn)
      throws RemoteException
  {
    ArrayList<LoanBook> result = new ArrayList<>();
    for (int i = 0; i<loanBooks.size();i++){
      if (loanBooks.get(i).getSsn().equals(ssn)){
        result.add(loanBooks.get(i));
      }
    }
    return result;
  }

  @Override public void returnBook(int loan_id) throws RemoteException
  {
    for (LoanBook x:loanBooks){
      if (x.getId()==loan_id){
        loanBooks.remove(x);
      }
    }
  }
}
