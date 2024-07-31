package DAO;


import entities.BorrowedBook;
import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private Connection connectToDatabase(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/libtesting", "postgres", "12345678");
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) throws SQLException {
        try (Connection connection = connectToDatabase();
             PreparedStatement pstm = connection.prepareStatement("INSERT INTO \"user\" (username, password) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());
            pstm.executeUpdate();

            // Retrieve the generated ID and set it to the user object
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public User getUserById(int id) {
        User user = null;
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"user\" WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                String username = result.getString("username");
                String password = result.getString("password");
                user = new User(id, username, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"user\"")) {
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                int id = result.getInt("id");
                String username = result.getString("username");
                String password = result.getString("password");
                users.add(new User(id, username, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public boolean deleteUser(int id) {
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"user\" WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateUser(User user) {
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE \"user\" SET username = ?, password = ? WHERE id = ?")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to borrow a book
    public void borrowBook(int userId, int bookId) {
        try (Connection connection = connectToDatabase();
             PreparedStatement pstm = connection.prepareStatement("INSERT INTO borrowed_books (user_id, book_id, borrowed_date) VALUES (?, ?, ?)")) {
            pstm.setInt(1, userId);
            pstm.setLong(2, bookId);
            pstm.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Method to get all borrowed books for a user
    public List<BorrowedBook> getBorrowedBooks(int userId) {
        List<BorrowedBook> borrowedBooks = new ArrayList<>();
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM borrowed_books WHERE user_id = ?")) {
            preparedStatement.setInt(1, userId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Long bookId = result.getLong("book_id");
                Date borrowedDate = result.getDate("borrowed_date");
                borrowedBooks.add(new BorrowedBook(userId, bookId, borrowedDate.toLocalDate()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return borrowedBooks;
    }
}

