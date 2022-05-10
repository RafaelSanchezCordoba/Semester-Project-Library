package model;

/**
 * Class author.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 08/04/22.
 */
public class Author {
    private final int id;
    private final String firstName;
    private final String lastName;

    /**
     *  Author constructor, 3 arguments:
     * @param id
     * The unique identification number of the author.
     * @param firstName
     * The first name of the author.
     * @param lastName
     * The last name of the author.
     */
    public Author(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString(){
        return id + " " + firstName + " " + lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public int getId(){
        return id;
    }
}
