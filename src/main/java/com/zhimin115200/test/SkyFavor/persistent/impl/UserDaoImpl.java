package com.zhimin115200.test.SkyFavor.persistent.impl;

import com.zhimin115200.test.SkyFavor.persistent.BaseDao;
import com.zhimin115200.test.SkyFavor.persistent.UserDao;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDao<SF_User> implements UserDao {

	private static Log logger = LogFactory.getLog(UserDaoImpl.class);
	public UserDaoImpl() {
        super();
        setClazz(SF_User.class);
    }

	@Override
	public SF_User get(String email) {
		StringBuffer sb = new StringBuffer();
		sb.append("from SF_User where 1=1 ");
		sb.append(" and email = :email ");
		Session session = getCurrentSession();
		Query query = session.createQuery(sb.toString());
		query.setParameter("email", email);
		return (SF_User)query.uniqueResult();
	}

	@Override
	public boolean add(SF_User user) {
		try{
			this.create(user);
		}catch(Exception e){
			logger.error("add user error:"+e.getMessage(),e);
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String email) {
		SF_User user = get(email);
		if(user!=null){
			try{
				this.delete(user);
			}catch(Exception e){
				logger.error("delete user error:"+e.getMessage(),e);
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean modify(SF_User user) {
		try{
			this.update(user);
		}catch(Exception e){
			logger.error("modify user error:"+e.getMessage(),e);
			return false;
		}
		return true;
	}

	@Override
	public List<SF_User> getAll() {
		List<SF_User> users = this.findAll();
		return users;
	}
}
