/*
 * Copyright 2010-2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author NINGPAI-LIH
 * 
 * @since 2014年3月19日下午5:54:40
 */
public final class Pageable<T> implements Serializable {

    private static final long serialVersionUID = -4684429926866096678L;

    private final long total;
    private final List<T> content = new ArrayList<T>();
    private int size = 10;
    private int page = 1;

    /**
     * {@code Pageable}构造函数
     * 
     * @param content
     *            本页内容, 不能为 {@literal null}.
     * @param total
     *            数据总量
     * @param page
     *            当前页数
     * @param size
     *            每页展示数
     */
    public Pageable(List<T> content, long total, int page, int size) {
        if (null == content) {
            throw new IllegalArgumentException("分页数据不能为空!");
        }
        this.content.addAll(content);
        this.total = total;
        this.page = page;
        this.size = size;
    }

    /**
     * 通过给定的内容创建一个新的{@link Pageable}
     * 
     * @param content
     *            不能为 {@literal null}.
     * @param page
     *            当前页数
     * @param size
     *            每页展示数
     */
    public Pageable(List<T> content, int page, int size) {
        this(content, null == content ? 0 : content.size(), page, size);
    }

    /**
     * 返回当期页面的页数
     * 
     * @return 当前页的页数
     */
    public int getNumber() {
        return page;
    }

    /**
     * 返回当前页面数据展示条数
     * 
     * @return 当前页面数据展示条数
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回当前数据的总页数
     * 
     * @return 当前数据的总页数
     */
    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total
                / (double) getSize());
    }

    /**
     * 返回当前页面的数据的数量
     * 
     * @return 当前页面的数据的数量
     */
    public int getNumberOfElements() {
        return content.size();
    }

    /**
     * 返回当前数据的总数
     * 
     * @return 当前数据的总数
     */
    public long getTotalElements() {
        return total;
    }

    /**
     * 返回是否有上一页
     * 
     * @return 是否有上一页
     */
    public boolean hasPreviousPage() {
        return getNumber() > 0;
    }

    /**
     * 返回是否是第一页
     * 
     * @return 是否是第一页
     */
    public boolean isFirstPage() {
        return !hasPreviousPage();
    }

    /**
     * 返回是否有下一页
     * 
     * @return 是否有下一页
     */
    public boolean hasNextPage() {
        return getNumber() + 1 < getTotalPages();
    }

    /**
     * 返回是否是最后一页
     * 
     * @return 是否是最后一页
     */
    public boolean isLastPage() {
        return !hasNextPage();
    }

    /**
     * 返回当期页的数据{@link List}
     * 
     * @return 当前页的数据
     */
    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    /**
     * 在{@link Pageable}是否有数据
     * 
     * @return 是否有数据
     */
    public boolean hasContent() {
        return !content.isEmpty();
    }

    /**
     * 结束页码
     */
    public int getEnd() {
        int end = Math.min(getBegin() + 10, this.getTotalPages());
        if (end <= 0) {
            end = 1;
        }
        return end;
    }

    /**
     * 当前页码
     */
    public int getCurrent() {
        return this.getNumber();
    }

    /**
     * 开始页码
     */
    public int getBegin() {
        return Math.max(1, getCurrent() - 5);
    }

}
