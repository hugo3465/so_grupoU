package core;

import exceptions.OutOfMemmoryException;

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

    public CPU(Kernel kernel) {
        this.kernel = kernel;

        // vai dizer para todas as tasks quem e onde está o CPU
        Task.setCPU(this);
    }

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
                            // passa para a próxima task
                            synchronized (kernel.waitingTasks) {
                                kernel.waitingTasks.add(currentTask);
                            }

                            // passa para a próxima tarefa
                            continue;
                        } finally {
                            // espera 500 milisegundos até executar a próxima tarefa da fila
                            Thread.sleep(500);
                        }

                    }

                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace(); // TODO apagar estes printStackTraces
        }

    }

    protected synchronized void taskCompleted(Task task, String response) {

        synchronized (kernel.tasksOnExecution) {
            kernel.tasksOnExecution.remove(task);
        }

        synchronized (kernel.tasksTerminated) {
            kernel.tasksTerminated.add(task);
        }

        // manda a tarefa para o kernel, que por sua vez manda para o middleware
        kernel.sendTask(task, response);

    }
}
