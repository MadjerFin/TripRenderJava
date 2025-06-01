package com.exemplo.controller;

import com.exemplo.model.User;
import com.exemplo.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @POST
    @Path("/register")
    public Response register(User user) {
        try {
            userService.save(user);
            return Response.status(Response.Status.CREATED).entity(user).build();
        } catch (Exception e) {
            e.printStackTrace(); // Vai aparecer nos logs do Railway
            return Response.serverError()
                    .entity("Erro interno ao registrar usuário: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/login")
    public Response login(User user) {
        User logged = userService.login(user.getEmail(), user.getSenha());
        if (logged != null) {
            return Response.ok(logged).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    public List<User> getAll() {
        return userService.findAll();
    }

    @GET
    @Path("/{id}")
    public User getById(@PathParam("id") Long id) {
        return userService.findById(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        userService.delete(id);
    }
}
