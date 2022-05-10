package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelBook;
import model.Book;
import model.Genre;
import model.GenreList;
import model.Magazine;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddRemoveBookViewModel implements PropertyChangeListener
{
  private final ModelBook model;
  private final StringProperty titleTextField;
  private final StringProperty publisherTextField;
  private final StringProperty authorTextField;
  private final StringProperty isbnTextField;
  private final StringProperty yearTextField;
  private final StringProperty editionTextField;
  private final StringProperty searchTextField;
  private final StringProperty errorLabel;
  private final SimpleListProperty<Book> bookList;
  private final SimpleListProperty<Genre> genreList;

  public AddRemoveBookViewModel(ModelBook model) throws RemoteException
  {
    this.model = model;
    this.titleTextField = new SimpleStringProperty("");
    this.publisherTextField = new SimpleStringProperty("");
    this.authorTextField = new SimpleStringProperty("");
    this.isbnTextField = new SimpleStringProperty("");
    this.yearTextField = new SimpleStringProperty("");
    this.editionTextField = new SimpleStringProperty("");
    this.searchTextField = new SimpleStringProperty("");
    this.errorLabel = new SimpleStringProperty("");
    ObservableList<Book> observableList = FXCollections.observableArrayList(
        new ArrayList<>());
    this.bookList = new SimpleListProperty<>(observableList);
    this.genreList = new SimpleListProperty<>();

    model.addPropertyChangeListener("newBook", this);
    model.addPropertyChangeListener("removeBook", this);
  }

  public void search()
  {

  }

  public void setGenreList() throws RemoteException, SQLException {
    genreList.clear();

    for (int i = 0; i < model.getGenreList().getSize(); i++)
    {
      genreList.add(model.getGenreList().getGenre(i));
    }

  }

  public void setBookList() throws RemoteException, SQLException
  {
    bookList.clear();
    for (int i = 0; i < model.getBookList().size(); i++)
    {
      bookList.add(model.getBookList().get(i));
    }
  }

  public void addBook(Book book) throws RemoteException, SQLException
  {
    model.addBook(book);
  }

  public void removeBook(int id) throws RemoteException, SQLException
  {
    model.removeBook(id);

  }

  public void reset() throws SQLException, RemoteException
  {
    setBookList();

    titleTextField.set("");
    publisherTextField.set("");
    authorTextField.set("");
    isbnTextField.set("");
    yearTextField.set("");
    editionTextField.set("");
    searchTextField.set("");
  }

  public void bindTitleTextField(StringProperty property)
  {
    property.bindBidirectional(titleTextField);
  }

  public void bindPublisherTextField(StringProperty property)
  {
    property.bindBidirectional(publisherTextField);
  }

  public void bindAuthorTextField(StringProperty property)
  {
    property.bindBidirectional(authorTextField);
  }

  public void bindISBNTextField(StringProperty property)
  {
    property.bindBidirectional(isbnTextField);
  }

  public void bindYearTextField(StringProperty property)
  {
    property.bindBidirectional(yearTextField);
  }

  public void bindEditionTextField(StringProperty property)
  {
    property.bindBidirectional(editionTextField);
  }

  public void bindSearchTextField(StringProperty property)
  {
    property.bindBidirectional(searchTextField);
  }

  public void bindErrorLabel(StringProperty property)
  {
    property.bind(errorLabel);
  }

  public void bindBookListView(ObjectProperty<ObservableList<Book>> property)
  {
    property.bindBidirectional(bookList);
  }

  public void bindBookListViewForTesting(SimpleListProperty<Book> property)
  {
    property.bindBidirectional(bookList);
  }

  public void bindGenreList(ObjectProperty property) {
    property.bindBidirectional(genreList);
  }
  public void bindGenreListForTest(SimpleListProperty<Genre> property) {
    property.bind(genreList);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("newBook"))
    {
      bookList.add((Book) evt.getNewValue());
    }
    else if (evt.getPropertyName().equals("removeBook"))
    {
      for (int i = 0; i < bookList.size(); i++)
      {
        if (bookList.get(i).getId() == (int) evt.getNewValue())
        {
          bookList.remove(bookList.get(i));
          break;
        }
      }
    }
  }
}
