import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroLivros extends JFrame {
    private JTextField nomeLivroField;
    private JComboBox<String> generoComboBox;
    private JTextArea resenhaTextArea;

    public CadastroLivros() {
        super("Cadastro de Livros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel lblNomeLivro = new JLabel("Nome do Livro:");
        nomeLivroField = new JTextField(20);

        JLabel lblGenero = new JLabel("Gênero:");
        String[] generos = {"Mystery", "Fantasy", "Thriller", "Romance", "Non-Fiction", "Biography", "Historical Fiction", "Self-Help", "Young Adult"}; 
        generoComboBox = new JComboBox<>(generos);

        JLabel lblResenha = new JLabel("Resenha:");
        resenhaTextArea = new JTextArea(5, 20);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        add(lblNomeLivro, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        add(nomeLivroField, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(lblGenero, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(generoComboBox, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        add(lblResenha, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(new JScrollPane(resenhaTextArea), gc);

        gc.gridx = 1;
        gc.gridy = 3;
        add(btnCadastrar, gc);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cadastrarLivro() {

        try { ConexaoMysql db1 = new ConexaoMysql();
            String sql = "INSERT INTO books (book_name, genres, resenha) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nomeLivroField.getText());
                preparedStatement.setString(2, (String) generoComboBox.getSelectedItem());
                preparedStatement.setString(3, resenhaTextArea.getText());
                preparedStatement.executeUpdate();
                db1.OpenDatabase();
                db1.Executaquery(sql);
                db1.CloseDatabase();     
                          
                JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar livro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroLivros());
    }
}