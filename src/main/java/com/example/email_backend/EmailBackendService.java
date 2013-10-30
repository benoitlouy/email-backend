package com.example.email_backend;


import com.example.email_backend.core.Account;
import com.example.email_backend.core.User;
import com.example.email_backend.db.AccountDAO;
import com.example.email_backend.db.UserDAO;
import com.example.email_backend.resources.UserManagementResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.migrations.MigrationsBundle;

public class EmailBackendService extends Service<EmailBackendConfiguration>
{
  private final HibernateBundle<EmailBackendConfiguration> hibernate =
      new HibernateBundle<EmailBackendConfiguration>(User.class, Account.class)
      {
        @Override
        public DatabaseConfiguration getDatabaseConfiguration(
            EmailBackendConfiguration configuration)
        {
          return configuration.getDatabaseConfiguration();
        }
      };
      
  private final MigrationsBundle<EmailBackendConfiguration> migration =
      new MigrationsBundle<EmailBackendConfiguration>()
      {
        @Override
        public DatabaseConfiguration getDatabaseConfiguration(
            EmailBackendConfiguration configuration)
        {
          return configuration.getDatabaseConfiguration();
        }
      };
  
  public static void main(String[] args) throws Exception
  {
    new EmailBackendService().run(args);
  }

  @Override
  public void initialize(Bootstrap<EmailBackendConfiguration> bootstrap)
  {
    bootstrap.setName("email-backend");
    bootstrap.addBundle(hibernate);
    bootstrap.addBundle(migration);
  }

  @Override
  public void run(EmailBackendConfiguration configuration,
                  Environment environment) throws ClassNotFoundException
  {
    final AccountDAO accountDAO = new AccountDAO(hibernate.getSessionFactory());
    final UserDAO userDAO = new UserDAO(hibernate.getSessionFactory());
    environment.addResource(new UserManagementResource(userDAO, accountDAO));
  }

}