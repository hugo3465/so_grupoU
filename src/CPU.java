import java.util.concurrent.LinkedBlockingQueue;

/**
 * unidade de processamento
 */
public class CPU implements Runnable {
    // Gestão, escalonamento e execução de tarefas

    // uma linkedBolckingQueue funciona como uma queue, mas a cabeça da fila é o
    // elemento que está na fila há mais tempo.
    private LinkedBlockingQueue<Runnable> taskQueue;

    public CPU() {
        this.taskQueue = new LinkedBlockingQueue<>();
    }

    // Método para adicionar uma tarefa à fila
    public synchronized void addTask(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Método para iniciar a execução da CPU
    /*public void startExecution() {
        new Thread(() -> {
            while (true) {
                try {
                    // Obter e executar a próxima tarefa da fila
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }*/

    @Override
    public void run() {
        while (true) {
            try {
                // Obter e executar a próxima tarefa da fila
                Runnable task = taskQueue.take();
                task.run();

                // espera 100 milisegundos até executar a próxima tarefa da fila
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
