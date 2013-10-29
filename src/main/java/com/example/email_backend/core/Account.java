package com.example.email_backend.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "account")
public class Account
{

  private long id;
  private String name;
  private String address;
  private String username;
  private String password;
  private String host;
  private int port;
  private User user;

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

  @Column(name = "address", nullable = false, length = 255)
  public String getAddress()
  {
    return address;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }
  
  @Column(name = "username", nullable = false, length = 255)
  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  @Column(name = "password", nullable = true, length = 255)
  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  @Column(name = "host", nullable = false, length = 255)
  public String getHost()
  {
    return host;
  }

  public void setHost(String host)
  {
    this.host = host;
  }

  @Column(name = "port")
  public int getPort()
  {
    return port;
  }

  public void setPort(int port)
  {
    this.port = port;
  }
  
  @Column(name = "name", nullable = false, length = 255)
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  @OneToOne(optional = false)
  @JoinColumn(name = "userId")
  @JsonBackReference("user-account")
  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

}
