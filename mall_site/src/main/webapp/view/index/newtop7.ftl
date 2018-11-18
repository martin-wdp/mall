<link rel="stylesheet" href="${basePath}/index_seven/css/style.css"/>
<script type="text/javascript" src="${basePath}/js/carmodel/jquery.qrcode.min.js"></script>
<script type="text/javascript" src="${basePath}/js/carmodel/qrcode.js"></script>
<input type="hidden" id="basePath" value="${basePath}"/>
<input type="hidden" id="vip" name="vip" value="${vip!'0'}"/>
<div id="qrcode2" style="width:200px; height:200px;position:absolute;left:-1000px;"></div>
<#--<div class="section_headerTop" id="iecompatibleshow" style="display: none;background-color: #EFD38C">
    <div class="slot slot_headerTop01">

        <ul class="siteNav left">
            <li><span>提示：本网站建议使用谷歌，火狐等浏览器会更加流畅，不推荐使用IE浏览器，360浏览器，给您带来不便请谅解！</span></li>
        </ul>
        <ul class="siteNav right" style="float: left">

            <li class="login_info back-img-border-right">
                <a class="site_login" href="#" onclick="iecompatiblehide()" title="我知道了关闭提示" style="width: 30px" target="_self">关闭</a>
            </li>
        </ul>
    </div>
    <div class="slot"></div>
</div>-->

<div class="section_headerTop">
    <div class="slot slot_headerTop01">

        <ul class="siteNav left">
            <li class="left-li-first"><span>您好<#if cust??><#if vip=="1"><label class="vip">${cust.customerNickname!''}</label><#else>${cust.customerNickname!''}</#if></#if>，欢迎来到${topmap.systembase.bsetName}！</span></li>
        </ul>
        <ul class="siteNav right">
            <input type="hidden" id="customer_id" value="<#if cust??>${cust.customerId!''}</#if>"/>
        <#if cust??>
        <#else>
            <li class="login_info back-img-border-right">
                <a class="site_login" href="${basePath}/login.html" target="_self">请先登录</a>
            </li>
        </#if>
        <#if cust??>
        <#else>
            <li class="login_info back-img-border-right" style="background-color: #2D2D2D;">
                <a class="site_register" href="${basePath}/register.html" target="_self" style="color: #F4AC00;">免费注册</a>
            </li>
        </#if>
        <#if cust??>
            <li class="loginout_info back-img-border-right">
                <a class="site_loginout" href="${basePath}/logout.html" target="_self">退出</a>
            </li>
        </#if>
            <li class="nav_item navCenter back-img-border-right">
                <a href="${basePath}/customer/index.html" >个人中心<s></s></a>

                <div class="navInfo">
                    <a href="${basePath}/customer/myorder.html">我的订单</a>
                </div>
            </li>
            <li class="nav_item navCenter back-img-border-right">
                <a href="javascript:" target="_self">下载中心<s></s></a>
                <div class="navInfo down">
                    <a class="khd" href="${basePath}/software/qpmall-client.exe">客户端下载</a>
                    <span style="display:block;width:90%;height:1px;margin:0 auto;background:#ccc;"></span>
                    <a class="gys" href="${basePath}/software/qpmall-supply.exe">供应商端下载</a>
                </div>
            </li>
            <li class="nav_item back-img-border-right"><a href="javascript:" onclick="shareUrl();" target="_self">分享</a></li>
            <li class="nav_item right-li-last"><span style="cursor: pointer;" onclick="openCustServiceChat();">联系客服</span></li>
        </ul>
    </div>
    <div class="slot"></div>
</div>
<div class="bh-mask" style="filter:alpha(opacity=70);"></div>
<div id="ctDia" class="bh-dialog dia0">
    <div class="dia-tit">
        <h4>加入收藏</h4>
        <a class="dia-close" href="javascript:" onclick="clss(this)" target="_self"></a>
    </div>
    <div class="dia-cont">
        <p style="text-align: center;line-height:100px;">请使用菜单栏或Ctrl+D进行添加！</p>
    </div>
    <div class="dia-btn"><a href="javascript:" onclick="clss(this)" target="_self">确定</a></div>
</div>

