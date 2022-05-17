package viewModel;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelBook;
import model.Book;
import model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddRemoveBooksViewModelTest
{
  private AddRemoveBookViewModel viewModel;
  private ModelBook model;
  private StringProperty title;
  private StringProperty publisher;
  private StringProperty author;
  private StringProperty isbn;
  private StringProperty year;
  private StringProperty genre;
  private StringProperty edition;
  private StringProperty search;
  private StringProperty error;
  private SimpleListProperty<Book> bookList;
  private SimpleListProperty<Genre> genreList;
  private SimpleListProperty<Genre> selectedGenreList;


  @BeforeEach void setUp() throws RemoteException
  {
    model=new FakeModelManagerBook();
    viewModel=new AddRemoveBookViewModel(model);

    this.title = new SimpleStringProperty("");
    this.publisher = new SimpleStringProperty("");
    this.author = new SimpleStringProperty("");
    this.isbn = new SimpleStringProperty("");
    this.year = new SimpleStringProperty("");
    this.genre = new SimpleStringProperty("");
    this.edition = new SimpleStringProperty("");
    this.search = new SimpleStringProperty("");
    this.error = new SimpleStringProperty("");
    ObservableList<Book> observableListBook = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<Genre> observableListGenre = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<Genre> observableListSelectedGenre = FXCollections.observableArrayList(new ArrayList<>());
    bookList = new SimpleListProperty<>(observableListBook);
    genreList = new SimpleListProperty<>(observableListGenre);
    selectedGenreList = new SimpleListProperty<>(observableListSelectedGenre);



    viewModel.bindTitleTextField(title);
    viewModel.bindAuthorTextField(author);
    viewModel.bindEditionTextField(edition);
    viewModel.bindErrorLabel(error);
    viewModel.bindISBNTextField(isbn);
    viewModel.bindPublisherTextField(publisher);
    viewModel.bindSearchTextField(search);
    viewModel.bindYearTextField(year);
    viewModel.bindBookListViewForTesting(bookList);
    viewModel.bindSelectedGenreListForTest(selectedGenreList);
    viewModel.bindGenreListForTest(genreList);
  }

  @Test void new_object_have_empty_fields()
  {
    assertEquals("",title.get());
    assertEquals("",publisher.get());
    assertEquals("",author.get());
    assertEquals("",isbn.get());
    assertEquals("",year.get());
    assertEquals("",genre.get());
    assertEquals("",edition.get());
    assertEquals("",search.get());
    assertEquals("",error.get());
    assertEquals("[]",bookList.get().toString());
    assertEquals("[]",selectedGenreList.get().toString());
  }

  @Test void filling_fields_doesnt_change_error_or_list()
  {
    title.set("Daugther of Smoke and Bone");
    publisher.set("Hodder & Stoughton");
    author.set("Laini Taylor");
    isbn.set("978-0-316-13402-6");
    year.set("2011");
    edition.set("1");
    genre.set("Fantasy");
    assertEquals("",error.get());
    assertEquals("[]",bookList.get().toString());
  }

  @Test void add_one_genre_to_the_selected_list()
  {
    viewModel.addGenreToSelectedGenreList(new Genre("Fantasy"));
    assertEquals("[Fantasy]",selectedGenreList.get().toString());
  }

  @Test void add_two_genres_to_the_selected_list()
  {
    viewModel.addGenreToSelectedGenreList(new Genre("Fantasy"));
    viewModel.addGenreToSelectedGenreList(new Genre("Juvenile"));
    assertEquals("[Fantasy, Juvenile]",selectedGenreList.get().toString());
  }

  @Test void add_multiple_genres_to_the_selected_list()
  {
    viewModel.addGenreToSelectedGenreList(new Genre("Fantasy"));
    viewModel.addGenreToSelectedGenreList(new Genre("Juvenile"));
    viewModel.addGenreToSelectedGenreList(new Genre("Horror"));
    viewModel.addGenreToSelectedGenreList(new Genre("Romance"));
    assertEquals("[Fantasy, Juvenile, Horror, Romance]",selectedGenreList.get().toString());
  }

  @Test void add_the_same_genre_twice_to_the_selected_list_doesnt_add_it()
  {
    viewModel.addGenreToSelectedGenreList(new Genre("Fantasy"));
    viewModel.addGenreToSelectedGenreList(new Genre("Fantasy"));
    assertEquals("Duplicated genre",error.get());
    assertEquals("[Fantasy]",selectedGenreList.get().toString());
  }

  @Test void correctly_adding_a_genre_clear_the_duplicated_genre_error()
  {
    viewModel.addGenreToSelectedGenreList(new Genre("Fantasy"));
    viewModel.addGenreToSelectedGenreList(new Genre("Fantasy"));
    viewModel.addGenreToSelectedGenreList(new Genre("Comedy"));
    assertEquals("",error.get());
  }

  @Test void correctly_adding_a_genre_doesnt_clear_the_other_errors()
      throws SQLException, RemoteException
  {
    publisher.set("Hodder & Stoughton");
    author.set("Laini Taylor");
    isbn.set("9780316134026");
    year.set("2011");
    edition.set("1");
    viewModel.addGenreToSelectedGenreList(new Genre("Fantasy"));
    viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
        edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
    viewModel.addGenreToSelectedGenreList(new Genre("Romance"));
    assertEquals("Title can't be null",error.get());
  }

  @Test void add_adds_the_book_one_genre() throws SQLException, RemoteException
  {
    title.set("Daugther of Smoke and Bone");
    publisher.set("Hodder & Stoughton");
    author.set("Laini Taylor");
    isbn.set("9780316134026");
    year.set("2011");
    edition.set("1");
    viewModel.addGenreToSelectedGenreList(new Genre("Fantasy"));
    viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
    edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
    assertEquals("[Title=Daugther of Smoke and Bone, author=Laini Taylor, edition=1, year published=2011, isbn=9780316134026]",bookList.get().toString());
  }

 @Test void add_adds_the_book_with_more_genres()
     throws SQLException, RemoteException
 {
  title.set("Daugther of Smoke and Bone");
  publisher.set("Hodder & Stoughton");
  author.set("Laini Taylor");
  isbn.set("9780316134026");
  year.set("2011");
  edition.set("1");
  viewModel.addGenreToSelectedGenreList(new Genre("Fantasy"));
  viewModel.addGenreToSelectedGenreList(new Genre("Romance"));
  viewModel.addGenreToSelectedGenreList(new Genre("Juvenile"));
  viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
      edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
  assertEquals("[Title=Daugther of Smoke and Bone, author=Laini Taylor, edition=1, year published=2011, isbn=9780316134026]",bookList.get().toString());
}

@Test void add_two_books() throws SQLException, RemoteException
{
  title.set("Daugther of Smoke and Bone");
  publisher.set("Hodder & Stoughton");
  author.set("Laini Taylor");
  isbn.set("9780316134026");
  year.set("2011");
  edition.set("1");
  viewModel.addGenreToSelectedGenreList(new Genre("Fantasy"));
  viewModel.addGenreToSelectedGenreList(new Genre("Romance"));
  viewModel.addGenreToSelectedGenreList(new Genre("Juvenile"));
  viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
      edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
  title.set("Harry Potter and the Philosopher's Stone");
  publisher.set("Bloomsburry");
  author.set("J.K Rowling");
  isbn.set("123456");
  year.set("1997");
  edition.set("1");
  viewModel.addGenreToSelectedGenreList(new Genre("Fantasy"));
   viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
      edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
  assertEquals("[Title=Daugther of Smoke and Bone, author=Laini Taylor, edition=1, year published=2011, isbn=9780316134026, Title=Harry Potter and the Philosopher's Stone, author=J.K Rowling, edition=1, year published=1997, isbn=123456]",bookList.get().toString());
}

@Test void add_multiple_books() throws SQLException, RemoteException
{
  title.set("Daugther of Smoke and Bone");
  publisher.set("Hodder & Stoughton");
  author.set("Laini Taylor");
  isbn.set("9780316134026");
  year.set("2011");
  edition.set("1");
  viewModel.addGenreToSelectedGenreList(new Genre("Fantasy"));
  viewModel.addGenreToSelectedGenreList(new Genre("Romance"));
  viewModel.addGenreToSelectedGenreList(new Genre("Juvenile"));
  viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
      edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
  title.set("Harry Potter and the Philosopher's Stone");
  publisher.set("Bloomsburry");
  author.set("J.K Rowling");
  isbn.set("123456");
  year.set("1997");
  edition.set("1");
  viewModel.addGenreToSelectedGenreList(new Genre("Fantasy"));
  viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
      edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
  title.set("The Outsiders");
  publisher.set("Viking Press");
  author.set("Robert Hunt");
  isbn.set("34567890");
  year.set("1967");
  edition.set("2");
  viewModel.addGenreToSelectedGenreList(new Genre("Drama"));
  viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
      edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
  assertEquals("[Title=Daugther of Smoke and Bone, author=Laini Taylor, edition=1, year published=2011, isbn=9780316134026, Title=Harry Potter and the Philosopher's Stone, author=J.K Rowling, edition=1, year published=1997, isbn=123456, Title=The Outsiders, author=Robert Hunt, edition=2, year published=1967, isbn=34567890]",bookList.get().toString());
}

//remove can't be tested from the view model because is done with the id from the database


//errors
  @Test void null_title_gives_error_and_doesnt_add()
      throws SQLException, RemoteException
  {
    publisher.set("Hodder & Stoughton");
    author.set("Laini Taylor");
    isbn.set("9780316134026");
    year.set("2011");
    edition.set("1");
    genre.set("Fantasy");
    viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
        edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
    assertEquals("Title can't be null",error.get());
    assertEquals("[]",bookList.getValue().toString());
  }

  @Test void null_publisher_gives_error_and_doesnt_add()
      throws SQLException, RemoteException
  {
    title.set("Daugther of Smoke and Bone");
    author.set("Laini Taylor");
    isbn.set("9780316134026");
    year.set("2011");
    edition.set("1");
    genre.set("Fantasy");
    viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
        edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
    assertEquals("Publisher can't be null",error.get());
    assertEquals("[]",bookList.getValue().toString());
  }

  @Test void null_isbn_gives_error_and_doesnt_add()
      throws SQLException, RemoteException
  {
    title.set("Daugther of Smoke and Bone");
    publisher.set("Hodder & Stoughton");
    author.set("Laini Taylor");
    year.set("2011");
    edition.set("1");
    genre.set("Fantasy");
    viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
        edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
    assertEquals("Isbn can't be null",error.get());
    assertEquals("[]",bookList.getValue().toString());
  }

  /*@Test void title_51_characters_gives_error_and_doesnt_add()
      throws SQLException, RemoteException
  {
    title.set("DaugtherofSmokeAndBone, days of blood and starlight");
    publisher.set("Hodder & Stoughton");
    author.set("Laini Taylor");
    isbn.set("9780316134026");
    year.set("2011");
    edition.set("1");
    genre.set("Fantasy");
    viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
        edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
    assertEquals("Title must have less than 50 characters",error.get());
    assertEquals("[]",bookList.getValue().toString());
  }

  @Test void publisher_51_characters_gives_error_and_doesnt_add()
      throws SQLException, RemoteException
  {
    title.set("Daugther of Smoke And Bone");
    publisher.set("Hodder & Stoughtonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
    author.set("Laini Taylor");
    isbn.set("9780316134026");
    year.set("2011");
    edition.set("1");
    genre.set("Fantasy");
    viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
        edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
    assertEquals("Publisher must have less than 50 characters",error.get());
    assertEquals("[]",bookList.getValue().toString());
  }*/

  @Test void future_year_gives_error_and_doesnt_add()
      throws SQLException, RemoteException
  {
    title.set("Daugther of Smoke and Bone");
    publisher.set("Hodder & Stoughton");
    author.set("Laini Taylor");
    isbn.set("9780316134026");
    year.set("2031");
    edition.set("1");
    genre.set("Fantasy");
    viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
        edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
    assertEquals("Invalid date: future date",error.get());
    assertEquals("[]",bookList.getValue().toString());
  }

  @Test void adding_two_books_with_the_same_isbn_gives_error_and_doesnt_add_the_second()
      throws SQLException, RemoteException
  {
    title.set("Daugther of Smoke and Bone");
    publisher.set("Hodder & Stoughton");
    author.set("Laini Taylor");
    isbn.set("9780316134026");
    year.set("2011");
    edition.set("1");
    genre.set("Fantasy");
    viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
        edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
    title.set("Harry Potter and the prisioner of Azkaban");
    publisher.set("Blomsburry");
    author.set("Me");
    isbn.set("9780316134026");
    year.set("2006");
    edition.set("3");
    genre.set("Fantasy");
    viewModel.addBook(new Book(author.get(), title.get(),publisher.get(),isbn.get(),Integer.parseInt(
        edition.get()),Integer.parseInt(year.get()),viewModel.getGenreList()));
    assertEquals("There is already a book with that isbn in the system",error.get());
    assertEquals("[Title=Daugther of Smoke and Bone, author=Laini Taylor, edition=1, year published=2011, isbn=9780316134026]",bookList.getValue().toString());
  }

}
