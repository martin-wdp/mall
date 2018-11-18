<!doctype html>
<html lang="en">
    <head>
    <#assign basePath=request.contextPath>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="renderer" content="webkit">
        <title>商家注册</title>
        <link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
        <script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="${basePath}/js/register/newregister.js"></script>
        <script type="text/javascript" src="${basePath}/js/default.js"></script>
        <style type="text/css">
             /*须加的样式*/
             .n_row .n_text {
                    width: 100%;
                    height: 36px;
                    border: 1px solid #d3d3d3;
                    border-radius: 3px;
                    line-height: 36px;
                    text-indent: 90px;
                }
             .tip-dia{ width: 980px;}
             .tip-dia .title{ height: 60px; line-height: 60px; font-size: 26px; color: #333; padding:0 10px; background: #ddd;}
             .tip-dia .title a{ color: #bbbbbb; font-size: 40px; font-weight: bold; float: right;}
             .tip-dia .cont{ background: #fff; padding:20px; min-height: 230px;}
             .tip-dia .cont ul li{ color: #999; font-size: 20px; line-height: 34px;}

             n_error {
                 border-color: #f2445d!important;
             }
             #sendCode {
                 border: 1px solid #ddd;
                 background-color: #fff;
                 color: #666;
                 width: 75px;
                 height: 38px;
                 border-radius: 3px;
                 text-align: center;
                 line-height: 38px;
                 display: block;
                 float: right;
                 text-indent: 0;
             }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="head2">
                <a href="${basicSet.bsetAddress}">
                    <img id="logo_pic" alt="" src="${basicSet.bsetThirdLogo!''}" style="height:45px;width:auto;">
                </a>
                <h1>商家注册</h1>
            </div>
        </div>
        <div class="n_login_bg" style="background-image:url( <#if basicSet??&&basicSet.thirdRegImg??>${basicSet.thirdRegImg}<#else> ../../images/rebg_1.jpg</#if>); background-position:top center; background-repeat: no-repeat;height:513px;">
            <div class="container" style="width:900px;">
                <div class="new_login clearfix" style="margin-top:6px;">
                    <div class="new_login_con" style="padding-bottom:5px;">
                        <div class="n_title clearfix">
                            <span>商家入驻注册</span>
                            <p>已有帐号在此
                                <a href="${basePath}/login.html">登录</a>
                            </p>
                        </div>
                        <form action="${basePath}/thirdRegisterCustomer.htm" method="post" id="userform">
                        <div class="mt20">
                            <div class="n_row" style="margin-bottom:25px;">
                                <input type="text" class="n_text checkname1" name="customerUsername" id="act_user"/>
                                <span class="n_rg">用户名：</span>
                                <div class="form_tips ">请填写6-20位字母、数字或下划线组成的用户名</div>
                                <div class="n_tips checkname2">您输入的用户名不正确</div>
                            </div>
                            <div class="n_row" style="margin-bottom:25px;">
                                <input  class="n_text checkpassword1" name="customerPassword" type="password"/>
                                <span class="n_rg ">设置密码：</span>
                                <div class="form_tips">密码必须是6-20位</div>
                                <div class="n_tips checkpassword2">您输入的密码不正确</div>
                            </div>
                            <div class="n_row" style="margin-bottom:25px;">
                                <input  class="n_text checkrepassword1" type="password"/>
                                <span class="n_rg ">确认密码：</span>
                                <div class="form_tips">密码必须是6-20位</div>
                                <div class="n_tips checkrepassword2">您输入的密码不正确</div>
                            </div>
                            <div class="n_row" style="margin-bottom:25px;">
                                <input type="text" class="n_text" style="width:145px;" name="varification" id="varification"/>
                                <img id="checkCodeImg" src="${basePath}/patchca.htm" onclick="this.src=this.src+'?'+Math.random(); "  style="border:1px solid #ddd;display:inline-block;vertical-align:middle; margin-left:5px;"/>
                                <a id="checkCodeA" href="#" class="ml10 bluee">换一张</a>
                                <span class="n_rg">验证码：</span>
                                <div class="n_tips varification">您输入的验证码不正确</div>
                            </div>
                            <div class="n_row" style="margin-bottom:25px;">
                                <input type="text" class="n_text checkmobile1" style="width:220px;"  name="infoMobile"/>
                                <button type="button" id="sendCode" class="f14" onclick="getCode(this)">发送短信</button>
                                <span class="n_rg">手机：</span>
                                <div class="form_tips checkmobile3">请输入正确的手机号,我们将免费的校验码发送到您的手机</div>
                                <div class="n_tips checkmobile2">您输入的验证码不正确</div>
                            </div>
                            <div class="n_row" style="margin-bottom:5px;">
                                <input type="text" class="n_text dynamiccode1" style="width:220px; text-indent:90px;" name="dynamiccode"/>
                                <span href="#" class="f14">输入验证码</span>
                                <span class="n_rg" style="width:85px;">短信验证码：</span>
                                <div class="n_tips dynamiccode2">您输入的短信验证码不正确</div>
                            </div>

                            <div class="n_row" style="margin-bottom:6px;">

                                <input type="checkbox" name="remember" class="re" id="readme">
                                <label>&nbsp;&nbsp;我已阅读并同意
                                    <a href="#" class="bluee" onclick="dia(1)">《商家注册协议》</a>
                                </label>
                                <div id="protocol_error" class="n_tips" style="position:static;">请接受服务条款</div>
                            </div>
                            <div class="n_row" style="margin-bottom:0;">
                                <a href="#">
                                    <input type="button" class="n_btn"  onclick="reg()"  value="同意并注册"/>
                                </a>
                            </div>

                        </div>
                            </form>
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
                        <#--<a href="http://www.qianmi.com/cooperation.html" target="_blank">商家入驻</a>-->
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
         <div class="mask"></div>
         <div class="dialog dia1 tip-dia">
             <div class="title">注册须知<a href="#" onclick="cls()">×</a></div>
             <div class="cont" style="height:400px; overflow-y: auto; ">
             ${(basicSet.thirdUserment)!''}

                 <#--<ul>-->
                     <#--<li>1、如果您已经在商城个人注册页面注册过账号，您可以直接使用个人账户直接登录。</li>-->
                     <#--<li>2、在此页面注册的账号，可在商城个人注册页面直接登录进行购物。</li>-->
                     <#--<li>3、为了提高您的用户体验和账户安全，我们建议验证您的手机或者邮箱，验证过后，您将会得-->

     <#--到如下服务；入驻申请审核通过后，我们将会通过邮件或者手机通知您，如果您的账户密码丢失-->

     <#--您可以通过手机或者邮件的方式找回，否则您将无法找回密码。</li>-->
                 <#--</ul>-->
             </div>
         </div>

        <script type="text/javascript">

            $(".n_text").each(function () {
                $(this).focus(function () {
                    $(this).next().next(".form_tips").show();
                });
                $(this).blur(function () {
                    $(this).next().next(".form_tips").hide();
                })
            })


        </script>
    </body>
</html>