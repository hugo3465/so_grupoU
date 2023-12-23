import core.Middleware;
import core.Task;
import enums.TaskPriority;

/**
 * Exemplo de utilização
 */
public class Demo {
    public static void main(String[] args) throws Exception {

        // Só instancio o middleware e o kernel que ele instancia o resto
        Middleware middleware = new Middleware();

        // Inicia o sistema operativo
        middleware.turnOnOperatingSystem();

        // Exemplo de adição de tarefas à CPU
        Task task1 = new Task("task1", "Qualquer coisa", 40, TaskPriority.HIGH_PRIORITY);
        Task task2 = new Task("task2", "Qualquer coisa", 12, TaskPriority.LOW_PRIORITY);
        Task task3 = new Task("task3", "Qualquer coisa", 12, TaskPriority.LOW_PRIORITY);
        Task task4 = new Task("task4", "Qualquer coisa", 12, TaskPriority.HIGH_PRIORITY);
        
        // adicionar estas tasks
        middleware.send(task1);
        middleware.send(task2);
        middleware.send(task3);
        middleware.send(task4);

        Thread.sleep(1000);


        // Outras operações do sistema
        // ...

        // Termina o sistema operativo
        middleware.turnOffOperatingSystem();
    }
}

/**
 * Log é o registo de todas as atividades computacionais de um satélite, tem de
 * estar sempre escrito
 */
