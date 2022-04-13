package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelBook;
import model.Book;
import server.RemoteBook;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

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
    private final ObservableList options ;
    private int counter;

    public AddRemoveBookViewModel(ModelBook model) throws RemoteException
    {
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
        this.options = FXCollections.observableArrayList();

        for (int i=0;i<model.getBookList().size();i++){
            Object[] row = model.getBookList().get(i);
            String id = row[0].toString();
            String publisher = row[1].toString();
            String title = (String)row[2];
            String isbn = (String) row[3];
            String author =(String) row[4];
            options.add(id+"   |  "+publisher+"  |  "+title+"  |  "+isbn+"  |  "+author);
            System.out.println(id+"|"+publisher+"|"+title+"|"+isbn+"|"+author+"i:"+i);
    }

        //this will get the highest id of the db
        Object[] row = model.getBookList().get(0);
        counter = Integer.parseInt(row[0].toString());


    }
    public void  dummy (){
        this.titleTextField.setValue("default_tile");
        this.publisherTextField.setValue("default_pubblisher");
        this.authorTextField.setValue("no_name");
        this.isbnTextField.setValue("deafult_isbn");
        this.yearTextField.setValue("0000");
        this.genreTextField.setValue("default_genre");
    }

    public void search() {

    }

    public void addBook() {
        counter++;
        try
        {
            model.addBook(new Book(counter,titleTextField.getValue(),publisherTextField.getValue()));
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }

    }

    public void removeBook(int id) throws RemoteException {
        model.removeBook(id);
    }
    public ObservableList getList(){
        return options;
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

    }
}