package com.tokioschool.thread;

import javafx.scene.layout.AnchorPane;

import static com.tokioschool.properties.PropertiesAPP.*;

public class TaskShowAlert implements Runnable {
    private final AnchorPane panel;

    public TaskShowAlert(AnchorPane pane) {
        this.panel = pane;
    }

    private void showAlert() {
        panel.setVisible(true);
    }

    private void hideAlert() {
        panel.setVisible(false);
    }

    @Override
    public void run() {
        showAlert();
        try {
            Thread.sleep(SECONDS_ALERT * 1000);
        } catch (InterruptedException interruptedException) {
            if(DEBUG_MODE){
                interruptedException.printStackTrace();
            }
        }
        hideAlert();
    }
}
