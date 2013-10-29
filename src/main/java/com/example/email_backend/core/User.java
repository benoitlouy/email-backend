package com.example.email_backend.core;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user")
public class User
{

  private long id;
  private String username;
  private String password;
  private Set<Account> accounts = new HashSet<Account>();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long getId()
  {
    return id;
  }

  public void setId(long id)
  {
    this.id = id;
  }

  @NotEmpty
  @JsonProperty
  @Column(name = "username", nullable = false, length = 255)
  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  @NotEmpty
  @JsonProperty
  @Column(name = "password", nullable = false, length = 255)
  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @JsonManagedReference("user-account")
  public Set<Account> getAccounts()
  {
    return accounts;
  }

  public void setAccounts(Set<Account> accounts)
  {
    this.accounts = accounts;
  }
  
  public User merge(User other)
  {
    if (other.password != null)
    {
      this.password = other.password;
    }
    return this;
  }
}
