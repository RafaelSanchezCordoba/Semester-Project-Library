package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelMagazine;
import model.CurrentTime;
import model.Magazine;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
        this.magazineList = new SimpleListProperty<>(observableList);

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
        property.bind(magazineList);
    }

    public void bindMagazineListViewForTest(SimpleListProperty<Magazine> property)
    {
        property.bind(magazineList);
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
        if (!errorsCheck())
        {
            model.addMagazine(magazine);
            errorLabel.set("");
        }
        reset();
    }

    public void removeMagazine(int id) throws SQLException, RemoteException
    {
        System.out.println("viewModel");
        model.removeMagazine(id);
    }

    public void setMagazineList() throws RemoteException, SQLException{
        magazineList.clear();
        magazineList.addAll(model.getMagazineList());
    }

    private boolean errorsCheck()
    {
        if (titleTextField.get().equals(""))
        {
            errorLabel.set("Title can't be null");
            return true;
        }
        else if (publisherTextField.get().equals(""))
        {
            errorLabel.set("Publisher can't be null");
            return true;
        }
        else if (Integer.parseInt(dayTextField.get())<1)
        {
            errorLabel.set("Invalid date");
            return true;
        }
        else if (Integer.parseInt(dayTextField.get())>28)
        {
            switch (Integer.parseInt(monthTextField.get()))
            {
                case 2:
                    errorLabel.set("Invalid date");
                    return true;
                case 1,3,5,7,8,10,12:
                    if (Integer.parseInt(dayTextField.get())>31)
                    {
                        errorLabel.set("Invalid date");
                        return true;
                    }
                case 4,6,9,11:
                    if (Integer.parseInt(dayTextField.get())>30)
                    {
                        errorLabel.set("Invalid date");
                        return true;
                    }
            }

        }
        else if (Integer.parseInt(monthTextField.get())<1||Integer.parseInt(monthTextField.get())>12)
        {
            errorLabel.set("Invalid date");
            return true;
        }
        else if (futureDateCheck())
        {
            errorLabel.set("Invalid date: future date");
            return true;
        }
        return false;
    }


    public boolean futureDateCheck()
    {
        //There probably is a simpler way to check if the date is future, but this is working

        Date date=new Date(Integer.parseInt(yearTextField.getValue())-1900,Integer.parseInt(monthTextField.getValue())-1,Integer.parseInt(dayTextField.getValue()));

        CurrentTime now=new CurrentTime();
        String day=now.getFormattedIsoDate().substring(8,10);
        String month=now.getFormattedIsoDate().substring(5,7);
        String year=now.getFormattedIsoDate().substring(0,4);
        Date currentDate=new Date(Integer.parseInt(year)-1900,Integer.parseInt(month)-1,Integer.parseInt(day));

        return date.after(currentDate);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {

        if (evt.getPropertyName().equals("newMagazine"))
        {
            magazineList.add((Magazine) evt.getNewValue());
        }
        else if (evt.getPropertyName().equals("removeMagazine"))
        {
            for (int i = 0; i < magazineList.size(); i++)
            {
                if (magazineList.get(i).getId() == (int) evt.getNewValue())
                {
                    magazineList.remove(magazineList.get(i));
                    break;
                }
            }
        }
    }
}
