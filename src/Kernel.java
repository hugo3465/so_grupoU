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

    // Método para iniciar a execução do kernel

    // inicializa os sub-componentes e outras cenas
    public void start() {
        // Inicialização e execução dos subcomponentes
        MEM mem = new MEM();
        CPU cpu = new CPU();
        Middleware middleware = new Middleware(cpu, mem);

        Thread cpuThread = new Thread(cpu);
        
        // Exemplo de inicialização de tarefas
        cpu.addTask(() -> middleware.sendData("Dados do satélite"));
        
        // Iniciar a execução da CPU em uma thread separada
        cpuThread.start();
    }

    // encerramento dos sub-componentes e outras cenas
    public void shutdown() {
        // Encerramento dos subcomponentes, se necessário
        // ...
    }
}
