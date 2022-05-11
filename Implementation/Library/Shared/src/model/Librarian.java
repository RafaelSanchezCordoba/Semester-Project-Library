package model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Date;

/**
 * The librarian class.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 08/04/22.
 */
public class Librarian  implements Serializable
{
    private final String password;
    private final String firstName;
    private final String lastName;
    private Date date;
    private final long ssn;

    /**
     * Librarian constructor.
     * @param ssn
     * The social security numbner.
     * @param password
     * The password.
     * @param firstName
     * The first name.
     * @param lastName
     * The last name.
     */
    public Librarian(long ssn, String password, String firstName, String lastName) {
        this.ssn = ssn;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date =  new CurrentTime().getFormattedIsoDate();
    }

    public long getSsn(){
        return ssn;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfEmployment() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override public String toString()
    {
        return "Librarian{" + "ssn:'" + ssn
            + '\'' + ", first Name:'" + firstName + '\'' + ", last Name:'"
            + lastName +"'}";
    }
}
