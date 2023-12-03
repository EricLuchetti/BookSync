package frontEnd.telas;

import javax.swing.*;

import backEnd.BookHandler;
import backEnd.CoverHandler;
import backEnd.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

public class BookListScreen extends JFrame{

    public BookListScreen(Object[][] books, User user) {
        setTitle("Book List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Create a panel to hold the list of books
        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));

        // Create a button above the list
        JButton myButton = new JButton("Voltar ao menu");
        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.isAdmin()){
                    AdminMenuScreen.main(null, user);
                    dispose();
                }else{
                    MainMenuScreen.main(null, user);
                    dispose();
                }
            }
        });

        // Add the button above the book list
        add(myButton, BorderLayout.NORTH);

        // Create book entries
        
        for (Object[] book : books) {
            //String imagePath = (String) book[0];
            String title = (String) book[1];
            JButton detailsButton = (JButton) book[2];

            ImageIcon imageIcon = (ImageIcon) book[0];
            JLabel imageLabel = new JLabel(imageIcon);
            JLabel titleLabel = new JLabel(title);

            // Create a panel for each book entry
            JPanel bookEntryPanel = new JPanel(new BorderLayout());
            bookEntryPanel.add(imageLabel, BorderLayout.WEST);
            bookEntryPanel.add(titleLabel, BorderLayout.CENTER);
            bookEntryPanel.add(detailsButton, BorderLayout.EAST);

            bookPanel.add(bookEntryPanel);
        }

        JScrollPane scrollPane = new JScrollPane(bookPanel);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args, User user) {
        SwingUtilities.invokeLater(() -> new BookListScreen((Object[][]) BookHandler.listBooks(user), user));
    }
}



