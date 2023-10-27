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
                    // Aqui você pode redirecionar para a tela de sucesso do admin
                } else {
                    // Usuário não é admin, exibe mensagem de erro
                    JOptionPane.showMessageDialog(null, "Erro ao fazer login!");
                }
            }
        };
    }
}