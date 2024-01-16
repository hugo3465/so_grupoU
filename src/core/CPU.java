package core;

import exceptions.OutOfMemmoryException;
import util.Configs;

/**
 * Representa a unidade de processamento (CPU) do sistema.
 * Realiza a gestão, escalonamento e execução de tarefas.
 * Utiliza um mecanismo de round robin ou um scheduler para realizar o
 * escalonamento.
 */
public class CPU implements Runnable {

    /**
     * O kernel associado à CPU.
     */
    private final Kernel kernel;

    /**
     * Construtor da classe `CPU`.
     *
     * @param kernel O kernel associado à CPU.
     */
    public CPU(Kernel kernel) {
        this.kernel = kernel;

        // vai dizer para todas as tasks quem e onde está o CPU
        Task.setCPU(this);
    }

    /**
     * Implementação do método `run` da interface `Runnable`.
     * Realiza a execução contínua das tarefas enquanto o sistema estiver ativo.
     */
    @Override
    public void run() {
        try {
            while (kernel.isOn()) {
                // quando o sistema operativo estiver em processo de encerramento, não pode
                // colocar mais tasks em execução
                if (!kernel.isOnShutoDownProcess()) {
                    Task currentTask;

                    // Obter e executar a próxima tarefa da fila, se não tiver, devolve null
                    synchronized (kernel.waitingTasks) {
                        currentTask = kernel.waitingTasks.poll();
                    }

                    // se houver uma tarefa para na lista, vai inicia-la e adicioná-la à lista de
                    // tarefas em execução
                    if (currentTask != null) {
                        Thread task = new Thread(currentTask);
                        task.setName(currentTask.getName());

                        try {
                            kernel.reserveMemory(currentTask.getMemory());
                            task.start();

                            synchronized (kernel.tasksOnExecution) {
                                kernel.tasksOnExecution.add(currentTask);
                            }
                        } catch (OutOfMemmoryException e) {
                            // se não tem memória suficiente para executar, volta para a fila de espera, e
                            // passa para a próxima tarefa
                            synchronized (kernel.waitingTasks) {
                                kernel.waitingTasks.add(currentTask);
                            }
                        } finally {
                            // espera o tempo pré-definido no ficheiro até executar a próxima tarefa da fila
                            Thread.sleep(Configs.getTempoEsperaAteProximaTarefa());
                        }

                    }

                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

    }

    /**
     * Método protegido que é chamado quando uma tarefa é concluída.
     * Remove a tarefa da lista de tarefas em execução e a adiciona à lista de
     * tarefas terminadas.
     * Em seguida, envia a tarefa para o middleware através do kernel.
     *
     * @param task A tarefa que foi concluída.
     */
    protected synchronized void taskCompleted(Task task) {

        synchronized (kernel.tasksOnExecution) {
            kernel.tasksOnExecution.remove(task);
        }

        synchronized (kernel.tasksTerminated) {
            kernel.tasksTerminated.add(task);
        }

        // manda a tarefa para o kernel, que por sua vez manda para o middleware
        kernel.sendToMiddleware(task);

    }
}
