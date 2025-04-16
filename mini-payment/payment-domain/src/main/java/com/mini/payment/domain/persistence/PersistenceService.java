package com.mini.payment.domain.persistence;

import com.mini.payment.domain.DomainImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Map;

/**
 * PersistenceService implementations given clients access to query and updating
 * the database via SQL or HQL.
 */
public interface PersistenceService {
    /**
     * Returns a Hibernate Session connected to the repository.
     * THe caller is responsible for properly closing this session.
     */
    Session openSession();

    /**
     * Executes the Hibernate Query Language and returns the results in list.
     *
     * @param hql hibernate query language
     */
    List query(String hql);

    /**
     * Executes the given hql query, using the variable arguments list to bind positional
     * parameters.
     *
     * @param hql    The Hibernate query language query containing positional params.
     * @param params the query parameters, organized in order
     * @return the result set for the query
     */
    List query(String hql, Object... params);

    /**
     * Executes the given query, using the variable arguments list to bind positional
     * parameters.
     *
     * @param hql    the Hibernate query language query containing positional parameters.
     * @param post   the query post-processor
     * @param params the query parameters, organized in order
     * @return the result set for the query
     */
    List query(String hql, QueryPostProcessor post, Object... params);

    /**
     * Executes the given query, using the associative array to bind named parameters.
     *
     * @param hql         The Hibernate query language query containing named parameters
     * @param namedParams the associative array of named parameters.
     * @return the result set for the query
     */
    List query(String hql, Map<String, Object> namedParams);

    /**
     * Executes the given query, using the associative array to bind named parameters.
     *
     * @param hql         the Hibernate query language query containing named parameters
     * @param namedParams the associative array of named parameters.
     * @param post        the query post-processor
     * @return the result set for the query
     */
    List query(String hql, Map<String, Object> namedParams, QueryPostProcessor post);


    /**
     * Executes the given query, using the associative array to bind named parameters.
     *
     * @param hql             the Hibernate query language query containing named parameters
     * @param pageStart       the start point for the page
     * @param pageSize        the number of elements to be returned from the page
     * @param namedParameters the associative array of named parameters.
     * @return the result set for the query
     */
    List pagedQuery(String hql, Map<String, Object> namedParameters, Integer pageStart,
                    Integer pageSize);

    /**
     * Executes the given query, using the associative array to bind named parameters.
     *
     * @param hql             the Hibernate query language query containing named parameters
     * @param pageStart       the start point for the page
     * @param pageSize        the number of elements to be returned from the page
     * @param namedParameters the associative array of named parameters.
     * @param post            the query post-processor. May be {@code null} if no post-processing is required.
     * @return the result set
     */
    List pagedQuery(String hql, Map<String, Object> namedParameters, Integer pageStart,
                    Integer pageSize, QueryPostProcessor post);

    /**
     * Inserts or Update the object. if it's new, creates new one. Otherwise updates the object
     *
     * @param item the object to persist
     * @param <T>  the type of {@code item}
     * @return the saved object
     */
    <T extends DomainImpl> T save(T item);

    /**
     * Inserts the object
     *
     * @param item         the object to persist
     * @param saveOrUpdate if false, Inserts one objects, if existed, throws HibernateException. If true, Check if
     *                     objects exists, if existed, related object will be updated
     * @param <T>          the type of {@code item}
     * @return the saved object
     */
    <T extends DomainImpl> T save(T item, boolean saveOrUpdate) throws HibernateException;

    /**
     * Permanently and irrevocably deletes an item from the database.
     *
     * @param item the item to delete
     * @param <T>  the type of {@code item}
     * @return the deleted item
     * @throws HibernateException if an error occurs
     */
    <T extends DomainImpl> T delete(T item) throws HibernateException;

    /**
     * Inserts the objects if they are new, otherwise updates the objects.
     *
     * @param itemList The list of objects to persist.
     * @return The list of passed in objects after Hibernate has set the IDs.
     */
    <T extends DomainImpl> List<T> saveAll(List<T> itemList);


