package com.tokioschool.properties;

import com.tokioschool.util.DateUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

public class PropertiesAPP {
    public static final String CHANGE_BACKGROUND_COLOR = "-fx-background-color: ";

    public static final boolean DEBUG_MODE = true;

    public static final String TYPE_NOTE = "NOTE";
    public static final String TYPE_REMINDER = "REMINDER";

    public static final String COLOR_THEME_1 = "#14213d;";
    public static final String COLOR_THEME_2 = "#372549;";
    public static final String COLOR_THEME_3 = "#0b3954;";
    public static final String COLOR_THEME_4 = "#bc4749;";
    public static final String COLOR_THEME_5 = "#354f52;";
    public static final String COLOR_THEME_6 = "#143642;";
    public static final String COLOR_THEME_7 = "#333533;";
    public static final String COLOR_THEME_8 = "#481d24;";

    public static final String LANGUAGE_1 = "ENGLISH";
    public static final String LANGUAGE_2 = "SPANISH";

    public static final String NOTIFICATION_OFF = "OFF";
    public static final String NOTIFICATION_ON = "ON";

    public static String CURRENT_THEME = COLOR_THEME_4;
    public static String CURRENT_LANGUAGE = LANGUAGE_1;
    public static String LAST_CONNECTION = DateUtil.localDateFormat(LocalDate.now());
    public static String NOTIFICATION_DELETE = NOTIFICATION_ON;

    public static final String COLOR_ALERT = " #9e2a2b";
    public static final String COLOR_CONFIRM = "#caffbf";

    public static final int SECONDS_ALERT = 4;

    public static void loadProperties() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/java/configuration.properties"));
            CURRENT_LANGUAGE = props.getProperty("language");
            CURRENT_THEME = props.getProperty("color_theme");
            NOTIFICATION_DELETE = props.getProperty("notification_delete");
        } catch (IOException ioException) {
            if(DEBUG_MODE) {
                ioException.printStackTrace();
            }
        }
    }

    public static void saveProperties() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/java/configuration.properties"));
        props.setProperty("language", CURRENT_LANGUAGE);
        props.setProperty("color_theme", CURRENT_THEME);
        props.setProperty("notification_delete", NOTIFICATION_DELETE);
        props.store(new FileOutputStream("src/main/java/configuration.properties"), "Application configuration");

    }

    public static void loadLastConnection() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/java/configuration.properties"));
            LAST_CONNECTION = props.getProperty("last_connection");
        } catch (IOException ioException) {
            if(DEBUG_MODE) {
                ioException.printStackTrace();
            }
        }
    }

    public static void saveLastConnection() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/java/configuration.properties"));
        props.setProperty("last_connection", DateUtil.localDateFormat(LocalDate.now()));
        props.store(new FileOutputStream("src/main/java/configuration.properties"), "Application configuration");
    }
}
