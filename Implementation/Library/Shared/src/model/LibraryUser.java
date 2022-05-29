package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *Class of a Library User .
 * @author Alexandru Dulghier.
 * @version 1.0 - 12/05/22.
 */
public class LibraryUser implements Serializable
{
  private final String firstName, lastName, ssn, password;
  /**
   * Constructor of the library user class
   * @param ssn
   * The social security number
   * @param firstName
   * The first name
   * @param lastName
   * The las name
   * @param password
   * The password
   */
  public LibraryUser(String ssn,String firstName,String lastName,String password){
    this.ssn= ssn;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
  }

  /**
   * toString method
   * @return String
   * The library user variables as a String
   */
  @Override public String toString()
  {
    return "LibraryUser{SSN: " + ssn + ", Last Name: "
        + lastName + ", First Name: " + firstName + "}";
  }

  /**
   * Determines if 2 library users are equal to each other
   * @param o
   * The object to compare
   * @return
   * True if they are, false if not
   */
  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    LibraryUser that = (LibraryUser) o;
    return firstName.equals(that.firstName) && lastName.equals(that.lastName)
        && ssn.equals(that.ssn) && password.equals(that.password);
  }

  /**
   * Get social security number method
   * @return String
   * The social security number of the library user
   */
  public String getSSN()
  {
    return ssn;
  }

  /**
   * Get first name method
   * @return
   * The first name of the library user
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * Get last name method
   * @return
   * The last name of the library user
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Get password method
   * @return
   * The password of the library user
   */
  public String getPassword()
  {
    return password;
  }
}
