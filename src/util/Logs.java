package util;

public class Logs {
    private static String pcLogPath = "./Files/terraLog.txt";
    private static String satelitePath = "./Files/sateliteLog.txt";

    public static void writeTerraLog(String message) {
        Files.writeFile(pcLogPath, message);
    }

    public static void writeSateliteLog(String message) {
        Files.writeFile(satelitePath, message);
    }
}
