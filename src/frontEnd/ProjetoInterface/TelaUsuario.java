package frontEnd.ProjetoInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

// Declaração da classe principal
public class TelaUsuario {

    // Método principal que será chamado para iniciar o programa
    public static void main(String[] args) {
        // Chama o método createAndShowGUI() na thread de eventos Swing
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    // Método para criar e exibir a interface gráfica
    private static void createAndShowGUI() {
        // Cria um JFrame (janela) com o título "Menu"
        JFrame frame = new JFrame("Menu");
        
        // Define o comportamento padrão ao fechar a janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Define o tamanho da janela
        frame.setSize(1300, 650);
        
        // Coloca a janela no centro da tela
        frame.setLocationRelativeTo(null);

        // Cria um JPanel personalizado com um fundo degradê
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Define as cores de início e fim para o degradê
                Color startColor = new Color(255, 165, 0);
                Color endColor = new Color(128, 0, 128);
                
                // Cria um objeto de degradê
                GradientPaint gradient = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
                
                // Configura o objeto Graphics2D para usar o degradê e preenche o painel com as cores
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Configura o layout do painel como BorderLayout
        panel.setLayout(new BorderLayout());

        // Adiciona um espaço vertical no topo do painel
        panel.add(Box.createVerticalStrut(30), BorderLayout.NORTH);

        // Cria uma etiqueta (label) com uma saudação e a adiciona ao painel
        String greetingText = "<html><div style='font-family: Arial; font-size: 30px; font-weight: 400; line-height: 54px; letter-spacing: 0em; text-align: center; margin: 0;'>Olá, o que você deseja realizar hoje?</div></html>";
        JLabel greeting = new JLabel(greetingText);
        greeting.setForeground(Color.WHITE);
        greeting.setHorizontalAlignment(SwingConstants.CENTER);

        // Adiciona a etiqueta ao painel na região Norte (topo)
        panel.add(greeting, BorderLayout.NORTH);

        // Cria um JPanel para os botões com layout FlowLayout e torna-o transparente
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 60));
        buttonPanel.setOpaque(false);

        // Cria dois botões estilizados e os adiciona ao painel de botões
        RoundButton bookShowcaseButton = createStyledButton("Vitrine de Livros", 300, 400);
        RoundButton addBookButton = createStyledButton("Adicionar Livro", 300, 400);

        buttonPanel.add(bookShowcaseButton);
        buttonPanel.add(addBookButton);

        // Adiciona o painel de botões ao painel principal na região Centro
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Define o conteúdo da janela como o painel principal e a torna visível
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // Método para criar um botão estilizado
    private static RoundButton createStyledButton(String text, int width, int height) {
        RoundButton button = new RoundButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(new Color(195, 145, 128));
        button.setForeground(Color.WHITE);
        return button;
    }
}

// Classe que estende JButton para criar botões redondos
class RoundButton extends JButton {

    // Construtor que recebe um rótulo para o botão
    public RoundButton(String label) {
        super(label);
        setContentAreaFilled(false);
    }

    // Método para pintar o componente (botão)
    @Override
    protected void paintComponent(Graphics g) {
        // Verifica se o botão está pressionado (armado)
        if (getModel().isArmed()) {
            g.setColor(new Color(150, 150, 150)); // Define a cor quando pressionado
        } else {
            g.setColor(getBackground()); // Usa a cor de fundo padrão
        }
        // Desenha um retângulo arredondado para representar o botão
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 20, 20);
        super.paintComponent(g);
    }

    // Método para pintar a borda do componente (botão)
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.WHITE); // Define a cor da borda
        // Desenha a borda do retângulo arredondado
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 20, 20);
    }

    // Método para verificar se um ponto (x, y) está dentro do botão
    @Override
    public boolean contains(int x, int y) {
        // Verifica se a forma (shape) é nula ou se as dimensões mudaram
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            // Cria um novo objeto RoundRectangle2D para representar a forma do botão
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        }
        // Verifica se o ponto (x, y) está dentro da forma do botão
        return shape.contains(x, y);
    }

    // Variável para armazenar a forma do botão
    private Shape shape;
}