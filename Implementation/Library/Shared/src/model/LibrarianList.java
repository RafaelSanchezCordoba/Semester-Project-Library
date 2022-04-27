package model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class LibrarianList implements Serializable
{
  private ArrayList<Librarian> list;

  public LibrarianList(){

    list = new ArrayList<Librarian>();
  }
  public void addLibrarian(Librarian librarian){
    list.add(librarian);
  }
  public void removeLibrarian(Librarian librarian){
    list.remove(librarian);
  }
  public ArrayList<Librarian> getLibrarianList(){
    return list;
  }

  public void removeLibrarianBySsn(int ssn){
    for (Librarian x: list){
      if (x.getSnn()==ssn){
        list.remove(x);
      }
    }
  }
  public Librarian getLibrarianBySsn(int ssn){
    for (Librarian x:list){
      if (x.getSnn()==ssn){
        return x;
      }
    }
    return null;
  }
  public String toStringArray(){
    String result = "";
    for (Librarian x:list){

      result += x.toString();
      }
    return result;
  }
}
