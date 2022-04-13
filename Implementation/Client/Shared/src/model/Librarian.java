package model;

/**
 * The librarian class.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 08/04/22.
 */
public class Librarian {
    private final String ssn, password, firstName, lastName, date;
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
    public Librarian(String ssn, String password, String firstName, String lastName) {
        currentTime = new CurrentTime();
        this.ssn = ssn;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = currentTime.getFormattedIsoDate();
    }


}