    <T extends DomainImpl> List<T> mergeAll(List<T> itemList) throws HibernateException;

    /**
     * Execute an SQL query with ? replacement parameters.
     *
     * @param sql    An SQL query to run.
     * @param params The replacement parameter values.
     * @return A List of Maps with lowercase column names as the keys.
     */
    List sqlQuery(String sql, Object... params);

    /**
     * Execute an SQL query with ? replacement parameters.
     *
     * @param sql    An SQL query to run.
     * @param limit  The maximum number of rows to retrieve.
     * @param params The replacement parameter values.
     * @return A List of Maps with lowercase column names as the keys.
     */
    List sqlQueryLimit(String sql, int limit, Object... params);

    /**
     * Execute an SQL query with ? replacement parameters.
     *
     * @param sql    An SQL query to run.
     * @param params The replacement parameter values.
     * @return A List of Object[]s with containing the values from the
     * select clause in order.
     */
    List<Object[]> sqlQueryArray(String sql, Object... params);

    /**
     * Execute an SQL update with ? replacement parameters.
     *
     * @param sql    An SQL update to run.
     * @param params The replacement parameter values.
     * @return The number of rows affected.
     */
    int sqlUpdate(String sql, Object... params);

    /**
     * Finds an object with the given name.
     *
     * @param clazz the data type of the object to search for
     * @param name  the name or unique identifier of the object
     * @param <T>   The data type of the object
     * @return the fully loaded object that corresponds to the given name, or {@code null} if no such object can be
     * found.
     */
    <T extends DomainImpl> T findObjectByName(Class<T> clazz, String name);

    /**
     * Finds an object with the given unique identifier.
     *
     * @param clazz    the data type of the object to search for
     * @param objId    the unique identifier of the object
     * @param typeName object's type name
     * @return the fully loaded object that corresponds to the given unique identifier, or {@code null} if no
     * such object can be found.
     */
    <T extends DomainImpl> T findSimpleObjectById(Class<T> clazz, String objId,
                                                  String typeName);

    /**
     * Finds an object with the given name.
     *
     * @param clazz the data type of the object to search for
     * @param name  the name or unique identifier of the object
     * @param post  the query post-processor to run on the object
     * @param <T>   The data type of the object
     * @return the fully loaded object that corresponds to the given name or unique identifier, or {@code null} if no
     * such object can be found.
     */
    <T extends DomainImpl> T findObjectByName(Class<T> clazz, String name,
                                              QueryPostProcessor post);

    /**
     * Finds an object with the given name or unique identifier.
     *
     * @param clazz  the data type of the object to search for
     * @param idName the name or unique identifier of the object
     * @param post   the query post-processor to run on the object (may be {@code null})
     * @param <T>    The data type of the object
     * @return the fully loaded object that corresponds to the given name or unique identifier, or {@code null} if no
     * such object can be found.
     */
    <T extends DomainImpl> T findObjectByIdOrName(Class<T> clazz, String idName,
                                                  QueryPostProcessor post);

    /**
     * Execute an HQL query for a single object.
     *
     * @param hql HQL query
     * @return object from the query
     */
    Object querySingle(String hql);

    /**
     * Executes an HQL query for a single object.
     *
     * @param hql    the HQL query text
     * @param params the parameters of the query
     * @return the result of the query
     */
    Object querySingle(String hql, Map<String, Object> params);

    /**
     * Executes an INSERT, UPDATE, or DELETE statement
     *
     * @param hql    hql statement to be executed
     * @param params parameters of the statement
     * @return the number of rows effects by the query
     */
    int executeQuery(String hql, Map<String, Object> params);

    /**
     * Executes an HQL query for a single object.
     *
     * @param hql    the HQL query text
     * @param params the parameters of the query
     * @param post   the post processor that handles the result of the query
     * @return the result of the query
     */
    Object querySingle(String hql, Map<String, Object> params, QueryPostProcessor post);

    /**
     * Returns an object if found, or creates the object and return it.
     */
    <T extends DomainImpl> T findOrSave(String hql, Map<String, Object> params, T item);
}
