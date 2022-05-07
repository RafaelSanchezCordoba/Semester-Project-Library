package viewModel;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelBook;
import model.Book;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddRemoveBookViewModel implements PropertyChangeListener {
    private final ModelBook model;
    private final StringProperty titleTextField;
    private final StringProperty publisherTextField;
    private final StringProperty authorTextField;
    private final StringProperty isbnTextField;
    private final StringProperty yearTextField;
    private final StringProperty genreTextField;
    private final StringProperty editionTextField;
    private final StringProperty searchTextField;
    private final StringProperty errorLabel;
    private final SimpleListProperty<Book> bookList;

    public AddRemoveBookViewModel(ModelBook model) throws RemoteException {
        this.model = model;
        this.titleTextField = new SimpleStringProperty("");
        this.publisherTextField = new SimpleStringProperty("");
        this.authorTextField = new SimpleStringProperty("");
        this.isbnTextField = new SimpleStringProperty("");
        this.yearTextField = new SimpleStringProperty("");
        this.genreTextField = new SimpleStringProperty("");
        this.editionTextField = new SimpleStringProperty("");
        this.searchTextField = new SimpleStringProperty("");
        this.errorLabel = new SimpleStringProperty("");
        ObservableList<Book> observableList = FXCollections.observableArrayList(new ArrayList<>());
        this.bookList = new SimpleListProperty<>(observableList);

        model.addPropertyChangeListener("newBook", this);
        model.removePropertyChangeListener("removeBook", this);
    }

    public void search() {

    }

    public void setBookList() throws RemoteException, SQLException{
        for (int i = 0; i < model.getBookList().size(); i++)
        {
            bookList.add(model.getBookList().get(i));
        }
    }

    public void addBook(Book book) throws RemoteException, SQLException {
        model.addBook(book);
    }

    public void removeBook(int id) throws RemoteException, SQLException
    {
        model.removeBook(id);

    }

    public void reset() throws SQLException, RemoteException{
        setBookList();

        titleTextField.set("");
        publisherTextField.set("");
        authorTextField.set("");
        isbnTextField.set("");
        yearTextField.set("");
        genreTextField.set("");
        editionTextField.set("");
        searchTextField.set("");
    }

    public void bindTitleTextField(StringProperty property) {
        property.bindBidirectional(titleTextField);
    }

    public void bindPublisherTextField(StringProperty property) {
        property.bindBidirectional(publisherTextField);
    }

    public void bindAuthorTextField(StringProperty property) {
        property.bindBidirectional(authorTextField);
    }

    public void bindISBNTextField(StringProperty property) {
        property.bindBidirectional(isbnTextField);
    }

    public void bindYearTextField(StringProperty property) {
        property.bindBidirectional(yearTextField);
    }

    public void bindGenreTextField(StringProperty property) {
        property.bindBidirectional(genreTextField);
    }

    public void bindEditionTextField(StringProperty property) {
        property.bindBidirectional(editionTextField);
    }

    public void bindSearchTextField(StringProperty property) {
        property.bindBidirectional(searchTextField);
    }

    public void bindErrorLabel(StringProperty property) {
        property.bind(errorLabel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try
        {
            setBookList();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
