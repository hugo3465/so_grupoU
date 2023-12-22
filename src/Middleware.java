import java.util.concurrent.Semaphore;

public class Middleware {
    // Comunicação entre tarefas utilizando protocolos de comunicação
    // Uso de recursos da MEM e CPU
    // Estrutura de dados para gerenciar mensagens e objetos
    // Definição de serviço de comunicação de satélite

    private final int MIDDLEWARE_SIZE = 5;
    
    // como o middleware só tem espaço 5, ent criou-se um semáforo para controlar quem entra
    private Semaphore semaphore;
    private Kernel kernel;

    public Middleware() {
        this.semaphore = new Semaphore(MIDDLEWARE_SIZE);
        this.kernel = new Kernel(this);
    }

    // Método para enviar dados do satélite para a estação
    public synchronized void send(Task task) {
        // Lógica para enviar dados utilizando protocolos de comunicação
        // ...
        // Armazenar dados na MEM para simular a comunicação com a estação


        // Lógica simulada de envio de dados utilizando protocolos de comunicação
        System.out.println("A enviar dados");
        
        // Armazenar dados na MEM para simular a comunicação com a estação

        try {
            semaphore.acquire();
            kernel.receiveTask(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Método para receber dados da estação para o satélite
     * 
     * @param taskThatAnswered identificador de qual task respondeu
     * @param response resposta que a task deu
     */
    public synchronized void receive(Task taskThatAnswered, String response) {
        // Lógica para receber dados utilizando protocolos de comunicação
        // ...

        // Recuperar dados da MEM para simular a comunicação com a estação



        // Lógica simulada de recebimento de dados utilizando protocolos de comunicação
        System.out.println("[" + taskThatAnswered.getName() + "] respondeu com: " + response);
        
        // Recuperar dados da MEM para simular a comunicação com a estação 
        
        semaphore.release();
    }

    public void turnOnOperatingSystem() {
        System.out.println("A iniciar o Sistema Operativo...");
        kernel.start();
        System.out.println("Ligado");
    }

    public void turnOffOperatingSystem() {
        System.out.println("A encerrar o Sistema Operativo...");
        kernel.shutdown();
        System.out.println("Encerrado");
    }
}
