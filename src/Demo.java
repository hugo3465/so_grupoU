/**
 * Exemplo de utilização
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        Kernel kernel = new Kernel();
        kernel.start();

        MEM mem = new MEM();
        CPU cpu = new CPU();
        Middleware middleware = new Middleware();

        // Utilização dos componentes conforme necessário
        // ...

        kernel.shutdown();
    }
}
