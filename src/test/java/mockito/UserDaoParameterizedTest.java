package mockito;

import DAO.UserDao;
import entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDaoParameterizedTest {

    @Mock
    private UserDao userDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource({
            "della, mis",
            "gaju, nadege",
            "neymar, santos"
    })
    public void testInsertUser(String username, String password) throws SQLException {
        User user = new User(username, password);
        doNothing().when(userDao).insertUser(user);
        assertDoesNotThrow(() -> userDao.insertUser(user));
        verify(userDao, times(1)).insertUser(user);
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        List<User> mockUsers = Arrays.asList(new User("Gaju", "Nadege"), new User("Pink", "Rose"));
        when(userDao.getAllUsers()).thenReturn(mockUsers);

        List<User> users = userDao.getAllUsers();
        assertFalse(users.isEmpty());
        assertEquals(2, users.size());
        verify(userDao, times(1)).getAllUsers();
    }
}
