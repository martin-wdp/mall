function selectGroupProduct(groupId, pageNo) {
    var productNo="";
    if($("#s_product_no").val()!=''){
        productNo="&productNo="+$("#s_product_no").val();
    }
    var productName="";
    if($("#s_product_name").val()!=''){
        productName="&productName="+$("#s_product_name").val();
    }
    var brandId = '';
    if($("#goodsBrandId").val()!=''){
    	brandId = '&brandId='+$("#goodsBrandId").val();
    }
    var goodsCateId = '';
    if($("#threeCateId").val()!=''){
    	goodsCateId="&catId="+$("#threeCateId").val();
    }
    //var haveStock = '';
    //if($("#haveStock").val()!=''){
    //	haveStock="&haveStock="+$("#haveStock").val();
    //}
    /*AJAX查询所有的货品列表*/
    $.get("queryProductForCoupon.htm?CSRFToken="+$("#CSRFToken").val()+"&groupId=&pageNo="+pageNo+"&pageSize=10"+productNo+productName+brandId+goodsCateId,
        function (data)
        {
            var list = data.list;
            var productListHtml = "";
            for (var i = 0; i < list.length; i++) {
                //+ "<td class='tc'>" + (i - 1 + 2) + "</td>"
                productListHtml += "<tr>";
                productListHtml += "<td class='tc'>" + "<input type='checkbox' class='productId' name='productId' value='" + list[i].goodsInfoId + "'/></td>";
                productListHtml += '<td><img height="50" alt="" src="'+list[i].goodsInfoImgId+'"></td>';

                productListHtml += "<td class='tc' title='"+list[i].goodsInfoItemNo+"'>" + list[i].goodsInfoItemNo + "</td>" ;
                productListHtml += "<td class='tc' title='"+list[i].goodsInfoName+"'>" + list[i].goodsInfoName + "</td>" ;
                productListHtml += "<td>";
                if (list[i].specVo.length > 0) {
                    for (var k = 0; k < list[i].specVo.length; k++) {
                        productListHtml += list[i].specVo[k].spec.specName;
                        productListHtml += ":" + list[i].specVo[k].specValueRemark + "<br/>";
                    }
                }
                productListHtml += "</td>";
                productListHtml += "<td>"+list[i].catName+"</td>";
                productListHtml += "<td>"+list[i].brandName+"</td>";
                productListHtml += "</tr>";
            }
            $("#productAddList tbody").html(productListHtml);
            $("input[type=button]").button();
            /*添加页脚*/
            $("#pageFoot").html("");
            var foot =  "";
            if(data.pageNo==1) {
                foot += '<span class="disabled"> 上一页 </span>';
            } else {
                foot += "<a href='javascript:void(0)' onclick='selectGroupProduct(" + groupId + "," + data.prePageNo + ")'>上一页</a>";
            }
            var i = 1;
            if(data.startNo>1){
            	foot = foot + "<a onclick='selectGroupProduct(" + groupId + ",1)' href='javascript:void(0)'>1</a>";
            	foot = foot + '<span class="current">... </span>';
            }
            for (var l = data.startNo; l <= data.endNo; l++)
            {
                if ((i - 1 + data.startNo) == data.pageNo)
                {
                    foot = foot + '<span class="current"> '+data.pageNo+' </span>';
                }
                else
                {
                    foot = foot + "<a onclick='selectGroupProduct(" + groupId + "," + (i - 1 + data.startNo) + ")' href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</a>";
                }
                i++;
            }
            if(data.endNo<data.totalPages){
            	foot = foot + '<span class="current">... </span>';
            	foot = foot + "<a onclick='selectGroupProduct(" + groupId + ","+data.totalPages+")' href='javascript:void(0)'>"+data.totalPages+"</a>";
            }
            foot = foot + "<a onclick='selectGroupProduct(" + groupId + "," + data.nextPageNo +  ")' href='javascript:void(0)'>下一页</a>" ;
            /*添加tfoot分页信息*/
            $("#pageFoot").html(foot);
        });
}
/**
 * 弹框展示货品选择框
 * @param groupId
 */
function chooseProduct(groupId) {
    selectGroupProduct(groupId,1);
    $('#chooseGoods').modal('show');
}

function submitSaveGroupProductForm() {
    var checkboxs = $("input[name=productId]");
    var oneSelect = false;
    for(var j = 0; j < checkboxs.length; j++) {
        if($(checkboxs[j]).is(':checked')==true) {
            oneSelect = true;
        }
    }
    if(!oneSelect) {
        showTipAlert("请至少选择一条记录！");
        return;
    }
    $("#saveGroupReleProduct").submit();
}