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
import static lt.jono.qr_gen.utils.QRCodeLayoutHelper.getRowsAndColumns;
import static lt.jono.qr_gen.utils.Validador.isValidNumber;

public class QRGenController implements Initializable {

    private int selectedResolution;

    @FXML
    public Label qrCodes;

    @FXML
    public Label totalPages;

    @FXML
    public Label qrCodesPerPage;

    @FXML
    public Label qrCodesInLastPage;


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
        int resolution = Integer.parseInt(menuItem.getUserData().toString().trim());
        resolutionMenuButton.setText("Rezoliucija: " + resolution + " pix");
        selectedResolution = resolution;
        labelQRCodes();
        labePages();
        qrCodesPerPage();
        qrCodesInLastPage();

    }

    @FXML
    private void labelQRCodes() {
        try {
            int x = Integer.parseInt(fromField.getText());
            int y = Integer.parseInt(toField.getText());
            int codes = y - x + 1;
            qrCodes.setText(String.valueOf(codes));
        } catch (NumberFormatException e) {
            generateDialogBox();
        }
    }

    @FXML
    private void labePages() {
        try {
            int x = Integer.parseInt(fromField.getText());
            int y = Integer.parseInt(toField.getText());
            int rezoliucija = selectedResolution;
            int[] rowsAndColumns = getRowsAndColumns((float) rezoliucija);
            int rows = rowsAndColumns[0];
            int columns = rowsAndColumns[1];
            int pages = Math.abs((x + 1 - y) / (rows * columns)) + 1;
            totalPages.setText(String.valueOf(pages));
        } catch (NumberFormatException e) {
            generateDialogBox();
        }
    }

    @FXML
    private void qrCodesPerPage() {
        int[] rowsAndColumns = getRowsAndColumns((float) selectedResolution);
        int rows = rowsAndColumns[0];
        int columns = rowsAndColumns[1];
        int qrCodes = rows * columns;
        qrCodesPerPage.setText(String.valueOf(qrCodes));
    }

    @FXML
    private void qrCodesInLastPage() {
        try {
            int x = Integer.parseInt(fromField.getText());
            int y = Integer.parseInt(toField.getText());
            int[] rowsAndColumns = getRowsAndColumns((float) selectedResolution);
            int rows = rowsAndColumns[0];
            int columns = rowsAndColumns[1];
            int pages = Math.abs((x + 1 - y) / (rows * columns)) + 1;
            int qrCodesLeftover = (pages * (rows * columns)) - (y - x + 1);
            qrCodesInLastPage.setText(String.valueOf(qrCodesLeftover));

        }catch (Exception e) {
            generateDialogBox();
        }
    }


    @FXML
    private void generateButtonClicked() {
        String series = seriesField.getText();
        int startNumber;
        int endNumber;
        try {
            startNumber = Integer.parseInt(fromField.getText());
            endNumber = Integer.parseInt(toField.getText());
            if (selectedResolution == 0) {
                resolutionSelection();
                return;
            }
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
        setupSpinne(marginTop, 60, 20);
        setupSpinne(marginBottom, 90, 50);
        setupSpinne(marginLeft, 60, 35);
        setupSpinne(marginRight, 60, 35);
    }

    private void setupSpinne(Spinner<Integer> spinner, int max, int initialValue) {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, max, initialValue, 1));

    }
}


