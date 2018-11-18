/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.util;

import com.ningpai.common.util.ConstantUtil;
/**
 * 第三方商品查询辅助Bean
 * @since 2014年5月12日 下午5:02:31
 * @version 2.0
 */
public final class ThirdValueBean {
    public static final String CATELISTCON = "cateManager.htm";
    // 第三方店铺分类Controller
    public static final String CATEMANAGER = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>前往分类列表";
    public static final String QUERYTHIRDCATEFORLIST = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>AJAX查询所有的分类信息For 列表展示";
    public static final String GETALLTHIRDCATE = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>AJAX查询所有的分类信息";
    public static final String SAVETHIRDCATE = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>保存分类图片";
    public static final String SAVETHIRDCATE2 = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>保存商家分类信息";
    public static final String DELTHIRDCATE = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>删除商家分类信息";
    public static final String BATCHDELTHIRDCATE = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>批量删除商家分类信息";
    public static final String UPDATETHIRDCATE = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>更新商家分类信息";
    public static final String FASTUPDATETHIRDCATE = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>快速更新商家分类信息";
    public static final String CHECKTHIRDCATENAME = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>验证分类名称是否可用";
    public static final String CHECKTHIRDDEL = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>验证分类是否可以删除";
    public static final String QUERYTHIRDCATEBYCATEID = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>根据分类ID查询分类信息";
    // END
    public static final String THIRDID = "thirdId";

    public static final String USETHIRDCATIDINCOOKIE = "_useThirdCatIdInCookie";
    public static final String UTF8 = ConstantUtil.UTF;
    public static final String CUST = "cust";
    public static final String GOODSID = "?goodsId=";
    public static final String THIRDGOODSGROUPMANAGER = "thirdGoodsGroupManager.htm";
    public static final String USETHIRDCATNAMEINCOOKIE = "_useThirdCatNameInCookie";

    private ThirdValueBean() {
        super();
    }

}
