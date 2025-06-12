package model;

import java.util.Objects;

public class Operation {
    private final OperationType type;
    private final double amount;
    private final String to;

    public Operation(OperationType type, double amount, String to) {
        this.type = type;
        this.amount = amount;
        this.to = to;
    }

    public OperationType getType() { return type; }
    public double getAmount() { return amount; }
    public String getTo() { return to; }

    @Override
    public String toString() {
        return switch (type) {
            case BALANCE_INQUIRY -> "balance inquiry " + format(amount);
            case TRANSFERRED -> "transferred " + format(amount) + " to " + to;
            case RECEIVED -> "recived " + format(amount) + " from " + to;
            case WITHDREW -> "withdrew " + format(amount);
            case UNKNOWN -> "unknown operation";
        };
    }

    private String format(double value) {
        return String.format("%.2f", value);
    }

    public static Operation parse(String line) {
        try {
            if (line.startsWith("balance inquiry")) {
                double amount = Double.parseDouble(line.split(" ")[2]);
                return new Operation(OperationType.BALANCE_INQUIRY, amount, null);
            } else if (line.startsWith("transferred")) {
                String[] parts = line.split(" ");
                double amount = Double.parseDouble(parts[1]);
                String toUser = parts[3];
                return new Operation(OperationType.TRANSFERRED, amount, toUser);
            } else if (line.startsWith("withdrew")) {
                double amount = Double.parseDouble(line.split(" ")[1]);
                return new Operation(OperationType.WITHDREW, amount, null);
            }
        } catch (Exception e) {
            System.err.println("Ошибка при парсинге строки: " + line);
            return new Operation(OperationType.UNKNOWN, 0, null);
        }
        return new Operation(OperationType.UNKNOWN, 0, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Double.compare(operation.amount, amount) == 0 &&
                type == operation.type &&
                Objects.equals(to, operation.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, amount, to);
    }
}