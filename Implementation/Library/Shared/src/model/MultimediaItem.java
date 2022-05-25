package model;

import model.GenreList;

import java.io.Serializable;

/**
 * MultimediaItem class that implements <code>Serializable</code>.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 08/04/22.
 */
public class MultimediaItem implements Serializable {
    private int id;
    private final String title, publisher;


    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getPublisher()
    {
        return publisher;
    }


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

    @Override public String toString()
    {
        return  title + ", Publisher: " + publisher ;
    }



    public void setId(int id) {
        this.id = id;
    }


}
