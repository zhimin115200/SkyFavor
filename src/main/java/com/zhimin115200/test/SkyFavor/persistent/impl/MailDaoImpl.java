package com.zhimin115200.test.SkyFavor.persistent.impl;

import com.zhimin115200.test.SkyFavor.persistent.BaseDao;
import com.zhimin115200.test.SkyFavor.persistent.MailDao;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_Mail;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MailDaoImpl extends BaseDao<SF_Mail> implements MailDao {

	public MailDaoImpl() {
        super();
        setClazz(SF_Mail.class);
    }

	@Override
	public SF_Mail get(String mailId) {

		StringBuffer sb = new StringBuffer();
		sb.append("from SF_Mail where 1=1 ");
		sb.append(" and mailId = :mailId ");
		Session session = getCurrentSession();
		Query query = session.createQuery(sb.toString());
		query.setParameter("mailId", mailId);
		return (SF_Mail)query.uniqueResult();
	}

	@Override
	public boolean add(SF_Mail mail) {
		try{
			this.create(mail);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String mailId) {
		SF_Mail mail = get(mailId);
		if(mail!=null){
			try{
				this.delete(mail);
			}catch(Exception e){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean modify(SF_Mail mail) {
		try{
			this.update(mail);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public List<SF_Mail> getAll() {
		List<SF_Mail> mails = this.findAll();
		return mails;
	}

	@Override
	public SF_Mail getLatest(String mail, Integer type) {
		StringBuffer sb = new StringBuffer();
		sb.append("from SF_Mail where 1=1 ");
		sb.append(" and email = :email ");
		sb.append(" and mailType = :mailType ");
		sb.append(" order by createTime desc");
		Session session = getCurrentSession();
		Query query = session.createQuery(sb.toString());
		query.setMaxResults(1);
		query.setParameter("email", mail);
		query.setParameter("mailType", type);
		return (SF_Mail)query.uniqueResult();
	}
}
