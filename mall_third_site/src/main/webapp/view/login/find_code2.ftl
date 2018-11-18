<!doctype html>
<html lang="en">
    <head>
    <#assign basePath=request.contextPath>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="renderer" content="webkit">
        <title>找回密码</title>
        <link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
        <script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="head2">
                <a href="${basicSet.bsetAddress!'#'}">
                <#if basicSet.bsetThirdLogo??>
                    <img id="logo_pic" src="${basicSet.bsetThirdLogo}" alt="" style="height:45px;width:auto;"/>
                <#else>
                    <img alt="" src="${basePath}/images/logo.jpg" id="logo_pic"/>
                </#if>

                </a>
                <h1>找回密码</h1>
            </div>
        </div>
        <div class="container pb50">
            <div class="n_step" style="padding-left:150px;">
                <div class="n_step_con">
                    <div class="n_step2"></div>
                    <ul class="ml10 clearfix">
                        <li class="p100 prev">填写账户名</li>
                        <li class="p130 cur">验证身份</li>
                        <li class="p130">设置新密码</li>
                        <li>完成</li>
                    </ul>
                </div>
                <div class="n_password">
                    <div class="n_item clearfix mb20">
                        <span class="label fl">验证方式：</span>
                        <div class="fl">
                            <select>
                                <option>已验证手机</option>
                                <#--<option>已验证邮箱</option>-->
                            </select>
                            <div class="ne_tips hide ">您输入的用户名有误</div>
                        </div>
                    </div>
                    <div class="n_item clearfix mb20">
                        <span class="label fl">以验证手机：</span>
                        <div class="fl">
                            <strong>   <#if (user??&& user.infoMobile?? && user.infoMobile?length>3)>
                    <#assign mo="${user.infoMobile?substring(3,user.infoMobile?length-3)}" />
                    <#assign mob="${user.infoMobile?replace(mo,'*****')}" />
                    ${mob}
                    </#if></strong>
                        </div>
                    </div>
                    <div class="n_item clearfix mb20">
                        <span class="label fl">验证码：</span>
                        <div class="fl">
                            <input type="text" placeholder="请输入验证码" class="short_text mr20" id="varification" name="varification"/>
                            <img id="checkCodeImg" class="code_image"  src="${basePath}/patchca.htm" onclick="this.src=this.src+'?'+Math.random(); "/>
                            <a href="#" class="ml20 ju_s"  id="checkCodeA">换一张</a>
                            <div class="ne_tips hide varification">您输入的验证码有误</div>
                        </div>
                    </div>
                    <div class="n_item clearfix mb20">
                        <span class="label fl">短信验证码：</span>
                        <div class="fl">
                            <input type="text" placeholder="请输入验证码" class="short_text mr20" id="dynamiccode"/>
                            <a href="#" class="hq_code" id="sendCode">获取短信验证码</a>
                            <div></div>
                            <div class="ne_tips hide mobile_tip">您输入的验证码有误</div>
                            <div class="mt20 col6 hide mobile_code">校验码已发出，请注意查收短信，如果没有收到，你可以在
                                <span class="ju_s timeleft">60</span>
                                秒后要求系统重新发送
                            </div>
                        </div>
                    </div>
                    <div class="n_item clearfix mb20">
                        <span class="label fl">&nbsp;</span>
                        <a class="fl" href="#">
                                <button class="n_nextstep" type="button" onclick="tonextThree()">下一步</button>
                        </a>
                    </div>

                    <#--<div class="n_item clearfix mb20">-->
                        <#--<span class="label fl">以验证邮箱：</span>-->
                        <#--<div class="fl">-->
                            <#--<strong>5*****6@qq.com</strong>-->
                        <#--</div>-->
                    <#--</div>-->

                    <#--<div class="n_item clearfix mb20">-->
                        <#--<span class="label fl">&nbsp;</span>-->
                        <#--<a class="fl" href="find_code3.ftl">-->
                            <#--<a href="find_code3.ftl">-->
                                <#--<button class="n_nextstep">发送验证邮件</button>-->
                            <#--</a>-->
                        <#--</a>-->
                    <#--</div>-->
                </div>
            </div>
            <!--n_step-->
        </div>


        <div class="wp">
            <#--<div class="service mt20 clearfix" style="padding-left:125px">-->
                <#--<dl class="svc_box svc_01 fl" style="background-image: url(http://img01.ningpai.com/1420529386315.jpg);width:170px;">-->
                    <#--<dt>购物指南</dt>-->
                    <#--<dd>-->
                        <#--<div>-->
                            <#--<a href="/help/54">交易条款</a>-->
                        <#--</div>-->
                        <#--<div>-->
                            <#--<a href="/help/55">积分说明</a>-->
                        <#--</div>-->
                        <#--<div>-->
                            <#--<a href="/help/71">查询账户余额</a>-->
                        <#--</div>-->
                        <#--<div>-->
                            <#--<a href="/help/57">什么是优惠券</a>-->
                        <#--</div>-->

                    <#--</dd>-->
                <#--</dl>-->
                <#--<dl class="svc_box svc_01 fl" style="background-image: url(http://img01.ningpai.com/1420529395146.jpg);width:170px;">-->
                    <#--<dt>支付方式</dt>-->
                    <#--<dd>-->
                        <#--<div>-->
                            <#--<a href="/help/1">货到付款</a>-->
                        <#--</div>-->

                    <#--</dd>-->
                <#--</dl>-->
                <#--<dl class="svc_box svc_01 fl" style="background-image: url(http://img01.ningpai.com/1420529403162.jpg);width:170px;">-->
                    <#--<dt>售后服务</dt>-->
                    <#--<dd>-->
                        <#--<div>-->
                            <#--<a href="/help/69">查询配送服务</a>-->
                        <#--</div>-->
                        <#--<div>-->
                            <#--<a href="/help/3">退款说明</a>-->
                        <#--</div>-->
                        <#--<div>-->
                            <#--<a href="/help/66">催办订单</a>-->
                        <#--</div>-->
                        <#--<div>-->
                            <#--<a href="/help/65">售后服务总则</a>-->
                        <#--</div>-->

                    <#--</dd>-->
                <#--</dl>-->
                <#--<dl class="svc_box svc_01 fl" style="background-image: url(http://img01.ningpai.com/1420529438986.jpg);width:170px;">-->
                    <#--<dt>关于我们</dt>-->
                    <#--<dd>-->
                        <#--<div>-->
                            <#--<a href="/help/4">公司简介</a>-->
                        <#--</div>-->

                    <#--</dd>-->
                <#--</dl>-->
                <#--<dl class="svc_box svc_01 fl" style="background-image: url(http://img01.ningpai.com/1420529447872.jpg);width:170px;">-->
                    <#--<dt>联系我们</dt>-->
                    <#--<dd>-->
                        <#--<div>-->
                            <#--<a href="/help/58">联系方式</a>-->
                        <#--</div>-->

                    <#--</dd>-->
                <#--</dl>-->
            <#--</div>-->
            <!--/service-->

            <div class="footer mt20">
                <ul class="ft_links tc">
                </ul>
                <!--/ft_links-->

                <div style="margin: 15px 0px;" id="bq">
                    <a style="color:#666666;font-family:tahoma, arial, 宋体;line-height:normal;text-align:center;background-color:#FFFFFF;"></a>
                    <span style="color:#666666;font-family:tahoma, arial, 宋体;line-height:normal;text-align:center;background-color:#FFFFFF;"> </span>
                    <#--<ul class="ft_links tc" style="color:#666666;font-family:tahoma, arial, 宋体;line-height:normal;text-align:center;margin:0px;padding:0px;list-style:none;background-color:#FFFFFF;">-->
                        <#--<a></a>-->
                        <#--<li style="margin:0px 5px;padding:0px;display:inline-block;zoom:1;">-->
                            <#--<a href="http://www.baidu.com"></a>-->
                            <#--<a href="http://www.qianmi.com" target="_blank">关于我们</a>-->
                        <#--</li>-->
                        <#--|-->
                        <#--<li style="margin:0px 5px;padding:0px;display:inline-block;zoom:1;">-->
                            <#--<a href="http://www.qianmi.com" target="_blank">联系我们</a>-->
                        <#--</li>-->
                        <#--|-->
                        <#--<li style="margin:0px 5px;padding:0px;display:inline-block;zoom:1;">-->
                            <#--<a href="http://www.qianmi.com" target="_blank">人才招聘</a>-->
                        <#--</li>-->
                        <#--|-->
                        <#--<li style="margin:0px 5px;padding:0px;display:inline-block;zoom:1;">-->
                            <#--<a href="http://www.baidu.com" target="_blank">商家入驻</a>-->
                        <#--</li>-->
                        <#--|-->
                        <#--<li style="margin:0px 5px;padding:0px;display:inline-block;zoom:1;">-->
                            <#--<a href="http://www.qianmi.com" target="_blank">广告服务</a>-->
                        <#--</li>-->
                        <#--|-->
                        <#--<li style="margin:0px 5px;padding:0px;display:inline-block;zoom:1;">-->
                            <#--<a href="http://www.qianmi.com" target="_blank">手机千米</a>-->
                        <#--</li>-->
                        <#--|-->
                        <#--<li style="margin:0px 5px;padding:0px;display:inline-block;zoom:1;">-->
                            <#--<a href="http://qpmall.qianmi.com/toFriendLink.html" target="_blank">友情链接</a>-->
                        <#--</li>-->
                        <#--|-->
                        <#--<li style="margin:0px 5px;padding:0px;display:inline-block;zoom:1;">-->
                            <#--<a href="http://www.qianmi.com" target="_blank">销售联盟</a>-->
                        <#--</li>-->
                        <#--|-->
                        <#--<li style="margin:0px 5px;padding:0px;display:inline-block;zoom:1;">-->
                            <#--<a href="http://www.qianmi.com" target="_blank">English Site</a>-->
                        <#--</li>-->
                    <#--</ul>-->
                    <div class="copyright tc mt15" style="color:#666666;font-family:tahoma, arial, 宋体;line-height:normal;text-align:center;margin:15px 0px 0px;padding:0px;background-color:#FFFFFF;">
                        <p class="mb20" style="margin-top:0px;margin-bottom:20px;padding:0px;">
                        ${basicSet.thirdCopyright!''}
                        </p>
                        <p>
                            <br>
                        </p>
                    </div>

                    <ul style="text-align:center;margin-top:20px;">
                        <!--站长统计-->
                        <li>
                            <script type="text/javascript">function show() {
                            }</script>
                        </li>
                    </ul>
                </div>

            </div>
            <!--/footer-->
        </div>

    </body>
