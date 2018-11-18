package com.ningpai.searchplatform.client;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * <p>
 * esclient管理类
 * </p>
 *
 * @author liangck
 * @version 1.0
 * @since 15/8/9 13:28
 */
// @Component
public class ESClientManager {

    /**
     * client
     */
    private TransportClient client;

    /**
     * 根据es 主机地址设置client
     * 
     * @param hosts
     *            主机地址字串
     */
    public ESClientManager(String hosts,String clusterName) {
        if(hosts==null||hosts.split(",").length==0)
            throw new NullPointerException("es服务器地址空...");
        if(clusterName==null||"".equals(clusterName))
            throw new NullPointerException("es集群名称为空...");

        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", clusterName).put("client.transport.sniff", true).build();
        client = new TransportClient(settings);
        // 分割成地址数组
        String[] hostArr = hosts.split(",");
        /* 添加 */
        for (String host : hostArr) {
            client.addTransportAddress(new InetSocketTransportAddress(host, 9300));
        }
    }

    /**
     * 获得client
     *
     * @return {@link TransportClient}
     */
    public TransportClient getClient() {
        return client;
    }
    /*
     * static { Settings settings = ImmutableSettings.settingsBuilder()
     * .put("cluster.name", "elasticsearch") .put("client.transport.sniff",
     * true).build(); client = new TransportClient(settings)
     * .addTransportAddress(new InetSocketTransportAddress( "127.0.0.1", 9300));
     * }
     */

}
