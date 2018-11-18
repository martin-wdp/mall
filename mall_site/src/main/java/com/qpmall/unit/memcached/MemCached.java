package com.qpmall.unit.memcached;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.qpmall.util.PropertieUtil;

/**
 * memcached缓存工具类
 *
 * @author  wdp
 */
public class MemCached {

    protected static MemCachedClient mcc = new MemCachedClient();

    protected static MemCached memCached = new MemCached();
    private static Properties props = new Properties();

    static {
        String path=("/"+MemCached.class.getResource("/").getPath() + "com/ningpai/web/config/memcachedconf.properties").substring(1);
        try {
            //System.out.println("path : " + path);
            props = PropertieUtil.readPropertiesFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String MemCachedIp = props.getProperty("MemCachedIp");
        String MemCachedPort = props.getProperty("MemCachedPort");
        // 服务器列表和其权重
        String[] servers = { MemCachedIp + ":" + MemCachedPort };

        //String[] servers = {"123.57.59.50:11211"};/*测试用*/
        Integer[] weights = { 3 };/*权重*/

        // 获取socke连接池的实例对象
        SockIOPool pool = SockIOPool.getInstance();

        // 设置服务器信息
        pool.setServers(servers);
        pool.setWeights(weights);

        // 设置初始连接数、最小和最大连接数以及最大处理时间
        pool.setInitConn(Integer.valueOf(props.getProperty("initconn")));
        //pool.setInitConn(50);
        pool.setMinConn(Integer.valueOf(props.getProperty("minconn")));
        //pool.setMinConn(50);
        pool.setMaxConn(Integer.valueOf(props.getProperty("maxconn")));
        //pool.setMaxConn(5000);
        pool.setMaxIdle(Integer.valueOf(props.getProperty("maxidle")));
        //pool.setMaxIdle(3600000);

        // 设置主线程的睡眠时间
        pool.setMaintSleep(Integer.valueOf(props.getProperty("maintsleep")));
        //pool.setMaintSleep(30);

        // 设置TCP的参数，连接超时等
        //pool.setNagle(false);
        pool.setSocketTO(Integer.valueOf(props.getProperty("socketto")));
        //pool.setSocketTO(3000);
        pool.setSocketConnectTO(Integer.valueOf(props.getProperty("socketconnectto")));
        //pool.setSocketConnectTO(0);

        // 初始化连接池
        pool.initialize();

        // 压缩设置，超过指定大小（单位为K）的数据都会被压缩
		/*
		 * mcc.setCompressEnable( true );
		 *   //mcc.set mcc.setCompressThreshold( 64
		 * * 1024 );
		 */
    }

    private MemCached() {}

    /**
     * 获取唯一实例.
     *
     * @return
     */
    public static MemCached getInstance() {
        return memCached;
    }

    /**
     * 添加一个指定的值到缓存中.
     *
     * @param key
     * @param value
     * @return
     */
    public boolean add(String key, Object value) {
        return mcc.add(key, value);
    }

    /**
     * 添加一个指定的值到缓存中.
     * @param key
     * @param value
     * @param expiry 过期时间
     * @return
     */
    public boolean add(String key, Object value, Date expiry) {
        return mcc.add(key, value, expiry);
    }

    /**
     * 更新指定的值到缓存中
     * @param key
     * @param value
     * @return
     */
    public boolean replace(String key, Object value) {
        return mcc.replace(key, value);
    }

    /**
     * 从缓存中删除指定的值
     * @param key
     */
    public void remove(String key) {
        mcc.delete(key);
        mcc.flushAll();
    }

    /**
     * 通过键替换键对应的值
     * @param key
     * @param value
     * @param expiry
     * @return
     */
    public boolean replace(String key, Object value, Date expiry) {
        return mcc.replace(key, value, expiry);
    }

    /**
     * 根据指定的关键字获取对象.
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return mcc.get(key);
    }

    public static void main(String[] args) {
        Date date = new Date();
        long ss = date.getTime();
        MemCached cache = MemCached.getInstance();
        cache.add("wdp_data", "{json}");
        if (cache.get("wdp_data") != null) {
            System.out.println(cache.get("wdp_data") );
        }
    }
}
