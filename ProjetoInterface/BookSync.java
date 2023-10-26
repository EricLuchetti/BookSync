import javax.swing.*;
import java.awt.*;

public class BookSync extends JFrame {

    private Font font = new Font("Arial", Font.BOLD, 16);
    private Font font2 = new Font("Arial", Font.ITALIC, 18);
    private Font font3 = new Font("Baskervville", Font.PLAIN, 22);
    JTextField login = new JTextField(30);
    JPasswordField senha = new JPasswordField(30);
    JTextField tfLogin, tfSenha;

    public static void main(String[] args) {
        BookSync ga = new BookSync();
        ga.desenhar();
    }

    public void desenhar() {
        this.setTitle("BookSync");
        this.setSize(1040, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Icon Form */
        try {
            ImageIcon icon = new ImageIcon("C:\\Users\\Amanda Oliveira\\Documents\\ProjetoInterface\\icon.png");
            setIconImage(icon.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Imagem Painel */
        JLabel imagemLabel = new JLabel();
        ImageIcon imagemLogo = new ImageIcon(
                "C:\\Users\\Amanda Oliveira\\Documents\\ProjetoInterface\\BooksyncImagem.png");
        imagemLabel.setIcon(imagemLogo);

        /* Introdução */
        String lbBemVindo = "<html><center>\nSEJA BEM-VINDO<br>Caso tenha cadastro, Acesse sua conta:</center></html>";
        JLabel lbDescricao = new JLabel(lbBemVindo);
        lbDescricao.setFont(font3);
        lbDescricao.setForeground(Color.WHITE);
        lbDescricao.setHorizontalAlignment(SwingConstants.CENTER);

        /* Form Painel Login */
        JLabel lbLogin = new JLabel("  E-mail");
        lbLogin.setFont(font);
        lbLogin.setForeground(Color.WHITE);

        tfLogin = new JTextField(" Insira o seu e-mail");
        tfLogin.setFont(font2);

        JLabel lbSenha = new JLabel("  Senha");
        lbSenha.setFont(font);
        lbSenha.setForeground(Color.WHITE);

        tfSenha = new JTextField(" Insira a sua senha");
        tfSenha.setFont(font2);

        /* Posições Login */
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(7, 1, 5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        painel.setOpaque(false);
        painel.add(lbDescricao);
        painel.add(lbLogin);
        painel.add(tfLogin);
        painel.add(lbSenha);
        painel.add(tfSenha);

        /* Posições Botoes + Painel */
        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(1, 2, 5, 5));
        botoes.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        botoes.setOpaque(false);
        botoes.add(btnAcessar());
        botoes.add(btnCancelar());

        JPanel formBotoesPanel = new JPanel();
        formBotoesPanel.setLayout(new BorderLayout());
        formBotoesPanel.setBackground(new Color(51, 51, 51));
        formBotoesPanel.add(painel, BorderLayout.CENTER);
        formBotoesPanel.add(botoes, BorderLayout.SOUTH);

        /* Posição Todos */
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(formBotoesPanel, BorderLayout.CENTER);
        mainPanel.add(imagemLabel, BorderLayout.WEST);

        add(mainPanel);
        setVisible(true);
    }

    /* Botões */
    JButton btnAcessar() {
        JButton acessar = new JButton("Acessar");
        acessar.setFont(font);
        acessar.setBackground(new Color(249, 161, 61));
        return acessar;
    }

    JButton btnCancelar() {
        JButton cancelar = new JButton("Cancelar");
        cancelar.setFont(font);
        cancelar.setBackground(new Color(249, 161, 61));
        cancelar.addActionListener(a -> {
            login.setText("");
            senha.setText("");
        });
        return cancelar;
    }
}