package com.ningpai.common.lucene.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryTermScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;

import com.ningpai.common.lucene.ikanalyzer.org.wltea.analyzer.lucene.IKAnalyzer;
import com.ningpai.common.util.LuceneValueUtil;

/**
 * 
 * LuenceProcess.java
 * 
 * @version ： 1.1
 * 
 * @author ： 苏若年 <a href="mailto:DennisIT@163.com">发送邮件</a>
 * 
 * @since ： 1.0 创建时间: Apr 3, 2013 11:48:11 AM
 * 
 * 
 */
@Service("luceneIkUtil")
public class LuceneIKUtil {

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(LuceneIKUtil.class);

    private static final String CONTEXT = "context";
    private static final String LOGGERINFO1 = "创建索引失败";

    private Directory directory;

    private Analyzer analyzer;

    /**
     * 带参数构造,参数用来指定索引文件目录
     * 
     * @param indexFilePath
     */
    public LuceneIKUtil(String indexFilePath) {
        try {
            directory = FSDirectory.open(new File(indexFilePath));
            analyzer = new IKAnalyzer();
        } catch (IOException e) {
            LOGGER.error("初始化FSDirectory失败！", e);
        }
    }

    /**
     * 默认构造,使用系统默认的路径作为索引
     */
    public LuceneIKUtil() {
        this(LuceneValueUtil.LUCENCEINDEX);
    }

