package backEnd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import frontEnd.telas.BookDetailScreen;

public class BookHandler {
        public static void updateOrInsertBook(String bookTitle, double score) {
        try (Connection connection = DatabaseConnector.connect()) {
            // Check if the book exists
            String checkQuery = "SELECT * FROM books WHERE title = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, bookTitle);
                ResultSet resultSet = checkStatement.executeQuery();

                if (resultSet.next()) {
                    // Book exists, perform update
                    int qtdReviews = resultSet.getInt("qtd_reviews");
                    double currentScore = resultSet.getDouble("review_score");

                    double newScore = ((currentScore * qtdReviews) + score) / (qtdReviews + 1);

                    String updateQuery = "UPDATE books SET review_score = ?, qtd_reviews = ? WHERE title = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                        updateStatement.setDouble(1, newScore);
                        updateStatement.setInt(2, qtdReviews + 1);
                        updateStatement.setString(3, bookTitle);
                        updateStatement.executeUpdate();
                        System.out.println("Book updated successfully.");
                    }
                } else {
                    // Book doesn't exist, insert new book
                    String insertQuery = "INSERT INTO books (title, qtd_reviews, review_score, public) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                        insertStatement.setString(1, bookTitle);
                        insertStatement.setInt(2, 1);
                        insertStatement.setDouble(3, score);
                        insertStatement.setBoolean(4, false);
                        insertStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void publishBook(String bookName) {
        try (Connection connection = DatabaseConnector.connect()) {
            // Update the book to set it as public
            String updateQuery = "UPDATE books SET public = true WHERE title = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setString(1, bookName);
                updateStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Object listBooks(User user){
        ArrayList<Object[]> bookList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.connect() ) {
            // Assuming you have a 'books' table with columns: id, title, cover_path, details, and public
            String query = "SELECT id, title, cover FROM books WHERE public = true";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int bookId = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    InputStream cover = resultSet.getBinaryStream("cover");

                    // Create a JButton for book details
                    JButton detailsButton = createDetailsButton(bookId,user);

                    // Create an array for a single book entry
                    Object[] bookEntry = {new ImageIcon(CoverHandler.saveImageAsTempFile(cover)), title, detailsButton};

                    // Add the book entry to the list
                    bookList.add(bookEntry);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert the ArrayList to a 2D array
        return bookList.toArray(new Object[0][0]);
    }

    private static JButton createDetailsButton(int bookId,User user) {
        JButton detailsButton = new JButton("Details");

        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the BookDetailScreen function with the respective book_id
                BookDetailScreen.main(null, bookId, user);
            }
        });

        return detailsButton;
    }

    public static void main(String[] args) {
        // Example usage:
        updateOrInsertBook("The Catcher in the Rye", 4.5);
    }
}

