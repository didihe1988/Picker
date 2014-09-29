package com.didihe1988.picker.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.MessageDao;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.model.Message.Filter;

@Repository
@Transactional
public class MessageDaoImpl implements MessageDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Message queryMessageById(int id) {
		// TODO Auto-generated method stub
		return (Message) getCurrentSession().get(Follow.class, id);
	}

	@Override
	public int addMessage(Message message) {
		// TODO Auto-generated method stub
		if (isMessageExists(message)) {
			return -1;
		}
		getCurrentSession().save(message);
		return 1;
	}

	@Override
	public int deleteMessage(Message message) {
		// TODO Auto-generated method stub
		if (!isMessageExists(message)) {
			return -1;
		}
		getCurrentSession().delete(message);
		return 1;
	}

	@Override
	public int updateMessage(Message message) {
		// TODO Auto-generated method stub
		if (!isMessageExists(message)) {
			return -1;
		}
		getCurrentSession().update(message);
		return 1;
	}

	@Override
	public boolean isMessageExists(Message message) {
		// TODO Auto-generated method stub
		if (message == null) {
			return false;
		}
		String hql = "select count(*) from Message as message where message.id =?";
		Query query = getCurrentSession().createQuery(hql);
		query.setLong(0, message.getId());
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isUncheckedMessageExists(int receiverId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Message message where message.receiverId =? and message.isChecked=false";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, receiverId);
		Long count = (Long) query.uniqueResult();
		if (count > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> queryMessageByReceiverId(int receiverId) {
		// TODO Auto-generated method stub
		String hql = "from Message as message where message.receiverId = ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, receiverId);
		return query.list();
	}

	@Override
	public int setMessageChecked(int id) {
		// TODO Auto-generated method stub
		String hql = "update Message as message set message.isChecked =false";
		Query query = getCurrentSession().createQuery(hql);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> queryMessageByReceiverIdAndType(int receiverId,
			int type) {
		// TODO Auto-generated method stub
		String hql = "";
		// 未查看的消息
		if (type == Message.MESSAGE_UNCHECKED) {
			hql = "from Message as message where message.receiverId = ? and message.isChecked =false";

		}
		/*
		 * // 有关问题的消息 else if (type == Message.MESSAGE_QUESTION) { hql =
		 * "from Message as message where message.receiverId = ? and message.type =4"
		 * ; } // 有关被关注人的消息 else if (type == Message.MESSAGE_FOLLOWED) { // hql
		 * = //
		 * "from Message as message where message.receiverId = ? and message.type between 1 and 3"
		 * ; hql =
		 * "from Message as message where message.receiverId = ? and message.type=1,2,3,4"
		 * ; }
		 */
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, receiverId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> queryMessageByReceiverIdAndFilter(int receiverId,
			Filter filter) {
		// TODO Auto-generated method stub
		String hql = "from Message as message where message.receiverId = ? and message.type between ? and ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, receiverId);
		query.setInteger(1, filter.getStartType());
		query.setInteger(2, filter.getEndType());
		return query.list();
	}
	
	

}
