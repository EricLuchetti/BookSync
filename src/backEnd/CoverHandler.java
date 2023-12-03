package backEnd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import javax.swing.ImageIcon;

public class CoverHandler {

    public static Object[][] getNonPublicBooks() throws IOException {
        Object[][] data = null;
        String[] columns = { "Título", "Autor", "Editora", "Capa Adicionada", "Imagem" };
        String queryCount = "SELECT Count(*) FROM books WHERE public = false";
        String query = "SELECT title, author, publisher, public, cover FROM books WHERE public = false";
        int rowCount = 0;

        try (Connection connection = DatabaseConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryCount)) {
            while (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DatabaseConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            // Counting the number of rows

            data = new Object[rowCount][columns.length];
            int row = 0;

            while (resultSet.next()) {
                data[row][0] = resultSet.getString("title");
                data[row][1] = resultSet.getString("author");
                data[row][2] = resultSet.getString("publisher");

                InputStream inputStream = resultSet.getBinaryStream("cover");
                String tempFilePath = saveImageAsTempFile(inputStream);

                data[row][4] = new ImageIcon(tempFilePath);
                if (data[row][4].toString().length() > 0){
                    data[row][3] = true;
                }else{
                    data[row][3] = false;
                }
                row++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void updateBookCover(String filePath, String bookName) throws IOException {

        if (backEnd.Services.isValidJpeg(filePath)) {

            try (Connection connection = DatabaseConnector.connect()) {
                // Find the book ID
                int bookId = -1;
                String query = "SELECT id FROM books WHERE title = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, bookName);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        bookId = resultSet.getInt("id");
                    }
                }

                if (bookId != -1) {
                    // Update cover and set book as public
                    String updateQuery = "UPDATE books SET cover = ? WHERE id = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                        File file = new File(filePath);
                        try (InputStream inputStream = new FileInputStream(file)) {
                            updateStatement.setBlob(1, inputStream);
                            updateStatement.setInt(2, bookId);
                            updateStatement.executeUpdate();
                            System.out.println("Book cover updated successfully.");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("Book not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String saveImageAsTempFile(InputStream inputStream) {
        if (inputStream == null){
            return "";
        }
        String tempFilePath = null;
        try {
            File tempFile = File.createTempFile("tempImage", ".jpeg");
            try (FileOutputStream fileOutputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
                System.out.println("JPEG file retrieved from the database and stored temporarily.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempFile.deleteOnExit();
            tempFilePath = tempFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFilePath;
    }


    public static void main(String[] args) throws IOException {
        // Example usage
    }
}
