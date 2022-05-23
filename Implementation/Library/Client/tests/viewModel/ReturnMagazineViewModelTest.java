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

import java.util.ArrayList;

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
    ObservableList<LoanMagazine> observableList = FXCollections.observableList(new ArrayList<>());
    this.loanedMagazines = new SimpleListProperty<>(observableList);
    viewModel.bindMultimediaItemLabel(multimediaItemLabel);
    viewModel.bindErrorLabel(errorLabel);
    viewModel.bindSelectedLibraryUserLabel(selectedLibraryUserLabel);
    viewModel.bindMultimediaItemLabel(multimediaItemLabel);
    viewModel.bindSsnTextField(ssnTextField);
    //viewModel.bindLoanedMagazines(loanedMagazines);

  }
  @Test public void zero(){}
}
