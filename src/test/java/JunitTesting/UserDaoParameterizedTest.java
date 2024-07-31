package JunitTesting;

import DAO.UserDao;
import entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoParameterizedTest {

    private static UserDao userDao;

    @BeforeAll
    public static void setup() {
        userDao = new UserDao();
    }

    @ParameterizedTest
    @CsvSource({
            "Arsene, Nyirinkwaya1!",
            "Gaju, what",
            "Hirwa, elly"
    })
    public void testInsertUser(String username, String password) {
        User user = new User(username, password);
        assertDoesNotThrow(() -> userDao.insertUser(user));
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        List<User> users = userDao.getAllUsers();
        assertFalse(users.isEmpty());
    }
}
