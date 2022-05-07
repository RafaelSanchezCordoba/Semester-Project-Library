package persistance;

import model.Genre;
import server.GenreStorage;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenreStorageTest implements GenreStorage {

    private int counter;
    private final ArrayList<Genre> genres;
    private static GenreStorageTest instance;

    private GenreStorageTest() {
        this.genres = new ArrayList<>();
        this.counter=0;
    }

    public static synchronized GenreStorageTest getInstance() {
        if (instance == null)
        {
            instance = new GenreStorageTest();
        }
        return instance;
    }

    @Override
    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.setId(counter);
        counter++;
        System.out.println(genre);
    }

    @Override
    public void removeGenre(int id) {
        for (int i = 0; i < genres.size(); i++)
        {
            if (id == genres.get(i).getId())
            {
                genres.remove(genres.get(i));
                break;
            }
        }
    }

    @Override public ArrayList<Genre> getGenreList() throws SQLException, RemoteException
    {
        return genres;
    }
}
