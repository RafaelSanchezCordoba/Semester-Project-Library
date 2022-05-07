package model;

import model.Genre;

import java.util.ArrayList;

/**
 * The class create a genre list.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 08/04/22.
 */
public class GenreList {
    private final ArrayList<Genre> genres = new ArrayList<Genre>();

    /**
     * Empty constructor
     */
    public GenreList() {

    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }
}
