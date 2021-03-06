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
    private final SimpleListProperty<Magazine> magazineList;
    private final StringProperty errorLabel;

    /**
     * AddRemoveMagazineViewModel constructor that: set the model
     * ,add the property change listener for adding and removing a book
     * and set JavaFX variables
     * @param model
     * The model book
     */
    public AddRemoveMagazineViewModel(ModelMagazine model)  {
        this.model = model;
        this.titleTextField = new SimpleStringProperty("");
        this.publisherTextField = new SimpleStringProperty("");
        this.volumeTextField = new SimpleStringProperty("");
        this.dayTextField = new SimpleStringProperty("");
        this.yearTextField = new SimpleStringProperty("");
        this.genreTextField = new SimpleStringProperty("");
        this.monthTextField = new SimpleStringProperty("");
        this.errorLabel = new SimpleStringProperty("");
        ObservableList<Magazine> observableList = FXCollections.observableArrayList( new ArrayList<Magazine>());
        this.magazineList = new SimpleListProperty<>(observableList);

        model.addPropertyChangeListener("newMagazine", this);
        model.addPropertyChangeListener("removeMagazine", this);

    }

    /**
     * Bind the title text field
     * @param property
     * A String property
     */
    public void bindTitleTextField(StringProperty property) {
        property.bindBidirectional(titleTextField);
    }

    /**
     * Bind the publisher text field
     * @param property
     * A String property
     */
    public void bindPublisherTextField(StringProperty property) {
        property.bindBidirectional(publisherTextField);
    }

    /**
     * Bind the volume text field
     * @param property
     * A String property
     */
    public void bindVolumeTextField(StringProperty property) {
        property.bindBidirectional(volumeTextField);
    }

    /**
     * Bind the day text field
     * @param property
     * A String property
     */
    public void bindDayTextField(StringProperty property) {
        property.bindBidirectional(dayTextField);
    }

    /**
     * Bind the year text field
     * @param property
     * A String property
     */
    public void bindYearTextField(StringProperty property) {
        property.bindBidirectional(yearTextField);
    }

    /**
     * Bind the genre text field
     * @param property
     * A String property
     */
    public void bindGenreTextField(StringProperty property) {
        property.bindBidirectional(genreTextField);
    }

    /**
     * Bind the month text field
     * @param property
     * A String property
     */
    public void bindMontTextField(StringProperty property) {
        property.bindBidirectional(monthTextField);
    }

    /**
     * Bind the error label
     * @param property
     * A String property
     */
    public void bindErrorLabel(StringProperty property) {
        property.bind(errorLabel);
    }

    /**
     * Bind the magazine list
     * @param property
     * Object property, magazine observable list
     */
    public void bindMagazineListView(ObjectProperty<ObservableList<Magazine>> property){
        property.bind(magazineList);
    }

    /**
     * Bind the magazine list for testing
     * @param property
     * Magazine simple list property
     */
    public void bindMagazineListViewForTest(SimpleListProperty<Magazine> property)
    {
        property.bind(magazineList);
    }

    /**
     * Reset all the JavaFX variables
     */
    public void reset() throws  RemoteException
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

    /**
     * Add magazine method
     */
    public void addMagazine()
        throws RemoteException
    {
        if (!errorsCheck())
        {
            int volume;
            if (volumeTextField.get().equals(""))
            {
                volume= 1;
            }
            else
            {
                volume=Integer.parseInt(volumeTextField.get());
            }
            java.sql.Date date = new java.sql.Date(Integer.parseInt(yearTextField.get()) - 1900,
                Integer.parseInt(monthTextField.get()) - 1, Integer.parseInt(dayTextField.get()));
            Magazine magazine = new Magazine(titleTextField.get(),
                publisherTextField.get(),volume,
                genreTextField.get(), date);
            model.addMagazine(magazine);
            errorLabel.set("");
        }
        reset();
    }

    /**
     * Remove a magazine with a specific identification number
     * @param id
     * The unique identification number passed as an argument
     */
    public void removeMagazine(int id) throws  RemoteException
    {
        try
        {
            errorLabel.set("");
            model.removeMagazine(id);
        }catch (RemoteException e){
            errorLabel.set("cannot remove this magazine because is currently being lended");
        }

    }

    /**
     * Set the magazine list with all the magazines
     */
    public void setMagazineList() throws RemoteException{
        magazineList.clear();
        magazineList.addAll(model.getMagazineList());
    }

    /**
     * Check if the information passed is correct if not print out a specific error message
     * @return
     * True if there are any error, false if not
     */
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
        else if (dayTextField.get().equals(""))
        {
            errorLabel.set("Day can't be null");
            return true;
        }
        else if (monthTextField.get().equals(""))
        {
            errorLabel.set("Month can't be null");
            return true;
        }
        else if(yearTextField.get().equals(""))
        {
            errorLabel.set("Year can't be null");
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
        else if(!volumeTextField.get().equals(""))
        {
            if (volumeFormatCheck())
            {
                errorLabel.set("The volume must be a number");
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the value format is correct
     * @return
     * False if it is, true if not
     */
    private boolean volumeFormatCheck()
    {
        try
        {
            Integer.parseInt(volumeTextField.get());
        }
        catch (NumberFormatException e)
        {
            return true;
        }
        return false;
    }

    /**
     * Check if the selected date is a future date
     * @return
     * True if it is, false if not
     */
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

    /**
     * Property change method that call different methods depends on the event name
     * @param evt
     * A book object passed as an event
     */
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
