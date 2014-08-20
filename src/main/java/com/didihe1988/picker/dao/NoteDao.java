package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.daoInterface.DeleteValidation;
import com.didihe1988.picker.model.Note;

public interface NoteDao extends DeleteValidation {
	public Note queryNoteById(int id);

	public int addNote(Note note);

	public int deleteNote(Note note);

	public int updateNote(Note note);

	public boolean isNoteExistsById(int id);

	public List<Note> queryNoteListByUserId(int id, boolean isPublic);

	public List<Note> queryNoteListByBookId(int id, boolean isPublic);

	public List<Note> queryAllNoteListByUserId(int id);

	public List<Note> queryAllNoteListByBookId(int id);

}
