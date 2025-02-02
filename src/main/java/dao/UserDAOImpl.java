/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.QuizCreator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAOImpl implements UserDAO {
    private EntityManagerFactory entityManagerFactory;

    public UserDAOImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("onlinetestsPU");
    }

    @Override
    public void createUser(QuizCreator user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void insertUser(QuizCreator newUser) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // Hash the password before saving
            String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashedPassword);

            entityManager.persist(newUser);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<QuizCreator> getAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<QuizCreator> users = entityManager.createQuery("SELECT u FROM QuizCreator u", QuizCreator.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return users;
    }

    @Override
    public QuizCreator getUserByUsername(String username) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        QuizCreator user = null;
        try {
            Query query = entityManager.createQuery("FROM QuizCreator WHERE username = :username",
                    QuizCreator.class);
            query.setParameter("username", username);
            user = (QuizCreator) query.getSingleResult();
        } finally {
            entityManager.close();
        }
        return user;
    }
}