package model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Date;

/**
 * The librarian class.
 * @author Rafael Sanchez Cordoba.
 * @version 1.0 08/04/22.
 */
public class Librarian  implements Serializable
{
    private final String password;
    private final String firstName;
    private final String lastName;
    private Date date;
    private final String ssn;

    /**
     * Librarian constructor.
     * @param ssn
     * The social security number.
     * @param password
     * The password.
     * @param firstName
     * The first name.
     * @param lastName
     * The last name.
     */
    public Librarian(String ssn, String password, String firstName, String lastName) {
        this.ssn = ssn;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        CurrentTime now=new CurrentTime();
        String day=now.getFormattedIsoDate().substring(8,10);
        String month=now.getFormattedIsoDate().substring(5,7);
        String year=now.getFormattedIsoDate().substring(0,4);
        this.date=new Date(Integer.parseInt(year)-1900,Integer.parseInt(month)-1,Integer.parseInt(day));
    }

    /**
     * Get the social security number method
     * @return
     * The social security number of the librarian
     */
    public String getSsn(){
        return ssn;
    }

    /**
     * Get password method
     * @return
     * The password of the librarian
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get first name method
     * @return
     * The first name of the librarian
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get last name method
     * @return
     * The last name of the librarian
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get date of employment method
     * @return
     * The date of employment
     */
    public Date getDateOfEmployment() {
        return date;
    }

    /**
     * Set date method
     * @param date
     * The date of employment
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * toString method of librarian
     * @return
     * The librarian variables values as a String
     */
    @Override public String toString()
    {
        return "Librarian{" + "ssn:'" + ssn
                + '\'' + ", first Name:'" + firstName + '\'' + ", last Name:'"
                + lastName +"'}";
    }
}
