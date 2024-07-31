package JunitTesting;

import entities.Book;
import DAO.BookDao;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDaoJunitTest {

    private static BookDao bookDao;

    @BeforeAll
    public static void setup() {
        bookDao = new BookDao();
    }

    @Test
    @Order(1)
    public void testInsertBook() {
        Book book = new Book("Test Title", "Test Author");
        assertDoesNotThrow(() -> bookDao.insertBook(book));
    }

    @Test
    @Order(2)
    public void testGetAllBooks() {
        List<Book> books = assertDoesNotThrow(() -> bookDao.getAllBooks());
        assertFalse(books.isEmpty());
    }

    @Test
    @Order(3)
    public void testGetBookById() {
        List<Book> books = assertDoesNotThrow(() -> bookDao.getAllBooks());
        Book firstBook = books.get(0);
        Book retrievedBook = assertDoesNotThrow(() -> bookDao.getBookById(firstBook.getId()));
        assertEquals(firstBook.getTitle(), retrievedBook.getTitle());
        assertEquals(firstBook.getAuthor(), retrievedBook.getAuthor());
    }

    @Test
    @Order(4)
    public void testUpdateBook() {
        List<Book> books = assertDoesNotThrow(() -> bookDao.getAllBooks());
        Book firstBook = books.get(0);
        firstBook.setTitle("Updated Title");
        firstBook.setAuthor("Updated Author");
        assertTrue(assertDoesNotThrow(() -> bookDao.updateBook(firstBook)));
        Book updatedBook = assertDoesNotThrow(() -> bookDao.getBookById(firstBook.getId()));
        assertEquals("Updated Title", updatedBook.getTitle());
        assertEquals("Updated Author", updatedBook.getAuthor());
    }

    @Test
    @Order(5)
    public void testDeleteBook() {
        List<Book> books = assertDoesNotThrow(() -> bookDao.getAllBooks());
        Book firstBook = books.get(0);
        assertTrue(assertDoesNotThrow(() -> bookDao.deleteBook(firstBook.getId())));
        Book deletedBook = assertDoesNotThrow(() -> bookDao.getBookById(firstBook.getId()));
        assertNull(deletedBook);
    }
}