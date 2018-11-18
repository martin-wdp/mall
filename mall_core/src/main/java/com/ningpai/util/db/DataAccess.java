//package com.ningpai.util.db;
//
//import com.ningpai.util.PropertieUtil;
//import net.sf.json.JSONArray;
//import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.search.SearchType;
//import org.elasticsearch.client.Requests;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.ImmutableSettings;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.common.xcontent.XContentBuilder;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.stereotype.Repository;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
//import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
//
///**
// * 数据访问：简单高效的数据访问，利用spring框架本身提供的数据访问功能，直接执行sql获取业务实体
// * 在默认情况下数据的保存、修改和删除在索引和数据库中同时进行，其事务的一致性由此类保证。
// * Created by 杨国栋 on 2015/12/23.
// */
//@Repository("dataAccess")
//public class DataAccess {
//
//    static  JdbcTemplate jdbcTemplate;
//    static  TransportClient transportClient;
//
//    /***
//     * 索引(数据库)
//     */
//    String Transport_Index;
//
//    public DataAccess() {
//
//        Properties properties = PropertieUtil.readPropertiesFile(DataAccess.class.getClassLoader().getResourceAsStream("com/ningpai/core/mybatis/mapper/jdbc.properties"));
//
//        String Transport_Address = properties.getProperty("Transport.Address");
//        int Transport_Port = Integer.valueOf(properties.getProperty("Transport.Port")).intValue();
//        Transport_Index= properties.getProperty("Transport.Index");
//
//        String Transport_clusterName= properties.getProperty("Transport.clusterName");
//        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name",Transport_clusterName).put("client.transport.sniff", true).build();
//
//        transportClient = new TransportClient(settings)
//                .addTransportAddress(new InetSocketTransportAddress(Transport_Address, Transport_Port));
//
//        String jdbc_url = properties.getProperty("jdbc.url");
//        String jdbc_username = properties.getProperty("jdbc.username");
//        String jdbc_password = properties.getProperty("jdbc.password");
//        String jdbc_driver = properties.getProperty("jdbc.driver");
//
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(jdbc_driver);
//        dataSource.setUrl(jdbc_url);
//        dataSource.setPassword(jdbc_password);
//        dataSource.setUsername(jdbc_username);
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    /***
//     * 添加、删除、修改：在数据库中进行
//     * @param sql
//     * @return
//     */
//    public int update(String sql){
//        return jdbcTemplate.update(sql);
//    }
//
//    /***
//     * 批量添加、删除、修改：在数据库中进行
//     * @param sql
//     * @return
//     */
//    public int[] batchUpdate(final String[] sql){
//        return jdbcTemplate.batchUpdate(sql);
//
//    }
//
//    /***
//     * 查询：在数据库中进行。返回自定义类的集合，以下为事例
//     * String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = custId";
//     * Customer customer = (Customer)queryForObject(sql, new BeanPropertyRowMapper(Customer.class));
//     * @param sql
//     * @param rowMapper
//     * @param <T>
//     * @return
//     */
//    public <T> List<T> query(String sql, RowMapper<T> rowMapper) {
//        return jdbcTemplate.query(sql, rowMapper);
//    }
//
//    /***
//     * 查询：在数据库中进行。返回自定义类的集合，以下为事例
//     * String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
//     * Customer customer = (Customer)queryForObject(sql, new Object[] { custId },  new BeanPropertyRowMapper(Customer.class));
//     * @param sql
//     * @param args
//     * @param rowMapper
//     * @param <T>
//     * @return
//     */
//    public <T> List<T> query(String sql,Object[] args, RowMapper<T> rowMapper) {
//        return jdbcTemplate.query(sql, args, rowMapper);
//    }
//
//    /***
//     * 查询：在数据库中进行。返回一列，以下为事例
//     *  String sql = "SELECT NAME FROM CUSTOMER WHERE CUST_ID = ?";
//     *  String name = (String)queryForObject(sql, new Object[] { custId }, String.class);
//     * @param sql
//     * @param args
//     * @param var3
//     * @param <T>
//     * @return
//     */
//    public  <T> T queryForObject(String sql, Object[] args, Class<T> var3) {
//        return jdbcTemplate.queryForObject(sql, args, var3);
//    }
//
//    /***
//     * 查询：在数据库中进行。返回一列，以下为事例
//     *  String sql = "SELECT NAME FROM CUSTOMER WHERE CUST_ID = custId";
//     *  String name = (String)queryForObject(sql, String.class);
//     * @param sql
//     * @param var3
//     * @param <T>
//     * @return
//     */
//    public  <T> T queryForObject(String sql, Class<T> var3) {
//        return jdbcTemplate.queryForObject(sql, var3);
//    }
//
//    /***
//     * 查询：在数据库中进行。返回自定义的类，以下为事例
//     * String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
//     * Customer customer = (Customer)queryForObject(sql, new Object[] { custId },  new BeanPropertyRowMapper(Customer.class));
//     * @param sql
//     * @param args
//     * @param rowMapper
//     * @param <T>
//     * @return
//     */
//    public <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper)
//    {
//        return jdbcTemplate.queryForObject(sql, args, rowMapper);
//    }
//
//    /***
//     * 查询：在数据库中进行。返回自定义的类，以下为事例
//     * String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = custId";
//     * Customer customer = (Customer)queryForObject(sql, new BeanPropertyRowMapper(Customer.class));
//     * @param sql
//     * @param rowMapper
//     * @param <T>
//     * @return
//     */
//    public <T> T queryForObject(String sql,  RowMapper<T> rowMapper)
//    {
//        return jdbcTemplate.queryForObject(sql, rowMapper);
//    }
//
//    /***
//     * 查询：在数据库中查询
//     * @param sql
//     * @return
//     */
//    public  List<Map<String, Object>> queryForList( String sql)
//    {
//        return  jdbcTemplate.queryForList(sql);
//    }
//
//   public   static void main(String[] args) {
//
//        DataAccess dataAccess = new DataAccess();
//
//
//         dataAccess.query("np_goods_category","and,cat_name,离合器;",0,10000);
//
//
//        // dataAccess.dataSynchronize(); 创建索引
//
////       ObjectMapper objectMapper = new ObjectMapper();
////      // objectMapper.writeValueAsString((Object)b.get(0));
////
////       List<Map> aa=   dataAccess.query("np_goods_type", "type_name", "汽车用品");
//    }
//
//    /***
//     * 查询：在索引中查询。查询表（table）中符合查询条件（QueryBuilder）的记录，返回从指定行（form）开始的指定记录条数（size）数据
//     * QueryBuilder语句的格式举例:  and,列名,值；or,列名,值；orderby,列名,desc；between,列名,值；from,0,25
//     * @param table 要查询的表
//     * @param QueryBuilder 查询语句
//     * @param From 从第几条开始返回
//     * @param Size 返回的条数
//     * @return
//     */
//    public List<Map> query(String table,String QueryBuilder,int From,int Size )
//    {
//        List<Map> list=new ArrayList<Map>();
//        // 创建查询索引
//        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch(Transport_Index);
//        searchRequestBuilder.setSearchType(SearchType.QUERY_THEN_FETCH);//
//        // 设置查询索引类型
//        searchRequestBuilder.setTypes(table);
//
//
////         QueryBuilders
////                .boolQuery()
////                .must(QueryBuilders.termQuery("name", "葫芦3033娃"))
////                .must(QueryBuilders.termQuery("home", "山西省太原市7967街道"))
////                .mustNot(QueryBuilders.termQuery("isRealMen", false))
////                .should(QueryBuilders.termQuery("now_home", "山西省太原市"));
////
////        QueryBuilder qb = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("type", "1")).must(QueryBuilders.matchPhraseQuery("name", "小别克老"))
////                .must(QueryBuilders.matchPhraseQuery("name", "小别克老"))
////                .mustNot(QueryBuilders.termQuery("type", "1"))
////                .should(QueryBuilders.termQuery("type", "1"));
////
////
////        final List<Team> list = new ArrayList<Team>();
////
////        SearchRequestBuilder srbTeam = client.prepareSearch(INDEX_NAME)
////                .setTypes("Team")
////                .setSearchType(SearchType.DEFAULT);
////
////        if (StringUtils.isNotEmpty(term)) {
//
////            qbTeam.must(QueryBuilders
////                    .boolQuery()
////                    .should(QueryBuilders.wildcardQuery("name", "*" + term + "*").boost(10f))
////                    .should(QueryBuilders.fuzzyLikeThisQuery("name", "description", "tag").analyzer("ik")
////                            .likeText(term)).boost(0.1f));
////
////            srbTeam.setQuery(qbTeam);
//
//
//
//
//
//
//
//
////            must(QueryBuilders
////                    .boolQuery()
////                    .should(QueryBuilders.wildcardQuery("name", "*" + term + "*").boost(10f))
////                    .should(QueryBuilders.fuzzyLikeThisQuery("name", "description", "tag").analyzer("ik")
////                            .likeText(term)).boost(0.1f));
//
//
//
//            BoolQueryBuilder qbTeam =new BoolQueryBuilder();// QueryBuilders.boolQuery().must(QueryBuilders.termQuery("rowState", 0)).mustNot(QueryBuilders.termQuery("status", 1));
//
//
//           // qbTeam .must(QueryBuilders.matchPhraseQuery("name", "小别克老"));
//
//
//    //   QueryBuilder="and,列名,值;or,列名,值;orderby,列名,desc;between,列名,值;from,0,25";
//
//        String [] aa= QueryBuilder.split(";");
//
//        for(int i=0;i<aa.length;i++)
//        {
//            String [] bb= aa[i].split(",");
//
//            if(bb[0].equals("and")==true  )
//            {
//                qbTeam .must(QueryBuilders.matchQuery(bb[1], bb[2]));
//            }
//            if(bb[0].equals("or")==true )
//            {
//                qbTeam.should(QueryBuilders.matchPhraseQuery(bb[1], bb[2]));
//            }
//        }
//
//
//
//
//
////        QueryBuilder qb1 = QueryBuilders.matchPhraseQuery("name", "小别克老");//不分词  你会发现，使用matchPhraseQuery并未查询出结果，而matchPhrasePrefixQuery查询出的，则是我们需要的结果。
////
////
////        BoolQueryBuilder qb2 = QueryBuilders.boolQuery().must(new QueryStringQueryBuilder("北京").field("body"))
////                .should(new QueryStringQueryBuilder("太多").field("body"));
//
//
//
//
//        searchRequestBuilder.setQuery(qbTeam);
//       // searchRequestBuilder.setQuery(QueryBuilders.termQuery("type", "1"));//查询--Query
//      //  searchRequestBuilder.setPostFilter(FilterBuilders.rangeFilter("price").from(10).to(550.23));//过滤 --Filter
//      //  searchRequestBuilder.addSort("price", SortOrder.DESC); //排序 -- sort
//       // searchRequestBuilder.setFrom(0).setSize(20).setExplain(true);//topN方式
//        searchRequestBuilder.execute().actionGet();//执行
//
//        SearchResponse response = searchRequestBuilder.execute().actionGet();
//
//        SearchHits searchHits = response.getHits();
//        SearchHit[] hits = searchHits.getHits();
//        for (int i = 0; i < hits.length; i++) {
////            SearchHit hit = hits[i];
////            Map result = hit.getSource();
//            list.add( hits[i].getSource());
//        }
//        return list;
//    }
//
//    /***
//     * 查询：在索引中查询。查询表（table）与字段（field）的值（value）相同的记录，返回从指定行（form）开始的指定记录条数（size）数据
//     * @param table
//     * @param field
//     * @param value
//     * @param From
//     * @param Size
//     * @return
//     */
//    public List<Map> query(String table,String field,String value,int From,int Size ){
//
//        List<Map> list=new ArrayList<Map>();
//        // 创建查询索引
//        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch(Transport_Index);
//        // 设置查询索引类型
//        searchRequestBuilder.setTypes(table);
//        // 设置查询类型
//        // 1.SearchType.DFS_QUERY_THEN_FETCH = 精确查询
//        // 2.SearchType.SCAN = 扫描查询,无序
//        // 3.SearchType.COUNT = 默认值
//        searchRequestBuilder.setSearchType(SearchType.COUNT);
//        // 设置查询关键词
//        searchRequestBuilder.setQuery(QueryBuilders.termQuery(field, value));
//        // 设置查询数据的位置,分页用吧
//        searchRequestBuilder.setFrom(From);
//        // 设置查询结果集的最大条数
//        searchRequestBuilder.setSize(Size);
//        // 设置是否按查询匹配度排序
//        searchRequestBuilder.setExplain(true);
//        // 最后就是返回搜索响应信息
//        SearchResponse response = searchRequestBuilder.execute().actionGet();
//
//        SearchHits searchHits = response.getHits();
//        SearchHit[] hits = searchHits.getHits();
//        for (int i = 0; i < hits.length; i++) {
////            SearchHit hit = hits[i];
////            Map result = hit.getSource();
//            list.add( hits[i].getSource());
//        }
//        return list;
//    }
//
//    /***
//     * 查询：在索引中查询。查询表（table）与字段（field）的值（value）相同的记录，返回前10000条数据
//     * @param table 表
//     * @param field 字段
//     * @param value 值
//     */
//    public List<Map> query(String table,String field,String value) {
//        return  query( table, field, value, 0, 10000 );
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    /***
//     * 数据刷新：从数据库刷新到索引
//     */
// void dataSynchronize()
//{
////    String aa ="  SELECT k.`TABLE_NAME`, k.column_name FROM information_schema.table_constraints t JOIN information_schema.key_column_usage k USING (constraint_name,table_schema,table_name) WHERE t.constraint_type='PRIMARY KEY' AND t.table_schema='qpmall'  ORDER BY k.`TABLE_NAME` ";
////
////    List<Map<String, Object>> jsonData=  queryForList(aa);
////
////    for(int i=0;i<jsonData.size();i++)
////    {
////        String table=jsonData.get(i).get("TABLE_NAME").toString();
////        String id=jsonData.get(i).get("column_name").toString();
////
////        List<Map<String, Object>> kk=queryForList("SELECT  * FROM "+ table +" LIMIT  10000 ");
////        createIndex(table,id,kk);
////    }
//
//    //货品（np_goods_info）
//    createIndex("np_goods_info","goods_info_id",queryForList("SELECT  * FROM np_goods_info "));
//    //商品（np_goods）
//    createIndex("np_goods","goods_id",queryForList("SELECT  * FROM np_goods "));
//    //图片（np_goods_image）
//    createIndex("np_goods_image","goods_img_id",queryForList("SELECT  * FROM np_goods_image "));
//
//    //        PutMappingRequestBuilder builder = transportClient.admin().indices().preparePutMapping(Transport_Index);
////        //testType就像当于数据的table
////        builder.setType("qp_auto_style");
//
//}
//
//
//
//    //创建表结构
//    private   void    createMapping(String type)
//    {
//        XContentBuilder mapping = null;
//        try {
//            mapping = jsonBuilder()
//                    .startObject()
//                            // 索引库名（类似数据库中的表）
//                    .startObject(Transport_Index)
//                    .startObject("properties");
//
//            for(int i=0;i<12;i++)
//            {}
//            // 新闻ID
//            jsonBuilder().startObject("id") .field("type", "long").field("store", "yes").field("index","not_analyzed").field("include_in_all","false").endObject()
//
//                    // title
//                    .startObject("titele")
//                    .field("type", "string")
//                    .field("store", "yes")
//                    .field("term_vector","with_positions_offsets")
//                    .field("indexAnalyzer", "ik")
//                    .field("searchAnalyzer", "ik")
//                    .field("include_in_all", "false")
//                    .field("boost", 4.0) // 打分（默认1.0）
//                    .endObject()
//                    .startObject("updatetime")
//                    .field("type", "string")
//                    .field("store", "yes")
//                    .field("include_in_all", "false")
//                    .endObject()
//                    .endObject()
//                    .endObject()
//                    .endObject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        PutMappingRequest mappingRequest = Requests.putMappingRequest("productIndex").type("productIndex").source(mapping);
//        transportClient.admin().indices().putMapping(mappingRequest).actionGet();
//
//
////        XContentBuilder builder= jsonBuilder()
////                .startObject()
////                .startObject("sina")
////                .startObject("properties")
////                .startObject("article_title")
////                .field("type", "string")
////                .field("store", "yes")
////                .field("analyzer","ik")
////                .field("index","analyzed")
////                .endObject()
////                .startObject("article_content")
////                .field("type", "string")
////                .field("store", "no")
////                .field("analyzer","ik")
////                .field("index","analyzed")
////                .endObject()
////                .startObject("article_url")
////                .field("type", "string")
////                .field("store", "yes")
////                .field("index","not_analyzed")
////                .endObject()
////                .endObject()
////                .endObject()
////                .endObject();
////        PutMappingRequest mapping = Requests.putMappingRequest("pages").type("sina").source(builder);
////        transportClient.admin().indices().putMapping(mapping).actionGet();
//
//    }
//
//    //创建索引
//    private void createIndex(String table,String id, List<Map<String, Object>> jsonData )
//    {
//        if(jsonData.size()<=0)
//        {return;}
//
//        for(int i=0;i<jsonData.size();i++)
//        {
//            JSONArray json = JSONArray.fromObject(jsonData.get(i));
//            String jsonStirng=json.get(0).toString();
//
//            String _id=jsonData.get(i).get(id).toString();
//            IndexResponse response = transportClient.prepareIndex(Transport_Index,table)
//                    .setId(_id)//必须为对象单独指定ID
//                    .setSource(jsonStirng)
//                    .execute()
//                    .actionGet();
//
//            // dataAccess.createIndex("qp_auto_style", b_id.get(i).get("auto_style_id_LiYang_ID").toString(), json.get(i).toString());
//        }
//    }
//
//
//    /***
//     * 插入一个对象，并返回这个对象的自增id
//     * @param obj
//     * @return
//     */
//    private <T> int insertObjectAndGetAutoIncreaseId(T obj) {
//
////        String insertSql = "insert into test(name) values (?)";
////        int count = jdbcTemplate.update(insertSql, new PreparedStatementSetter() {
////            @Override
////            public void setValues(PreparedStatement pstmt) throws SQLException {
////                pstmt.setObject(1, "name4");
////            }});
////
////
////
////        final String sql = BeanOperator.getSqlByObject(SqlTypes.INSERT, obj);
////
////        KeyHolder keyHolder = new GeneratedKeyHolder();
////        int autoIncId = 0;
////
////        jdbcTemplate.update(new PreparedStatementCreator() {
////            public PreparedStatement createPreparedStatement(Connection con)
////                    throws SQLException {
////                PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
////                return ps;
////            }
////        }, keyHolder);
////
////        autoIncId = keyHolder.getKey().intValue();
//
//        return 1;//autoIncId;
//    }
//
//}
