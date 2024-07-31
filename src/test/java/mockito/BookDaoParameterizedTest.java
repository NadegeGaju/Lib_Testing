package mockito;

import DAO.BookDao;
import entities.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookDaoParameterizedTest {

    @Mock
    private BookDao bookDao;

    @BeforeEach




    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource({
            "hoel, nadege",
            "nawe, nuko",
            "charles, due"
    })
    public void testInsertBook(String title, String author) {
        Book book = new Book(title, author);
        doNothing().when(bookDao).insertBook(book);
        assertDoesNotThrow(() -> bookDao.insertBook(book));
        verify(bookDao, times(1)).insertBook(book);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> mockBooks = Arrays.asList(new Book("how to be a hero", "dellamis"), new Book("how to crack", "withrin"));
        when(bookDao.getAllBooks()).thenReturn(mockBooks);

        List<Book> books = assertDoesNotThrow(() -> bookDao.getAllBooks());
        assertFalse(books.isEmpty());
        assertEquals(2, books.size());
        verify(bookDao, times(1)).getAllBooks();
    }
}
