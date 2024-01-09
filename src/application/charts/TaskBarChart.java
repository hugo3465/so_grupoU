package application.charts;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Representa um gráfico de barras para visualizar a quantidade de tarefas em
 * diferentes estados.
 */
public class TaskBarChart extends JFrame {

    /** O gráfico JFreeChart a ser exibido. */
    private JFreeChart chart;
    /** O conjunto de dados para o gráfico de barras. */
    private DefaultCategoryDataset dataset;

    /**
     * Construtor da classe TaskBarChart.
     *
     * @param title Título do gráfico.
     */
    public TaskBarChart(String title) {
        super(title);

        // Cria um conjunto de dados inicial
        dataset = createDataset();

        // Cria um gráfico com base no conjunto de dados
        chart = ChartFactory.createBarChart(
                title,
                "Tasks",
                "Quantity",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false);

        // Cria um painel para exibir o gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 350));
        this.setLocation(500, 500);

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
        dataset.addValue(0, "", "Waiting");
        dataset.addValue(0, "", "Executing");
        return dataset;
    }

    /**
     * Atualiza o conjunto de dados com novos valores para tarefas em diferentes
     * estados.
     *
     * @param waitingTasks   Quantidade de tarefas em espera.
     * @param executingTasks Quantidade de tarefas em execução.
     */
    public void updateDataset(int waitingTasks, int executingTasks) {
        // Atualiza os valores no conjunto de dados (substitua esta lógica conforme
        // necessário)
        dataset.setValue(waitingTasks, "", "Waiting");
        dataset.setValue(executingTasks, "", "Executing");

        // Notifica o gráfico de que o conjunto de dados foi alterado
        // JFreeChart chart = ((ChartPanel)
        // getContentPane().getComponent(0)).getChart();
        // chart.fireChartChanged();

        // // Repinta o painel do gráfico
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
