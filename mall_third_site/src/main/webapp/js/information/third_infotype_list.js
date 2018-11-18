/**
 * 文章栏目管理
 * Created by liangck on 15/5/28.
 */

/*树形控件数组*/
var map=new Array();
var basePath=$("#basePath").val();

/**
 * ajax
 * @param pageNo
 * @param pageSize
 */
function doAjaxForCate(pageNo,pageSize){

    $.get("queryThirdInfoTypeVoList.htm?pageNo=" + pageNo + "&pageSize=" + pageSize + "&searchText=" + $("#searchText").val(),
        function(data)
        {
            /*首先把内容置空*/
            $("#treetable").html("");
            /*分页*/
            if(data.list.length>0){
                var foot="<div class='ops-left'><button class='btn muilty-delete-btn btn-primary btn-xs' onclick='javascript:batchDelCate(\"infoTypeIds\");' type='button'>批量删除</button></div>";
                foot+="<div class='ops-right'><nav><ul class='pagination'>";
                foot+="<li ";
                if(data.pageNo<=1){
                    foot+="class='disabled'";
                }
                foot+="><a ";
                if(data.pageNo>1){
                    foot+="onclick='changePageNo(this);'";
                }
                foot+="href='javascript:;' aria-label='Previous' data-role='"+data.prePageNo+"'>";
                foot+="<span aria-hidden='true'>&laquo;</span></a></li>";
                if(data.startNo>1){
                    foot+="<li><a href='javascript:void(0);' onclick='changePageNo(this);' data-role='1'>1</a></li>...";
                }
                for(var i=data.startNo;i<=data.endNo;i++){
                    foot+="<li ";
                    if(i==data.pageNo){
                        foot+="class='active'";
                    }
                    foot+="><a href='javascript:;' ";
                    if(i!=data.pageNo){
                        foot+="onclick='changePageNo(this);'";

                    }
                    foot+=" data-role='"+i+"'>"+i+"</a></li>";
                }
                if(data.totalPages>data.endNo){
                    foot+="...<li><a href='javascript:void(0);' onclick='changePageNo(this);' data-role='"+data.totalPages+"'>"+data.totalPages+"</a></li>";
                }
                foot+="<li ";
                if(data.pageNo >= data.endNo){
                    foot+="class='disabled'";
                }
                foot+="><a ";
                if(data.pageNo<data.endNo){
                    foot+="onclick='changePageNo(this);'";
                }
                foot+=" data-role='"+data.nextPageNo+"' href='javascript:;' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li></ul></nav></div>";
                $(".footer-operation").html(foot);


                /*进行递归*/
                var html=getCateList(data.list,0);

                /*渲染表哥*/
                /*把计算出来的结果放在表格的BODY中*/
                $("#treetable").html(html);

                /*初始化樹*/
                initTree(map);
            }else{
                $(".footer-operation").html("<center>暂无数据</center>");
            }
        }
    );

}

/**
 * 获取AJAX返回的分类列表，并进行反递归
 * @param data 列表数据
 * @param pgrade 父级序号
 */

/*当前节点的父节点在树列表中的索引，第一个为0*/
var index=0;
function getCateList(data,pgrade)
{
    var html="";
    for (var i = 0; i < data.length; i++)
    {
        var infot=data[i];
        html+="<tr>";
        html+="<td><input type='checkbox' name='infoTypeIds' value='"+infot.infoTypeId+"'/></td>";
        html+="<td>"+infot.name+"</td>";
        html+="<td>"+(infot.seoDesc==null?"暂无描述":infot.seoDesc)+"</td>";
        html+="<td>"+infot.sort+"</td>";
        if(infot.isShow=='0'){
            html+="<td><i class='glyphicon glyphicon-remove'></i></td>";
        }else{
            html+="<td><i class='glyphicon glyphicon-ok'></i></td>";
        }
        html+="<td>";
        html+="<div class='btn-group'>";
        html+="<button type='button' class='btn modify-infotype-btn btn-primary btn-sm' data-toggle='modal' data-target='#addColumn' data-key='"+infot.infoTypeId+"'>修改</button>";
        html+="<button type='button' class='btn btn-primary btn-sm dropdown-toggle' data-toggle='dropdown' aria-expanded='false'>";
        html+="<span class='caret'></span>";
        html+="</button>";
        html+="<ul class='dropdown-menu' role='menu'>";
        html+="<li><a href='javascript:;' onclick='delGoodsBrand("+infot.infoTypeId+")' class='delete-btn' data-key='"+infot.infoTypeId+"'>删除</a></li>";
        html+="</ul>";
        html+="</div>";
        html+="</td>";
        html+="</tr>";
        /*放入树形控件组*/
        map.push(pgrade);

        /*当前元素在树索引中的位置向前推一*/
        index=index+1;
        /*递归子分类列表*/
        if(infot.childs!=null&&infot.childs.length>0){
            html = html + getCateList(infot.childs,index);
            /*将当前元素在树索引的位置向前推子节点的个数位*/
            //index+=infot.childs.length;
        }
    }
    return html;

    /*$("#list tbody tr").each(function ()
     {

     });*/
}

