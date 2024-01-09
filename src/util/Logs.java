package util;

/**
 * A classe Logs fornece métodos para escrever logs no sistema.
 */
public class Logs {
    /**
     * Caminho do ficheiro de log para o computador.
     */
    private static String pcLogPath = "./Files/terraLog.txt";

    /**
     * Caminho do ficheiro de log para o satélite.
     */
    private static String satelitePath = "./Files/sateliteLog.txt";

    /**
     * Escreve uma mensagem de log no ficheiro do computador.
     *
     * @param message Mensagem a ser registrada no log.
     */
    public static void writeTerraLog(String message) {
        Files.writeFile(pcLogPath, message);
    }

    /**
     * Escreve uma mensagem de log no ficheiro do satélite.
     *
     * @param message Mensagem a ser registrada no log.
     */
    public static void writeSateliteLog(String message) {
        Files.writeFile(satelitePath, message);
    }
}
