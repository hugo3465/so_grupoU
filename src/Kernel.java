/**
 * Kernel do sistema operativo
 * O que deve fazer:
 *  - mantém as estruturas de dados e respetiva validação 
 *  - controla as tarefas a executar
 *  - lança a execução dos outros componentes e o seu término
 */
public class Kernel {
    // Estruturas de dados e validação
    // Controlo das tarefas a executar
    // Outras responsabilidades

    private MEM mem;
    private CPU cpu;

    // inicializa os sub-componentes e outras coisas
    public void start() {
        // Inicialização e execução dos subcomponentes
        this.mem = new MEM();
        this.cpu = new CPU();
    }

    // encerramento dos sub-componentes e outras coisas
    public void shutdown() {
        // Encerramento dos subcomponentes, se necessário
        // ...
    }
}
