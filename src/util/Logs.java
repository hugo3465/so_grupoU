package util;

public class Logs {
    private static String pcLogPath = "./Files/pcLog.txt";
    private static String satelitePath = "./Files/sateliteLog.txt";

    public static void writePcLog(String message) {
        Files.writeFile(pcLogPath, message);
    }

    public static void writeSateliteLog(String message) {
        Files.writeFile(satelitePath, message);
    }
}
