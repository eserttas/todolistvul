package com.elifserttas.todolist.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.elifserttas.todolist.command.RoleCommand;
import com.elifserttas.todolist.entity.Role;

import lombok.Synchronized;

@Component
public class RoleToRoleCommandConverter implements Converter<Role,RoleCommand> {

    @Synchronized
    @Nullable
    @Override
    public RoleCommand convert(Role role) {
        if(role == null)
            return null;
        RoleCommand roleCommand = new RoleCommand();
        roleCommand.setId(role.getId());
        roleCommand.setRole(role.getRole());
        return roleCommand;

    }
}