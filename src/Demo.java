import core.Task;
import enums.TaskPriority;

public class Demo {
    public static void main(String[] args) {
        // Middleware middleware = new Middleware();
        // middleware.turnOnOperatingSystem();

        Task task1 = new Task("Teste", "Teste1", 5, TaskPriority.HIGH_PRIORITY, 1000);

        System.out.println(task1.getResponse());

        Thread thread = new Thread(task1);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       System.out.println(task1.getResponse()); 
        // middleware.send(task1);

        // try {
        //     Thread.sleep(5000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }


        // Task responsedTask = middleware.receive();
        // System.out.println(responsedTask.getResponse());
        
    }
}
