package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianTest
{
  private Librarian librarian;

  @BeforeEach
  void setUp()
  {
  }

  @Test void create_new_librarian_with_all_corrects_parameters()
  {
    librarian=new Librarian("1234567890","password","Pepito","Perez");
    //using toString to check that the librarian is created correctly
    assertEquals("Librarian{ssn:'1234567890', first Name:'Pepito', last Name:'Perez'}",librarian.toString());
  }
  @Test void get_ssn_as_a_string()
  {
    librarian=new Librarian("1234567890","password","Pepito","Perez");
    assertEquals("1234567890",librarian.getSsn());
  }

  @Test void get_ssn_as_an_int()
  {
    librarian=new Librarian("1234567890","password","Pepito","Perez");
    assertEquals(1234567890,librarian.getSnn());
  }

  @Test void null_ssn()
  {
    librarian=new Librarian(null,"password","Pepito","Perez");
    assertEquals("Librarian{ssn:'null', first Name:'Pepito', last Name:'Perez'}",librarian.toString());
  }

  @Test void null_password()
  {
    librarian=new Librarian("1234567890",null,"Pepito","Perez");
    assertEquals("Librarian{ssn:'1234567890', first Name:'Pepito', last Name:'Perez'}",librarian.toString());
  }

  @Test void null_first_name()
  {
    librarian=new Librarian("1234567890","password",null,"Perez");
    assertEquals("Librarian{ssn:'1234567890', first Name:'null', last Name:'Perez'}",librarian.toString());
  }

  @Test void null_last_name()
  {
   librarian=new Librarian("1234567890","password","Pepito",null);
    assertEquals("Librarian{ssn:'1234567890', first Name:'Pepito', last Name:'null'}",librarian.toString());
  }

  //The following test are about null values which are restricted in the database

 /* @Test void null_ssn()
  {
    assertThrows(IllegalArgumentException.class,()->librarian=new Librarian(null,"password","Pepito","Perez"));
  }

  @Test void null_password()
  {
    assertThrows(IllegalArgumentException.class,()->librarian=new Librarian("1234567890",null,"Pepito","Perez"));
  }

  @Test void null_first_name()
  {
    assertThrows(IllegalArgumentException.class,()->librarian=new Librarian("1234567890","password",null,"Perez"));
  }

  @Test void null_last_name()
  {
    assertThrows(IllegalArgumentException.class,()->librarian=new Librarian("1234567890","password","Pepito",null));
  }

//The following tests are about restrictions in length in the database

  @Test void password_with_21_characters()
  {
    assertThrows(IllegalArgumentException.class,()->librarian=new Librarian("1234567890","qwertyuiopasdfghjklzx","Pepito","Perez"));
  }
  @Test void name_with_51_characters()
  {
    assertThrows(IllegalArgumentException.class,()->librarian=new Librarian("1234567890","password","Pepitoooooooooooooooooooooooooooooooooooooooooooooo","Perez"));
  }

  @Test void last_name_with_51_characters()
  {
    assertThrows(IllegalArgumentException.class,()->librarian=new Librarian("1234567890","password","Paco","Pepitoooooooooooooooooooooooooooooooooooooooooooooo"));
  }*/

  }
