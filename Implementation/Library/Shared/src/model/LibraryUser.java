package model;
/**
 * The library user class.
 * @author Rosa Briales Marfil.
 * @version 1.0 12/05/22.
 */
public class LibraryUser
{
  private final String ssn, password, firstName, lastName;

  /**
   * Library user constructor.
   * @param ssn
   * The social security number.
   * @param password
   * The password.
   * @param firstName
   * The first name.
   * @param lastName
   * The last name.
   */
  public LibraryUser(String ssn, String password, String firstName,
      String lastName)
  {
    this.ssn = ssn;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Get the password
   * @return String with the user's password
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * Get the first name
   * @return String with the user's name
   */
  public String getFirstName()
  {
    return firstName;
  }


  /**
   * Get the last name
   * @return String with the user's last name
   */
  public String getLastName()
  {
    return lastName;
  }


  /**
   * Get the social security number
   * @return String with the user's social security number
   */
  public String getSsn()
  {
    return ssn;
  }
}
