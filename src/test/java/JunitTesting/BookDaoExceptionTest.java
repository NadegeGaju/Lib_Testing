package JunitTesting;

import DAO.BookDao;
import entities.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class BookDaoExceptionTest {

    private static BookDao bookDao;

    @BeforeAll
    public static void setup() {
        bookDao = new BookDao();
    }

    @Test
    public void testInsertBookWithNullTitle() {
        Book book = new Book(null, "author");
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> bookDao.insertBook(book));
        assertTrue(thrown.getCause() instanceof SQLException);
    }

    @Test
    public void testInsertBookWithNullAuthor() {
        Book book = new Book("title", null);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> bookDao.insertBook(book));
        assertTrue(thrown.getCause() instanceof SQLException);
    }

    @Test
    public void testInsertBookWithNullTitleAndAuthor() {
        Book book = new Book(null, null);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> bookDao.insertBook(book));
        assertTrue(thrown.getCause() instanceof SQLException);
    }
}
