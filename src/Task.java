public class Task implements Runnable {

    /**
     * 
     */
    private static CPU cpu;

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

    public Task(String name, String msg, int memmory) {
        this.name = name;
        this.msg = msg;
        this.memmory = memmory;
    }

    // Static method to set the CPU instance
    protected static void setCPU(CPU cpuInstance) {
        cpu = cpuInstance;
    }

    @Override
    public void run() {
        try {
            System.out.println("Task " + name + " a executar");
            System.out.println("[" + name + "]: " + msg);

            Thread.sleep(1000); // por defeito as task vão durar 1 segundo, no futuro gostaria que cada uma
                                // demorasse tempos diferentes

            // Task has completed, inform the CPU
            cpu.taskCompleted(this, "dummy conclusion message");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // TODO Auto-generated catch block
            e.printStackTrace();
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