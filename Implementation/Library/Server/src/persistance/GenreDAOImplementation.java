package persistance;

import model.Genre;

import java.sql.*;

public class GenreDAOImplementation implements GenreDAO{

    private String insertGenreSql = "INSERT INTO \"library\".genre(id,genre)"
            +"VALUES(?,?)";
    private String removeGenreSql = "DELETE FROM\"library\".genre "
            +"WHERE id = ?";

    private static GenreDAOImplementation instance;

    private GenreDAOImplementation() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized GenreDAOImplementation getInstance() throws SQLException {
        if (instance == null) {
            instance = new GenreDAOImplementation();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:postgresql://tai.db.elephantsql.com/naeoxool",
                "naeoxool","1eiSjWkSFVXj15hc0j47p_js1irgaDWr");
    }

    @Override
    public void addGenre(Genre genre) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement((insertGenreSql), PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, genre.getGenre());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                genre.setId(keys.getInt(1));
            } else {
                throw new SQLException("No keys generated");
            }
        }
    }

    @Override
    public void removeGenre(int id) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(removeGenreSql);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
