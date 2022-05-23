package viewModel;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.*;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturnMagazineViewModelTest
{
  private StringProperty multimediaItemLabel;
  private StringProperty errorLabel;
  private StringProperty selectedMultimediaItemLabel;
  private StringProperty selectedLibraryUserLabel;
  private StringProperty  multimediaItemSearchTextField;
  private StringProperty ssnTextField;
  private SimpleListProperty<LoanMagazine> loanedMagazines ;
  private ReturnMagazineViewModel viewModel;
  private LibraryUser user;

  @BeforeEach public void setUp(){
    ModelLoanMagazine fakeModel=new FakeModelManagerLoanMagazines();
    viewModel=new ReturnMagazineViewModel(fakeModel);
    multimediaItemLabel=new SimpleStringProperty();
    errorLabel=new SimpleStringProperty();
    selectedLibraryUserLabel=new SimpleStringProperty();
    selectedMultimediaItemLabel=new SimpleStringProperty();
    multimediaItemSearchTextField=new SimpleStringProperty();
    ssnTextField= new SimpleStringProperty();
    ObservableList<LoanMagazine> observableList = FXCollections.observableArrayList( new ArrayList<LoanMagazine>());
    this.loanedMagazines = new SimpleListProperty<>(observableList);
    viewModel.bindMultimediaItemLabel(multimediaItemLabel);
    viewModel.bindErrorLabel(errorLabel);
    viewModel.bindSelectedLibraryUserLabel(selectedLibraryUserLabel);
    viewModel.bindMultimediaItemLabel(multimediaItemLabel);
    viewModel.bindSsnTextField(ssnTextField);
    viewModel.bindLoanedMagazinesForTest(loanedMagazines);

  }
  //Return magazine

  //Zero
  @Test public void zeroReturnMagazine(){

  }
  //One
  @Test  public void oneReturnMagazine(){
    try
    {
      viewModel.returnMagazine(3);
      //assertEquals();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }
  //Many
  @Test public void manyReturnMagazine(){

  }
  //Boundary
  @Test public void boundaryReturnMagazine(){

  }
  //Exception
  @Test public void exceptionReturnMagazine(){
    //should i create a method to set user to null   and  then check?
  }

  //GetUser
  //Zero
  @Test public void zeroGetUser(){

  }
  //One
  @Test  public void oneGetUser(){
    ssnTextField.set("1234567891234");
    viewModel.getUser(ssnTextField.getValue());
    assertEquals("Rosa|Lopez",selectedLibraryUserLabel.get());
  }
  //Many
  @Test public void manyGetUser(){

  }
  //Boundary
  @Test public void boundaryGetUser(){

  }
  //Exception
  @Test public void exceptionGetUser(){
    ssnTextField.set("1234567891239");
    viewModel.getUser(ssnTextField.getValue());
    assertEquals("Library User does  not exit",errorLabel.get());
  }
}
