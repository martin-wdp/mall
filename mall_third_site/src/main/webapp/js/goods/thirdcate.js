/**
 * 商家分类JS
 * auther yuankk
*/
$(function () 
{
    /*预加载所有的数据*/
    $.get("getAllThirdCateForList.htm", function (data)
    {
        if (null != data && data.length > 0)
        {
            $(".cls_tb tbody").html("");
            var html = getCateList(data);
            $(".cls_tb tbody").html(html);
        }
        else
        {
            $(".cls_tb tbody").html("<tr><td colspan='6'><center>暂没有符合条件的记录!</center></td></tr>");
        }
        $(".cls_name").each(function () 
        {
            var cur = $(this);
            cur.click(function () 
            {
                cur.next("input").show().focus();
                cur.hide();
            });
        });
        $(".cls_input").blur(function () 
        {
            var v = $(this).val();
            $(this).prev(".cls_name").text(v).show();
            $(this).hide();
        });
    });
    /*当点击搜索按钮的时候*/
    $(".sch_btn").click(function ()
    {
        /*预加载所有的数据*/
        $.get("getAllThirdCateForList.htm?condition=1&searchText=" + $(".sch_text").val(), function (data)
        {
            if (null != data && data.length > 0)
            {
                $(".cls_tb tbody").html("");
                var html = getCateList(data);
                $(".cls_tb tbody").html(html);
                /*处理重复的行*/
                var trs = $(".cls_tb tbody tr");
                var count = 0;
                //定义循环的次数标记
                //循环获取到的行,然后跟当前页面上的行,并标记出现的次数
                for (var i = 0; i < trs.length; i++)
                {
                    count = 0;
                    for (var j = 0; j < $(".cls_tb tbody tr").length; j++)
                    {
                        if ($($(".cls_tb tbody tr")[j]).html() == $(trs[i]).html()) {
                            count++;
                        }
                        /*如果出现的次数大于1次,就remove掉当前循环到的行*/
                        if (count > 1) {
                            $(trs[i]).remove();
                        }
                    }
                }
            }
            else
            {
                $(".cls_tb tbody").html("<tr><td colspan='6'><center>暂没有符合条件的记录!</center></td></tr>");
            }
            $(".cls_name").each(function () 
            {
                var cur = $(this);
                cur.click(function () 
                {
                    cur.next("input").show().focus();
                    cur.hide();
                });
            });
            $(".cls_input").blur(function () 
            {
                var v = $(this).val();
                $(this).prev(".cls_name").text(v).show();
                $(this).hide();
            });
        });
    });
    /*关闭添加的弹出框*/
    $(".closeAddBox").click(function ()
    {
        $(".upCatId").remove();
        $(".oldCatName").remove();
        $(".addThirdCate")[0].reset();
        $(".catParentName").val("所有");
        $(".catparentId").val(0);
        $(".d_wp").show();
        $(".bl_box").hide();
        $(".ui-state-error").removeClass("ui-state-error");
        $(".ui-state-highlight").text("").removeClass("ui-state-highlight");
    });
    /*处理属性菜单*/
    var setting = {
        data : {
            key : { }, simpleData : {
                enable : true 
            }
        },
        callback : {
            onClick : onClick 
        }
    };
    /*点击添加按钮*/
    $(".add_bt").click(function ()
    {
    	$(".boxTitle").html("添加分类");
        $(".addThirdCate").attr("action", "saveThirdCate.htm");
        /*查询商品分类放在树形控件中*/
        $.get("getAllThirdCate.htm", function (data) 
        {
            var zNodes = new Array();
            var node = {
                id : 0, pId : null, name : '所有', open : true 
            };
            zNodes.push(node);
            for (var i = 0; i < data.length; i++) 
            {
                var node = {
                    id : data[i].catId, pId : data[i].catParentId, name : data[i].catName, open : true 
                };
                zNodes.push(node);
            }
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
        $(this).parents(".d_wp").hide();
        $(".bl_box").show();
    });
    /*当分类名称失去焦点的时候验证分类名称是否已经使用*/
    $(".catName").blur(function ()
    {
        if (checkNull($(".catName"), $(".catNameTip")))
        {
            if (checkSpecSymb($(".catName"), $(".catNameTip")))
            {
                if ($(".oldCatName").val() != "")
                {
                    checkExists('checkThirdCateName', 'cateName', $(this), 'catNameTip', 1, "oldCatName");
                }
                else {
                    checkExists('checkThirdCateName', 'cateName', $(this), 'catNameTip', 0, null);
                }
            }
        }
    });
});
/* 处理传递过来的数据,并添加到页面中 */
function getCateList(data)
{
    var html = "";
    for (var i = 0; i < data.length; i++)
    {
        html = html + "<tr class='";
        if (data[i].catGrade == 1) {
            html = html + "fst_tr";
        }
        else {
            html = html + "sec_tr";
        }
        html = html + " grade" + data[i].catGrade + "'>" + "<td><input type='checkbox' name='thirdCateIds' class='thirdCateChk' value='" + data[i].catId + "'></td>" + "<td style='text-align: left; text-indent:";
        if (data[i].catGrade == 1) {
            html = html + "0";
        }
        else {
            html = html + (data[i].catGrade - 1) * 30;
        }
        html = html + "px;'>";
        if (data[i].cateVos.length > 0) {
            html = html + "<a class='close_sec' href='javascript:;' onclick='sec($(this))'></a>";
        }
        html = html + "<span class='cls_name'>" + data[i].catName + "</span><input class='cls_input none' type='text' data-role='" + data[i].catId + "' onkeydown='fastupkeydown(this);' onblur='fastUpdateCate(this);' value='" + data[i].catName + "'></td>" + "<td><img alt='' src='" + data[i].catImg + "' width='70' height='30'></td>" + "<td>" + data[i].catSort + "</td>" + "<td>" + timeStamp2String(data[i].catCreateTime) + "</td>" + "" + "<td>" + "<a class='delete_it' href='javascript:;' onclick='delThirdCate(" + data[i].catId + ")'>删除</a>" + "<a class='modify_it' href='javascript:;' onclick='modifyThirdCate(" + data[i].catId + ",this);'>修改</a>" + "</td>" + "</tr>";
        html = html + getCateList(data[i].cateVos);
    }
    return html;
}
/*树形菜单的点击事件*/
function onClick(event, treeId, treeNode, clickFlag) 
{
    $(".catparentId").val(treeNode.id);
    $(".catParentName").val(treeNode.name);
    $(".menuContent").fadeOut("slow");
}
/*处理时间格式化*/
function timeStamp2String(time)
{
    var date = new Date(parseFloat(time));
    var datetime = new Date();
    datetime.setTime(date);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}
/*删除第三方分类*/
function delThirdCate(catId)
{
    $.get("checkThirdCateDel.htm?thirdCateId=" + catId, function (data)
    {
        if (!data) {
            $(".show_title").text("分类下存在子分类,不可删除!");
            dia(1);
        }
        else {
        	$(".del_cate_on_line").attr("href","delThirdCate.htm?thirdCateId=" + catId);
        	dia(3);
        }
    });
}

/*删除分类信息*/
function delCate(){
	/*跳转到删除的控制器*/
    location.href = "delThirdCate.htm?thirdCateId=" + catId;
}

/*批量删除第三方分类*/
function batchDelThirdCate()
{
    var bool = false;
    var checks = $(".thirdCateChk");
    var checkCate = new Array();
    /*取出所有选中的记录*/
    for (var i = 0; i < checks.length; i++) {
        var e = checks[i];
        if (e.checked == true) {
            bool = true;
            checkCate.push(e);
        }
    }
    /*如果bool=false,表示未选中数据*/
    if (!bool) {
    	 $(".show_title").text("请至少选择一行数据!");
         dia(1);
         return null;
    }
    else
    {
        var bool2 = true;
        for (var i = 0 ; i < checkCate.length; i++)
        {
            $.get("checkThirdCateDel.htm?thirdCateId=" + $(checkCate[i]).val(), function (data)
            {
                /*如果验证的结果为false,表示不可删除*/
                if (!data) {
                    if (bool2) {
                        $(".show_title").text("分类下存在子分类,不可删除!");
                        dia(1);
                    }
                    bool2 = false;
                    return null;
                }
                /*如果循环到最后一个,且标记为true可删除*/
                if (i == checkCate.length && bool2) {
                    dia(2);
                }
            });
        }
    }
}
/*批量删除分类信息*/
function batchDelCate(){
	$("#cateList").attr("action", "batchDelThirdCate.htm").submit();
}

/*快速更新分类信息*/
function fastUpdateCate(obj)
{
    /*如果分类名称为空,就提示不允许更新,否则就允许更新*/
    if ($(obj).val() == "") {
        $(obj).val($(obj).parent().find(".cls_name").text());
        return;
    }
    else
    {
    	var bool = true;
    	/*如果输入的值等于之前的值,就不验证是否存在*/
		if($(obj).val()==$(obj).parent().find(".cls_name").text()){
			bool = true;
		}else{
			var URL="checkThirdCateName.htm?cateName="+$(obj).val();
			$.ajax({
				type: 'post',
				url:URL,
				async:false,
				success: function(data) {
					bool = data;
				}
			});
		}
		/*如果验证结果是true,就可以更新*/
    	if(bool){
    		 $.get("fastUpdateThirdCate.htm?catId=" + $(obj).attr("data-role") + "&catName=" + $(obj).val(), 
    			        function (data)
    			        {
    			            if (data > 0) {
    			                location.href = "cateManager.htm";
    			            }
    		 });
    	}else{
    		$(obj).val($(obj).parent().find(".cls_name").text());
    		$(".show_title").text("分类名称已经存在!");
            dia(1);
    	}
    }
}

/*快速更新时的回车事件*/
function fastupkeydown(obj){
	 if (event.keyCode == 13){  
		 $(obj).blur();
	 }
}
/*显示树形控件*/
function showMenu() 
{
    var selObj = $(".catparentId");
    var businessOffset = $(".catparentId").offset();
    $(".menuContent").css( 
    {
        left : businessOffset.left + 800 + "px", top : businessOffset.top - selObj.outerHeight() + 200 + "px" 
    }).slideDown("fast");
    onBodyDownForArea();
}
/*隐藏树形控件*/
function onBodyDownForArea()
{
    jQuery.fn.isChildAndSelfOf = function (b)
    {
        return (this.closest(b).length > 0);
    };
    $(document).click(function (event)
    {
        if (!($(event.target).isChildAndSelfOf(".menuContent")) && !($(event.target).hasClass("st_choose"))) {
            $(".menuContent").fadeOut("slow");
        };
    });
}
/*当表单提交的时候验证*/
function chkThirdCateForm()
{
	
    var bValid = true;
    bValid = checkSpecSymb($(".catName"), $(".catNameTip")) && bValid;
    bValid = checkRegexp( $(".catSort"), /^[0-9]+$/, "排序必须为正整数." , $(".catSortTip")) && bValid;
    bValid = checkSpecSymb($(".catSeoTitle"), $(".catSeoTitleTip")) && bValid;
    bValid = checkSpecSymb($(".catSeoKeyword"), $(".catSeoKeywordTip")) && bValid;
    bValid = checkSpecSymb($(".catSeoDesc"), $(".catSeoDescTip")) && bValid;
    if(checkSpecSymb($(".catName"), $(".catNameTip"))){
    	 bValid = checkNull($(".catName"), $(".catNameTip")) && bValid;
    }
    if ($(".checkExistsFlag").val() == "0") {
    	$(".catName").addClass("ui-state-error");
		updateTips("名称或编号已经存在!",$(".catNameTip"));
        bValid = false;
    }
    if (bValid) {
        $(".addThirdCate").submit();
        $(".addThirdCate").attr("action","");
    }
}
/*修改第三方分类*/
function modifyThirdCate(catId, obj)
{
    $(".addThirdCate").attr("action", "updateThirdCate.htm");
    /*添加必须的字段*/
    $(".addThirdCate").append("<input type='hidden' name='catId' class='upCatId' value='" + catId + "' />");
    $.get("queryThirdCateByCateId.htm?catId=" + catId, function (data)
    {
        if (null != data)
        {
            $(".addThirdCate").append("<input type='hidden' class='oldCatName' value='" + data.catName + "' />");
            $(".catName").val(data.catName);
            $(".catparentId").val(data.catParentId);
            $(".catSort").val(data.catSort);
            $(".catSeoTitle").val(data.catSeoTitle);
            $(".catSeoKeyword").val(data.catSeoKeyword);
            $(".catSeoDesc").val(data.catSeoDesc);
            $(".boxTitle").html("修改分类");
            $(".d_wp").hide();
            $(".bl_box").show();
            /*查询商品分类放在树形控件中*/
            $.get("getAllThirdCate.htm", function (dataList) 
            {
                /*处理属性菜单*/
                var setting = {
                    data : {
                        key : { }, simpleData : {
                            enable : true 
                        }
                    },
                    callback : {
                        onClick : onClick 
                    }
                };
                var zNodes = new Array();
                var node = {
                    id : 0, pId : null, name : '所有', open : true 
                };
                zNodes.push(node);
                for (var i = 0; i < dataList.length; i++) 
                {
                    if (dataList[i].catId == data.catParentId) {
                        $(".catParentName").val(dataList[i].catName);
                    }
                    if (dataList[i].catId == data.catId) {
                    }else{
                    	var node = 
                        {
                            id : dataList[i].catId, pId : dataList[i].catParentId, name : dataList[i].catName, 
                            open : true 
                        };
                    	zNodes.push(node);
                    }
                }
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            });
        }
        else {
        	$(".show_title").text("发生错误,请重试!");
            dia(1);
        }
    });
}


/*验证分类名称是否已经存在*/
function checkThirdCateExists(url,objName,obj,oldObj){
		if(obj!=oldObj){
			var URL=url+".htm?"+objName+"="+obj;
			$.ajax({
				type: 'post',
				url:URL,
				async:false,
				success: function(data) {
					if(data){
						return data;
					}else{
						return false;
					}
				}
				});
		}else{
			return true;
		}
}

