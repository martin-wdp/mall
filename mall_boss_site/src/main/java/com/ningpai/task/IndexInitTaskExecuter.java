package com.ningpai.task;

import com.ningpai.goods.service.GoodsElasticSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;

/**
 * <p>
 *     初始化创建索引
 * </p>
 * @author liangck
 * @version 1.0
 * @since 15/9/15 19:29
 */
public class IndexInitTaskExecuter {

    /**
     * 记录日志工具
     */
    private static final Logger LOGGER= LoggerFactory.getLogger(IndexInitTaskExecuter.class);


    /** 配置的线程池 **/
    @Resource(name = "threadPool")
    private ThreadPoolTaskExecutor taskExecutor;

    /** 商品es service **/
    @Resource(name = "GoodsElasticSearchService")
    private GoodsElasticSearchService goodsElasticSearchService;

    /**
     * 初始化索引
     */
    public void init(){
        taskExecutor.execute(new InitIndexTask());
    }

    /** 初始化索引 **/
    private class InitIndexTask implements Runnable{
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p/>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            LOGGER.info("异步初始化索引...");
            goodsElasticSearchService.createGoodsIndexToEs1();
            LOGGER.info("异步初始化索引完成...");
        }

    }

}
