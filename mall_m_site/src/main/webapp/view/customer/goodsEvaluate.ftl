<!DOCTYPE HTML>
<head>
<#assign basePath=request.contextPath>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=0.9,user-scalable=no"/>
<meta name="MobileOptimized" content="320">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="${basePath}/css/goodsEvaluate.css">
    <title>发表<#if comment??>晒单<#else >评价</#if></title>
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script> <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<style>
    .commerror{display: none;color: #ff0000;font-size: 13px;}
</style>


<div class="goodsMes">
	<p>订单号：<span>${orderNo!}</span></p>
	<div class="goodsImgMes">
		<img src="${goodsImg!}" />
		<ul>
			<li>${goodsName!}</li>
			<li><strong>实付：</strong><em>￥${goodsPrice?string("0.00")}</em><span>x${goodsNum!}</span></li>
		</ul>
	</div>
</div>
<#if comment??>
    <#assign isInit = "1" />
    <#assign commentScore = comment.commentScore />
<#else >
    <#assign isInit = "0" />
    <#assign commentScore = 1 />
</#if>

<div class="goodScore">
    <form id="sharePull" action="${basePath}/saveShare.htm">
        <input type="hidden" name="commentScore" id="commentScore" />
        <input name="goodsId" value="${productId!''}" type="hidden" />
        <input name="customerId" value="<#--<#if cust?? >-->${cust.customerId!}<#--</#if>-->" type="hidden" />
        <input name="orderId" value="${orderId!''}" type="hidden" />
        <h3>商品评分</h3>
        <ul>
            <li><span>1星:很不满意。再三提醒卖家超过一天才发货耽误我的时间。</span><i></i></li>
            <li><span>2星:很不满意。再三提醒卖家超过一天才发货耽误我的时间。</span><i></i></li>
            <li><span>3星:很不满意。再三提醒卖家超过一天才发货耽误我的时间。</span><i></i></li>
            <li><span>4星:满意。再三提醒卖家超过一天才发货耽误我的时间。</span><i></i></li>
            <li><span>5星:很满意。再三提醒卖家超过一天才发货耽误我的时间。</span><i></i></li>
        </ul>
        <p class="commerror" id="commentScoreError">请选择评分星级</p>
</div>

<div class="scoreDetail ">
    <h3>评价详情</h3>
    <textarea <#if comment??>readonly</#if> id="commentContent"
                                            name="commentContent"
                      placeholder="说说你对这件商品的评价吧。"><#if comment??>${comment.commentContent!}</#if></textarea>
    <p class="commerror" id="contentError">请填写商品评价内容</p>
</div>
<input type="hidden" name="commentId" value="<#if comment??>${comment.commentId}</#if>">
<input type="hidden" class="updatePIC" name="span#avactor1">
<input type="hidden" class="updatePIC" name="span#avactor2">
<input type="hidden"  name="imageNames">
<input type="hidden"  name="orderGoodsId" <#if orderGoodsId??>value="${orderGoodsId}"</#if>/>
<div class="single">
    <h3>晒一晒</h3>
    <ul>
        <li><input id="pic1" type="file" name="shareFile"
                   onchange="uploadUserAvatarAjax('pic1')" url="${basePath}/shareUploadM.htm?id=avactor1"
                   accept=".png,.jpg,.jpeg"><span id="avactor1" data-id="#pic1"><em>x</em></span></li>
        <li><input id="pic2" type="file" name="shareFile"
                   onchange="uploadUserAvatarAjax('pic2')" url="${basePath}/shareUploadM.htm?id=avactor2"
                   accept=".png,.jpg,.jpeg"><span id="avactor2" data-id="#pic2"><em>x</em></span></li>
    </ul>
    <p class="commerror" id="sharePic">请上传图片</p>
</div>

<div class="btn">
    <button type="button" onclick="pullComment()"><#if comment??>发表<#else >评价</#if>晒单</button>
</div>

    </form>



<script src="${basePath}/js/customer/jquery-1.11.3.min.js"></script>
<script src="${basePath}/js/customer/public.js"></script>
<script src="${basePath}/js/customer/goodsEvaluate.js"></script>
<script src="${basePath}/js/customer/ajaxMutiUploadFile.js"></script>
<script>
    if('${isInit}'=="1"){
        $(".goodScore li").css("background-image","url(${basePath}/images/qp_xqpl0.png)");
        var n = ${commentScore?string("0")}-1;
        $("#commentScore").val(n);
        for(i=0;i<=n;i++){
            $($(".goodScore li")[i]).css("background-image","url(${basePath}/images/qp_xqpl.png)");
        }
        $(".goodScore li").children().hide();
        $(this).children().show();
        $(".goodScore li").unbind();
    }

    function uploadUserAvatarAjax(picid) {
        if ($("#"+picid).val() != "") {
            var url = $("#"+picid).attr("url");
            /*$("#upload_form").submit();*/
            $.ajaxMutiUpload({
                url: url,
                fileElementId: picid,
                async: false,
                success: function (data) {

                }
            });
        }
    }

    /**
     * 上传文件成功后，触发。
     * @param msg
     */
    function callback(msg) {
        //上传失败
        if (msg.split(",").length < 2) {
            if (msg == '101') {
//			showAlert("操作提示","每张图片不超过4M!","warn");
               /* $("#titleerr").text("每张图片不超过4M!");
                dia(4);
                $("#customer_imgs").attr("src", img_src);*/
            } else if (msg == '102') {
//			/*alert("操作提示","图片格式不正确!","warn");
                /*$("#titleerr").text("图片格式不正确!");
                dia(4);
                $("#customer_imgs").attr("src", img_src);*/
            }
            return;
        }
        //上传成功
        var imageName = msg.split(",")[0];
        var id = "span#"+msg.split(",")[1];
        //alert(111);
        $(id).css({"background-image": "url(" + imageName + ")", "background-size": "100%"});
        $(id).children("em").show();
        $('input[name="'+id+'"]').val(imageName);
        $(id).children("em").bind("click", function () {
            $(this).hide();
            $(id).removeAttr("style");
            // 清除图片上传的隐藏域属性数据
            var inputId = $(id).attr("data-id");
            $('input[id="' + inputId + '"]').val("");
            $('input[name="'+id+'"]').val("");
            $($(id).attr("data-id")).val("");
            Write.Must.No(id);
            event.stopPropagation();//阻止冒泡事件
        });
        //img_src = $("#customer_imgs").attr("src");
    }


    function pullComment (){
        $(".commerror").hide();
        //评论ID 如果值不是空则说明已经提交过评论了
        var commentId = $('input[name="commentId"]').val();
        //图片上传 目前只有两张 上传在0-2张之间
        var avactor1 = $('input[name="span#avactor1"]').val();
        var avactor2 = $('input[name="span#avactor2"]').val();
        //commentId 不是空是在已经提评价的基础上晒单
        if(commentId != ""){
            //晒单必须上传在1-2张图片 若没有
            if(avactor1!="" || avactor2!=""){
                initImgNames();
                $("#sharePull").submit();
            }else{
                $("#sharePic").show();
            }
        }else if(!checkIn()){
            $("#sharePull").submit();
        }
        return false;//阻止冒泡事件
    }
    //验证输入
    function checkIn (){
        //评论内容
        //var commentContent = $("#commentContent").text();
        var commentContent = $("#commentContent").val();
        //评论分数
        var commentScore = $("#commentScore").val();
        //alert($("#commentContent").val());
        var flag = false;
        if(commentContent ==""){
            flag =  true;
            $("#contentError").show();
        }
        if(commentScore ==""){
            flag = true;
            $("#commentScoreError").show();
        }
        return flag;
    }
    //合并上传的图片的地址
    function initImgNames(){
        var obj = $(".updatePIC");
        var imgNames = "";
        for(var i=0 ; i<obj.length ; i++){
            if(obj[i].value!=""){
                imgNames +=  obj[i].value+",";
            }
        }
        $('input[name="imageNames"]').val(imgNames)
    }

</script>