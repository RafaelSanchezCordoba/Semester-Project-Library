package model;

import java.io.Serializable;

public class Magazine extends MultimediaItem implements Serializable {
    private final int volume;
    private final Genre genre;

    public Magazine(int id, String title, String publisher, int volume, Genre genre) {
        super(id, title, publisher);
        this.volume = volume;
        this.genre = genre;
    }
}
