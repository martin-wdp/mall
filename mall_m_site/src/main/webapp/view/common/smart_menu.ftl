<#if !(at?? && at == "1") || !(at?exists)>
<div class="smart_menu" >
    <ul id="keleyi-menu">
        <li><a href="${basePath}/main.html?tag=1"><img src="${basePath}/images/qp_za0.png" alt="首页"/><br/>首页</a></li>
        <li><a href="${basePath}/cates.html?tag=2"><img src="${basePath}/images/qp_zb0.png" alt="分类"/><br/>分类</a></li>
        <li><a href="${basePath}/myshoppingmcart.html?tag=3"><img src="${basePath}/images/qp_zc0.png" alt="购物车"/><br/>购物车</a>
        </li>
        <li id="me"><a href="${basePath}/customer/index.html?tag=4"><img src="${basePath}/images/qp_zd0.png" alt="我"/><br/>我</a>
        </li>
    </ul>
</div><!-- /smart_menu -->
</#if>
<script src="${basePath}/js/jquery.js"></script>
<script>
    var n = GetQueryString("tag");
//    var dev = GetQueryString("at");
//    if(dev=='1'){
//        $(".smart_menu").hide();
//        $(".cart_check").css("padding","1em 0.5em 2.2em 0.5em");
//    }
    $("#go").hide();
    switch(n){
        case '1':pictSty(n,"a");break;
        case '2':pictSty(n,"b");break;
        case '3':pictSty(n,"c");break;
        case '4':pictSty(n,"d");break;
        case '-1':$(".smart_menu").hide();$(".cart_check").css("padding","1em 0.5em 2.2em 0.5em");$("#go").show();break;
    }

    $("#keleyi-menu li").click(function(){
        pictSty(1,"a0");pictSty(2,"b0");
        pictSty(3,"c0");pictSty(4,"d0");
        $("#keleyi-menu li a").css("color","#fff");
        var n = $(this).index()+1;
        if( n == 1 ){
            pictSty(n,"a");
        }else if( n == 2 ){
            pictSty(n,"b");
        }else if( n == 3){
            pictSty(n,"c");
        }else if( n == 4){
            pictSty(4,"d");
            $("#meLi a").css("color","#fff");
        }
    });
    function pictSty(n,ch){
        $("#keleyi-menu li:nth-child("+n+") img").attr("src","${basePath}/images/qp_z"+ch+".png");
        $("#keleyi-menu li:nth-child("+n+") a").css("color","#E05D03");
    }

    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  unescape(r[2]); return null;
    }

</script>