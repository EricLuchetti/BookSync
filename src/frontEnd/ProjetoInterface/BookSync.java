package frontEnd.ProjetoInterface;

import javax.swing.*;
import javax.swing.border.AbstractBorder;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookSync extends JFrame {
    // Fontes do sistema
    private Font font = new Font("Arial", Font.BOLD, 16);
    private Font font2 = new Font("Arial", Font.ITALIC, 18);
    private Font font3 = new Font("Baskerville", Font.PLAIN, 22);
    JTextField tfLogin, tfSenha;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookSync ga = new BookSync();
            ga.desenhar();
        });
    }

    public BookSync() {
        this.setTitle("BookSync");
        this.setSize(1040, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        // Icone do Sistema
        try {
            ImageIcon icon = new ImageIcon("src\\frontEnd\\ProjetoInterface\\BooksyncImagem.PNG");
            setIconImage(icon.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Background
    public void desenhar() {
        BackgroundPanel mainPanel = new BackgroundPanel(
                "src\\frontEnd\\ProjetoInterface\\FundoBookSync.jpg");

        // Imagem BookSync, lado esquerdo
        JLabel lbImagemLogo = new JLabel();
        ImageIcon imagemLogo = new ImageIcon("src\\frontEnd\\ProjetoInterface\\BooksyncImagem.PNG");
        lbImagemLogo.setIcon(imagemLogo);

        // Bem Vindo
        String lbBemVindo = "<html><center>\nSEJA BEM-VINDO<br>Caso tenha cadastro, Acesse sua conta:</center></html>";
        JLabel lbDescricao = new JLabel(lbBemVindo);
        lbDescricao.setFont(font3);
        lbDescricao.setForeground(Color.WHITE);
        lbDescricao.setHorizontalAlignment(SwingConstants.CENTER);

        // Login
        JLabel lbLogin = new JLabel("  E-mail");
        lbLogin.setFont(font);
        lbLogin.setForeground(Color.WHITE);

        tfLogin = new JTextField();
        tfLogin.setFont(font2);
        tfLogin.setBackground(Color.WHITE);
        tfLogin.setForeground(Color.WHITE);
        Dimension novaDimensao = new Dimension(300, 40);
        tfLogin.setPreferredSize(novaDimensao);
        tfLogin.setOpaque(false);
        tfLogin.setBorder(new RoundedBorder(10));

        // Senha
        JLabel lbSenha = new JLabel("  Senha");
        lbSenha.setFont(font);
        lbSenha.setForeground(Color.WHITE);

        tfSenha = new JPasswordField();
        tfSenha.setFont(font2);
        tfSenha.setBackground(Color.WHITE);
        tfSenha.setForeground(Color.WHITE);
        tfSenha.setPreferredSize(novaDimensao);
        tfSenha.setOpaque(false);
        tfSenha.setBorder(new RoundedBorder(10));

        // Esqueceu sua senha
        JLabel lbEsqueceuSenha = new JLabel(
                "<html><center>Esqueceu sua senha? Fale com o administrador.</center></html>");
        lbEsqueceuSenha.setFont(font);
        lbEsqueceuSenha.setForeground(Color.WHITE);

        // Botão Acesso ao Sistema
        JButton acessar = btnAcessar();

        // Painel de Posições
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        painel.setOpaque(false);

        // Posição dos elementos
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 15, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;

        painel.add(lbDescricao, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        painel.add(lbLogin, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(tfLogin, gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 1;
        painel.add(lbSenha, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        painel.add(tfSenha, gbc);

        gbc.gridy = 5;
        gbc.gridwidth = 2;
        painel.add(acessar, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        painel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        // Painel com Botão
        JPanel acessosPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        acessosPanel.setOpaque(false);
        acessosPanel.add(acessar);

        // Painel Esqueci a Senha
        JPanel esqueceuSenhaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        esqueceuSenhaPanel.setOpaque(false);
        esqueceuSenhaPanel.add(lbEsqueceuSenha);
        acessosPanel.add(esqueceuSenhaPanel);

        gbc.gridy++;
        gbc.gridwidth = 2;
        painel.add(acessosPanel, gbc);

        // Painel de Posição
        JPanel painelPosicao = new JPanel(new BorderLayout());
        painelPosicao.setOpaque(false);
        painelPosicao.add(painel, BorderLayout.CENTER);

        // Posições do painel e imagem
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.add(lbImagemLogo, BorderLayout.WEST);
        contentPanel.add(painelPosicao, BorderLayout.CENTER);

        // Painel Terminado e centralizado
        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Design, caixa de Login
    JTextField createRoundedTextField(String placeholder, int radius) {
        JTextField tf = new JTextField(placeholder);
        tf.setFont(font2);
        tf.setOpaque(true);
        tf.setBorder(new RoundedBorder(radius));
        return tf;
    }

    // Design, caixa de Senha
    JPasswordField createRoundedPasswordField(String placeholder, int radius) {
        JPasswordField tf = new JPasswordField(placeholder);
        tf.setFont(font2);
        tf.setOpaque(true);
        tf.setBorder(new RoundedBorder(radius));
        return tf;
    }

    // Desigin do Botão
    JButton btnAcessar() {
        RoundedButton acessar = new RoundedButton("Acessar", 50);
        acessar.setFont(font);
        acessar.setForeground(Color.WHITE);
        acessar.setBackground(new Color(249, 161, 61));

        // Definação de dimensões do botão
        acessar.setPreferredSize(new Dimension(100, 40));
        acessar.setMinimumSize(new Dimension(100, 40));
        acessar.setMaximumSize(new Dimension(100, 40));

        acessar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the values from tfLogin and tfSenha
                String login = tfLogin.getText();
                String senha = tfSenha.getText();

                // Call the login function from another file with login and senha values
                backEnd.LoginHandler.login(login, senha);

                // Close the current screen
                dispose();
            }
        });

        return acessar;
    }

    // Fundo da tela, background
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            this.backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Classe RoundBorder caixas de botão
    class RoundedButton extends JButton {
        private int radius;

        public RoundedButton(String label, int radius) {
            super(label);
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(getBackground());
            g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, radius, radius));
            super.paintComponent(g2d);
            g2d.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
        }

        @Override
        public boolean contains(int x, int y) {
            return new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, radius, radius).contains(x, y);
        }
    }

    // Classe RoundBorder de caixas de textos
    class RoundedBorder extends AbstractBorder {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.WHITE);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = radius;
            return insets;
        }
    }
}
