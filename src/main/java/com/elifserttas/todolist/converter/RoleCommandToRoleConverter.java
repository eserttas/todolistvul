package com.elifserttas.todolist.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.elifserttas.todolist.command.RoleCommand;
import com.elifserttas.todolist.entity.Role;

import lombok.Synchronized;

@Component
public class RoleCommandToRoleConverter implements Converter<RoleCommand,Role> {

    @Synchronized
    @Nullable
    @Override
    public Role convert(RoleCommand roleCommand) {
        if(roleCommand == null)
            return null;
        Role role = new Role();
        role.setId(roleCommand.getId());
        role.setRole(roleCommand.getRole());
        return role;
    }
}