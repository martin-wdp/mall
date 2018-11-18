package com.ningpai.app.util;

import com.ningpai.util.PropertieUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库操作工具。 Created by JYH on 2015/7/7.
 */
public class DBUtil {

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(DBUtil.class);

    /** 定义一个MYSQL链接对象 */
    private static Connection con = null;

    /**
     * 创建jdbc链接
     * 
     * @return Connection
     */
    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                Properties properties = PropertieUtil.readPropertiesFile(DBUtil.class.getClassLoader().getResourceAsStream("com/ningpai/web/config/jdbc.properties"));
                String driver = properties.getProperty("jdbc.driver");
                String url = properties.getProperty("jdbc.url");
                LOGGER.debug("jdbc url===============================" + url);
                String username = properties.getProperty("jdbc.username");
                String password = properties.getProperty("jdbc.password");
                Class.forName(driver).newInstance(); // MYSQL驱动
                con = DriverManager.getConnection(url, username, password); // 链接本地MYSQL
            }
        } catch (Exception e) {
            LOGGER.error("MYSQL ERROR:", e);
        }
        return con;
    }

    /**
     * 执行sql语句，单条执行
     * 
     * @param sql
     *            要执行的sql语句
     */
    public static void excuteUpdateSql(String sql) {
        Connection conn = getConnection();
        PreparedStatement stmt = null; // 创建声明
        try {
            stmt = conn.prepareStatement(sql);

            // 新增一条数据
            stmt.executeUpdate(sql);
            // 提交事务
            conn.commit();
            stmt.close();
        } catch (Exception e) {
            LOGGER.error("MYSQL ERROR:", e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("关闭数据库连接时发生异常:", e);
            }
        }
    }

    /**
     * 批量执行sql语句
     * 
     * @param sqls
     *            要批量执行的sql语句
     */
    public static void excuteBatchUpdateSql(String[] sqls) {
        Connection conn = getConnection();
        PreparedStatement stmt = null; // 创建声明
        try {
            for (String sql : sqls) {
                try {
                    stmt = conn.prepareStatement(sql);
                    // 新增一条数据
                    stmt.executeUpdate(sql);
                    stmt.close();
                } catch(Exception e) {
                    LOGGER.error("批量执行失败:", e);
                }
            }
            // 提交事务
            conn.commit();
        } catch (Exception e) {
            /*try {
                conn.rollback();
            } catch (SQLException e1) {
                LOGGER.error("回滚失败:", e1);
            }*/
            LOGGER.error("批量执行失败:", e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }

                conn.close();
            } catch (SQLException e) {
                LOGGER.error("关闭数据库连接时发生异常:", e);
            }
        }
    }

}
