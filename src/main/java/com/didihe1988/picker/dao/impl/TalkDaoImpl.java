package com.didihe1988.picker.dao.impl;
/*package com.didihe1988.picker.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.dao.TalkDao;
import com.didihe1988.picker.model.Talk;

@Repository
@Transactional
public class TalkDaoImpl implements TalkDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Talk queryTalkById(int id) {
		// TODO Auto-generated method stub
		return (Talk) getCurrentSession().get(Talk.class, id);
	}

	@Override
	public int addTalk(Talk talk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteTalk(Talk tlak) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateTalk(Talk talk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isTalkExists(Talk talk) {
		// TODO Auto-generated method stub
		if(talk==null)
		{
			return false;
		}
		String hqlString="select count(*) from Talk as t where t."
	}

	@Override
	public int getLatestTalkIdByTalkerId(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}*/