function initTree(map){
    /*初始化树形控件*/
    $("#treetable").jqTreeTable(map, {
        openImg: basePath+"/css/TreeTable/tv-collapsable.gif",
        shutImg: basePath+"/css/TreeTable/tv-expandable.gif",
        leafImg: basePath+"/css/TreeTable/tv-item.gif",
        lastOpenImg: basePath+"/css/TreeTable/tv-collapsable-last.gif",
        lastShutImg: basePath+"/css/TreeTable/tv-expandable-last.gif",
        lastLeafImg: basePath+"/css/TreeTable/tv-item-last.gif",
        vertLineImg: basePath+"/css/TreeTable/vertline.gif",
        blankImg: basePath+"/css/TreeTable/blank.gif",
        collapse: false,
        column: 1,
        striped: false,
        highlight: false,
        state: false
    });
    //$(".parimg").click();
}

/**
 * AJAX检查栏目名称是否存在
 * @param title 文章标题
 */
function checkAddInfoTypeName(name,infoTypeId){
    var a;
    $.ajax({
        url:"checkThirdInfoTypeName.htm",
        data:{name:name,infoTypeId:infoTypeId},
        success:function(data){
            a = data;
        },
        async: false
    });
    return a;
}

/*显示树形控件*/
function showMenu()
{
    var selObj = $("#parentId");
    var businessOffset = $("#parentId").offset();
    $(".menuContent").css(
        {
            left : businessOffset.left + 800 + "px", top : businessOffset.top - selObj.outerHeight() + 200 + "px"
        }).show();
    $("#treeDemo").fadeIn("slow");
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
/*树形控件点击回调*/
function onClick(event, treeId, treeNode, clickFlag)
{
	
    $("#parentId").val(treeNode.pId);
    $(".parentName").val(treeNode.name);
    $("#treeDemo").fadeOut("slow");
}

/*验证表单*/
function validataForm(){
    var flag = true;
    //验证栏目名称
    if($("#up_name").val()==""){
        $("#up_name").next().html( "请填写栏目名称!", $("#name_tips"));
        $("#up_name").focus();
        flag = flag && false;
    }else{
        if(!checkAddInfoTypeName($("#up_name").val(),$("#infoTypeId").val())){
            $("#up_name").next().html( "栏目名称已存在!", $("#name_tips"));
            $("#up_name").focus();
            flag = flag && false;
        }else{
            $("#up_name").next().html("");
            flag = flag && true;
        }
        flag = flag && checkSpecSymb("up_name");
    }

    //验证seo内容摘要
    if($("#up_desc").val()==""){
        $("#up_desc").val($("#up_name").val());
    }
    flag = flag && checkSpecSymb("up_desc");
    //验证排序
    if($("#up_sort").val()==""){
        $("#up_sort").next().html( "请填写排序!");
        $("#up_sort").focus();
        flag = flag && false;
    }else{
        $("#up_sort").removeClass( "ui-state-error" );
        $("#sort_tips").text("").removeClass( "ui-state-highlight" );
        flag = flag && true;
    }
    flag = flag && checkNumAndDialog("up_sort");

    return flag;
}

/*填充表单数据*/
function fillFormData(tid){
    $.get("selectInfoTypeByPrimaryKey.htm",{infoTypeId:tid},function(data){
        if(data!=null){
            $("#infoTypeId").val(data.infoTypeId);
            $("#up_name").val(data.name);
            if(data.parentId==0){
                $(".parentName").val("所有");
            }else{
                $(".parentName").val(data.parentName);
            }
            $("#parentId").val(data.parentId);
            $("input[name='isShow'][value='"+data.isShow+"']").attr("checked","checked");
            $("#up_sort").val(data.sort);
            $("#up_seoDesc").val(data.seoDesc);
        }
    },"json");
}

/*重置表单*/
function resetForm(){
    document.getElementById("infoTypeForm").reset();
    $("#up_name").next().html("");
    $("#up_sort").next().html("");
}

/*分页*/
function changePageNo(obj){
    var pageNo = $(obj).attr("data-role");
    doAjaxForCate(pageNo,15);
}

/*单个删除*/
function delGoodsBrand(cateId)
{
    $.get("checkDelThirdInfoType.htm?infoTypeId=" + cateId, function (data)
    {
        if (data.flag)
        {
            $("#deleteInfoTypeForm").attr("action","delThirdInfoType.htm?infoTypeId=" + cateId);
            $("#delete-tip").modal('show');
        }
        else
        {
            //输入错误
            $(".result-tip-title").text(data.msg);
            $("#select-tip").modal('show');
        }
    });
}

/*批量删除*/
function batchDelCate(obj)
{
    //判断是否有按钮选中
    var bool = false;
    var brandIds = document.getElementsByName(obj);
    var checkedBrand = new Array();
    for (var i = 0; i < brandIds.length; i++) {
        var e = brandIds[i];
        if (e.checked == true) {
            bool = true;
            checkedBrand.push(e.value);
        }
    }
    if (bool == false)
    {
        //选择数据
        $(".result-tip-title").text("请至少选择一行数据！");
        $("#select-tip").modal('show');
        return false;
    }
    else
    {
        var bool2 = true;
        for (var i = 0; i < checkedBrand.length; i++)
        {
            $.ajax({
                url:"checkDelThirdInfoType.htm?infoTypeId=" + checkedBrand[i],
                async:false,
                dataType:'json',
                success:function (data)
                {
                    bool2 = data.flag && bool2;
                    if (!data.flag) {
                        //输入错误
                        $(".result-tip-title").text(data.msg);
                        $("#select-tip").modal('show');
                        return false;
                    }
                }
            });
        }
        if (bool2)
        {
            $("#deleteInfoTypeForm").attr("action","batchDelThirdInfoType.htm?infoTypeIds=" + checkedBrand);
            $("#delete-tip").modal('show');
        }
    }
}

$(function(){

    /*初始化分页列表*/
    doAjaxForCate(1,15);

    /*查询栏目放在树形控件中*/
    $.get("selectAllThirdInfoType.htm", function (dataList)
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
            if (dataList[i].infoTypeId == $("#parentId").val()) {
                $(".parentName").val(dataList[i].name);
            }
            if (dataList[i].infoTypeId == $("#infoTypeId").val()) {
                break;
            }
            var node =
            {
                id : dataList[i].infoTypeId, pId : dataList[i].parentId, name : dataList[i].name,
                open : true
            };
            zNodes.push(node);
        }
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });

    /*添加文章类型*/
    $(".create-infotype-btn").click(function(){
        /*重置表单*/
        resetForm();
        /*设置弹出框title*/
        $(".infotype-info-title").html("添加栏目");
        /*设置表单提交action*/
        $("#infoTypeForm").attr("action","saveThirdInfoType.htm");
    });

    /*修改按钮*/
    $("#treetable").on("click","button.modify-infotype-btn",function(){
        /*重置表单*/
        resetForm();
        /*填充修改表单数据*/
        fillFormData($(this).attr("data-key"));
        /*设置弹出框title*/
        $(".infotype-info-title").html("修改栏目");
        /*设置表单提交action*/
        $("#infoTypeForm").attr("action","updateThirdInfoType.htm");
    });

    /*提交按钮*/
    $("#save").click(function(){
        if(validataForm()){//验证表单通过，提交表单
            $("#infoTypeForm").submit();
        }
    });

    /*点击搜索框的时候*/
    $("#searchBtn").click(function(){
        doAjaxForCate(1,15);
    });

    /*确认删除*/
    $("#tip-submit-btn").click(function(){
        $("#deleteInfoTypeForm").submit();
    });

    /*重置按钮*/
    $("#reset-search-btn").click(function(){
        $("#searchText").val("");
    });
});