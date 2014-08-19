package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Note;

public interface NoteService {
	public int addNote(Note note);

	public int deleteNote(Note note);

	public int updateNote(Note note);

	public Note getNoteById(int id);

	public boolean isNoteExistsById(int id);

	public List<Note> getNoteListByUserId(int id);

	public List<Note> getNoteListByBookId(int id);
}
