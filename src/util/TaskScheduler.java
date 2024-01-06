package util;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import core.Task;

/**
 * O agendador de tarefas com prioridades
 * A prioridade das tarefas é estática
 */
public class TaskScheduler {
    private Queue<Task> highPriorityTasks;
    private int highPriorityCount;

    private Queue<Task> lowPriorityTasks;
    private int lowPriorityCount;

    /**
     * Construtor da classe TaskScheduler
     * Inicializa as filas de tarefas com prioridades alta e baixa
     * Inicializa contadores de tarefas de alta e baixa prioridade
     */
    public TaskScheduler() {
        this.highPriorityTasks = new LinkedBlockingQueue<>();
        this.lowPriorityTasks = new LinkedBlockingQueue<>();
        this.highPriorityCount = 0;
        this.lowPriorityCount = 0;
    }

    /**
     * Adiciona uma tarefa à fila correspondente com base em sua prioridade
     * Atualiza os contadores de tarefas de alta e baixa prioridade
     *
     * @param task A tarefa a ser adicionada
     */
    public void add(Task task) {
        switch (task.getPriority()) {
            case LOW_PRIORITY:
                lowPriorityTasks.add(task);
                lowPriorityCount++;
                break;
            case HIGH_PRIORITY:
                highPriorityTasks.add(task);
                highPriorityCount++;
                break;
            default:
                break;
        }
    }

    /**
     * Retorna e remove a próxima tarefa da fila, respeitando a prioridade
     * Atualiza os contadores de tarefas de alta e baixa prioridade
     *
     * @return A próxima tarefa, ou null se a fila estiver vazia
     */
    public Task poll() {
        if (isEmpty()) {
            return null;
        }

        Task removedTask = null;
        if (!highPriorityTasks.isEmpty()) {
            removedTask = highPriorityTasks.poll();
            highPriorityCount--;
        } else if (!lowPriorityTasks.isEmpty()) {
            removedTask = lowPriorityTasks.poll();
            lowPriorityCount--;
        }

        return removedTask;
    }

    /**
     * Limpa ambas as filas e redefine os contadores para zero
     */
    public void clear() {
        highPriorityTasks.clear();
        lowPriorityTasks.clear();
        highPriorityCount = 0;
        lowPriorityCount = 0;
    }

    /**
     * Verifica se ambas as filas estão vazias
     *
     * @return true se ambas as filas estiverem vazias, false caso contrário
     */
    public boolean isEmpty() {
        return (highPriorityTasks.isEmpty() && lowPriorityTasks.isEmpty());
    }

    /**
     * Retorna o número total de tarefas nas filas
     *
     * @return O número total de tarefas
     */
    public int size() {
        return (lowPriorityCount + highPriorityCount);
    }

    /**
     * Retorna o número de tarefas de baixa prioridade na fila
     *
     * @return O número de tarefas de baixa prioridade
     */
    public int lowPrioritySize() {
        return lowPriorityCount;
    }

    /**
     * Retorna o número de tarefas de alta prioridade na fila
     *
     * @return O número de tarefas de alta prioridade
     */
    public int highPriorityCount() {
        return highPriorityCount;
    }
}
