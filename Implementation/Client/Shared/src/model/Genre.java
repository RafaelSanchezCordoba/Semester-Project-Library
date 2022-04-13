package model;

/**
 * Genre class that create a genre object.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 08/04/22.
 */
public class Genre {
    private final int id;
    private final String genre;

    /**
     * Genre constructor
     * @param id
     * The unique identification number of the genre.
     * @param genre
     * The genre name, ex: Drama, Horror...
     */
    public Genre(int id, String genre) {
        this.id = id;
        this.genre = genre;
    }
}
