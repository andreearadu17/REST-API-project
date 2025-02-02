package controller;

import model.Quiz;
import model.QuizCreator;
import model.Question;
import dao.QuestionDAO;
import dao.QuizDAO;
import dto.QuizDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Path("/users/{userId}/addquiz")
public class AddQuizController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addQuiz(@PathParam("userId") int userId, QuizRequest quizRequest,
            @Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        QuizCreator loggedInUser = (QuizCreator) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"You must be logged in to add a quiz.\"}")
                    .build();
        }

        if (loggedInUser.getQuiz_creator_id() != userId) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("{\"message\":\"You are not authorized to add a quiz for this user.\"}")
                    .build();
        }

        if (quizRequest.getQuestionIds() == null || quizRequest.getQuestionIds().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"You must select at least one question.\"}")
                    .build();
        }

        Quiz quiz = new Quiz();
        quiz.setQuizName(quizRequest.getQuizName());
        quiz.setQuizCreator(loggedInUser);
        quiz.setQuestions(new ArrayList<>());

        QuestionDAO questionDAO = new QuestionDAO();
        for (Long questionId : quizRequest.getQuestionIds()) {
            Question question = questionDAO.getQuestionWithQuizzesById(questionId);
            if (question != null) {
                quiz.getQuestions().add(question);
                // question.getQuizzes().add(quiz);
            }
        }

        QuizDAO quizDAO = new QuizDAO();
        quizDAO.insertQuiz(quiz);

        return Response.status(Response.Status.CREATED)
                .entity(new QuizDTO(quiz)) // ✅ Good: Converts to DTO before returning
                .build();

    }

    public static class QuizRequest {
        private String quizName;
        private List<Long> questionIds;

        public String getQuizName() {
            return quizName;
        }

        public void setQuizName(String quizName) {
            this.quizName = quizName;
        }

        public List<Long> getQuestionIds() {
            return questionIds;
        }

        public void setQuestionIds(List<Long> questionIds) {
            this.questionIds = questionIds;
        }
    }
}
