package com.example.email_backend.db;

import org.hibernate.SessionFactory;

import com.example.email_backend.core.User;
import com.yammer.dropwizard.hibernate.AbstractDAO;

public class UserDAO extends AbstractDAO<User>
{

  public UserDAO(SessionFactory sessionFactory)
  {
    super(sessionFactory);
  }
  
  public User save(User user)
  {
    return persist(user);
  }
  
  public User findById(Long userId)
  {
    return get(userId);
  }

}
