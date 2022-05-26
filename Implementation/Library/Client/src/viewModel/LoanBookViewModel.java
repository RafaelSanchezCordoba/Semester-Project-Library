package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelLoanBook;
import model.LibraryUser;
import model.LoanBook;
import model.MultimediaItem;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

import java.util.ArrayList;

public class LoanBookViewModel implements PropertyChangeListener
{
  private LibraryUser  libraryUser;
  private final ModelLoanBook model ;


  private final StringProperty multimediaItemLabel;
  private final StringProperty errorLabel;
  private final StringProperty selectedMultimediaItemLabel;
  private final StringProperty selectedLibraryUserLabel;
  private final StringProperty ssnTextField;

  private SimpleListProperty<MultimediaItem> availableBooks;

  /**
   * LoanBookViewModel constructor that:
   * set the model
   * ,add the property change listener for adding and removing a book
   * and set JavaFX variables
   * @param model
   * The model library user
   */
  public LoanBookViewModel(ModelLoanBook model){
    this.model= model;
    this.multimediaItemLabel = new SimpleStringProperty("");
    this.errorLabel = new SimpleStringProperty("");
    this.selectedMultimediaItemLabel = new SimpleStringProperty("");
    this.selectedLibraryUserLabel = new SimpleStringProperty("");
    this.ssnTextField = new SimpleStringProperty("");

    ObservableList<MultimediaItem> observableList = FXCollections.observableArrayList( new ArrayList<MultimediaItem>());
    this.availableBooks = new SimpleListProperty<>(observableList);

    model.addPropertyChangeListener("newLoanBook",this);
  }

  /**
   * Property change method that call different methods depends on the event name
   * @param event
   * A library user object passed as an event
   */
  public void propertyChange(PropertyChangeEvent event){
    try
    {
      reset();
    }
    catch ( RemoteException e)
    {

      e.printStackTrace();
    }

  }

  /**
   * Bind the multimedia label
   * @param property
   * A String property
   */
  public void bindMultimediaItemLabel(StringProperty property){
    property.bindBidirectional(multimediaItemLabel);
  }

  /**
   * Bind the error label
   * @param property
   * A String property
   */
  public void bindErrorLabel(StringProperty property){
    property.bindBidirectional(errorLabel);
  }

  /**
   * Bind selected library user label
   * @param property
   * A String property
   */
  public void bindSelectedLibraryUserLabel(StringProperty property){
    property.bindBidirectional(selectedLibraryUserLabel);
  }

  /**
   * Bind
   * @param property
   */
  public void bindSsnTextField(StringProperty property){
    property.bindBidirectional(ssnTextField);
  }

  /**
   * Bind the available book list
   * @param property
   * Object property, multimedia item observable list
   */
  public void bindAvailableBooksList(
      ObjectProperty<ObservableList<MultimediaItem>> property){
    property.bind(availableBooks);
  }

  /**
   * Reset all the JavaFX variables
   */
  public void reset() throws  RemoteException
  {
    setBookList();
    multimediaItemLabel.set("");
    errorLabel.set("");
    selectedMultimediaItemLabel.set("");
   // selectedLibraryUserLabel.set("");
    ssnTextField.set("");
    System.out.println(availableBooks.size());
  }

  /**
   * Set the book list with the available books
   * @throws RemoteException
   */
  public void setBookList()throws RemoteException{
    availableBooks.clear();
    availableBooks.addAll(model.getAvailableBooks());
  }

  /**
   * Create a new book loan
   * @param idBook
   * The unique book identification number
   */
  public void createLoan(int idBook) throws  RemoteException
  {
   if(libraryUser==null){
     errorLabel.set("First fill the ssn");
   }
   else
   {
     model.addLoanBook(new LoanBook(idBook, libraryUser.getSSN()));
   }

  }

  /**
   * Get a user with a specific social security number
   * @param ssn
   * The social security number passed as an argument
   */
  public void getUser(String ssn)
  {
    try
  {
    libraryUser = model.getUser(ssn);
    if(libraryUser==null){
      errorLabel.set("");
    }

    else
    {
      selectedLibraryUserLabel.set(libraryUser.getFirstName() + " " + libraryUser.getLastName());
    }
  }catch (RemoteException e){
      errorLabel.set(e.getMessage());
    }
  }


}


