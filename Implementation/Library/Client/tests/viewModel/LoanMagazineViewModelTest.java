package viewModel;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.FakeModelManagerLoanMagazine;
import mediator.ModelLoanMagazine;
import model.Librarian;
import model.MultimediaItem;
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
        viewModel.bindSelectedLibraryUserLabel(selectedMultimediaItem);
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

    @Test void add_one_record_to_loan_magazine_view_by_one_librarian(){
        MultimediaItem m1 = new MultimediaItem("Wiedźmin","Supernova");
        Librarian l1 = new Librarian("123456789","admin","Franciszek","Nurkiewicz");
        multimediaItem.set(m1.toString());
        selectedMultimediaItem.set(m1.toString());
        selectedLibraryUser.set(l1.toString());
        assertEquals("MultimediaItem{id=0, title='Wiedźmin', publisher='Supernova'}",m1.toString());
    }

    @Test void add_two_record_to_loan_magazine_view_model_by_one_librarian(){
        MultimediaItem m1 = new MultimediaItem("Wiedźmin","Supernova");
        MultimediaItem m2 = new MultimediaItem("Dune","Hodder & Stoughton");
        Librarian l1 = new Librarian("123456789","admin","Franciszek","Nurkiewicz");
        multimediaItem.set(m1.toString());
        multimediaItem.set(m2.toString());
        selectedMultimediaItem.set(m1.toString());
        selectedMultimediaItem.set(m2.toString());
        selectedLibraryUser.set(l1.toString());
        assertEquals("MultimediaItem{id=0, title='Wiedźmin', publisher='Supernova'} MultimediaItem{id=0, title='Dune', publisher='Hodder & Stoughton'}",m1 + " " + m2);
    }

//    @Test void add_two_record_on_objects(){
//        MultimediaItem m1 = new MultimediaItem("Wiedźmin","Supernova");
//        MultimediaItem m2 = new MultimediaItem("Dune","Hodder & Stoughton");
//        Librarian l1 = new Librarian("123456789","admin","Franciszek","Nurkiewicz");
//        multimediaItem.set(m1.toString());
//        assertEquals(m1.toString(),"MultimediaItem{id=0, title='Wiedźmin', publisher='Supernova'}");
//    }


}