package com.mini.payment.domain.persistence;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("persistenceService")
public class PersistenceServiceImpl implements PersistenceService {
    private static final Logger LOG = LoggerFactory.getLogger(PersistenceServiceImpl.class);
    private static final Object[] EMPTY = {};

    private EntityManagerFactory entityManagerFactory;

    private SessionFactory sessionFactory;

    public PersistenceServiceImpl(final EntityManagerFactory entityManagerFactory) {
        super();
        setSessionFactory(entityManagerFactory.unwrap(SessionFactory.class));
    }

    // interface for pluggable row handling strategies below.
    private interface RowBuilder {
        Object buildRow(ResultSet rs, String[] colNames) throws SQLException;
    }

    private final RowBuilder mapBuilder = new RowBuilder() {

        @Override
        public Object buildRow(ResultSet rs, String[] colNames) throws SQLException {
            Map row = new HashMap();
            for (int i = 0; i < colNames.length; i++) {
                row.put(colNames[i], readColumnValue(rs, i));
            }
            return row;
        }
    };

    /**
     * Returns an Object [] for each row maintaining the column order from the
     * select clause.
     */
    private final RowBuilder arrayBuilder = new RowBuilder() {
        @Override
        public Object buildRow(ResultSet rs, String[] colNames)
                throws SQLException {
            Object[] row = new Object[colNames.length];
            for (int i = 0; i < colNames.length; i++) {
                row[i] = readColumnValue(rs, i);
            }
            return row;
        }
    };

    /**
     * Returns the given column value from the ResultSet after applying
     * common CLOB and Timestamp handling.
     */
    private Object readColumnValue(ResultSet rs, int i) throws SQLException {
        Object val = rs.getObject(i + 1);
        // Clobs are not Serializable
        if (val instanceof Clob) {
            Clob clob = (Clob) val;
            val = clob.getSubString(1, (int) clob.length());
        }

        if (isOracleTimestamp(val)) {
            val = rs.getTimestamp(i + 1);
        }
        return val;
    }

    /**
     * Returns true if the input Object is Oracle's oracle.sql.TIMESTAMP
     * class, which does not extend java.utilities.Date or java.sql.Timestamp.
     */
    private boolean isOracleTimestamp(Object val) {
        return (val != null && "oracle.sql.TIMESTAMP".equals(val.getClass()
                .getName()));
    }


    @Override
    public Session openSession() {
        return getSessionFactory().openSession();
    }

    public void shutdown() {
        getSessionFactory().close();
    }

    @Override
    public List query(String hql) {
        return this.query(hql, EMPTY);
    }

    @Override
    public List query(String hql, Object... params) {
        return query(hql, null, params);
    }

    @Override
    public List query(String hql, QueryPostProcessor post, Object... params) {
        Session session = null;
        try {
            session = openSession();
            Query query = session.createQuery(hql);
            for (int i = 0; i < params.length; i++) {
                query = query.setParameter(i, params[i]);
            }
            List result = query.list();
            if (Objects.nonNull(post)) {
                return post.processListResult(result);
            } else {
                return result;
            }
        } catch (JDBCException e) {
            LOG.error("JDBCException executing query {} Database may be down or unavailable.",
                    hql, e);
            throw e;
        } catch (HibernateException e) {
            LOG.error("Hibernate exception execution query {} ",
                    hql, e);
            throw e;
        } finally {
            close(session);
        }
    }


    @Override
    public List query(String hql, Map<String, Object> namedParams) {
        return query(hql, namedParams, null);
    }

    @Override
    public List query(String hql, Map<String, Object> namedParams, QueryPostProcessor post) {
        return List.of();
    }

    @Override
    public List pagedQuery(String hql, Map<String, Object> namedParameters,
                           Integer pageStart, Integer pageSize) {
        return pagedQuery(hql, namedParameters, pageStart, pageSize, null);
    }

    @Override
    public List pagedQuery(String hql, Map<String, Object> namedParameters,
                           Integer pageStart, Integer pageSize,
                           QueryPostProcessor post) {
        Session session = null;
        if (Objects.isNull(pageSize) || Objects.isNull(pageSize)) {
            LOG.debug("pageStart and pageSize are required, but not provided");
            return null;
        }

        try {
            session = openSession();
            Query query = session.createQuery(hql);
            for (Map.Entry<String, Object> entry : namedParameters.entrySet()) {
                query = query.setParameter(entry.getKey(), entry.getValue());
            }

            query.setFirstResult(pageStart);
            query.setMaxResults(pageSize);

            List result = query.list();

            if (Objects.nonNull(post)) {
                return post.processListResult(result);
            } else {
                return result;
            }
        } catch (JDBCException e) {
            LOG.error("JDBCException executing query {}", hql, e);
            throw e;
        } catch (HibernateException e) {
            LOG.error("HibernateException executing query {}", hql, e);
            throw e;
        } finally {
            close(session);
        }

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
        Session session = null;
        Transaction trx = null;

        if (Objects.isNull(params)) {
            LOG.debug("params required but not provided");
            return null;
        }

        try {
            session = openSession();
            Query query = session.createQuery(hql);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                query = query.setParameter(entry.getKey(), entry.getValue());
            }

            T found = (T) query.uniqueResult();
            trx = session.beginTransaction();
            item.setEditTime(new Date());
            session.persist(item);
            trx.commit();
            return item;
        } catch (Exception e) {
            if (e instanceof JDBCException) {
                LOG.error("JDBCException executing query {}", hql, e);
            } else if (e instanceof HibernateException) {
                LOG.error("HibernateException exception executing query {}", hql, e);
            }

            rollback(trx);
            throw e;
        } finally {
            close(session);
        }
    }


    private void rollback(Transaction trx) {
        if (Objects.nonNull(trx)) {
            try {
                trx.rollback();
            } catch (HibernateException e) {
                LOG.error("Error rolling back Transaction", e);
            }
        }
    }

    private void close(Session session) {
        if (Objects.nonNull(session)) {
            try {
                session.close();
            } catch (HibernateException e) {
                LOG.error("Error closing Session", e);
            }
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
