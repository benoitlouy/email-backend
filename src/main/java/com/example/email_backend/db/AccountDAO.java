package com.example.email_backend.db;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.example.email_backend.core.Account;
import com.example.email_backend.core.User;
import com.yammer.dropwizard.hibernate.AbstractDAO;

public class AccountDAO extends AbstractDAO<Account>
{

  public AccountDAO(SessionFactory sessionFactory)
  {
    super(sessionFactory);
  }
  
  public Account save(Account account)
  {
    return persist(account);
  }
  
  public Account findByIdForUser(Long accountId, User user)
  {
    Criteria criteria = criteria().add(Restrictions.idEq(accountId)).add(Restrictions.eq("user", user));
    return uniqueResult(criteria);
  }
  
  public void delete(Account account)
  {
    currentSession().delete(account);
  }

}
