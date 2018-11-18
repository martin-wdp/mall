package com.ningpai.manager.util;

import com.ningpai.util.MyLogger;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mybatis - 获取Mybatis查询sql工具
 *
 * @author liangck
 */
public class SqlHelper {
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    /**
     * 日志
     *
     * */
    public static final MyLogger LOGGER = new MyLogger(SqlHelper.class);

    /**
     * 反射对象，增加对低版本Mybatis的支持
     *
     * @param object
     *            反射对象
     * @return
     */
    public static MetaObject forObject(Object object) {
        return MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
    }

    /**
     * 通过接口获取sql
     *
     * @param mapper
     * @param methodName
     * @param args
     * @return
     */
    public static String getMapperSql(Object mapper, String methodName, Object... args) {
        MetaObject metaObject = forObject(mapper);
        SqlSession session = (SqlSession) metaObject.getValue("h.sqlSession");
        Class mapperInterface = (Class) metaObject.getValue("h.mapperInterface");
        String fullMethodName = mapperInterface.getCanonicalName() + "." + methodName;
        if (args == null || args.length == 0) {
            return getNamespaceSql(session, fullMethodName, null);
        } else {
            return getMapperSql(session, mapperInterface, methodName, args);
        }
    }

    /**
     * 通过Mapper方法名获取sql
     *
     * @param session
     * @param fullMapperMethodName
     * @param args
     * @return
     */
    public static String getMapperSql(SqlSession session, String fullMapperMethodName, Object... args) {
        if (args == null || args.length == 0) {
            return getNamespaceSql(session, fullMapperMethodName, null);
        }
        String methodName = fullMapperMethodName.substring(fullMapperMethodName.lastIndexOf('.') + 1);
        Class mapperInterface = null;
        try {
            mapperInterface = Class.forName(fullMapperMethodName.substring(0, fullMapperMethodName.lastIndexOf('.')));
            return getMapperSql(session, mapperInterface, methodName, args);
        } catch (ClassNotFoundException e) {
            LOGGER.error("",e);
            throw new IllegalArgumentException("参数" + fullMapperMethodName + "无效！");
        }
    }

    /**
     * 通过Mapper接口和方法名
     *
     * @param session
     * @param mapperInterface
     * @param methodName
     * @param args
     * @return
     */
    public static String getMapperSql(SqlSession session, Class mapperInterface, String methodName, Object... args) {
        String fullMapperMethodName = mapperInterface.getCanonicalName() + "." + methodName;
        if (args == null || args.length == 0) {
            return getNamespaceSql(session, fullMapperMethodName, null);
        }
        Method method = getDeclaredMethods(mapperInterface, methodName);
        Map params = new HashMap();
        final Class<?>[] argTypes = method.getParameterTypes();
        for (int i = 0; i < argTypes.length; i++) {
            if (!RowBounds.class.isAssignableFrom(argTypes[i]) && !ResultHandler.class.isAssignableFrom(argTypes[i])) {
                String paramName = "param" + (params.size() + 1);
                paramName = getParamNameFromAnnotation(method, i, paramName);
                params.put(paramName, i >= args.length ? null : args[i]);
            }
        }
        if (args != null && args.length == 1) {
            Object wParams = wrapCollection(args[0]);
            if (wParams instanceof Map) {
                params.putAll((Map) wParams);
            }
        }
        return getNamespaceSql(session, fullMapperMethodName, params);
    }

    /**
     * 通过命名空间方式获取sql
     *
     * @param session
     * @param namespace
     * @return
     */
    public static String getNamespaceSql(SqlSession session, String namespace) {
        return getNamespaceSql(session, namespace, null);
    }

