package DAO;

import entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    protected Connection connectToDatabase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/libtesting", "postgres", "12345678");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertBook(Book book) {
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book (title, author) VALUES (?, ?)")) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book getBookById(Long id) {
        Book book = null;
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                String title = result.getString("title");
                String author = result.getString("author");
                book = new Book(id, title, author);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book")) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long id = result.getLong("id");
                String title = result.getString("title");
                String author = result.getString("author");
                books.add(new Book(id, title, author));
            }
            System.out.println("Retrieved all books");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public boolean deleteBook(Long id) {
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM book WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateBook(Book book) {
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET title = ?, author = ? WHERE id = ?")) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setLong(3, book.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

