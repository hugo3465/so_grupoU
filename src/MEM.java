/**
 * Unidade de memória
 */
public class MEM {
    // Armazenamento e manipulação de dados
    // Recursos necessários ao funcionamento do satélite

    // Armazenamento e manipulação de dados
    private String storedData;

    // Métodos para armazenar e recuperar dados
    public synchronized void storeData(String data) {
        storedData = data;
    }

    public synchronized String retrieveData() {
        return storedData;
    }
}
