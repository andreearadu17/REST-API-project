/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

public class QuizDAO {
    private EntityManagerFactory entityManagerFactory;

    public QuizDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("onlinetestsPU");
    }

    public Quiz getQuizByNameAndCreator(String quizName, QuizCreator creator) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // HQL query to get a quiz by name and creator
            return (Quiz) entityManager
                    .createQuery("SELECT q FROM Quiz q WHERE q.quizName = :quizName AND q.quizCreator = :creator")
                    .setParameter("quizName", quizName)
                    .setParameter("creator", creator)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // If no quiz is found, return null
        }
    }

    public void createQuiz(Quiz quiz) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(quiz);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void insertQuiz(Quiz newQuiz) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(newQuiz);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Transactional
    public Quiz getQuizWithQuestionsById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Quiz quiz = entityManager
                .createQuery("SELECT q FROM Quiz q LEFT JOIN FETCH q.questions WHERE q.id = :id", Quiz.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.close();
        return quiz;
    }

    @Transactional
    public List<Quiz> getAllQuizzes() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT q FROM Quiz q");
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Transactional
    public List<Quiz> getAllQuizzesForUser(Long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager
                .createQuery(
                        "SELECT q FROM Quiz q JOIN FETCH q.quizCreator WHERE q.quizCreator.id = :userId",
                        Quiz.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Transactional
    public Quiz getQuizById(Long quizId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Quiz quiz = entityManager.createQuery(
                "SELECT q FROM Quiz q JOIN FETCH q.quizCreator WHERE q.id = :quizId", Quiz.class)
                .setParameter("quizId", quizId)
                .getSingleResult();

        entityManager.close();
        return quiz;
    }

    public void deleteQuiz(Quiz quiz) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(quiz) ? quiz : entityManager.merge(quiz));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateQuiz(Quiz quiz) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(quiz); // Salvează modificările
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

}