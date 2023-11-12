import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroTela extends JFrame {
    private JTextField nomeUsuarioField;
    private JTextField emailField;
    private JTextField idadeField;
    private JComboBox<String> sexoComboBox;
    private JComboBox<String> generoFavoritoComboBox;
    private JPasswordField senhaField;
    private JButton cadastrarButton;

    public CadastroTela() {
        setTitle("Cadastro de Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Nome de Usuário:"));
        nomeUsuarioField = new JTextField();
        add(nomeUsuarioField);

        add(new JLabel("Login:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Idade:"));
        idadeField = new JTextField();
        add(idadeField);

        add(new JLabel("Sexo:"));
        String[] sexos = {"Masculino", "Feminino", "Outro"};
        sexoComboBox = new JComboBox<>(sexos);
        add(sexoComboBox);

        add(new JLabel("Gênero Favorito:"));
        String[] generos = {"Mystery", "Fantasy", "Thriller", "Romance", "Non-Fiction", "Biography", "Historical Fiction", "Self-Help", "Young Adult"};
        generoFavoritoComboBox = new JComboBox<>(generos);
        add(generoFavoritoComboBox);

        add(new JLabel("Senha:"));
        senhaField = new JPasswordField();
        add(senhaField);

        cadastrarButton = new JButton("Cadastrar");
        add(cadastrarButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void cadastrarUsuario() {
        try {
           ConexaoMysql db1 = new ConexaoMysql();
            String sql = "INSERT INTO user (name, login, idade, sex, genre_id, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nomeUsuarioField.getText());
            preparedStatement.setString(2, emailField.getText());
            preparedStatement.setInt(3, Integer.parseInt(idadeField.getText()));
            preparedStatement.setString(4, (String) sexoComboBox.getSelectedItem());
            preparedStatement.setString(5, (String) generoFavoritoComboBox.getSelectedItem());
            preparedStatement.setString(6, new String(senhaField.getPassword()));
            preparedStatement.executeUpdate();
            db1.OpenDatabase();
            db1.Executaquery(sql);
            db1.CloseDatabase();

            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastroTela cadastroTela = new CadastroTela();
            cadastroTela.setVisible(true);
        });
    }
}
