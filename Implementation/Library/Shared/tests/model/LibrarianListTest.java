package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianListTest
{
  private LibrarianList list;

  //Librarian l1=new Librarian("11111111","password1","Rosa","Briales");
  //Librarian l2=new Librarian("22222222","password2","Rafael","Sanchez");
  //Librarian l3=new Librarian("33333333","password3","Maria","Ortiz");
  //Librarian l4=new Librarian("44444444","password4","Alexandru","Dulghier");
  //Librarian l5=new Librarian("55555555","password5","Franciszek","Jan Nurkiewicz");


  @BeforeEach void setUp()
  {
    list=new LibrarianList();
  }

  @Test void add_one_librarian()
  {
    Librarian l1=new Librarian("11111111","password1","Rosa","Briales");
    list.addLibrarian(l1);
    assertEquals(l1,list.getLibrarianList().get(0));
  }

  @Test void add_two_librarians()
  {
    Librarian l1=new Librarian("11111111","password1","Rosa","Briales");
    Librarian l2=new Librarian("22222222","password2","Rafael","Sanchez");
    ArrayList<Librarian> testList=new ArrayList<>();
    testList.add(l1);
    testList.add(l2);
    list.addLibrarian(l1);
    list.addLibrarian(l2);
    assertEquals(testList,list.getLibrarianList());
  }
  @Test void add_multiple_librarians()
  {
    Librarian l1=new Librarian("11111111","password1","Rosa","Briales");
    Librarian l2=new Librarian("22222222","password2","Rafael","Sanchez");
    Librarian l3=new Librarian("33333333","password3","Maria","Ortiz");
    Librarian l4=new Librarian("44444444","password4","Alexandru","Dulghier");
    Librarian l5=new Librarian("55555555","password5","Franciszek","Jan Nurkiewicz");
    ArrayList<Librarian> testList=new ArrayList<>();
    testList.add(l1);
    testList.add(l2);
    testList.add(l3);
    testList.add(l4);
    testList.add(l5);
    list.addLibrarian(l1);
    list.addLibrarian(l2);
    list.addLibrarian(l3);
    list.addLibrarian(l4);
    list.addLibrarian(l5);
    assertEquals(testList,list.getLibrarianList());
  }

 /*
 //The following tests is about restrictions in the database

 @Test void add_same_librarian_twice()
  {
    Librarian l1=new Librarian("11111111","password1","Rosa","Briales");
    list.addLibrarian(l1);
    assertThrows(IllegalArgumentException.class,()->list.addLibrarian(l1));
  }

  @Test void add_two_librarians_with_same_ssn()
  {
    Librarian l1=new Librarian("11111111","password1","Rosa","Briales");
    Librarian l2=new Librarian("11111111","password1","Maria","Ortiz");
    list.addLibrarian(l1);
    assertThrows(IllegalArgumentException.class,()->list.addLibrarian(l2));
  }
*/
  @Test void remove_libarian()
  {
    Librarian l1=new Librarian("11111111","password1","Rosa","Briales");
    Librarian l2=new Librarian("22222222","password2","Rafael","Sanchez");
    ArrayList<Librarian> testList=new ArrayList<>();
    testList.add(l1);
    list.addLibrarian(l1);
    list.addLibrarian(l2);
    list.removeLibrarian(l2);
    assertEquals(testList,list.getLibrarianList());
  }

  @Test void remove_a_librarian_not_on_the_list()
  {
    Librarian l1=new Librarian("11111111","password1","Rosa","Briales");
    Librarian l2=new Librarian("22222222","password2","Rafael","Sanchez");
    Librarian l3=new Librarian("33333333","password3","Maria","Ortiz");
    ArrayList<Librarian> testList=new ArrayList<>();
    testList.add(l1);
    testList.add(l2);
    list.addLibrarian(l1);
    list.addLibrarian(l2);
    list.removeLibrarian(l3);
    assertEquals(testList,list.getLibrarianList());
  }

  @Test void remove_librarian_by_ssn()
  {
    Librarian l1=new Librarian("11111111","password1","Rosa","Briales");
    Librarian l2=new Librarian("22222222","password2","Rafael","Sanchez");
    ArrayList<Librarian> testList=new ArrayList<>();
    testList.add(l2);
    list.addLibrarian(l1);
    list.addLibrarian(l2);
    list.removeLibrarianBySsn(11111111);
    assertEquals(testList,list.getLibrarianList());
  }

  @Test void remove_librarian_by_ssn_when_it_doesnt_match_any_on_the_list()
  {
    Librarian l1=new Librarian("11111111","password1","Rosa","Briales");
    Librarian l2=new Librarian("22222222","password2","Rafael","Sanchez");
    ArrayList<Librarian> testList=new ArrayList<>();
    testList.add(l1);
    testList.add(l2);
    list.addLibrarian(l1);
    list.addLibrarian(l2);
    list.removeLibrarianBySsn(11191111);
    assertEquals(testList,list.getLibrarianList());
  }

  @Test void get_librarian_by_ssn()
  {
    Librarian l1=new Librarian("11111111","password1","Rosa","Briales");
    Librarian l2=new Librarian("22222222","password2","Rafael","Sanchez");
    list.addLibrarian(l1);
    list.addLibrarian(l2);
    assertEquals(l1,list.getLibrarianBySsn(11111111));
  }

  @Test void get_librarian_by_ssn_when_it_doesnt_match_any_on_the_list()
  {
    Librarian l1=new Librarian("11111111","password1","Rosa","Briales");
    Librarian l2=new Librarian("22222222","password2","Rafael","Sanchez");
    list.addLibrarian(l1);
    list.addLibrarian(l2);
    assertNull(list.getLibrarianBySsn(333311111));
  }

}
