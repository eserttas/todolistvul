package com.elifserttas.todolist.command;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String email;

    private String password;

    private String userName;

    private String lastName;

    private int active;

    Set<RoleCommand> roles = new HashSet<>();

    UsersDetailCommand usersDetailCommand;

    public UsersCommand(UsersCommand usersCommand){

        this.id = usersCommand.getId();
        this.email = usersCommand.getEmail();
        this.password = usersCommand.getPassword();
        this.userName = usersCommand.getUserName();
        this.lastName = usersCommand.getLastName();
        this.active = usersCommand.getActive();
        this.roles = usersCommand.getRoles();
        this.usersDetailCommand = usersCommand.getUsersDetailCommand();
    }
}
