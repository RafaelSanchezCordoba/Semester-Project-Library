package model;

import model.Genre;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class create a genre list.
 * @author Rafael Sanchez Cordoba.
 * @version 1.0 08/04/22.
 */
public class GenreList implements Serializable
{
    private final ArrayList<Genre> genres;

    /**
     * Empty constructor that create the object
     */
    public GenreList() {
        genres= new ArrayList<>();
    }

    /**
     * Add the genre passed as an argument into the genre list
     * @param genre
     * The genre
     */
    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    /**
     * Get a genre in a specific index of the list
     * @param index
     * The position in the genre list
     * @return
     * The genre in the index passed as an argument
     */
    public Genre getGenre(int index){
        return genres.get(index);
    }

    /**
     * Get size of the list method
     * @return
     * The size of the genre list as an Integer
     */
    public int getSize(){
        return genres.size();
    }

    /**
     * Add a list of genres passed as an argument into this genre list
     * @param genres
     * List of genres
     */
    public void addAll(ArrayList<Genre> genres){
        this.genres.addAll(genres);
    }

    /**
     * toString method of genre list
     * @return
     * Print all the genres in the list as a String
     */
    @Override public String toString()
    {
        String list="";
        for (int i=0;i<genres.size();i++)
        {
            list+=genres.get(i)+" ";
        }
        return list.trim();
    }
}
