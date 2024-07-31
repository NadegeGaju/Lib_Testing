package JunitTesting;

import entities.User;
import DAO.UserDao;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDaoJunitTest {

    private static UserDao userDao;

    @BeforeAll
    public static void setup() {
        userDao = new UserDao();
    }

    @Test
    @Order(1)
    public void testInsertUser() {
        User user = new User("TestUser", "TestPassword");
        assertDoesNotThrow(() -> userDao.insertUser(user));
    }

    @Test
    @Order(2)
    public void testGetAllUsers() {
        List<User> users = assertDoesNotThrow(() -> userDao.getAllUsers());
        assertFalse(users.isEmpty());
    }

    @Test
    @Order(3)
    public void testGetUserById() {
        List<User> users = assertDoesNotThrow(() -> userDao.getAllUsers());
        User firstUser = users.get(0);
        User retrievedUser = assertDoesNotThrow(() -> userDao.getUserById(firstUser.getId()));
        assertEquals(firstUser.getUsername(), retrievedUser.getUsername());
        assertEquals(firstUser.getPassword(), retrievedUser.getPassword());
    }

    @Test
    @Order(4)
    public void testUpdateUser() {
        List<User> users = assertDoesNotThrow(() -> userDao.getAllUsers());
        User firstUser = users.get(0);
        firstUser.setUsername("UpdatedUsername");
        firstUser.setPassword("UpdatedPassword");
        assertTrue(assertDoesNotThrow(() -> userDao.updateUser(firstUser)));
        User updatedUser = assertDoesNotThrow(() -> userDao.getUserById(firstUser.getId()));
        assertEquals("UpdatedUsername", updatedUser.getUsername());
        assertEquals("UpdatedPassword", updatedUser.getPassword());
    }

    @Test
    @Order(5)
    public void testDeleteUser() {
        List<User> users = assertDoesNotThrow(() -> userDao.getAllUsers());
        User firstUser = users.get(0);
        assertTrue(assertDoesNotThrow(() -> userDao.deleteUser(firstUser.getId())));
        User deletedUser = assertDoesNotThrow(() -> userDao.getUserById(firstUser.getId()));
        assertNull(deletedUser);
    }
}
