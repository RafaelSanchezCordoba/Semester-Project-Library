package viewModel;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.ModelMagazine;
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
    this.error = new SimpleStringProperty("");
    ObservableList<Magazine> observableList = FXCollections.observableArrayList(
        new ArrayList<>());
    this.magazineList = new SimpleListProperty<>(observableList);

    viewModel.bindTitleTextField(title);
    viewModel.bindPublisherTextField(publisher);
    viewModel.bindVolumeTextField(volume);
    viewModel.bindDayTextField(day);
    viewModel.bindMontTextField(month);
    viewModel.bindYearTextField(year);
    viewModel.bindGenreTextField(genre);
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
    assertEquals("",error.get());
    assertEquals("[]",magazineList.get().toString());
  }

  @Test
   void setting_the_labels_doesnt_change_list_or_error() {
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

  @Test void add_adds_the_magazine() throws RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("[Forbes, Publisher: Forbes, Volume: 134, Genre: Economy, Date: 2022-03-12]",magazineList.get().toString());
  }

  @Test void add_multiple_magazines() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
    title.set("Hola");
    publisher.set("Paquito");
    volume.set("128");
    day.set("1");
    month.set("8");
    year.set("2019");
    genre.set("Sports");
    viewModel.addMagazine();
    assertEquals("[Forbes, Publisher: Forbes, Volume: 134, Genre: Economy, Date: 2022-03-12, Hola, Publisher: Paquito, Volume: 128, Genre: Sports, Date: 2019-08-01]",magazineList.get().toString());
  }

  @Test void adding_clear_fields() throws RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("",title.get());
    assertEquals("",publisher.get());
    assertEquals("",volume.get());
    assertEquals("",day.get());
    assertEquals("",month.get());
    assertEquals("",year.get());
    assertEquals("",genre.get());
    assertEquals("",error.get());
  }

  //removing can't be tested from the viewModel, because it requires the id which the magazine won't have until it gets to the storage or the database

  //errors

  @Test void null_title_gives_error_and_doesnt_add() throws RemoteException
  {
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("Title can't be null",error.get());
    assertEquals("[]",magazineList.get().toString());
  }

  @Test void null_publisher_gives_error_and_doesnt_add()
      throws SQLException, RemoteException
  {
    title.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("Publisher can't be null",error.get());
    assertEquals("[]",magazineList.get().toString());
  }

  @Test void negative_date_give_error_and_doesnt_add() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("-12");
    month.set("3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
     assertEquals("Invalid date",error.get());
    assertEquals("[]",magazineList.get().toString());
  }

  @Test void zero_date_give_error_and_doesnt_add() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("0");
    month.set("3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("Invalid date",error.get());
    assertEquals("[]",magazineList.get().toString());
  }

  @Test void february_29_date_give_error_and_doesnt_add() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("29");
    month.set("2");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("Invalid date",error.get());
    assertEquals("[]",magazineList.get().toString());
  }

  @Test void date_32_give_error_and_doesnt_add() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("32");
    month.set("3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("Invalid date",error.get());
    assertEquals("[]",magazineList.get().toString());
  }

  @Test void september_31_give_error_and_doesnt_add() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("31");
    month.set("9");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("Invalid date",error.get());
    assertEquals("[]",magazineList.get().toString());
  }

  @Test void future_date_give_error_and_doesnt_add() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("6");
    year.set("2023");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("Invalid date: future date",error.get());
    assertEquals("[]",magazineList.get().toString());
  }

  @Test void negative_month_give_error_and_doesnt_add() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("-3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("Invalid date",error.get());
    assertEquals("[]",magazineList.get().toString());
  }

  @Test void zero_month_give_error_and_doesnt_add() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("0");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("Invalid date",error.get());
    assertEquals("[]",magazineList.get().toString());
  }

  @Test void month_13_give_error_and_doesnt_add() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("13");
    year.set("2021");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("Invalid date",error.get());
    assertEquals("[]",magazineList.get().toString());
  }


  @Test void errors_clear_fields() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("-3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("",title.get());
    assertEquals("",publisher.get());
    assertEquals("",volume.get());
    assertEquals("",day.get());
    assertEquals("",month.get());
    assertEquals("",year.get());
    assertEquals("",genre.get());
  }

  @Test void correctly_adding_clear_errors() throws SQLException, RemoteException
  {
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("-3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("Invalid date",error.get());
    title.set("Forbes");
    publisher.set("Forbes");
    volume.set("134");
    day.set("12");
    month.set("3");
    year.set("2022");
    genre.set("Economy");
    viewModel.addMagazine();
    assertEquals("",error.get());
  }


  @Test
  public void error_cannot_be_set_outside_viewmodel() {
    assertThrows(RuntimeException.class, () -> error.set("Error"));
  }



}
