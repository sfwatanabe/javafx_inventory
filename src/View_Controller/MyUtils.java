package View_Controller;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * The MyAlerts class contains static methods to be used across multiple controller
 * files belonging to the View_Controller package within the Inventory project.
 *
 * @author Sakae Watanabe
 */
public class MyUtils {

  /**
   * The invalidPopup method will popup a warning message advising
   * the type of issue along with a short message to the user.
   *
   * @param warnType The type of warning being issued to the user.
   * @param message The context of the warning message to be displayed.
   */
  protected static void invalidPopup(String warnType, String message) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle(warnType);
    alert.setHeaderText(warnType);
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   * The confirmPopup method will open a confirmation dialog window for user
   * interaction and returns true when OK is clicked.
   *
   * @param event Action event passed through by calling method.
   * @param header String value to be used as the confirmation dialog header.
   * @param content String value to be used as the confirmation dialog content text.
   * @return True if the user clicks OK, false otherwise.
   */
  protected static boolean confirmPopup(ActionEvent event, String header, String content) {
    Alert confirmation = new Alert(AlertType.CONFIRMATION);
    confirmation.setTitle("Confirmation Notice");
    confirmation.setHeaderText(header);
    confirmation.setContentText(content);

    Optional<ButtonType> choice = confirmation.showAndWait();
    if (choice.get() == ButtonType.OK) {
      return true;
    }
    return false;
  }

}