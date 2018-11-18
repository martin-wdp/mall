<div
<#if map.pageAdvs??>
    <#list map.pageAdvs as sadv>
        <#if sadv_index == 0>
                class="top_banner" style="width: 100%;
                height: 112px;
                background: url(${(sadv.adverPath)!''}) no-repeat center center;"
        </#if>
    </#list>
</#if>
        >

</div>
<div class="third_header">
    <div class="container pr clearfix">
    <#if map.classBar??>
        <ul style="width:880px; float:left;">
            <#list map.classBar as bar>
                <li><a href="${basePath}/${(bar.url)!''}" target="_blank">${(bar.name)!''}</a></li>
            </#list>
        </ul>
    </#if>
        <div class="sy_search">

            <b></b><#--<input type="text" placeholder="搜索商品"
                          value="<#if map.search??&&map.search.title??>${(map.search.title)?html}</#if>"
                          onblur="searchTitleHead(this);">-->
        </div>
        <div class="third_store">收藏店铺
            <div class="extra_info">
                <div class="extra_rig">
                    <div class="dp_detail">
                        <table>
                            <tr>
                                <td width="125">店铺动态评分</td>
                            </tr>
                            <tr>
                                <td>描述相符：<span>
                                <#if map.comment??&&map.comment.cscoreAvg??>
                                ${(map.comment.cscoreAvg)?string("0.0")}
                                <#else>
                                    暂无评价
                                </#if>

                                </span></td>
                            </tr>
                            <tr>
                                <td>服务态度：<span>
                                <#if map.comment??&&map.comment.attscoteAvg??>
                                ${(map.comment.attscoteAvg)?string("0.0")}
                                <#else>
                                    暂无评价
                                </#if>
                                </span></td>
                            </tr>
                            <tr>
                                <td>发货速度：<span>
                                <#if map.comment??&&map.comment.attscoteAvg??>
                                ${(map.comment.dscoreAvg)?string("0.0")}
                                <#else>
                                    暂无评价
                                </#if>
                                </span></td>
                            </tr>
                        </table>
                    </div>
                    <div class="dp_detail">
                        <table>
                            <tr>
                                <td>店铺服务</td>
                            </tr>
                            <tr>
                                <td>掌柜：${(map.storeInfo.customerName)!''}</td>
                            </tr>

                            <tr>
                                <td>店铺名称：${(map.storeInfo.storeName)!''}</td>
                            </tr>
                            <tr>
                                <td>店铺地址：${(map.storeInfo.companyAddrDel)!''}</td>
                            </tr>

                        </table>
                    </div>
                    <div class="jd_gg pt10">
                        <a href="javascript:void(0)" class="collectstore">收藏店铺</a>
                    </div>
                </div>
                <!--extra_rig-->
            </div>
        </div>
    </div>
</div>