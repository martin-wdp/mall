package com.ningpai.core;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanggd on 2015/12/24.
 */
public class ListSearch<T> {

    /**
     * 在排序后的List中查询:前提是对要搜索的字段排序
     */
    public List<T> SearchOrder(List<T> searchList,String searchFieldName,String searchString)
    {
        if(searchList.size()==0)
        { return new ArrayList<T>(); }

        // 通过类的字节码得到该类中声明的所有属性，无论私有或公有
        Field searchField = null;
        try {
            Type genType = getClass().getGenericSuperclass();
            searchField = searchList.get(0).getClass().getDeclaredField(searchFieldName); // searchField = T.class.getDeclaredField(searchFieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        searchField.setAccessible(true); // 设置访问权限:防止私有属性访问不到

        int ii=-1;
        for(int i = 0; i<searchList.size(); i++){

            Object searchFieldString=new Object();

            try {
                searchFieldString = searchField.get(searchList.get(i));// 得到私有的变量值
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if(searchFieldString.equals(searchString))
            {
                ii=i ;
                break;
            }
        }

        if(ii==-1)
        { return new ArrayList<T>();}

        java.util.List<T> findList=new ArrayList<T>();

        for(int i = ii; i<searchList.size(); i++){

            Object searchFieldString=new Object();

            try {
                searchFieldString = searchField.get(searchList.get(i));// 得到私有的变量值
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if(searchFieldString.equals(searchString))
            { findList.add(searchList.get(i)); }
            else{break;}
        }

        return findList;
    }
}
