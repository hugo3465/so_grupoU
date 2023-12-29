package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

import util.Logs;

public class Middleware {
    private final int MIDDLEWARE_SIZE = 5;

    // como o middleware só tem espaço 5, ent criou-se um semáforo para controlar
    // quem entra
    private Semaphore semaphore;
    private Kernel kernel;

    public Middleware() {
        this.semaphore = new Semaphore(MIDDLEWARE_SIZE);
        this.kernel = new Kernel(this);
    }

    // Método para enviar dados do satélite para a estação
    public synchronized void send(Task task) {
        System.out.println("A enviar dados");

        try {
            semaphore.acquire();
            synchronized (kernel) {
                kernel.receiveTask(task);
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
        Logs.writePcLog("[" + taskThatAnswered.getName() + "] respondeu com: " + response);

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
}