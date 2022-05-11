package model;

import model.Genre;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class create a genre list.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 08/04/22.
 */
public class GenreList implements Serializable
{
    private final ArrayList<Genre> genres;

    /**
     * Empty constructor
     */
    public GenreList() {
        genres= new ArrayList<>();
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }
    public Genre getGenre(int index){
        return genres.get(index);
    }

    public int getSize(){
        return genres.size();
    }
    public void addAll(ArrayList<Genre> genres){
        this.genres.addAll(genres);
    }
}
