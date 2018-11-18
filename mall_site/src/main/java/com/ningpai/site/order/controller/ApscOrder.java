package com.ningpai.site.order.controller;

import com.ningpai.util.PropertieUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by pl on 2016/2/19.
 * Desc:
 */
public class ApscOrder {

    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    private PreparedStatement pstmt;


    static {
        String path=(ApscOrder.class.getResource("/").getPath() + "com/ningpai/web/config/jdbc.properties");
        try {
            Properties p = PropertieUtil.readPropertiesFile(path.substring(1));
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(p.getProperty("aspc.url"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**查询多条记录
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> findModeResult(String sql, List<Object> params) throws SQLException{
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = con.prepareStatement(sql);
        if(params != null && !params.isEmpty()){
            for(int i = 0; i<params.size(); i++){
                pstmt.setObject(index++, params.get(i));
            }
        }
        rs = pstmt.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int cols_len = metaData.getColumnCount();
        while(rs.next()){
            Map<String, Object> map = new HashMap<String, Object>();
            for(int i=0; i<cols_len; i++){
                String cols_name = metaData.getColumnName(i+1);
                Object cols_value = rs.getObject(cols_name);
                if(cols_value == null){
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 增加、删除、改
     * @param sql
     * @return
     * @throws SQLException
     */
    public boolean updateByPreparedStatement(String sql)throws SQLException{
        boolean flag = false;
        int result = -1;
        pstmt = con.prepareStatement(sql);
        int index = 1;
        /*if(params != null && !params.isEmpty()){
            for(int i=0; i<params.size(); i++){
                pstmt.setObject(index++, params.get(i));
            }
        }*/
        result = pstmt.executeUpdate();
        flag = result > 0 ? true : false;
        return flag;
    }

    /**
     * 释放数据库连接
     */
    public void releaseConn(){
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws SQLException {
        String orderId ="10216022315000001"; //获取后台通知的数据，其他字段也可用类似方式获取
        String respCode ="00"; //获取应答码，收到后台通知了respCode的值一般是00，可以不需要根据这个应答码判断。

        ApscOrder apscOrder=new ApscOrder();

        String sql="select * from opdd where docnum=?";
        List<Object> newlist=new ArrayList<>();
        newlist.add(orderId);
        List<Map<String, Object>> neworderlist = apscOrder.findModeResult(sql, newlist);
        String DocEntry="";
        if (neworderlist.size()>0) {
            DocEntry = neworderlist.get(0).get("DocEntry").toString();

            //String SQL = "SELECT A.*,B.ShortName FROM BCPL8 A,BCPL B WHERE A.DocEntry=B.DocEntry AND A.DatarowID = ? ";
            String SQL = "select * from bcpl8 where purchaseID= ? ";
            List<Object> list=new ArrayList<>();
            list.add(DocEntry);
            List<Map<String, Object>> orderlist = apscOrder.findModeResult(SQL, list);

            String PayStat="";
            String DatarowID="";//根据订单编号换内部编号
            if (orderlist.size()>0) {
                PayStat = orderlist.get(0).get("PayStat").toString();
                DatarowID = orderlist.get(0).get("DatarowID").toString();
                if (PayStat != "0") {
                    Date currentTime = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = formatter.format(currentTime);
                    SQL = "UPDATE BCPL8 SET PayStat = 0,SubKind=1,BankID = '',OrderTime = '" + dateString + "',PayAmount = " + (Double.parseDouble("200")/100) + ",DealID = '',DealTime = '" + dateString + "',Fee = " + (Double.parseDouble("0")) + " WHERE DatarowID = '" + DatarowID + "'  " + "exec [pPayOnline2] '" + DatarowID + "'";
                    if(apscOrder.updateByPreparedStatement(SQL)){
                        //resp.getWriter().print("ok");
                    }
                }else{
                    //resp.getWriter().print("ok");
                }
            }else{
                //resp.getWriter().print("ok");
            }
        }

    }
}
