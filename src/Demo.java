import core.Middleware;
import core.Task;
import enums.TaskPriority;

/**
 * Exemplo de utilização
 */
public class Demo implements Runnable {

    private static Middleware middleware;

    public Demo() {
        // Só instancio o middleware e o kernel que ele instancia o resto
        middleware = new Middleware();
    }

    public static void main(String[] args) throws Exception {

        Thread chartThread = new Thread(new Demo());

        // Inicia o sistema operativo
        middleware.turnOnOperatingSystem();
        chartThread.start();

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

        // TODO TIRAR
        int i = 0;
        Task taskEx = null;
        while (i < 100) {
            switch (generateRandomNumber()) {
                case 0:
                    taskEx = new Task("Auto generated" + i, "Auto Generated", 0, TaskPriority.HIGH_PRIORITY);
                    break;
                case 1:
                    taskEx = new Task("Auto generated" + i, "Auto Generated", 0, TaskPriority.LOW_PRIORITY);
                    break;
                default:
                    break;
            }
            i++;
            middleware.send(taskEx);
            System.out.println("\n" + i);

            Thread.sleep(100);
        }

        // Outras operações do sistema
        // ...

        // Termina o sistema operativo
        System.out.println("\nA fazer shutdown");
        //middleware.turnOffOperatingSystem();
    }

    @Override
    public void run() {
        CircularChart example = new CircularChart("Tasks");
        example.setVisible(true);

        while (true) {
            // faz update do gráfico de 200 em 200 milisegundos
            try {
                Thread.sleep(200);
                example.updateDataset(middleware.getNumberOfWaitingTasks(), middleware.getNumberOfExecutingTasks(),
                        middleware.getNumberOfFinishedTasks());

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    // TODO apenas para testes
    public static int generateRandomNumber() {
        int min = 0; // Min value
        int max = 1; // Max value

        int randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);

        return randomInt;
    }
}

/**
 * Log é o registo de todas as atividades computacionais de um satélite, tem de
 * estar sempre escrito
 */
