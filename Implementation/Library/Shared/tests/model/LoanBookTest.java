package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoanBookTest {
    private LoanBook loanBook;
    private Date startDate = new Date(2022-1900,12-1,1);
    private Date endDate = new Date(2023-1900, 12-1, 1);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        loanBook = null;
        assertNull(loanBook);
    }

    @Test
    void create_new_loan_book_with_all_correct_parameters(){
        loanBook = new LoanBook(1, "123456789");
        assertEquals("id_book=1, startDate=2022-05-22, endDate=null, ssn='123456789", loanBook.toString());
    }

    @Test void create_new_loan_book_with_empty_ssn_number(){
        loanBook = new LoanBook(1,"");
        assertEquals("id_book=1, startDate=2022-05-22, endDate=null, ssn='", loanBook.toString());
    }

    @Test void create_new_loan_book_with_null_ssn_number(){
        loanBook = new LoanBook(1,null);
        assertEquals("id_book=1, startDate=2022-05-22, endDate=null, ssn='null", loanBook.toString());
    }

    @Test void set_id_for_loan_book(){
        loanBook = new LoanBook(1,"123456789");
        loanBook.setId(2);
        assertEquals(2, loanBook.getId());
    }

    @Test void  get_id_of_book_for_loan_magazine(){
        loanBook = new LoanBook(1,"123456789");
        int result = loanBook.getId_book();
        assertEquals(1,result);
    }

    @Test void get_id_of_loan_book(){
        loanBook = new LoanBook(1,"123456789");
        assertEquals(0, loanBook.getId());
    }

    @Test void get_id_of_loan_book_after_setting_it(){
        loanBook = new LoanBook(1,"123456789");
        loanBook.setId(12);
        assertEquals(12, loanBook.getId());
    }

    @Test void get_end_date_of_loan_book(){
        loanBook = new LoanBook(0,"123456789");
        assertNull(loanBook.getEndDate());
    }

    @Test void get_ssn_from_loan_book(){
        loanBook = new LoanBook(0,"123456789");
        assertEquals("123456789", loanBook.getSsn());
    }

    @Test void get_start_date_from_loan_book(){
        loanBook = new LoanBook(0,"123456789");
        assertEquals("2022-05-22", loanBook.getStartDate().toString());
    }

    @Test void get_ssn_number_from_loan_book_when_ssn_is_null(){
        loanBook = new LoanBook(0,null);
        assertNull(loanBook.getSsn());
    }

    @Test void get_ssn_number_from_loan_book_when_ssn_is_empty(){
        loanBook = new LoanBook(0,"");
        assertEquals("", loanBook.getSsn());
    }
}
