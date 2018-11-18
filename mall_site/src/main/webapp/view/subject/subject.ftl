<#assign basePath=request.contextPath>
<style>
body {background-image: url(${(subject.backgroundImg)!''});}
</style>
<script src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script>
    function browserRedirect() {
        var sUserAgent = navigator.userAgent.toLowerCase();
        var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
        var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
        var bIsMidp = sUserAgent.match(/midp/i) == "midp";
        var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
        var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
        var bIsAndroid = sUserAgent.match(/android/i) == "android";
        var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
        var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
        if (bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
            //跳转到移动版
            <#if ((mobProjectUrl??) && (mobProjectUrl?length>0))>
                location = "${mobProjectUrl}";
            </#if>
        }
    }
    //2015-12-10 暂时屏蔽通过PC端地址访问移动端
    //browserRedirect();
    //根据样式类名获取元素标记
    function getElementsByClassName(n) {
        var classElements = [],allElements = document.getElementsByTagName('p');//所有p元素
        for (var i=0; i< allElements.length; i++ )
        {
            if (allElements[i].className == n ) {
                classElements[classElements.length] = allElements[i];
            }
        }
        return classElements;
    }
    //根据登录人员的会员类型，加载价格
    $(function(){
        var vip = $("#vip").val();
        $.each($("p[class='topic_price']"), function(i,n){
            var price = $($(this).find("strong")).html();
            var priceArr = price.split(" ");
            var vipPrice,normalPrice;
            if(priceArr.length > 1){
                normalPrice = priceArr[0]
                vipPrice = priceArr[1];
            }else{
                normalPrice = priceArr[0];
                vipPrice = normalPrice;
            }
            if(vip == "0"){
                $($(this).find("strong")).html(normalPrice);
            }else if(vip == "1"){
                $($(this).find("strong")).html(vipPrice);
            }
        });
    });
</script>
<input type="hidden" id="vip" name="vip" value="${vip!''}"/>
${(subject.content)!''}
