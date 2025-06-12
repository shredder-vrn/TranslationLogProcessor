import model.LogEntry;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            TransactionProcessor processor = new TransactionProcessor();
            Map<String, List<LogEntry>> userLogs = processor.parseAllFiles("input_logs");
            processor.writeUserLogs(userLogs, "transactions_by_users");

            System.out.println("Обработка завершена. Результат сохранён в директорию: transactions_by_users");

        } catch (Exception e) {
            System.err.println("Ошибка во время выполнения программы:");
            e.printStackTrace();
        }
    }
}