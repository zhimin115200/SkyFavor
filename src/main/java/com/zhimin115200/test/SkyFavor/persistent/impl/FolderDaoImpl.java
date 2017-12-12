package com.zhimin115200.test.SkyFavor.persistent.impl;

import com.zhimin115200.test.SkyFavor.persistent.BaseDao;
import com.zhimin115200.test.SkyFavor.persistent.FolderDao;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_Folder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FolderDaoImpl extends BaseDao<SF_Folder> implements FolderDao {

	public FolderDaoImpl() {
        super();
        setClazz(SF_Folder.class);
    }

	@Override
	public SF_Folder get(String folderId) {
		StringBuffer sb = new StringBuffer();
		sb.append("from SF_Folder where 1=1 ");
		sb.append(" and folderId = :folderId ");
		Session session = getCurrentSession();
		Query query = session.createQuery(sb.toString());
		query.setParameter("folderId", folderId);
		return (SF_Folder)query.uniqueResult();
	}

	@Override
	public boolean add(SF_Folder folder) {
		try{
			this.create(folder);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String folder_id) {
		SF_Folder folder = get(folder_id);
		if(folder!=null){
			try{
				this.delete(folder);
			}catch(Exception e){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean modify(SF_Folder folder) {
		try{
			this.update(folder);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public List<SF_Folder> getAll(String email) {
		StringBuffer sb = new StringBuffer();
		sb.append("from SF_Folder where 1=1 ");
		sb.append(" and email = :email ");
		Session session = getCurrentSession();
		Query query = session.createQuery(sb.toString());
		query.setParameter("email", email);
		List<SF_Folder> folders = query.list();
		return folders;
	}
}
