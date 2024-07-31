package testUtil;

import DAO.UserDao;
import entities.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
public class UserTest {
    private static User user;
    private static UserDao userDb;

    public static void main(String[] args) throws SQLException {
        Scanner UserData = new Scanner(System.in);
        String username, password;

        System.out.println("Insert username:");
        username = UserData.nextLine();

        System.out.println("Insert password:");
        password = UserData.nextLine();

        userDb = new UserDao();
        user = new User(username, password);

        userDb.insertUser(user);

        System.out.println("Get all users:");

        List<User> allUsers = userDb.getAllUsers();
        for (User user : allUsers) {
            System.out.println(user);
        }
    }
}

