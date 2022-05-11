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

    /**
     * getId method returning id of a multimedia item
     * @return
     * id of the multimedia item
     */
    public int getId()
    {
        return id;
    }

    /**
     * getTitle method returning title of a multimedia item
     * @return
     * title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * getPublisher method returning publisher of a multimedia item
     * @return
     * publisher
     */
    public String getPublisher()
    {
        return publisher;
    }


    /**
     * Multimedia item constructor - two parameters
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
     * Multimedia item constructor - no parameters
     * id, title and publisher of an item are initialized.
     */
    public MultimediaItem(){
        id= 0;
        title = null;
        publisher= null;
    }

    /**
     * Set id method for multimedia item
     * @param id
     * id
     */
    public void setId(int id) {
        this.id = id;
    }

}
