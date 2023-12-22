import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Kernel do sistema operativo
 * O que deve fazer:
 * - mantém as estruturas de dados e respetiva validação
 * - controla as tarefas a executar (prioridade provavelmente)(se calhar a
 * linked blockingqueue tem de ficar aqui)
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

    // uma linkedBolckingQueue funciona como uma queue, mas a cabeça da fila é o
    // elemento que está na fila há mais tempo.
    protected LinkedBlockingQueue<Task> waitingTasks;

    protected ArrayList<Task> tasksOnExecution;

    /**
     * Array que contém todas as tarefas que acabaram de ser executadas pelo sistema
     * operativo.
     */
    protected ArrayList<Task> tasksTerminated;

    // acho que vou remover isto
    private volatile boolean isOnShutDownProcess; // Flag to indicate CPU shutdown

    public Kernel(Middleware middleware) {
        this.middleware = middleware;

        this.waitingTasks = new LinkedBlockingQueue<>(); // pode ter capacidade de 5
        this.isOnShutDownProcess = false;
        this.tasksOnExecution = new ArrayList<>();
        this.tasksTerminated = new ArrayList<>();
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

    // Método para receber uma tarefa do Middleware e colocar na lista de espera
    // para ser processada pela CPU
    // acho que podia nem estar syncronized este
    protected synchronized void receiveTask(Task task) {
        // só pode adicionar tarefas, se o CPU não estiver em processo de
        if (!isOnShutDownProcess) {
            try {
                // TODO: Reservar a memória na MEM
                waitingTasks.put(task);
                System.out.println("Tarefa agendada");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // acho que este pode nem estar syncronized
    protected synchronized void sendTask(Task task, String response) {
        middleware.receive(task, response);
    }

    // encerramento dos sub-componentes e outras coisas
    public void shutdown() {
        this.isOnShutDownProcess = true;
        waitingTasks.clear(); // Optionally, clear the task queue if you want to discard remaining tasks

        // Espera até que todas as tarefas a serem executadas acabem, para encerrar o
        // CPU
        while (!tasksOnExecution.isEmpty()) {
            try {
                // Aguarda um curto período de tempo antes de verificar novamente
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace(); // Lidar com a interrupção conforme necessário
            }
        }

    }

    public boolean isOnShutoDownProcess() {
        return this.isOnShutDownProcess;
    }
}
