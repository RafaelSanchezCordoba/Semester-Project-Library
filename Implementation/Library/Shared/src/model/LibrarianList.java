package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class to implement a list of Librarian Object
 *  with the necessary methods
 * @author Alexandru Dulghier
 */
public class LibrarianList implements Serializable
{
  private ArrayList<Librarian> list;

  /**
   * Constructor with no arguments
   *initializes an empty list of Librarians
   */
  public LibrarianList(){

    list = new ArrayList<Librarian>();
  }

  /**
   * method to add a Librarian to the list
   * @param librarian
   */
  public void addLibrarian(Librarian librarian){
    list.add(librarian);
  }

  /**
   * method to remove a librarian from the list
   * @param librarian
   */
  public void removeLibrarian(Librarian librarian){
    list.remove(librarian);
  }

  /**
   * method to return the entire list of Librarians
   * @return ArrayList<Librarian>
   */
  public ArrayList<Librarian> getLibrarianList(){
    return list;
  }

  /**
   * method that removes one or multiple Librarians from the
   * list if they have the specified ssn as an attribute
   * @param ssn
   */
<<<<<<< Updated upstream
  public void removeLibrarianBySsn(int ssn){
=======
<<<<<<< HEAD
  public void removeLibrarianBySsn(String ssn){
=======
  public void removeLibrarianBySsn(int ssn){
>>>>>>> 7e718619a38eaa66df02ddfe9864aa4893dd4e7c
>>>>>>> Stashed changes
    for (Librarian x: list){
      if (x.getSsn().equals(ssn)){
        list.remove(x);
      }
    }
  }

  /**
   * Method that returns the first Librarian that has
   * the specified ssn as an attribute
   * @param ssn
   * @return Librarian
   */
<<<<<<< Updated upstream
  public Librarian getLibrarianBySsn(int ssn){
=======
<<<<<<< HEAD
  public Librarian getLibrarianBySsn(String ssn){
=======
  public Librarian getLibrarianBySsn(int ssn){
>>>>>>> 7e718619a38eaa66df02ddfe9864aa4893dd4e7c
>>>>>>> Stashed changes
    for (Librarian x:list){
      if (x.getSsn().equals(ssn)){
        return x;
      }
    }
    return null;
  }

  /**
   * Method that returns the entire List of
   * Librarian in a sting format
   * @return String
   */
  public String toStringArray(){
    String result = "";
    for (Librarian x:list){

      result += x.toString();
      result+=" ";
      }
    return result.trim();
  }
}
