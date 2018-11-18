<%--
添加商品--选择适配车型-车型树
  Created by IntelliJ IDEA.
  User:
  Date:
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>选择适配车型</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="<%=basePath %>js/html5shiv.min.js"></script>
    <script src="<%=basePath %>js/respond.min.js"></script>
    <![endif]-->
    <style>
        body{background:none;overflow-y: scroll}
    </style>
</head>
<body>
<div role="tabpanel" class="upload_body">

    <!-- Tab panes -->
    <div class="tab-content">
        <ul id="treeDemo" class="ztree"></ul>
    </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
<script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/plugins/iframeTools.js"></script>
<script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
<script>
    $(function(){
        var parent = art.dialog.parent,				// 父页面window对象
                api = art.dialog.open.api;	// 			art.dialog.open扩展方法
        if (!api) return;
        // 操作对话框
        api.title('选择适配车型')
            // 自定义按钮
                .button(
                {
                    name: '保存',
                    callback: function () {
                        var win = art.dialog.open.origin;//来源页面
                        var treeObj =$.fn.zTree.getZTreeObj("treeDemo");
                        var paramInfo=[];
                        if(treeObj){
                            var nodes = treeObj.getCheckedNodes(true);
                            if(nodes.length==0){
                                alert('至少选择一个适配的车型！');
                                return false;
                            }
                            for(var i=0;i < nodes.length;i++){
                                var newPath='';
                                //查询该节点下是否有选中的节点
                                var childNodes = treeObj.getNodesByParam("checked", true, nodes[i]);
                                if(childNodes.length==0){
                                    var style ={};
                                    style.id=nodes[i].id;
                                    style.levelNum=nodes[i].levelNum;
                                    style.path = nodes[i].parentName;
                                    paramInfo.push(style);
                                }
                            }
                            $.ajax({
                                url:'autostyle/getAutoStyleId.htm',
                                dataType:'json',
                                data:{"jsonArr":JSON.stringify(paramInfo)},
//                                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                                success:function(msg){
                                    if(msg){
                                        var jsonArr =eval(msg);
                                        win.window.saveChooseStyle(jsonArr);
                                        art.dialog.close();
                                    }

                                }
                            });
                        }
                        return false;
                    },
                    focus: true
                },
                {
                    name: '取消',
                    callback: function () {
                        art.dialog.close();
                    }
                }
        );
        var zSetting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            check: {
                enable: true,
                chkboxType: { "Y" : "ps", "N" : "s" },
                nocheckInherit:false,
                nocheck:true
            },
            async: {
                enable: true,
                url: "autostyle/getTreeDatas.htm",
                autoParam: ["id=pId", "levelNum","parentName"],
                otherParam: { "liYangID":""}
            },
            callback: {

            }, view: {
                expandSpeed: 0
            }
        };
        //初始化车类品牌数据
        $.fn.zTree.init($("#treeDemo"), zSetting, []);
    });
</script>
</body>
</html>

