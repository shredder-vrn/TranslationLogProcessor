package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEntry {
    private final LocalDateTime timestamp;
    private final String user;
    private final Operation operation;

    public LogEntry(LocalDateTime timestamp, String user, Operation operation) {
        this.timestamp = timestamp;
        this.user = user;
        this.operation = operation;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public String getUser() { return user; }
    public Operation getOperation() { return operation; }

    public static LocalDateTime parseTimestamp(String str) {
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return String.format("[%s] %s %s",
                timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                user,
                operation);
    }
}