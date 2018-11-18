/**
 * Created by Administrator on 2015/12/18 0018.
 */
var car = {
    brand : "奥迪",
    series : "",
    type : ""

};

$(".adapCarType").click(function(){
   $(".carTypeBox").show();
});




$(function(){
    var basePath = "http://"+window.location.host;
    //var basePath = "http://test.qpmall.com";
     $(".adap").html('<h3><i onclick="clickHiden()"></i>适配车型</h3><img class="loading" src="../images/loading4.gif" />');
     $.getJSON(basePath+"/getAutoCarTypeList.htm",{"productId":$("#productId").val()},function(data){
         var tt='<h3><i onclick="clickHiden()"></i>适配车型</h3>';
         $.each(data,function(k,v){
             tt+='<div class="cartypeBlock">';
                tt+='<h4 onclick="clickSystem(this)">'+ v.key+'</h4>';
                tt+='<div class="carList" num="1">';
                    tt+='<ul>';
                     $.each(v.valList2,function(k,v){
                        tt+='<li>'
                        +'<p onclick="clickType(this)">'+ v.key+'</p>'
                        +'<div class="twoList dis" num="0">';
                         $.each(v.valList2,function(k,v){
                             tt+='<span>'+v.key+'</span>'
                             +'<ul>';
                             var sttr=new Array();
                             var ii=0;
                             $.each(v.valList,function(k,v){
                                //tt+='<li>'+ v.autoStyleEngine+' '+ v.autoStyleGearbox+' '+ v.autoStyleProductiveYear+'年产'+'</li>';
                                 if(ii==0){
                                     sttr.push(v.autoStyleEngine + v.autoStyleGearbox + v.autoStyleProductiveYear);
                                     tt+='<li>'+ v.autoStyleEngine+' '+ v.autoStyleGearbox+' '+ v.autoStyleProductiveYear+'年产'+'</li>';
                                 }else{
                                     var tm=0;
                                     for (x in sttr)
                                     {
                                         if(sttr[x]==v.autoStyleEngine + v.autoStyleGearbox + v.autoStyleProductiveYear){
                                             tm=1;
                                         }
                                     }
                                     if(tm==0){
                                         sttr.push(v.autoStyleEngine + v.autoStyleGearbox + v.autoStyleProductiveYear);
                                         tt+='<li>'+ v.autoStyleEngine+' '+ v.autoStyleGearbox+' '+ v.autoStyleProductiveYear+'年产'+'</li>';
                                     }
                                 }
                                 ii++;
                             });
                             tt+='</ul>';
                         });
                        tt+='</div>'
                        +'</li>';
                     });
                    tt+='</ul>';
                tt+='</div>';
             tt+='</div>';
         });
         if(data.length==0){
             $(".adap").html("<h3><i onclick=\"clickHiden()\"></i>适配车型</h3><span style='text-align: center;font-size: 20px;margin-top: 20px;'>暂无数据</span>");
         }else{
             $(".adap").html($(tt));
         }
     });

    //隐藏适配车型
    $(".adap h3 i").click(function(){

    });
});

function clickHiden(){
    $(".carTypeBox").hide();
}

//品牌
function clickSystem(e){
    var n = selectShow(e,".carList");
    if(n == 1){
        $(".twoList").hide().attr("num","0").parent().children("p").css({"color":"#ccc","background-image":"url(../images/qp_xqtj.png)","border-bottom":"1px solid #ccc"});
    }
    $(".twoList li").css("color","#000");
    car.brand  = $(e).html();
}
//车系
function clickType(e){
    var n = selectShow(e,".twoList");
    if(n==1){
        $(e).css({"color":"#ccc","background-image":"url(../images/qp_xqtj.png)","border-bottom":"1px solid #ccc"});
    }else{
        $(e).css({"color":"#f6ab00","background-image":"url(../images/qp_xqtj0.png)","border":"none"});
    }
    car.series = $(e).html();
}

/*//类型
function clickLi(e){
    $(".twoList li").css("color","#000");
    $(e).css("color","#f6ba00");
    car.type = $(e).html();
    var carType = car.brand+","+car.series+","+car.type;
    $(".adapCarType span").html(carType);//选中的车型写入页面中
    $(".carTypeBox").hide();
}*/

function selectShow(self,obj){
    var $blk = $(self).parent().children(obj);
    var num = $blk.attr("num");
    if(obj == ".carList"){
        $(".cartypeBlock h4").parent().children(".carList").hide().attr("num","0");
        $(".twoList").hide().attr("num","0").parent().children("p").css({"color":"#ccc","background-image":"url(../images/qp_xqtj.png)","border-bottom":"1px solid #ccc"});
    }
    if(num == 0){
        $blk.show();
        $blk.attr("num","1");
    }else{
        $blk.hide();
        $blk.attr("num","0");
    }
    return num;
}

