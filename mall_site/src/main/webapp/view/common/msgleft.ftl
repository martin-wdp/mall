<div class="msg_left fl">
	<h3 class="m_tit">消息中心</h3>
    <div class="inbox">
    	<h4>收件箱</h4>
        <ul class="msg_menu">
        	<li id="sys" <#if msg==1> class="cur" </#if>><a href="${basePath}/systemmsg.html">系统消息 <span id="syscount">（0）</span></a></li>
            <li id="let" <#if msg==2> class="cur" </#if>><a href="${basePath}/lettermsg.html">收到的私信 <span id="lettercount">（0）</span></a></li>
            <li id="com" <#if msg==3> class="cur" </#if>><a href="${basePath}/commentmsg.html">评论 <span id="commentcount">（0）</span></a></li>
        </ul><!--/msg_menu-->
    </div><!--/inbox-->
    <div class="outbox mt20">
    	<h4>发件箱</h4>
        <ul class="msg_menu">
        	<li <#if msg==4> class="cur" </#if>><a href="${basePath}/sendlettermsg.html">已发私信</a></li>
        </ul><!--/msg_menu-->
    </div><!--/outbox-->
</div><!--/msg_left-->
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript">
 var msg = ${msg};
	    $.ajax({
	    url:'messagecount.html?'+Math.random(),
	    dataType:'json',
	    success:function(result){
	         if(result.syscount > 0 || msg == 1){
	            $("#syscount").html('('+result.syscount+')');
	            $("#sys").addClass('cur');
	         }else{
	            $("#sys").removeClass('cur');
	         }
	         if(result.lettercount > 0 || msg == 2){
	            $("#lettercount").html('('+result.lettercount+')');
	            $("#let").addClass('cur');
	         }else{
	            $("#let").removeClass('cur');
	         }
	         if(result.commentcount > 0 || msg == 3){
	            $("#commentcount").html('('+result.commentcount+')');
	            $("#com").addClass('cur');
	         }else{
	            $("#com").removeClass('cur');
	         }
	     }
	 });
 
</script>