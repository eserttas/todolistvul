package com.elifserttas.todolist.service;

import com.elifserttas.todolist.converter.UsersToUsersCommandConverter;
import org.springframework.stereotype.Service;

import com.elifserttas.todolist.command.UsersCommand;
import com.elifserttas.todolist.converter.UsersCommandToUsersConverter;
import com.elifserttas.todolist.entity.Users;
import com.elifserttas.todolist.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
	
	UserRepository userRepository;
	
	UsersCommandToUsersConverter usersCommandToUsersConverter;

	UsersToUsersCommandConverter usersToUsersCommandConverter;
	
	public UserServiceImpl(UserRepository userRepository,
						   UsersCommandToUsersConverter usersCommandToUsersConverter,
						   UsersToUsersCommandConverter usersToUsersCommandConverter) {
		super();
		this.userRepository = userRepository;
		this.usersCommandToUsersConverter = usersCommandToUsersConverter;
		this.usersToUsersCommandConverter = usersToUsersCommandConverter;
	}

	@Override
	public void saveUsersCommand(UsersCommand usersCommand) {
		
		Users user =  usersCommandToUsersConverter.convert(usersCommand);
		userRepository.save(user);
		
		
	}

	public List<UsersCommand> findAll(){
		List<Users> all = userRepository.findAll();
		List<UsersCommand> collect = all.stream().map(usersToUsersCommandConverter::convert).collect(Collectors.toList());
		return collect;
	}

	public void deleteById(Long id){
		userRepository.deleteById(id);
	}

}
