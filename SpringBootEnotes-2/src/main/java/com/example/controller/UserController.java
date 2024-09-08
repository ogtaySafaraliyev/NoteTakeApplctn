package com.example.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Notes;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.NotesService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NotesService notesService;

	@ModelAttribute
	public User getUser(Principal p, Model m) {
		String email = p.getName();
		User user = userRepository.findByEmail(email);
		m.addAttribute("user", user);
		return user;
	}

	@GetMapping("/addNotes")
	public String addNotes() {
		return "addNotes";
	}

	@GetMapping("/viewNotes")
	public String viewNotes(Principal p, Model m, @RequestParam(defaultValue = "0") Integer pageNo) {
		User user = getUser(p, m);		
		Page<Notes> notes = notesService.getNotesByUser(user, pageNo);		
		
		m.addAttribute("currentPage", pageNo);
		m.addAttribute("totalNotes", notes.getTotalElements());
		m.addAttribute("totalPages", notes.getTotalPages());
		m.addAttribute("notesList", notes.getContent());
		return "viewNotes";
	}

	@GetMapping("/editNotes/{id}")
	public String editNotes(@PathVariable int id, Model m) {
		Notes notes = notesService.getNotesById(id);
		m.addAttribute("n", notes);
		return "editNotes";
	}
	
	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m) {
		
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p, m));
		
		Notes savedNotes = notesService.saveNotes(notes);
	    if(savedNotes != null) {
	    	session.setAttribute("message", "New Note saved");
	    }
	    else {
	    	session.setAttribute("message", "Something wrong on server");
	    }
		return "redirect:/user/addNotes";
	}
	
	@PostMapping("/updateNotes")
	public String updateNotes(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m) {
		
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p, m));
		
		Notes savedNotes = notesService.updateNotes(notes);
	    if(savedNotes != null) {
	    	session.setAttribute("message", "Note updated");
	    }
	    else {
	    	session.setAttribute("message", "Something wrong on server");
	    }
		return "redirect:/user/viewNotes";
	}
	
	@GetMapping("/deleteNotes/{id}")
	public String deleteNotes(@PathVariable int id, HttpSession session) {
		boolean isFlag = notesService.deleteNotes(id);
		if(isFlag) {
	    	session.setAttribute("message", "Note deleted");
	    }
	    else {
	    	session.setAttribute("message", "Something wrong on server");
	    }
		return "redirect:/user/viewNotes";
	}
}
