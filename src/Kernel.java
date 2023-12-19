/**
 * Kernel do sistema operativo
 * O que deve fazer:
 * - mantém as estruturas de dados e respetiva validação
 * - controla as tarefas a executar
 * - lança a execução dos outros componentes e o seu término
 */
public class Kernel {
    // Estruturas de dados e validação
    // Controlo das tarefas a executar
    // Outras responsabilidades

    private MEM mem;
    private CPU cpu;

    private Thread cpuThread;

    // inicializa os sub-componentes e outras coisas
    public void start() {
        System.out.println("A iniciar o Sistema Operativo");
        // Inicialização e execução dos subcomponentes
        this.mem = new MEM();
        this.cpu = new CPU();

        cpuThread = new Thread(cpu);
        cpuThread.start();
    }

    // Método para receber uma tarefa do Middleware e enviar para a CPU
    public void receiveTask(Task task) {
        // Lógica para validar a tarefa, se necessário
        // ...

        // Adicionar a tarefa à fila da CPU
        cpu.addTask(task);

        // Lógica adicional, se necessário
        // ...

        // Enviar resposta ao Middleware
        //middleware.receive("Tarefa recebida com sucesso");
    }

    // encerramento dos sub-componentes e outras coisas
    public void shutdown() {
        System.out.println("A encerrar o Sistema Operatiivo");
        cpuThread.interrupt();
    }
}
