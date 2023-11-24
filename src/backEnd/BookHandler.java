package backEnd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static void main(String[] args) {
        // Example usage:
        updateOrInsertBook("The Catcher in the Rye", 4.5);
    }
}

