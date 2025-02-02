
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
import java.util.stream.Collectors;

import dao.QuizDAO;
import dto.QuestionDto;
import dao.QuestionDAO;
import model.Quiz;
import model.Question;

import javax.ws.rs.*;

import javax.servlet.http.HttpSession;
import dto.QuestionDto;
import javax.servlet.http.HttpServletRequest;

@Path("/users/{userId}/quizzes/{quizId}/edit")
public class EditQuizController {

    // protected void doPost(HttpServletRequest request, HttpServletResponse
    // response)
    // throws ServletException, IOException {
    // String quizIdParam = request.getParameter("quizId");
    // String questionsToRemoveParam = request.getParameter("questionsToRemove");

    // if (quizIdParam != null) {
    // try {
    // Long quizId = Long.parseLong(quizIdParam);
    // QuizDAO quizDAO = new QuizDAO();
    // Quiz quiz = quizDAO.getQuizWithQuestionsById(quizId);
    // if (quiz != null) {
    // QuestionDAO questionDAO = new QuestionDAO();
    // // Remove questions that are in the questionsToRemoveParam
    // if (questionsToRemoveParam != null && !questionsToRemoveParam.isEmpty()) {
    // String[] questionsToRemove = questionsToRemoveParam.split(",");
    // for (String questionIdStr : questionsToRemove) {
    // Long questionId = Long.parseLong(questionIdStr);
    // Question question = questionDAO.getQuestionWithQuizzesById(questionId);
    // if (question != null) {
    // // Use Iterator to safely remove elements
    // Iterator<Question> quizIterator = quiz.getQuestions().iterator();
    // while (quizIterator.hasNext()) {
    // Question q = quizIterator.next();
    // if (q.getId().equals(questionId)) {
    // quizIterator.remove();
    // }
    // }

    // Iterator<Quiz> questionIterator = question.getQuizzes().iterator();
    // while (questionIterator.hasNext()) {
    // Quiz qz = questionIterator.next();
    // if (qz.getId().equals(quizId)) {
    // questionIterator.remove();
    // }
    // }

    // questionDAO.updateQuestion(question);
    // }
    // }
    // }

    // quizDAO.updateQuiz(quiz);
    // response.sendRedirect(request.getContextPath() +
    // "/ViewQuizList?edited=true");
    // } else {
    // response.getWriter().println("Quiz not found.");
    // }
    // } catch (NumberFormatException e) {
    // response.getWriter().println("Invalid quiz ID.");
    // e.printStackTrace();
    // }
    // } else {
    // response.getWriter().println("Quiz ID is missing.");
    // }
    // }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response editQuiz(@PathParam("userId") Long userId,
            @PathParam("quizId") Long quizId,
            @Context HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"You must be logged in to edit a quiz.\"}")
                    .build();
        }

        if (quizId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Quiz ID is missing.\"}")
                    .build();
        }

        QuestionDAO questionDAO = new QuestionDAO();
        List<Question> questions = questionDAO.getAllQuestionsForQuiz(quizId);

        if (questions == null || questions.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"No questions found for the given quiz.\"}")
                    .build();
        }

        // ✅ Convert Questions to DTOs
        List<QuestionDto> questionDTOs = questions.stream()
                .map(QuestionDto::new)
                .collect(Collectors.toList());

        return Response.ok(questionDTOs).build();
    }

}
