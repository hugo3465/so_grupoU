package application.charts;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import util.Configs;

/**
 * A classe `MemoryBarChart` representa um gráfico de barras para visualização
 * de dados relacionados à memória.
 * Utiliza a biblioteca JFreeChart para criar e exibir o gráfico.
 */
public class MemoryBarChart extends JFrame {
    /** O gráfico JFreeChart a ser exibido. */
    private JFreeChart chart;
    /** O conjunto de dados para o gráfico de barras. */
    private DefaultCategoryDataset dataset;

    /**
     * Construtor da classe MemoryBarChart.
     *
     * @param title Título do gráfico.
     */
    public MemoryBarChart(String title) {
        super(title);

        // Cria um conjunto de dados inicial
        dataset = createDataset();

        // Cria um gráfico com base no conjunto de dados
        chart = ChartFactory.createBarChart(
                title,
                "",
                "",
                dataset,
                PlotOrientation.HORIZONTAL,
                false,
                false,
                false);

        // Cria um painel para exibir o gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(300, 165));
        this.setLocation(500, 500);

        // Obtém o eixo do gráfico
        NumberAxis rangeAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();

        // Define o valor máximo para o eixo (mude conforme necessário)
        long maxValue = Configs.getTamanhoMem(); // Example: set the maximum value to 100
        rangeAxis.setRange(0, maxValue);

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
    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(0, "", "");
        return dataset;
    }

    /**
     * Atualiza o conjunto de dados com novos valores para tarefas em diferentes
     * estados.
     *
     * @param waitingTasks   Quantidade de tarefas em espera.
     * @param executingTasks Quantidade de tarefas em execução.
     */
    public void updateDataset(int ammountOfMemmory) {
        // Atualiza os valores no conjunto de dados (substitua esta lógica conforme
        // necessário)
        dataset.setValue(ammountOfMemmory, "", "");

        // Notifica o gráfico de que o conjunto de dados foi alterado
        // JFreeChart chart = ((ChartPanel)
        // getContentPane().getComponent(0)).getChart();
        // chart.fireChartChanged();

        // Repinta o painel do gráfico
        repaint();
    }

    /**
     * Fecha a janela do gráfico.
     */
    public void close() {
        this.dispose();
    }
}
