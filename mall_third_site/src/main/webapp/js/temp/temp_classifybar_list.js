/*树形控件关系数组*/
var map=new Array();
var basePath=$("#basePath").val();

/*執行AJAX查詢分類信息*/
function doAjaxForCate(pageNo, pageSize){
    /*AJAX查询分类信息*/
    $.get("queryThirdTempClassifyBar.htm?pageNo=" + pageNo + "&pageSize=" + pageSize + "&tempId=" + $("#tempId").val(),
        function (data)
        {
            /*分页*/
            if(data.list.length>0){
                var foot="<div class='ops-left'><button class='btn muilty-delete-btn btn-primary btn-xs' type='button'>批量删除</button></div>";
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

                /*首先把内容置空*/
                $("#treetable").html("");

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
        });
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
        var cb=data[i];
        html+="<tr>";
        html+="<td><input type='checkbox' name='classifyBarId' value='"+cb.classifyBarId+"'/></td>";
        html+="<td>"+cb.name+"</td>";
        html+="<td>"+cb.url+"</td>";
        if(cb.isUsing=='0'){
            html+="<td><i class='glyphicon glyphicon-remove'></i></td>";
        }else{
            html+="<td><i class='glyphicon glyphicon-ok'></i></td>";
        }
        html+="<td>";
        html+="<div class='btn-group'>";
        //html+="<button type='button' class='btn add-classify-btn btn-primary btn-sm' data-toggle='modal' data-target='#sortNav' pdata-key='"+cb.classifyBarId+"'>添加子分类导航</button>";
        html+="<button type='button' class='btn modify-classify-btn btn-primary btn-sm' data-toggle='modal' data-target='#sortNav' pdata-key='"+cb.classifyBarId+"'>修改</button>";
        html+="<button type='button' class='btn btn-primary btn-sm dropdown-toggle' data-toggle='dropdown' aria-expanded='false'>";
        html+="<span class='caret'></span>";
        html+="</button>";
        html+="<ul class='dropdown-menu' role='menu'>";
        //html+="<li><a href='javascript:;' class='modify-classify-btn' data-toggle='modal' data-target='#sortNav' data-key='"+cb.classifyBarId+"'>修改</a></li>";
        html+="<li><a href='javascript:;' data-toggle='modal' data-target='#delete-tip' class='delete-btn' data-key='"+cb.classifyBarId+"'>删除</a></li>";
        html+="</ul>";
        html+="</div>";
        html+="</td>";
        html+="</tr>";
        /*放入树形控件组*/
        map.push(pgrade);

        /*当前元素在树索引中的位置向前推一*/
        index=index+1;
        /*递归子分类列表*/
        if(cb.childs!=null&&cb.childs.length>0){
            html = html + getCateList(cb.childs,index);
            /*将当前元素在树索引的位置向前推子节点的个数位*/
            index+=cb.childs.length;
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

/*分页*/
function changePageNo(obj){
    var pageNo=$(obj).attr("data-role");
    doAjaxForCate(pageNo,15);
}


//验证特殊字符，将调试显示到页面中
/*function checkSpecSymb(inputobj){
    var regexp=new RegExp("[`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]");
    if (regexp.test( $("#"+inputobj).val() ) ) {
        $("#"+inputobj).next().html("输入的内容包含特殊字符!");
        $("#"+inputobj).focus();
        return false;
    }
    else {
        $("#"+inputobj).next().html("");
        return true;
    }
}*/
//根据传过来的对象验证是否为数字（已經移到common js中）
/*function checkNumAndDialog(inputobj){
    if (isNaN($("#"+inputobj).val() ) || $("#"+inputobj).val()<0) {
        $("#"+inputobj).next().html( "不是数字，请填写数字类型！" );
        $("#"+inputobj).focus();
        return false;
    }
    else {
        $("#"+inputobj).next().html( "" );
        return true;
    }
}*/

/*验证表单*/
function validateForm(){
    var flag=true;
    //验证栏目名称
    if($("#up_name").val()==""){
        $("#up_name").next().html("请填写导航名称!");
        $("#up_name").next().addClass("error");
        $("#up_name").focus();
        flag = flag && false;
    }
    flag = flag && checkSpecSymb("up_name");
    //验证URL
    if($("#up_url").val()==""){
        $("#up_url").next().html("请填写URL!");
        $("#up_url").next().addClass("error");
        $("#up_url").focus();
        flag = flag && false;
    }else{
        $("#up_url").next().html("");
        flag = flag && true;
    }
    //验证排序
    if($("#up_sort").val()==""){
        $("#up_sort").next().html( "请填写排序！" );
        $("#up_sort").next().addClass("error");
        $("#up_sort").focus();
        flag = flag && false;
    }else{
        $("#up_sort").next().html( "" );
        flag = flag && true;
    }
    return flag && checkNumAndDialog("up_sort");
}

/*重置表单*/
function resetForm(){
    document.getElementById("classyInfoForm").reset();
    $("#up_name").next().html("");
    $("#up_url").next().html("");
    $("#up_sort").next().html( "" );
}

/*填充修改表单*/
function fillUpdateForm(barId){
    $.getJSON("getClassifyBarById.htm",{classifyBarId:barId},function(data){
        if(data!=null){
            $("#up_classifyBarId").val(data.classifyBarId);
            $("#up_name").val(data.name);
            $("#up_url").val(data.url);
            $("#up_sort").val(data.sort);
            $("input[name='isUsing'][value='"+data.isUsing+"']").attr("checked",true);
        }
    });
}

$(function(){
    /*初始化分页列表*/
    doAjaxForCate(1,15);

    /*添加按钮*/
    $(".add-classify-btn").click(function(){
        /*重置表单*/
        resetForm();
        /*设置添加分类的parentid */
        $("#up_parentId").val($(this).attr("pdata-key"));
        /*设置表单action*/
        $("#classyInfoForm").attr("action",basePath+"/createThirdTempClassifyBar.htm");
    });

    $("#treetable").on("click","button.add-classify-btn",function(){
    	$(".modal-title").html("添加分类导航");
        /*重置表单*/
        resetForm();
        /*设置添加分类的parentid */
        $("#up_parentId").val($(this).attr("pdata-key"));
        /*设置表单action*/
        $("#classyInfoForm").attr("action",basePath+"/createThirdTempClassifyBar.htm");
    });


    /*修改按钮*/
    $("#treetable").on("click",".modify-classify-btn",function(){
    	$(".modal-title").html("修改分类导航");
        /*重置表单*/
        resetForm();
        /*设置表单action*/
        $("#classyInfoForm").attr("action",basePath+"/updateThirdTempClassifyBar.htm");
        /*填充表单*/
        fillUpdateForm($(".modify-classify-btn").attr("pdata-key"));
    });

    /*保存按钮*/
    var num=0;
    $("#save").click(function(){
        if(validateForm()&&num==0){//验证通过，提交表单
            num+=1;
            $("#classyInfoForm").submit();
        }
    });

    /*单个删除按钮*/
    $("#treetable").on("click","a.delete-btn",function(){
        /*设置确定按钮的样式，用于事件控制*/
        $("#tip-submit-btn").removeClass("muilty-delete").addClass("single-delete");
        $("#deleteKey").val($(this).attr("data-key"));
    });

    /*批量删除按钮*/
    $("div.footer-operation").on("click","button.muilty-delete-btn",function(){
        if(checkSelect("classifyBarId")){//检查是否选中了行记录
            /*设置确定按钮的样式，用于事件控制*/
            $("#tip-submit-btn").removeClass("single-delete").addClass("muilty-delete");
            $("#delete-tip").modal('show');
        }else{//如果未选中,弹出提示框
            $("#select-tip").modal('show');
        }
    });

    /*单个删除事件*/
    $("div.modal-footer").on("click","button.single-delete",function(){
        $("#singleDeleteForm").attr("action",$("#basePath").val()+"/deleteThirdTempClassifyBar.htm").submit();
    });
    /*批量删除*/
    $("div.modal-footer").on("click","button.muilty-delete",function(){
        $("#muilt-delete-form").attr("action",$("#basePath").val()+"/deleteThirdTempClassifyBar.htm").submit();
    });
});



