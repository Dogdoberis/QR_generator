package lt.jono.qr_gen;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static lt.jono.qr_gen.GenerateQRCodes.generateQRCodes;

public class QRGenController {

    private int selectedResolution;

    @FXML
    private TextField seriesField;

    @FXML
    private TextField fromField;

    @FXML
    private TextField toField;

    @FXML
    private SplitMenuButton resolutionMenuButton;


    @FXML
    private void handleExitButton() {
        Platform.exit(); // Uždarome programą
    }


    @FXML
    private void handleResolutionMenuItemClicked(ActionEvent event) {
        MenuItem menuItem = ((MenuItem) event.getTarget());
        int resolution = Integer.parseInt(menuItem.getUserData().toString().trim());
        System.out.println("Pasirinkta rezoliucija: " + resolution + " pix");
        resolutionMenuButton.setText("Pasirinkta rezoliucija: " + resolution + " pix");
        selectedResolution = resolution;
        //generateButtonClicked(); // Perduodama rezoliucija
    }

    @FXML
    private void generateButtonClicked() {
        String series = seriesField.getText();
        int startNumber = Integer.parseInt(fromField.getText());
        int endNumber = Integer.parseInt(toField.getText());
        int resolution = selectedResolution;
        generateQRCodes(series, startNumber, endNumber, resolution);
        showQRGeneratedDialog();
    }
    @FXML
    private void showQRGeneratedDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("QR Kodai Sugeneruoti");
        alert.setHeaderText(null);
        alert.setContentText("QR kodai buvo sėkmingai sugeneruoti!");

        ButtonType okButton = new ButtonType("Gerai");
        alert.getButtonTypes().setAll(okButton);

        alert.showAndWait();
    }
}
