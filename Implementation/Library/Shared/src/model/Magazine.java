package model;

import java.io.Serializable;


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
     * @param id
     * The unique identification number of the magazine.
     * @param title
     * The title of the magazine.
     * @param publisher
     * The publisher of the magazine.
     * @param volume
     * The volume of the magazine.
     * @param genre
     * The genre of the magazine.
     */
    public Magazine(int id, String title, String publisher, int volume, String genre, java.sql.Date date) {
        super(id, title, publisher);
        this.volume = volume;
        this.genre = genre;
        this.date = date;
    }
    public Magazine(){
        super(0,"","");
        this.volume = 0;
        this.genre = "";
        this.date = null;
    }

    public int getVolume()
    {
        return volume;
    }

    public String getGenre()
    {
        return genre;
    }

    public String getDate()
    {
        return date.toString();
    }
}
