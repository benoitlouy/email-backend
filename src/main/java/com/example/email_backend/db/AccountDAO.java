package com.example.email_backend.db;

import org.hibernate.SessionFactory;

import com.example.email_backend.core.Account;
import com.yammer.dropwizard.hibernate.AbstractDAO;

public class AccountDAO extends AbstractDAO<Account>
{

  public AccountDAO(SessionFactory sessionFactory)
  {
    super(sessionFactory);
  }

}
