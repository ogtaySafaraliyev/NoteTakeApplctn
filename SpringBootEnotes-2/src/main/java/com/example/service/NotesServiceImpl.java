package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.entity.Notes;
import com.example.entity.User;
import com.example.repository.NotesRepository;

@Service
public class NotesServiceImpl implements NotesService {

	@Autowired
	private NotesRepository notesRepository;

	@Override
	public Notes saveNotes(Notes notes) {
		return notesRepository.save(notes);
	}

	@Override
	public Notes getNotesById(int id) {
		return notesRepository.findById(id).get();
	}

	@Override
	public Notes updateNotes(Notes notes) {
		return notesRepository.save(notes);
	}

	@Override
	public boolean deleteNotes(int id) {
		Notes notes = notesRepository.findById(id).get();
		if (notes != null) {
			notesRepository.delete(notes);
			return true;
		}
		return false;
	}

//	@Override
//	public List<Notes> getNotesByUser(User user) {
//		return notesRepository.getNotesByUser(user);
//	}

	@Override
	public Page<Notes> getNotesByUser(User user, int pageNo) {	
		PageRequest pageable = PageRequest.of(pageNo, 3);		
		return notesRepository.getNotesByUser(user, pageable);
	}

}
