import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

import org.jfree.data.time.TimePeriod;

import application.TaskBarChart;
import application.TasksCircularChart;
import core.Middleware;
import core.Task;
import enums.TaskPriority;
import util.Configs;

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

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // // Create an instance of Configs
        // Configs configsInstance = new Configs();

        // Thread chartThread = new Thread(new Demo());

        // // Inicia o sistema operativo
        // middleware.turnOnOperatingSystem();
        // chartThread.start();

        // // Exemplo de adição de tarefas à CPU
        // Task task1 = new Task("task1", "Qualquer coisa", 40, TaskPriority.HIGH_PRIORITY,
        //         generateRandomNumber(Configs.getTempoMinimoTarefa(), Configs.getTempoMaximoTarefa()));
        // Task task2 = new Task("task2", "Qualquer coisa", 12, TaskPriority.LOW_PRIORITY,
        //         generateRandomNumber(Configs.getTempoMinimoTarefa(), Configs.getTempoMaximoTarefa()));
        // Task task3 = new Task("task3", "Qualquer coisa", 12, TaskPriority.LOW_PRIORITY,
        //         generateRandomNumber(Configs.getTempoMinimoTarefa(), Configs.getTempoMaximoTarefa()));
        // Task task4 = new Task("task4", "Qualquer coisa", 12, TaskPriority.HIGH_PRIORITY,
        //         generateRandomNumber(Configs.getTempoMinimoTarefa(), Configs.getTempoMaximoTarefa()));

        // // adicionar estas tasks
        // middleware.send(task1);
        // middleware.send(task2);
        // middleware.send(task3);
        // middleware.send(task4);

        // Thread.sleep(1000);

        // // TODO TIRAR
        // int i = 0;
        // Task taskEx = null;
        // while (i < 100) {
        //     switch (generateRandomNumber(0, 1)) {
        //         case 0:
        //             taskEx = new Task("Auto generated" + i, "Auto Generated", 20, TaskPriority.HIGH_PRIORITY,
        //                     generateRandomNumber(1000, 10000));
        //             break;
        //         case 1:
        //             taskEx = new Task("Auto generated" + i, "Auto Generated", 20, TaskPriority.LOW_PRIORITY,
        //                     generateRandomNumber(1000, 10000));
        //             break;
        //         default:
        //             break;
        //     }
        //     i++;
        //     middleware.send(taskEx);
        //     System.out.println("\n" + i);

        //     Thread.sleep(100);
        // }

        // // Outras operações do sistema
        // // ...

        // // Termina o sistema operativo
        // System.out.println("\nA fazer shutdown");
        // middleware.turnOffOperatingSystem();
    }

    @Override
    public void run() {
        TasksCircularChart circularChart = new TasksCircularChart("Tasks");
        circularChart.setVisible(true);

        TaskBarChart barChart = new TaskBarChart("Tasks");
        barChart.setVisible(true);

        while (true) {
            // faz update do gráfico de 200 em 200 milisegundos
            try {
                Thread.sleep(Configs.getTempoAtualizacaoDoGrafico());
                circularChart.updateDataset(middleware.getNumberOfWaitingTasks(),
                        middleware.getNumberOfExecutingTasks(),
                        middleware.getNumberOfFinishedTasks());

                barChart.updateDataset(middleware.getNumberOfWaitingTasks(), middleware.getNumberOfExecutingTasks());

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    // TODO apenas para testes
    public static int generateRandomNumber(long min, long max) {

        int randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);

        return randomInt;
    }
}

/**
 * Log é o registo de todas as atividades computacionais de um satélite, tem de
 * estar sempre escrito
 */
