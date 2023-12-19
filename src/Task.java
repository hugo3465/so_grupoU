public class Task implements Runnable {

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String msg;

    /**
     * The ammount of memmory that the task will use
     */
    private int memmory;

    public Task(String msg, int memmory) {
        this.msg = msg;
        this.memmory = memmory;
    }

    @Override
    public void run() {
        try {
            System.out.println("Task " + name + " a executar");
            System.out.println("[" + name + "]: " + msg);

            Thread.sleep(1000); // por defeito as task vão durar 1 segundo, no futuro gostaria que cada uma
                                // demorasse tempos diferentes


        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // faz receive
        }
    }

    /**
     * @return message of the task
     */
    public String getMessage() {
        return this.msg;
    }

    /**
     * @return name of the task
     */
    public String getName() {
        return this.name;
    }
}