package JunitTesting;

import DAO.UserDao;
import entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDaoExceptionTest {

    private static UserDao userDao;

    @BeforeAll
    public static void setup() {
        userDao = new UserDao();
    }

    @Test
    public void testInsertUserWithNullUsername() {
        User user = new User(null, "password");
        assertThrows(SQLException.class, () -> userDao.insertUser(user));
    }

    @Test
    public void testInsertUserWithNullPassword() {
        User user = new User("username", null);
        assertThrows(SQLException.class, () -> userDao.insertUser(user));
    }

    @Test
    public void testInsertUserWithNullUsernameAndPassword() {
        User user = new User(null, null);
        assertThrows(SQLException.class, () -> userDao.insertUser(user));
    }
}
