package viewModel;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.FakeModelManagerLoanBook;
import mediator.ModelLoanBook;
import model.Book;
import model.Librarian;
import model.MultimediaItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LoanBookViewModelTest {
    private LoanBookViewModel viewModel;
    private ModelLoanBook model;
    private StringProperty multimediaItem;
    private StringProperty error;
    private StringProperty selectedMultimediaItem;
    private StringProperty selectedLibraryUser;
    private StringProperty ssn;
    private SimpleListProperty<MultimediaItem> availaibleBooks;

    @BeforeEach void setUp() {
        model = new FakeModelManagerLoanBook();
        viewModel = new LoanBookViewModel(model);
        this.multimediaItem = new SimpleStringProperty("");
        this.error = new SimpleStringProperty("");
        this.selectedMultimediaItem = new SimpleStringProperty("");
        this.selectedLibraryUser = new SimpleStringProperty("");
        this.ssn = new SimpleStringProperty("");
        ObservableList<MultimediaItem> observableListMultimediaItem = FXCollections.observableArrayList(new ArrayList<>());
        availaibleBooks = new SimpleListProperty<>(observableListMultimediaItem);

        viewModel.bindMultimediaItemLabel(multimediaItem);
        viewModel.bindErrorLabel(error);
        viewModel.bindSelectedLibraryUserLabel(selectedLibraryUser);
        viewModel.bindMultimediaItemLabel(selectedMultimediaItem);
        viewModel.bindSsnTextField(ssn);
    }

    @Test void new_object_has_empty_fields(){
        assertEquals("",multimediaItem.get());
        assertEquals("",error.get());
        assertEquals("",selectedMultimediaItem.get());
        assertEquals("",selectedLibraryUser.get());
        assertEquals("",ssn.get());
        assertEquals("ListProperty [value: []]",availaibleBooks.toString());
    }

    @Test void add_one_record_to_loan_book_by_one_librarian(){
        Librarian l1 = new Librarian("123456789123","admin","Franciszek","Nurkiewicz");
        MultimediaItem m1 = new MultimediaItem("Wiedźmin","Supernova");
        Book b1 = new Book(m1.getTitle(),m1.getPublisher(),"123456789A",1,1992);
        b1.setAuthor("Andrzej Sapkowski");
        selectedMultimediaItem.set(b1.toString());
        selectedLibraryUser.set(l1.toString());
        assertEquals("Title=Wiedźmin, author=Andrzej Sapkowski, edition=1, year published=1992, isbn=123456789A",selectedMultimediaItem.get());
        assertEquals(l1.getSsn(),"123456789123");
    }

    @Test void add_two_record_to_loan_book_by_one_librarian(){
        MultimediaItem m1 = new MultimediaItem("Wiedźmin","Supernova");
        MultimediaItem m2 = new MultimediaItem("Jenseits von gut und böse","PWN");
        Book b1 = new Book("Andrzej Sapkowski",m1.getTitle(),m1.getPublisher(),1,1992);
        Book b2 = new Book("Friedrich Nietzsche",m2.getTitle(),m2.getPublisher(),1,1886);
        Librarian l1 = new Librarian("123456789","admin","Franciszek","Nurkiewicz");
        assertEquals("123456789",l1.getSsn());
        assertEquals("Title=Andrzej Sapkowski, author=Anonymous, edition=1, year published=1992, isbn=Supernova",b1.toString());
    }
}