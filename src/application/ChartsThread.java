package application;

import core.Middleware;
import util.Configs;

public class ChartsThread implements Runnable {
    /**
     * MidleWear é um objeto partilhado, que serve para obter valores para os
     * gráfico;
     */
    Middleware middleware;

    public ChartsThread(Middleware middleware) {
        this.middleware = middleware;
    }

    @Override
    public void run() {
        TasksCircularChart circularChart = new TasksCircularChart("Tasks");
        circularChart.setVisible(true);

        TaskBarChart barChart = new TaskBarChart("Tasks");
        barChart.setVisible(true);

        // vai executar até que o sistema operativo seja desligado
        while (middleware.isOperational()) {
            // faz update do gráfico de 200 em 200 milisegundos
            try {

                circularChart.updateDataset(middleware.getNumberOfWaitingTasks(),
                        middleware.getNumberOfExecutingTasks(),
                        middleware.getNumberOfFinishedTasks());

                barChart.updateDataset(middleware.getNumberOfWaitingTasks(),
                        middleware.getNumberOfExecutingTasks());

                Thread.sleep(Configs.getTempoAtualizacaoDoGrafico());

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        
        circularChart.close();
        barChart.close();
    }
}
