package persistance;

import model.Magazine;
import server.MagazineStorage;

import java.util.ArrayList;

public class MagazineStorageTest implements MagazineStorage
{
  private int counter = 0;
  private ArrayList<Magazine> magazines;
  private static MagazineStorageTest instance;

  private MagazineStorageTest()
  {
    this.magazines = new ArrayList<Magazine>();
  }
  public static synchronized MagazineStorageTest getInstance(){
    if(instance==null){
      instance=  new MagazineStorageTest();
    }
    return instance;
  }

  @Override public void addMagazine(Magazine magazine)
  {
    magazines.add( magazine);
    magazine.setId(magazines.size());
    System.out.println(magazine);
  }

  @Override public void removeMagazine(int id)
  {
    for (int i = 0; i <magazines.size() ; i++)
    {
      if(id== magazines.get(i).getId()){
        magazines.remove(magazines.get(i));
        break;
      }
    }


  }

  public ArrayList<Magazine> getMagazineList()
  {
    return magazines;
  }
}
