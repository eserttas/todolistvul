package com.elifserttas.todolist.service;

import org.springframework.stereotype.Service;

import com.elifserttas.todolist.command.UsersCommand;
import com.elifserttas.todolist.converter.UsersCommandToUsersConverter;
import com.elifserttas.todolist.entity.Users;
import com.elifserttas.todolist.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	UserRepository userRepository;
	
	UsersCommandToUsersConverter usersCommandToUsersConverter;
	
	public UserServiceImpl(UserRepository userRepository, UsersCommandToUsersConverter usersCommandToUsersConverter) {
		super();
		this.userRepository = userRepository;
		this.usersCommandToUsersConverter = usersCommandToUsersConverter;
	}

	@Override
	public void saveUsersCommand(UsersCommand usersCommand) {
		
		Users user =  usersCommandToUsersConverter.convert(usersCommand);
		userRepository.save(user);
		
		
	}

}
