package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelLoanBook;
import model.Book;
import model.LibraryUser;
import model.LoanBook;
import model.MultimediaItem;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Collection;

public class LoanBookViewModel implements PropertyChangeListener
{
  private LibraryUser  libraryUser;
  private final ModelLoanBook model ;


  private final StringProperty multimediaItemLabel;
  private final StringProperty errorLabel;
  private final StringProperty selectedMultimediaItemLabel;
  private final StringProperty selectedLibraryUserLabel;
  private final StringProperty ssnTextField;

  private SimpleListProperty<Book> availableBooks;

  public LoanBookViewModel(ModelLoanBook model){
    this.model= model;
    this.multimediaItemLabel = new SimpleStringProperty("");
    this.errorLabel = new SimpleStringProperty("");
    this.selectedMultimediaItemLabel = new SimpleStringProperty("");
    this.selectedLibraryUserLabel = new SimpleStringProperty("");
    this.ssnTextField = new SimpleStringProperty("");

    ObservableList<Book> observableList = FXCollections.observableArrayList( new ArrayList<Book>());
    this.availableBooks = new SimpleListProperty<>(observableList);

    model.addPropertyChangeListener("addLoanBook", this);
  }

  public void bindMultimediaItemLabel(StringProperty property){
    property.bindBidirectional(multimediaItemLabel);
  }

  public void bindErrorLabel(StringProperty property){
    property.bindBidirectional(errorLabel);
  }



  public void bindSelectedLibraryUserLabel(StringProperty property){
    property.bindBidirectional(selectedLibraryUserLabel);
  }

  public void bindMultimediaItemSearchTextField(StringProperty property){
    property.bindBidirectional(multimediaItemLabel);
  }

  public void bindSsnTextField(StringProperty property){
    property.bindBidirectional(ssnTextField);
  }

  public void bindAvailableBooksList(
      ObjectProperty<ObservableList<Book>> property){
    property.bind(availableBooks);
  }

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

  public void setBookList()throws RemoteException{
    availableBooks.clear();
    availableBooks.addAll(model.getAvailableBooks());
  }

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

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    try {
      reset();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    availableBooks.remove(evt.getNewValue());

    System.out.println(evt.getNewValue().toString());
  }
}


