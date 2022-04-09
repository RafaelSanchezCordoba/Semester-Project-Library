package sharedObjects;

public class Librarian {
    private final String ssn, password, firstName, lastName, date;
    private final CurrentTime currentTime;

    public Librarian(String ssn, String password, String firstName, String lastName) {
        currentTime = new CurrentTime();
        this.ssn = ssn;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = currentTime.getFormattedIsoDate();
    }


}
