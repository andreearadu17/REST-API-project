/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import java.util.List;

public class QuestionDAO {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("onlinetestsPU");

    public void createQuestion(Question question) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(question);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void insertQuestion(Question newQuestion) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(newQuestion);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Question> getAllQuestions() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT q FROM Question q", Question.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Question getQuestionById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Question question = entityManager.find(Question.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return question;
    }

    public List<Question> getAllQuestionsForUser(QuizCreator user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Question> questions = entityManager.createQuery(
                "SELECT q FROM Question q JOIN FETCH q.creator WHERE q.creator = :creator", Question.class)
                .setParameter("creator", user)
                .getResultList();
        entityManager.close();
        return questions;
    }

    public List<Question> getAllQuestionsForQuiz(Long quizId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Question> questions = entityManager.createQuery(
                "SELECT q FROM Question q LEFT JOIN FETCH q.quizzes WHERE q.id IN "
                        + "(SELECT q2.id FROM Question q2 JOIN q2.quizzes qu WHERE qu.id = :quizId)",
                Question.class)
                .setParameter("quizId", quizId)
                .getResultList();

        entityManager.close();
        return questions;
    }

    public Question getQuestionWithQuizzesById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Question question = entityManager
                .createQuery("SELECT q FROM Question q LEFT JOIN FETCH q.quizzes WHERE q.id = :id", Question.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.close();
        return question;
    }

    public Question mergeQuestion(Question question) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Question mergedQuestion = entityManager.merge(question);
        entityManager.getTransaction().commit();
        entityManager.close();
        return mergedQuestion;
    }

    public void updateQuestion(Question question) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(question); // Salvează modificările
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

}