<script type="text/javascript">


    $(function(){
        //验证码绑定onclick事件
        $("#checkCodeA").click(
                function(){
                    $("#checkCodeImg").click();
                }
        );
        $("#sendCode").click(
                function(){
                    //验证码
                    var enterValue = $("#varification").val();
                    if(enterValue != ''&&enterValue!=null){

                        $.ajax({
                            url: "checkpatchca.htm?enterValue="+enterValue,
                            context: document.body,
                            async:false,
                            success: function(data){
                                if(data==0){
                                    $(".varification").html('请输入正确的验证码再发送短信');
                                    $(".varification").show();
                                }else{
                                        $(".varification").html('');
                                        $(this).attr("disabled","disabled");
                                        var mobile = "${userMobile}";
                                        var datas = "mobile=" + mobile;
                                        $.ajax({
                                            type: 'get',
                                            url:'sendcodetovalidate.htm',
                                            data:datas,
                                            async:false,
                                            success: function(data) {
                                                if(data==1) {
                                                    $(".varification").html('');
                                                    $(".mobile_code").show();
                                                    setTimeout(countDown, 1000);
                                                    $(this).removeAttr("disabled");
                                                    $("#sendCode").attr("disabled","disabled");
                                                    $("#checkCodeImg").click();
                                                }else if(data==0){
                                                    //网络异常
                                                    $(".mobile_tip").html('网络异常请稍后再试');
                                                    $(".mobile_tip").show();
                                                    $("#sendCode").removeAttr("disabled");
                                                }else if(data==-1){

                                                    $(".mobile_tip").html('60秒内只能获取一次验证码,请稍后重试');
                                                    $(".mobile_tip").show();
                                                }
                                            },
                                            error : function() {
                                                //网络异常
                                                $(".mobile_tip").html('网络异常请稍后再试');
                                                $(".mobile_tip").show();
                                            }
                                        });
                                    };

                            }
                        });

                    }
                    else{
                        if(enterValue == ''||enterValue==null){
                            $(".varification").html('请输入验证码');
                            $(".varification").show();
                        }

                    }

                    function countDown(){
                        var time = $(".timeleft").text();
                        $(".timeleft").text(time - 1);
                        if (time == 1) {
                            $(".mobile_code").hide();
                            $("#sendCode").removeAttr("disabled");
                        } else {
                            setTimeout(countDown, 1000);
                        }
                    }
                });

    });


    function checkmobilecode(){
        //短信验证码
        var sendCode = $("#dynamiccode").val();
        //验证码
        var enterValue = $("#varification").val();
        var str=true;
        if (enterValue != '' && enterValue!=null) {

            if(sendCode!='' && sendCode!=null){
                $.ajax({
                    url: "checkmobilecode.htm?codetext=" + sendCode + "&mobile=" + ${userMobile},
                    context: document.body,
                    async:false,
                    success: function (data) {

                        if (data == 0) {
                            $(".mobile_tip").html('短信验证码不正确');
                            $(".mobile_tip").show();
                            $("#sendCode").removeAttr("disabled");
                            //如果失败修更新验证码
                            $("#checkCodeImg").click();
                            str=str&&false;
                        } else {
                            $(".varification").html('');
                            str=str&&true;
                        }

                    }
                });
            }
            else{
                $(".mobile_tip").html('请输入短信验证码');
                $(".mobile_tip").show();
                str=str&&false;
            }

        }
        else {
            $(".varification").html('请输入验证码');
            $(".varification").show();
            str=str&&false;
        }


        return str;

    }


    function tonextThree(){
        var mobile = "${userMobile}";
        //验证码
        var enterValue = $("#varification").val();
        //短信验证码
        var codetext=$("#dynamiccode").val();
        if(enterValue != ''&&enterValue!=null) {
            $(".varification").hide();
            if(checkmobilecode()){
                $.ajax({
                    url: "checkpatchca.htm?enterValue=" + enterValue,
                    async: false,
                    success: function (data) {
                        if(data==0){
                            $(".varification").html('验证码不正确');
                            $(".varification").show();

                        }else{

                            if(codetext==''||codetext==null){
                                $(".mobile_tip").html('请输入短信验证码');
                                $(".mobile_tip").show();
                            }
                            else{
                                $.ajax({
                                    url: "checkmobilecode.htm?codetext=" + codetext + "&mobile=" + mobile,
                                    context: document.body,
                                    async:false,
                                    success: function (data) {
                                        if (data == 0) {
                                            $(".mobile_tip").html('短信验证码不正确');
                                            $(".mobile_tip").show();
                                            $("#sendCode").removeAttr("disabled");
                                            //如果失败修更新验证码
                                            $("#checkCodeImg").click();
                                        } else {
                                            window.location.href="${basePath}/findCodeThree.htm?mobile="+mobile+"&codetext="+codetext;
                                        }
                                    }
                                });
                            }

                        }
                    }
                });
            }
            else{

            }

        }else{
            $(".varification").html("请输入验证码");
            $(".varification").show();
        }
    }

</script>
</html>