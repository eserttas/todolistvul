package com.elifserttas.todolist.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RoleCommand {

    private Long id;

    private String role;

    @Override
    public String toString() {
        return "RoleCommand{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
