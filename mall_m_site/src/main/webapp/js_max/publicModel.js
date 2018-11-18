/**
 * Created by Administrator on 2015/11/25 0025.
 */
var basePath = $("#basePath").val();
FastClick.attach(document.body);
$("#keleyi-menu").keleyi({
    width: '100%',
    bar_height:'60px',
    item_background_color: '#1D1D1D',
    item_background_color_hover: '#1D1D1D',
    item_margin: '0',
    bar_background_color: '#1D1D1D'
});
var _t = setTimeout(function(){
    $(".keleyi-menu").show();
},100);

$('.more_order_goods').click(function(){
    $(this).parent().prevAll().show();
    $(this).remove();
});


$('.fill_item input').focus(function(){
    $(this).parent().next().css('borderColor','#EB6122');
});
$('.fill_item input').blur(function(){
    $(this).parent().next().css('borderColor','#EEEEEE');
});

//收藏按钮
$(".goodsBtn i").bind("click",function(){
    var _this = this;
    var url =  $(this).attr("data-url");
    var goodsInfoId = $(this).attr("data-productId");
    //ajax 添加收藏
    $.ajax({
        type : 'post',
        url : url,
        async : false,
        data:{productId:goodsInfoId},
        success:function(data){
            if(data == "-1"){
                //已收藏的取消收藏
                $(_this).css("background-image","url("+basePath+"/images/qp_scb.png)");
            }else if(data == "1"){
                //添加收藏
                $(_this).css("background-image","url("+basePath+"/images/qp_sca.png)");
            }else if(data == "-2"){
                //未登录跳转登录
                window.location.href = basePath +"/login.html";
            }
        }
    });
});


//加入购物车
$(".goodsBtn button").click(function(){
    var count = $(this).attr("data-count");
    $(this).attr("data-count","1");
    //$(_this).attr({"disabled":"disabled"});
    if(count=="0"){
        var _this = this;
        var url =  $(this).attr("data-url");
        var goodsInfoId = $(this).attr("data-productId");
        $.ajax({
            type : 'post',
            url : url,
            async : false,
            data:{productId:goodsInfoId},
            success:function(data){
                    //......
                    //启用按钮
                    $(_this).children("span").show();
                    setTimeout(function(){
                        $(_this).children("span").hide();
                    },1000);
                    //$(_this).removeAttr("disabled");
                    $(_this).attr("data-count","0");
            },
            error : function(){
               // $(_this).removeAttr("disabled");
                $(_this).attr("data-count","0");
            }
        });
    }

});

//=========================================
/*      这里添加电话和客服的方法       */
//=========================================
$(".btn-phone").click(function(){
    //alert("电话");
});
$(".btn-customerService").click(function(){
    // alert("客服");
});


//倒计时
function showCountDown(basePath){
    var currentTime ="";
    var newBaseDate ="";
    var url = basePath+"/getCurrentTime.htm";
    $.ajax({
        url:url,
        async: false,
        success: function(data){
            currentTime=data.currentT;
            newBaseDate=data.newBaseT;
        }
    });
    var endDate = newBaseDate;
    var nowDate = currentTime;
    var timer = setInterval(function(){

        var time = parseInt((endDate - nowDate)/1000);
        var day = Math.floor(time / (60*60*24));
        time -= day * (60*60*24);
        var hour = Math.floor(time /(60*60));
        time -= hour * (60*60);
        var minute = Math.floor(time / 60);
        var second = time - (minute * 60);

        if(day<10){
            if(day<0){
                return;
            }else{
                day="0"+day;
            }
        }
        if(hour<10){
            hour="0"+hour;
        }
        if(minute<10){
            minute="0"+minute;
        }
        if(second<10){
            second="0"+second;
        }

        $("#day").html(day);
        $("#hour").html(hour);
        $("#minute").html(minute);
        $("#second").html(second);

        if(day=='00'&&hour=='00'&&minute=='00'&&second=='00'){
            clearInterval(timer);
        }
        nowDate += 1000;
    },1000);
}
/*var dev = GetQueryString("at");
if(dev == "1"){
    $(".app").show();
    $(".pc").hide();
}*/

function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}