    /**
     * 通过命名空间方式获取sql
     *
     * @param session
     * @param namespace
     * @param params
     * @return
     */
    public static String getNamespaceSql(SqlSession session, String namespace, Object params) {
        Object paramsNew = params;
        paramsNew = wrapCollection(paramsNew);
        Configuration configuration = session.getConfiguration();
        MappedStatement mappedStatement = configuration.getMappedStatement(namespace);
        // TypeHandlerRegistry typeHandlerRegistry =
        // mappedStatement.getConfiguration().getTypeHandlerRegistry();
        BoundSql boundSql = mappedStatement.getBoundSql(paramsNew);
        // List<ParameterMapping> parameterMappings =
        // boundSql.getParameterMappings();
        String sql = boundSql.getSql();
        /*
         * if (parameterMappings != null) { for (int i = 0; i <
         * parameterMappings.size(); i++) { ParameterMapping parameterMapping =
         * parameterMappings.get(i); if (parameterMapping.getMode() !=
         * ParameterMode.OUT) { Object value; String propertyName =
         * parameterMapping.getProperty(); if
         * (boundSql.hasAdditionalParameter(propertyName)) { value =
         * boundSql.getAdditionalParameter(propertyName); } else if (params ==
         * null) { value = null; } else if
         * (typeHandlerRegistry.hasTypeHandler(params.getClass())) { value =
         * params; } else { MetaObject metaObject =
         * configuration.newMetaObject(params); value =
         * metaObject.getValue(propertyName); } JdbcType jdbcType =
         * parameterMapping.getJdbcType(); if (value == null && jdbcType ==
         * null) jdbcType = configuration.getJdbcTypeForNull(); sql =
         * replaceParameter(sql, value, jdbcType,
         * parameterMapping.getJavaType()); } } }
         */
        return formatSql(sql);
    }

    /**
     * 根据类型替换参数 仅作为数字和字符串两种类型进行处理，需要特殊处理的可以继续完善这里
     *
     * @param sql
     * @param value
     * @param jdbcType
     * @param javaType
     * @return
     */
    // private static String replaceParameter(String sql, Object value, JdbcType
    // jdbcType, Class javaType) {
    // String strValue = String.valueOf(value);
    // if (jdbcType != null) {
    // switch (jdbcType) {
    // //数字
    // case BIT:
    // case TINYINT:
    // case SMALLINT:
    // case INTEGER:
    // case BIGINT:
    // case FLOAT:
    // case REAL:
    // case DOUBLE:
    // case NUMERIC:
    // case DECIMAL:
    // break;
    // //日期
    // case DATE:
    // case TIME:
    // case TIMESTAMP:
    // //其他，包含字符串和其他特殊类型
    // case LONGVARCHAR: break;
    // case LONGVARBINARY: break;
    // default:
    // strValue = "'" + strValue + "'";
    //
    //
    // }
    // } else if (Number.class.isAssignableFrom(javaType)) {
    // //不加单引号
    // } else {
    // strValue = "'" + strValue + "'";
    // }
    // return sql.replaceFirst("//?", strValue);
    // }

    /**
     * 获取指定的方法
     *
     * @param clazz
     * @param methodName
     * @return
     */
    private static Method getDeclaredMethods(Class clazz, String methodName) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        throw new IllegalArgumentException("方法" + methodName + "不存在！");
    }

    /**
     * 获取参数注解名
     *
     * @param method
     * @param i
     * @param paramName
     * @return
     */
    private static String getParamNameFromAnnotation(Method method, int i, String paramName) {
        String paramNameNew = paramName;
        final Object[] paramAnnos = method.getParameterAnnotations()[i];
        for (Object paramAnno : paramAnnos) {
            if (paramAnno instanceof Param) {
                paramNameNew = ((Param) paramAnno).value();
            }
        }
        return paramNameNew;
    }

    /**
     * 简单包装参数
     *
     * @param object
     * @return
     */
    private static Object wrapCollection(final Object object) {
        if (object instanceof List) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list", object);
            return map;
        } else if (object != null && object.getClass().isArray()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("array", object);
            return map;
        }
        return object;
    }

    /**
     * 格式化sql
     * 
     * @param sql
     *            sql字符串
     * @return 格式化后的sql
     */
    public static String formatSql(String sql) {
        return sql == null ? "" : sql.replaceAll("\r|\n+", " ");
    }
}