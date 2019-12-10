package com.elifserttas.todolist.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.elifserttas.todolist.command.UsersDetailCommand;
import com.elifserttas.todolist.entity.UsersDetail;

import lombok.Synchronized;

@Component
public class UsersDetailToUserDetailCommandConverter implements Converter<UsersDetail,UsersDetailCommand> {


    @Synchronized
    @Nullable
    @Override
    public UsersDetailCommand convert(UsersDetail usersDetail) {
        if(usersDetail == null)
            return null;

        UsersDetailCommand usersDetailCommand = new UsersDetailCommand();
        usersDetailCommand.setId(usersDetail.getId());
        usersDetailCommand.setDescription(usersDetailCommand.getDescription());
        return usersDetailCommand;
    }
}
