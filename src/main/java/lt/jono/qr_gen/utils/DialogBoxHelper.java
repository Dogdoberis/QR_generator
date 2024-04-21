package lt.jono.qr_gen.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DialogBoxHelper {

    public static void showQRGeneratedDialog() {
        showAlert("QR Kodai Sugeneruoti", null, "QR kodai buvo sėkmingai sugeneruoti!");
    }

    public static void numberSequenceError() {
        showAlert("Klaida:", "KLAIDA", "Pradžios skaičius negali būti didesnis už pabaigos skaičiu!");
    }

    public static void numberError() {
        showAlert("Klaida:", "KLAIDA", "skaičius negali būti nulinis!");
    }

    public static void numericalError() {
        showAlert("Klaida:", "KALAIDA", "įvedėte ne skaičių!!");
    }
    public static void qrGeneratedError(String message) {
        showAlert("Klaida:" ,message,"Klaida kuriant QR kodą:");
    }
    public static void qrConfigFileError() {
        showAlert("Klaida:" ,null,"Atsiprašau neina rasti config.properties failo arba jis sugadintas");
    }
//    public static  void resolutionSelection(String message) {
//        showAlert("Klaida:" ,message,"Resoliucijia: ");
//    }

    private static void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        ButtonType okButton = new ButtonType("Gerai");
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }
}
