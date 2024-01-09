package application.charts;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.Dimension;

/**
 * Representa um gráfico circular para visualizar a distribuição de tarefas em
 * diferentes estados.
 */
public class TasksCircularChart extends JFrame {
    /** O gráfico JFreeChart a ser exibido. */
    private JFreeChart chart;
    /** O conjunto de dados para o gráfico circular. */
    private DefaultPieDataset<String> dataset;

    /**
     * Construtor da classe TasksCircularChart.
     *
     * @param title Título do gráfico.
     */
    public TasksCircularChart(String title) {
        super(title);

        // Cria um conjunto de dados inicial
        dataset = createDataset();

        // Cria um gráfico com base no conjunto de dados
        chart = ChartFactory.createPieChart3D( // Remova o 3D para desativar a representação tridimensional
                title,
                dataset,
                true,
                true,
                false);

        // Cria um painel para exibir o gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));

        // Configura o JFrame
        setContentPane(chartPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Cria um conjunto de dados inicial para o gráfico.
     *
     * @return O conjunto de dados inicial.
     */
    private DefaultPieDataset<String> createDataset() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.setValue("Waiting", 0);
        dataset.setValue("Executing", 0);
        dataset.setValue("Finished", 0);
        return dataset;
    }

    /**
     * Atualiza o conjunto de dados com novos valores para tarefas em diferentes
     * estados.
     *
     * @param waitingTasks   Quantidade de tarefas em espera.
     * @param executingTasks Quantidade de tarefas em execução.
     * @param finishedTasks  Quantidade de tarefas concluídas.
     */
    public void updateDataset(int waitingTasks, int executingTasks, int finishedTasks) {

        // Atualiza os valores no conjunto de dados (substitua esta lógica conforme
        // necessário)
        dataset.setValue("Waiting", waitingTasks);
        dataset.setValue("Executing", executingTasks);
        dataset.setValue("Finished", finishedTasks);

        // Notifica o gráfico de que o conjunto de dados foi alterado
        // JFreeChart chart = ((ChartPanel)
        // getContentPane().getComponent(0)).getChart();
        // chart.fireChartChanged();

        // Repinta o painel do gráfico
        // ((ChartPanel) getContentPane().getComponent(0)).repaint();
        repaint();

    }

    /**
     * Fecha a janela do gráfico.
     */
    public void close() {
        this.dispose();
    }
}
