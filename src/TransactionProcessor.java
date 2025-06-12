import model.LogEntry;
import java.io.IOException;
import java.util.*;

public class TransactionProcessor {

    public Map<String, List<LogEntry>> parseAllFiles(String inputDir) throws IOException {
        return LogParser.parseAllFiles(inputDir);
    }

    public void writeUserLogs(Map<String, List<LogEntry>> userLogs, String outputDir) throws IOException {
        ReportWriter.writeUserLogs(userLogs, outputDir);
    }
}