package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import java.util.Date;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class LoanMagazineTest {
    private LoanMagazine loanMagazine;
    private Date startDate = new Date(2022-1900,12-1,1);

    @BeforeEach void setUp() {
    }

    @AfterEach void tearDown() {
        loanMagazine = null;
        assertNull(loanMagazine);
    }

    @Test void create_new_loan_magazine_with_all_correct_parameters(){
        loanMagazine = new LoanMagazine(1,startDate,"123456789");
        assertEquals("0 1 2022-12-01 123456789",loanMagazine.toString());
    }

    @Test void create_new_loan_magazine_with_empty_ssn_number(){
        loanMagazine = new LoanMagazine(1,startDate,"");
        assertEquals("0 1 2022-12-01 ",loanMagazine.toString());
    }

    @Test void create_new_loan_magazine_with_null_ssn_number(){
        loanMagazine = new LoanMagazine(1,startDate,null);
        assertEquals("0 1 2022-12-01 null",loanMagazine.toString());
    }

    @Test void get_start_date_when_date_is_null(){
        loanMagazine = new LoanMagazine(1,null,"123456789");
        assertNull(loanMagazine.getStartDate());
    }

    @Test void set_id_for_loan_magazine(){
        loanMagazine = new LoanMagazine(1,startDate,"123456789");
        loanMagazine.setId(2);
        assertEquals(2,loanMagazine.getId());
    }

    @Test void  get_id_of_magazine_for_loan_magazine(){
        loanMagazine = new LoanMagazine(1,startDate,"123456789");
        int result = loanMagazine.getId_magazine();
        assertEquals(1,result);
    }

    @Test void get_id_of_loan_magazine(){
        loanMagazine = new LoanMagazine(1,startDate,"123456789");
        assertEquals(0,loanMagazine.getId());
    }

    @Test void get_id_of_loan_magazine_after_setting_it(){
        loanMagazine = new LoanMagazine(1,startDate,"123456789");
        loanMagazine.setId(12);
        assertEquals(12,loanMagazine.getId());
    }

    @Test void get_end_date_of_loan_magazine(){
        loanMagazine = new LoanMagazine(0,startDate,"123456789");
        assertNull(loanMagazine.getEndDate());
    }

    @Test void get_ssn_from_loan_magazine(){
        loanMagazine = new LoanMagazine(0,startDate,"123456789");
        assertEquals("123456789",loanMagazine.getSsn());
    }

    @Test void get_start_date_from_loan_magazine(){
        loanMagazine = new LoanMagazine(0, startDate,"123456789");
        assertEquals("2022-12-01",loanMagazine.getStartDate().toString());
    }

    @Test void get_start_date_from_loan_magazine_when_date_is_null(){
        loanMagazine = new LoanMagazine(0, null,"123456789");
        assertNull(loanMagazine.getStartDate());
    }

    @Test void get_ssn_number_from_loan_magazine_when_ssn_is_null(){
        loanMagazine = new LoanMagazine(0,startDate,null);
        assertNull(loanMagazine.getSsn());
    }

    @Test void get_ssn_number_from_loan_magazine_when_ssn_is_empty(){
        loanMagazine = new LoanMagazine(0,startDate,"");
        assertEquals("",loanMagazine.getSsn());
    }

}