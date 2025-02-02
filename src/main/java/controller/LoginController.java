package controller;

import dao.UserDAOImpl;
import model.QuizCreator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mindrot.jbcrypt.BCrypt;

import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Path("/login")
public class LoginController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(QuizCreator credentials, @Context HttpServletRequest request) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        // Check if the user exists in the database
        UserDAOImpl userDAO = new UserDAOImpl();
        QuizCreator user = userDAO.getUserByUsername(username);

        // If user is found and password matches
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            // Create a new session for the logged-in user
            HttpSession session = request.getSession(true); // 'true' creates a new session if one doesn't exist
            session.setAttribute("loggedInUser", user); // Save the logged-in user in the session

            return Response.ok(user.getQuiz_creator_id()).build(); // Return the user object in the response
        } else {
            // If authentication fails, return Unauthorized status with an empty user object
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new QuizCreator()) // Send an empty object to indicate failure
                    .build();
        }
    }
}
