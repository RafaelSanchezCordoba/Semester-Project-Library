package fakeStorage;

import model.Librarian;

import java.util.ArrayList;

public class FakeStorageImplementation implements FakeStorage
{
  private ArrayList<Librarian> librarians;

  public FakeStorageImplementation(){
    librarians = new ArrayList<>();
  }
   public  void addLibrarian(Librarian librarian){
    librarians.add(librarian);
  }
  public ArrayList<Librarian> getLibrarianList(){
    return librarians;
  }
   public  void removeLibrarian(Librarian librarian){
    librarians.remove(librarian);
  }
}
