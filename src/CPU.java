import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * unidade de processamento
 * O que deve fazer:
 * - Gerir tarefas
 * - escalonar tarefas
 * - executar tarefas
 * (se calhar coloco aqui um round robin, ou um scheduler)
 */
public class CPU implements Runnable {

    private final Kernel kernel;

    // uma linkedBolckingQueue funciona como uma queue, mas a cabeça da fila é o
    // elemento que está na fila há mais tempo.
    private LinkedBlockingQueue<Task> taskQueue;

    private ArrayList<Thread> threadsOnExecution;

    /**
     * Array que contém todas as tarefas que acabaram de ser executadas pelo sistema
     * operativo.
     */
    private ArrayList<Thread> threadsTerminated;

    // acho que vou remover isto
    private volatile boolean isOnShutDownProcess; // Flag to indicate CPU shutdown

    public CPU(Kernel kernel) {
        this.kernel = kernel;
        this.taskQueue = new LinkedBlockingQueue<>(); // pode ter capacidade de 5
        this.isOnShutDownProcess = false;
        this.threadsOnExecution = new ArrayList<>();
        this.threadsTerminated = new ArrayList<>();

        // vai dizer para todas as tasks quem e onde está o CPU
        Task.setCPU(this);
    }

    // Método para adicionar uma tarefa à fila
    protected synchronized void addTask(Task task) {
        // só pode adicionar tarefas, se o CPU não estiver em processo de
        if (!isOnShutDownProcess) {
            try {
                taskQueue.put(task);
                System.out.println("Tarefa agendada");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    @Override
    public void run() {
        try {
            while (!isOnShutDownProcess) {
                // Obter e executar a próxima tarefa da fila
                Task currentTask = taskQueue.take();

                // se houver uma tarefa para na lista, vai inicia-la e adicioná-la à lista de
                // tarefas em execução
                if (currentTask != null) {
                    Thread task = new Thread(currentTask);
                    task.setName(currentTask.getName());
                    threadsOnExecution.add(task);
                    task.start();
                }

                // espera 100 milisegundos até executar a próxima tarefa da fila
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace(); // TODO apagar estes printStackTraces
        }

    }

    public void shutdown() {
        isOnShutDownProcess = true;
        taskQueue.clear(); // Optionally, clear the task queue if you want to discard remaining tasks

        // Espera até que todas as tarefas a serem executadas acabem, para encerrar o
        // CPU

        // espera até que todas as tarefas em execução acabem
        synchronized (threadsOnExecution) {
            for (Thread thread : threadsOnExecution) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace(); // Handle the InterruptedException as needed
                }
            }
        }

        // Thread.currentThread().interrupt(); // se tem os joins acho que nem é preciso
        // esta linha

    }

    protected synchronized void taskCompleted(Task task, String response) {
        // Itera sobre a lista das threads em execução, e remove a thread que acaboou de
        // ser finalizada. Ela é encontrada pelo nome
        // Use an iterator to safely remove elements during iteration

        // manda a tarefa para o kernel, que por sua vez manda para o middleware
        kernel.sendTask(task, response);

        // tem um deadLock aqui
        synchronized (threadsOnExecution) {
            Iterator<Thread> iterator = threadsOnExecution.iterator();
            while (iterator.hasNext()) {
                Thread thread = iterator.next();
                if (thread.getName().equals(task.getName())) {
                    threadsTerminated.add(thread);
                    iterator.remove(); // Safely remove the current thread
                    break;
                }
            }

            // for (Thread thread : threadsOnExecution) {
            // if (thread.getName().equals(task.getName())) {
            // threadsOnExecution.remove(thread);
            // break;
            // }
            // }
        }

    }

    public boolean isOnShutoDownProcess() {
        return this.isOnShutDownProcess;
    }
}
