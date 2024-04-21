package lt.jono.qr_gen.controller;

import com.itextpdf.text.DocumentException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static lt.jono.qr_gen.generator.GenerateQRCodes.generateQRCodes;
import static lt.jono.qr_gen.utils.DialogBoxHelper.*;
import static lt.jono.qr_gen.utils.Validador.isValidNumber;

public class QRGenController implements Initializable {
    private int selectedResolution;

    @FXML
    private Spinner<Integer> marginTop;

    @FXML
    private Spinner<Integer> marginBottom;

    @FXML
    private Spinner<Integer> marginLeft;

    @FXML
    private Spinner<Integer> marginRight;

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
        int defaultResolution = 60;
        int resolution = Integer.parseInt(menuItem.getUserData().toString().trim());
        selectedResolution = (selectedResolution == 0) ? defaultResolution : selectedResolution;
        resolutionMenuButton.setText("Rezoliucija: " + resolution + " pix");
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
            int marginTopValue = marginTop.getValue();
            int marginBottomValue = marginBottom.getValue();
            int marginLeftValue = marginLeft.getValue();
            int marginRightValue = marginRight.getValue();
            if (startNumber > endNumber) {
                numberSequenceError();
            } else if (isValidNumber(startNumber) && isValidNumber(endNumber)) {
                generateQRCodes(series,
                        startNumber,
                        endNumber,
                        resolution,
                        marginTopValue,
                        marginBottomValue,
                        marginLeftValue,
                        marginRightValue);
                showQRGeneratedDialog();
            } else {
                numberError();
            }
        } catch (NumberFormatException e) {
            numericalError();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

@Override
    public void initialize(URL location, ResourceBundle resources) {
        setupSpinne(marginTop, 10,50,1);
        setupSpinne(marginBottom, 10,50,1);
        setupSpinne(marginLeft, 10,50,1);
        setupSpinne(marginRight, 10,50,1);
}
private void setupSpinne(Spinner<Integer> spinner, int min, int max, int step) {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, step));
}
}
