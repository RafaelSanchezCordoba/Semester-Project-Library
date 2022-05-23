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
   * @param firstName
   * @param lastName
   * @param password
   */
  public LibraryUser(String ssn,String firstName,String lastName,String password){
    this.ssn= ssn;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
  }

  /**
   * return ssn fname lname of the library user as a String
   * @return String
   */
  @Override public String toString()
  {
    return "LibraryUser{SSN: " + ssn + ", Last Name: "
        + lastName + ", First Name: " + firstName + "}";
  }

  /**
   * Determines if 2 library users are equal to each other
   * @param o
   * @return
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
   * returns the ssn of the library user as String
   * @return String
   */
  public String getSSN()
  {
    return ssn;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getPassword()
  {
    return password;
  }
}
