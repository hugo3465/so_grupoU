package core;

import java.util.ArrayList;
import java.util.List;

import exceptions.OutOfMemmoryException;
import util.Configs;
import util.Logs;
import util.TaskScheduler;

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

    private Thread cpuThread; // acho que posso até tirar isto daqui

    // uma linkedBolckingQueue funciona como uma queue, mas a cabeça da fila é o
    // elemento que está na fila há mais tempo.
    protected TaskScheduler waitingTasks;

    protected List<Task> tasksOnExecution;

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

    public Kernel(Middleware middleware) {
        this.middleware = middleware;

        this.waitingTasks = new TaskScheduler(); // pode ter capacidade de 5
        this.isOn = false;
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

        // TODO: testes, vários núcleos. Para apagar, APENAS TESTES PARA AUMENTAR VELOCIDADE
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

    // Método para receber uma tarefa do Middleware e colocar na lista de espera
    // para ser processada pela CPU
    // acho que podia nem estar syncronized este
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

    protected void sendTask(Task task, String response) {
        middleware.receive(task, response);

        mem.deallocateMemmory(task.getMemory());
    }

    // encerramento dos sub-componentes e outras coisas
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

    public int getMemoryOnUsage() {
        return mem.getUsedMemory();
    }

    public synchronized void reserveMemory(int memoryToReserve) throws OutOfMemmoryException {
        mem.alocateMemmory(memoryToReserve);
    }

    public boolean isOnShutoDownProcess() {
        return this.isOnShutDownProcess;
    }

    public boolean isOn() {
        return isOn;
    }
}
