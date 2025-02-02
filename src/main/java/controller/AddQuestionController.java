package controller;

import model.Question;
import dao.QuestionDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;
import model.QuizCreator;

@Path("/users/{userId}/addquestion")
public class AddQuestionController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addQuestion(@PathParam("userId") int userId, Question newQuestion,
            @Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        QuizCreator loggedInUser = (QuizCreator) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"You must be logged in to add a question.\"}")
                    .build();
        }

        if (loggedInUser.getQuiz_creator_id() != userId) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("{\"message\":\"You are not authorized to add a question for this user.\"}")
                    .build();
        }

        newQuestion.setCreator(loggedInUser);

        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.insertQuestion(newQuestion);

        return Response.status(Response.Status.CREATED)
                .entity(newQuestion)
                .build();
    }
}