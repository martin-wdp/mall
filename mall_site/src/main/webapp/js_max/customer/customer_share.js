var gid= "";
$(function ()
{
	//选择上传文件后触发
	$(".upload_file").change(function() {
		if($(this).val()!=""){
			var orderGoodsId = $(this).attr("order_goods_id");
			var shareImgs = $(".share"+orderGoodsId);
			var waitImg = $("#wait_img"+orderGoodsId);
			gid = orderGoodsId;
			if(waitImg.length>0) return;
			if(shareImgs.length==10) {
                $("#errorT").html("最多上传10张图片！");
                dia(3);
				setTimeout(function(){
					$("#err_img_"+orderGoodsId).hide();
				},3000);
				return;
			}
			$("#upload_form"+orderGoodsId).submit();
			$("#share_imgs"+orderGoodsId).append('<li id="wait_img'+orderGoodsId+'" ><image src="images/load.jpg" width="36px" height="36px"/></li>');
		}
	});
	//点击“晒单”，保存晒单信息
	$(".share_btn").click(function() {
		var orderGoodsId = $(this).attr("order_goods_id");
		saveShare(orderGoodsId);
	});
	//点击“删除”时，删除选中的图片
	$(".delete_btn").click(function() {
		var orderGoodsId = $(this).attr("order_goods_id");
		var shareImgs = $(".share"+orderGoodsId);
        var img= $("#share_imgs"+orderGoodsId).find(".selected");

			if(img.length==1) {
				img.remove();
                $(".imgNum"+orderGoodsId).html(  $(".share"+orderGoodsId).length);
			}else{
				dia(3);
			}

	});
	
});
/**
 * 保存晒单信息
 * @param orderGoodsId 订单-商品id
 */
function saveShare(orderGoodsId,type) {
    //评分
    var point="";
    //评论标签
    var tag="";
    //晒单图片
    var imgs = "";
    //评论内容
    var str=$("#complaincon"+orderGoodsId).val();
    //晒单标题
    var title=$("#title"+orderGoodsId).val();
    if(type==1){
       point=$("#commentScore"+orderGoodsId).val();
        if(point==0){
            $("#errorT").html("请给商品评分！");
            dia(3);
            return;
        }
        tag=$($("#g_tag"+orderGoodsId).find(".tags1").get(0)).attr("title");
        var reg= /^([\u4e00-\u9fa5_A-Za-z0-9 \\`\\~\\!\\@\\#\\$\\^\\&\*\(\)\=\{\}\'\:\;\'\,[\]\.\/\?\~\！\@\#\￥\…\…\&\*\（\）\;\—\|\{\}\【\】\‘\；\：\”\“\'\。\，\、\？])+$/;
        //内容长度最多500
        if($.trim(str).length>500 ||$.trim(str).length<10) {
            $("#errorT2").html("请输入评论内容!");
            $("#errorT2").append("<p>字数在10-500之间！</p>");
            dia(2);
            return false;
        }
        if(!reg.test(str)){
            $("#errorT2").html("评论内容不合法!!");
            $("#errorT2").append("<p>不能包含特殊字符!</p>");
            dia(2);
            return false;
        }
        var shareImgs = $(".share"+orderGoodsId),token=$("#hi_token").val();
        //图片至少3张
        //if(shareImgs!=null&&shareImgs.length<3&&shareImgs.length>0) {
        if(shareImgs!=null&&shareImgs.length<3) {
            $("#errorT").html("至少上传3张图片！");
            dia(3);
            return;
        }
        for(var i=0;i<shareImgs.length;i++) {
            imgs += $(shareImgs[i]).val() + ",";
        }
    }

  if(type==2){
      $("#commTip"+orderGoodsId).text("");
      //晒单内容
        str=$("#content"+orderGoodsId).html();
      //晒单图片
      var shareImgs = $(".share"+orderGoodsId),token=$("#hi_token").val();
      //图片至少3张
      if(shareImgs.length<3) {
          $("#errorT").html("至少上传3张图片！");
          dia(3);
          return;
      }
      for(var i=0;i<shareImgs.length;i++) {
          imgs += $(shareImgs[i]).val() + ",";
      }
      //
  }

	//ajax保存晒单信息
	$.ajax({
		url:"saveShare.htm",
		data:{
            goodsId:$("#goodInfoId"+orderGoodsId).val(),//商品Id
			orderGoodsId:orderGoodsId,//订单商品Id
            commentContent:str,//评论内容
			imageNames:imgs,//晒单图片
            commentTag:tag,//评论标签
            shareTitle:title,//评论晒单标题
            commentScore:point,//评分
			CSRFToken:token
		},
		success:function(msg) {
			if(msg=='ok') {
				location.reload();
			}
		}
	});
}



/**
 * 上传文件成功后，触发。
 * @param msg
 */
function callback(msg) {  
	//上传失败
	if(msg.split(",").length<2) {
		if(msg=='101') {
			$("#wait_img"+gid).remove();
            $("#titleerr").text("每张图片不超过4M!");
			dia(4);
		} else if(msg=='102') {
			$("#wait_img"+gid).remove();
            $("#titleerr").text("图片格式不正确!");
			dia(4);
			
		}
		return;
	}
	//上传成功
	var imageName = msg.split(",")[0],newImg ="";
	var orderGoodsId = msg.split(",")[1];
	$("#wait_img"+orderGoodsId).remove();
	if(imageName.indexOf("!")!= -1){
		newImg = imageName.substring(0,imageName.indexOf("!")+1)+56;
	}else{
		newImg = imageName;
	}
	$("#share_imgs"+orderGoodsId).append('<li class="share_img"><input class="share'+orderGoodsId+'" type="hidden" value="'+imageName+'" ></input><img id="share'+orderGoodsId+'"  width="36" height="36" alt="" src="'+newImg+'" /></li>');
	$(".share_img").click(function() {
		$(".share_img").removeClass("selected");
		$(this).addClass("selected");
	});
    $(".imgNum"+orderGoodsId).html(  $(".share"+orderGoodsId).length);
} 

