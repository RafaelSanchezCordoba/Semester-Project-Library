package persistance.DAO;

import model.Genre;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GenreDAO {
    void addGenre(Genre genre) throws SQLException;
    void removeGenre(int id) throws SQLException;
    ArrayList<Genre> getGenreList() throws SQLException;
}
