package com.mini.payment.domain.persistence;

import com.mini.payment.domain.DomainImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Map;

public interface PersistenceService {
    Session openSession();

    List query(String hql);

    List query(String hql, Object... params);

    List query(String hql, QueryPostProcessor post, Object... params);

    List query(String hql, Map<String, Object> namedParams);

    List query(String hql, Map<String, Object> namedParams, QueryPostProcessor post);

    List pagedQuery(String hql, Map<String, Object> namedParameters, Integer pageStart,
                    Integer pageSize);

    List pagedQuery(String hql, Map<String, Object> namedParameters, Integer pageStart,
                    Integer pageSize, QueryPostProcessor post);

    <T extends DomainImpl> T save(T item);

    <T extends DomainImpl> T save(T item, boolean saveOrUpdate) throws HibernateException;

    <T extends DomainImpl> T delete(T item) throws HibernateException;

    <T extends DomainImpl> List<T> mergeAll(List<T> itemList) throws HibernateException;

    List sqlQuery(String sql, Object... params);

    List sqlQueryLimit(String sql, int limit, Object... params);

    List<Object[]> sqlQueryArray(String sql, Object... params);

    int sqlUpdate(String sql, Object... params);

    <T extends DomainImpl> T findObjectByName(Class<T> clazz, String name);

    <T extends DomainImpl> T findSimpleObjectById(Class<T> clazz, String objId,
                                                  String typeName);

    <T extends DomainImpl> T findObjectByName(Class<T> clazz, String name,
                                              QueryPostProcessor post);

    <T extends DomainImpl> T findObjectByIdOrName(Class<T> clazz, String idName,
                                                  QueryPostProcessor post);

    Object querySingle(String hql);

    Object querySingle(String hql, Map<String, Object> params);

    int executeQuery(String hql, Map<String, Object> params);

    Object querySingle(String hql, Map<String, Object> params, QueryPostProcessor post);

    <T extends DomainImpl> T findOrSave(String hql, Map<String, Object> params, T item);
}
