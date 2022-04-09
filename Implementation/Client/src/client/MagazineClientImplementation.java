package client;

import model.multimediaItem.Magazine;

import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;

public class MagazineClientImplementation extends UnicastRemoteObject implements MagazineClient
{
  @Override public void addMagazine(Magazine magazine)
  {

  }

  @Override public void removeMagazine(int id)
  {

  }

  @Override public Magazine getMagazine()
  {
    return null;
  }

  @Override public void close() throws IOException
  {

  }
}
