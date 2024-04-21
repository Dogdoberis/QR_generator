package lt.jono.qr_gen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class QRCodeCenerator extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("qrcodegenerator-view.fxml")));
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("QRCode_generator_2.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setTitle("QR Code Generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args)  {
        launch();
   }
}