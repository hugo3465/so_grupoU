package util;

import java.util.List;

/**
 * A classe Configs representa a configuração do sistema.
 * Contém métodos para importar configurações a partir de um arquivo e fornecer
 * acesso às diferentes configurações.
 */
public class Configs {
    /**
     * Nome do ficheiro que contém as configurações.
     */
    private static final String fileName = "./Files/configs.txt";

    /**
     * Numeros de parametros que o ficheiro de configuração contém
     */
    private final static int QUANTIDADE_TAGS = 9;

    // Variveis com os nomes das tags do ficheiro configuracao (Parte inicial antes
    // do valor associado)
    private static final String tagTamanhoMem = "TamanhoMem:";
    private static final String tagStressTestNumeroTarefas = "StressTestNumeroDeTarefas:";
    private static final String tagStressTestTempoMinimoTarefa = "StressTestTempoMinimoTarefa:";
    private static final String tagStressTestTTempoMaximoTarefa = "StressTestTempoMaximoTarefa:";
    private static final String tagStressTempoEntreTarefas = "StressTempoEntreTarefas:";
    private static final String tagMensagemRespostaTarefa = "MensagemRespostaTarefa:";
    private static final String tagTempoEsperaAteProximaTarefa = "TempoEsperaAteProximaTarefa:";
    private static final String tagPeriodoVerificacaoTarefasEmExecucao = "PeriodoVerificacaoTarefasEmExecucao:";
    private static final String tagTempoAtualizacaoDoGrafico = "TempoAtualizacaoDoGrafico:";

    private static long tamanhoMem;
    private static int numeroTarefasEmStress;
    private static long tempoMinimoTarefaEmStress;
    private static long tempoMaximoTarefaEmStress;
    private static long tempoEntreTarefasEmStress;
    private static String mensagemRespostaTarefa;
    private static long tempoEsperaAteProximaTarefa;
    private static long periodoVerificacaoTarefasEmExecucao;
    private static long tempoAtualizacaoDoGrafico;

    /**
     * Construtor da classe Configs. Importa automaticamente as configurações.
     */
    public Configs() {
        importConfig();
    }

    /**
     * Associa valores por defeito às variaveis quando o ficheiro é
     * inválido
     */
    private static void defaultValues() {
        tamanhoMem = 500;
        numeroTarefasEmStress = 100;
        tempoMinimoTarefaEmStress = 1000; // 1 segundo
        tempoMaximoTarefaEmStress = 10000; // 10 segundos
        tempoEntreTarefasEmStress = 200; // 200 milisegundos
        mensagemRespostaTarefa = "Mensagem de conclusão fictícia";
        tempoEsperaAteProximaTarefa = 500; // meio segundo
        periodoVerificacaoTarefasEmExecucao = 100; // 100 milisegundos
        tempoAtualizacaoDoGrafico = 200; // 200 milisegundos
    }

    /**
     * Verifica se a estrutura do ficheiro é valida
     *
     * @param data Lista que contem todas as linhas do ficheiro de configuração
     * @return True se o ficheiro é valido, False caso contrário
     */
    private static boolean isValidStructConfigFile(List<String> data) {
        boolean response = true;

        if (data != null && data.size() < QUANTIDADE_TAGS) {
            response = false;
        } else if (!data.get(0).contains(tagTamanhoMem)) {
            response = false;
        } else if (!data.get(1).contains(tagStressTestNumeroTarefas)) {
            response = false;
        } else if (!data.get(2).contains(tagStressTestTempoMinimoTarefa)) {
            response = false;
        } else if (!data.get(3).contains(tagStressTestTTempoMaximoTarefa)) {
            response = false;
        } else if (!data.get(4).contains(tagStressTempoEntreTarefas)) {
            response = false;
        } else if (!data.get(5).contains(tagMensagemRespostaTarefa)) {
            response = false;
        } else if (!data.get(6).contains(tagTempoEsperaAteProximaTarefa)) {
            response = false;
        } else if (!data.get(7).contains(tagPeriodoVerificacaoTarefasEmExecucao)) {
            response = false;
        } else if (!data.get(8).contains(tagTempoAtualizacaoDoGrafico)) {
            response = false;
        }

        return response;
    }

