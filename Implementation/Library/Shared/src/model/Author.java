package model;

/**
 * Class author.
 * @author Rafael Sánchez Córdoba, Franciszek Nurkiewicz.
 * @version 2.0 10/05/2022.
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

    /**
     * To String method returning id,
     * first name and last name as one string
     * @return
     * id, first name, last name as String
     */
    public String toString(){
        return id + " " + firstName + " " + lastName;
    }

    /**
     * Get first name method returning
     * first name of an author
     * @return
     * firstName
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Get last name method returning
     * last name of an author
     * @return
     * lastName
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Get id method returning id
     * of an author
     * @return
     * id
     */
    public int getId(){
        return id;
    }
}
