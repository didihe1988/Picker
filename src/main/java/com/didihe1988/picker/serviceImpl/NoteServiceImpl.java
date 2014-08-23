package com.didihe1988.picker.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.NoteDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Note;
import com.didihe1988.picker.service.NoteService;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
	@Autowired
	private NoteDao noteDao;

	@Autowired
	private UserDao userDao;

	@Override
	public int addNote(Note note) {
		// TODO Auto-generated method stub
		if (note == null) {
			return Status.NULLPOINTER;
		}
		noteDao.addNote(note);
		/*
		 * 
		 */
		userDao.incrementNum(Constant.NOTE_NUM, note.getUserId());
		return Status.SUCCESS;
	}

	@Override
	public int deleteNote(Note note) {
		// TODO Auto-generated method stub
		if (note == null) {
			return Status.NULLPOINTER;
		}
		int status = noteDao.deleteNote(note);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int updateNote(Note note) {
		// TODO Auto-generated method stub
		if (note == null) {
			return Status.NULLPOINTER;
		}
		int status = noteDao.updateNote(note);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public Note getNoteById(int id) {
		// TODO Auto-generated method stub
		return noteDao.queryNoteById(id);
	}

	@Override
	public boolean isNoteExistsById(int id) {
		// TODO Auto-generated method stub
		return noteDao.isNoteExistsById(id);
	}

	@Override
	public List<Note> getALlNoteListByUserId(int id) {
		// TODO Auto-generated method stub
		return noteDao.queryAllNoteListByUserId(id);
	}

	@Override
	public List<Note> getALLNoteListByBookId(int id) {
		// TODO Auto-generated method stub
		return noteDao.queryAllNoteListByBookId(id);
	}

	@Override
	public List<Note> getPublicNoteListByBookId(int id) {
		// TODO Auto-generated method stub
		return noteDao.queryNoteListByBookId(id, true);
	}

	@Override
	public List<Note> getPublicNoteListByUserId(int id) {
		// TODO Auto-generated method stub
		return noteDao.queryNoteListByUserId(id, true);
	}

}
