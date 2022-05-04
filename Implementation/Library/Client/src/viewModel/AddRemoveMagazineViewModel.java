package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelMagazine;
import model.Magazine;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddRemoveMagazineViewModel implements PropertyChangeListener {
    private final ModelMagazine model;

    private final StringProperty titleTextField;
    private final StringProperty publisherTextField;
    private final StringProperty volumeTextField;
    private final StringProperty dayTextField;
    private final StringProperty yearTextField;
    private final StringProperty monthTextField;
    private final StringProperty genreTextField;
    private final StringProperty searchTextField;
    private final SimpleListProperty<Magazine> magazineList;
    private final StringProperty errorLabel;

    public AddRemoveMagazineViewModel(ModelMagazine model)  {
        this.model = model;
        this.titleTextField = new SimpleStringProperty("");
        this.publisherTextField = new SimpleStringProperty("");
        this.volumeTextField = new SimpleStringProperty("");
        this.dayTextField = new SimpleStringProperty("");
        this.yearTextField = new SimpleStringProperty("");
        this.genreTextField = new SimpleStringProperty("");
        this.monthTextField = new SimpleStringProperty("");
        this.searchTextField = new SimpleStringProperty("");
        this.errorLabel = new SimpleStringProperty("");
        ObservableList<Magazine> observableList = FXCollections.observableArrayList( new ArrayList<Magazine>());
        this.magazineList=  new SimpleListProperty<>(observableList);

        model.addPropertyChangeListener("newMagazine", this);
        model.addPropertyChangeListener("removeMagazine", this);

    }

    public void bindTitleTextField(StringProperty property) {
        property.bindBidirectional(titleTextField);
    }

    public void bindPublisherTextField(StringProperty property) {
        property.bindBidirectional(publisherTextField);
    }

    public void bindVolumeTextField(StringProperty property) {
        property.bindBidirectional(volumeTextField);
    }

    public void bindDayTextField(StringProperty property) {
        property.bindBidirectional(dayTextField);
    }

    public void bindYearTextField(StringProperty property) {
        property.bindBidirectional(yearTextField);
    }

    public void bindGenreTextField(StringProperty property) {
        property.bindBidirectional(genreTextField);
    }

    public void bindMontTextField(StringProperty property) {
        property.bindBidirectional(monthTextField);
    }

    public void bindSearchTextField(StringProperty property) {
        property.bindBidirectional(searchTextField);
    }

    public void bindErrorLabel(StringProperty property) {
        property.bind(errorLabel);
    }

    public void bindMagazineListView(ObjectProperty<ObservableList<Magazine>> property){
        property.bindBidirectional(magazineList);
    }


    public void search() {

    }

    public void reset() throws SQLException, RemoteException
    {
        setMagazineList();

        titleTextField.set("");
        genreTextField.set("");
        volumeTextField.set("");
        publisherTextField.set("");
        dayTextField.set("");
        monthTextField.set("");
        yearTextField.set("");
    }

    public void addMagazine(Magazine magazine)
        throws RemoteException, SQLException
    {

        model.addMagazine(magazine);
    }

    public void removeMagazine(int id) throws SQLException, RemoteException
    {
        model.removeMagazine(id);
    }

    public void setMagazineList() throws RemoteException, SQLException{
        for (int i = 0; i < model.getMagazineList().size(); i++)
        {
            magazineList.add(model.getMagazineList().get(i));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        try
        {

            setMagazineList();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        //        if(evt.getPropertyName().equals("newMagazine"))
//        {
//            magazineList.add((Magazine) evt.getNewValue());
//        }
//        else if (evt.getPropertyName().equals("removeMagazine")){
//            for (int i = 0; i < magazineList.size(); i++) {
//              if (magazineList.get(i).getId() == (int) evt.getNewValue()) {
//                  magazineList.remove(magazineList.get(i));
//              }
//            }
    }
}
