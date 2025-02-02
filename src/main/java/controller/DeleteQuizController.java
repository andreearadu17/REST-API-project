
package controller;

import dao.QuizDAO;
import dao.QuestionDAO;
import model.Quiz;
import model.Question;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Path("/users/{userId}/quizzes/{quizId}")
public class DeleteQuizController {

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteQuiz(@PathParam("userId") Long userId,
            @PathParam("quizId") Long quizId,
            @Context HttpServletRequest request) {
        // Check if the user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"You must be logged in to delete a quiz.\"}")
                    .build();
        }

        // Validate that the logged-in user is authorized to delete the quiz
        // You can add logic to check if the logged-in user is the creator of the quiz
        // Example:
        // if (!loggedInUser.getId().equals(userId)) {
        // return Response.status(Response.Status.FORBIDDEN)
        // .entity("{\"message\":\"You are not authorized to delete this quiz.\"}")
        // .build();
        // }

        QuizDAO quizDAO = new QuizDAO();
        Quiz quiz = quizDAO.getQuizWithQuestionsById(quizId);

        if (quiz != null) {

            QuestionDAO questionDAO = new QuestionDAO();
            List<Question> questions = quiz.getQuestions();

            // Remove references in quiz_question table
            for (Question question : questions) {
                Question fullQuestion = questionDAO.getQuestionWithQuizzesById(question.getId());
                fullQuestion.getQuizzes().remove(quiz);
                questionDAO.updateQuestion(fullQuestion);
            }

            // Delete the quiz
            quizDAO.deleteQuiz(quiz);
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("{\"message\":\"Quiz deleted successfully.\"}")
                    .build();

        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Quiz not found.\"}")
                    .build();
        }

    }
}
