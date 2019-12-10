package com.elifserttas.todolist.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.elifserttas.todolist.command.UsersCommand;
import com.elifserttas.todolist.converter.UsersCommandToUsersConverter;
import com.elifserttas.todolist.entity.Note;
import com.elifserttas.todolist.repository.NoteRepository;
import com.elifserttas.todolist.service.NoteService;
import com.elifserttas.todolist.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	NoteRepository repository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UsersCommandToUsersConverter usersCommandToUsersConverter;
	
	@Autowired
	NoteService noteService;
	
	
	List<Note> notes;
	
	@GetMapping("/")
	public String maindPage(Model model) {
		notes = new ArrayList<Note>();
		UsersCommand users = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if(principal instanceof UsersCommand){
                users = (UsersCommand) principal;
            }
        }

       
		if(users != null)
			notes = repository.findNoteByUser_id(users.getId());
		model.addAttribute("note",new Note());
		model.addAttribute("noteList",notes);
		 model.addAttribute("currentUser",users);
		 
		return "post-index";
	}
	
	@GetMapping("/post/add")
	public String createPage(Model model) {
	
		
		model.addAttribute("note",new Note());
		
		return "post-add";
	}
	
	@PostMapping("/post/add")
	public String createPost(@AuthenticationPrincipal UsersCommand usersCommand
			,@ModelAttribute Note note,Model model) {
		
		if(!note.getTitle().isEmpty()) {
			note.setCreateDate(new Date());
			note.addUser(usersCommandToUsersConverter.convert(usersCommand));
			
			if(note.getFile() != null) {
				String filePath = uploadFile(note.getFile());
				if(!StringUtils.isEmpty(filePath)) {
					note.setFileUrl(filePath);
				}
			}
			
			repository.save(note);
			notes.add(note);
		}
		
		model.addAttribute("note",new Note());
		model.addAttribute("noteList",notes);
		
		return "redirect:/";
	}
	
	@GetMapping("/post/edit/{id}")
	public String editPost(@PathVariable("id") Long id,Model model) {
		
		Optional<Note> noteOptional = repository.findById(id);
		if(!noteOptional.isPresent()) {			
			return "redirect:/post";
		}
		model.addAttribute("note", noteOptional.get());
		
		
		return "post-add";
	}
	
	@GetMapping("/post/delete/{id}")
	public String createPost(@AuthenticationPrincipal UsersCommand usersCommand,@PathVariable("id") Long id) {
		Optional<Note> noteOptional = repository.findById(id);
		if(!noteOptional.isPresent())
			return "redirect:/";
		noteOptional.get().setUser(null);
		repository.delete(noteOptional.get());
		
		
		
		
		return "redirect:/";
	}
	
	@GetMapping("/post/{id}")
	public String getPost(@PathVariable("id") String id,Model model) {
		Note note = noteService.findById(id);
		//Optional<Note> note = repository.findById(id);
//		if(!note.isPresent()) {
//			return "redirect:/post";
//		}
		model.addAttribute("note", note);
		
		return "post-detail";
	}
	
	@RequestMapping(value = {"/login"})
    public String loginAction(@RequestParam(name = "error",required = false) String error,Model model){
        model.addAttribute("error");

        return ("login");
   }
	
	@GetMapping(value = {"/register"})
    public String registerAction(Model model){

        UsersCommand usersCommand = new UsersCommand();

        model.addAttribute("Users",usersCommand);

        return ("register");
    }

    @PostMapping(value = {"/register"})
    public String register(UsersCommand usersCommand){

        usersCommand.setActive(1);

        userService.saveUsersCommand(usersCommand);

        return ("redirect:/");
    }
    
    @RequestMapping(value="/file/download", method=RequestMethod.GET)
    @ResponseBody
    public FileSystemResource downloadFile(@RequestParam(value="file") String fileUrl) {
        return new FileSystemResource(new File("tmp"+File.separator+fileUrl));
    }
    
    public String uploadFile(MultipartFile file) {
    	  String fileName = null;
        try {
        	Path copyLocation = Paths
                .get("tmp" + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            fileName = file.getOriginalFilename();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return fileName;
        
    }
}
