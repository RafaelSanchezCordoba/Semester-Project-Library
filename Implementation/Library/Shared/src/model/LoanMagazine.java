package model;

import java.io.Serializable;
import java.sql.Date;

public class LoanMagazine implements Serializable {
    private int id;
    private final int id_magazine;
    private final Date startDate;
    private Date endDate;
    private final String ssn;

    /**
     * The constructor of loan magazine that set the start date as the current date
     * @param id_magazine
     * The id of the magazine
     * @param ssn
     * The social security number of the library user
     */
    public LoanMagazine(int id_magazine, String ssn) {
        this.id = 0;
        this.id_magazine = id_magazine;
        this.endDate = null;
        this.ssn = ssn;
        CurrentTime now=new CurrentTime();
        String day=now.getFormattedIsoDate().substring(8,10);
        String month=now.getFormattedIsoDate().substring(5,7);
        String year=now.getFormattedIsoDate().substring(0,4);
        this.startDate = new Date(Integer.parseInt(year)-1900,Integer.parseInt(month)-1,Integer.parseInt(day));
    }

    /**
     * The constructor with all the variables
     * @param id
     * The unique identification number
     * @param id_magazine
     * The id of the magazine
     * @param ssn
     * The social security number
     * @param startDate
     * The start date of the loan
     * @param endDate
     * The end date of the loan
     */
    public LoanMagazine (int id,int id_magazine,String ssn,Date startDate,Date endDate){
        this.id = id;
        this.id_magazine = id_magazine;
        this.ssn = ssn;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Get end date method
     * @return
     * The end date of the loan
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Get start date method
     * @return
     * The start date of the method
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the end date when the book is returned
     */
    public void setEndDate(){
        CurrentTime now=new CurrentTime();
        String day=now.getFormattedIsoDate().substring(8,10);
        String month=now.getFormattedIsoDate().substring(5,7);
        String year=now.getFormattedIsoDate().substring(0,4);

        this.endDate = new Date(Integer.parseInt(year)-1900,Integer.parseInt(month)-1,Integer.parseInt(day));
    }

    /**
     * Get id method
     * @return
     * The unique identification number
     */
    public int getId() {
        return id;
    }

    /**
     * Get id of magazine method
     * @return
     * The id of the magazine
     */
    public int getId_magazine() {
        return id_magazine;
    }

    /**
     * Get the social security number method
     * @return
     * The social security number
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Set id method
     * @param id
     * The unique identification number
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * toString method
     * @return
     * The variables of loan magazine as a String
     */
    public String toString(){
        return getId() + " " + getId_magazine() + " " + getStartDate() + " " + getSsn();
    }
}