<div id="ctDia" class="bh-dialog dia101">
    <div class="dia-tit">
        <h4>分享好友</h4>
        <a class="dia-close" href="javascript:" onclick="clss(this)" target="_self"></a>
    </div>
    <div class="dia-cont">
        <div class="dia_collect_intro tc pt30" style="text-align: center;padding-top: 30px;">
            <span id="content" style="font-size:16px"></span><br />
            <#--<img id="erimage" src="../../images/brand/erloading.gif" style="max-width: 150px;">-->
            <img class="dia-logo" src="" width="50px" height="50px" style=" position: absolute;top: 150px; left: 200px">
            <div id="qrcode" style="width:150px; height:150px; margin: 0 auto"></div>
        </div>

        <div class="dia-btn" style="padding-top: 30px;">
            <a id="copy" data-clipboard-target="content" href="javascript:" target="_self">复制</a>
            <a onclick="clss(this)" href="javascript:" target="_self">取消</a>
            <br/>
            <span style="color: red; font-size:12px">亲！如果您的好友成功注册为会员，会有积分送给您哦！</span>
        </div>
        <!--/dia_ops-->
    </div>
    <!--/dia_cont-->
</div><!--/dialog-->


<div id="ctDia" class="bh-dialog dia102">
    <div class="dia-tit">
        <h4>登录提示</h4>
        <a class="dia-close" href="javascript:" onclick="clss(this)" target="_self"></a>
    </div>
    <div class="dia-cont">
        <p style="text-align: center;line-height:100px;">您未登录！现在确认要登录吗？</p>
    </div>
    <div class="dia-btn">
        <a onclick="login()" href="javascript:" target="_self">确定</a>
        <a href="javascript:" onclick="clss(this)" target="_self">取消</a>
    </div>
</div><!--/dialog-->


<div id="ctDia" class="bh-dialog dia103">
    <div class="dia-tit">
        <h4>操作提示</h4>
        <a class="dia-close" href="javascript:" onclick="clss(this)" target="_self"></a>
    </div>
    <div class="dia-cont">
        <p style="text-align: center">复制成功！</p>
    </div>
    <div class="dia-btn"><a href="javascript:" onclick="clss()" target="_self">确定</a></div>

</div><!--/dialog-->

