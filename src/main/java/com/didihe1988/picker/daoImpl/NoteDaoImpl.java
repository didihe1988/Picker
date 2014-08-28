package com.didihe1988.picker.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.NoteDao;
import com.didihe1988.picker.model.Note;

@Repository
@Transactional
public class NoteDaoImpl implements NoteDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * @description 检测user是否拥有该note
	 */
	@Override
	public boolean checkDeleteValidation(int ownerId, int objectId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Note as n where n.userId = ? and n.id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, ownerId);
		query.setInteger(1, objectId);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Note queryNoteById(int id) {
		// TODO Auto-generated method stub
		return (Note) getCurrentSession().get(Note.class, id);
	}

	/**
	 * @description 没办法检测是否已存在
	 */
	@Override
	public int addNote(Note note) {
		// TODO Auto-generated method stub
		getCurrentSession().save(note);
		return 1;
	}

	@Override
	public int deleteNote(Note note) {
		// TODO Auto-generated method stub
		if (!checkDeleteValidation(note.getUserId(), note.getId())) {
			return -1;
		}
		getCurrentSession().delete(note);
		return 1;
	}

	@Override
	public int updateNote(Note note) {
		// TODO Auto-generated method stub
		if (!isNoteExistsById(note.getId())) {
			return -1;
		}
		getCurrentSession().update(note);
		return 1;
	}

	@Override
	public boolean isNoteExistsById(int id) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Note as n where n.id = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Note> queryNoteListByUserId(int id, boolean isPublic) {
		// TODO Auto-generated method stub
		String hql = "from Note as n where n.userId=? and n.isPublic =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		query.setBoolean(1, isPublic);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Note> queryNoteListByBookId(int id, boolean isPublic) {
		// TODO Auto-generated method stub
		String hql = "from Note as n where n.bookId=? and n.isPublic =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		query.setBoolean(1, isPublic);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Note> queryAllNoteListByUserId(int id) {
		// TODO Auto-generated method stub
		String hql = "from Note as n where n.userId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Note> queryAllNoteListByBookId(int id) {
		// TODO Auto-generated method stub
		String hql = "from Note as n where n.bookId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.list();
	}

	@Override
	public int incrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update Note as n set n." + property + "=n." + property
				+ "+1  where n.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int decrementNum(String property, int id) {
		// TODO Auto-generated method stub
		String hql = "update Note as n set n." + property + "=n." + property
				+ "-1 where n.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int queryLatestNoteIdByUserId(int id) {
		// TODO Auto-generated method stub
		String hql = "select max(a.id) from Note as n where n.userId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (Integer) query.uniqueResult();
	}

}
