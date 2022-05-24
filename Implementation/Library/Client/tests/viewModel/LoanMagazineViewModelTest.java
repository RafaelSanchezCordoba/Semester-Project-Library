package viewModel;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.FakeModelManagerLoanMagazine;
import mediator.ModelLoanMagazine;
import model.Librarian;
import model.Magazine;
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

    @Test void new_object_has_empty_fields(){
        assertEquals("",multimediaItem.get());
        assertEquals("",error.get());
        assertEquals("",selectedMultimediaItem.get());
        assertEquals("",selectedLibraryUser.get());
        assertEquals("ListProperty [value: []]",availaibleMagazines.toString());
    }

    @Test void filling_fields_doesnt_change_error_or_list(){
        MultimediaItem m1 = new MultimediaItem("New Your Times","NYC");
        Librarian l1 = new Librarian("123456789","admin","Franciszek","Nurkiewicz");
        Magazine ma1 = new Magazine(m1.getTitle(),m1.getPublisher(),1,"News", java.sql.Date.valueOf("2022-12-1"));
        selectedMultimediaItem.set(ma1.toString());
        selectedLibraryUser.set(l1.toString());
        assertEquals("MultimediaItem{id=0, title='New Your Times', publisher='NYC'}",m1.toString());
    }

    @Test void add_one_record_to_loan_magazine_view_by_one_librarian(){
        MultimediaItem m1 = new MultimediaItem("New Your Times","NYC");
        Magazine ma1 = new Magazine(m1.getTitle(),m1.getPublisher(),1,"News", java.sql.Date.valueOf("2022-12-1"));
        Librarian l1 = new Librarian("123456789","admin","Franciszek","Nurkiewicz");
        selectedMultimediaItem.set(ma1.toString());
        selectedLibraryUser.set(l1.toString());
        assertEquals("MultimediaItem{id=0, title='New Your Times', publisher='NYC'}",m1.toString());
    }

    @Test void add_two_record_to_loan_magazine_view_model_by_one_librarian(){
        MultimediaItem m1 = new MultimediaItem("New Your Times","NYC");
        MultimediaItem m2 = new MultimediaItem("Forbes","NYC");
        Magazine ma1 = new Magazine(m1.getTitle(),m1.getPublisher(),1,"News", java.sql.Date.valueOf("2022-12-1"));
        Magazine ma2 = new Magazine(m2.getTitle(),m2.getPublisher(),2,"Finances", java.sql.Date.valueOf("2022-05-05"));
        Librarian l1 = new Librarian("123456789","admin","Franciszek","Nurkiewicz");
        assertEquals("123456789",l1.getSsn());
        selectedMultimediaItem.set(ma1.toString());
        assertEquals("StringProperty [value: MultimediaItem{id=0, title='New Your Times', publisher='NYC'}]",selectedMultimediaItem.toString());
        selectedMultimediaItem.set(ma2.toString());
        assertEquals("StringProperty [value: MultimediaItem{id=0, title='Forbes', publisher='NYC'}]",selectedMultimediaItem.toString());
    }
}