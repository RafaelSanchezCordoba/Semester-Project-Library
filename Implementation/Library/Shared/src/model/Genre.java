package model;

/**
 * Genre class that create a genre object.
 * @author Rafael Sánchez Córdoba, Franciszek Nurkiewicz.
 * @version 2.0 10/05/2022.
 */
public class Genre {
    private int id;
    private final String genre;

    /**
     * The unique identification number of the genre.
     * @param genre
     * The genre name, ex: Drama, Horror...
     */
    public Genre(String genre) {
        this.genre = genre;
    }

    /**
     * Get genre method returning String with genre name
     * @return
     * genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Set method setting genres id
     * @param id
     * id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get genre's id method returning id of genre
     * @return
     * id
     */
    public int getId()
    {
        return id;
    }

    /**
     * To String method returning genre
     * @return
     * genre
     */
    @Override public String toString(){
        return genre;
    }
}
