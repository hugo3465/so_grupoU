import java.util.concurrent.LinkedBlockingQueue;

/**
 * unidade de processamento
 * O que deve fazer:
 *  - Gerir tarefas
 *  - escalonar tarefas
 *  - executar tarefas
 */
public class CPU implements Runnable {

    // uma linkedBolckingQueue funciona como uma queue, mas a cabeça da fila é o
    // elemento que está na fila há mais tempo.
    private LinkedBlockingQueue<Task> taskQueue;

    public CPU() {
        this.taskQueue = new LinkedBlockingQueue<>();
    }

    // Método para adicionar uma tarefa à fila
    public synchronized void addTask(Task task) {
        try {
            taskQueue.put(task);
            System.out.println("Tarefa agendada: " + task.getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Obter e executar a próxima tarefa da fila
                Thread task = new Thread(taskQueue.take());
                task.start();

                // espera 100 milisegundos até executar a próxima tarefa da fila
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
