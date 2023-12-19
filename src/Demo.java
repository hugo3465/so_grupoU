/**
 * Exemplo de utilização
 */
public class Demo {
    public static void main(String[] args) throws Exception {

        // Só instancio o middleware e o kernel que ele instancia o resto
        Kernel kernel = new Kernel();
        Middleware middleware = new Middleware(kernel);

        // Inicia o sistema operativo
        System.out.println("Iniciando o sistema operativo...");

        // Exemplo de adição de tarefas à CPU
        Task task1 = new Task("task1", 40);
        Task task2 = new Task("task2", 12);
        // adicionar estas tasks


        // Outras operações do sistema
        // ...

        // Termina o sistema operativo
        System.out.println("A encerrar o sistema operativo...");

        kernel.shutdown();
    }
}

/**
 * Log é o registo de todas as atividades computacionais de um satélite, tem de estar sempre escrito
 */
