package server;

import model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteBook extends Remote {
    void addBook(Book book) throws RemoteException, SQLException;
    void removeBook(int id) throws RemoteException, SQLException;
    ArrayList<Book> getBookList() throws RemoteException, SQLException;

}
