package storage;

import model.Magazine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistance.MagazineStorageTest;

import java.sql.Date;

public class MagazineStorageTestTest
{


  private static MagazineStorageTest storage = MagazineStorageTest.getInstance();

 private static   Magazine magazine;
 private static   Magazine magazine1;
 private static   Magazine magazine2;

  @BeforeAll
  public static void setupVariables(){
    Date date = new Date(2020-1990-8);
    magazine = new Magazine("fishing with the wind","vogue",1,"sport",date);
    magazine1 = new Magazine("the chad","sigma",4,"fashion",date);
    magazine2 = new Magazine("how to survive","wild",99,"science",date);
  }

  @Test
  public  void addOne(){
    int exp = storage.getMagazineList().size();
    storage.addMagazine(magazine);
    exp++;

    Assertions.assertEquals(exp,storage.getMagazineList().size());
  }

  @Test
  public void checkObject(){
    storage.addMagazine(magazine);
    Assertions.assertEquals(magazine,storage.getMagazineList().get(0));
  }
  @Test
  public void addMultiple(){
    int exp = storage.getMagazineList().size();
    storage.addMagazine(magazine);
    exp++;
    storage.addMagazine(magazine1);
    exp++;
    storage.addMagazine(magazine2);
    exp++;

    Assertions.assertEquals(exp,storage.getMagazineList().size());
  }
  @Test
  public void checkUniqueID(){
    storage.addMagazine(magazine2);
    storage.addMagazine(magazine);
    storage.addMagazine(magazine1);

    Assertions.assertEquals(true,(storage.getMagazineList().get(1).getId())!=storage.getMagazineList().get(2).getId());
  }
  @Test
  public  void removeMultiple(){
    int exp = storage.getMagazineList().size();

    storage.addMagazine(magazine2);
    storage.addMagazine(magazine);
    storage.addMagazine(magazine1);
    exp++;

    storage.removeMagazine(storage.getMagazineList().get(0).getId());
    storage.removeMagazine(storage.getMagazineList().get(1).getId());

    Assertions.assertEquals(exp,storage.getMagazineList().size());

  }
  @Test
  public void removeOne(){
    int exp = storage.getMagazineList().size();
    storage.addMagazine(magazine2);
    storage.removeMagazine(storage.getMagazineList().get(0).getId());

    Assertions.assertEquals(exp,storage.getMagazineList().size());

  }
}
