import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.Dimension;

public class CircularChart extends JFrame {
    private DefaultPieDataset dataset;

    public CircularChart(String title) {
        super(title);

        // Create an initial dataset
        dataset = createDataset();

        // Create a chart based on the dataset
        JFreeChart chart = ChartFactory.createPieChart(
                title,
                dataset,
                true,
                true,
                false);

        // Create a panel to display the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));

        // Set up the JFrame
        setContentPane(chartPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Waiting", 0);
        dataset.setValue("Executing", 0);
        dataset.setValue("Finished", 0);
        return dataset;
    }

    public void updateDataset(int waitingTasks, int executingTasks, int finishedTasks) {

        // Update values in the dataset (replace this logic with your own)
        dataset.setValue("Waiting", waitingTasks);
        dataset.setValue("Executing", executingTasks);
        dataset.setValue("Finished", finishedTasks);


        // NÃO SEI SE ESTAS LINHAS ERAM IMPORTANTES
        // Notify the chart that the dataset has changed
        // JFreeChart chart = ((ChartPanel) getContentPane().getComponent(0)).getChart();
        // chart.fireChartChanged();

        // Repaint the chart panel
        // ((ChartPanel) getContentPane().getComponent(0)).repaint();

    }
}
