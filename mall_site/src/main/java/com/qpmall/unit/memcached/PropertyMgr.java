package com.qpmall.unit.memcached;

import java.io.IOException;
import java.util.Properties;
/**
 * 负责读取配置文件
 * @author wdp
 *
 */
public class PropertyMgr {
	private static Properties props = new Properties();
	/**
	 * 单例
	 */
	static{
		/*try {
            String path=(PropertyMgr.class.getResource("/").getPath() + "com/ningpai/web/config/memcachedconf.properties").substring(1);;//"memcachedconf.properties"
			props.load(PropertyMgr.class.getClassLoader()
                    .getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	/**
	 * 获取整个配置
	 * @return
	 */
	public static Properties getProps() {
		return props;
	}
	/**
	 * 根据key获取value
	 * @param key
	 * @return
	 */
	public static String getStringValue(String key){
		return props.getProperty(key);
	}

    public static int getIntValue(String key){
        return Integer.parseInt(props.getProperty(key));
    }
}

