import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {
    // Métodos para carregar configurações a partir de um arquivo
    // ...

    public Properties loadConfig(String configFile) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(configFile)) {
            properties.load(input);
        }
        return properties;
    }
}
