package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

import util.Logs;
import util.Buffer;

/**
 * Representa o Middleware do sistema, que atua como uma interface entre o
 * satélite e o sistema operativo.
 * Controla a comunicação entre o satélite e o Kernel.
 */
public class Middleware {
    private final int MIDDLEWARE_BUFFER_SEND_SIZE = 3;
    private final int MIDDLEWARE_BUFFER_RECEIVE_SIZE = 2;
    private final int MIDDLEWARE_SIZE = MIDDLEWARE_BUFFER_SEND_SIZE + MIDDLEWARE_BUFFER_RECEIVE_SIZE;

    private Kernel kernel;

    /**
     * Estrutura de dadsos que controla a leitura e a escrita de tasks, tem um
     * espaço de 5 unidades, sendo 3 delas para enviar as tasks apra o kernel, e 2
     * para enviar para a aplicação
     */
    protected Buffer<Task> buffer;

    /**
     * Semáforo que controla a escrita no buffer, só pode ter 3 tasks para enviar
     * para o kernel no buffer
     */
    protected Semaphore sendSemaphore;

    /**
     * Semáforo que controla a leitura no buffer, só pode ter 2 tasks para enviar
     * para a aplicação no buffer
     */
    protected Semaphore receiveSemaphore;

    /**
     * Construtor da classe `Middleware`.
     * Inicializa o kernel, os semáforos e o buffer.
     */
    public Middleware() {
        this.kernel = new Kernel(this);

        this.sendSemaphore = new Semaphore(MIDDLEWARE_BUFFER_SEND_SIZE);
        this.receiveSemaphore = new Semaphore(MIDDLEWARE_BUFFER_RECEIVE_SIZE);
        this.buffer = new Buffer<>(MIDDLEWARE_SIZE);
    }

    /**
     * Método para enviar dados do satélite para a estação.
     *
     * @param task A tarefa a ser enviada.
     */
    public synchronized void send(Task task) {
        System.out.println("A enviar dados");

        try {
            sendSemaphore.acquire();

            // quando uma taské colocada no buffer, é colocada sempre na frente
            synchronized (buffer) {
                buffer.addFront(task);
            }

            Task taskToSendToKernel = null;
            synchronized (buffer) {
                taskToSendToKernel = buffer.removeFront();
            }

            kernel.receiveTask(taskToSendToKernel);

            sendSemaphore.release();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Método para receber dados da estação para o satélite.
     *
     * @return A tarefa recebida.
     */
    public synchronized Task receive() {

        Task answeredTask;
        synchronized (buffer) {
            answeredTask = buffer.removeRear();
        }

        // caso não haja nehuma Task no buffer para receber, retorna null
        if (answeredTask == null) {
            return null;
        }

        String taskName = answeredTask.getName();
        String taskResponse = answeredTask.getResponse();

        System.out.println("[" + taskName + "] respondeu com: " + taskResponse);

        // escrever no log
        Logs.writeTerraLog("[" + taskName + "] respondeu com: " + taskResponse);

        receiveSemaphore.release();

        return answeredTask;
    }

    /**
     * Inicia o sistema operativo (Kernel) e registra o evento no log.
     */
    public void turnOnOperatingSystem() {
        System.out.println("A iniciar o Sistema Operativo...");
        kernel.start();
        System.out.println("Ligado");

        // escrever no log
        Logs.writeSateliteLog("Ligado em: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    /**
     * Encerra o sistema operativo (Kernel) e registra o evento no log.
     */
    public void turnOffOperatingSystem() {
        System.out.println("A encerrar o Sistema Operativo...");
        kernel.shutdown();
        System.out.println("Encerrado");

        // escrever no log
        Logs.writeSateliteLog("Desligado em: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    /**
     * Obtém o número de tarefas em espera no sistema operativo.
     *
     * @return O número de tarefas em espera.
     */
    public int getNumberOfWaitingTasks() {
        return kernel.waitingTasks.size();
    }

    /**
     * Obtém o número de tarefas em execução no sistema operativo.
     *
     * @return O número de tarefas em execução.
     */
    public int getNumberOfExecutingTasks() {
        return kernel.tasksOnExecution.size();
    }

    /**
     * Obtém o número de tarefas concluídas no sistema operativo.
     *
     * @return O número de tarefas concluídas.
     */
    public int getNumberOfFinishedTasks() {
        return kernel.tasksTerminated.size();
    }

    /**
     * Verifica se o sistema operativo está operacional.
     *
     * @return `true` se o sistema operativo estiver operacional, `false` caso
     *         contrário.
     */
    public boolean isOperational() {
        return kernel.isOn();
    }

    /**
     * Obtém a quantidade de memória em uso no sistema operativo.
     *
     * @return A quantidade de memória em uso.
     */
    public int getMemoryOnUsage() {
        return kernel.getMemoryOnUsage();
    }
}