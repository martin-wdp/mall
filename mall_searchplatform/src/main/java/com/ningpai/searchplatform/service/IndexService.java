package com.ningpai.searchplatform.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.searchplatform.bean.ESSource;
import com.ningpai.searchplatform.bean.IESMappingType;
import com.ningpai.searchplatform.client.ESClientManager;
import com.ningpai.searchplatform.request.IndexCreateRequest;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.block.ClusterBlockException;
import org.elasticsearch.indices.IndexClosedException;
import org.elasticsearch.node.NodeClosedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 索引服务
 * </p>
 * <p>
 * es --- rdbms index --- database type --- table document --- record
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/9 12:46
 */
@Service
public class IndexService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(IndexService.class);

    /** elasticsearch client **/
    @Autowired
    private ESClientManager clientManager;
    // private TransportClient transportClient;

    /** mapping service **/
    @Autowired
    private MappingService mappingService;

    /**
     * 创建索引
     * 
     * @param indexRequest
     *            {@link IndexCreateRequest} 索引请求信息
     */
    public void createIndex(IndexCreateRequest indexRequest) {
        TransportClient transportClient = clientManager.getClient();
        // 索引名称
        String index = indexRequest.getIndex();
        String type = indexRequest.getEsMapType().getSimpleName();
        // 文档数据源
        List<ESSource> sources = indexRequest.getSources();
        try {
            // 如果索引存在,则删除索引
            if (isIndexExist(index)) {
                deleteIndex(index);
            }
            // 文档数据不为空
            if (sources != null && !sources.isEmpty()) {
                // 创建索引
                transportClient.admin().indices().prepareCreate(index)
                        .execute().actionGet();
                // 设置field mapping
                if (!mappingService.isTypeExists(index,
                        indexRequest.getEsMapType())) {
                    mappingService.buildIndexType(index,
                            indexRequest.getEsMapType());
                }
                // 批量索引数据
                BulkRequestBuilder bulkRequestBuilder = transportClient
                        .prepareBulk();
                // 遍历文档数据
                for (ESSource source : sources) {
                    IndexRequest request = transportClient
                            .prepareIndex(index, type, source.getId())
                            .setSource(JSON.toJSONString(source.getDocument()))
                            .request();
                    bulkRequestBuilder.add(request);
                }
                // 进行批处理
                BulkResponse bulkResponse = bulkRequestBuilder.execute()
                        .actionGet();
                if (bulkResponse.hasFailures()) {
                    LOGGER.info("bulk createIndex fail... ");
                } else {
                    LOGGER.info("bulk createIndex successful...");
                }
            } else {
                LOGGER.info("the sources is empty createIndex fail...");
            }
        } catch (IndexClosedException e) {
            LOGGER.error("client is closed... error message: " + e.getMessage());
            transportClient.admin().indices().prepareOpen(index).execute()
                    .actionGet();
            throw e;
        } catch (NodeClosedException e) {
            LOGGER.error("node is closed ... e = {} ", e.getMessage());
            throw e;
        } catch (ClusterBlockException e) {
            LOGGER.error("cluster is block ... e= {}", e.getMessage());
            throw e;
        } catch (ElasticsearchException e) {
            LOGGER.error("elastic search throw ... e = {} ", e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.error("elastic search throw ... e = {}New ", e.getMessage());
        }
    }

    /**
     * 删除索引
     * 
     * @param indice
     *            索引
     * @return
     */
    public boolean deleteIndex(String indice) {
        TransportClient transportClient = clientManager.getClient();
        transportClient.admin().indices().prepareDelete(indice).execute()
                .actionGet();
        return true;
    }

    /**
     * 验证索引是否已经存在
     * 
     * @param indice
     *            索引
     * @return
     */
    public boolean isIndexExist(String indice) {
        TransportClient transportClient = clientManager.getClient();
        IndicesExistsRequest existsRequest = new IndicesExistsRequest(indice);
        IndicesExistsResponse response = transportClient.admin().indices()
                .exists(existsRequest).actionGet();
        return response.isExists();
    }

    /**
     *
     * 新增一个doc文档
     * 
     * @param index
     *            文档所在索引
     * @param typeName
     *            文档所属类型
     * @param docId
     *            文档ID
     * @param iesMappingType
     *            文档数据
     */
    public void addDoc(String index, String typeName, String docId,
            IESMappingType iesMappingType) {
        clientManager.getClient().prepareIndex(index, typeName, docId)
                .setSource(JSON.toJSONString(iesMappingType)).execute()
                .actionGet();
    }

    /**
     * 批量添加同一种类型的文档
     * @param index 索引
     * @param typeName 类型
     * @param esMappingTypes 文档数据
     */
    public void batchAddDoc(String index,String typeName,IESMappingType... esMappingTypes){
        BulkRequestBuilder requestBuilder=clientManager.getClient().prepareBulk();
        for (IESMappingType mappingType:esMappingTypes){
            requestBuilder.add(clientManager.getClient().prepareIndex(index,typeName,mappingType.generateDocId()).setSource(JSON.toJSONString(mappingType)));
        }
        requestBuilder.execute().actionGet();
    }

    
    /**
     * 批量添加同一类型的文档
     * @param index 索引
     * @param typeName 类型
     * @param esMappingTypes 文档数据
     */
    public void batchAddDoc(String index,String typeName,List<? extends IESMappingType> esMappingTypes){
        BulkRequestBuilder requestBuilder=clientManager.getClient().prepareBulk();
        for (IESMappingType mappingType:esMappingTypes){
            requestBuilder.add(clientManager.getClient().prepareIndex(index,typeName,mappingType.generateDocId()).setSource(JSON.toJSONString(mappingType)));
        }
        requestBuilder.execute().actionGet();
    }

    /**
     * 更新DOC文档 更新该doc的所有数据
     * 
     * @param index
     *            文档所在索引
     * @param typeName
     *            文档所属类型
     * @param docId
     *            文档ID
     * @param iesMappingType
     *            文档数据(会全部更新)
     */
    public void updateDoc(String index, String typeName, String docId,
            IESMappingType iesMappingType) {
        // UpdateResponse response=
        clientManager.getClient().prepareUpdate(index, typeName, docId)
                .setDoc(JSON.toJSONString(iesMappingType)).execute()
                .actionGet();
        // res.
    }

    /**
     * 批量更新文档信息
     * @param index 索引
     * @param typeName 类型名称
     * @param mappingTypes 文档数据
     */
    public void batchUpdateDoc(String index,String typeName,IESMappingType... mappingTypes){
        BulkRequestBuilder requestBuilder=clientManager.getClient().prepareBulk();
        for (IESMappingType mappingType:mappingTypes){
            requestBuilder.add(clientManager.getClient().prepareUpdate(index,typeName,mappingType.generateDocId()).setDoc(JSON.toJSONString(mappingType)));
        }
        requestBuilder.execute().actionGet();
    }

    /**
     * 批量更新文档信息
     * @param index 索引
     * @param typeName 类型名称
     * @param mappingTypes 文档数据
     */
    public void batchUpdateDoc(String index,String typeName,List<? extends IESMappingType> mappingTypes){
        BulkRequestBuilder requestBuilder=clientManager.getClient().prepareBulk();
        for (IESMappingType mappingType:mappingTypes){
            requestBuilder.add(clientManager.getClient().prepareUpdate(index,typeName,mappingType.generateDocId()).setDoc(JSON.toJSONString(mappingType)));
        }
        requestBuilder.execute().actionGet();
    }

    /**
     * 删除单个doc
     * 
     * @param index
     *            文档所在索引
     * @param typeName
     *            文档所属类型
     * @param docId
     *            文档ID
     */
    public void deleteDoc(String index, String typeName, String docId) {
        clientManager.getClient().prepareDelete(index, typeName, docId)
                .execute().actionGet();
    }

    /**
     * 批量删除文档
     * 
     * @param index
     *            文档所在索引库
     * @param type
     *            文档所属类型
     * @param docIds
     *            文档ID(一至多个)
     */
    public void batchDeleteDoc(String index, String type, String... docIds) {
        BulkRequestBuilder requestBuilder = clientManager.getClient()
                .prepareBulk();
        for (String docId : docIds) {
            requestBuilder.add(clientManager.getClient().prepareDelete(index,
                    type, docId));
        }
        requestBuilder.execute().actionGet();
    }
    /**
     * 批量删除文档
     * 
     * @param index
     *            文档所在索引库
     * @param type
     *            文档所属类型
     * @param docIds
     *            文档ID(一至多个)
     */
    public void batchDeleteDocForList(String index, String type, List<Long> docIds) {
        BulkRequestBuilder requestBuilder = clientManager.getClient()
                .prepareBulk();
        for (Long docId : docIds) {
            requestBuilder.add(clientManager.getClient().prepareDelete(index,
                    type, String.valueOf(docId)));
        }
        requestBuilder.execute().actionGet();
    }
    
}
