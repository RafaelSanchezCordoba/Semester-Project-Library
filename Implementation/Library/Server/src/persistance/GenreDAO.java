package persistance;

import model.Genre;

import java.sql.SQLException;

public interface GenreDAO {
    void addGenre(Genre genre) throws SQLException;
    void removeGenre(int id) throws SQLException;
}
