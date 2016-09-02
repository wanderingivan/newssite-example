package com.newssite.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Abstract class that provides convenience methods for 
 * session, query and criteria handling.
 */
public abstract class AbstractHibernateDao<T> {
	
	private final Class<T> type;
	
	private SessionFactory sessionFactory;

	public AbstractHibernateDao(SessionFactory sessionFactory,Class<T> type) {
		super();
		this.sessionFactory = sessionFactory;
		this.type = type;
	}
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	protected Query createQuery(String query){
		return getSession().createQuery(query);
	}
	
	protected Query getNamedQuery(String name){
		return getSession().getNamedQuery(name);
	}
	
	protected Criteria createCriteria(){
		return getSession().createCriteria(type);
	}
	
	@SuppressWarnings("rawtypes")
	protected Criteria createCriteria(Class clazz){
		return getSession().createCriteria(clazz);
	}
	@SuppressWarnings("rawtypes")
	protected Criteria createCriteria(Class clazz,String alias){
		return getSession().createCriteria(clazz,alias);
	}
	
	@SuppressWarnings("unchecked")
	protected T load(Integer id){
		return (T) getSession().load(type, id);
	}
}
