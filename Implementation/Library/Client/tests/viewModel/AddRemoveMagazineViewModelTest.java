package viewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.FakeModelManagerMagazine;
import mediator.ModelMagazine;
import model.CurrentTime;
import model.Magazine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AddRemoveMagazineViewModelTest
{

  private AddRemoveMagazineViewModel viewModel;
  private StringProperty title;
  private StringProperty publisher;
  private StringProperty volume;
  private StringProperty day;
  private StringProperty year;
  private StringProperty month;
  private StringProperty genre;
  private StringProperty search;
  private SimpleListProperty<Magazine> magazineList;
  private StringProperty error;
  private ModelMagazine model;

  @BeforeEach void setUp()
  {
    model=new FakeModelManagerMagazine();
    viewModel=new AddRemoveMagazineViewModel(model);
    this.title = new SimpleStringProperty("");
    this.publisher = new SimpleStringProperty("");
    this.volume = new SimpleStringProperty("");
    this.day = new SimpleStringProperty("");
    this.year = new SimpleStringProperty("");
    this.genre = new SimpleStringProperty("");
    this.month= new SimpleStringProperty("");
    this.search= new SimpleStringProperty("");
    this.error = new SimpleStringProperty("");
    ObservableList<Magazine> observableList = FXCollections.observableArrayList( new ArrayList<Magazine>());
    this.magazineList = new SimpleListProperty<>(observableList);

    viewModel.bindTitleTextField(title);
    viewModel.bindPublisherTextField(publisher);
    viewModel.bindVolumeTextField(volume);
    viewModel.bindDayTextField(day);
    viewModel.bindMontTextField(month);
    viewModel.bindYearTextField(year);
    viewModel.bindGenreTextField(genre);
    viewModel.bindSearchTextField(search);
    viewModel.bindErrorLabel(error);
    viewModel.bindMagazineListViewForTest(magazineList);
  }

  @Test void a_new_object_is_blank()
  {
    assertEquals("",title.get());
    assertEquals("",publisher.get());
    assertEquals("",volume.get());
    assertEquals("",day.get());
    assertEquals("",month.get());
    assertEquals("",year.get());
    assertEquals("",genre.get());
    assertEquals("",search.get());
    assertEquals("",error.get());
    assertEquals("[]",magazineList.get().toString());
  }

  @Test
  public void setting_the_labels_doesnt_change_list_or_error() {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("3");
    year.set("2022");
    genre.set("Economy");
    assertEquals("", error.get());
    assertEquals("[]", magazineList.getValue().toString());
  }

  @Test void add_adds_the_magazine() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine(new Magazine(title.getValue(),publisher.getValue(),Integer.parseInt(volume.getValue()),genre.getValue(),new Date(Integer.parseInt(year.getValue())-1900,Integer.parseInt(month.getValue())-1,Integer.parseInt(day.getValue()))));
    assertEquals("[Magazine{title=Forbes, publisher=Forbes, volume=134, genre='Economy', date=2022-03-12}]",magazineList.get().toString());
  }

  @Test void add_two_magazines() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine(new Magazine(title.getValue(),publisher.getValue(),Integer.parseInt(volume.getValue()),genre.getValue(),new Date(Integer.parseInt(year.getValue())-1900,Integer.parseInt(month.getValue())-1,Integer.parseInt(day.getValue()))));
    title.set("Hola");
    publisher.set("Paquito");
    volume.set("128");
    day.set("1");
    month.set("8");
    year.set("2019");
    genre.set("Sports");
    viewModel.addMagazine(new Magazine(title.getValue(),publisher.getValue(),Integer.parseInt(volume.getValue()),genre.getValue(),new Date(Integer.parseInt(year.getValue())-1900,Integer.parseInt(month.getValue())-1,Integer.parseInt(day.getValue()))));
    assertEquals("[Magazine{title=Forbes, publisher=Forbes, volume=134, genre='Economy', date=2022-03-12}, Magazine{title=Hola, publisher=Paquito, volume=128, genre='Sports', date=2019-08-01}]",magazineList.get().toString());
  }

  @Test void remove_removes_the_magazine() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine(new Magazine(title.getValue(),publisher.getValue(),Integer.parseInt(volume.getValue()),genre.getValue(),new Date(Integer.parseInt(year.getValue())-1900,Integer.parseInt(month.getValue())-1,Integer.parseInt(day.getValue()))));
    title.set("Hola");
    publisher.set("Paquito");
    volume.set("128");
    day.set("1");
    month.set("8");
    year.set("2019");
    genre.set("Sports");
    viewModel.addMagazine(new Magazine(title.getValue(),publisher.getValue(),Integer.parseInt(volume.getValue()),genre.getValue(),new Date(Integer.parseInt(year.getValue())-1900,Integer.parseInt(month.getValue())-1,Integer.parseInt(day.getValue()))));
    viewModel.removeMagazine(magazineList.get(0).getId());
    assertEquals("[Magazine{title=Hola, publisher=Paquito, volume=128, genre='Sports', date=2019-08-01}]",magazineList.get().toString());
  }




}
