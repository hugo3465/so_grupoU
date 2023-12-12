public class Middleware {
    // Comunicação entre tarefas utilizando protocolos de comunicação
    // Uso de recursos da MEM e CPU
    // Estrutura de dados para gerenciar mensagens e objetos
    // Definição de serviço de comunicação de satélite

    private CPU cpu;
    private MEM mem;

    public Middleware(CPU cpu, MEM mem) {
        this.cpu = cpu;
        this.mem = mem;
    }

    // Método para enviar dados do satélite para a estação
    public synchronized void sendData(String data) {
        // Lógica para enviar dados utilizando protocolos de comunicação
        // ...
        // Armazenar dados na MEM para simular a comunicação com a estação


        // Lógica simulada de envio de dados utilizando protocolos de comunicação
        System.out.println("A enviar dados: " + data);
        
        // Armazenar dados na MEM para simular a comunicação com a estação
    
        mem.storeData(data);
    }

    // Método para receber dados da estação para o satélite
    public synchronized String receiveData() {
        // Lógica para receber dados utilizando protocolos de comunicação
        // ...

        // Recuperar dados da MEM para simular a comunicação com a estação



        // Lógica simulada de recebimento de dados utilizando protocolos de comunicação
        String receivedData = "Dados recebidos da estação";
        System.out.println("A receber dados: " + receivedData);
        
        // Recuperar dados da MEM para simular a comunicação com a estação        
        return mem.retrieveData();
    }
}
