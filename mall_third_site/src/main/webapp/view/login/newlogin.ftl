<!doctype html>
<html lang="en">
    <head>
    <#assign basePath=request.contextPath>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="renderer" content="webkit">
        <title>${basicSet.bsetName}</title>
        <link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
        <script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="${basePath}/js/login/newlogin.js"></script>
        <script type="text/javascript" src="${basePath}/js/third.js"></script>
   <style type="text/css">
       error {
           border-color: #f2445d!important;
       }
   </style>

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
                <h1>商家登录</h1>
            </div>
        </div>
        <div class="n_login_bg" style="background-image:url(
        <#if basicSet.thirdLoginImg??>${basicSet.thirdLoginImg} <#else>../../images/bg_1.jpg</#if>
        ); background-position:center center; background-repeat: no-repeat;height:460px;">
            <div class="container" style="width:900px;">
                <div class="new_login clearfix" style="margin-top:40px;">
                    <div class="new_login_con">
                        <div class="n_title clearfix">
                            <span>商家登录</span>
                            <p>还没有商家账号？马上
                                <a href="${basePath}/register.html">免费注册</a>
                            </p>
                        </div>
                        <div class="mt20">
                            <div class="n_row" style="margin-bottom:25px;">
                                <input type="text" onBlur="pwd()" placeholder="用户名" class="n_text" id="managername" name="name"/>
                                <span id="login_name"></span>
                                <div class="n_tips username_tip" >您输入的用户名不正确</div>
                            </div>
                            <div class="n_row" style="margin-bottom:25px;">
                                <input type="password" onBlur="pwd()"  name="password" placeholder="密码" class="n_text" oncopy="return false;" onpaste="return false" oncut="return false;" id="managerpassword"/>
                                <span id="login_code"></span>
                                <div class="n_tips password_tip">您输入的密码不正确</div>
                            </div>
                            <div class="n_row" style="margin-bottom:25px;">
                                <input type="text" class="n_text" name="captcha" id="captcha" style="width:156px;text-indent: 70px;"/>
                                <div class="air-yanzhen">
                                    <img id="checkCodeImg" class="code_image"  src="${basePath}/patchca.htm" onclick="this.src=this.src+'?'+Math.random(); "/>
                                </div>
                                <a href="#" id="checkCodeA" class="ml10 bluee">换一张</a>
                                <span class="n_rg">验证码：</span>
                                <div class="n_tips captcha_tip">您输入的验证码不正确</div>
                            </div>
                            <div class="n_row">
                                <a href="findCodeOne.htm" class="fr">忘记密码？</a>
                                <#--<input type="checkbox" name="remember" class="re">-->
                                <label>&nbsp;&nbsp;</label>
                            </div>
                            <div class="n_row">
                                <a href="#">
                                    <input type="button"  onclick="login()" class="n_btn" value="登 录"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container pt30">
            <div style="margin: 15px 0px;" id="bq">
                <a style="color:#666666;font-family:tahoma, arial, 宋体;line-height:normal;text-align:center;background-color:#FFFFFF;"></a>
                <span style="color:#666666;font-family:tahoma, arial, 宋体;line-height:normal;text-align:center;background-color:#FFFFFF;"> </span>
                <#--<ul class="ft_links tc" style="color:#666666;font-family:tahoma, arial, 宋体;line-height:normal;text-align:center;margin:0px;padding:0px;list-style:none;background-color:#FFFFFF;">-->
                    <#--<a></a>-->
                    <#--<li style="margin:0px 5px;padding:0px;display:inline-block;*display:inline;zoom:1;">-->
                        <#--<a href="http://www.baidu.com"></a>-->
                        <#--<a href="http://www.qianmi.com" target="_blank">关于我们</a>-->
                    <#--</li>-->
                    <#--|-->
                    <#--<li style="margin:0px 5px;padding:0px;display:inline-block;*display:inline;zoom:1;">-->
                        <#--<a href="http://www.qianmi.com" target="_blank">联系我们</a>-->
                    <#--</li>-->
                    <#--|-->
                    <#--<li style="margin:0px 5px;padding:0px;display:inline-block;*display:inline;zoom:1;">-->
                        <#--<a href="http://www.qianmi.com" target="_blank">人才招聘</a>-->
                    <#--</li>-->
                    <#--|-->
                    <#--<li style="margin:0px 5px;padding:0px;display:inline-block;*display:inline;zoom:1;">-->
                        <#--<a href="http://www.baidu.com" target="_blank">商家入驻</a>-->
                    <#--</li>-->
                    <#--|-->
                    <#--<li style="margin:0px 5px;padding:0px;display:inline-block;*display:inline;zoom:1;">-->
                        <#--<a href="http://www.qianmi.com" target="_blank">广告服务</a>-->
                    <#--</li>-->
                    <#--|-->
                    <#--<li style="margin:0px 5px;padding:0px;display:inline-block;*display:inline;zoom:1;">-->
                        <#--<a href="http://www.qianmi.com" target="_blank">手机千米</a>-->
                    <#--</li>-->
                    <#--|-->
                    <#--<li style="margin:0px 5px;padding:0px;display:inline-block;*display:inline;zoom:1;">-->
                        <#--<a href="http://qpmall.qianmi.com/toFriendLink.html" target="_blank">友情链接</a>-->
                    <#--</li>-->
                    <#--|-->
                    <#--<li style="margin:0px 5px;padding:0px;display:inline-block;*display:inline;zoom:1;">-->
                        <#--<a href="http://www.qianmi.com" target="_blank">销售联盟</a>-->
                    <#--</li>-->
                    <#--|-->
                    <#--<li style="margin:0px 5px;padding:0px;display:inline-block;*display:inline;zoom:1;">-->
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

            </div>
        </div>

        <script type="text/javascript">
            //背景图片
            $(document).ready(function () {
                <#--var random_bg = Math.floor(Math.random() * 4 + 1);-->
                <#--var bg = 'url(${basePath}/images/login/bg_' + random_bg + '.jpg)';-->
                <#--$(".n_login_bg").css("background-image", bg);-->
            });


            document.onkeydown=function(event){
                var e = event || window.event || arguments.callee.caller.arguments[0];
                if(e && e.keyCode==13){ // enter 键
                    login();
                }
            };

            //用户名 密码失去焦点 隐藏提示语句
            function pwd(){
                $('.password_tip').html('');
                $('.username_tip').html('');
            }
        </script>
    </body>
</html>