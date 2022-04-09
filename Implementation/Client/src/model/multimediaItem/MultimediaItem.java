package model.multimediaItem;

import model.GenreList;

public class MultimediaItem {
    private final int id;
    private final String title, publisher;
    private GenreList genres;

    public MultimediaItem(int id, String title, String publisher, GenreList genres) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.genres = genres;
    }


}
