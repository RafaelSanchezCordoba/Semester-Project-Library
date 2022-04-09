package client;


import model.Magazine;

import java.io.Closeable;

public interface MagazineClient extends Closeable
{
  void addMagazine(Magazine magazine);
  void removeMagazine(int id);

}
