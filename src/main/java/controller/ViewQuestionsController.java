package controller;

import dao.QuestionDAO;
import dto.QuestionDto;
import model.Question;
import model.QuizCreator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users/{userId}/questions")
public class ViewQuestionsController {

    private QuestionDAO questionDAO = new QuestionDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestions(@Context HttpServletRequest request, @PathParam("userId") int userId) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"You must be logged in to view this page.\"}")
                    .build();
        }

        QuizCreator loggedInUser = (QuizCreator) session.getAttribute("loggedInUser");

        if (loggedInUser.getQuiz_creator_id() != userId) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("{\"message\":\"You are not authorized to view this user's questions.\"}")
                    .build();
        }

        List<Question> questions = questionDAO.getAllQuestionsForUser(loggedInUser);

        List<QuestionDto> questionDTOs = questions.stream()
                .map(q -> new QuestionDto(q.getId(), q.getQText(), q.getCreator().getUsername()))
                .collect(Collectors.toList());

        return Response.ok(questionDTOs).build();
    }
}