package com.qpmall.db.service.impl;

import com.qpmall.db.service.DataService;
import com.qpmall.util.PropertieUtil;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yanggd on 2016/1/5.
 */
@Service("dataService")
public class DataServiceImpl  implements DataService {

    static JdbcTemplate jdbcTemplate;
    static TransportClient transportClient;

    /**
     * 索引(数据库)
     */
    String Transport_Index;

    public DataServiceImpl() {

        /*Properties properties = new Properties();*///= com.qpmall.util.PropertieUtil.readPropertiesFile(DataServiceImpl.class.getClassLoader().getResourceAsStream("com/ningpai/core/mybatis/mapper/jdbc.properties"));
        Properties esProperties = PropertieUtil.readPropertiesFile(DataServiceImpl.class.getClassLoader().getResourceAsStream("com/ningpai/web/config/es-hosts.properties"));
        String Transport_Address = esProperties.getProperty("es.hosts");
        int Transport_Port = Integer.valueOf(esProperties.getProperty("es.port")).intValue();
        Transport_Index = esProperties.getProperty("es.index");

        String Transport_clusterName = esProperties.getProperty("es.cluster.name");
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", Transport_clusterName).put("client.transport.sniff", true).build();

        transportClient = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(Transport_Address, Transport_Port));
        Properties properties = PropertieUtil.readPropertiesFile(DataServiceImpl.class.getClassLoader().getResourceAsStream("com/ningpai/web/config/jdbc.properties"));
        String jdbc_url = properties.getProperty("jdbc.url");
        String jdbc_username = properties.getProperty("jdbc.username");
        String jdbc_password = properties.getProperty("jdbc.password");
        String jdbc_driver = properties.getProperty("jdbc.driver");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbc_driver);
        dataSource.setUrl(jdbc_url);
        dataSource.setPassword(jdbc_password);
        dataSource.setUsername(jdbc_username);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 查询：在索引中查询。查询表（table）中符合查询条件（QueryBuilder）的记录，返回从指定行（form）开始的指定记录条数（size）数据
     * QueryBuilder语句的格式举例:  and,列名,值；or,列名,值；orderby,列名,desc；between,列名,值；from,0,25
     *
     * @param table        要查询的表
     * @param QueryBuilder 查询语句
     * @param From         从第几条开始返回
     * @param Size         返回的条数
     * @return
     */
    public List<Map> IndexQueryBuilderSize(String table, String QueryBuilder, int From, int Size) {
        List<Map> list = new ArrayList<Map>();
        // 创建查询索引
        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch(Transport_Index);
        searchRequestBuilder.setSearchType(SearchType.QUERY_THEN_FETCH);//
        // 设置查询索引类型
        searchRequestBuilder.setTypes(table);


//         QueryBuilders
//                .boolQuery()
//                .must(QueryBuilders.termQuery("name", "葫芦3033娃"))
//                .must(QueryBuilders.termQuery("home", "山西省太原市7967街道"))
//                .mustNot(QueryBuilders.termQuery("isRealMen", false))
//                .should(QueryBuilders.termQuery("now_home", "山西省太原市"));
//
//        QueryBuilder qb = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("type", "1")).must(QueryBuilders.matchPhraseQuery("name", "小别克老"))
//                .must(QueryBuilders.matchPhraseQuery("name", "小别克老"))
//                .mustNot(QueryBuilders.termQuery("type", "1"))
//                .should(QueryBuilders.termQuery("type", "1"));
//
//
//        final List<Team> list = new ArrayList<Team>();
//
//        SearchRequestBuilder srbTeam = client.prepareSearch(INDEX_NAME)
//                .setTypes("Team")
//                .setSearchType(SearchType.DEFAULT);
//
//        if (StringUtils.isNotEmpty(term)) {

//            qbTeam.must(QueryBuilders
//                    .boolQuery()
//                    .should(QueryBuilders.wildcardQuery("name", "*" + term + "*").boost(10f))
//                    .should(QueryBuilders.fuzzyLikeThisQuery("name", "description", "tag").analyzer("ik")
//                            .likeText(term)).boost(0.1f));
//
//            srbTeam.setQuery(qbTeam);


//            must(QueryBuilders
//                    .boolQuery()
//                    .should(QueryBuilders.wildcardQuery("name", "*" + term + "*").boost(10f))
//                    .should(QueryBuilders.fuzzyLikeThisQuery("name", "description", "tag").analyzer("ik")
//                            .likeText(term)).boost(0.1f));


        BoolQueryBuilder qbTeam = new BoolQueryBuilder();// QueryBuilders.boolQuery().must(QueryBuilders.termQuery("rowState", 0)).mustNot(QueryBuilders.termQuery("status", 1));


        // qbTeam .must(QueryBuilders.matchPhraseQuery("name", "小别克老"));


        //   QueryBuilder="and,列名,值;or,列名,值;orderby,列名,desc;between,列名,值;from,0,25";

        String[] aa = QueryBuilder.split(";");

        for (int i = 0; i < aa.length; i++) {
            String[] bb = aa[i].split(",");

            if (bb[0].equals("and") == true) {
                qbTeam.must(QueryBuilders.matchQuery(bb[1], bb[2]));
            }
            if (bb[0].equals("or") == true) {
                qbTeam.should(QueryBuilders.matchPhraseQuery(bb[1], bb[2]));
            }
        }


//        QueryBuilder qb1 = QueryBuilders.matchPhraseQuery("name", "小别克老");//不分词  你会发现，使用matchPhraseQuery并未查询出结果，而matchPhrasePrefixQuery查询出的，则是我们需要的结果。
//
//
//        BoolQueryBuilder qb2 = QueryBuilders.boolQuery().must(new QueryStringQueryBuilder("北京").field("body"))
//                .should(new QueryStringQueryBuilder("太多").field("body"));


        searchRequestBuilder.setQuery(qbTeam);
        // searchRequestBuilder.setQuery(QueryBuilders.termQuery("type", "1"));//查询--Query
        //  searchRequestBuilder.setPostFilter(FilterBuilders.rangeFilter("price").from(10).to(550.23));//过滤 --Filter
        //  searchRequestBuilder.addSort("price", SortOrder.DESC); //排序 -- sort
        // searchRequestBuilder.setFrom(0).setSize(20).setExplain(true);//topN方式
        searchRequestBuilder.execute().actionGet();//执行

        SearchResponse response = searchRequestBuilder.execute().actionGet();

        SearchHits searchHits = response.getHits();
        SearchHit[] hits = searchHits.getHits();
        for (int i = 0; i < hits.length; i++) {
//            SearchHit hit = hits[i];
//            Map result = hit.getSource();
            list.add(hits[i].getSource());
        }
        return list;
    }

    /**
     * 查询：在索引中查询。查询表（table）与字段（field）的值（value）相同的记录，返回从指定行（form）开始的指定记录条数（size）数据
     *
     * @param table
     * @param field
     * @param value
     * @param From
     * @param Size
     * @return
     */
    public List<Map> IndexQuerySize(String table, String field, String value, int From, int Size) {

        List<Map> list = new ArrayList<Map>();
        // 创建查询索引
        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch(Transport_Index);
        // 设置查询索引类型
        searchRequestBuilder.setTypes(table);
        // 设置查询类型
        // 1.SearchType.DFS_QUERY_THEN_FETCH = 精确查询
        // 2.SearchType.SCAN = 扫描查询,无序
        // 3.SearchType.COUNT = 默认值
        searchRequestBuilder.setSearchType(SearchType.COUNT);
        // 设置查询关键词
        searchRequestBuilder.setQuery(QueryBuilders.termQuery(field, value));
        // 设置查询数据的位置,分页用吧
        searchRequestBuilder.setFrom(From);
        // 设置查询结果集的最大条数
        searchRequestBuilder.setSize(Size);
        // 设置是否按查询匹配度排序
        searchRequestBuilder.setExplain(true);
        // 最后就是返回搜索响应信息
        SearchResponse response = searchRequestBuilder.execute().actionGet();

        SearchHits searchHits = response.getHits();
        SearchHit[] hits = searchHits.getHits();
        for (int i = 0; i < hits.length; i++) {
//            SearchHit hit = hits[i];
//            Map result = hit.getSource();
            list.add(hits[i].getSource());
        }
        return list;
    }

    /**
     * 查询：在索引中查询。查询表（table）与字段（field）的值（value）相同的记录，返回前10000条数据
     *
     * @param table 表
     * @param field 字段
     * @param value 值
     */
    public List<Map> IndexQuery(String table, String field, String value) {
        return IndexQuerySize(table, field, value, 0, 10000);
    }

    /**
     * 添加、删除、修改：在数据库中进行
     *
     * @param sql
     * @return
     */
    public int dbUpdate(String sql) {
        return jdbcTemplate.update(sql);
    }

    /**
     * 批量添加、删除、修改：在数据库中进行
     *
     * @param sql
     * @return
     */
    public int[] dbBatchUpdate(final String[] sql) {
        return jdbcTemplate.batchUpdate(sql);
    }

    /**
     * 查询：在数据库中进行。返回自定义类的集合，以下为事例
     * String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = custId";
     * Customer customer = (Customer)dbQueryRowMapper(sql, new BeanPropertyRowMapper(Customer.class));
     *
     * @param sql
     * @param rowMapper
     * @param <T>
     * @return
     */
    public <T> List<T> dbQueryRowMapper(String sql, RowMapper<T> rowMapper) {
        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * 查询：在数据库中进行。返回自定义类的集合，以下为事例
     * String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
     * Customer customer = (Customer)queryForObject(sql, new Object[] { custId },  new BeanPropertyRowMapper(Customer.class));
     *
     * @param sql
     * @param args
     * @param rowMapper
     * @param <T>
     * @return
     */
    public <T> List<T> dbQueryArgsRowMapper(String sql, Object[] args, RowMapper<T> rowMapper) {
        return jdbcTemplate.query(sql, args, rowMapper);
    }

    /**
     * 查询：在数据库中进行。返回一列，以下为事例
     * String sql = "SELECT NAME FROM CUSTOMER WHERE CUST_ID = ?";
     * String name = (String)dbQueryForObjectArgsClass(sql, new Object[] { custId }, String.class);
     *
     * @param sql
     * @param args
     * @param var3
     * @param <T>
     * @return
     */
    public <T> T dbQueryForObjectArgsClass(String sql, Object[] args, Class<T> var3) {
        return jdbcTemplate.queryForObject(sql, args, var3);
    }

    /**
     * 查询：在数据库中进行。返回一列，以下为事例
     * String sql = "SELECT NAME FROM CUSTOMER WHERE CUST_ID = custId";
     * String name = (String)dbQueryForObjectClass(sql, String.class);
     *
     * @param sql
     * @param var3
     * @param <T>
     * @return
     */
    public <T> T dbQueryForObjectClass(String sql, Class<T> var3) {
        return jdbcTemplate.queryForObject(sql, var3);
    }

    /**
     * 查询：在数据库中进行。返回自定义的类，以下为事例
     * String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = custId";
     * Customer customer = (Customer)dbQueryForObjectRowMapper(sql, new BeanPropertyRowMapper(Customer.class));
     *
     * @param sql
     * @param rowMapper
     * @param <T>
     * @return
     */
    public <T> T dbQueryForObjectRowMapper(String sql, RowMapper<T> rowMapper) {
        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    /**
     * 查询：在数据库中进行。返回自定义的类，以下为事例
     * String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
     * Customer customer = (Customer)queryForObject(sql, new Object[] { custId },  new BeanPropertyRowMapper(Customer.class));
     *
     * @param sql
     * @param args
     * @param rowMapper
     * @param <T>
     * @return
     */
    public <T> T dbQueryForObjectArgs(String sql, Object[] args, RowMapper<T> rowMapper) {
        return jdbcTemplate.queryForObject(sql, args, rowMapper);
    }

    /**
     * 查询：在数据库中查询
     *
     * @param sql
     * @return
     */
    public List<Map<String, Object>> dbQueryForList(String sql) {
        return jdbcTemplate.queryForList(sql);
    }

    public static void main(String[] args) {

        DataServiceImpl dataAccess = new DataServiceImpl();

        dataAccess.IndexQueryBuilderSize("np_goods_category", "and,cat_name,离合器;", 0, 10000);

       /* dataAccess.dataSynchronize(); //创建索引*/

//       ObjectMapper objectMapper = new ObjectMapper();
//      // objectMapper.writeValueAsString((Object)b.get(0));
//
//       List<Map> aa=   dataAccess.query("np_goods_type", "type_name", "汽车用品");
    }
}