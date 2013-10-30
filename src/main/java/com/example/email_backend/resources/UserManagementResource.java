package com.example.email_backend.resources;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.email_backend.core.Account;
import com.example.email_backend.core.Message;
import com.example.email_backend.core.User;
import com.example.email_backend.db.AccountDAO;
import com.example.email_backend.db.UserDAO;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.jersey.params.LongParam;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserManagementResource
{
  private UserDAO userDAO;
  private AccountDAO accountDAO;
  
  public UserManagementResource(UserDAO userDAO, AccountDAO accountDAO)
  {
    this.userDAO = userDAO;
    this.accountDAO = accountDAO;
  }
  
  private User getUserOr404(Long userId)
  {
    User user = userDAO.findById(userId);
    if (user == null)
    {
      Response response = Response.status(404).entity(new Message("user not found")).build();
      throw new WebApplicationException(response);
    }
    return user;
  }
  
  private Account getAccountOr404(Long userId, Long accountId)
  {
    User user = getUserOr404(userId);
    Account account = accountDAO.findByIdForUser(accountId, user);
    if (account == null)
    {
      Response response = Response.status(404).entity(new Message("account not found")).build();
      throw new WebApplicationException(response);
    }
    return account;
  }
  
  @Path("/{userId}")
  @GET
  @UnitOfWork
  public User getUser(@PathParam("userId") LongParam userId)
  {
    User user = getUserOr404(userId.get());
    user.getAccounts().size();
    return user;
  }
  
  @Path("/add")
  @POST
  @UnitOfWork
  public User addUser(@Valid User user)
  {
    return userDAO.save(user);
  }
  
  @Path("/{userId}/update")
  @POST
  @UnitOfWork
  public User updateUser(@PathParam("userId") LongParam userId, User updatedUser)
  {
    User user = userDAO.findById(userId.get());
    if (user != null)
    {
      userDAO.save(user.merge(updatedUser));
    }
    return user;
  }
  
  @Path("/{userId}/account/add")
  @POST
  @UnitOfWork
  public Account addAccount(@PathParam("userId") LongParam userId, @Valid Account account)
  {
    User user = getUserOr404(userId.get());
    account.setUser(user);
    return accountDAO.save(account);
  }
  
  @Path("/{userId}/account/{accountId}")
  @GET
  @UnitOfWork
  public Account getAccount(@PathParam("userId") LongParam userId, @PathParam("accountId") LongParam accountId)
  {
    Account account = getAccountOr404(userId.get(), accountId.get());
    return account;
  }
  
  @Path("/{userId}/account/{accountId}/delete")
  @POST
  @UnitOfWork
  public Response deleteAccount(@PathParam("userId") LongParam userId, @PathParam("accountId") LongParam accountId)
  {
    Account account = getAccountOr404(userId.get(), accountId.get());
    accountDAO.delete(account);
    return Response.ok(new Message("account deleted")).build();
  }
}