    /**
     * Converter os valores do ficheiro de configurações para os tipos
     * compativeis, bem como a associação dos mesmos às respetivas variaveis
     *
     * @param data Lista que contem todas as linhas do ficheiro de configuração
     * @return True caso todos os valores sejam válidos, False caso contrário
     */
    public static boolean convertValues(List<String> data) {
        if (isValidStructConfigFile(data)) {
            try {
                tamanhoMem = Long.parseLong(data.get(0).split(tagTamanhoMem)[1].trim());
                numeroTarefasEmStress =  Integer.parseInt(data.get(1).split(tagStressTestNumeroTarefas)[1].trim());
                tempoMinimoTarefaEmStress = Long.parseLong(data.get(2).split(tagStressTestTempoMinimoTarefa)[1].trim());
                tempoMaximoTarefaEmStress = Long
                        .parseLong(data.get(3).split(tagStressTestTTempoMaximoTarefa)[1].trim());
                tempoEntreTarefasEmStress = Long.parseLong(data.get(4).split(tagStressTempoEntreTarefas)[1].trim());
                mensagemRespostaTarefa = data.get(5).split(tagMensagemRespostaTarefa)[1];
                tempoEsperaAteProximaTarefa = Long
                        .parseLong(data.get(6).split(tagTempoEsperaAteProximaTarefa)[1].trim());
                periodoVerificacaoTarefasEmExecucao = Long
                        .parseLong(data.get(7).split(tagPeriodoVerificacaoTarefasEmExecucao)[1].trim());
                tempoAtualizacaoDoGrafico = Long.parseLong(data.get(8).split(tagTempoAtualizacaoDoGrafico)[1].trim());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Importar os valores do ficheiro de configuração
     *
     * @return True caso os valores sejam importados com sucesso, False caso
     *         contrário
     */
    public static boolean importConfig() {
        List<String> data = Files.readFile(fileName);

        // se falhou na leitura do fichiero, vai preencher com valores prédefinidos
        if (!convertValues(data)) {
            // preenche com valores pré-definidos
            defaultValues();

            System.out.println("Ficheiro invalido! Preenchimento com valores Pré-definidos");

            return false;
        }
        return true;
    }

    /**
     * Obtém o tamanho da memória configurado.
     *
     * @return O tamanho da memória.
     */
    public static long getTamanhoMem() {
        return tamanhoMem;
    }

    /**
     * Obtém o numero de tarefas que irão ser executadas durante o teste de stress
     * 
     * @return número de tarefas a exeutar durante o teste de stress
     */
    public static int getNumeroTarefasNoTesteStress() {
        return numeroTarefasEmStress;
    }

    /**
     * Obtém o tempo mínimo da tarefa durante o stress test.
     *
     * @return O tempo mínimo de tarefa.
     */
    public static long getTempoMinimoTarefa() {
        return tempoMinimoTarefaEmStress;
    }

    /**
     * Obtém o tempo máximo da tarefa durante o stress test.
     *
     * @return O tempo máximo de tarefa.
     */
    public static long getTempoMaximoTarefa() {
        return tempoMaximoTarefaEmStress;
    }

    /**
     * Obtem o tempo entre o lançamento de tarefas durante o stress test
     * 
     * @return O tempo entre o lançamento de tarefas
     */
    public static long getTempoEntreTarefas() {
        return tempoEntreTarefasEmStress;
    }

    /**
     * Obtém a mensagem de resposta da tarefa configurada.
     *
     * @return A mensagem de resposta de tarefa.
     */
    public static String getMensagemRespostaTarefa() {
        return mensagemRespostaTarefa;
    }

    /**
     * Obtém o tempo de espera até a próxima tarefa configurado.
     *
     * @return O tempo de espera até a próxima tarefa.
     */
    public static long getTempoEsperaAteProximaTarefa() {
        return tempoEsperaAteProximaTarefa;
    }

    /**
     * Obtém o período de verificação de tarefas em execução configurado.
     *
     * @return O período de verificação de tarefas em execução.
     */
    public static Long getPeriodoVerificacaoTarefasEmExecucao() {
        return periodoVerificacaoTarefasEmExecucao;
    }

    /**
     * Obtém o tempo de atualização do gráfico configurado.
     *
     * @return O tempo de atualização do gráfico.
     */
    public static long getTempoAtualizacaoDoGrafico() {
        return tempoAtualizacaoDoGrafico;
    }
}
