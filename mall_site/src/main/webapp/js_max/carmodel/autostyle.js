$(function(){
    var location = (window.location+'').split('/');
    //var basePath = location[0]+'//'+location[2]+'/'+location[3];
    var basePath = $("#basePath").val();
    var valstr="";
    var carstyleID="";
    var label = "<span class='clsCar' onclick='closeCar()' title='清除当前车型'>X</span>";
    $.getJSON(basePath+"/GetRecordByUserId.htm",{},function(data){
        var tt="";
        var count=0;
        for(var k=data.length-1;k>=0;k--) {
            v=data[k];
            if(count>5){
                break;
            }
            count++;
            //if(k)
            var str=v.autoStyleEngine==""?"":v.autoStyleEngine;
            var str2= v.autoStyleGearbox==""?"":v.autoStyleGearbox;
            if(typeof(str) == "undefined"){
                str="暂无";
            }
            if(typeof(str2) == "undefined"){
                str2="暂无";
            }
            var SalesName=v.autoStyleType.match(/\[([^\"]*)]/);
            var tmpsd='';
            if(SalesName==null){
                tmpsd=v.autoStyleType;
            }
            else{
                tmpsd=SalesName[1];
            }
            tt+='<li>'
            +'<a href="javascript:;">'+ v.goodsBrandName +" " + tmpsd + " "+v.autoStyleSalesName+" "+ v.autoStyleProductiveYear+"年产"+'</a>'
            +'<i class="view-det" id="'+ v.autoStyleIdLiyangyasuoId+'"></i>'
            +'</li>';
            //valstr=v.goodsBrandName +" "+ SalesName[1] +" " + v.autoStyleSalesName + " "+ v.autoStyleProductiveYear+"年产"+label;
            //valstr="请先选车型"+label;
            valstr="";
            carstyleID= v.autoStyleIdLiyangyasuoId;
        }
        if(valstr !=""){
            //var htmStr = "<i></i><em id='selCarType'>请先选车型</em>";
            //$(".carChange").css("background","#FFAF02").html(htmStr);
            //$(".carChange").css("background","#FFAF02").html($("#selectVal").val()+label);
        }
        $(".view-list").html(tt);
        //$("#carstyleID").val(carstyleID);

        //var htmStrImg = "<img src='"+basePath+"/images/btn-carType-bg.gif' height='27'/>";
        //$(".carChange").css({"background":"none","width":"100px"}).html(htmStrImg);

        /*if($(".carChange").children().length != 1){
         $(".carChange").width("250");
         $(".inputSearch").width("330");
         }*/
        $.get(basePath+"/getSeeeionVal.htm",{},function(data){
            //alert(data);
            if(data.indexOf("请先选车型")!=-1){
                //var htmStr = "<i></i><em id='selCarType'>请先选车型</em>";
                var htmStrImg = "<img src='"+basePath+"/images/btn-carType-bg.gif' height='27'/>";
                $(".carChange").css({"background":"none","width":"106px"}).html(htmStrImg);
                $(".inputSearch").width("480");
            }else{
                $(".carChange").css("padding-left","13px").html(data.split("&")[0]+label);
                $("#carstyleID").val(data.split("&")[1]);
            }
        },"text");
        if(carstyleID != "" && carstyleID != null){
            changeCarTypeBtnClass("2");
        }else{
            changeCarTypeBtnClass("1");
        }
    });

    //点击首页“请先选车型” 弹出弹层
    $(".carChange").click(function(){
        //carChange
        //alert("点我了");
        //$(".carChange").width("106");
        var _this = $(this).parent("li");
        var _index = _this.index();
        var _box = _this.parent(".switch-tabs");
        _this.addClass("on").siblings(".on").removeClass("on");
        _box.next(".tabs-wrap").children(".tabs-item:eq("+_index+")").addClass("on").siblings(".on").removeClass("on");
        //
        //if($(this).html().indexOf("浏览")>0){
        if(true){
            $.getJSON(basePath+"/GetRecordByUserId.htm",{},function(data){
                var tt="";

                var count=0;
                for(var k=data.length-1;k>=0;k--) {
                    v=data[k];
                    if(count>5){
                        break;
                    }
                    count++;
                    //if(k)
                    var str=v.autoStyleEngine==""?"":v.autoStyleEngine;
                    var str2= v.autoStyleGearbox==""?"":v.autoStyleGearbox;
                    if(typeof(str) == "undefined"){
                        str="暂无";
                    }
                    if(typeof(str2) == "undefined"){
                        str2="暂无";
                    }
                    var SalesName=v.autoStyleType.match(/\[([^\"]*)]/);
                    var tmpd='';
                    if (SalesName == null) {
                        tmpd = v.autoStyleType;
                    }else{
                        tmpd=SalesName[1];
                    }
                    tt+='<li>'
                    +'<a href="javascript:;">'+ v.goodsBrandName +" "+ tmpd + " "+ v.autoStyleSalesName+" "+ v.autoStyleProductiveYear+"年产"+'</a>'
                    +'<i class="view-det" id="'+ v.autoStyleIdLiyangyasuoId+'"></i>'
                    +'</li>';
                    valstr=v.goodsBrandName +" "+ tmpd + " "+v.autoStyleSalesName +" "+ v.autoStyleProductiveYear+"年产"+label;
                }
                $(".view-list").html(tt);
                // $("#selCarType").html($("#selectVal").val()+label);
                //$("#selCarType").html("请选择车型");
            });
            $(".tabs-wrap").find(".tabs-item:first").hide();
        }else{
            $(".tabs-wrap").find(".tabs-item:first").show();
        }
    });


    /*if($(".chosen-list li").length == 0) {
     $(".chosen-items").hide();
     } else {
     $(".chosen-items").show();
     };*/

    //点击品牌的abcd
    $("ul.brands-filter").on("click","li:not(#first)",function(){

        var _this = $(this);
        var _index = _this.index()-1;
        _this.addClass("on").siblings(".on").removeClass("on");
        $("div.brands-cont").find(".brand-filter-content").hide();
        $("div.brands-cont").find(".brand-filter-content:eq("+_index+")").show();
    });

    //删除已选规格
    $(".chosen-list").on("click", ".item-det", function(){
        var _index = $(this).parent().index();
        if(_index <= 3){
            $("#last_li").css("background-position","0 -200px");
        }
        $("div.tabs-item").find(".on").removeClass("on");
        if(_index == 0){
            $(this).parent().parent().find("li").remove();
            $("#yeartp").show();
            $(".brand-filter-content").first().removeAttr("style");
            $(".brands-filter").find("li").eq(1).addClass("on");
            $(".history_records2").show();
        }else{
            $(this).parent().parent().find("li:gt("+(_index-1)+")").remove();
        }
        $("ul.choose-process").find("li.chosen").removeClass("chosen");
        $("ul.choose-process").find("li:lt("+(_index+1)+")").addClass("chosen");

        $("div.tabs-item:eq("+(_index+1)+")").addClass("on");
        //$("ul.brands-filter li a:first").parent().addClass("on");
        //$(".brands-cont").find("div").hide();
        //$(".brands-cont").find("div:first").show();
        gethtml();
    });

    //点击选择品牌
    $(".choose-wp").on("click","a",function(){

        var _word = $(this).find(".choose-word").text();
        var salesname=$(this).find(".choose-word").attr("salesname");
        var _index = $(this).parents(".tabs-item").index();
        if(_index == 1){
            $(".history_records2").hide();
        }
        if(_index == 4){
            $("#last_li").css("background-position","0 -40px");
        }
        var _chosen='';
        if(salesname==null){
            _chosen = '<li data-index='+_index+'><span class="chosen-word">'+_word+'</span><i class="item-det"></i></li>';
        }
        else{
            _chosen = '<li data-index='+_index+'><span class="chosen-word" salesnames="'+salesname+'">'+_word+'</span><i class="item-det"></i></li>';
        }
        $(this).parents(".choose-wp").find(".on").removeClass("on");
        $(this).addClass("on");
        if($(".chosen-list").find("li[data-index="+_index+"]").length == 0) {
            $(".chosen-list").append(_chosen);
            $(".chosen-items").show();
        } else {
            $(".chosen-list").find("li[data-index="+_index+"]").remove();
            $(".chosen-list").append(_chosen);
        }
        $("ul.choose-process").find("li:eq("+(_index)+")").addClass("chosen");
        $("div.tabs-item").find(".on").removeClass("on");
        $(this).parent().hide();
        $("div.tabs-item:eq("+(_index+1)+")").addClass("on");
        $("#yeartp").hide();
        gethtml();
        return false;

    });

    //点击最后一个规格
    $(".items-content .tabs-item:last .models-cont").on("click","a",function(){
        $(".choose-tabs, .dia-content").hide();
        var autoStyleYear="";
        var goodsBrandName="";
        var autoStyleSystem="";
        var autoStyleType="";
        var autoStyleSweptVolume="";
        var SalesName="";
        var autoStyleSalesName='';

        $(".chosen-list").find("span").each(function(index,element){
            if(index==0){
                goodsBrandName=$(element).html();
            }
            if(index==1){
                autoStyleSystem=$(element).html();
            }
            if(index==2){
                autoStyleType=$(element).html();
                var st=autoStyleType.match(/\[([^\"]*)]/);
                if(st==null){
                    autoStyleType=autoStyleType;
                }else{
                    autoStyleType=st[1];
                }
                SalesName=$(element).attr("SalesNames");
            }
            if(index==3){
                autoStyleSweptVolume=$(element).html();
            }
        });

        autoStyleYear=$(this).find("span").html();
        autoStyleSalesName=$(this).attr("autoStyleSalesName");

        $("#valText").html(goodsBrandName+" "+autoStyleSystem+" "+autoStyleType+" "+autoStyleSweptVolume+" "+autoStyleYear+"年产");
        $("#selCarType").html(goodsBrandName+" "+autoStyleType+" "+" "+autoStyleSalesName+ " " +autoStyleYear+"年产"+label);
        $(".carChange").css("background","#FFAF02").css("padding-left","13px").html(goodsBrandName+" "+autoStyleType+" "+" "+autoStyleSalesName+ " " +autoStyleYear+"年产"+label);

        $(".choose-success").show();
        $(".carChange").width("247");
        $(".inputSearch").width("328");
        $("#yasuoid").val($(this).attr("yasuoid"));
        $("#carstyleID").val($(this).attr("yasuoid"));

        //alert(goodsBrandName+" "+autoStyleSystem+" "+autoStyleType+" "+autoStyleSweptVolume+" "+autoStyleYear+"年产");
        $.getJSON(basePath+"/AddRecord.htm",{"autoStyleIdLiyangyasuoId":$(this).attr("yasuoid"),"autoStyleYear":autoStyleYear,"goodsBrandName":goodsBrandName,"autoStyleType":autoStyleType,"autoStyleSalesName":autoStyleSalesName},function(data){
            if(data=="err"){
                alert("选择车型异常....请重新选择");
            }
        });
        setTimeout(function(){
            $(".choose-dialog").hide();
            $(".box").hide();
            $("#selCarType").attr("title",goodsBrandName+" "+autoStyleSystem+" "+autoStyleType+" "+autoStyleSweptVolume+" "+autoStyleYear+"年产");
            changeCarTypeBtnClass("2");
        },3000);//1000为1秒钟

    });

    //删除历史记录
    $(".view-list").on("click","i.view-det",function(){
        var id=$(this).attr("id");
        $(this).parent().remove();
        if(id==""){
            //$(this).parent("li").remove();
            return;
        }
        $.getJSON(basePath+"/DelRecord.htm",{"autoId":id},function(data){
            if(data=="err"){
                alert("删除异常...");
            }
        });
        var htmStrImg = "<img src='"+basePath+"/images/btn-carType-bg.gif' height='27'/>";
        $(".carChange").css({"background":"none","width":"106px","padding-left":"0px"}).html(htmStrImg);
        $(".inputSearch").width("480");
        $("#yasuoid").val("");
        $("#carstyleID").val("");

        /*var len =$(".view-list").find("li").length;
        *//*if(len==1){
         alert(len+"//"+id);
         }*//*
        var autoid=$("#carstyleID").val();

        $(this).parent("li").remove();
        var htm=$(".view-list").first("li").find("a").html();
        var yasuid=$(".view-list").first("li").find("i").attr("id");
        if(id==autoid){
            if(len==1){
                var htmStrImg = "<img src='"+basePath+"/images/btn-carType-bg.gif' height='27'/>";
                $(".carChange").css({"background":"none","width":"106px"}).html(htmStrImg);
                $(".inputSearch").width("480");
                $("#yasuoid").val("");
                $("#carstyleID").val("");
            }else{
                $("#selCarType").html(htm);
                $(".carChange").html(htm);
                $("#yasuoid").val(yasuid);
                $("#carstyleID").val(yasuid);
            }
        }*/
    });

    //关闭弹层
    $(".dia-close").click(function(){
        $(".choose-dialog").hide();
        $(".box").hide();
    });

    //点击历史记录已选择车型
    $(".view-list").on("click","a",function(){
        $("#selCarType").html($(this).html()+label).attr("title",$(this).html());
        $(".carChange").css("background","#FFAF02").css("padding-left","13px").html($(this).html()+label).attr("title",$(this).html());
        $("#carstyleID").val($(this).parent().find("i").attr("id"));
        $(".choose-dialog").hide();
        $(".box").hide();
        changeCarTypeBtnClass("2");
        $(".carChange").width("247");
        $(".inputSearch").width("328");
        $.getJSON(basePath+"/addSeeeionVal.htm",{"carstyleID":$(this).parent().find("i").attr("id"),"val":$(this).html()},function(){

        });
        return false;
    });


    function gethtml(){
        var autoStyleSweptVolume="";
        var goodsBrandName="";
        var autoStyleSystem="";
        var autoStyleType="";
        var autoStyleEngine="";//发动机
        var autoStyleGearbox="";//排量

        $(".chosen-list").find("span").each(function(index,element){
            if(index==0){
                //autoStyleYear=$(element).html();
                goodsBrandName=$(element).html();
            }
            if(index==1){
                //goodsBrandName=$(element).html();
                autoStyleSystem=$(element).html();
            }
            if(index==2){
                //autoStyleSystem=$(element).html();
                autoStyleType=$(element).html();
            }
            if(index==3){
                //autoStyleType=$(element).html();
                autoStyleSweptVolume=$(element).html();
                //if(autoStyleSweptVolume.indexOf("0")!=-1&&autoStyleSweptVolume>1){
                //    autoStyleSweptVolume=autoStyleSweptVolume.split('.')[0];
                //}
                autoStyleEngine=autoStyleSweptVolume.split("/")[0];
                autoStyleGearbox=autoStyleSweptVolume.split("/")[1];

            }
        });
        if($(".chosen-list").find("span").length==0){
            return;
        }
        //alert(autoStyleYear+"/"+goodsBrandName+"/"+autoStyleSystem+"/"+autoStyleType);
        //根据年款查品牌
        //根据车型车系查数据
        $.getJSON(basePath+"/GetCarStyleByType.htm",{"autoStyleEngine":autoStyleEngine,"autoStyleGearbox":autoStyleGearbox,"goodsBrandName":goodsBrandName,"autoStyleSystem":autoStyleSystem,"autoStyleType":autoStyleType},function(data){
            var tt = "";
            var sttr=[];
            var sttr2=[];
            var sttr3=[];
            var sttr4=[];
            var ii=0;
            var ii2=0;
            var ii3=0;
            var ii4=0;
            $(".models-cont").show();
            $(".models-cont").html("");
            var sttr5=data.brandMake;
            var sttr6=[];
            var make="";
            /*$.each(data, function(k, v) {
             if(autoStyleSystem==""&&autoStyleType==""&&autoStyleSweptVolume==""){
             if(k==0){
             make= v.autoStyleBrandMake;
             sttr5.push(make);
             }
             if(v.autoStyleBrandMake!=make&&sttr5.indexOf(v.autoStyleBrandMake)==-1){
             sttr5.push(v.autoStyleBrandMake);
             make=v.autoStyleBrandMake;
             }
             }
             });*/
            if(autoStyleSystem==""&&autoStyleType==""&&autoStyleSweptVolume=="") {

                for(y in sttr5){
                    tt+="<p class='carType'>"+sttr5[y]+"</p>";
                    $.each(data.newlist, function(k, v) {
                        if(sttr5[y]==v.autoStyleBrandMake){
                            if(ii==0){
                                sttr.push(v.autoStyleSystem+v.autoStyleBrandMake);
                                tt+='<a href="javascript:;" title="'+v.autoStyleSystem+'"><span class="choose-word">'+v.autoStyleSystem+'</span></a>';
                            }else{
                                var tm=0;
                                for (x in sttr)
                                {
                                    if(sttr[x]==v.autoStyleSystem+v.autoStyleBrandMake){
                                        tm=1;
                                    }
                                }
                                if(tm==0){
                                    sttr.push(v.autoStyleSystem+v.autoStyleBrandMake);
                                    tt+='<a href="javascript:;" title="'+v.autoStyleSystem+'"><span class="choose-word">'+v.autoStyleSystem+'</span></a>';
                                }
                            }
                        }
                        ii++;
                    });
                }
            }

            if(autoStyleSystem!=""&&autoStyleType==""&&autoStyleSweptVolume==""){
                $.each(data.newlist, function(k, v) {
                    if (ii2 == 0) {
                        sttr2.push(v.autoStyleType);
                        tt += '<a href="javascript:;"><span class="choose-word" SalesName="' + v.autoStyleSalesName + '">' + v.autoStyleType + '</span></a>';
                    } else {
                        var tm1 = 0;
                        for (x in sttr2) {
                            if (sttr2[x] == v.autoStyleType) {
                                tm1 = 1;
                            }
                        }
                        if (tm1 == 0) {
                            sttr2.push(v.autoStyleType);
                            tt += '<a href="javascript:;"><span class="choose-word" SalesName="' + v.autoStyleSalesName + '">' + v.autoStyleType + '</span></a>';
                        }
                    }
                    ii2++;
                });
            }
            if(autoStyleSystem!=""&&autoStyleType!=""&&autoStyleSweptVolume==""){
                $.each(data.newlist, function(k, v) {
                    //auto_style_engine,auto_style_gearbox
                    var str = v.autoStyleEngine == "" ? "" : v.autoStyleEngine;
                    var str2 = v.autoStyleGearbox == "" ? "" : v.autoStyleGearbox;
                    var sweptVolume = v.autoStyleSweptVolume;
                    if (typeof(str) == "undefined") {
                        str = "暂无";
                    }
                    if (typeof(str2) == "undefined") {
                        str2 = "暂无";
                    }
                    if (ii3 == 0) {
                        sttr3.push(str + str2);
                        tt += '<a href="javascript:;"><span class="choose-word">' + str + '/' + str2 + '</span></a>';
                    } else {
                        var tm3 = 0;
                        for (x in sttr3) {
                            if (sttr3[x] == str + str2) {
                                tm3 = 1;
                            }
                        }
                        if (tm3 == 0) {
                            sttr3.push(str + str2);
                            tt += '<a href="javascript:;"><span class="choose-word">' + str + '/' + str2 + '</span></a>';
                        }
                    }
                    ii3++;
                });
            }
            if(autoStyleSystem!=""&&autoStyleType!=""&&autoStyleSweptVolume!=""){
                $.each(data.newlist, function(k, v) {
                    if (ii4 == 0) {
                        sttr4.push(v.autoStyleProductiveYear);
                        tt += '<a href="javascript:;" autoStyleSalesName="'+ v.autoStyleSalesName+'" yasuoid="' + v.autoStyleIdLiyangyasuoId + '"><span class="choose-word">' + v.autoStyleProductiveYear + '</span></a>';
                    } else {
                        var tm1 = 0;
                        for (x in sttr4) {
                            if (sttr4[x] == v.autoStyleProductiveYear) {
                                tm1 = 1;
                            }
                        }
                        if (tm1 == 0) {
                            sttr4.push(v.autoStyleProductiveYear);
                            tt += '<a href="javascript:;" autoStyleSalesName="'+ v.autoStyleSalesName+'"  yasuoid="' + v.autoStyleIdLiyangyasuoId + '"><span class="choose-word">' + v.autoStyleProductiveYear + '</span></a>';
                        }
                    }
                    ii4++;
                });
            }
            $(".models-cont").html(tt);
        })
    }

    //搜索vin码查车型
    $(".search-btn,.new_search_btn").click(function(){
        var searchVal=$(".search-input").val();
        if(searchVal==""){
            return;
        }
        if(searchVal.length!=17){
            $("#errmsg").html("VIN码格式错误(长度17位)");
            return;
        }
        if(searchVal.indexOf('O')!=-1||searchVal.indexOf('I')!=-1||searchVal.indexOf('Q')!=-1){
            $("#errmsg").html("VIN码不包含'O','I','Q'");
            return;
        }
        $.getJSON(basePath+"/GetCarAutoStyleByVin.htm",{"vin":searchVal},function(data){
            if(data.stat=="ok"){
                $(".search-input").val("");
                if(!data.qpAutoStyleBean && typeof data.qpAutoStyleBean != "undefined" && data.qpAutoStyleBean != 0){
                    $("#errmsg").html("未查询到结果");
                    return;
                }
                $(".vinBox").hide();
                $(".choose-success").show();
                $(".choose-tabs, .dia-content").hide();
                var str=data.qpAutoStyleBean.goodsBrandName+" "+data.qpAutoStyleBean.autoStyleSystem+" "+data.qpAutoStyleBean.autoStyleType+" "+data.qpAutoStyleBean.autoStyleEngine+"/"+data.qpAutoStyleBean.autoStyleGearbox+" "+data.qpAutoStyleBean.autoStyleProductiveYear+"年产";

                var SalesName=data.qpAutoStyleBean.autoStyleType.match(/\[([^\"]*)]/);
                var tmp='';
                if(SalesName==null){
                    tmp=data.qpAutoStyleBean.autoStyleType;
                }
                else{
                    tmp=SalesName[1];
                }

                var str2=data.qpAutoStyleBean.goodsBrandName+" "+tmp+" "+data.qpAutoStyleBean.autoStyleSalesName+" "+data.qpAutoStyleBean.autoStyleProductiveYear+"年产";
                $("#valText").html(str);

                $("#selCarType").html(str2+label);
                $(".carChange").css("background","#FFAF02").css("padding-left","13px").css("width","250px").html(str2+label);
                $(".choose-success").show();

                //$("#selCarType").html(str);
                //$(".choose-success").show();

                $("#yasuoid").val(data.qpAutoStyleBean.autoStyleIdLiyangyasuoId);
                $("#carstyleID").val(data.qpAutoStyleBean.autoStyleIdLiyangyasuoId);


                $.getJSON(basePath+"/AddRecord.htm",{"autoStyleIdLiyangyasuoId":data.qpAutoStyleBean.autoStyleIdLiyangyasuoId,"autoStyleYear":data.qpAutoStyleBean.autoStyleProductiveYear,"goodsBrandName":data.qpAutoStyleBean.goodsBrandName,"autoStyleSystem":data.qpAutoStyleBean.autoStyleSystem,"autoStyleType":tmp},function(data){
                    if(data=="err"){
                        alert("选择车型异常....请重新选择");
                    }
                });
                setTimeout(function(){
                    $(".choose-dialog").hide();
                    $(".box").hide();
                    changeCarTypeBtnClass("2");
                },2000);//1000为1秒钟
            }
            else{
                $("#errmsg").html(data.msg);
            }
        });
    });



    $(".listSec li").mouseover(function(){
        var n = $(this).index();
        var id = $(this).parent().parent().attr("id");
        $(".listGoods p").hide();
        $($("#"+id+" .listGoods p")[n]).show();

        var h = $("#"+id+" .listGoods").height();
        if(h>439) {

            $("#" + id + " .listGoods").parent().css("height", h + 20 + "px");
        }else{
            $("#" + id + " .listGoods").parent().css("height", "449px");
        }

        $(".listSec li").removeClass("listSec_navSty");
        $(this).addClass("listSec_navSty");
        $(".listSec li h3 a").css("color","#666");
        $(".listSec li h3 a b").css("background-image","url(/index_seven/css/icons/icon_listSec.png)");
        var $a = $(this).children("h3").children("a");
        $a.css("color","#000");
        $a.children("b").css("background-image","url(/index_seven/css/icons/icon_listSec_hover.png)");
    });

    $(".dropdown-menu li").mouseover(function(){
        var n = $(this).index()+1;
        var _this = this;

        if(n == 11){
            $("#submenu-11").height("514");
        }

        if(n==9){
            $("#submenu-9").height("449");
        }
        $(".listSec li").removeClass("listSec_navSty");
        var $a = $(".listSec li:nth-child(1)").addClass("listSec_navSty").children("h3").children("a");
        $(".listSec li h3 a").css("color","#666");
        $(".listSec li h3 a b").css("background-image","url(/index_seven/css/icons/icon_listSec.png)");
        $a.css("color","#000");
        $a.children("b").css("background-image","url(/index_seven/css/icons/icon_listSec_hover.png)");
        $(".listGoods p").hide();
        $($("#submenu-"+n+" .listGoods p")[0]).show();

        $(".dropdown-menu li a").css("color","#fff");
        $(this).children("a").css("color","#272727");

        $("#submenu-"+n).mouseover(function(){

            $(_this).children("a").css("color","#272727")
            ;
        }).mouseout(function(){
            $(_this).children("a").css("color","#fff");
        });
    });

    $(".dropdown-menu li").mouseout(function(){
        $(this).children("a").css("color","#fff");
    });
});


$(window).scroll(function(){
    var h = $(window).scrollTop();
    if(h >= 990){
        $("#btnFloor").css("top",h+20+"px");
    }else{
        $("#btnFloor").css("top","990px");
    }
});
