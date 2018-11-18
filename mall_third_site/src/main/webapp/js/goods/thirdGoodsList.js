/**
* 第三方商品列表JS
* author IT_kang
*/
function changePageNo(obj){
	/*获取查询的方式标记*/
	var show_flag=$(".show_flag").val();
	if(show_flag==1){
		$(".simple_search").append("<input type='hidden' name='pageNo' value='"+$(obj).attr("data-role")+"' />").submit();
	}else{
		$(".high_search").append("<input type='hidden' name='pageNo' value='"+$(obj).attr("data-role")+"' />").submit();
	}
}

$(function(){
	/*处理属性菜单*/
	var settingForThird = {
	    data : {
	        key : { }, simpleData : {
	            enable : true 
	        }
	    },
	    callback : {
	        onClick : onClickForThird 
	    }
	};
	var settingForNp = {
		    data : {
		        key : { }, simpleData : {
		            enable : true 
		        }
		    },
		    callback : {
		        onClick : onClickForNp
		    }
		};
	 /*查询商品分类放在树形控件中*/
    $.get("getAllThirdCate.htm", function (data) 
    {
        var zNodes = new Array();
        for (var i = 0; i < data.length; i++) 
        {
            var node = {
                id : data[i].catId, pId : data[i].catParentId, name : data[i].catName, open : true 
            };
            zNodes.push(node);
            /*判断条件中选择的是不是当前循环到的分类,如果是,就更新高级查询的输入框为分类名称*/
            if(data[i].catId == $(".third_cate_id").val()){
            	$(".third_cate_name").val(data[i].catName);
            }
        }
        $.fn.zTree.init($("#thirdtreeDemo"), settingForThird, zNodes);
    });
    /*查询所有的签约的分类信息,并添加到树形菜单中显示*/
	$.get("queryAllGrandCateForThird.htm",function(data){
		if (null != data && data.length>0) {
		    var zNodes = new Array();
            for (var i = 0; i < data.length; i++) 
            {
                var node = {
                    id : data[i].catId, pId : data[i].catParentId, name : data[i].catName, open : true 
                };
                zNodes.push(node);
                /*判断条件中选择的是不是当前循环到的分类,如果是,就更新高级查询的输入框为分类名称*/
                if(data[i].catId == $(".np_cate_id").val()){
                	$(".np_cate_name").val(data[i].catName);
                }
            }
            $.fn.zTree.init($("#nptreeDemo"), settingForNp, zNodes);
		}
	});
	//点击重置表单的时候
	$(".reset_high_search").click(function(data){
		$(".np_cate_id").val("");
		$(".third_cate_id").val("");
	});
	
	//点击批量下架的时候
	$(".batch_off").click(function(){
		var ch_goods = $(".ch_goods");
		var bool = 0;
		for(var i = 0;i<ch_goods.length;i++){
			if($(ch_goods[i]).prop("checked")){
				bool=bool-1+2;
			}
		}
		if(bool>0){
			$(".list_form").attr("action","batchDownThirdGoods.html").submit();
		}else{
			$(".show_title").text("请至少选择一行进行操作!");
            $("#select-tip").modal('show');
		}
	});
	
	//点击批量下架的时候
	$(".batch_upload").click(function(){
		var ch_goods = $(".ch_goods");
		var bool = 0;
		for(var i = 0;i<ch_goods.length;i++){
			if($(ch_goods[i]).prop("checked")){
				bool=bool-1+2;
			}
		}
		if(bool>0){
			$(".list_form").attr("action","batchUploadThirdGoods.html").submit();
		}else{
            $(".modal-title").html("操作提示");
			$(".show_title").text("请至少选择一行进行操作!");
            $("#select-tip").modal('show');
		}
	});

    //搜索金额输入框
    $(".iqy_text").bind("keyup",function(){
        $(this).val($(this).val().replace(/\D|^0/g,''));
    }).bind("input propertychange",function(){
        $(this).val($(this).val().replace(/\D|^0/g,''));
    });

    //重置搜索
    $(".rst_btn").click(function(){
        $(".high_search")[0].reset();
        $(".third_cate_id").val("");
        $(".np_cate_id").val("" );
        $(".sm-input").val("");
        $(".np_cate_name").val("");
        $(".third_cate_name").val("");
    });
});

/*树形菜单的点击事件*/
function onClickForThird(event, treeId, treeNode, clickFlag) 
{
    $(".third_cate_id").val(treeNode.id);
    $(".third_cate_name").val(treeNode.name);
    $(".thirdmenuContent").fadeOut("slow");
}
function onClickForNp(event, treeId, treeNode, clickFlag) 
{
    $(".np_cate_id").val(treeNode.id);
    $(".np_cate_name").val(treeNode.name);
    $(".npmenuContent").fadeOut("slow");
}
/*显示树形控件*/
function showChThirdCate(t) 
{
    var selObj = $(t);
    var businessOffset = $(t).offset();
    $(".thirdmenuContent").css( 
    {
        left : businessOffset.left - 120 + "px", top : businessOffset.top - selObj.outerHeight() + 30 + "px"
    }).slideDown("fast");
    onBodyDownForArea();
}
function showChNpCate(t) 
{
    var selObj = $(t);
    var businessOffset = $(t).offset();
    $(".npmenuContent").css( 
    {
        left : businessOffset.left - 120 + "px", top : businessOffset.top - selObj.outerHeight() + 30 + "px"
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
        if (!($(event.target).isChildAndSelfOf(".npmenuContent")) && !($(event.target).hasClass("st_choose"))) {
            $(".npmenuContent").fadeOut("slow");
        };
        if (!($(event.target).isChildAndSelfOf(".thirdmenuContent")) && !($(event.target).hasClass("st_choose"))) {
            $(".thirdmenuContent").fadeOut("slow");
        };
    });
}