package viewModel;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.FakeModelManagerLoanMagazine;
import mediator.ModelLoanMagazine;
import mediator.ModelManagerLoanMagazine;
import model.Librarian;
import model.MultimediaItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LoanMagazineViewModelTest {
    private LoanMagazineViewModel viewModel;
    private ModelLoanMagazine model;
    private StringProperty multimediaItem;
    private StringProperty error;
    private StringProperty selectedMultimediaItem;
    private StringProperty selectedLibraryUser;
    private StringProperty multimediaItemSearch;
    private StringProperty ssn;
    private SimpleListProperty<MultimediaItem> availaibleMagazines;



    @BeforeEach void setUp() {
        model = new FakeModelManagerLoanMagazine();
        viewModel = new LoanMagazineViewModel(model);
        this.multimediaItem = new SimpleStringProperty("");
        this.error = new SimpleStringProperty("");
        this.selectedMultimediaItem = new SimpleStringProperty("");
        this.selectedLibraryUser = new SimpleStringProperty("");
        this.multimediaItemSearch = new SimpleStringProperty("");
        this.ssn = new SimpleStringProperty("");
        ObservableList<MultimediaItem> observableListMultimediaItem = FXCollections.observableArrayList(new ArrayList<>());
        availaibleMagazines =new SimpleListProperty<>(observableListMultimediaItem);

        viewModel.bindMultimediaItemLabel(multimediaItem);
        viewModel.bindErrorLabel(error);
        viewModel.bindSelectedMultimediaItemLabel(selectedMultimediaItem);
        viewModel.bindSelectedLibraryUserLabel(selectedLibraryUser);
        viewModel.bindMultimediaItemLabel(multimediaItemSearch);
        viewModel.bindSsnTextField(ssn);

    }

//    @Test void new_object_has_empty_fields(){
//        assertEquals("",multimediaItem.get());
//        assertEquals("",error.get());
//        assertEquals("",selectedMultimediaItem.get());
//        assertEquals("",selectedLibraryUser.get());
//        assertEquals("",multimediaItem.get());
//        assertEquals("",ssn.get());
//        assertEquals("[]",availaibleMagazines.get().toString());
//    }

    @Test void filling_fields_doesnt_change_error_or_list(){
        MultimediaItem m1 = new MultimediaItem("Wiedźmin","Supernova");
        Librarian l1 = new Librarian("123456789","admin","Franciszek","Nurkiewicz");

        multimediaItem.set(m1.toString());
        assertEquals("",error.get());
        selectedMultimediaItem.set(m1.toString());
        selectedLibraryUser.set(l1.toString());
        ssn.set(l1.getSsn());
        assertEquals("[]",availaibleMagazines.get().toString());
    }


}