module lt.jono.qr_gen {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires qrgen;
    requires itextpdf;

    opens lt.jono.qr_gen to javafx.fxml;
    exports lt.jono.qr_gen;
}