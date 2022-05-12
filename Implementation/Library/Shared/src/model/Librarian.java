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
<<<<<<< Updated upstream
    private String date;
    private final int ssn;
=======
<<<<<<< HEAD
    private Date date;
    private final String ssn;
=======
    private String date;
    private final int ssn;
>>>>>>> 7e718619a38eaa66df02ddfe9864aa4893dd4e7c
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
    public Librarian(int ssn, String password, String firstName, String lastName) {
=======
<<<<<<< HEAD
    public Librarian(String ssn, String password, String firstName, String lastName) {
=======
    public Librarian(int ssn, String password, String firstName, String lastName) {
>>>>>>> 7e718619a38eaa66df02ddfe9864aa4893dd4e7c
>>>>>>> Stashed changes
        this.ssn = ssn;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date =  new CurrentTime().getFormattedIsoDate();
    }
<<<<<<< Updated upstream

    public int getSsn(){
=======

<<<<<<< HEAD

    public String getSsn(){
=======
    public int getSsn(){
>>>>>>> 7e718619a38eaa66df02ddfe9864aa4893dd4e7c
>>>>>>> Stashed changes
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
