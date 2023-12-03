import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
import java.awt.geom.RoundRectangle2D;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class CadastroTela2 extends JFrame {
    private Font font = new Font("Arial", Font.BOLD, 12);
    private Font font2 = new Font("Arial", Font.PLAIN, 14);
    private Font font3 = new Font("Baskerville", Font.PLAIN, 16);

    private JTextField nomeUsuarioField;
    private JTextField emailField;
    private JTextField idadeField;
    private JComboBox<String> sexoComboBox;
    private JComboBox<String> generoFavoritoComboBox;
    private JPasswordField senhaField;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            CadastroTela cadastroTela = new CadastroTela();
            cadastroTela.desenhar();
        });
    }

    public CadastroTela2() {
        setTitle("Cadastro de Usuário");
        this.setSize(1040, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        try {
            ImageIcon icon = new ImageIcon("C:/Users/Amanda Oliveira/Documents/ProjetoInterface/BooksyncImagem.PNG");
            setIconImage(icon.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void desenhar() {
        BackgroundPanel mainPanel = new BackgroundPanel(
                "C:/Users/Amanda Oliveira/Documents/ProjetoInterface/FundoBookSync.jpg");

        JLabel lbImagemLogo = new JLabel();
        ImageIcon imagemLogo = new ImageIcon("C:/Users/Amanda Oliveira/Documents/ProjetoInterface/BooksyncImagem.PNG");
        lbImagemLogo.setIcon(imagemLogo);

        String lbBemVindo = "<html><center>Seja bem-vindo Admin!<br>Para realizar um cadastro, insira as informacoes do usuario:</center></html>";
        JLabel lbDescricao = new JLabel(lbBemVindo);
        lbDescricao.setFont(font3);
        lbDescricao.setForeground(Color.WHITE);
        lbDescricao.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lbNomeUsuario = new JLabel("Nome Completo:");
        lbNomeUsuario.setFont(font);
        lbNomeUsuario.setForeground(Color.WHITE);

        nomeUsuarioField = new JTextField();
        nomeUsuarioField.setFont(font2);
        nomeUsuarioField.setForeground(Color.WHITE);
        Dimension novaDimensao = new Dimension(300, 35);
        nomeUsuarioField.setPreferredSize(novaDimensao);
        nomeUsuarioField.setOpaque(false);
        nomeUsuarioField.setBorder(new RoundedBorder(10));

        JLabel lbEmail = new JLabel("Login:");
        lbEmail.setFont(font);
        lbEmail.setForeground(Color.WHITE);

        emailField = new JTextField();
        emailField.setFont(font2);
        emailField.setForeground(Color.WHITE);
        emailField.setPreferredSize(novaDimensao);
        emailField.setOpaque(false);
        emailField.setBorder(new RoundedBorder(10));

        JLabel lbIdade = new JLabel("Idade:");
        lbIdade.setFont(font);
        lbIdade.setForeground(Color.WHITE);

        idadeField = new JTextField();
        idadeField.setFont(font2);
        idadeField.setForeground(Color.WHITE);
        idadeField.setPreferredSize(novaDimensao);
        idadeField.setOpaque(false);
        idadeField.setBorder(new RoundedBorder(10));

        JLabel lbSexo = new JLabel("Sexo:");
        lbSexo.setFont(font);
        lbSexo.setForeground(Color.WHITE);

        String[] sexos = { "Masculino", "Feminino", "Outro" };
        sexoComboBox = new JComboBox<>(sexos);
        sexoComboBox.setFont(font2);
        sexoComboBox.setForeground(Color.WHITE);
        sexoComboBox.setPreferredSize(novaDimensao);
        sexoComboBox.setOpaque(false);
        sexoComboBox.setBorder(new RoundedBorder(10));
        sexoComboBox.setUI(new CustomComboBoxUI());

        JLabel lbGenero = new JLabel("Genero Favorito:");
        lbGenero.setFont(font);
        lbGenero.setForeground(Color.WHITE);

        String[] generos = { "Mystery", "Fantasy", "Thriller", "Romance", "Non-Fiction", "Biography",
                "Historical Fiction", "Self-Help", "Young Adult" };
        generoFavoritoComboBox = new JComboBox<>(generos);
        generoFavoritoComboBox.setFont(font2);
        generoFavoritoComboBox.setForeground(Color.WHITE);
        generoFavoritoComboBox.setPreferredSize(novaDimensao);
        generoFavoritoComboBox.setOpaque(false);
        generoFavoritoComboBox.setBorder(new RoundedBorder(10));
        generoFavoritoComboBox.setUI(new CustomComboBoxUI());

        JLabel lbSenha = new JLabel("Insira uma senha:");
        lbSenha.setFont(font);
        lbSenha.setForeground(Color.WHITE);

        senhaField = new JPasswordField();
        senhaField.setFont(font2);
        senhaField.setForeground(Color.WHITE);
        senhaField.setPreferredSize(novaDimensao);
        senhaField.setOpaque(false);
        senhaField.setBorder(new RoundedBorder(10));
        add(senhaField);

        JButton cadastrarButton = btnCadastrar();
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicione aqui a lógica para tratar o clique no botão de cadastro
                JOptionPane.showMessageDialog(null, "Botão Cadastrar clicado!");
            }
        });

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(40, 50, 50, 50));
        painel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 2, 0, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(0, 2, 0, 15);
        gbc.gridy = 0;
        painel.add(lbDescricao, gbc);

        gbc.insets = new Insets(10, 2, 0, 15);
        gbc.gridy++;
        painel.add(lbNomeUsuario, gbc);

        gbc.gridy++;
        painel.add(nomeUsuarioField, gbc);

        gbc.gridy++;
        painel.add(lbEmail, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(emailField, gbc);

        gbc.gridy++;
        painel.add(lbIdade, gbc);

        gbc.gridy++;
        painel.add(idadeField, gbc);

        gbc.gridy++;
        painel.add(lbSexo, gbc);

        gbc.gridy++;
        painel.add(sexoComboBox, gbc);

        gbc.gridy++;
        painel.add(lbGenero, gbc);

        gbc.gridy++;
        painel.add(generoFavoritoComboBox, gbc);

        gbc.gridy++;
        painel.add(lbSenha, gbc);

        gbc.gridy++;
        painel.add(senhaField, gbc);

        gbc.gridy++;
        painel.add(cadastrarButton, gbc);

        JPanel formBotoesPanel = new JPanel(new BorderLayout());
        formBotoesPanel.setOpaque(false);
        formBotoesPanel.add(painel, BorderLayout.CENTER);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.add(lbImagemLogo, BorderLayout.WEST);
        contentPanel.add(formBotoesPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);

        // Caixas de Opções, mudança de cor
        senhaField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(senhaField.getPassword()).equals("Senha")) {
                    senhaField.setText("");
                    senhaField.setForeground(Color.BLACK); // Restaura a cor preta
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(senhaField.getPassword()).isEmpty()) {
                    senhaField.setText("Senha");
                    senhaField.setForeground(Color.GRAY);
                }
            }
        });

    }

    // Botão e Design
    JButton btnCadastrar() {
        JButton cadastrarButton = new RoundedButton("Cadastrar", 50);
        cadastrarButton.setFont(font);
        cadastrarButton.setForeground(Color.WHITE);
        cadastrarButton.setBackground(new Color(249, 161, 61));

        // Definação de dimensões do botão
        cadastrarButton.setPreferredSize(new Dimension(100, 40));
        cadastrarButton.setMinimumSize(new Dimension(100, 40));
        cadastrarButton.setMaximumSize(new Dimension(100, 40));

        return cadastrarButton;
    }

    // Design botão de alternativa
    class CustomComboBoxUI extends BasicComboBoxUI {
        private boolean comboBoxHasFocus = false;

        @Override
        protected JButton createArrowButton() {
            return new JButton() {
                @Override
                public int getWidth() {
                    return 0;
                }
            };
        }

        @Override
        protected ComboPopup createPopup() {
            return new BasicComboPopup(comboBox) {
                @Override
                protected JScrollPane createScroller() {
                    return new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                }
            };
        }

        @Override
        public void installDefaults() {
            super.installDefaults();
            addComboBoxFocusListener(); // Adiciona o FocusListener à JComboBox
            comboBox.setRenderer(createRenderer()); // Configura o ListCellRenderer
        }

        @Override
        public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
            g.setColor(new Color(0, 0, 0, 0));
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }

        @Override
        protected ListCellRenderer createRenderer() {
            return new BasicComboBoxRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                        boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                    if (comboBoxHasFocus) {
                        // Defina a cor do texto quando a JComboBox estiver aberta
                        setForeground(Color.BLACK);
                    } else {
                        // Defina a cor do texto quando a JComboBox estiver fechada
                        setForeground(Color.WHITE);
                    }

                    setOpaque(false);
                    return this;
                }

                @Override
                public void paintComponent(Graphics g) {
                    setBackground(new Color(0, 0, 0, 0));
                    super.paintComponent(g);
                }
            };
        }

        // Adiciona um FocusListener à JComboBox
        private void addComboBoxFocusListener() {
            comboBox.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    comboBoxHasFocus = true;
                    comboBox.repaint();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    comboBoxHasFocus = false;
                    comboBox.repaint();
                }
            });
        }
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

    // Classe RoundBorder de texto
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
