package frontEnd.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LivroGUI {
    private JFrame frame;
    private JPanel panel;
    private JTable table;

    public LivroGUI() {
        frame = new JFrame("Livros");
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Adicionando mais livros
        adicionarLivro("caminho1.jpg", "Livro 1", "Detalhes do Livro 1");
        adicionarLivro("caminho2.jpg", "Livro 2", "Detalhes do Livro 2");
        adicionarLivro("caminho3.jpg", "Livro 3", "Detalhes do Livro 3");
        adicionarLivro("caminho4.jpg", "Livro 4", "Detalhes do Livro 4");
        adicionarLivro("caminho5.jpg", "Livro 5", "Detalhes do Livro 5");
        adicionarLivro("caminho6.jpg", "Livro 6", "Detalhes do Livro 6");
        adicionarLivro("caminho7.jpg", "Livro 7", "Detalhes do Livro 7");
        adicionarLivro("caminho8.jpg", "Livro 8", "Detalhes do Livro 8");
        adicionarLivro("caminho9.jpg", "Livro 9", "Detalhes do Livro 9");
        adicionarLivro("caminho10.jpg", "Livro 10", "Detalhes do Livro 10");
        adicionarLivro("caminho11.jpg", "Livro 11", "Detalhes do Livro 11");
        adicionarLivro("caminho12.jpg", "Livro 12", "Detalhes do Livro 12");
        adicionarLivro("caminho13.jpg", "Livro 13", "Detalhes do Livro 13");
        adicionarLivro("caminho14.jpg", "Livro 14", "Detalhes do Livro 14");
        adicionarLivro("caminho15.jpg", "Livro 15", "Detalhes do Livro 15");

        // Criando a tabela
        String[] colunas = {"Imagem", "Nome do Livro", "Ver mais"};
        Object[][] dados = {
                {new ImageIcon("caminho1.jpg"), "Livro 1", "Detalhes do Livro 1"},
                {new ImageIcon("caminho2.jpg"), "Livro 2", "Detalhes do Livro 2"},
                {new ImageIcon("caminho3.jpg"), "Livro 3", "Detalhes do Livro 3"},
                {new ImageIcon("caminho4.jpg"), "Livro 4", "Detalhes do Livro 4"},
                {new ImageIcon("caminho5.jpg"), "Livro 5", "Detalhes do Livro 5"},
                {new ImageIcon("caminho6.jpg"), "Livro 6", "Detalhes do Livro 6"},
                {new ImageIcon("caminho7.jpg"), "Livro 7", "Detalhes do Livro 7"},
                {new ImageIcon("caminho8.jpg"), "Livro 8", "Detalhes do Livro 8"},
                {new ImageIcon("caminho9.jpg"), "Livro 9", "Detalhes do Livro 9"},
                {new ImageIcon("caminho10.jpg"), "Livro 10", "Detalhes do Livro 10"},
                {new ImageIcon("caminho11.jpg"), "Livro 11", "Detalhes do Livro 11"},
                {new ImageIcon("caminho12.jpg"), "Livro 12", "Detalhes do Livro 12"},
                {new ImageIcon("caminho13.jpg"), "Livro 13", "Detalhes do Livro 13"},
                {new ImageIcon("caminho14.jpg"), "Livro 14", "Detalhes do Livro 14"},
                {new ImageIcon("caminho15.jpg"), "Livro 15", "Detalhes do Livro 15"}
        };
        table = new JTable(dados, colunas);

        // Adicionando um ouvinte de ação à tabela
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String detalhes = (String) table.getValueAt(selectedRow, 2);
                exibirDetalhesLivro(detalhes);
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = createStyledButton("Voltar", 150, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuUsuario.main(null);
                frame.dispose();
            }
        });

        panel.add(backButton, BorderLayout.NORTH);

        frame.add(panel);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void adicionarLivro(String caminhoCapa, String nomeLivro, String detalhes) {
        JPanel livroPanel = new JPanel();
        JLabel capa = new JLabel(new ImageIcon(caminhoCapa));
        JLabel label = new JLabel(nomeLivro);
        JButton button = new JButton("Ver mais");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exibirDetalhesLivro(detalhes);
            }
        });

        livroPanel.add(capa);
        livroPanel.add(label);
        livroPanel.add(button);

        panel.add(livroPanel);
    }

    private void exibirDetalhesLivro(String detalhes) {
        JOptionPane.showMessageDialog(frame, detalhes, "Detalhes do Livro", JOptionPane.INFORMATION_MESSAGE);
    }

    private JButton createStyledButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(new Color(195, 145, 128));
        button.setForeground(Color.WHITE);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LivroGUI());
    }
}



