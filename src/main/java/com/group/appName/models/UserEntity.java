package com.group.appName.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {

    private int id;
    private String username;
    private String email;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int Id) {
    }

    @Column(name = "user_name")
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    @Column(name = "email")
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}



