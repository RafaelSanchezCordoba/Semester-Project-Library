package model;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * The librarian class.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 08/04/22.
 */
public class Librarian  implements Serializable
{
    private final String password, firstName, lastName, date;
    private final int ssn;
    private final CurrentTime currentTime;

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
        currentTime = new CurrentTime();
        this.ssn = ssn;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = currentTime.getFormattedIsoDate();
    }

    public int getSnn(){
        return ssn;
    }


    @Override public String toString()
    {
        return "Librarian{" + "ssn:'" + ssn
            + '\'' + ", first Name:'" + firstName + '\'' + ", last Name:'"
            + lastName +"}";
    }
}
