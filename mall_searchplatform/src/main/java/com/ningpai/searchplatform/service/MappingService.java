package com.ningpai.searchplatform.service;

import com.ningpai.searchplatform.annotation.ESDocObject;
import com.ningpai.searchplatform.annotation.ESField;
import com.ningpai.searchplatform.bean.IESMappingType;
import com.ningpai.searchplatform.client.ESClientManager;
import com.ningpai.searchplatform.enumeration.ESAnalyzer;
import com.ningpai.searchplatform.enumeration.ESFieldType;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * mapping service
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/9 12:49
 */
@Service
public class MappingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MappingService.class);

    /**
     * es transport客户端
     */
    @Autowired
    private ESClientManager clientManager;

    // private TransportClient transportClient;

    /**
     * 想索引中添加一个mapTYpe of1081
     * 
     * @param index
     * @param esMapTypeClass
     * @return
     * @throws IOException
     */
    public boolean buildIndexType(String index, Class<? extends IESMappingType> esMapTypeClass) throws IOException {
        TransportClient transportClient = clientManager.getClient();
        XContentBuilder builder = buildXContentBuilder(index, esMapTypeClass);
        PutMappingResponse response = transportClient.admin().indices().preparePutMapping(index).setType(esMapTypeClass.getSimpleName()).setSource(builder).execute().actionGet();
        return response.isAcknowledged();
    }

    /**
     * 根据IESMappingType 的实现类,生成创建mappingType的 XContentBuilder
     * 
     * @param esMapTypeClass
     * @return
     * @throws IOException
     */
    public XContentBuilder buildXContentBuilder(String index, Class<? extends IESMappingType> esMapTypeClass) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject();

        // builder.startObject().startObject("properties").startObject("fieldName")
        // .field("type", "typeName").field("store", "ifStore").
        // field("indexAnalyzer","analyzerName").field("searchAnalyzer","analyzerName").endObject();
        // 处理对象属性类型
        dealObj(builder, esMapTypeClass.getDeclaredFields());
        builder.endObject();
        return builder;
    }

    /**
     * 根据对象的es属性注解判断是否进行属性处理
     * 
     * @param builder
     * @param subfields
     * @throws IOException
     */
    private void dealObj(XContentBuilder builder, Field[] subfields) throws IOException {
        // 子属性放到该索引下
        builder.startObject("properties");
        for (Field field : subfields) {
            ESField fieldProp = field.getAnnotation(ESField.class);
            if (fieldProp != null) {
                dealField(builder, field, fieldProp);
            }
        }
        builder.endObject();
    }

    /**
     * <p>
     * 处理对象的属性类型
     * </p>
     * 
     * @param builder
     * @param fieldProp
     * @param field
     */
    /*
     * private void dealField(XContentBuilder builder,Field field,ESField
     * fieldProp)throws IOException{ //属性名称 String
     * fieldName=fieldProp.fieldName();
     * if(fieldName==null||"".equals(fieldName)){ fieldName=field.getName(); }
     * //属性类型 String fieldType=fieldProp.fieldType().getTypeValue();
     * if(fieldType==null||"".equals(fieldType)){ fieldType="string"; } //是否索引
     * String store=fieldProp.isStore()?"yes":"no"; //分词器 String
     * analyzer=fieldProp.analyzerType().name();
     * builder.startObject(fieldName).field
     * ("type",fieldType).field("store",store);
     * if(analyzer!=null&&!"".equals(analyzer)){
     * builder.field("indexAnalyzer",analyzer).field("searchAnalyzer",analyzer);
     * } builder.endObject(); }
     */

    /**
     * 处理对象的属性类型
     * 
     * @param builder
     * @param field
     *            属性
     * @param fieldProp
     *            属性注解信息
     * @throws IOException
     */
    private void dealField(XContentBuilder builder, Field field, ESField fieldProp) throws IOException {
        try {
            if (List.class.isAssignableFrom(field.getType()) || field.getType().isArray()) {
                String className = "";
                builder.startObject(field.getName());// 这里如果是startArray就会有问题.
                if (fieldProp.fieldType() == ESFieldType.NESTED) {
                    builder.field("type", ESFieldType.NESTED.getTypeValue());
                }
                Type fc = field.getGenericType();
                if (fc instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) fc;
                    className = pt.getActualTypeArguments()[0].toString().replace("class ", "");
                } else if (field.getType().isArray()) {
                    className = field.getGenericType().toString().replace("class [L", "").replace("/", ".").replace(";", "");
                }

                Class clazz = Class.forName(className);
                // -----------
                // ||clazz.getAnnotation(ESDocObject.class)!=null
                if (IESMappingType.class.isAssignableFrom(clazz) || clazz.getAnnotation(ESDocObject.class) != null) {
                    // XContentBuilder subbuilder =
                    // XContentFactory.jsonBuilder().startObject();
                    // ESMapTypeI tt =(ESMapTypeI)obj;
                    dealObj(builder, clazz.getDeclaredFields());
                } else if (clazz.isPrimitive() || isSimpleType(clazz)) {
                    builder // .startObject()
                    .field("type", ESFieldType.STRING.getTypeValue()).field("index", ESAnalyzer.not_analyzed.name()).field("store", fieldProp.isStore());
                    // .endObject();
                }
                builder.endObject();
            } else if (Map.class.isAssignableFrom(field.getType())) {
                System.out.println("Map:" + field.getName());
            } else {
                // 处理简单对象
                String className = field.getGenericType().toString().replace("class ", "");
                if (isSimpleType(field.getType())) {
                    dealSimpleObjField(builder, field.getName(), fieldProp);
                    return;
                }

                // 如果是复杂的组合类型，继承于ESMapTypeI,则进行递归处理
                try {
                    Class complexClazz = Class.forName(className);
                    if (IESMappingType.class.isAssignableFrom(complexClazz)) {
                        builder.startObject(field.getName());
                        if (fieldProp.fieldType() == ESFieldType.NESTED) {
                            builder.field("type", ESFieldType.NESTED.getTypeValue());
                        }
                        dealObj(builder, complexClazz.getDeclaredFields());
                        builder.endObject();
                    }
                    /*
                     * else if(obj.getClass().isPrimitive()){
                     * //dealSimpleObjField
                     * (Mapbuilder,field.getName(),eSMapType); }
                     */
                } catch (Exception e) {
                    LOGGER.error("创建mapping出错...", e);
                }

            }
        } catch (Exception e) {
            LOGGER.error("创建mapping出错...", e);
        }
    }

    /**
     * 判断是否是简单的对象.
     *
     * @param cls
     * @return
     */
    private static boolean isSimpleType(Class cls) {
        if (cls == String.class || cls == Integer.class || cls == BigDecimal.class || cls == Date.class || cls == int.class || cls == long.class || cls == Long.class) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 处理对象类型的域值,处理已经是最简单对象的field
     */
    public void dealSimpleObjField(XContentBuilder mapbuilder, String fieldName, ESField eSMapType) throws IOException {

        mapbuilder.startObject(fieldName).field("store", eSMapType.isStore()).field("type", eSMapType.fieldType().getTypeValue());
        if (eSMapType.analyzerType() == ESAnalyzer.ansj_auto) {
            mapbuilder.field("indexAnalyzer", ESAnalyzer.ansj_index.name()).field("searchAnalyzer", ESAnalyzer.ansj_query.name());
        } else if (eSMapType.analyzerType() != ESAnalyzer.not_analyzed) {
            mapbuilder.field("indexAnalyzer", eSMapType.analyzerType().name()).field("searchAnalyzer", eSMapType.analyzerType().name());
        } else {
            mapbuilder.field("index", eSMapType.analyzerType().name());
        }
        mapbuilder.endObject();

    }

    /**
     * 验证MapType 是否存在
     * 
     * @param index
     * @param esMapTypeClass
     * @author of1081
     * @return
     */
    public boolean isTypeExists(String index, Class<? extends IESMappingType> esMapTypeClass) {
        return isTypeExists(index, esMapTypeClass.getSimpleName());
    }

    /**
     * 验证类型是否存在
     * 
     * @param index
     *            索引
     * @param typeName
     *            类型名称
     * @return true:存在,false:不存在
     */
    public boolean isTypeExists(String index, String typeName) {
        TypesExistsRequest request = new TypesExistsRequest(new String[] { index }, typeName);
        TypesExistsResponse response = clientManager.getClient().admin().indices().typesExists(request).actionGet();
        return response.isExists();
    }

}
