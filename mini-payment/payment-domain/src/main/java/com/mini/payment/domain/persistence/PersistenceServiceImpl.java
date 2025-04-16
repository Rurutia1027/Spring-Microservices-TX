package com.mini.payment.domain.persistence;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.EntityManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("persistenceService")
public class PersistenceServiceImpl implements PersistenceService {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Session openSession() {
        return null;
    }

    @Override
    public List query(String hql) {
        return List.of();
    }

    @Override
    public List query(String hql, Object... params) {
        return List.of();
    }

    @Override
    public List query(String hql, QueryPostProcessor post, Object... params) {
        return List.of();
    }

    @Override
    public List query(String hql, Map<String, Object> namedParams) {
        return List.of();
    }

    @Override
    public List query(String hql, Map<String, Object> namedParams, QueryPostProcessor post) {
        return List.of();
    }

    @Override
    public List pagedQuery(String hql, Map<String, Object> namedParameters, Integer pageStart, Integer pageSize) {
        return List.of();
    }

    @Override
    public List pagedQuery(String hql, Map<String, Object> namedParameters, Integer pageStart, Integer pageSize, QueryPostProcessor post) {
        return List.of();
    }

    @Override
    public <T extends DomainImpl> T save(T item) {
        return null;
    }

    @Override
    public <T extends DomainImpl> T save(T item, boolean saveOrUpdate) throws HibernateException {
        return null;
    }

    @Override
    public <T extends DomainImpl> T delete(T item) throws HibernateException {
        return null;
    }

    @Override
    public <T extends DomainImpl> List<T> saveAll(List<T> itemList) {
        return List.of();
    }

    @Override
    public <T extends DomainImpl> List<T> mergeAll(List<T> itemList) throws HibernateException {
        return List.of();
    }

    @Override
    public List sqlQuery(String sql, Object... params) {
        return List.of();
    }

    @Override
    public List sqlQueryLimit(String sql, int limit, Object... params) {
        return List.of();
    }

    @Override
    public List<Object[]> sqlQueryArray(String sql, Object... params) {
        return List.of();
    }

    @Override
    public int sqlUpdate(String sql, Object... params) {
        return 0;
    }

    @Override
    public <T extends DomainImpl> T findObjectByName(Class<T> clazz, String name) {
        return null;
    }

    @Override
    public <T extends DomainImpl> T findSimpleObjectById(Class<T> clazz, String objId, String typeName) {
        return null;
    }

    @Override
    public <T extends DomainImpl> T findObjectByName(Class<T> clazz, String name, QueryPostProcessor post) {
        return null;
    }

    @Override
    public <T extends DomainImpl> T findObjectByIdOrName(Class<T> clazz, String idName, QueryPostProcessor post) {
        return null;
    }

    @Override
    public Object querySingle(String hql) {
        return null;
    }

    @Override
    public Object querySingle(String hql, Map<String, Object> params) {
        return null;
    }

    @Override
    public int executeQuery(String hql, Map<String, Object> params) {
        return 0;
    }

    @Override
    public Object querySingle(String hql, Map<String, Object> params, QueryPostProcessor post) {
        return null;
    }

    @Override
    public <T extends DomainImpl> T findOrSave(String hql, Map<String, Object> params, T item) {
        return null;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
