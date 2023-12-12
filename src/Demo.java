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

        Thread cpuThread = new Thread(cpu);

         // Exemplo de adição de tarefas à CPU
         cpu.addTask(() -> System.out.println("Tarefa 1 sendo executada"));
         cpu.addTask(() -> System.out.println("Tarefa 2 sendo executada"));
 
         // Iniciar a execução da CPU em uma thread separada
         cpuThread.start();
 
         // Outras operações do sistema
         // ...

        kernel.shutdown();
    }
}
