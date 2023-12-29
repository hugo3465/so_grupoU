package core;

import enums.TaskPriority;
import util.Configs;
import util.Logs;

/**
 * Representa uma tarefa a ser executada pelo sistema operativo.
 * As tarefas são unidades de trabalho que podem ser agendadas e executadas pela
 * CPU.
 */
public class Task implements Runnable {

    /**
     * Instância da CPU responsável pela execução das tarefas.
     */
    private static CPU cpu;

    /**
     * Nome da tarefa.
     */
    private String name;

    /**
     * Mensagem associada à tarefa.
     */
    private String msg;

    /**
     * Quantidade de memória que a tarefa utilizará.
     */
    private int memory;

    /**
     * Prioridade da tarefa (Alta ou Baixa).
     */
    private TaskPriority priority;

    /**
     * Tempo estimado para a execução da tarefa, em milissegundos.
     */
    private int expectedTime;

    /**
     * Construtor da classe Task.
     *
     * @param name         Nome da tarefa.
     * @param msg          Mensagem associada à tarefa.
     * @param memory       Quantidade de memória que a tarefa utilizará.
     * @param priority     Prioridade da tarefa (Alta ou Baixa).
     * @param expectedTime Tempo estimado para a execução da tarefa, em
     *                     milissegundos.
     */
    public Task(String name, String msg, int memory, TaskPriority priority, int expectedTime) {
        this.name = name;
        this.msg = msg;
        this.memory = memory;
        this.priority = priority;
        this.expectedTime = expectedTime;
    }

    /**
     * Método estático para definir a instância da CPU responsável pela execução das
     * tarefas.
     *
     * @param cpuInstance Instância da CPU.
     */
    protected static void setCPU(CPU cpuInstance) {
        cpu = cpuInstance;
    }

    @Override
    public void run() {
        try {
            // escrever na consola
            System.out.println("Tarefa " + name + " a executar");
            System.out.println("[" + name + "]: " + msg);

            // escrever no log
            Logs.writeSateliteLog("Tarefa " + name + " a executar");

            Thread.sleep(expectedTime);

            // Tarefa concluída, avisa a CPU
            cpu.taskCompleted(this, Configs.getMensagemRespostaTarefa());

            // Notify waiting threads (if any)
            notifyExecutionComplete();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    /**
     * Obtém a mensagem associada à tarefa.
     *
     * @return Mensagem da tarefa.
     */
    public String getMessage() {
        return this.msg;
    }

    /**
     * Obtém o nome da tarefa.
     *
     * @return Nome da tarefa.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtém a prioridade da tarefa.
     *
     * @return Prioridade da tarefa (Alta ou Baixa).
     */
    public TaskPriority getPriority() {
        return this.priority;
    }

    /**
     * Obtém o tempo estimado para a execução da tarefa, em milissegundos.
     *
     * @return Tempo estimado para a execução da tarefa.
     */
    public int getExpectedTime() {
        return this.expectedTime;
    }

    public int getMemory() {
        return memory;
    }

    // para já estas dois métodos ainda não servem para nada
    public synchronized void waitForExecution() throws InterruptedException {
        wait();
    }

    public synchronized void notifyExecutionComplete() {
        notify();
    }
}
