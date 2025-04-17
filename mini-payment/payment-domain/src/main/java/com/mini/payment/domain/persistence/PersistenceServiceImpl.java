package com.mini.payment.domain.persistence;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
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

    private SessionFactoryImplementor sessionFactory;

    public PersistenceServiceImpl(final EntityManagerFactory entityManagerFactory) {
        super();
        setSessionFactory(entityManagerFactory.unwrap(SessionFactoryImplementor.class));
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
        Session session = null;
        if (Objects.isNull(namedParams)) {
            LOG.debug("");
            return null;
        }
        try {
            session = openSession();
            Query query = session.createQuery(hql);
            for (Map.Entry<String, Object> entry : namedParams.entrySet()) {
                query = query.setParameter(entry.getKey(), entry.getValue());
            }

            List result = query.list();

            if (Objects.nonNull(post)) {
                return post.processListResult(result);
            } else {
                return result;
            }

        } catch (JDBCException e) {
            LOG.error("JDBCException executing query {} got exception", hql, e);
            throw e;
        } catch (HibernateException e) {
            LOG.error("HibernateException executing query {} got exception", hql, e);
            throw e;
        } finally {
            close(session);
        }
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
        Session session = null;
        Transaction trx = null;

        try {
            session = openSession();
            trx = session.beginTransaction();
            session.persist(item);
            trx.commit();
            return item;
        } catch (Exception e) {
            if (e instanceof JDBCException) {
                LOG.info("JDBCException got exception ", e);
            } else if (e instanceof HibernateException) {
                LOG.info("HibernateException got exception ", e);
            }
            rollback(trx);
            throw e;
        } finally {
            close(session);
        }
    }

    // saveOrUpdate = true, then save & update this record, and the record will be detached
    // saveOrUpdate = false, then create a new record, with new identify id value
    @Override
    public <T extends DomainImpl> T save(T item, boolean saveOrUpdate) throws HibernateException {
        Session session = null;
        Transaction trx = null;
        T retItem = null;

        try {
            session = openSession();
            trx = session.beginTransaction();
            T result;
            if (saveOrUpdate) {
                result = (T) session.merge(item);
            } else {
                session.persist(item);
                result = item;
            }
            trx.commit();
            return result;
        } catch (HibernateException e) {
            LOG.info("Hibernate Exception while executing saveOrUpdate", e);
            rollback(trx);
            throw e;
        } finally {
            close(session);
        }
    }

    @Override
    public <T extends DomainImpl> T delete(T item) throws HibernateException {
        Session session = null;
        Transaction trx = null;

        try {
            session = openSession();
            trx = session.beginTransaction();
            session.remove(item);
            trx.commit();
            return item;
        } catch (HibernateException e) {
            LOG.error("Got Hibernate Exception while deleting item, " +
                    "gonna rollback", e);
            rollback(trx);
            throw e;
        } finally {
            close(session);
        }
    }

    /**
     * Persist a list of items of type T to the data store.
     *
     * @param itemList {@link java.util.List} of objects to persist
     * @param <T>      The type of the item being persisted
     * @return {@link java.util.List} of type T
     * @throws org.hibernate.HibernateException if exception occurs during saveAll.
     */
    @Override
    public <T extends DomainImpl> List<T> saveAll(List<T> itemList) {
        Session session = null;
        Transaction trx = null;

        try {
            session = openSession();
            trx = session.beginTransaction();
            for (T save : itemList) {
                session.persist(save);
            }
            trx.commit();
            return itemList;
        } catch (HibernateException e) {
            LOG.error("Hibernate exception during executing saveAll", e);
            rollback(trx);
            throw e;
        } finally {
            close(session);
        }
    }

    /**
     * Persist a list of items of type T to the data store.
     *
     * @param itemList {@link java.util.List} of objects to persist
     * @param <T>      The type of the item being persisted
     * @return {@link java.util.List} of type T
     * @throws org.hibernate.HibernateException if exception occurs during saveAll.
     */
    @Override
    public <T extends DomainImpl> List<T> mergeAll(List<T> itemList)
            throws HibernateException {
        Session session = null;
        Transaction trx = null;

        try {
            session = openSession();
            trx = session.beginTransaction();
            for (T save : itemList) {
                session.merge(save);
            }
            trx.commit();
            return itemList;
        } catch (HibernateException e) {
            LOG.error("HibernateException got exception via mergeAll gonna rollback");
            rollback(trx);
            throw e;
        } finally {
            close(session);
        }
    }

    @Override
    public List sqlQuery(String sql, Object... params) {
        return sqlQueryExecute(sql, 0, params, mapBuilder);
    }

    @Override
    public List sqlQueryLimit(String sql, int limit, Object... params) {
        return sqlQueryExecute(sql, limit, params, mapBuilder);
    }

    @Override
    public List<Object[]> sqlQueryArray(String sql, Object... params) {
        return sqlQueryExecute(sql, 0, params, arrayBuilder);
    }

    @Override
    public int sqlUpdate(String sql, Object... params) {
        Session session = null;
        try {
            session = openSession();
            return session.doReturningWork(new UpdateWork(sql, params));
        } catch (Exception e) {
            for (Object param : params) {
                sql = sql + "," + param.toString();
            }
            LOG.error("Failed to execute sql {}, with exception {}",
                    sql, e.getMessage());
            throw e;
        } finally {
            close(session);
        }
    }

    @Override
    public <T extends DomainImpl> T findObjectByName(Class<T> clazz, String name) {
        return findObjectByName(clazz, name, null);
    }

    @Override
    public <T extends DomainImpl> T findSimpleObjectById(Class<T> clazz, String objId, String typeName) {
        return null;
    }

    @Override
    public <T extends DomainImpl> T findObjectByName(Class<T> clazz, String name, QueryPostProcessor post) {
        if (Objects.isNull(name)) {
            return null;
        }

        EntityPersister persister = sessionFactory.getMetamodel().entityPersister(clazz);
        String[] propertyNames = persister.getPropertyNames();
        String hql = "from " + clazz.getName() + " where lower(name) = lower(?1)";
        if (Arrays.asList(propertyNames).contains("deleted")) {
            hql += " and deleted is null";
        }

        List<T> found = query(hql, post, name);
        return !found.isEmpty() ? found.get(0) : null;
    }

    @Override
    public <T extends DomainImpl> T findObjectByIdOrName(Class<T> clazz, String idName,
                                                         QueryPostProcessor post) {
        if (Objects.isNull(idName)) {
            LOG.debug("Name or unique identifier required, but not provided");
            return null;
        }

        EntityPersister persister = sessionFactory.getMetamodel().entityPersister(clazz);
        String[] propertyNames = persister.getPropertyNames();
        String hql = "from " + clazz.getName()
                + " where (id=?0 or lower(name)=lower(?1))"
                + " and deleted is null";
        List<T> found = query(hql, post, idName, idName);
        return found.isEmpty() ? null : found.get(0);
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

    private List sqlQueryExecute(String sql, int limit, Object[] params,
                                 RowBuilder builder) {
        Session session = null;
        try {
            session = openSession();
            return session.doReturningWork(
                    new LimitedWork(sql, limit, params, builder));

        } catch (HibernateException e) {
            LOG.error("Exception got during executing sql {} with {}",
                    sql, params.length, e);
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

    public SessionFactoryImplementor getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactoryImplementor sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // -- define static classes --
    private static class BaseWork {
        protected void close(final Statement stmt) {
            if (Objects.nonNull(stmt)) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    LOG.error("Error closing Statement", e);
                }
            }
        }

        protected void close(final ResultSet rs) {
            if (Objects.nonNull(rs)) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOG.error("Error closing ResultSet", e);
                }
            }
        }

        protected void rollback(final Connection conn) {
            if (Objects.nonNull(conn)) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    LOG.error("Error rolling back Connection", e);
                }
            }
        }

        // Returns the lower-cased column names in ResultSet.
        protected String[] columnNames(final ResultSet rs) throws SQLException {
            final String[] cols = getColumnNames(rs);
            for (int i = 0; i < cols.length; i++) {
                cols[i] = cols[i].toLowerCase();
            }
            return cols;
        }

        // Returns the column names in the ResultSet.
        protected String[] getColumnNames(final ResultSet rs) throws SQLException {
            final ResultSetMetaData meta = rs.getMetaData();
            final String[] names = new String[meta.getColumnCount()];
            for (int i = 0; i < names.length; i++) {
                names[i] = meta.getColumnName(i + 1);
            }
            return names;
        }
    }

    // UpdateWork
    private static class UpdateWork extends BaseWork
            implements ReturningWork<Integer> {
        private final String sql;
        private final Object[] params;

        public UpdateWork(String sql, Object... params) {
            this.sql = sql;
            this.params = params;
        }

        @Override
        public Integer execute(Connection connection) throws SQLException {
            PreparedStatement stmt = null;
            boolean autoCommit = connection.getAutoCommit();
            try {
                connection.setAutoCommit(false);
                stmt = connection.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
                int rows = stmt.executeUpdate();
                connection.commit();
                return rows;
            } catch (SQLException e) {
                LOG.error("Exception executing sql '{}' with {} parameters",
                        sql, params.length, e);
                rollback(connection);
                throw e;
            } finally {
                close(stmt);
                connection.setAutoCommit(autoCommit);
            }
        }
    }

    // LimitedWork
    private static class LimitedWork extends BaseWork
            implements ReturningWork<List<Object>> {
        private final String sql;
        private final int limit;
        private final Object[] params;
        private final RowBuilder builder;

        public LimitedWork(String sql, int limit, Object[] params, RowBuilder builder) {
            this.sql = sql;
            this.limit = limit;
            this.params = params;
            this.builder = builder;
        }

        @Override
        public List<Object> execute(Connection conn) throws SQLException {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setMaxRows(limit);
                for (int i = 0; i < params.length; i++) {
                    if (params[i] == null) {
                        // Oracle's null handling always works with VARCHAR
                        stmt.setNull(i + 1, Types.VARCHAR);
                    } else {
                        stmt.setObject(i + 1, params[i]);
                    }
                }
                rs = stmt.executeQuery();

                List<Object> found = new ArrayList<>();
                while (rs.next()) {
                    found.add(builder.buildRow(rs, columnNames(rs)));
                }

                // reset in case the stmt is reused by pooling
                stmt.setMaxRows(0);
                return found;
            } finally {
                close(rs);
                close(stmt);
            }
        }
    }
}
