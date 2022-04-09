import sharedObjects.Magazine;

import java.rmi.Remote;

public interface MagazineRemote extends Remote
{
  void addMagazine(Magazine magazine);
  void removeMagazine(int id);
  Magazine  getMagazine();
}
