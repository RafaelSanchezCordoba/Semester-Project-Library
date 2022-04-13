package mediator;

import model.Book;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ModelBook extends PropertyChangeSubject{
    void addBook(Book book) throws RemoteException;
    void removeBook(int id) throws RemoteException;
    ArrayList<Object[]>getBookList() throws RemoteException ;
}
