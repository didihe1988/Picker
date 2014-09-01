package com.didihe1988.picker.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.dao.NoteDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.Note;
import com.didihe1988.picker.model.NoteDp;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.service.NoteService;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
	@Autowired
	private NoteDao noteDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private BookDao bookDao;

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
	public int deleteNote(Note note, int userId) {
		// TODO Auto-generated method stub
		if (note == null) {
			return Status.NULLPOINTER;
		}
		if (!noteDao.checkOperateValidation(userId, note.getId())) {
			return Status.INVALID;
		}
		noteDao.deleteNote(note);
		return Status.SUCCESS;
	}

	@Override
	public int deleteNoteById(int id, int userId) {
		// TODO Auto-generated method stub
		return deleteNote(noteDao.queryNoteById(id), userId);
	}

	@Override
	public int updateNote(Note note, int userId) {
		// TODO Auto-generated method stub
		if (note == null) {
			return Status.NULLPOINTER;
		}
		if (!noteDao.checkOperateValidation(userId, note.getId())) {
			return Status.INVALID;
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

	@Override
	public NoteDp getNoteDpByNoteId(int id) {
		// TODO Auto-generated method stub
		Note note = noteDao.queryNoteById(id);
		return getNoteDpByNote(note);
	}

	private NoteDp getNoteDpByNote(Note note) {
		User user = userDao.queryUserById(note.getUserId());
		String bookName = bookDao.queryBookById(note.getBookId()).getBookName();
		return new NoteDp(note, bookName, user.getUsername(),
				user.getAvatarUrl());
	}

	private List<NoteDp> getNoteDpListFromNoteList(List<Note> noteList) {
		List<NoteDp> list = new ArrayList<NoteDp>();
		for (Note note : noteList) {
			NoteDp noteDp = getNoteDpByNote(note);
			list.add(noteDp);
		}
		return list;
	}

	@Override
	public List<NoteDp> getALlNoteDpListByUserId(int id) {
		// TODO Auto-generated method stub
		return getNoteDpListFromNoteList(getALlNoteListByUserId(id));
	}

	@Override
	public List<NoteDp> getPublicNoteDpListByUserId(int id) {
		// TODO Auto-generated method stub
		return getNoteDpListFromNoteList(getPublicNoteListByUserId(id));
	}

	@Override
	public List<NoteDp> getALLNoteDpListByBookId(int id) {
		// TODO Auto-generated method stub
		return getNoteDpListFromNoteList(getALLNoteListByBookId(id));
	}

	@Override
	public List<NoteDp> getPublicNoteDpListByBookId(int id) {
		// TODO Auto-generated method stub
		return getNoteDpListFromNoteList(getPublicNoteListByBookId(id));
	}

	@Override
	public int getLatestNoteIdByUserId(int id) {
		// TODO Auto-generated method stub
		return noteDao.queryLatestNoteIdByUserId(id);
	}

	@Override
	public boolean checkOperateValidation(int userId, int noteId) {
		// TODO Auto-generated method stub
		return noteDao.checkOperateValidation(userId, noteId);
	}

}
