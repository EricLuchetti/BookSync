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

public class CadastroTela extends JFrame {
    private Font font = new Font("Arial", Font.BOLD, 16);
    private Font font2 = new Font("Arial", Font.PLAIN, 18);
    private Font font3 = new Font("Baskerville", Font.PLAIN, 22);

    private JTextField nomeUsuarioField;
    private JTextField emailField;
    private JTextField idadeField;
    private JComboBox<String> sexoComboBox;
    private JComboBox<String> generoFavoritoComboBox;
    private JPasswordField senhaField;
    private JButton cadastrarButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastroTela cadastroTela = new CadastroTela();
            cadastroTela.desenhar();
        });

        SwingUtilities.invokeLater(() -> {
            try {
                // Configurar o Look and Feel
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CadastroTela() {
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
                "C:/Users/Amanda Oliveira/Documents/ProjetoInterface/FundoTelaTotal.png");

        JLabel lbImagemLogo = new JLabel();
        ImageIcon imagemLogo = new ImageIcon("C:/Users/Amanda Oliveira/Documents/ProjetoInterface/BooksyncImagem.PNG");
        lbImagemLogo.setIcon(imagemLogo);

        String lbBemVindo = "<html><center>\nSEJA BEM-VINDO ADMIN<br>Para realizar um novo cadastro, insira as informações do usuário:</center></html>";
        JLabel lbDescricao = new JLabel(lbBemVindo);
        lbDescricao.setFont(font3);
        lbDescricao.setForeground(Color.WHITE);
        lbDescricao.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lbNomeUsuario = new JLabel("Nome de Usuário:");
        lbNomeUsuario.setFont(font);
        lbNomeUsuario.setForeground(Color.WHITE);

        nomeUsuarioField = new JTextField();
        nomeUsuarioField.setFont(font2);
        nomeUsuarioField.setForeground(Color.WHITE);
        Dimension novaDimensao = new Dimension(300, 40);
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
        add(new JLabel("Idade:"));

        JLabel lbSexo = new JLabel("Sexo:");
        lbSexo.setFont(font);
        lbSexo.setForeground(Color.WHITE);

        String[] sexos = { "Masculino", "Feminino", "Outro" };
        sexoComboBox = new JComboBox<>(sexos);
        sexoComboBox.setFont(font2);
        sexoComboBox.setForeground(Color.WHITE);
        sexoComboBox.setOpaque(false);
        sexoComboBox.setBorder(new RoundedBorder(10));
        sexoComboBox.setUI(new CustomComboBoxUI());

        JLabel lbGenero = new JLabel("Gênero Favorito:");
        lbGenero.setFont(font);
        lbGenero.setForeground(Color.WHITE);

        String[] generos = { "Mystery", "Fantasy", "Thriller", "Romance", "Non-Fiction", "Biography",
                "Historical Fiction", "Self-Help", "Young Adult" };
        generoFavoritoComboBox = new JComboBox<>(generos);
        generoFavoritoComboBox.setFont(font2);
        generoFavoritoComboBox.setForeground(Color.WHITE);
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

        cadastrarButton = new JButton("Cadastrar");
        add(cadastrarButton);

        /*
         * cadastrarButton.addActionListener(new ActionListener() {
         * 
         * @Override
         * public void actionPerformed(ActionEvent e) {
         * cadastrarUsuario();
         * }
         * });
         */

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        painel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        painel.add(lbDescricao, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
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
        gbc.gridwidth = 1;
        painel.add(cadastrarButton, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        painel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        JPanel acessosPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        acessosPanel.setOpaque(false);
        acessosPanel.add(cadastrarButton);

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

        /*
         * private void cadastrarUsuario() {
         * try {
         * ConexaoMysql db1 = new ConexaoMysql();
         * String sql =
         * "INSERT INTO user (name, login, idade, sex, genre_id, password) VALUES (?, ?, ?, ?, ?, ?)"
         * ;
         * PreparedStatement preparedStatement = connection.prepareStatement(sql);
         * preparedStatement.setString(1, nomeUsuarioField.getText());
         * preparedStatement.setString(2, emailField.getText());
         * preparedStatement.setInt(3, Integer.parseInt(idadeField.getText()));
         * preparedStatement.setString(4, (String) sexoComboBox.getSelectedItem());
         * preparedStatement.setString(5, (String)
         * generoFavoritoComboBox.getSelectedItem());
         * preparedStatement.setString(6, new String(senhaField.getPassword()));
         * preparedStatement.executeUpdate();
         * db1.OpenDatabase();
         * db1.Executaquery(sql);
         * db1.CloseDatabase();
         * 
         * JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
         * connection.close();
         * } catch (SQLException e) {
         * JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário: " +
         * e.getMessage());
         * }
         * }
         */

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
