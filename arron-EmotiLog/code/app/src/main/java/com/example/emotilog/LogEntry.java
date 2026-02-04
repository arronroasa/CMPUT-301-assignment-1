package com.example.emotilog;

/**
 * The logic for each logged entry. Entries have an associated
 * emotion and timestamp.
 */
public class LogEntry {
    private String emotion;
    private long timestamp;

    public LogEntry(String Emotion, long Timestamp) {
        this.emotion = Emotion;
        this.timestamp = Timestamp;
    }

    public String getEmotion() {
        return emotion;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
