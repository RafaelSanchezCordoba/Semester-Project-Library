package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.MultimediaItem;

public class ReturnItemViewController
{
  private ViewHandler viewHandler;
  private Region root;

  @FXML private Label multimediaItemLabel;
  @FXML private Label errorLabelBook;
  @FXML private Label errorLabelMagazine;
  @FXML private Label selectedLibraryUserLabel;
  @FXML private TextField multimediaItemSearchTextField;
  @FXML private TextField ssnTextField;
  @FXML private ListView<MultimediaItem> magazineListView;
  @FXML private ListView<MultimediaItem> bookListView;

  
}
