package com.didihe1988.picker.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.PrivateMessageDao;
import com.didihe1988.picker.model.PrivateMessage;
@Repository
@Transactional
public class PrivateMessageDaoImpl implements PrivateMessageDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean checkOperateValidation(int ownerId, int objectId) {
		// TODO Auto-generated method stub

		String hql = "select count(*) from PrivateMessage as p where (p.senderId =? or p.recerverId=?) and p.id=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, ownerId);
		query.setInteger(1, ownerId);
		query.setInteger(2, objectId);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}

		return false;
	}

	@Override
	public PrivateMessage queryPrivateMessageById(int id) {
		// TODO Auto-generated method stub
		return (PrivateMessage) getCurrentSession().get(PrivateMessage.class,
				id);
	}

	@Override
	public int addPrivateMessage(PrivateMessage privateMessage) {
		// TODO Auto-generated method stub
		getCurrentSession().save(privateMessage);
		return 0;
	}

	@Override
	public int deletePMById(int id) {
		// TODO Auto-generated method stub
		if (!isPrivateMessageExistsById(id)) {
			return -1;
		}
		String hql = "delete PrivateMessage  where id=? ";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return query.executeUpdate();
	}

	@Override
	public int updatePrivateMessage(PrivateMessage privateMessage) {
		// TODO Auto-generated method stub
		if (!isPrivateMessageExistsById(privateMessage.getId())) {
			return -1;
		}
		getCurrentSession().update(privateMessage);
		return 1;
	}

	@Override
	public boolean isPrivateMessageExistsById(int id) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from PrivateMessage as p where p.id =?";
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
	public List<PrivateMessage> queryPrivateMessageByUserId(int userId1,
			int userId2) {
		// TODO Auto-generated method stub
		String hql = "from PrivateMessage as p where (p.senderId=? and p.receiverId=? ) or (p.senderId=? and p.receiverId=? )";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, userId1);
		query.setInteger(1, userId2);
		query.setInteger(2, userId2);
		query.setInteger(3, userId1);
		return query.list();
	}

	@Override
	public int getLatestPrivateMessageIdByUserId(int id) {
		// TODO Auto-generated method stub
		String hql = "select max(p.id) from PrivateMessage as p where p.senderId= ? or p.receiverId =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		query.setInteger(1, id);
		return (Integer) query.uniqueResult();
	}

}