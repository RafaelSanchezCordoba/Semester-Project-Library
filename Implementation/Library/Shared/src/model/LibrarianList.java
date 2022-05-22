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
   * initializes an empty list of Librarians
   */
  public LibrarianList(){

    list = new ArrayList<Librarian>();
  }

  /**
   * Method to add a Librarian to the list
   * @param librarian
   * The librarian passed as an argument
   */
  public void addLibrarian(Librarian librarian){
    list.add(librarian);
  }

  /**
   * Method to remove a librarian from the list
   * @param librarian
   * The librarian passed as an argument
   */
  public void removeLibrarian(Librarian librarian){
    list.remove(librarian);
  }

  /**
   * Method to return the entire list of Librarians
   * @return ArrayList<Librarian>
   * The librarian list
   */
  public ArrayList<Librarian> getLibrarianList(){
    return list;
  }

  /**
   * Method that removes one or multiple Librarians from the
   * list if they have the specified ssn as an attribute
   * @param ssn
   * The social security number of the librarian
   */
  public void removeLibrarianBySsn(String ssn){
    for (int i=0;i< list.size();i++){
      if (list.get(i).getSsn().equals(ssn)){
        list.remove(list.get(i));
      }
    }
  }

  /**
   * Method that returns the first Librarian that has
   * the specified ssn as an attribute
   * @param ssn
   * The social security number of the librarian
   * @return Librarian
   * The librarian with the social security number passed as an argument
   */
  public Librarian getLibrarianBySsn(String ssn){
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
   * All the librarians in the list as a String
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
