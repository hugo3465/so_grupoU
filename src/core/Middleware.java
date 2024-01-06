package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;

import application.App;
import util.Logs;
import util.Buffer;

public class Middleware {
    private final int MIDDLEWARE_BUFFER_SEND_SIZE = 3;
    private final int MIDDLEWARE_BUFFER_RECEIVE_SIZE = 2;
    private final int MIDDLEWARE_SIZE = MIDDLEWARE_BUFFER_SEND_SIZE + MIDDLEWARE_BUFFER_RECEIVE_SIZE;

    private App application;
    private Kernel kernel;
    // como o middleware só tem espaço 5, ent criou-se um semáforo para controlar
    // quem entra
    private Semaphore sendSemaphore;
    private Semaphore receiveSemaphore;
    private Buffer<Task> buffer;

    public Middleware(App application) {
        this.application = application;
        this.kernel = new Kernel(this);

        this.sendSemaphore = new Semaphore(MIDDLEWARE_BUFFER_SEND_SIZE);
        this.receiveSemaphore = new Semaphore(MIDDLEWARE_BUFFER_RECEIVE_SIZE);
        this.buffer = new Buffer<>(MIDDLEWARE_SIZE);
    }

    // Método para enviar dados do satélite para a estação
    public synchronized void send(Task task) {
        System.out.println("A enviar dados");

        try {
            sendSemaphore.acquire();
            // synchronized (kernel) {
            //     kernel.receiveTask(task);
            // }

            synchronized(buffer) {

            }

        } catch (Exception e) { // mudar para InterruptedException
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Método para receber dados da estação para o satélite
     * 
     * @param taskThatAnswered identificador de qual task respondeu
     * @param response         resposta que a task deu
     */
    public void receive(Task taskThatAnswered, String response) {
        System.out.println("[" + taskThatAnswered.getName() + "] respondeu com: " + response);

        // escrever no log
        Logs.writeTerraLog("[" + taskThatAnswered.getName() + "] respondeu com: " + response);

        application.receiveTask(taskThatAnswered, response);

        semaphore.release();
    }

    public void turnOnOperatingSystem() {
        System.out.println("A iniciar o Sistema Operativo...");
        kernel.start();
        System.out.println("Ligado");

        // escrever no log
        Logs.writeSateliteLog("Ligado em: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    public void turnOffOperatingSystem() {
        System.out.println("A encerrar o Sistema Operativo...");
        kernel.shutdown();
        System.out.println("Encerrado");

        // escrever no log
        Logs.writeSateliteLog("Desligado em: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    public int getNumberOfWaitingTasks() {
        return kernel.waitingTasks.size();
    }

    public int getNumberOfExecutingTasks() {
        return kernel.tasksOnExecution.size();
    }

    public int getNumberOfFinishedTasks() {
        return kernel.tasksTerminated.size();
    }

    public boolean isOperational() {
        return kernel.isOn();
    }

    public int getMemoryOnUsage() {
        return kernel.getMemoryOnUsage();
    }
}