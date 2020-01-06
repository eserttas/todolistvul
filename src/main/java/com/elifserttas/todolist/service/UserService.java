package com.elifserttas.todolist.service;

import com.elifserttas.todolist.entity.Users;
import org.apache.catalina.User;

import com.elifserttas.todolist.command.UsersCommand;

import java.util.List;

public interface UserService {

	public void saveUsersCommand(UsersCommand usersCommand);

	public List<UsersCommand> findAll();

	public void deleteById(Long id);
	
}
