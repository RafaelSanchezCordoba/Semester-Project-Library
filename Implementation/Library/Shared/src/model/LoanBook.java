package model;

import java.io.Serializable;
import java.sql.Date;

public class LoanBook implements Serializable
{
  private int id ;
  private final  int id_book;
  private final  Date startDate, endDate;
  private final String ssn;

  /**
   * Constructor of loan book that set the startDate as the current date
   * @param id_book
   * The id of the book to make the loan
   * @param ssn
   * The social security number of the library user
   */
  public LoanBook(int id_book,String ssn){
    this.id_book = id_book;
    this.ssn = ssn;
    this.endDate = null;
    CurrentTime now = new CurrentTime();
    String day=now.getFormattedIsoDate().substring(8,10);
    String month=now.getFormattedIsoDate().substring(5,7);
    String year=now.getFormattedIsoDate().substring(0,4);
    this.startDate = new Date(Integer.parseInt(year)-1900,Integer.parseInt(month)-1,Integer.parseInt(day));

  }

  /**
   * Constructor of loan book with all the variables
   * @param id
   * The unique identification number of the loan
   * @param id_book
   * The id of the book
   * @param ssn
   * The social security number of the library user
   * @param startDate
   * The star date of the loan
   * @param endDate
   * The end date of the loan
   */
  public LoanBook (int id,int id_book,String ssn,Date startDate,Date endDate){
    this.id = id;
    this.id_book = id_book;
    this.ssn = ssn;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  /**
   * Set the unique identification number of the loan
   * @param id
   * The unique identification number
   */
  public void  setId(int id){
    this.id = id;
  }

  /**
   * Get id method
   * @return
   * The unique identification number
   */
  public int getId()
  {
    return id;
  }

  /**
   * Get id of the book method
   * @return
   * The id of the book
   */
  public int getId_book()
  {
    return id_book;
  }

  /**
   * Get start date method
   * @return
   * The start date of the loan
   */
  public Date getStartDate()
  {
    return startDate;
  }

  /**
   * Get end date method
   * @return
   * The end date of the loan
   */
  public Date getEndDate()
  {
    return endDate;
  }

  /**
   * Get social security number method
   * @return
   * The social security number
   */
  public String getSsn()
  {
    return ssn;
  }

  /**
   * toString method
   * @return
   * The variables of loan book as a String
   */
  @Override public String toString()
  {
    return "" + "id_book=" + id_book + ", startDate=" + startDate
        + ", endDate=" + endDate + ", ssn='" + ssn;
  }
}
