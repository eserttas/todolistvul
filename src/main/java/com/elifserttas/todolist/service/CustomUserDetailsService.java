package com.elifserttas.todolist.service;


import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elifserttas.todolist.command.UsersCommand;
import com.elifserttas.todolist.converter.UsersToUsersCommandConverter;
import com.elifserttas.todolist.entity.Users;
import com.elifserttas.todolist.model.CustomUserDetails;
import com.elifserttas.todolist.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private UsersToUsersCommandConverter usersToUsersCommandConverter;

    public CustomUserDetailsService(UserRepository userRepository, UsersToUsersCommandConverter usersToUsersCommandConverter) {
        this.userRepository = userRepository;
        this.usersToUsersCommandConverter = usersToUsersCommandConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = userRepository.findByUserName(username);

        optionalUsers.orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));

        Optional<UsersCommand> usersCommandOptional = Optional.of(usersToUsersCommandConverter.convert(optionalUsers.get()));

        return usersCommandOptional.map(CustomUserDetails::new).get();
    }
}