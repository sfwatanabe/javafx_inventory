package View_Controller;

import static Model.Inventory.lookupPart;
import static View_Controller.MyAlerts.confirmPopup;
import static View_Controller.MyAlerts.invalidPopup;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * The MainScreenController class controls the logic for the Main Screen
 * of the inventory application.
 *
 * @author Sakae Watanabe
 */
public class MainScreenController implements Initializable {

  @FXML
  private TextField mainPartSearchText;

  @FXML
  private Button mainPartSearchButton;

  @FXML
  private TableView<Part> mainPartTableView;

  @FXML
  private TableColumn<Part, Integer> mainPartIDColumn;

  @FXML
  private TableColumn<Part, String> mainPartNameColumn;

  @FXML
  private TableColumn<Part, Integer> mainPartInvColumn;

  @FXML
  private TableColumn<Part, Double> mainPartPriceColumn;

  @FXML
  private Button mainAddPartButton;

  @FXML
  private Button mainModPartButton;

  @FXML
  private Button mainDeletePartButton;

  @FXML
  private TextField mainProductSearchText;

  @FXML
  private Button mainProductSearchButton;

  @FXML
  private TableView<Product> mainProductTableView;

  @FXML
  private TableColumn<Product, Integer> mainProductIDColumn;

  @FXML
  private TableColumn<Product, String> mainProductNameColumn;

  @FXML
  private TableColumn<Product, Integer> mainProductInvColumn;

  @FXML
  private TableColumn<Product, Double> mainProductPriceColumn;

  @FXML
  private Button mainAddProductButton;

  @FXML
  private Button mainModProductButton;

  @FXML
  private Button mainDeleteProductButton;

  @FXML
  private Button mainExitButton;

  /**
   * The Initialize method sets up items for both Part and Product
   * table views.
   * TODO: update javadoc for Main Screen initialize method.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // Setup parts table and load full list from Inventory.
    mainPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    mainPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    mainPartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
    mainPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    mainPartTableView.setItems(Inventory.getAllParts());

    // Setup products table and load full list from Inventory.
    mainProductIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    mainProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    mainProductInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
    mainProductPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    mainProductTableView.setItems(Inventory.getAllProducts());

  }

  /**
   * The addPartButtonPushed method handles changing the scene when the addPartButton
   * is pushed. A call is made to the AddModifyPartController initAddPart method to prepare
   * label for the scene.
   *
   * @param event Event captured when user pushes the addPartButton.
   */
  @FXML
  private void addPartButtonPushed(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/View_Controller/AddModifyPart.fxml"));
    Parent addPartParent = loader.load();
    Scene addPartScene = new Scene(addPartParent);

    AddModifyPartController controller = loader.getController();
    controller.initAddPart();

    Stage addPartWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
    addPartWindow.setScene(addPartScene);
    addPartWindow.show();
  }

  /**
   * The modPartButtonPushed method handles changing the scene when the modPartButton
   * is pushed. A call is made to the AddModifyPartController initModPart method to prepare
   * label and text fields for the modify part scene. Popup dialog is generated if user has
   * no item selected.
   *
   * @param event Event captured when user pushes the modPartButton.
   */
  @FXML
  private void modPartButtonPushed(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/View_Controller/AddModifyPart.fxml"));
    Parent modPartParent = loader.load();
    Scene modPartScene = new Scene(modPartParent);

    Part selectedPart = mainPartTableView.getSelectionModel().getSelectedItem();
    if (selectedPart != null) {
      AddModifyPartController controller = loader.getController();
      controller.initModPart(selectedPart);

      Stage addPartWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
      addPartWindow.setScene(modPartScene);
      addPartWindow.show();
    } else {
      invalidPopup("No Item Selected", "Please select a part to modify.");
    }
  }

  /**
   * The deletePartButtonPushed method will delete the selected part from the mainPartTableView
   * after user confirms the action. Popup dialog is generated if user has no item selected.
   *
   * @param event Event captured when user pushes the deletePartButton.
   */
  @FXML
  private void deletePartButtonPushed(ActionEvent event) {
    Part selectedPart = mainPartTableView.getSelectionModel().getSelectedItem();
    if (selectedPart != null && (confirmPopup(event, "Are You Sure?",
                          "Part will be permanently deleted from Inventory."))) {
        Inventory.deletePart(selectedPart);
    } else {
      invalidPopup("No Part Selected", "Please select a part to delete.");
    }
  }

  @FXML
  private void addProductButtonPushed(ActionEvent event) {

  }


  @FXML
  private void deleteProductButtonPushed(ActionEvent event) {

  }

  @FXML
  private void exitButtonPushed(ActionEvent event) {

  }

  /**
   * The mainSearchPartHandler method responds to user input in the searchPart field
   * to either lookup a list of products matching the given ID or product name. If no
   * results matched or user entered empty query a popup is generated letting the user
   * know that no results were found and the original list is restored.
   *
   * @param event Action event generated by user pushing searchPart button OR pressing
   *              Enter key after query has been typed.
   */
  @FXML
  private void mainSearchPartHandler(ActionEvent event) {
    String searchPart = mainPartSearchText.getText().trim();
    ObservableList<Part> searchPartResults = FXCollections.observableArrayList();
    try {
        int id = Integer.parseInt(searchPart);
        searchPartResults.add(Inventory.lookupPart(id));
    } catch (NumberFormatException e) {
        searchPartResults.addAll(Inventory.lookupPart(searchPart));
    }
    if (searchPartResults.isEmpty() || searchPart.equals("")) {
      mainPartSearchText.clear();
      invalidPopup("No Results", "No results were found matching input.");
      System.out.println("This happened");
      System.out.println(searchPartResults);
      mainPartTableView.setItems(Inventory.getAllParts());
    } else {
      mainPartTableView.setItems(searchPartResults);
    }
  }

  @FXML
  private void mainSearchProductHandler(ActionEvent event) {

  }


  @FXML
  private void modProductButtonPushed(ActionEvent event) {

  }
}
