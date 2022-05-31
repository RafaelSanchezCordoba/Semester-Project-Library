package model;

import java.io.Serializable;

/**
 * Genre class that create a genre object.
 * @author Rafael Sanchez Cordoba.
 * @version 1.0 08/04/22.
 */
public class Genre implements Serializable
{
    private int id;
    private final String genre;

    /**
     * Genre constructor
     * @param genre
     * The genre name, ex: Drama, Horror...
     */
    public Genre(String genre) {
        this.genre = genre;
    }

    /**
     * Get genre method
     * @return
     * The genre as a String
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Set id method
     * @param id
     * The unique identification number of genre
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get Id method
     * @return
     * The unique number identification of the genre
     */
    public int getId()
    {
        return id;
    }

    /**
     * toString method
     * @return
     * Genre as a String
     */
    @Override public String toString()
    {
        return  genre;
    }
}