<script type="text/javascript" src="${basePath}/js/jquery.zclip.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.zclip.js"></script>
<script>
    $(function () {

        //分享生成二维码
        //shareUrl();

        var navigatorName = "Microsoft Internet Explorer";
        var isIE = false;
        if (!!window.ActiveXObject || ("ActiveXObject" in window) || window.navigator.userAgent.indexOf("MSIE")>0) {
            isIE = true;
        }
        if (isIE) {
            $("#iecompatibleshow").show();

        }
    });
    function iecompatiblehide(){
        $("#iecompatibleshow").hide();
    }
    function dias(n) {
        $(".bh-mask").fadeIn();
        $(".dia" + n).fadeIn();
    }
    function clss() {
        $(".bh-dialog").fadeOut();
        $(".bh-mask").fadeOut();
    }
    //分享链接给好友 弹出层
    function shareUrl() {
        var basePath = $('#basePath').val();
        var str = window.location.href;
        var b = str.indexOf('/', str.indexOf('/') + 2);
//		var a = str.indexOf("//")+2;
        var local = str.substring(0, b);
        var customerId = $('#customer_id').val(); //当前登录的用户ID
        if (customerId == null || customerId == "") {
            dias(102);
        } else {
            //给会员id加密
            var b = new Base64();
            var result = 'customer_id=' + customerId;
            var str = b.encode('"' + result + '"');
//            修改2015年11月27号，付陈林，修改二维码手机扫描时跳转到手机注册界面
//            原代码 var url = local + basePath + '/register.html?' + str;
            var url = local + basePath + '/mobile/register.html?' + str;
//            修改结束
            var logo=local+basePath+"/images/brand/logo.jpg";
            $('#content').html(url);
            /*if(logo.indexOf("www.qpmall.com")==-1){
                $("#erimage").attr("src","http://qr.liantu.com/api.php?w=150&text="+url);
            }
            else{
                $("#erimage").attr("src","http://qr.liantu.com/api.php?logo="+logo+"&w=150&text="+url);
            }*/
            //$("#output").html("");
            /*$("#output").qrcode({
                text: url,
                height: 150,
                width: 150,
                src: logo//这里配置Logo的地址即可。
            })*/

            var qrcode1 = new QRCode($("#qrcode2")[0],{
                width : 150,
                height : 150
            });
            $(".dia-logo").attr("src",logo);
            qrcode1.makeCode(url);
            setTimeout(function(){
                $("#qrcode").html($("#qrcode2").html());
                $("#qrcode2").html("");
            },100);

            dias(101);
        }
        //复制成功弹出复制成功窗口
        $('#copy').zclip({
            path: '${basePath}/js/ZeroClipboard.swf',
            copy: function () {
//                Edit by: 付陈林, Time:2015年11月30日
//                Content:修改了点击复制的时候关闭主窗口 ,添加内容clss(101)
                clss(101);
//                  Edit end
                return $("#content").html();
            },
            afterCopy: function () {
                dias(103);
            }
        });
    }

    //推荐该网站给好友- 登录
    function login() {
        var basePath = $('#basePath').val();
        //获取页面登录的 href
        window.location.href = basePath + '/login.html';

    }

    //对会员ID进行加密
    function Base64() {
        var _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
        this.encode = function (input) {
            var output = "";
            var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
            var i = 0;
            input = _utf8_encode(input);
            while (i < input.length) {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);
                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;
                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }
                output1 = output + _keyStr.charAt(enc1) + _keyStr.charAt(enc2) + _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
            }
            return output1;
        };

        // public method for decoding
        this.decode = function (input) {
            var output = "";
            var chr1, chr2, chr3;
            var enc1, enc2, enc3, enc4;
            var i = 0;
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
            while (i < input.length) {
                enc1 = _keyStr.indexOf(input.charAt(i++));
                enc2 = _keyStr.indexOf(input.charAt(i++));
                enc3 = _keyStr.indexOf(input.charAt(i++));
                enc4 = _keyStr.indexOf(input.charAt(i++));
                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;
                output = output + String.fromCharCode(chr1);
                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }
            }
            output = _utf8_decode(output);
            return output;
        };

        // private method for UTF-8 encoding
        _utf8_encode = function (string) {
            string = string.replace(/\r\n/g, "\n");
            var utftext = "";
            for (var n = 0; n < string.length; n++) {
                var c = string.charCodeAt(n);
                if (c < 128) {
                    utftext += String.fromCharCode(c);
                } else if ((c > 127) && (c < 2048)) {
                    utftext += String.fromCharCode((c >> 6) | 192);
                    utftext += String.fromCharCode((c & 63) | 128);
                } else {
                    utftext += String.fromCharCode((c >> 12) | 224);
                    utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                    utftext += String.fromCharCode((c & 63) | 128);
                }

            }
            return utftext;
        };

        // private method for UTF-8 decoding
        _utf8_decode = function (utftext) {
            var string = "";
            var i = 0;
            var c = c1 = c2 = 0;
            while (i < utftext.length) {
                c = utftext.charCodeAt(i);
                if (c < 128) {
                    string += String.fromCharCode(c);
                    i++;
                } else if ((c > 191) && (c < 224)) {
                    c2 = utftext.charCodeAt(i + 1);
                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                    i += 2;
                } else {
                    c2 = utftext.charCodeAt(i + 1);
                    c3 = utftext.charCodeAt(i + 2);
                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                    i += 3;
                }
            }
            return string;
        }
    }

    /*客服咨询代码  begin */
    NTKF_PARAM = {
        siteid: 'cj_1000', //平台基础id
        settingid: 'cj_1000_9999', //Ntalker分配的缺省客服组id，平台客服组用平台的settingid，商家客服组用商家的settingid
        uid: '<#if cust??>${cust.customerId}</#if>',  //用户id
        uname: '<#if cust??>${cust.customerUsername}</#if>', //用户名
        userlevel: '0' //用户级别，1为vip用户，0为普通用户
    };

    //打开客服咨询对话窗口
    function openCustServiceChat() {
        //alert(NTKF_PARAM.ntalkerparam.item.url);
        NTKF.im_openInPageChat('cj_1000_9999');
    }

    /*客服咨询代码  end */
</script>