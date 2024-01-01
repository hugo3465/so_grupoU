
package application;

import core.Middleware;
import core.Task;
import enums.TaskPriority;
import util.Configs;
import util.Random;

public class App extends javax.swing.JFrame {
        private Middleware middleware;
        private Thread charts;
        private MessageInterface earthFrame;
        private Thread stressTestThread;

        /**
         * Creates new form Interface
         */
        public App() {
                initComponents();
                this.middleware = new Middleware(this);

                // importar os valores do ficheiro de configuração
                Configs.importConfig();
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
                PowerOn = new javax.swing.JButton();
                PowerOff = new javax.swing.JButton();
                taskName = new javax.swing.JTextField();
                GenerateTask = new javax.swing.JButton();
                taskMessage = new javax.swing.JTextField();
                taskPriority = new java.awt.Choice();
                label2 = new java.awt.Label();
                label3 = new java.awt.Label();
                label4 = new java.awt.Label();
                label5 = new java.awt.Label();
                label6 = new java.awt.Label();
                label1 = new java.awt.Label();
                taskMemmory = new java.awt.Choice();
                taskExpectedTime = new java.awt.Choice();
                StressTest = new javax.swing.JButton();

                jCheckBoxMenuItem1.setSelected(true);
                jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                PowerOn.setText("Ligar");
                PowerOn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                PowerOnActionPerformed(evt);
                        }
                });

                PowerOff.setText("Desligar");
                PowerOff.setEnabled(false);
                PowerOff.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                PowerOffActionPerformed(evt);
                        }
                });

                GenerateTask.setText("Gerar Tarefa");
                GenerateTask.setEnabled(false);
                GenerateTask.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                GenerateTaskActionPerformed(evt);
                        }
                });

                label2.setText("Nome:");

                label3.setText("Mensagem:");

                label4.setText("Memória");

                label5.setText("Tempo Expectado:");

                label6.setText("Prioridade:");

                label1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                label1.setText("Tarefa");

                StressTest.setText("Teste de Stress");
                StressTest.setEnabled(false);
                StressTest.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                StressTestActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(287, 287, 287)
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(label4,
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(label5,
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addContainerGap(
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addGroup(layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(label3,
                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(label6,
                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(taskMessage)
                                                                                .addComponent(GenerateTask,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                111,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(taskPriority,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                110,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(taskMemmory,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(taskExpectedTime,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(28, 28, 28))
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(21, 21, 21)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(PowerOff,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(PowerOn,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(label1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(63, 63, 63))
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(352, 352, 352)
                                                                                                .addComponent(label2,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(taskName,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                111,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(185, 185, 185)
                                                                                                .addComponent(StressTest,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                160,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(31, 31, 31)
                                                                                                .addComponent(PowerOn))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                layout.createSequentialGroup()
                                                                                                                .addContainerGap()
                                                                                                                .addComponent(label1,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                28,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(taskName,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(label2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(9, 9, 9)
                                                                                                .addComponent(PowerOff))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(label3,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(taskMessage,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(label4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(taskMemmory,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(14, 14, 14)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(label5,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(taskExpectedTime,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(16, 16, 16)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(taskPriority,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(label6,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(34, 34, 34)
                                                                .addComponent(GenerateTask)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                50,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(StressTest)
                                                                .addGap(21, 21, 21)));

                taskPriority.add("Alta Prioridade");
                taskPriority.add("Baixa Prioridade");
                label1.getAccessibleContext().setAccessibleName("");
                taskMemmory.add("5");
                taskMemmory.add("10");
                taskMemmory.add("20");
                taskMemmory.add("30");
                taskMemmory.add("40");
                taskMemmory.add("50");
                taskExpectedTime.add("1 segundo");
                taskExpectedTime.add("5 segundos");
                taskExpectedTime.add("10 segundos");

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void PowerOnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_PowerOnActionPerformed
                PowerOn.setEnabled(false);
                PowerOff.setEnabled(true);

                GenerateTask.setEnabled(true);
                StressTest.setEnabled(true);

                // criar frames para o satélite e para a terra
                earthFrame = new MessageInterface("Terra");

                // criar os gráficos
                this.charts = new Thread(new ChartsThread(middleware));
                this.charts.start();

                // lgar o sistema operativo
                middleware.turnOnOperatingSystem();

        }// GEN-LAST:event_PowerOnActionPerformed

        private void PowerOffActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_PowerOffActionPerformed
                PowerOn.setEnabled(true);
                PowerOff.setEnabled(false);

                GenerateTask.setEnabled(false);
                StressTest.setEnabled(false);

                // Interrompe a Thread stressTest se estiver ativa
                if (stressTestThread != null && stressTestThread.isAlive()) {
                        stressTestThread.interrupt();
                        System.out.println("ESTAVA LIGADA");
                }

                // desligar o sistema operarivo
                middleware.turnOffOperatingSystem();

                // fechar as interfaces
                earthFrame.close();

        }// GEN-LAST:event_PowerOffActionPerformed

        private void GenerateTaskActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_GenerateTaskActionPerformed
                // só gera a tarefa, se o sistema operativo estiver ligado
                if (!PowerOn.isEnabled()) {
                        try {
                                String name = taskName.getText();
                                String message = taskMessage.getText();
                                int memmory = Integer.parseInt(taskMemmory.getSelectedItem());
                                int expectedTime = getExpectedTime();
                                TaskPriority priority = getPriority();

                                if (name != null && message != null) {
                                        Task task = new Task(name, message, memmory, priority, expectedTime);

                                        middleware.send(task);

                                        earthFrame.addText("Tarefa agendada");
                                }
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }

        }// GEN-LAST:event_GenerateTaskActionPerformed

        private void StressTestActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_StressTestActionPerformed
                // só gera a tarefa, se o sistema operativo estiver ligado
                if (!PowerOn.isEnabled()) {
                        earthFrame.addText("Realização de um Stress Test");
                        // é preciso esta thread, pois, sem ela o stress test ia monopolizar toda a
                        // interface, e não seria possível executar mais nada enquanto estivesse a
                        // lançar tarefas

                        this.stressTestThread = new Thread(() -> {
                                executeStressTest();
                        });
                        stressTestThread.start();
                }

        }// GEN-LAST:event_StressTestActionPerformed

        private int getExpectedTime() {
                int memmory = 0;
                switch (taskExpectedTime.getSelectedItem()) {
                        case "1 segundo":
                                memmory = 1000;
                                break;
                        case "5 segundos":
                                memmory = 5000;
                                break;
                        case "10 segundos":
                                memmory = 10000;
                                break;
                        default:
                                break;
                }
                return memmory;
        }

        private TaskPriority getPriority() {
                TaskPriority priority = null;

                switch (taskPriority.getSelectedItem()) {
                        case "Alta Prioridade":
                                priority = TaskPriority.HIGH_PRIORITY;
                                break;
                        case "Baixa Prioridade":
                                priority = TaskPriority.LOW_PRIORITY;
                                break;
                        default:
                                break;
                }

                return priority;
        }

        /**
         * Vai lançar o número de thread pré-definidas no ficheiro de configuração
         */
        private void executeStressTest() {
                long minTime = Configs.getTempoMinimoTarefa();
                long maxTime = Configs.getTempoMaximoTarefa();
                long timeBetweenTasks = Configs.getTempoEntreTarefas();
                int numTasks = Configs.getNumeroTarefasNoTesteStress();

                int i = 0;
                Task taskEx = null;
                while (i < numTasks) {
                        switch (Random.generateRandomNumber(0, 1)) {
                                case 0:
                                        taskEx = new Task("Auto generated" + i, "Auto Generated", 20,
                                                        TaskPriority.HIGH_PRIORITY,
                                                        Random.generateRandomNumber(minTime, maxTime));
                                        break;
                                case 1:
                                        taskEx = new Task("Auto generated" + i, "Auto Generated", 20,
                                                        TaskPriority.LOW_PRIORITY,
                                                        Random.generateRandomNumber(minTime, maxTime));
                                        break;
                                default:
                                        break;
                        }
                        i++;

                        middleware.send(taskEx);

                        earthFrame.addText("Tarefa agendada");

                        try {
                                Thread.sleep(timeBetweenTasks);
                        } catch (InterruptedException e) {
                                // Trata a exceção ou simplesmente sai do loop
                                break;
                        }
                }
        }

        public void receiveTask(Task task, String response) {
                earthFrame.addText("[" + task.getName() + "] respondeu com: " + response);
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                /* Set the Nimbus look and feel */
                // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
                // (optional) ">
                /*
                 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
                 * look and feel.
                 * For details see
                 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
                 */
                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(App.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(App.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(App.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(App.class.getName())
                                        .log(java.util.logging.Level.SEVERE, null, ex);
                }
                // </editor-fold>

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new App().setVisible(true);
                        }
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton GenerateTask;
        private javax.swing.JButton PowerOff;
        private javax.swing.JButton PowerOn;
        private javax.swing.JButton StressTest;
        private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
        private java.awt.Label label1;
        private java.awt.Label label2;
        private java.awt.Label label3;
        private java.awt.Label label4;
        private java.awt.Label label5;
        private java.awt.Label label6;
        private java.awt.Choice taskExpectedTime;
        private java.awt.Choice taskMemmory;
        private javax.swing.JTextField taskMessage;
        private javax.swing.JTextField taskName;
        private java.awt.Choice taskPriority;
        // End of variables declaration//GEN-END:variables
}
