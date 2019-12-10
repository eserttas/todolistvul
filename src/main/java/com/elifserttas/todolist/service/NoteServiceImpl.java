package com.elifserttas.todolist.service;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.elifserttas.todolist.entity.Note;

@Service("noteService")
public class NoteServiceImpl implements NoteService{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Note findById(String id) {

	List<Note> note =	jdbcTemplate.query("Select * from Note where id =" +id+";",new RowMapper() {

		public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
			Note note = new Note();
			note.setId(rs.getLong("id"));
			note.setTitle(rs.getString("title"));
			note.setBody(rs.getString("body"));
			note.setFileUrl(rs.getString("file_url"));
			return note;
		}
		
	});
		return note.get(0);
	}

}
