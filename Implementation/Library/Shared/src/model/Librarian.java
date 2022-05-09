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
    private String date;
    private final int ssn;

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
    public Librarian(int ssn, String password, String firstName, String lastName) {
        this.ssn = ssn;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date =  new CurrentTime().getFormattedIsoDate();
    }

    public int getSsn(){
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

    public String getDateOfEmployment() {
        return date;
    }

    public void setDate(Date date) {
        this.date = String.valueOf(date);
    }

    @Override public String toString()
    {
        return "Librarian{" + "ssn:'" + ssn
            + '\'' + ", first Name:'" + firstName + '\'' + ", last Name:'"
            + lastName +"'}";
    }
}
