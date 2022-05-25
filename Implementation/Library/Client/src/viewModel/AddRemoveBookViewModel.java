package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelBook;
import model.*;

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
  private final StringProperty errorLabel;
  private final SimpleListProperty<Book> bookList;
  private final SimpleListProperty<Genre> genreList;
  private final SimpleListProperty<Genre> selectedGenreList;

  public AddRemoveBookViewModel(ModelBook model) throws RemoteException
  {
    this.model = model;
    this.titleTextField = new SimpleStringProperty("");
    this.publisherTextField = new SimpleStringProperty("");
    this.authorTextField = new SimpleStringProperty("");
    this.isbnTextField = new SimpleStringProperty("");
    this.yearTextField = new SimpleStringProperty("");
    this.editionTextField = new SimpleStringProperty("");
    this.errorLabel = new SimpleStringProperty("");
    ObservableList<Book> observableListBook = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<Genre> observableListGenre = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<Genre> observableListSelectedGenre = FXCollections.observableArrayList(new ArrayList<>());
    this.bookList = new SimpleListProperty<>(observableListBook);
    this.genreList = new SimpleListProperty<>(observableListGenre);
    this.selectedGenreList = new SimpleListProperty<>(observableListSelectedGenre);

    model.addPropertyChangeListener("newBook", this);
    model.addPropertyChangeListener("removeBook", this);
  }

  public void search()
  {

  }

  private boolean errorCheck() throws SQLException, RemoteException
  {
    if (titleTextField.get().equals(""))
    {
      errorLabel.set("Title can't be null");
      return true;
    }
    else if (publisherTextField.get().equals(""))
    {
      errorLabel.set("Publisher can't be null");
      return true;
    }
    else if (isbnTextField.get().equals(""))
    {
      errorLabel.set("Isbn can't be null");
      return true;
    }
    /*else if (titleTextField.get().length()>50)
    {
      errorLabel.set("Title must have less than 50 characters");
      return true;
    }
    else if (publisherTextField.get().length()>50)
    {
      errorLabel.set("Publisher must have less than 50 characters");
      return true;
    }*/
    else if (futureYearCheck())
    {
      errorLabel.set("Invalid date: future date");
      return true;
    }
    else if (duplicateIsbnCheck()){
      errorLabel.set("There is already a book with that isbn in the system");
      return true;
    }
    return false;
  }
  private boolean futureYearCheck()
  {
    CurrentTime now=new CurrentTime();
    String year=now.getFormattedIsoDate().substring(0,4);
    return Integer.parseInt(yearTextField.get())>Integer.parseInt(year);
  }

  private boolean duplicateIsbnCheck() throws SQLException, RemoteException
  {
    for (int i=0;i<model.getBookList().size();i++)
    {
      if (isbnTextField.get().equals(model.getBookList().get(i).getIsbn()))
      {
        return true;
      }
    } return false;
  }

  private boolean duplicatedGenreCheck(Genre genre)
  {
    for (int i=0;i<selectedGenreList.get().size();i++)
    {
      if (genre.getGenre().equals(selectedGenreList.get(i).getGenre()))
      {
        return true;
      }
    }
    return false;
  }

  public GenreList getGenreList() throws SQLException, RemoteException {
    GenreList genres = new GenreList();
    for (int i = 0; i < selectedGenreList.size(); i++) {
      genres.addGenre(selectedGenreList.get(i));
    }
    return genres;
  }

  public void addGenreToSelectedGenreList(Genre genre) {
    if (!duplicatedGenreCheck(genre))
    {
      selectedGenreList.add(genre);
      if (errorLabel.get().equals("Duplicated genre"))
      {
        errorLabel.set("");
      }
    }
    else
    {
      errorLabel.set("Duplicated genre");
    }
  }

  public void removeFromSelectedGenreList(Genre genre) {
    selectedGenreList.remove(genre);
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
    bookList.addAll(model.getBookList());
  }

  public void addBook(Book book) throws RemoteException, SQLException
  {
    if (!errorCheck()){
    model.addBook(book);}
    reset();
  }

  public void removeBook(int id) throws RemoteException, SQLException
  {
    model.removeBook(id);
  }

  public void reset() throws SQLException, RemoteException
  {
    setBookList();
    setGenreList();

    titleTextField.set("");
    publisherTextField.set("");
    authorTextField.set("");
    isbnTextField.set("");
    yearTextField.set("");
    editionTextField.set("");
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

  public void bindErrorLabel(StringProperty property)
  {
    property.bindBidirectional(errorLabel);
  }

  public void bindBookListView(ObjectProperty<ObservableList<Book>> property)
  {
    property.bindBidirectional(bookList);
  }

  public void bindBookListViewForTesting(SimpleListProperty<Book> property)
  {
    property.bind(bookList);
  }

  public void bindGenreList(ObjectProperty<ObservableList<Genre>> property) {
    property.bindBidirectional(genreList);
  }

  public void bindSelectedGenreList(ObjectProperty<ObservableList<Genre>> property) {
    property.bindBidirectional(selectedGenreList);
  }

  public void bindGenreListForTest(SimpleListProperty<Genre> property) {
    property.bind(genreList);
  }
  public void bindSelectedGenreListForTest(SimpleListProperty<Genre> property) {
    property.bind(selectedGenreList);
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
