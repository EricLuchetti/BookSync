package backEnd;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Services {

    //Dentro dessa classe estarão inclusas todas as funções genericas que são utilizadas em multiplas partes do programa

    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Error encrypting password.");
        }
    }

    public static void removeUser(String login) {
        try (Connection connection = DatabaseConnector.connect()) {
            // Remove entries from the users_genres table
            String removeUserGenresQuery = "DELETE FROM users_genres WHERE user_id = (SELECT id FROM users WHERE login = ?)";
            try (PreparedStatement removeUserGenresStatement = connection.prepareStatement(removeUserGenresQuery)) {
                removeUserGenresStatement.setString(1, login);
                removeUserGenresStatement.executeUpdate();
            }

            // Remove entries from the users table
            String removeUserQuery = "DELETE FROM users WHERE login = ?";
            try (PreparedStatement removeUserStatement = connection.prepareStatement(removeUserQuery)) {
                removeUserStatement.setString(1, login);
                removeUserStatement.executeUpdate();
                System.out.println("User removed successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeBook(String bookName) {
        try (Connection connection = DatabaseConnector.connect()) {
            // Remove entries from the books_genres table
            String removeBookGenresQuery = "DELETE FROM books_genres WHERE book_id = (SELECT id FROM books WHERE title = ?)";
            try (PreparedStatement removeBookGenresStatement = connection.prepareStatement(removeBookGenresQuery)) {
                removeBookGenresStatement.setString(1, bookName);
                removeBookGenresStatement.executeUpdate();
            }

            // Remove entries from the books table
            String removeBookQuery = "DELETE FROM books WHERE title = ?";
            try (PreparedStatement removeBookStatement = connection.prepareStatement(removeBookQuery)) {
                removeBookStatement.setString(1, bookName);
                removeBookStatement.executeUpdate();
                System.out.println("Book removed successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeGenre(int genreId) {
        try (Connection connection = DatabaseConnector.connect()) {
            // Remove entries from the users_genres table
            String removeGenreUsersQuery = "DELETE FROM users_genres WHERE genre_id = ?";
            try (PreparedStatement removeGenreUsersStatement = connection.prepareStatement(removeGenreUsersQuery)) {
                removeGenreUsersStatement.setInt(1, genreId);
                removeGenreUsersStatement.executeUpdate();
            }

            // Remove entries from the books_genres table
            String removeGenreBooksQuery = "DELETE FROM books_genres WHERE genre_id = ?";
            try (PreparedStatement removeGenreBooksStatement = connection.prepareStatement(removeGenreBooksQuery)) {
                removeGenreBooksStatement.setInt(1, genreId);
                removeGenreBooksStatement.executeUpdate();
            }

            // Remove entry from the genres table
            String removeGenreQuery = "DELETE FROM genres WHERE id = ?";
            try (PreparedStatement removeGenreStatement = connection.prepareStatement(removeGenreQuery)) {
                removeGenreStatement.setInt(1, genreId);
                removeGenreStatement.executeUpdate();
                System.out.println("Genre removed successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidJpeg(String filePath) {
        // Check if the file exists
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("File does not exist or is not a file.");
            return false;
        }

        // Check if the file is a JPEG
        String fileName = file.getName();
        if (!fileName.toLowerCase().endsWith(".jpg") && !fileName.toLowerCase().endsWith(".jpeg")) {
            System.out.println("File is not a JPEG.");
            return false;
        }

        // Check if the file size is within the limits of MEDIUMBLOB
        long fileSize = file.length(); // Size in bytes
        long maxSize = 16777215; // Maximum size for MEDIUMBLOB in bytes

        if (fileSize > maxSize) {
            System.out.println("File size exceeds the maximum size for MEDIUMBLOB.");
            return false;
        }

        return true;
    }

}
