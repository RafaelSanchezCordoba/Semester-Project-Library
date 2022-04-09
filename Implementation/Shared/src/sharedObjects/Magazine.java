package sharedObjects;

public class Magazine extends MultimediaItem{
    private final int volume;
    private Genre  genre;

    public Magazine(int id, String title, String publisher, int volume, Genre genre) {
        super(id, title, publisher);
        this.volume = volume;
        this.genre=genre;
    }
}
