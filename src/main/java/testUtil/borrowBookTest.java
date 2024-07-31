package testUtil;

import DAO.BookDao;
import DAO.UserDao;
import entities.BorrowedBook;

import java.util.List;

public class borrowBookTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        BookDao bookDao = new BookDao();


        int userId = 9;
        int bookId = 1;


        userDao.borrowBook(userId, bookId);
        System.out.println("Book borrowed successfully!");


        List<BorrowedBook> borrowedBooks = userDao.getBorrowedBooks(userId);
        for (BorrowedBook borrowedBook : borrowedBooks) {
            System.out.println(borrowedBook);
        }
    }
    }
