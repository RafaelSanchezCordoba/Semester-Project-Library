package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelLoanBook;
import mediator.ModelManagerLoanBook;
import model.LibraryUser;
import model.LoanBook;
import model.LoanMagazine;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

import java.util.ArrayList;

public class ReturnBookViewModel implements PropertyChangeListener
{
  private final ModelLoanBook model;
  private LibraryUser user;

  private final StringProperty multimediaItemLabel;
  private final StringProperty errorLabel;
  private final StringProperty selectedMultimediaItemLabel;
  private final StringProperty selectedLibraryUserLabel;
  private final StringProperty ssnTextField;
  private SimpleListProperty<LoanBook> loanedBooks;

  public ReturnBookViewModel(ModelLoanBook model){
    this.model = model;
    this.multimediaItemLabel = new SimpleStringProperty("");
    this.errorLabel = new SimpleStringProperty("");
    this.selectedMultimediaItemLabel = new SimpleStringProperty("");
    this.selectedLibraryUserLabel = new SimpleStringProperty("");
    this.ssnTextField = new SimpleStringProperty();
    ObservableList<LoanBook> observableList = FXCollections.observableArrayList( new ArrayList<LoanBook>());
    this.loanedBooks = new SimpleListProperty<>(observableList);

    model.addPropertyChangeListener("removeLoanBook",this);
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



  public void bindSsnTextField(StringProperty property){
    property.bindBidirectional(ssnTextField);
  }

  public void bindLoanedBooks(
      ObjectProperty<ObservableList<LoanBook>> property){
    property.bind(loanedBooks);
  }
  public void reset() throws  RemoteException
  {
    multimediaItemLabel.set("");
    errorLabel.set("");
    selectedMultimediaItemLabel.set("");
    //  selectedLibraryUserLabel.set("");
    ssnTextField.set("");
  }

  public void setLoanedBooks() throws RemoteException{
    loanedBooks.clear();
    loanedBooks.addAll(model.getUserBookLoans(user.getSSN()));

  }
  public void getUser(String ssn){
    try
    {
      user = model.getUser(ssn);
      if(user==null){
        errorLabel.set("Library User does not exist");
      }

      else{
        selectedLibraryUserLabel.set(user.getFirstName() + " " + user.getLastName());
      }

    }catch (RemoteException e){
      System.out.println("error");
      errorLabel.set(e.getMessage());
    }
  }
  public void returnBook(int id_loan) throws RemoteException {
    if(user==null)
    {
      errorLabel.set("First fill the ssn");
    }
    else
    {
      model.returnBook(id_loan);
    }
  }
  @Override
  public void propertyChange(PropertyChangeEvent evt)
  {
    try {
     // reset();
      setLoanedBooks();
    } catch ( RemoteException e) {
      e.printStackTrace();
    }

  }
}
