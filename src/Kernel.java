/**
 * Kernel do sistema operativo
 * O que deve fazer:
 * - mantém as estruturas de dados e respetiva validação
 * - controla as tarefas a executar (prioridade provavelmente)(se calhar a linked blockingqueue tem de ficar aqui)
 * - lança a execução dos outros componentes e o seu término
 */
public class Kernel {
    // Estruturas de dados e validação
    // Controlo das tarefas a executar
    // Outras responsabilidades

    private final Middleware middleware;
    private MEM mem;
    private CPU cpu;

    private Thread cpuThread;

    public Kernel(Middleware middleware) {
        this.middleware = middleware;
    }

    // inicializa os sub-componentes e outras coisas
    public void start() {
        // Inicializar Unidade Central de Processamento (CPU)
        this.cpu = new CPU(this);

        // Inicializar Memória Virtual (RAM)
        this.mem = new MEM();

        cpuThread = new Thread(cpu);
        cpuThread.start();
    }

    // Método para receber uma tarefa do Middleware e enviar para a CPU
    // acho que podia nem estar syncronized este
    protected synchronized void receiveTask(Task task) {
        // Lógica para validar a tarefa, se necessário
        // ...

        // Adicionar a tarefa à fila da CPU
        cpu.addTask(task);

        // Lógica adicional, se necessário
        // ...

        // Enviar resposta ao Middleware
        //middleware.receive("Tarefa recebida com sucesso");
    }

    // acho que este pode nem estar syncronized
    protected synchronized void sendTask(Task task, String response) {
        middleware.receive(task, response);
    }

    // encerramento dos sub-componentes e outras coisas
    public void shutdown() {
        cpu.shutdown();
    }
}
