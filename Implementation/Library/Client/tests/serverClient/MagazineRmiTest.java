//
//package client;
//
//import client.MagazineClient;
//import client.MagazineClientImplementation;
//import model.Magazine;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.rmi.AlreadyBoundException;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.sql.Date;
//import java.sql.SQLException;
//
//public class MagazineRmiTest
//{
//  private static Registry registry;
//  private static MagazineClientImplementation magazineClient;
//  private static Magazine magazine;
//  private static Magazine magazine1;
//  private static Magazine magazine2;
//
//  @BeforeAll
//  public static void setupMagazineServer()
//      throws IOException, NotBoundException, AlreadyBoundException
//  {
//    registry = LocateRegistry.createRegistry(1012);
//    magazineClient = new MagazineClientImplementation("localhost",Registry.REGISTRY_PORT);
//    registry.bind("magazine",magazineClient);
//  }
//
//  @BeforeAll
//  public static void setupVariables(){
//    Date date = new Date(2020-1900,8,12);
//    magazine = new Magazine("boi","kratos",1,"olimpic",date);
//    magazine1 = new Magazine("the mirror","ggg",4,"grind",date);
//    magazine2 = new Magazine("flying snow","shin",2,"xuanxuan",date);
//
//  }
//  @Test
//  public void addNull() throws RemoteException, SQLException
//  {
//
//    try
//    {
//      int exp = magazineClient.getMagazineList().size();
//      magazineClient.addMagazine(null);
//      exp++;
//      Assertions.assertEquals(exp,magazineClient.getMagazineList().size());
//    }catch (NullPointerException e){
//      e.fillInStackTrace();
//    }
//  }
//  @Test
//  public void addOne() throws SQLException, RemoteException
//  {
//    int exp = magazineClient.getMagazineList().size();
//    magazineClient.addMagazine(magazine);
//    exp++;
//    Assertions.assertEquals(exp,magazineClient.getMagazineList().size());
//  }
//
//  @Test
//  public void addMultiple() throws RemoteException, SQLException
//  {
//    int exp = magazineClient.getMagazineList().size();
//    magazineClient.addMagazine(magazine1);
//    exp++;
//    magazineClient.addMagazine(magazine);
//    exp++;
//    magazineClient.addMagazine(magazine2);
//    exp++;
//
//    Assertions.assertEquals(exp,magazineClient.getMagazineList().size());
//  }
//  @Test
//  public void removeOne() throws SQLException, RemoteException
//  {
//    //test in storage
//    //because id is null
//  }
//}

