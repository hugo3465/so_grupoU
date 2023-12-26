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

    // Variveis com os nomes das tags do ficheiro configuracao (Parte inicial antes
    // do valor associado)
    private final static int QUANTIDADE_TAGS = 7;
    private static final String tagTamanhoMem = "TamanhoMem:";
    private static final String tagTempoMinimoTarefa = "TempoMinimoTarefa:";
    private static final String tagTempoMaximoTarefa = "TempoMaximoTarefa:";
    private static final String tagMensagemRespostaTarefa = "MensagemRespostaTarefa:";
    private static final String tagTempoEsperaAteProximaTarefa = "TempoEsperaAteProximaTarefa:";
    private static final String tagPeriodoVerificacaoTarefasEmExecucao = "PeriodoVerificacaoTarefasEmExecucao:";
    private static final String tagTempoAtualizacaoDoGrafico = "TempoAtualizacaoDoGrafico:";

    private static long tamanhoMem;
    private static long tempoMinimoTarefa;
    private static long tempoMaximoTarefa;
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
        tempoMinimoTarefa = 1000; // 1 segundo
        tempoMaximoTarefa = 10000; // 10 segundos
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
        } else if (!data.get(1).contains(tagTempoMinimoTarefa)) {
            response = false;
        } else if (!data.get(2).contains(tagTempoMaximoTarefa)) {
            response = false;
        } else if (!data.get(3).contains(tagMensagemRespostaTarefa)) {
            response = false;
        } else if (!data.get(4).contains(tagTempoEsperaAteProximaTarefa)) {
            response = false;
        } else if (!data.get(5).contains(tagPeriodoVerificacaoTarefasEmExecucao)) {
            response = false;
        } else if (!data.get(6).contains(tagTempoAtualizacaoDoGrafico)) {
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
                tempoMinimoTarefa = Long.parseLong(data.get(1).split(tagTempoMinimoTarefa)[1].trim());
                tempoMaximoTarefa = Long.parseLong(data.get(2).split(tagTempoMaximoTarefa)[1].trim());
                mensagemRespostaTarefa = data.get(3).split(tagMensagemRespostaTarefa)[1];
                tempoEsperaAteProximaTarefa = Long
                        .parseLong(data.get(4).split(tagTempoEsperaAteProximaTarefa)[1].trim());
                periodoVerificacaoTarefasEmExecucao = Long
                        .parseLong(data.get(5).split(tagPeriodoVerificacaoTarefasEmExecucao)[1].trim());
                tempoAtualizacaoDoGrafico = Long.parseLong(data.get(6).split(tagTempoAtualizacaoDoGrafico)[1].trim());
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
     * Obtém o tempo mínimo de tarefa configurado.
     *
     * @return O tempo mínimo de tarefa.
     */
    public static long getTempoMinimoTarefa() {
        return tempoMinimoTarefa;
    }

    /**
     * Obtém o tempo máximo de tarefa configurado.
     *
     * @return O tempo máximo de tarefa.
     */
    public static long getTempoMaximoTarefa() {
        return tempoMaximoTarefa;
    }

    /**
     * Obtém a mensagem de resposta de tarefa configurada.
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
