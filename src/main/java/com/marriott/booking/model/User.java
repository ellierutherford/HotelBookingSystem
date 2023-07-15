package com.marriott.booking.model;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private Long user_id;

    @Column(nullable = false, unique = true, length = 45)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 120)
    private String roles;

    public User(){

    }

    public User(Long userId, String username, String password, String roles){
        this.user_id = userId;
        this.password = password;
        this.username = username;
        if(roles.isEmpty()){
            this.roles = "user";
        }
        else{
            this.roles = roles;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "SecurityUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }

    public Long getUser_id() {return this.user_id; }

    public void setUser_id(Long user_id) { this.user_id = user_id;}

    // password getter and setter?? is this right??
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}