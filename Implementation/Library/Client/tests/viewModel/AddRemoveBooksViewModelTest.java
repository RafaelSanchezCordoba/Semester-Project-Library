package viewModel;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.FakeModelManagerBook;
import mediator.ModelBook;
import model.Book;
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
    ObservableList<Book> observableList = FXCollections.observableArrayList(new ArrayList<>());
    this.bookList = new SimpleListProperty<>(observableList);

    viewModel.bindTitleTextField(title);
    viewModel.bindAuthorTextField(author);
    viewModel.bindEditionTextField(edition);
    viewModel.bindErrorLabel(error);
    viewModel.bindISBNTextField(isbn);
    viewModel.bindPublisherTextField(publisher);
    viewModel.bindSearchTextField(search);
    viewModel.bindYearTextField(year);
    viewModel.bindBookListViewForTesting(bookList);
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

  @Test void add_adds_the_book_one_genre() throws SQLException, RemoteException
  {
    title.set("Daugther of Smoke and Bone");
    publisher.set("Hodder & Stoughton");
    author.set("Laini Taylor");
    isbn.set("9780316134026");
    year.set("2011");
    edition.set("1");
    genre.set("Fantasy");

    //wait until genres is working!!

  }


}
