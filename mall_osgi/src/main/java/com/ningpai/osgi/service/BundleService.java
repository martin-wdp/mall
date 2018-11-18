package com.ningpai.osgi.service;

import org.osgi.framework.Bundle;

/**
 * BundleService，提供保存、查询、开启、停止以及卸载bundle的服务
 * @Author hehu
 * @date 2015-07-09 13:37:50
 */
public interface BundleService {
    /**
     * 保存bundle
     * @param bundle 要保存的bundle
     */
    void storeBundle(Bundle bundle);

    /**
     * 根据bundleId查询bundle
     * @param bundleId bundle唯一标识
     * @return bundleid对应的bundle
     */
    int getBundleState(Long bundleId);

    /**
     * 根据bundleId开启bundle
     * @param bundleId bundle唯一标识
     */
    void startBundle(Long bundleId);

    /**
     * 根据bundleId停止bundle
     * @param bundleId bundle唯一标识
     */
    void stopBundle(Long bundleId);

    /**
     * 根据bundleId卸载bundle
     * @param bundleId bundle唯一标识
     */
    void uninstallBundle(Long bundleId);
}
