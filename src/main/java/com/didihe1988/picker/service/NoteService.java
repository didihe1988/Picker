package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Note;
import com.didihe1988.picker.model.NoteDp;

public interface NoteService {
	public int addNote(Note note);

	public int deleteNote(Note note, int userId);

	public int deleteNoteById(int id, int userId);

	public int updateNote(Note note,int userId);

	public Note getNoteById(int id);

	public boolean isNoteExistsById(int id);

	public List<Note> getALlNoteListByUserId(int id);

	public List<NoteDp> getALlNoteDpListByUserId(int id,int userId);

	public List<Note> getPublicNoteListByUserId(int id);

	public List<NoteDp> getPublicNoteDpListByUserId(int id,int userId);

	public List<Note> getALLNoteListByBookId(int id);

	public List<NoteDp> getALLNoteDpListByBookId(int id,int userId);

	public List<Note> getPublicNoteListByBookId(int id);

	public List<NoteDp> getPublicNoteDpListByBookId(int id,int userId);

	public NoteDp getNoteDpByNoteId(int id,int userId);

	public int getLatestNoteIdByUserId(int id);
	
	public boolean checkOperateValidation(int userId, int noteId);
}
