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

  /**
   * AddRemoveBookViewModel constructor that:
   * set the model
   * ,add the property change listener for adding and removing a book
   * and set JavaFX variables
   * @param model
   * The model book
   * @throws RemoteException
   */
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

  /**
   * Not implemented
   */
  public void search()
  {

  }

  /**
   * Check if the information passed is correct if not print out a specific error message
   * @return
   * True if there are any error, false if not
   * @throws RemoteException
   */
  private boolean errorCheck() throws RemoteException
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

  /**
   * Check if the year set it by the user is a future year
   * @return
   * True if is a future year, false if not
   */
  private boolean futureYearCheck()
  {
    CurrentTime now=new CurrentTime();
    String year=now.getFormattedIsoDate().substring(0,4);
    return Integer.parseInt(yearTextField.get())>Integer.parseInt(year);
  }

  /**
   * Check if the isbn is unique
   * @return
   * True if is not unique, false if it is
   * @throws RemoteException
   */
  private boolean duplicateIsbnCheck() throws  RemoteException
  {
    for (int i=0;i<model.getBookList().size();i++)
    {
      if (isbnTextField.get().equals(model.getBookList().get(i).getIsbn()))
      {
        return true;
      }
    } return false;
  }

  /**
   * Check if the genre is unique
   * @param genre
   * The genre to check
   * @return
   * True if is not unique, false if it is
   */
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

  /**
   * Get genre list method
   * @return
   * The genre list
   * @throws RemoteException
   */
  public GenreList getGenreList() throws  RemoteException {
    GenreList genres = new GenreList();
    for (int i = 0; i < selectedGenreList.size(); i++) {
      genres.addGenre(selectedGenreList.get(i));
    }
    return genres;
  }

  /**
   * Add the selected genre to the selected genre list
   * @param genre
   * The genre selected
   */
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

  /**
   * Remove the selected genre from the selected genre list
   * @param genre
   * The genre selected
   */
  public void removeFromSelectedGenreList(Genre genre) {
    selectedGenreList.remove(genre);
  }

  public void setGenreList() throws RemoteException {
    genreList.clear();

    for (int i = 0; i < model.getGenreList().getSize(); i++)
    {
      genreList.add(model.getGenreList().getGenre(i));
    }
  }

  /**
   * Set the book list with all the books
   */
  public void setBookList() throws RemoteException
  {
    bookList.clear();
    bookList.addAll(model.getBookList());
  }

  /**
   * Add book method
   * @param book
   * The book passed as an argument
   */
  public void addBook(Book book) throws RemoteException
  {
    errorLabel.set("");
    if (!errorCheck()){
    model.addBook(book);}
    reset();
  }

  /**
   * Remove a book with a specific id passed as an argument
   * @param id
   * The unique identification number
   * @throws RemoteException
   * If the book is already lent
   */
  public void removeBook(int id) throws RemoteException
  {
    try
    {
      model.removeBook(id);
      errorLabel.set("");
    }catch (RemoteException e)
    {
      errorLabel.set("cannot remove this book because is currently being lent");
    }
  }

  /**
   * Reset all the JavaFX variables
   * @throws RemoteException
   */
  public void reset() throws  RemoteException
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

  /**
   * Bind the title text field
   * @param property
   * A String property
   */
  public void bindTitleTextField(StringProperty property)
  {
    property.bindBidirectional(titleTextField);
  }

  /**
   * Bind the publisher text field
   * @param property
   * A String property
   */
  public void bindPublisherTextField(StringProperty property)
  {
    property.bindBidirectional(publisherTextField);
  }

  /**
   * Bind the author text field
   * @param property
   * A String property
   */
  public void bindAuthorTextField(StringProperty property)
  {
    property.bindBidirectional(authorTextField);
  }

  /**
   * Bind the isbn text field
   * @param property
   * A String property
   */
  public void bindISBNTextField(StringProperty property)
  {
    property.bindBidirectional(isbnTextField);
  }

  /**
   * Bind the year text field
   * @param property
   * A String property
   */
  public void bindYearTextField(StringProperty property)
  {
    property.bindBidirectional(yearTextField);
  }

  /**
   * Bind the edition text field
   * @param property
   * A String property
   */
  public void bindEditionTextField(StringProperty property)
  {
    property.bindBidirectional(editionTextField);
  }

  /**
   * Bind the error label
   * @param property
   * A String property
   */
  public void bindErrorLabel(StringProperty property)
  {
    property.bindBidirectional(errorLabel);
  }

  /**
   * Bind the book list
   * @param property
   * Object property, observable list of books
   */
  public void bindBookListView(ObjectProperty<ObservableList<Book>> property)
  {
    property.bindBidirectional(bookList);
  }

  /**
   * Bind the book list for testing
   * @param property
   * Simple list property of type book
   */
  public void bindBookListViewForTesting(SimpleListProperty<Book> property)
  {
    property.bind(bookList);
  }

  /**
   * Bind the genre list
   * @param property
   * Object property, observable list of genres
   */
  public void bindGenreList(ObjectProperty<ObservableList<Genre>> property) {
    property.bindBidirectional(genreList);
  }

  /**
   * Bind the selected genre list
   * @param property
   * Object property, observable list of genres
   */
  public void bindSelectedGenreList(ObjectProperty<ObservableList<Genre>> property) {
    property.bindBidirectional(selectedGenreList);
  }

  /**
   * Bind the genre list for testing
   * @param property
   * Simple list property of genre
   */
  public void bindGenreListForTest(SimpleListProperty<Genre> property) {
    property.bind(genreList);
  }

  /**
   * Bind the genre list for testing
   * @param property
   * Simple list property of genre
   */
  public void bindSelectedGenreListForTest(SimpleListProperty<Genre> property) {
    property.bind(selectedGenreList);
  }

  /**
   * Property change method that call different methods depends on the event name
   * @param evt
   * A book object passed as an event
   */
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
