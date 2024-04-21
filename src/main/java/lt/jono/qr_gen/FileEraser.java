package lt.jono.qr_gen;

import java.io.File;
import java.util.List;

public class FileEraser {
    public static void deleteFiles(List<String> fileNames) {
        for (String fileName : fileNames) {
            File file = new File(fileName);
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("Failas " + fileName + " sėkmingai ištrintas.");
                } else {
                    System.out.println("Nepavyko ištrinti failo " + fileName + ".");
                }
            } else {
                System.out.println("Failas " + fileName + " neegzistuoja.");
            }
        }
    }
}
