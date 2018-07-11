package com.wealth.persistence.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wealth.domain.BaseDomainEntity;

public abstract class AbstractDAO<T extends BaseDomainEntity> {

	protected static Log logger = LogFactory.getLog(AbstractDAO.class);	

	@SuppressWarnings("unchecked")
	public Integer persist(T entity) {
		try {
			if (entity.getId() == null) {
				return (Integer) getSession().save(entity);
			} else {
				getSession().update(entity);
				return entity.getId();
			}
		} catch (RuntimeException re) {
			logger.error("persist failed:id=" + entity.getId() + ":Entity="
					+ getEntityClass(), re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public T findById(Integer id) {
		try {
			if (id != null) {
				return (T) getSession().get(getEntityClass(), id);
			} else {
				return null;
			}
		} catch (RuntimeException re) {
			logger.error("findById failed:id=" + id + ":Entity="
					+ getEntityClass(), re);
			throw re;
		}
	}

	public void deleteEntity(T entity) {
		try {
			getSession().delete(entity);
		} catch (RuntimeException re) {
			logger.error("deleteEntity failed:id=" + entity.getId()
					+ ":Entity=" + getEntityClass(), re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		try {
			Query query = getSession().createQuery("from " + getEntityName());
			return query.list();
		} catch (RuntimeException re) {
			logger.error("findAll failed:Entity=" + getEntityClass(), re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public Criteria createCriteria(Class classInstance) {
		Session session = getSession();
		return session.createCriteria(classInstance);
	}

	public Criteria createCriteria() {
		return createCriteria(getEntityClass());
	}

	@SuppressWarnings("unchecked")
	public List<T> searchByCriteria(Criteria criteria) {
		try {
			return criteria.list();
		} catch (RuntimeException re) {
			logger.error("searchByCriteria failed:Entity=" + getEntityClass(), re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Deprecated
	// The query string is mallformed. Needs to be revised. Consider using JPA
	public List<T> findByProperty(String propertyName, final Object value) {
		try {
			Session session = getSession();
			final String queryString = "from " + getEntityName()
					+ " as model where model." + propertyName + " = " + value;

			Query query = session.createQuery(queryString);
			return query.list();
		} catch (RuntimeException re) {
			logger
					.error("findByProperty failed:Entity=" + getEntityClass(),
							re);
			throw re;
		}
	}

	/**
	 * This revolting method is required as we cannot do a T.Class
	 * 
	 * @return
	 */
	protected abstract Class<T> getEntityClass();

	protected abstract String getEntityName();
	
	protected abstract Session getSession();
}