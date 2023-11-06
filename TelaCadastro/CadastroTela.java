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

        add(new JLabel("Email:"));
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
        String[] generos = {"Aventura", "Romance", "Livro de Receitas"}; //Adcionar gêneros depois 
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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "seu_usuario", "sua_senha"); //Preencher com os dados do seu banco de dados
            String sql = "INSERT INTO usuarios (nome_usuario, email, idade, sexo, genero_favorito, senha) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nomeUsuarioField.getText());
            preparedStatement.setString(2, emailField.getText());
            preparedStatement.setInt(3, Integer.parseInt(idadeField.getText()));
            preparedStatement.setString(4, (String) sexoComboBox.getSelectedItem());
            preparedStatement.setString(5, (String) generoFavoritoComboBox.getSelectedItem());
            preparedStatement.setString(6, new String(senhaField.getPassword()));
            preparedStatement.executeUpdate();

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