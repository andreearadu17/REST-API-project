package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Answer;

public class AnswerDAO {
    private EntityManagerFactory entityManagerFactory;

    public AnswerDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("onlinetestsPU");
    }

    public void insertAnswer(Answer answer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(answer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Answer> getAnswersByQuizId(Long quizId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Answer> answers = entityManager
                .createQuery("SELECT a FROM Answer a WHERE a.quiz.id = :quizId", Answer.class)
                .setParameter("quizId", quizId)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return answers;

    }

    public void deleteAnswer(Answer answer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(answer) ? answer : entityManager.merge(answer));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
