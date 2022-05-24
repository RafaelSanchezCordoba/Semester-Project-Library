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
import java.sql.SQLException;
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

  public void propertyChange(PropertyChangeEvent event){
    try
    {
      reset();
    }
    catch (SQLException e)
    {

      e.printStackTrace();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

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
      ObjectProperty<ObservableList<MultimediaItem>> property){
    property.bind(availableBooks);
  }

  public void reset() throws SQLException, RemoteException
  {
    setBookList();
    multimediaItemLabel.set("");
    errorLabel.set("");
    selectedMultimediaItemLabel.set("");
   // selectedLibraryUserLabel.set("");
    ssnTextField.set("");
    System.out.println(availableBooks.size());
  }

  public void setBookList()throws RemoteException,SQLException{
    availableBooks.clear();
    availableBooks.addAll(model.getAvailableBooks());
  }

  public void createLoan(int idBook) throws SQLException, RemoteException
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
      errorLabel.set("Library User does  not exit");
    }

    else
    {
      selectedLibraryUserLabel.set(libraryUser.getFirstName() + "|" + libraryUser.getLastName());
    }
  }catch (RemoteException e){
      errorLabel.set(e.getMessage());
    }
  }


}


