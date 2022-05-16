package model;

import java.io.Serializable;
import java.sql.Date;

public class LoanMagazine implements Serializable {
    private int id;
    private final int id_magazine;
    private final Date startDate, endDate;
    private final String ssn;


    public LoanMagazine(int id_magazine, Date startDate, String ssn) {
        this.id = 0;
        this.id_magazine = id_magazine;
        this.startDate = startDate;
        this.endDate = null;
        this.ssn = ssn;
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
}
