package com.didihe1988.picker.daoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctResultTransformer;
import org.hibernate.transform.Transformers;
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

	/**
	 * @description ownerId:userId objectId:dialogId
	 */
	@Override
	public boolean checkOperateValidation(int ownerId, long objectId) {
		// TODO Auto-generated method stub

		String hql = "select count(*) from PrivateMessage as p where (p.senderId =? or p.receiverId=?) and p.dialogId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, ownerId);
		query.setInteger(1, ownerId);
		query.setLong(2, objectId);
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
	public int deletePrivateMessageById(int id) {
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
	public boolean isPrivateMessageExistsById(long id) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from PrivateMessage as p where p.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setLong(0, id);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PrivateMessage> queryPrivateMessageByUserId(int userId) {
		// TODO Auto-generated method stub
		/*
		 * from PrivateMessage as p where p.senderId=? or p.receiverId =?"
		 */
		/*
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				PrivateMessage.class);
		LogicalExpression expression = Restrictions.or(
				Restrictions.eq("senderId", userId),
				Restrictions.eq("receiverId", userId));
		criteria.add(expression);
		criteria.setProjection(Projections.distinct(Projections
				.property("dialogId")));*/
		String hql="from PrivateMessage as p where p.senderId=? or p.receiverId =? order by p.time desc";
		Query query=getCurrentSession().createQuery(hql);
		query.setInteger(0, userId);
		query.setInteger(1, userId);
		List<PrivateMessage> privateMessages=query.list();
		List<PrivateMessage> list=new ArrayList<PrivateMessage>();
		List<Long> dialogIds=new ArrayList<Long>();
		for(PrivateMessage privateMessage:privateMessages)
		{
			long dialogId=privateMessage.getDialogId();
			if(!dialogIds.contains(dialogId))
			{
				dialogIds.add(dialogId);
				list.add(privateMessage);
			}
		}
		return list;
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

	@SuppressWarnings("unchecked")
	@Override
	public long getDialogIdByUserId(int userId1, int userId2) {
		// TODO Auto-generated method stub
		String hql = "from PrivateMessage as p where (p.receiverId =? and p.senderId= ?) or (p.senderId =? and p.receiverId= ?)";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, userId1);
		query.setInteger(1, userId2);
		query.setInteger(2, userId1);
		query.setInteger(3, userId2);
		List<PrivateMessage> list = query.list();
		if (list.size() == 0) {
			return -1;
		} else {
			return list.get(0).getDialogId();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PrivateMessage> queryPrivateMessageByDialogId(long dialogId) {
		// TODO Auto-generated method stub
		String hql = "from PrivateMessage as p where p.dialogId=? ";
		Query query = getCurrentSession().createQuery(hql);
		query.setLong(0, dialogId);
		return query.list();
	}
}
