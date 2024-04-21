package lt.jono.qr_gen.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import static lt.jono.qr_gen.generator.GenerateQRCodes.generateQRCodes;

public class QRGenController {

    private int selectedResolution;

    private final int defaultResolution = 60;

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
        Platform.exit();
    }


    @FXML
    private void handleResolutionMenuItemClicked(ActionEvent event) {
        MenuItem menuItem = ((MenuItem) event.getTarget());
        int resolution = Integer.parseInt(menuItem.getUserData().toString().trim());
        selectedResolution = (selectedResolution == 0) ? defaultResolution : selectedResolution;
        resolutionMenuButton.setText("Pasirinkta rezoliucija: " + resolution + " pix");
        selectedResolution = resolution;
    }

    @FXML
    private void generateButtonClicked() {
        String series = seriesField.getText();
        int startNumber;
        int endNumber;
        try {
            startNumber = Integer.parseInt(fromField.getText());
            endNumber = Integer.parseInt(toField.getText());
            int resolution = selectedResolution;
            if (startNumber > endNumber) {
                System.out.println("Klaida: pradžios skaičius negali būti didesnis už pabaigos skaičiu");
            } else if (isValidNumber(startNumber) && isValidNumber(endNumber)) {
                generateQRCodes(series, startNumber, endNumber, resolution);
                showQRGeneratedDialog();
            } else {
                System.out.println("Klaida: įvedėte netinkamus skaičius");
            }
        } catch (NumberFormatException e) {
            System.out.println("!!Klaida: įvedėte ne skaičių!!");
        }
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

    public boolean isValidNumber(int number) {
        return number > 0;
    }
}
