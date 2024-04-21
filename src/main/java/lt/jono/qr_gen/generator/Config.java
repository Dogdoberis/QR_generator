package lt.jono.qr_gen.generator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static lt.jono.qr_gen.utils.DialogBoxHelper.qrConfigFileError;

public class Config {
    static int frameWidth;
    static int qrWidth;
    static int qrHeight;
    static int margin;
    static int textHeight;
    static int arcWidth;
    static int arcHeight;
    static int fontSize;

    static  {
        try {
            InputStream input = Generator.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                qrConfigFileError();
            }
            Properties prop = new Properties();
            prop.load(input);
            frameWidth = Integer.parseInt(prop.getProperty("frameWidth"));
            qrWidth = Integer.parseInt(prop.getProperty("qrWidth"));
            qrHeight = Integer.parseInt(prop.getProperty("qrHeight"));
            margin = Integer.parseInt(prop.getProperty("margin"));
            textHeight = Integer.parseInt(prop.getProperty("textHeight"));
            arcWidth = Integer.parseInt(prop.getProperty("arcWidth"));
            arcHeight = Integer.parseInt(prop.getProperty("arcHeight"));
            fontSize = Integer.parseInt(prop.getProperty("fontSize"));

        }catch (IOException e){
            qrConfigFileError();
        }
    }

}
