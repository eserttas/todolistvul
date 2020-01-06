package com.elifserttas.todolist.controller;

import com.elifserttas.todolist.command.UsersCommand;
import com.elifserttas.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public String defaultPage(){
        return "redirect:/admin/users";
    }

    @GetMapping("/users")
    public String getUserList(@AuthenticationPrincipal UsersCommand users,Model model){
        List<UsersCommand> all = userService.findAll();
        model.addAttribute("users",all);
        model.addAttribute("currentUser",users);
        return "user-list";

    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin/users";

    }
}
