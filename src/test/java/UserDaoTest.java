import com.ebook.dao.UserDAO;
import com.ebook.entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    private static UserDAO userDao;

    @BeforeAll // Chạy trước khi chạy các test case
    static void setUp() {
        userDao = new UserDAO();
    }

    @Test
    void test() {
        User newUser = new User();
        newUser.setEmail("test@gmail.com");
        newUser.setFullName("Test User");
        newUser.setPassword("test123");

        User user = userDao.create(newUser);

        assertTrue(user.getUser_id() > 0);
    }

    @Test
    void testFind() {
        User user = userDao.find(1);
        assertEquals(1, (int) user.getUser_id());
    }

    @Test
    void testUpdate() {
        User user = userDao.find(1);
        user.setFullName("Test User Updated");
        User updatedUser = userDao.update(user);
        assertEquals("Test User Updated", updatedUser.getFullName());
    }

    @Test
    void testDelete() {
        userDao.delete(1);
        User user = userDao.find(1);
        assertNull(user);
    }

    @Test
    void testFindAll() {
        assertEquals(5, userDao.findAll().size());
        assertEquals(5, userDao.findAllWithHQL().size());
    }

    @Test
    void testCount() {
        assertEquals(5, userDao.count());
    }

    @AfterAll // Chạy sau khi chạy các test case
    static void tearDown() {
        userDao = null;
    }
}
