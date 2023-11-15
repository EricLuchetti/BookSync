package frontEnd.ProjetoInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    public static ActionListener createLoginActionListener(JTextField tfLogin, JTextField tfSenha) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfLogin.getText();
                String password = tfSenha.getText();

                // Verificação do usuário (suponhamos que o usuário admin seja "admin" e a senha "admin123")
                if (username.equals("admin") && password.equals("admin123")) {
                    // Usuário é admin, redirecione para a tela de sucesso
                    JOptionPane.showMessageDialog(null, "Login com sucesso (Admin)!");
                    // Aqui você pode abrir a tela de "Lista de livros"
                    openListaDeLivrosPage();
                } else {
                    // Usuário não é admin, exibe mensagem de erro
                    JOptionPane.showMessageDialog(null, "Erro ao fazer login!");
                }
            }

            // Método para abrir a página "Lista de livros"
            private void openListaDeLivrosPage() {
                JFrame listaDeLivrosFrame = new JFrame("Lista de Livros");
                // Configurar a janela "Lista de livros" aqui
                listaDeLivrosFrame.setSize(800, 600);
                listaDeLivrosFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // Adicione os componentes da lista de livros aqui

                // Exiba a janela
                listaDeLivrosFrame.setVisible(true);
            }
        };
    }
}