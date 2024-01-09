package util;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import core.Task;

/**
 * O agendador de tarefas com prioridades
 * A prioridade das tarefas é estática
 */
public class TaskScheduler {
    private final int STARVATION_RANGE = 5;

    private Queue<Task> highPriorityTasks;
    private int highPriorityCount;

    private Queue<Task> lowPriorityTasks;
    private int lowPriorityCount;

    /**
     * Serve para evitar problemas de starvation, por cada x
     * {@code highPriorityTask} que executam, uma {@code lowPriorityTasks} vai
     * executar.
     * 
     * O número de {@code highPriorityTasks} que podem executar antes de uma
     * LowPriority é defenida na constante {@code STARVATION_RANGE}
     */
    private int startvationCount;

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
        this.startvationCount = 0;
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

        // remove lowPriority, caso tenha atingido o STARVATIONCOUNT
        if (startvationCount >= STARVATION_RANGE && !lowPriorityTasks.isEmpty()) {
            removedTask = removeLowPriorityTask();
        } else if (!highPriorityTasks.isEmpty()) {
            removedTask = removeHighPrioriTask();
        } else if (!lowPriorityTasks.isEmpty()) {
            removedTask = removeLowPriorityTask();
        }

        return removedTask;
    }

    /**
     * Remove e retorna a próxima tarefa de baixa prioridade da fila. Atualiza o
     * contador de tarefas de baixa prioridade e zera o contador de starvation.
     *
     * @return A próxima tarefa de baixa prioridade, ou null se a fila estiver
     *         vazia.
     */
    private Task removeLowPriorityTask() {
        Task removedTask = null;

        removedTask = lowPriorityTasks.poll();
        lowPriorityCount--;

        startvationCount = 0;

        return removedTask;
    }

    /**
     * Remove e retorna a próxima tarefa de alta prioridade da fila. Atualiza o
     * contador de tarefas de alta prioridade e incrementa o contador de starvation.
     *
     * @return A próxima tarefa de alta prioridade, ou null se a fila estiver vazia.
     */
    private Task removeHighPrioriTask() {
        Task removedTask = null;

        removedTask = highPriorityTasks.poll();
        highPriorityCount--;

        startvationCount++;

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
        startvationCount = 0;
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
