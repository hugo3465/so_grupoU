/**
 * Exemplo de utilização
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        Kernel kernel = new Kernel();
        kernel.start();

        MEM mem = new MEM();
        CPU cpu = new CPU();
        Middleware middleware = new Middleware(cpu, mem);

        // Inicia o sistema operativo
        System.out.println("Iniciando o sistema operativo...");

        Thread cpuThread = new Thread(cpu);

        // Exemplo de adição de tarefas à CPU
        Task task1 = new Task("task1");
        Task task2 = new Task("task2");
        cpu.addTask(task1);
        cpu.addTask(task2);
        // cpu.addTask(() -> System.out.println("Tarefa 1 sendo executada"));
        // cpu.addTask(() -> System.out.println("Tarefa 2 sendo executada"));

        // Iniciar a execução da CPU em uma thread separada
        cpuThread.start();

        // Outras operações do sistema
        // ...

        // Termina o sistema operativo
        System.out.println("Encerrando o sistema operativo...");

        kernel.shutdown();
    }
}
