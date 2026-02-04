/**
 * The logic for a button press. Assigns an emotion to a button
 * and handles what happens when said button is clicked.
 */
package com.example.emotilog;

public class ButtonLogic {
    private Logger logger;
    private String emotion;
    public ButtonLogic(Logger logger, String emotion) {
        this.logger = logger;
        this.emotion = emotion;
    }

    public void logEmotion() {
        logger.log(this.emotion);
    }
}