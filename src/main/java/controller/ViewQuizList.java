package controller;

import model.Quiz;
import model.QuizCreator;
import dao.QuizDAO;
import dao.QuestionDAO;
import dto.QuizDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users/{userId}/quizzes")
public class ViewQuizList {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserQuizzes(@PathParam("userId") Long userId, @Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        QuizCreator loggedInUser = (QuizCreator) session.getAttribute("loggedInUser");

        // Check if the user is logged in
        if (loggedInUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"You must be logged in to view quizzes.\"}")
                    .build();
        }

        // Check if the logged-in user is authorized to view quizzes for the given
        // userId
        if (loggedInUser.getQuiz_creator_id() != userId) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("{\"message\":\"You are not authorized to view these quizzes.\"}")
                    .build();
        }

        // Retrieve all quizzes for the user from the database
        QuizDAO quizDAO = new QuizDAO();
        List<Quiz> quizzes = quizDAO.getAllQuizzesForUser(userId);

        // Convert Quiz entities to DTOs
        List<QuizDTO> quizDTOs = quizzes.stream().map(QuizDTO::new).collect(Collectors.toList());

        return Response.ok(quizDTOs).build(); // Return the quizzes as JSON
    }
}
