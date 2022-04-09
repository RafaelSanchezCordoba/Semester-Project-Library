package client;


import model.multimediaItem.Magazine;

import java.io.Closeable;

public interface MagazineClient extends Closeable
{
  void addMagazine(Magazine magazine);
  void removeMagazine(int id);
  Magazine  getMagazine();
}
