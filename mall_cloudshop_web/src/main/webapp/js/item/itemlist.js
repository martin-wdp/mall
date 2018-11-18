/**
 * 商品列表js
 * Created by liangck on 15/6/30.
 */


/**
 * 根据参数移步获取商品列表
 * @param pageNo
 * @param pageSize
 * @param url
 * @param itemtype
 */
function getItemsByParams(pageNo,pageSize,url,itemtype){
    $.ajax(
        {
            url:url,
            data:{pageNo:pageNo,pageSize:pageSize},
            dataType:"json",
            type:"post",
            success:function(data){
                if(data.totalPages>0&&data.items!=null){
                    $("#"+itemtype+"items").html("");
                    var infos="";
                    for(var i=0;i<items.length;i++){
                        var item=items[i];
                        infos+="<tr>";
                        infos+="<td><input type='checkbox' value='"+item.numIid+"'></td>";
                        infos+="<td><img alt='' height='50' src='"+item.picUrl+"'></td>";
                        infos+="<td>"+item.title+"</td>";
                        infos+="<td>"+item.numIid+"</td>";
                        infos+="<td>"+item.price+"</td>";
                        infos+="<td>"+item.typeName+"</td>";
                        infos+="<td>"+item.brandName+"</td>";
                        infos+="<td>";
                        infos+="<div class='btn-group'>";
                        infos+="<button type='button' class='btn btn-default'>查看货品</button>";
                        infos+="<button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown'>";
                        infos+="<span class='caret'></span>";
                        infos+="<span class=‘sr-only’>Toggle Dropdown</span>";
                        infos+="</button>";
                        infos+="<ul class='dropdown-menu' role='menu'>";
                        infos+="<li><a href='#'>修改</a></li>";
                        infos+="</ul>";
                        infos+="</div>";
                        infos+="</td>";
                        infos+="</tr>";
                    }
                    $("#"+itemtype+"items").html(infos);
                    /*设置分页信息*/
                    setPageInfo(data,itemtype);
                }
            }
        });
}

/**
 * 设置分页数据
 * @param data ItemPageBean
 * @param itemtype item类型（Inventory 下架， onsale 在售）
 */
function setPageInfo(data,itemtype){
    $("#"+itemtype+"pagefoot").html("");
    //if(data.totalPages>0&&data.items.length>0){
     var foots="<div class='table_pagenav pull-right'>";
            foots+="<div class='meneame'>";
        if(data.pageNo!=1){
            foots+="<a  href='javascript:void(0);' onclick='changeNextPage("+data.pageSize+","+(data.pageNo-1)+")'> 上一页 </a>";
        }else{
            foots+="<span class='disabled'> 上一页 </span>";
        }
        if(data.pageNo!=1){
            foots+="<a  href='javascript:void(0);' onclick='changeNextPage("+data.pageSize+","+(data.pageNo-1)+")'>上一页 </a>";
        }
        if(data.startNo>2){
            foots+="<a  href='javascript:void(0);'  onclick='changeNextPage(${pageBean.pageSize },1)' >1 </a>";
            foots+="<span class='current'> ...</span>";
        }
        for(var j=data.startNo;j<data.endNo;j++){
            if(data.pageNo==j){
                foots+="<span class='current'> ${pageBean.startNo+sta.count-1}</span>";
            }else{
                foots+="<a  href='javascript:void(0);'  onclick='changeNextPage("+data.pageSize+","+j+")' >"+j+" </a>";
            }
        }
        if(data.endNo<data.totalPages){
            foots+="<span class='current'> ...</span>";
            foots+="<a  href='javascript:void(0);'  onclick='changeNextPage("+data.pageSize+","+data.totalPages+")' >"+data.totalPages+"</a>";
        }
        if(data.pageNo!=data.totalPages){
            foots+="<a  href='javascript:void(0);'  onclick='changeNextPage("+data.pageSize+","+(data.pageNo+1)+")'> 下一页 </a>";
        }else{
            foots+="<span class='disabled'> 下一页 </span>";
        }
        foots+="</div>";
        foots+="</div>";
        foots+="<div class='table_ctrl pull-left'>";
        foots+="<div class='table_ctrl pull-left'>";
        foots+="<label class='control-label'>每页显示：</label>";
        foots+="<select class='form-control' onChange='changePage(this);'>";
        foots+="<option value='10' ";
        if(data.pageSize==10){
            foots+="selected='selected'";
        }
        foots+=">10</option>";
        foots+="<option value='15' ";
        if(data.pageSize==15){
            foots+="selected='selected'";
        }
        foots+=">15</option>";
        foots+="<option value='20' ";
        if(data.pageSize==20){
            foots+="selected='selected' ";
        }
        foots+=">20</option>";
        foots+="<option value='50' ";
        if(data.pageSize==50){
            foots+="selected='selected' ";
        }
        foots+=">50</option>";
        foots+="<option vallue='100' ";
        if(pageSize==100){
            foots+="selected='selected' ";
        }
        foots+="></option>";
        foots+="</select>";
        foots+="</div>";
        foots+="<div class='clr'></div>";
        $("#"+itemtype+"pagefoot").html(foots);
    //}
}

$(function(){
    /*加载在售商品列表*/
    getItemsByParams(1,15,"getOnsaleItemInfos.htm","onsale");

});