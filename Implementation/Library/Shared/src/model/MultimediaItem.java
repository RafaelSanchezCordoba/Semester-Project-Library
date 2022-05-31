package model;

import model.GenreList;

import java.io.Serializable;

/**
 * MultimediaItem class that implements <code>Serializable</code>.
 * @author Rafael Sanchez Cordoba.
 * @version 1.0 08/04/22.
 */
public class MultimediaItem implements Serializable {
    private int id;
    private final String title, publisher;

    /**
     * Multimedia item constructor
     * @param title
     * The title of the multimedia item.
     * @param publisher
     * The publisher of the multimedia item.
     */

    public MultimediaItem(String title, String publisher) {
        this.id = 0;
        this.title = title;
        this.publisher = publisher;
    }

    /**
     * Get id method
     * @return
     * The unique identification number of the multimedia item
     */
    public int getId()
    {
        return id;
    }

    /**
     * Get title method
     * @return
     * The title of the multimedia item
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Get publisher method
     * @return
     * The publisher of the multimedia item
     */
    public String getPublisher()
    {
        return publisher;
    }

    /**
     * Set id method
     * @param id
     * The unique identification number
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * toString method
     * @return
     * The variables of the multimedia item as a String
     */
    @Override public String toString()
    {
        return  title + ", Publisher: " + publisher ;
    }





}
