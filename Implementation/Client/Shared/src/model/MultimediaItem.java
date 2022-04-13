package model;

import model.GenreList;

import java.io.Serializable;

public class MultimediaItem implements Serializable {
    private final int id;
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

    public MultimediaItem(int id, String title, String publisher) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
    }
    public MultimediaItem(){
        id= 0;
        title = null;
        publisher= null;
    }


}
