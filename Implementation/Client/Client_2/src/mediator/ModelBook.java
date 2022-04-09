package mediator;

import model.Book;

public interface ModelBook extends PropertyChangeSubject{
    void addBook(Book book);
    void removeBook(int id);
}
