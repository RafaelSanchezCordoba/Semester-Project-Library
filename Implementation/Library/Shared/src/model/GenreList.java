package model;

import model.Genre;

import java.util.ArrayList;

/**
 * The class create a genre list.
 * @author Rafael Sánchez Córdoba & Franciszek Nurkiewicz.
 * @version 2.0 10/05/2022.
 */
public class GenreList {
    private final ArrayList<Genre> genres = new ArrayList<>();

    /**
     * Empty constructor
     */
    public GenreList() {}

    /**
     * Add genre method that will add genre to the ArrayList of genres
     * @param genre
     * genre
     */
    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    /**
     * Get genres list returning arrayList of genres
     * @return
     * genres - arraylist
     */
    public ArrayList<Genre> getGenresList(){
        return genres;
    }

    /**
     * Remove Genre method returning genre from ArrayList genres
     * @param genre
     * genre
     */
    public void removeGenre(Genre genre){
        genres.remove(genre);
    }
}
