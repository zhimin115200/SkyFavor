package com.zhimin115200.test.SkyFavor.persistent.impl;

import com.zhimin115200.test.SkyFavor.persistent.BaseDao;
import com.zhimin115200.test.SkyFavor.persistent.FileDao;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_File;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileDaoImpl extends BaseDao<SF_File> implements FileDao {

	public FileDaoImpl() {
        super();
        setClazz(SF_File.class);
    }

	@Override
	public SF_File get(String fileId) {
		StringBuffer sb = new StringBuffer();
		sb.append("from SF_File where 1=1 ");
		sb.append(" and fileId = :fileId ");
		Session session = getCurrentSession();
		Query query = session.createQuery(sb.toString());
		query.setParameter("fileId", fileId);
		return (SF_File)query.uniqueResult();
	}

	@Override
	public boolean add(SF_File file) {
		try{
			this.create(file);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String fileId) {
		SF_File file = get(fileId);
		if(file!=null){
			try{
				this.delete(file);
			}catch(Exception e){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean modify(SF_File file) {
		try{
			this.update(file);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public List<SF_File> getAll(String folderId) {
		StringBuffer sb = new StringBuffer();
		sb.append("from SF_File where 1=1 ");
		sb.append(" and folderId = :folderId ");
		Session session = getCurrentSession();
		Query query = session.createQuery(sb.toString());
		query.setParameter("folderId", folderId);
		List<SF_File> files = query.list();
		return files;
	}
}
