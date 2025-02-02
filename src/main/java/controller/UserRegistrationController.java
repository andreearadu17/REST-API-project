package controller;

import dao.UserDAOImpl;
import model.QuizCreator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserRegistrationController {

        @POST
        @Path("/signup")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response registerUser(QuizCreator user) {
                UserDAOImpl userDAO = new UserDAOImpl();
                userDAO.insertUser(user);
                return Response.status(Response.Status.CREATED).entity(user).build();
        }

}