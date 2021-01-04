package com.tokioschool;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import static com.tokioschool.properties.PropertiesAPP.*;
import static com.tokioschool.properties.PropertiesAPP.LANGUAGE_1;

public class PrimaryController {

    @FXML private static VBox PanelBack;
    @FXML private Button ButtonAccept;
    @FXML private Label LabelTitle;
    @FXML private Label LabelLine1;
    @FXML private Label LabelLine2;

    private ResourceBundle res;

    public static VBox getPanelBack() {
        return PanelBack;
    }

    @FXML
    private void switchToApp() throws IOException {
        Launcher.setRoot("app");
    }

    int count = 0;
    @FXML
    public void applyChanges() {
        if(count != 0) return;
        changeLanguage();
        changeLanguageComponents();
        count++;
    }

    public void changeLanguage() {
        Locale locale;
        if(CURRENT_LANGUAGE.equals(LANGUAGE_1)){
            locale = new Locale("en");
        }else{
            locale = new Locale("es");
        }
        res = ResourceBundle.getBundle("text", locale);
    }

    private void changeLanguageComponents() {
        ButtonAccept.setText(res.getString("continue"));
        LabelTitle.setText(res.getString("welcome_to"));
        LabelLine1.setText(res.getString("line_1"));
        LabelLine2.setText(res.getString("line_2"));
    }


}
