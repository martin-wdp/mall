<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#assign basePath=request.contextPath>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>qpmall——首页</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<script>
	$(function(){
		$.ajax({ url: "${basePath}/getBottom.htm", async:false ,success: function(date){
				var helpCates = date.helpCates;
				if(helpCates!=null && helpCates.length>0){
					for(var i=0;i<5;i++){
						$(".ft_menu").append("<dl class='ft_nav ft_0"+(i+1)+" tl'><dt style='background-image: url("
						+helpCates[i].helpcateImg+")'>"+helpCates[i].helpcateName+"</dt><dd>");
						for(var j=0;j<3 && j<helpCates[i].helpCenters.length;j++){
						 	var center = helpCates[i].helpCenters[j];
							$(".ft_0"+(i+1)+" dd").append("<div><a href='${basePath}/help/"+center.helpId+"' >"+center.helpTitle+"</a></div>");
						}
					}
				}
				var sys = date.sys;
				$("#bq").append(sys.bsetCopyright);
				/*
				var helpCenters = date.helpCenters;
				if(helpCenters!=null && helpCenters.length>0){
					for(var i=0;i<helpCenters.length;i++){
						$("#helpCenters").append("<a href='#' target='_blank' rel='nofollow' style='text-decoration:line-through;'>"+helpCenters[i].helpTitle+"</a>");
						if(i>=0 && i<helpCenters.length-1){
							$("#helpCenters").append("|");
						}
					}
				}


				var webCerts = date.webCerts;
				if(webCerts!=null && webCerts.length>0){
					for(var i=0;i<webCerts.length;i++){
						$("#webcert").append("<a href='"+webCerts[i].url+"'><img class='vm' alt='"+webCerts[i].name+"' title='"+webCerts[i].name+"' src='"+webCerts[i].imgsrc+"' /></a>");
					}
				}
				*/
			}
		})
	});
</script>
</head>

<body>
<script type="text/javascript" src="http://dl.ntalker.com/js/b2b/ntkfstat.js?siteid=cj_1000" charset="utf-8"></script>
<div class="explan_wp mt15">
    <div class="explan_box">
        <ul class="ep_list clearfix tc">
            <li class="ep_01"><span>正版正货品质保证</span></li>
            <li class="ep_02"><span>4天内免费送达</span></li>
            <li class="ep_03"><span>15天体验无理由退换货</span></li>
            <li class="ep_04"><span>免息分期支付灵活</span></li>
            <li class="ep_05"><span>购物金回馈多买多得</span></li>
            <li class="ep_06"><span>24小时全天服务</span></li>
            <li class="ep_07"><span>365天全年无休</span></li>
        </ul><!--/ep_list-->
    </div><!--/explan_box-->
</div><!--/explan_wp-->
<div class="footer">
    <div class="ft_box clearfix">
        <div class="ft_menu tc clearfix">

        </div>
    </div>
    <div align="center" id="helpCenters">

    </div>
    <div id="bq">

    </div>
    <div class="ft_bt tc mt10" id="webcert">

        <!--
            <a href="javascript:;"><img class="vm" alt="" src="${basePath}/images/bt_02.gif" /></a>
            <a href="javascript:;"><img class="vm" alt="" src="${basePath}/images/bt_03.gif" /></a>
            <a href="javascript:;"><img class="vm" alt="" src="${basePath}/images/bt_04.gif" /></a>
        	-->
    </div><!--/ft_bt-->
</div><!--/footer-->

</body>