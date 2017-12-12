package com.zhimin115200.test.SkyFavor.persistent;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.common.base.Preconditions;

public class BaseDao<T extends Serializable> {
	private Class<T> clazz;

	@Resource(name = "sessionFactoryForSF")
	private SessionFactory sessionFactory;

	public void create(final T entity) {
		Preconditions.checkNotNull(entity);
		getCurrentSession().saveOrUpdate(entity);
	}

	public T createEntity(final T entity) {
		Preconditions.checkNotNull(entity);
		getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	public void delete(final T entity) {
		Preconditions.checkNotNull(entity);
		getCurrentSession().delete(entity);
	}

	public void deleteById(final long entityId) {
		final T entity = findById(entityId);
		Preconditions.checkState(entity != null);
		delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	@SuppressWarnings("unchecked")
	public T findById(final long id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	public T update(final T entity) {
		Preconditions.checkNotNull(entity);
		getCurrentSession().update(entity);
		return entity;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void setClazz(final Class<T> clazzToSet) {
		this.clazz = Preconditions.checkNotNull(clazzToSet);
	}
}
