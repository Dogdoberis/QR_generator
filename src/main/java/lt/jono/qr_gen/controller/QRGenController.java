package lt.jono.qr_gen.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static lt.jono.qr_gen.generator.GenerateQRCodes.generateQRCodes;
import static lt.jono.qr_gen.utils.DialogBoxHelper.*;
import static lt.jono.qr_gen.utils.Validador.isValidNumber;

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
        Platform.exit();
    }


    @FXML
    private void handleResolutionMenuItemClicked(ActionEvent event) {
        MenuItem menuItem = ((MenuItem) event.getTarget());
        int resolution = Integer.parseInt(menuItem.getUserData().toString().trim());
        int defaultResolution = 60;
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
                numberSequenceError();
            } else if (isValidNumber(startNumber) && isValidNumber(endNumber)) {
                generateQRCodes(series, startNumber, endNumber, resolution);
                showQRGeneratedDialog();
            } else {
                numberError();
            }
        } catch (NumberFormatException e) {
            numericalError();
        }
    }
}
