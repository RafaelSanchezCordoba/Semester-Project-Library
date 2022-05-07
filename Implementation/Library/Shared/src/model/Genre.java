package model;

/**
 * Genre class that create a genre object.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 08/04/22.
 */
public class Genre {
    private int id;
    private final String genre;

    /**
     * Genre constructor
     * @param id
     * The unique identification number of the genre.
     * @param genre
     * The genre name, ex: Drama, Horror...
     */
    public Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }
}
