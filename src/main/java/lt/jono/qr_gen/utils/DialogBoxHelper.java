package lt.jono.qr_gen.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DialogBoxHelper {

    public static void showQRGeneratedDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("QR Kodai Sugeneruoti");
        alert.setHeaderText(null);
        alert.setContentText("QR kodai buvo sėkmingai sugeneruoti!");
        ButtonType okButton = new ButtonType("Gerai");
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }


    public static void numberSequenceError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Klaida:");
        alert.setHeaderText(null);
        alert.setContentText("Pradžios skaičius negali būti didesnis už pabaigos skaičiu!");
        ButtonType okButton = new ButtonType("Gerai");
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }


    public static void numberError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Klaida:");
        alert.setHeaderText(null);
        alert.setContentText("skaičius negali būti nulinis!");
        ButtonType okButton = new ButtonType("Gerai");
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }


    public static void numericalError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Klaida:");
        alert.setHeaderText(null);
        alert.setContentText("įvedėte ne skaičių!!");
        ButtonType okButton = new ButtonType("Gerai");
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }
}
