package model;

import model.GenreList;

import java.io.Serializable;

/**
 * MultimediaItem class that implements <code>Serializable</code>.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 08/04/22.
 */
public class MultimediaItem implements Serializable {
    private final int id;
    private final String title, publisher;

    /**
     * Multimedia item constructor
     * @param id
     * The unique identification number of the multimedia item,
     * @param title
     * The title of the multimedia item.
     * @param publisher
     * The publisher of the multimedia item.
     */
    public MultimediaItem(int id, String title, String publisher) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
    }


}
