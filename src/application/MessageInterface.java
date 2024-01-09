package application;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A classe `MessageInterface` representa uma interface gráfica simples para
 * exibir mensagens.
 * Utiliza a biblioteca Swing do Java para criar uma janela com uma área de
 * texto.
 */
public class MessageInterface {
    /** Nome associado à instância da interface. */
    private String name;
    /** O frame (janela) da interface. */
    private JFrame frame;
    /** A área de texto para exibir as mensagens. */
    private JTextArea textArea;

    /**
     * Construtor da classe `MessageInterface`.
     *
     * @param name O nome associado à instância da interface.
     */
    public MessageInterface(String name) {
        this.name = name;
        frame = new JFrame(name);
        textArea = new JTextArea(10, 30);

        // Desabilita a edição da área de texto
        textArea.setEnabled(false);
        // Adiciona uma barra de rolagem à área de texto
        frame.add(new JScrollPane(textArea));
        // Define o tamanho da janela
        frame.setSize(400, 700);
        // Torna a janela visível
        frame.setVisible(true);
    }

    /**
     * Adiciona uma mensagem à área de texto da interface.
     *
     * @param message A mensagem a ser adicionada.
     */
    public void addText(String message) {
        textArea.append("[" + name + "]: " + message + "\n");
    }

    /**
     * Fecha a janela da interface.
     */
    public void close() {
        frame.dispose();
    }
}
