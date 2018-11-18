$(function(){
    loadImageCateToPage();
    $("#saveImageCateForm").validate();
    $("#editImageCateForm").validate();
});

function loadImageCateToPage() {
    $.ajax({
        url: "queryImageClassifyByPbForAjax.htm?CSRFToken=" + $("#CSRFToken").val(),
        async: false,
        success: function (data) {
            loadImageCateHtml(data.list,0);
            /* 下面是另一种带折叠的表格，没有分页 */
            // 这里要说明一下
            // 比如现在有5行，其中第二行和第一行平级，第三行是第二行的下级行，第四行是第三行的下级
            // 第五行和第三行平级，也就是说是第二行的下级行，应该如下所示：
            //      1
            //      2
            //         3
            //            4
            //         5
            // 这样的话，map应该是这样的
            // 行号：1					 2				 3                 4				 5
            // map： 0    ,              0,              2,                3,                2
            // 说明：1行的上级的行为0    2行的上级也为0  3行的上级是第2行  4行的上级是第3行  第5行的上级是第2行
            var map = [];
            $(".tr").each(function(){
            	if($(this).attr("parent_index")!=0){
            		$(this).addClass("collapsed");
            	}
                map.push($(this).attr("parent_index"));
            });
            $("#treetable").jqTreeTable(map, {
                openImg: "images/TreeTable/tv-collapsable.gif",
                shutImg: "images/TreeTable/tv-expandable.gif",
                leafImg: "images/TreeTable/tv-item.gif",
                lastOpenImg: "images/TreeTable/tv-collapsable-last.gif",
                lastShutImg: "images/TreeTable/tv-expandable-last.gif",
                lastLeafImg: "images/TreeTable/tv-item-last.gif",
                vertLineImg: "images/TreeTable/vertline.gif",
                blankImg: "images/TreeTable/blank.gif",
                collapse: false,
                column: 0,
                striped: true,
                highlight: true,
                state:false
            });
            /* 下面是另一种带折叠的表格，没有分页 END */
        }
    });
}
var rowNum=0;
function loadImageCateHtml(data,parentRowNum) {
    rowNum ++;
    for (var i = 0; i < data.length; i++) {
        var catesHtml = '';
        var cate = data[i];
        catesHtml +=
            '<tr parent_index="'+parentRowNum+ '" class="tr">' +
            '<td>&nbsp;&nbsp;' +   cate.classifyName+'</td>' +
            '<td>';
        if (cate.isHasImg == '0') {
            catesHtml += '<a href="javascript:;"><span class="label label-default">否</span></a>';
        } else {
            catesHtml += '<a href="javascript:;"><span class="label label-success">是</span></a>';
        }
        catesHtml += '</td>';
        //catesHtml += '<td>';
        //if (cate.isSys == '0') {
        //    catesHtml += '<a href="javascript:;"><span class="label label-default">否</span></a>';
        //} else {
        //    catesHtml += '<a href="javascript:;"><span class="label label-success">是</span></a>';
        //}
        //catesHtml += '</td>';
        catesHtml += '<td>';
        catesHtml +=
            '<div class="btn-group">' +
            '<button type="button" class="btn btn-default" onclick="showUpdateImageCate(' + cate.classifyId + ',' + cate.parentId + ',' + '\'' + cate.classifyName + '\',\'' + cate.isHasImg + '\',\'' + cate.isSys + '\')">编辑</button>' +
            '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">' +
            '<span class="caret"></span>' +
            '<span class="sr-only">Toggle Dropdown</span>' +
            '</button>' +
            '<ul class="dropdown-menu" role="menu">' +
            '<li><a href="javascript:;" onclick="deleteInfoImageClassify(' + cate.classifyId + ')">删除</a></li>' +
            '</ul>' +
            '</div>' +
            '</td>' +
            '</tr>';
        $("#treetable").append(catesHtml);
        loadImageCateHtml(cate.childs,rowNum);
    }
}

/**
 * 删除图片分类
 * @param classifyId
 */
function deleteInfoImageClassify(classifyId){
    var flag;
    $.ajax({
        url:"checkDeleteClassify.htm?CSRFToken="+$("#CSRFToken").val(),
        data:{infoImageClassifyId:classifyId},
        success:function(data){
            flag = data;
        },
        async: false
    });
    if(flag){
        //删除提示弹出框
        showDeleteOneConfirmAlert("deleteImageClassifyAction.htm?infoImageClassifyId="+classifyId+"&CSRFToken="+$("#CSRFToken").val());
    }else{
        showTipAlert("该分类下包含图片信息，请先移除图片信息再删除！");
    }
}

/**
 * 弹框显示图片分类添加框
 */
function showAddImageCate() {
    $("#parentId").val(-1);
    loadImageCate("treeDemo");
    num=0;
    /* 下面是关于树形菜单 END */
    $('#addImgCate').modal('show');
}

/**
 * 加载图片分类树
 * @param treeId 树控件id
 * @param parentId 父类id
 */
function   loadImageCate(treeId) {
    /* 下面是关于树形菜单 */
    var setting = {
        view: {
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback:{
            onClick:onClick
        },
        view:{
        	showIcon:false
		}
    };
    var zNodes=new Array();
    $.ajax({
        url: "queryAllImageClassifyForAjax.htm?CSRFToken=" + $("#CSRFToken").val(),
        async: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var node = {
                    id: data[i].classifyId, pId: data[i].parentId, name: data[i].classifyName, open: false
                };
                zNodes.push(node);
            }
            zNodes.push({
                    id: -1, pId: -1, name: '所有', open: true
                });
        }
    });
    var zTree;
    $.fn.zTree.init($("#"+treeId), setting, zNodes);
}

/**
 * 树节点点击事件
 * @param e 点击事件
 * @param treeId 树id
 * @param treeNode 节点
 */
function onClick(e,treeId,treeNode) {
    $("#parentId").val(treeNode.id);
    $("#parentId_update").val(treeNode.id);
}

/**
 * 弹框显示图片分类编辑框
 * @param classifyId 图片分类id
 * @param parentId 图片分类父类id
 * @param classifyName 图片分类名称
 * @param isHasImg 是否包含图片
 * @param isSys 是否为系统分类
 */
function showUpdateImageCate(classifyId,parentId,classifyName,isHasImg,isSys) {
    $("#classifyId").val(classifyId);
    $("#parentId_update").val(parentId);
    $("#classifyName").val(classifyName);
    $("#isHasImg"+isHasImg).click();
    $("#isSys"+isSys).click();
    loadImageCate("treeDemo_update");

    $('#editImgCate').modal('show');
}

/**
 * 确定添加图片分类
 */
var num=0;
function submitImageCateForm() {
    if($("#saveImageCateForm").valid()&&num==0){
        num+=1;
        $("#saveImageCateForm").submit();
    }

}