    /**
     * 创建索引 Description：
     * 
     * @author dennisit@163.com Apr 3, 2013
     * @throws Exception
     */
    public void index(String sql) throws Exception {
        // 1.创建IndexWriter
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_47, analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        // 2.把原来的索引文件删除
        indexWriter.deleteAll();

        File file = new File(LuceneValueUtil.LUCENCEINDEX);
        if (file.exists()) {
            delAllFile(LuceneValueUtil.LUCENCEINDEX);
        }
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
        // 下面语句之前就要先创建javademo数据库
        InputStream in = null;
        Properties p;
        try {
            // String root = LuceneIKUtil.class.getResource("/").getPath();
            // String filePath = new File(root).getCanonicalPath();
            // String path = filePath +
            // "/com/ningpai/web/config/jdbc.properties";
            // in = new BufferedInputStream(new FileInputStream(new
            // File(LuceneDirceotry.class.getResource("/").getFile()).getCanonicalPath()+"/com/ningpai/web/config/jdbc.properties"));
            in = LuceneIKUtil.class.getClassLoader().getResourceAsStream("com/ningpai/web/config/jdbc.properties");
            p = new Properties();
            p.load(in);
            String url = p.getProperty("lucene.url");
            // 3.创建Directory
            directory = FSDirectory.open(new File(LuceneValueUtil.LUCENCEINDEX));
            // 4.获取结果集
            Class.forName(p.getProperty("jdbc.driver"));

            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            stmt = conn.createStatement();

            // executeQuery会返回结果的集合，否则返回空值
            rs = stmt.executeQuery(sql);

            // 5.生成索引文件
            Document document;
            while (rs.next()) {
                if (rs.getString("goods_info_name") == null) {
                    continue;
                }
                document = new Document();
                document.add(new Field("id", rs.getString("goods_info_id"), Field.Store.YES, Field.Index.ANALYZED));
                document.add(new Field(CONTEXT, rs.getString("goods_info_name"), Field.Store.YES, Field.Index.ANALYZED));
                indexWriter.addDocument(document);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            LOGGER.error(LOGGERINFO1, e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (conn != null) {
                    conn.close();
                }
                indexWriter.close();

            } catch (IOException e1) {
                LOGGER.error(LOGGERINFO1, e1);
            }
        }
    }

    /**
     * 
     * Description： 添加索引
     * 
     * @author dennisit@163.com Apr 3, 2013
     * @param id
     *            货品Id
     * @param productName
     *            货品名称
     * @return
     */
    public Document addDocument(Integer id, String productName) {
        Document doc = new Document();
        // Field.Index.NO 表示不索引
        // Field.Index.ANALYZED 表示分词且索引
        // Field.Index.NOT_ANALYZED 表示不分词且索引
        doc.add(new Field("id", String.valueOf(id), Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field(CONTEXT, productName, Field.Store.YES, Field.Index.ANALYZED));
        return doc;
    }

    /**
     * 
     * Description： 更新索引
     * 
     * @author dennisit@163.com Apr 3, 2013
     * @param productId
     *            货品Id
     * @param productName
     *            货品名称
     */
    public void update(Long productId, String productName) {
        IndexWriterConfig indexWriterConfig;
        IndexWriter indexWriter;
        Document document;
        Term term;
        try {
            indexWriterConfig = new IndexWriterConfig(Version.LUCENE_47, analyzer);
            indexWriter = new IndexWriter(directory, indexWriterConfig);
            document = addDocument(productId.intValue(), productName);
            term = new Term("id", String.valueOf(productId));
            indexWriter.updateDocument(term, document);
            indexWriter.close();
        } catch (Exception e) {
            LOGGER.error("更新索引失败！", e);
        }
    }

    /**
     * 
     * Description：按照ID进行删除索引
     * 
     * @author dennisit@163.com Apr 3, 2013
     * @param productId
     *            货品Id
     */
    public void delete(Long productId) {
        try {
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_47, analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
            Term term = new Term("id", productId + "");
            indexWriter.deleteDocuments(term);
            indexWriter.close();
        } catch (Exception e) {
            LOGGER.error("删除索引失败！", e);
        }
    }

    /**
     * 
     * Description：查询
     * 
     * @author dennisit@163.com Apr 3, 2013
     * @param param
     *            查询条件
     * @param param1
     *            分页时用
     */
    // public List<Medicine> search(String[] fields,String keyword){
    public Map<String, Object> searcher(String param, int param1, File path) {
        String paramNew = param;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, String> nameMap = new HashMap<String, String>();
        IndexSearcher indexSearcher;
        List<String> result = new ArrayList<String>();
        try {
            // 1.创建索引搜索器,且只读
            @SuppressWarnings("deprecation")
            IndexReader indexReader = IndexReader.open(directory);
            indexSearcher = new IndexSearcher(indexReader);
            // indexSearcher.setDefaultFieldSortScoring();

            // 2.创建查询解析器，这里可以传多个字段，但是我们只按货品名称
            MultiFieldQueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_47, new String[] { CONTEXT }, analyzer);
            String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\"]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(paramNew);
            paramNew = m.replaceAll("").trim();
            Query query = queryParser.parse(paramNew);

            TopFieldCollector tfc = TopFieldCollector
                    .create(Sort.RELEVANCE, 100, false /* fillFields */, true /* trackDocScores */, true /* trackMaxScore */, false /* docsInOrder */);
            indexSearcher.search(query, tfc);
            TopDocs topDocs = tfc.topDocs();
            // 返回前number条记录,按相关度进行排序
            // TopDocs topDocs = indexSearcher.search(query, 100,
            // Sort.RELEVANCE);
            // 信息展示
            // int totalCount = topDocs.totalHits;

            // 3.高亮显示
            /*
             * 创建高亮器,使搜索的结果高亮显示 SimpleHTMLFormatter：用来控制你要加亮的关键字的高亮方式 此类有2个构造方法
             * 1：SimpleHTMLFormatter()默认的构造方法.加亮方式：<B>关键字</B>
             * 2：SimpleHTMLFormatter(String preTag, String
             * postTag).加亮方式：preTag关键字postTag
             */
            Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
            /*
             * QueryScorer QueryScorer
             * 是内置的计分器。计分器的工作首先是将片段排序。QueryScorer使用的项是从用户输入的查询中得到的；
             * 它会从原始输入的单词、词组和布尔查询中提取项，并且基于相应的加权因子（boost factor）给它们加权。
             * 为了便于QueryScoere使用，还必须对查询的原始形式进行重写。 比如，带通配符查询、模糊查询、前缀查询以及范围查询
             * 等，都被重写为BoolenaQuery中所使用的项。
             * 在将Query实例传递到QueryScorer之前，可以调用Query.rewrite
             * (IndexReader)方法来重写Query对象
             */
            Scorer fragmentScorer = new QueryTermScorer(query);
            Highlighter highlighter = new Highlighter(formatter, fragmentScorer);
            Fragmenter fragmenter = new SimpleFragmenter(100);
            /*
             * Highlighter利用Fragmenter将原始文本分割成多个片段。
             * 内置的SimpleFragmenter将原始文本分割成相同大小的片段，片段默认的大小为100个字符。这个大小是可控制的。
             */
            highlighter.setTextFragmenter(fragmenter);

            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            for (ScoreDoc scDoc : scoreDocs) {
                if (scDoc.score < 0.1) {
                    continue;
                }
                Document document = indexSearcher.doc(scDoc.doc);
                String name = document.get(CONTEXT);

                String lighterName = highlighter.getBestFragment(analyzer, CONTEXT, name);
                if (null == lighterName) {
                    lighterName = name;
                }
                nameMap.put(document.get("id"), lighterName);
                result.add(document.get("id"));
            }
        } catch (Exception e) {
            LOGGER.error("索引查询失败！", e);
        }
        resultMap.put("productIds", result);
        resultMap.put("productLightNames", nameMap);
        return resultMap;
    }

    /**
     * delAllFile
     * 
     * @param path
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                // 先删除文件夹里面的文件
                delAllFile(path + "/" + tempList[i]);
                // 再删除空文件夹
                delFolder(path + "/" + tempList[i]);
                flag = true;
            }
        }
        return flag;
    }

    /**
     * delFolder
     * 
     * @param folderPath
     */
    public static void delFolder(String folderPath) {
        try {
            // 删除完里面所有内容
            delAllFile(folderPath);
            String filePath = folderPath;
            java.io.File myFilePath = new java.io.File(filePath);
            // 删除空文件夹
            myFilePath.delete();
        } catch (Exception e) {
            LOGGER.error("删除空文件夹错误", e);
        }
    }

    /**
     * dirceotry
     * 
     * @param title
     * @return
     */
    public Map<String, Object> dirceotry(String title) {
        return this.searcher(title, 100, new File(LuceneValueUtil.LUCENCEINDEX));
    }

    /*
     * public static void main(String[] args) { LuceneIKUtil luceneProcess = new
     * LuenceIKUtil("F:/index"); try { luceneProcess.createIndex(); } catch
     * (Exception e) { e.printStackTrace(); } //修改测试 luceneProcess.update(2,
     * "测试内容", "修改测试。。。");
     * 
     * //查询测试 String [] fields = {"name","function"}; List<Medicine> list =
     * luenceProcess.search(fields,"感冒"); for(int i=0; i<list.size(); i++){
     * Medicine medicine = list.get(i);
     * System.out.println("("+medicine.getId()+")"+medicine.getName() + "\t" +
     * medicine.getFunction()); } //删除测试 //luenceProcess.delete(1);
     * 
     * }
     */
}
