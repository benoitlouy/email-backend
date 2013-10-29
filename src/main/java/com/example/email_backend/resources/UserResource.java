package com.example.email_backend.resources;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.email_backend.core.Error;
import com.example.email_backend.core.User;
import com.example.email_backend.db.UserDAO;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.jersey.params.LongParam;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource
{
  private UserDAO userDAO;
  
  public UserResource(UserDAO userDAO)
  {
    this.userDAO = userDAO;
  }
  
  @Path("/{userId}")
  @GET
  @UnitOfWork
  public User getUser(@PathParam("userId") LongParam userId)
  {
    User user = userDAO.findById(userId.get());
    if (user == null)
    {
      Response response = Response.status(404).entity(new Error("not found")).build();
      throw new WebApplicationException(response);
    }
    user.getAccounts().size();
    return user;
  }
  
  @Path("/create")
  @POST
  @UnitOfWork
  public User createUser(@Valid User user)
  {
    return userDAO.save(user);
  }
  
  @Path("/update/{userId}")
  @POST
  @UnitOfWork
  public User alterUser(@PathParam("userId") LongParam userId, User updatedUser)
  {
    User user = userDAO.findById(userId.get());
    if (user != null)
    {
      userDAO.save(user.merge(updatedUser));
    }
    return user;
  }
  
  
}
