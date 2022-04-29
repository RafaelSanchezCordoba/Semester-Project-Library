package server;

import model.Genre;

public interface GenreStorage {
    void addGenre(Genre genre);
    void removeGenre(int id);
}
