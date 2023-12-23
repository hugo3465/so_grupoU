package util;

import java.util.concurrent.LinkedBlockingQueue;

import core.Task;

/**
 * A prioridade das tarefas vai vão ser estaticas
 */
public class TaskScheduler {
    private LinkedBlockingQueue<Task> highPriorityTasks;

    private int highPriorityCount;

    private LinkedBlockingQueue<Task> lowPriorityTasks;

    private int lowPriorityCount;

    public TaskScheduler() {
        this.highPriorityTasks = new LinkedBlockingQueue<>();
        this.lowPriorityTasks = new LinkedBlockingQueue<>();
        this.highPriorityCount = 0;
        this.lowPriorityCount = 0;
    }

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

    public void clear() {
        highPriorityTasks.clear();
        lowPriorityTasks.clear();
        highPriorityCount = 0;
        lowPriorityCount = 0;
    }

    public boolean isEmpty() {
        return (highPriorityTasks.isEmpty() && lowPriorityTasks.isEmpty());
    }

    public int size() {
        return (lowPriorityCount + highPriorityCount);
    }

    public int lowPrioritySize() {
        return lowPriorityCount;
    }

    public int highPriorityCount() {
        return highPriorityCount;
    }
}
