package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.Model;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AddRemoveMagazineViewModel implements PropertyChangeListener {
    private final Model model;

    private final StringProperty titleTextField;
    private final StringProperty publisherTextField;
    private final StringProperty volumeTextField;
    private final StringProperty dayTextField;
    private final StringProperty yearTextField;
    private final StringProperty monthTextField;
    private final StringProperty genreTextField;
    private final StringProperty searchTextField;
    private final StringProperty errorLabel;

    public AddRemoveMagazineViewModel(Model model) {
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

    public void search() {

    }

    public void addMagazine() {

    }

    public void removeMagazine() {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
