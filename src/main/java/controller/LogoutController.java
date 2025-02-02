package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/logout")
public class LogoutController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response logoutUser(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Get existing session (don't create new one)

        if (session != null) {
            session.invalidate(); // Destroy the session
            return Response.ok("{\"message\":\"User logged out successfully.\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"No active session found.\"}")
                    .build();
        }
    }
}
