package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelLoanMagazine;
import model.Librarian;
import model.LibraryUser;
import model.LoanMagazine;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnMagazineViewModel implements PropertyChangeListener {
    private final ModelLoanMagazine model;
    private LibraryUser user;

    private final StringProperty multimediaItemLabel;
    private final StringProperty errorLabel;
    private final StringProperty selectedMultimediaItemLabel;
    private final StringProperty selectedLibraryUserLabel;
    private final StringProperty  multimediaItemSearchTextField;
    private final StringProperty ssnTextField;
    private SimpleListProperty<LoanMagazine> loanedMagazines ;

    public ReturnMagazineViewModel(ModelLoanMagazine model) {
        this.model= model;
        this.multimediaItemLabel = new SimpleStringProperty("");
        this.errorLabel = new SimpleStringProperty("");
        this.selectedLibraryUserLabel = new SimpleStringProperty("");
        this.selectedMultimediaItemLabel = new SimpleStringProperty("");
        this.multimediaItemSearchTextField = new SimpleStringProperty("");
        this.ssnTextField = new SimpleStringProperty();
        ObservableList<LoanMagazine> observableList = FXCollections.observableArrayList( new ArrayList<LoanMagazine>());
        this.loanedMagazines = new SimpleListProperty<>(observableList);

        model.addPropertyChangeListener("removeLoanMagazine", this);
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

    public void bindLoanedMagazines(ObjectProperty<ObservableList<LoanMagazine>> property){
        property.bind(loanedMagazines);
    }
    public void bindLoanedMagazinesForTest(SimpleListProperty<LoanMagazine> property){
        property.bind(loanedMagazines);
    }

    public void reset() throws SQLException, RemoteException {
        multimediaItemLabel.set("");
        errorLabel.set("");
        selectedMultimediaItemLabel.set("");
        //  selectedLibraryUserLabel.set("");
        multimediaItemSearchTextField.set("");
        ssnTextField.set("");
    }

    public void setLoanedMagazines() throws RemoteException, SQLException{
        loanedMagazines.clear();
        loanedMagazines.addAll(model.getUserLoans(user.getSSN()));

    }

    public void getUser(String ssn){
        try
        {
            user = model.getUser(ssn);
            if(user==null){
                errorLabel.set("Library User does  not exit");
            }

            else{
                selectedLibraryUserLabel.set(user.getFirstName() + "|" + user.getLastName());
            }

        }catch (RemoteException e){
            System.out.println("error");
            errorLabel.set(e.getMessage());
        }
    }

    public void returnMagazine(int id_loan) throws RemoteException {
        if(user==null)
        {
            errorLabel.set("First fill the ssn");
        }
        else
        {
            model.returnMagazine(id_loan);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            reset();
            setLoanedMagazines();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
