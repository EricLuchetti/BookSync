package backEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LivroAdd {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            criarGUI();
        });
    }

    private static void criarGUI() {
        JFrame frame = new JFrame("Cadastro de Livro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel lblCapa = new JLabel("Capa do Livro:");
        JButton btnSelecionarImagem = new JButton("Selecionar Imagem");
        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField();
        JLabel lblAutor = new JLabel("Autor:");
        JTextField txtAutor = new JTextField();
        JLabel lblResenha = new JLabel("Resenha:");
        JTextArea txtResenha = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtResenha);
        JButton btnSalvar = new JButton("Salvar");

        panel.add(lblCapa);
        panel.add(btnSelecionarImagem);
        panel.add(lblTitulo);
        panel.add(txtTitulo);
        panel.add(lblAutor);
        panel.add(txtAutor);
        panel.add(lblResenha);
        panel.add(scrollPane);
        panel.add(btnSalvar);

        btnSelecionarImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "jpg", "png", "jpeg", "gif");
                fileChooser.setFileFilter(filter);

                int resultado = fileChooser.showOpenDialog(null);

                if (resultado == JFileChooser.APPROVE_OPTION) {
                    // Aqui você pode obter o caminho do arquivo selecionado
                    String caminhoDaImagem = fileChooser.getSelectedFile().getAbsolutePath();
                    // Você pode fazer o que quiser com o caminho da imagem, por exemplo, exibi-la em algum lugar
                    // ou salvá-la em uma variável para uso posterior.
                    System.out.println("Caminho da Imagem: " + caminhoDaImagem);
                }
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você pode salvar as informações do livro, por exemplo, em um banco de dados ou arquivo.
                String titulo = txtTitulo.getText();
                String autor = txtAutor.getText();
                String resenha = txtResenha.getText();

                // Exemplo de exibição das informações
                JOptionPane.showMessageDialog(frame, "Livro Salvo:\nTítulo: " + titulo + "\nAutor: " + autor + "\nResenha: " + resenha);
            }
        });

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);
    }
}