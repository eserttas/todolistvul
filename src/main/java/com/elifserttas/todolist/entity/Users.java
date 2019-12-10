package com.elifserttas.todolist.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String userName;

    private String lastName;

    private int active;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<Role>();

    @OneToMany(fetch = FetchType.EAGER,
    		mappedBy = "user",
    		cascade = {CascadeType.ALL},
    		orphanRemoval = true
    		)
    private Set<Note> notes = new HashSet<Note>();
    @OneToOne(mappedBy = "users" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    UsersDetail usersDetail;


    public Users() {
    }

    public Users(Users users) {
        this.active = users.active;
        this.email = users.email;
        this.roles = users.roles;
        this.password = users.password;
        this.userName = users.userName;
        this.lastName = users.lastName;
        this.id = users.id;
    }



    public Users addUserDetails(UsersDetail usersDetail){
        usersDetail.setUsers(this);
        this.usersDetail = usersDetail;
        return this;
    }

//    public Users addComment(Comment comments){
//        comments.setUsers(this);
//        this.comments.add(comments);
//        return this;
//    }


}