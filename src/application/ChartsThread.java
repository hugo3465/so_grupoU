package application;

import application.charts.MemoryBarChart;
import application.charts.TaskBarChart;
import application.charts.TasksCircularChart;
import core.Middleware;
import util.Configs;

/**
 * A classe `ChartsThread` representa uma thread responsável por atualizar e
 * exibir gráficos relacionados às estatísticas do sistema.
 * Utiliza objetos de gráficos específicos, como {@link TasksCircularChart},
 * {@link TaskBarChart} e {@link MemoryBarChart}.
 */
public class ChartsThread implements Runnable {
    /**
     * MidleWear é um objeto partilhado, que serve para obter valores para os
     * gráfico;
     */
    Middleware middleware;

    /**
     * Construtor da classe `ChartsThread`.
     *
     * @param middleware O objeto `Middleware` a ser utilizado para obter valores
     *                   para os gráficos.
     */
    public ChartsThread(Middleware middleware) {
        this.middleware = middleware;
    }

    /**
     * Método principal que executa a thread para atualizar e exibir os gráficos.
     * Executa até que o sistema operativo seja desligado.
     */
    @Override
    public void run() {
        // inicia o gráfico circular das Tasks
        TasksCircularChart circularChart = new TasksCircularChart("Tasks");
        circularChart.setVisible(true);

        // Inicia o gráfico de barras das Tasks
        TaskBarChart barChart = new TaskBarChart("Tasks");
        barChart.setVisible(true);

        // Inicia o gráfico de barras da memmoria a ser usada
        MemoryBarChart memoryBarChart = new MemoryBarChart("Used Memmory");
        memoryBarChart.setVisible(true);

        // Executa até que o sistema operativo seja desligado
        while (middleware.isOperational()) {
            // faz update do gráfico de 200 em 200 milisegundos
            try {

                // update do gráfico circular das tarefas
                circularChart.updateDataset(middleware.getNumberOfWaitingTasks(),
                        middleware.getNumberOfExecutingTasks(),
                        middleware.getNumberOfFinishedTasks());

                // update do gráfico de barras das tarefas
                barChart.updateDataset(middleware.getNumberOfWaitingTasks(),
                        middleware.getNumberOfExecutingTasks());

                // update do gráfico de barras da memória em execução
                memoryBarChart.updateDataset(middleware.getMemoryOnUsage());

                Thread.sleep(Configs.getTempoAtualizacaoDoGrafico());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        // encerramento dos gráficos quando o sistema operativo for desligado
        circularChart.close();
        barChart.close();
        memoryBarChart.close();
    }
}
