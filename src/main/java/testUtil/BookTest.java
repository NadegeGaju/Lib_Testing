package testUtil;

import DAO.BookDao;
import DAO.UserDao;
import entities.Book;
import entities.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookTest {
    private static Book book;
    private static BookDao bookDao;

    private static Scanner bookData = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {

        String title, author;

        System.out.println("Insert Book title:");
        title = bookData.nextLine();

        System.out.println("Insert author:");
        author = bookData.nextLine();

        bookDao = new BookDao();
        book = new Book(title, author);

        bookDao.insertBook(book);

        System.out.println("Get all users:");

        List<Book> books = bookDao.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
