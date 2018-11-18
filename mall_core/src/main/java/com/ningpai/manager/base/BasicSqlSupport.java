/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ningpai.manager.base;

import com.ningpai.manager.util.SqlHelper;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author AthrunNatu
 *         <p/>
 *         2013-5-30
 */
/*
 * 用于所有DAO成实现类的父类，内置提供了session方法
 */
public class BasicSqlSupport {

    // Spring注入
    @Resource(name = "sqlSession")
    private SqlSession session;
    // Spring注入
    @Resource(name = "sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 根据配置查询 ID，及查询参数获取运行时sql内容
     *
     * @param mappedId
     *            配置的sql ID
     * @param parameterObject
     * @return String sql内容
     */
    protected String getRunTimeSqlContent(String mappedId, Object parameterObject) {
        MappedStatement ms = sqlSessionFactory.getConfiguration().getMappedStatement(mappedId);
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        return formatSql(boundSql.getSql());
    }

    /**
     * 根据配置查询ID，获取运行时sql内容
     *
     * @param mappedId
     *            配置的sqlID
     * @return String sql内容
     */
    protected String getRunTimeSqlContent(String mappedId) {
        return this.getRunTimeSqlContent(mappedId, null);
    }

    /**
     * 通过Mapper方法名获取sql
     *
     * @param session
     * @param mappedId
     * @param param
     * @return
     */
    protected String getRunTimeSqlContent(SqlSession session, String mappedId, Object param) {
        return SqlHelper.getNamespaceSql(session, mappedId, param);
    }

    /**
     * 通过Mapper方法名获取sql
     *
     * @param session
     * @param mappedId
     * @return
     */
    protected String getRunTimeSqlContent(SqlSession session, String mappedId) {
        return SqlHelper.getNamespaceSql(session, mappedId);
    }

    /**
     * 格式化sql
     *
     * @param sql
     *            sql字符串
     * @return 格式化后的sql
     */
    private String formatSql(String sql) {
        return sql == null ? "" : sql.replaceAll("\r|\n+", " ");
    }

    /**
     * Retrieve a single row mapped from the statement key
     *
     * @param <T>
     *            the returned object type
     * @param statement
     * @return Mapped object
     */
    public <T> T selectOne(String statement) {
        return getSession().selectOne(statement);
    }

    /**
     * Retrieve a single row mapped from the statement key and parameter.
     *
     * @param <T>
     *            the returned object type
     * @param statement
     *            Unique identifier matching the statement to use.
     * @param parameter
     *            A parameter object to pass to the statement.
     * @return Mapped object
     */
    public <T> T selectOne(String statement, Object parameter) {
        return getSession().selectOne(statement, parameter);
    }

    /**
     * Retrieve a list of mapped objects from the statement key and parameter.
     *
     * @param <E>
     *            the returned list element type
     * @param statement
     *            Unique identifier matching the statement to use.
     * @return List of mapped object
     */
    public <E> List<E> selectList(String statement) {
        return getSession().selectList(statement);
    }

    /**
     * Retrieve a list of mapped objects from the statement key and parameter.
     *
     * @param <E>
     *            the returned list element type
     * @param statement
     *            Unique identifier matching the statement to use.
     * @param parameter
     *            A parameter object to pass to the statement.
     * @return List of mapped object
     */
    public <E> List<E> selectList(String statement, Object parameter) {
        return getSession().selectList(statement, parameter);
    }

    /**
     * Retrieve a list of mapped objects from the statement key and parameter,
     * within the specified row bounds.
     *
     * @param <E>
     *            the returned list element type
     * @param statement
     *            Unique identifier matching the statement to use.
     * @param parameter
     *            A parameter object to pass to the statement.
     * @param rowBounds
     *            Bounds to limit object retrieval
     * @return List of mapped object
     */
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        return getSession().selectList(statement, parameter, rowBounds);
    }

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects. Eg. Return a of Map[Integer,Author] for
     * selectMap("selectAuthors","id")
     *
     * @param <K>
     *            the returned Map keys type
     * @param <V>
     *            the returned Map values type
     * @param statement
     *            Unique identifier matching the statement to use.
     * @param mapKey
     *            The property to use as key for each value in the list.
     * @return Map containing key pair data.
     */
    public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
        return getSession().selectMap(statement, mapKey);
    }

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects.
     *
     * @param <K>
     *            the returned Map keys type
     * @param <V>
     *            the returned Map values type
     * @param statement
     *            Unique identifier matching the statement to use.
     * @param parameter
     *            A parameter object to pass to the statement.
     * @param mapKey
     *            The property to use as key for each value in the list.
     * @return Map containing key pair data.
     */
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        return getSession().selectMap(statement, parameter, mapKey);
    }

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects.
     *
     * @param <K>
     *            the returned Map keys type
     * @param <V>
     *            the returned Map values type
     * @param statement
     *            Unique identifier matching the statement to use.
     * @param parameter
     *            A parameter object to pass to the statement.
     * @param mapKey
     *            The property to use as key for each value in the list.
     * @param rowBounds
     *            Bounds to limit object retrieval
     * @return Map containing key pair data.
     */
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        return getSession().selectMap(statement, parameter, mapKey, rowBounds);
    }

    /**
     * Retrieve a single row mapped from the statement key and parameter using a
     * {@code ResultHandler}.
     *
     * @param statement
     *            Unique identifier matching the statement to use.
     * @param parameter
     *            A parameter object to pass to the statement.
     * @param handler
     *            ResultHandler that will handle each retrieved row
     * @return Mapped object
     */
    public void select(String statement, Object parameter, ResultHandler handler) {
        getSession().select(statement, parameter, handler);
    }

    /**
     * Retrieve a single row mapped from the statement using a
     * {@code ResultHandler}.
     *
     * @param statement
     *            Unique identifier matching the statement to use.
     * @param handler
     *            ResultHandler that will handle each retrieved row
     * @return Mapped object
     */
    public void select(String statement, ResultHandler handler) {
        getSession().select(statement, handler);
    }

    /**
     * Retrieve a single row mapped from the statement key and parameter using a
     * {@code ResultHandler} and {@code RowBounds}
     *
     * @param statement
     *            Unique identifier matching the statement to use.
     * @param rowBounds
     *            RowBound instance to limit the query results
     * @param handler
     *            ResultHandler that will handle each retrieved row
     * @return Mapped object
     */
    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        getSession().select(statement, parameter, rowBounds, handler);
    }

    /**
     * Execute an insert statement.
     *
     * @param statement
     *            Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the insert.
     */
    public int insert(String statement) {
        return getSession().insert(statement);
    }

    /**
     * Execute an insert statement with the given parameter object. Any
     * generated autoincrement values or selectKey entries will modify the given
     * parameter object properties. Only the number of rows affected will be
     * returned.
     *
     * @param statement
     *            Unique identifier matching the statement to execute.
     * @param parameter
     *            A parameter object to pass to the statement.
     * @return int The number of rows affected by the insert.
     */
    public int insert(String statement, Object parameter) {
        return getSession().insert(statement, parameter);
    }

    /**
     * Execute an update statement. The number of rows affected will be
     * returned.
     *
     * @param statement
     *            Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the update.
     */
    public int update(String statement) {
        return getSession().update(statement);
    }

    /**
     * Execute an update statement. The number of rows affected will be
     * returned.
     *
     * @param statement
     *            Unique identifier matching the statement to execute.
     * @param parameter
     *            A parameter object to pass to the statement.
     * @return int The number of rows affected by the update.
     */
    public int update(String statement, Object parameter) {
        return getSession().update(statement, parameter);
    }

    /**
     * Execute a delete statement. The number of rows affected will be returned.
     *
     * @param statement
     *            Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the delete.
     */
    public int delete(String statement) {
        return getSession().delete(statement);
    }

    /**
     * Execute a delete statement. The number of rows affected will be returned.
     *
     * @param statement
     *            Unique identifier matching the statement to execute.
     * @param parameter
     *            A parameter object to pass to the statement.
     * @return int The number of rows affected by the delete.
     */
    public int delete(String statement, Object parameter) {
        return getSession().delete(statement, parameter);
    }

    /**
     * 截取sql的mapper ID，作为Transaction name
     *
     * @param statement
     *            sql mapper ID
     * @return 截取后的字符串
     */
    private String getDefaultSqlTransectionName(String statement) {
        int index = statement.substring(0, statement.lastIndexOf(".")).lastIndexOf(".");
        return statement.substring(index + 1);
    }

    private SqlSession getSession() {
        return session;
    }

    public void setSession(SqlSession session) {
        this.session = session;
    }

}
