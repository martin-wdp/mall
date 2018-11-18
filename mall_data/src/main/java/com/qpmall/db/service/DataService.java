package com.qpmall.db.service;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by yanggd on 2016/1/5.
 */
public interface DataService {

    /**
     * 查询：在索引中查询。查询表（table）中符合查询条件（QueryBuilder）的记录，返回从指定行（form）开始的指定记录条数（size）数据
     * QueryBuilder语句的格式举例:  and,列名,值；or,列名,值；orderby,列名,desc；between,列名,值；from,0,25
     *
     * @param table        要查询的表
     * @param QueryBuilder 查询语句
     * @param From         从第几条开始返回
     * @param Size         返回的条数
     * @return
     */
    public List<Map> IndexQueryBuilderSize(String table, String QueryBuilder, int From, int Size);

    /**
     * 查询：在索引中查询。查询表（table）与字段（field）的值（value）相同的记录，返回从指定行（form）开始的指定记录条数（size）数据
     *
     * @param table
     * @param field
     * @param value
     * @param From
     * @param Size
     * @return
     */
    public List<Map> IndexQuerySize(String table, String field, String value, int From, int Size) ;

    /**
     * 查询：在索引中查询。查询表（table）与字段（field）的值（value）相同的记录，返回前10000条数据
     *
     * @param table 表
     * @param field 字段
     * @param value 值
     */
    public List<Map> IndexQuery(String table, String field, String value) ;

    /**
     * 添加、删除、修改：在数据库中进行
     *
     * @param sql
     * @return
     */
    public int dbUpdate(String sql);

    /**
     * 批量添加、删除、修改：在数据库中进行
     *
     * @param sql
     * @return
     */
    public int[] dbBatchUpdate(final String[] sql) ;

    /**
     * 查询：在数据库中进行。返回自定义类的集合，以下为事例
     * String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = custId";
     * Customer customer = (Customer)dbQueryRowMapper(sql, new BeanPropertyRowMapper(Customer.class));
     *
     * @param sql
     * @param rowMapper
     * @param <T>
     * @return
     */
    public <T> List<T> dbQueryRowMapper(String sql, RowMapper<T> rowMapper);

    /**
     * 查询：在数据库中进行。返回自定义类的集合，以下为事例
     * String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
     * Customer customer = (Customer)queryForObject(sql, new Object[] { custId },  new BeanPropertyRowMapper(Customer.class));
     *
     * @param sql
     * @param args
     * @param rowMapper
     * @param <T>
     * @return
     */
    public <T> List<T> dbQueryArgsRowMapper(String sql, Object[] args, RowMapper<T> rowMapper) ;

    /**
     * 查询：在数据库中进行。返回一列，以下为事例
     * String sql = "SELECT NAME FROM CUSTOMER WHERE CUST_ID = ?";
     * String name = (String)dbQueryForObjectArgsClass(sql, new Object[] { custId }, String.class);
     *
     * @param sql
     * @param args
     * @param var3
     * @param <T>
     * @return
     */
    public <T> T dbQueryForObjectArgsClass(String sql, Object[] args, Class<T> var3);

    /**
     * 查询：在数据库中进行。返回一列，以下为事例
     * String sql = "SELECT NAME FROM CUSTOMER WHERE CUST_ID = custId";
     * String name = (String)dbQueryForObjectClass(sql, String.class);
     *
     * @param sql
     * @param var3
     * @param <T>
     * @return
     */
    public <T> T dbQueryForObjectClass(String sql, Class<T> var3);

    /**
     * 查询：在数据库中进行。返回自定义的类，以下为事例
     * String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = custId";
     * Customer customer = (Customer)dbQueryForObjectRowMapper(sql, new BeanPropertyRowMapper(Customer.class));
     *
     * @param sql
     * @param rowMapper
     * @param <T>
     * @return
     */
    public <T> T dbQueryForObjectRowMapper(String sql, RowMapper<T> rowMapper) ;

    /**
     * 查询：在数据库中进行。返回自定义的类，以下为事例
     * String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
     * Customer customer = (Customer)queryForObject(sql, new Object[] { custId },  new BeanPropertyRowMapper(Customer.class));
     *
     * @param sql
     * @param args
     * @param rowMapper
     * @param <T>
     * @return
     */
    public <T> T dbQueryForObjectArgs(String sql, Object[] args, RowMapper<T> rowMapper);

    /**
     * 查询：在数据库中查询
     *
     * @param sql
     * @return
     */
    public List<Map<String, Object>> dbQueryForList(String sql);
}
