package util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ViewFactory {
    public static Parent loadView(String fxmlFile) {
        try {
            return FXMLLoader.load(ViewFactory.class.getResource(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
