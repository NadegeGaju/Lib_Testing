package JunitTesting;

import DAO.BookDao;
import entities.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookDaoParameterizedTest {

    private static BookDao bookDao;

    @BeforeAll
    public static void setup() {
        bookDao = new BookDao();
    }

    @ParameterizedTest
    @CsvSource({
            "Romeo and Juliet, sharkspear",
            "inkotanyi, Paul kagame",
            "hard work, cristiano Ronaldo"
    })
    public void testInsertBook(String title, String author) {
        Book book = new Book(title, author);
        assertDoesNotThrow(() -> bookDao.insertBook(book));
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = assertDoesNotThrow(() -> bookDao.getAllBooks());
        assertFalse(books.isEmpty());
    }
}
