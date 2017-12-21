package com.zhimin115200.test.SkyFavor.persistent.impl;

import com.zhimin115200.test.SkyFavor.persistent.BaseDao;
import com.zhimin115200.test.SkyFavor.persistent.UserDao;
import com.zhimin115200.test.SkyFavor.persistent.UserTokenDao;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_User;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_User_Token;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class UserTokenDaoImpl extends BaseDao<SF_User_Token> implements UserTokenDao {

	private static Log logger = LogFactory.getLog(UserTokenDaoImpl.class);
	public UserTokenDaoImpl() {
        super();
        setClazz(SF_User_Token.class);
    }

	@Override
	public SF_User_Token get(String email) {
		StringBuffer sb = new StringBuffer();
		sb.append("from SF_User_Token where 1=1 ");
		sb.append(" and email = :email ");
		sb.append(" order by id desc ");
		Session session = getCurrentSession();
		Query query = session.createQuery(sb.toString());
		query.setParameter("email", email);
		List<SF_User_Token> tks = query.list();
		if(!CollectionUtils.isEmpty(tks)){
			return tks.get(0);
		}else{
			return null;
		}
	}

	@Override
	public boolean add(SF_User_Token token) {
		try{
			this.create(token);
		}catch(Exception e){
			logger.error("add user error:"+e.getMessage(),e);
			return false;
		}
		return true;
	}
}
