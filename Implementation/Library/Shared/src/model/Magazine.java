package model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Sub-class Magazine that extends <code>"MultimediaItem"</code>  and implements <code>Serializable</code>.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 08/04/22.
 */
public class Magazine extends MultimediaItem implements Serializable {
    private final int volume;
    private final String genre;
    private final java.sql.Date date;

    /**
     * Magazine constructor with super method.
     * @param title
     * The title of the magazine.
     * @param publisher
     * The publisher of the magazine.
     * @param volume
     * The volume of the magazine.
     * @param genre
     * The genre of the magazine.
     */

    public Magazine(String title, String publisher, int volume, String genre, java.sql.Date date) {
        super(title, publisher);
        this.volume = volume;
        this.genre = genre;
        this.date = date;
    }

    public int getVolume()
    {
        return volume;
    }

    public String getGenre()
    {
        return genre;
    }

    public Date getDate()
    {
        return date;
    }



    @Override public String toString()
    {
        return super.toString()+", Volume: "+getVolume()+", Genre: "+getGenre();
    }
}
