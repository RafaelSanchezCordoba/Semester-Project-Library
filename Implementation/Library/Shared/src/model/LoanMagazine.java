package model;

import java.io.Serializable;
import java.sql.Date;

public class LoanMagazine implements Serializable {
    private int id;
    private final int id_magazine;
    private final Date startDate, endDate;
    private final String ssn;


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

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getId() {
        return id;
    }

    public int getId_magazine() {
        return id_magazine;
    }

    public String getSsn() {
        return ssn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return getId() + " " + getId_magazine() + " " + getStartDate() + " " + getSsn();
    }
}
