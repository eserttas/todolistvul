package com.elifserttas.todolist.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.elifserttas.todolist.command.UsersDetailCommand;
import com.elifserttas.todolist.entity.UsersDetail;

import lombok.Synchronized;

@Component
public class UsersDetailCommandToUserDetailsConverter implements Converter<UsersDetailCommand,UsersDetail> {

    @Synchronized
    @Nullable
    @Override
    public UsersDetail convert(UsersDetailCommand usersDetailCommand) {
        if(usersDetailCommand == null)
            return null;

        UsersDetail usersDetail = new UsersDetail();
        usersDetail.setDescription(usersDetailCommand.getDescription());
        usersDetail.setId(usersDetailCommand.getId());

        return usersDetail;
    }
}