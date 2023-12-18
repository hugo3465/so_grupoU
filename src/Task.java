public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        String taskName = Thread.currentThread().getName();
        System.out.println("Task " + taskName + " a executar");

        try {
            Thread.sleep(1000); // por defeito as task vão durar 1 segundo, no futuro gostaria que cada uma
                                // demorasse tempos diferentes
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @return name of the task
     */
    public String getName() {
        return this.name;
    }
}