
package controller;

import model.*;
import dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dao.QuizDAO;
import dto.QuestionDto;
import dto.QuestionIdsDTO;
import dto.QuizDTO;
import dto.QuizWithQuestionsDTO;
import dao.QuestionDAO;
import model.Quiz;
import model.Question;

import javax.ws.rs.*;

import javax.servlet.http.HttpSession;
import dto.QuestionDto;
import javax.servlet.http.HttpServletRequest;

@Path("/users/{userId}/quizzes/{quizId}/edit")
public class EditQuizController {

    @POST
    @Path("/remove")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeQuestionForQuiz(@PathParam("userId") Long userId, @PathParam("quizId") Long quizId,
            Map<String, List<Long>> payload,
            @Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"User is not logged in.\"}")
                    .build();
        }
        QuizDAO quizDAO = new QuizDAO();

        List<Quiz> quizzes = quizDAO.getAllQuizzesForUser(userId);
        Quiz currentQuiz = null;

        for (Quiz quiz : quizzes) {
            if (quiz.getId() == quizId) {
                currentQuiz = quiz;
                break;
            }
        }

        if (currentQuiz == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Quiz not found.\"}")
                    .build();
        }

        List<Long> questionsToRemove = payload.get("questionsToRemove");

        QuestionDAO questionDAO = new QuestionDAO();

        for (Long questionToRemove : questionsToRemove) {
            Iterator<Question> quizIterator = currentQuiz.getQuestions().iterator();
            Question questionn = questionDAO.getQuestionWithQuizzesById(questionToRemove);
            Iterator<Quiz> questionIterator = questionn.getQuizzes().iterator();

            while (quizIterator.hasNext()) {
                Question q = quizIterator.next();
                if (q.getId().equals(questionToRemove)) {
                    quizIterator.remove();
                }
            }

            while (questionIterator.hasNext()) {
                Quiz qz = questionIterator.next();
                if (qz.getId().equals(quizId)) {
                    questionIterator.remove();
                }
            }
            questionDAO.updateQuestion(questionn);
        }
        quizDAO.updateQuiz(currentQuiz);
        return Response.ok("Questions removed").build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuizById(@PathParam("userId") Long userId, @PathParam("quizId") Long quizId,
            @Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"User is not logged in.\"}")
                    .build();
        }

        QuizDAO quizDAO = new QuizDAO();
        QuestionDAO questionDAO = new QuestionDAO();

        List<Quiz> quizzes = quizDAO.getAllQuizzesForUser(userId);
        Quiz currentQuiz = null;

        for (Quiz quiz : quizzes) {
            if (quiz.getId() == quizId) {
                currentQuiz = quiz;
                break;
            }
        }

        if (currentQuiz == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Quiz not found.\"}")
                    .build();
        }

        // List<Question> questions = questionDAO.getAllQuestionsForQuiz(quizId);
        // List<Long> questionIds = questions.stream()
        // .map(Question::getId)
        // .collect(Collectors.toList());

        QuizWithQuestionsDTO quizDTO = new QuizWithQuestionsDTO(currentQuiz);
        // quizDTO.setQuestionIds(questionIds); // Attach the question list

        return Response.ok(quizDTO).build();
    }
}
