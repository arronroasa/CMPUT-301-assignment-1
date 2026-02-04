/**
 * Stores log entries in a list to be displayed.
 */
package com.example.emotilog;

import com.example.emotilog.LogEntry;

import java.util.ArrayList;

class Summary implements Logger {
    private static ArrayList<LogEntry> logs = new ArrayList<>();

    @Override
    public void log(String emotion) {
        LogEntry newEntry = new LogEntry(emotion, System.currentTimeMillis());
        logs.add(newEntry);
    }

    public static ArrayList<LogEntry> getLogs() {
        return logs;
    }
}
