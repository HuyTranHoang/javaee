import com.ebook.config.HibernateSessionFactoryConfig;
import com.ebook.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestHibernate {
    public static void main(String[] args) {
        Transaction transaction = null;

        try (Session session = HibernateSessionFactoryConfig.getSessionFactory().openSession()) {
            System.out.println("Connected to the database");

            transaction = session.beginTransaction();
            User user = new User();
            user.setEmail("test@gmail.com");
            user.setFullName("Test User");
            user.setPassword("test123");

            session.save(user);
            transaction.commit();

            System.out.println("User saved with Id: " + user.getUserId());

//            User getUser = session.get(User.class, user.getUserId());
//            System.out.println("User get: " + getUser.getFullName());
//            System.out.println("User password: " + getUser.getPassword());

        } catch (Exception e) {
            System.out.println("Oops, there's an error");
//            if (transaction != null)
//                transaction.rollback();

            e.printStackTrace();
        }
    }
}
