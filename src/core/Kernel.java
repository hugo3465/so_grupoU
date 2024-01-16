package core;

import java.util.ArrayList;
import java.util.List;

import exceptions.OutOfMemmoryException;
import util.Configs;
import util.Logs;
import util.TaskScheduler;

/**
 * O Kernel representa o núcleo do sistema operativo.
 * Mantém as estruturas de dados, controla as tarefas a executar e lança a
 * execução dos outros componentes.
 */
public class Kernel {

    /** O middleware associado ao kernel. */
    private final Middleware middleware;
    private MEM mem;
    private CPU cpu;

    private Thread cpuThread; // acho que posso até tirar isto daqui

    /** A lista de tarefas em espera para execução. */
    // uma linkedBolckingQueue funciona como uma queue, mas a cabeça da fila é o
    // elemento que está na fila há mais tempo.
    protected TaskScheduler waitingTasks;

    /** A lista de tarefas atualmente em execução. */
    protected List<Task> tasksOnExecution;

    /**
     * A lista de tarefas que foram recentemente terminadas pelo sistema operativo.
     */
    /**
     * Array que contém todas as tarefas que acabaram de ser executadas pelo sistema
     * operativo.
     */
    protected List<Task> tasksTerminated;

    /**
     * Indica se o sistema operativo está ligado ou não
     */
    private boolean isOn;

    /**
     * Flag que indica se o sistema operativo está em processo de encerramento.
     * Vai permanecer como true, até que não haja mais nenhuma task a ser executada
     */
    private boolean isOnShutDownProcess;

    /**
     * Construtor da classe `Kernel`.
     *
     * @param middleware O middleware associado ao kernel.
     */
    public Kernel(Middleware middleware) {
        this.middleware = middleware;

        this.waitingTasks = new TaskScheduler(); // pode ter capacidade de 5
        this.isOn = false;
        this.isOnShutDownProcess = false;
        this.tasksOnExecution = new ArrayList<>();
        this.tasksTerminated = new ArrayList<>();
    }

    /**
     * Inicializa os sub-componentes e outras configurações do kernel.
     * Inicializa a CPU e a memória virtual (RAM), e inicia a thread da CPU.
     */
    public void start() {
        // Inicializar Unidade Central de Processamento (CPU)
        this.cpu = new CPU(this);

        // Inicializar Memória Virtual (RAM)
        this.mem = new MEM();

        cpuThread = new Thread(cpu);
        cpuThread.start();

        // TODO: testes, vários núcleos. Para apagar, APENAS TESTES PARA AUMENTAR
        // VELOCIDADE
        // Thread nucleo2 = new Thread(cpu);
        // nucleo2.start();
        // Thread nucleo3 = new Thread(cpu);
        // nucleo3.start();
        // Thread nucleo4 = new Thread(cpu);
        // nucleo4.start();
        // Thread nucleo5 = new Thread(cpu);
        // nucleo5.start();
        // Thread nucleo6 = new Thread(cpu);
        // nucleo6.start();

        this.isOn = true;
    }

    /**
     * Método para receber uma tarefa do Middleware e colocá-la na lista de espera
     * para ser processada pela CPU.
     * Só pode adicionar tarefas se o CPU não estiver em processo de encerramento.
     *
     * @param task A tarefa a ser recebida e agendada.
     */
    protected synchronized void receiveTask(Task task) {
        // só pode adicionar tarefas, se o CPU não estiver em processo de encerramento
        if (!isOnShutDownProcess && isOn) {

            synchronized (waitingTasks) {
                waitingTasks.add(task);
            }

            System.out.println("Tarefa agendada");

            // escrever no log
            Logs.writeTerraLog("Tarefa agendada");
        }
    }

    /**
     * Método protegido para enviar uma tarefa finalizada para o Middleware.
     * Desaloca a memória utilizada pela tarefa e a adiciona ao buffer do
     * Middleware.
     *
     * @param task A tarefa a ser enviada para o Middleware.
     */
    protected void sendToMiddleware(Task task) {
        try {
            middleware.receiveSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mem.deallocateMemmory(task.getMemory());

        synchronized (middleware.buffer) {
            middleware.buffer.addRear(task);
        }
    }

    /**
     * Encerra os sub-componentes e realiza procedimentos de encerramento do sistema
     * operativo.
     * Aguarda até que todas as tarefas em execução terminem antes de encerrar a CPU
     * e o sistema operativo.
     */
    public void shutdown() {
        this.isOnShutDownProcess = true;

        // descartar todas as tarefas que estavam em espera
        synchronized (waitingTasks) {
            waitingTasks.clear();
        }

        // Espera até que todas as tarefas em execução acabem, para encerrar o CPU
        while (!tasksOnExecution.isEmpty()) {
            try {
                // Aguarda um curto período de tempo antes de verificar novamente
                Thread.sleep(Configs.getPeriodoVerificacaoTarefasEmExecucao());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

        // apaga a lista das tarefas terminadas
        synchronized (tasksTerminated) {
            tasksTerminated.clear();
        }

        // quando não houver mais nenhuma tarefa a ser executada ca CPU, encerra o
        // sistema operativo
        this.isOn = false;
        this.isOnShutDownProcess = false;
    }

    /**
     * Obtém a quantidade de memória em uso no momento.
     *
     * @return A quantidade de memória em uso.
     */
    public int getMemoryOnUsage() {
        return mem.getUsedMemory();
    }

    /**
     * Reserva uma quantidade específica de memória para uma tarefa.
     *
     * @param memoryToReserve A quantidade de memória a ser reservada.
     * @throws OutOfMemmoryException Se não houver memória suficiente para alocar.
     */
    public synchronized void reserveMemory(int memoryToReserve) throws OutOfMemmoryException {
        mem.alocateMemmory(memoryToReserve);
    }

    /**
     * Verifica se o sistema operativo está em processo de encerramento.
     *
     * @return `true` se o sistema estiver em processo de encerramento, `false` caso
     *         contrário.
     */
    public boolean isOnShutoDownProcess() {
        return this.isOnShutDownProcess;
    }

    /**
     * Verifica se o sistema operativo está ligado.
     *
     * @return `true` se o sistema estiver ligado, `false` caso contrário.
     */
    public boolean isOn() {
        return isOn;
    }
}
