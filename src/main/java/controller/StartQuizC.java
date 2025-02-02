
package controller;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

import dao.AnswerDAO;
import dao.QuestionDAO;
import dao.QuizDAO;
import dto.AnswerDTO;
import model.Answer;
import model.Question;
import model.Quiz;
import model.QuizCreator;

import javax.ws.rs.*;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

@Path("/users/{userId}/quizzes/{quizId}/save")
public class StartQuizC {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitQuizAnswers(
            @PathParam("userId") Long userId,
            @PathParam("quizId") Long quizId,
            Map<String, List<AnswerDTO>> payload,
            @Context HttpServletRequest request) {

        // Check if user is logged in
        HttpSession session = request.getSession(false);
        QuizCreator loggedInUser = (session != null) ? (QuizCreator) session.getAttribute("loggedInUser") : null;

        if (loggedInUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"User is not logged in.\"}")
                    .build();
        }

        // Fetch quiz
        QuizDAO quizDAO = new QuizDAO();
        Quiz quiz = quizDAO.getQuizWithQuestionsById(quizId);

        if (quiz == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Quiz not found.\"}")
                    .build();
        }

        // Fetch questions for the quiz
        QuestionDAO questionDAO = new QuestionDAO();
        List<Question> questions = questionDAO.getAllQuestionsForQuiz(quizId);
        List<AnswerDTO> updatedQuestions = payload.get("questions");
        // Save answers
        AnswerDAO answerDAO = new AnswerDAO();
        for (AnswerDTO submittedAnswer : updatedQuestions) {
            for (Question question : questions) {
                if (question.getId().equals(submittedAnswer.getId())) {
                    Answer answer = new Answer();
                    answer.setQuestion(question);
                    answer.setQuiz(quiz);
                    answer.setSolver(loggedInUser);
                    answer.setResponse(submittedAnswer.getQuestionAnswer());
                    answerDAO.insertAnswer(answer);
                }
            }
        }

        return Response.ok("{\"message\":\"Quiz submitted successfully!\"}").build();
    }
}
