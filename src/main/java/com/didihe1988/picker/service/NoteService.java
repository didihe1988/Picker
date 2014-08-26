package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Note;
import com.didihe1988.picker.model.NoteDp;

public interface NoteService {
	public int addNote(Note note);

	public int deleteNote(Note note);

	public int updateNote(Note note);

	public Note getNoteById(int id);

	public boolean isNoteExistsById(int id);

	public List<Note> getALlNoteListByUserId(int id);

	public List<Note> getPublicNoteListByUserId(int id);

	public List<Note> getALLNoteListByBookId(int id);

	public List<Note> getPublicNoteListByBookId(int id);

	public NoteDp getNoteDpByNoteId(int id);
}
