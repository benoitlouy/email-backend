package com.example.email_backend;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

public class EmailBackendConfiguration extends Configuration
{
   @Valid
   @NotNull
   @JsonProperty("database")
   private DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
   
   public DatabaseConfiguration getDatabaseConfiguration()
   {
     return databaseConfiguration;
   }
}