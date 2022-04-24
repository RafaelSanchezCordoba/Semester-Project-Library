package persistance;

import model.Magazine;

import java.util.ArrayList;
import java.util.HashMap;

public class MagazineStorageImplementation  implements MagazineStorage
{
  private ArrayList<Magazine> magazines;
  private static MagazineStorageImplementation instance;

  private MagazineStorageImplementation()
  {
    this.magazines = new ArrayList<Magazine>();
  }
  public static synchronized MagazineStorageImplementation getInstance(){
    if(instance==null){
      instance=  new MagazineStorageImplementation();
    }
    return instance;
  }

  @Override public void addMagazine(Magazine magazine)
  {
    magazines.add( magazine);
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
