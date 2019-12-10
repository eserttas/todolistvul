package com.elifserttas.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elifserttas.todolist.entity.Note;
import com.elifserttas.todolist.entity.Users;

@Repository("noteRepository")
public interface NoteRepository extends JpaRepository<Note, Long> {

	public List<Note> findNoteByUser_id(Long id);
	
	public Note findById(String id);
	
}
