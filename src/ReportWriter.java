import model.LogEntry;
import model.Operation;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;


public class ReportWriter {

    public static void writeUserLogs(Map<String, List<LogEntry>> userLogs, String outputDir) throws IOException {
        Files.createDirectories(Paths.get(outputDir));

        for (Map.Entry<String, List<LogEntry>> entry : userLogs.entrySet()) {
            writeUserLogFile(entry.getKey(), entry.getValue(), outputDir);
        }
    }

    private static void writeUserLogFile(String user, List<LogEntry> entries, String outputDir) throws IOException {
        entries.sort(Comparator.comparing(LogEntry::getTimestamp));

        Path outFile = Paths.get(outputDir, user + ".log");
        try (BufferedWriter writer = Files.newBufferedWriter(outFile)) {
            for (LogEntry entry : entries) {
                writer.write(entry.toString());
                writer.newLine();
            }

            double balance = calculateFinalBalance(entries);
            String finalLine = String.format("[%s] %s final balance %.2f",
                    LocalDateTime.now(), user, balance);
            writer.write(finalLine);
        }
    }

    private static double calculateFinalBalance(List<LogEntry> entries) {
        double balance = 0;
        for (LogEntry entry : entries) {
            Operation op = entry.getOperation();
            switch (op.getType()) {
                case BALANCE_INQUIRY:
                    balance = op.getAmount();
                    break;
                case TRANSFERRED:
                case WITHDREW:
                    balance -= op.getAmount();
                    break;
                case RECEIVED:
                    balance += op.getAmount();
                    break;
                default:
                    break;
            }
        }
        return balance;
    }
}