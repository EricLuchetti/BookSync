package frontEnd.ProjetoInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

class RoundButton extends JButton {
    public RoundButton(String label) {
        super(label);
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 30, 30);

        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 30, 30);
    }

    Shape shape;

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        }
        return shape.contains(x, y);
    }
}

public class TelaAdm {

    // Método principal que inicia o programa
    public static void main(String[] args) {
        // SwingUtilities.invokeLater() é usado para garantir que a interface gráfica seja construída em uma thread específica.
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    // Este método cria e exibe a interface gráfica
    private static void createAndShowGUI() {
        // Cria uma janela (frame) com o título "Menu"
        JFrame frame = new JFrame("Menu");

        // Define o comportamento padrão ao fechar a janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define o tamanho da janela
        frame.setSize(1300, 650);

        // Coloca a janela no centro da tela
        frame.setLocationRelativeTo(null);

        try {
            // Tenta carregar e definir um ícone para a janela
            ImageIcon icon = new ImageIcon("C:\\Users\\Amanda Oliveira\\Documents\\ProjetoInterface\\icon.png");
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            // Se houver um erro ao carregar o ícone, imprime o erro
            e.printStackTrace();
        }

        // Cria um painel personalizado com um fundo colorido degradê
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

        // Cria um painel para os botões, configurando seu layout e tornando-o transparente
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 60));
        buttonPanel.setOpaque(false);

        // Cria três botões estilizados e os adiciona ao painel de botões
        RoundButton manageButton = createStyledButton("Gerenciar livros", 300, 400);
        manageButton.addActionListener(new ActionListener() {
            @Override            
            public void actionPerformed(ActionEvent e){
            frontEnd.ProjetoInterface.BookSync.main(null);
            dispose();
            }
            return manageButton;

        });
        RoundButton bookShowcaseButton = createStyledButton("Vitrine de Livros", 300, 400);
        RoundButton addBookButton = createStyledButton("Adicionar Livro", 300, 400);

        buttonPanel.add(manageButton);
        buttonPanel.add(bookShowcaseButton);
        buttonPanel.add(addBookButton);

        // Adiciona o painel de botões ao painel principal na região Centro
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Define o conteúdo da janela como o painel principal e a torna visível
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // Este método cria e configura um botão com um estilo específico
    private static RoundButton createStyledButton(String text, int width, int height) {
        RoundButton button = new RoundButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(new Color(195, 145, 128));
        button.setForeground(Color.WHITE);
        return button;
    }
}