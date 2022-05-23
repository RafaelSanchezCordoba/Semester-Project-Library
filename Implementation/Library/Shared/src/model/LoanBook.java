package model;

import java.io.Serializable;
import java.sql.Date;

public class LoanBook implements Serializable
{
  private int id ;
  private final  int id_book;
  private final  Date startDate, endDate;
  private final String ssn;

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
  public LoanBook (int id,int id_book,String ssn,Date startDate,Date endDate){
    this.id = id;
    this.id_book = id_book;
    this.ssn = ssn;
    this.startDate = startDate;
    this.endDate = endDate;
  }
  public void  setId(int id){
    this.id = id;
  }

  public int getId()
  {
    return id;
  }

  public int getId_book()
  {
    return id_book;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  @Override public String toString()
  {
    return "" + "id_book=" + id_book + ", startDate=" + startDate
        + ", endDate=" + endDate + ", ssn='" + ssn;
  }

  public String getSsn()
  {
    return ssn;
  }
}
