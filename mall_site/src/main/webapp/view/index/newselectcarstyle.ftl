<#assign basePath=request.contextPath>
<!--start 适配车型-->
<link rel="stylesheet" href="${basePath}/css/carmodel/style.css">
<script src="${basePath}/js/carmodel/jquery.easydrag.handler.beta2.js"></script>
<script src="${basePath}/js_max/carmodel/autostyle.js"></script>
<!--end 适配车型-->
<div class="box">
    <div class="box3">&nbsp;</div>

    <div class="box2">
        <div class="choose-dialog">
            <div class="navTop">
                <ul style="margin-left:164px;">
                    <li id="vin" class="carHover" data="vinBox" data="vinBox">VIN码选车型</li>
                    <li data="handBox" data="handBox">手动选车型</li>
                </ul>
                <a href="javascript:;" class="dia-close btn_close"></a>
            </div>
            <input type="hidden" id="selectVal" value="${selectVal?default("请先选车型")}">
            <div class="nav_cont">
                <div class="vinBox">
                    <span id="errmsg" style="color: red;position:absolute;left:34px;top:-30px;"></span>
                    <div class="choose-search new_choose_search">
                        <input class="search-input" placeholder="请在此处输入VIN码" type="text">
                        <button class="new_search_btn" type="button"></button>
                    </div>
                  <!--  <span class="answer" alt="如何查看VIN码"><a href="#" >如何查看VIN码</a></span> -->

                    <!-- 浏览车型 -->
                    <div class="history_records">
                        <p>历史记录</p>
                        <ul class="view-list">

                        </ul>
                    </div>
                </div>

                <div class="handBox" style="display:none;">
                    <div class="dia-content tabs-wrap">

                        <!-- 选择车型 -->
                        <div class="tabs-item on">
                            <!-- 顺序选项卡 -->
                            <ul class="choose-process switch-tabs">

                                <li class="first-ps chosen on">
                                    <a href="javascript:;" target="_self"><i>1</i>选择品牌</a>
                                </li>
                                <li class="">
                                    <a href="javascript:;" target="_self"><i>2</i>选择车系</a>
                                </li>
                                <li class="">
                                    <a href="javascript:;" target="_self"><i>3</i>选择车型</a>
                                </li>
                                <li class="">
                                    <a href="javascript:;" target="_self"><i>4</i>发动机/变速箱</a>
                                </li>
                                <li id="last_li" class="last-ps" style="background-position:0 -200px;">
                                    <a href="javascript:;" target="_self"><i>5</i>生产年份</a>
                                </li>
                            </ul>
                            <div class="items-content tabs-wrap">
                                <!-- 已选条件 -->
                                <div class="chosen-items">
                                    <label>
                                        已选：
                                    </label>
                                    <ul class="chosen-list">

                                    </ul>
                                </div>

                                <!-- 品牌内容 -->
                                <div class="tabs-item on" id="yeartp" style="min-height:250px;">
                                    <ul class="brands-filter">
                                    </ul>
                                    <div class="brands-cont choose-wp">
                                        <!--品牌tab-->
                                    </div>
                                </div>

                                <!-- 车型内容 -->
                                <div class="tabs-item">
                                    <div class="models-scroll choose-wp" style="height:300px;">
                                        <div class="models-item">
                                            <div class="models-cont">

                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- 车款内容 -->
                                <div class="tabs-item">
                                    <div class="models-scroll choose-wp">
                                        <div class="models-item">
                                            <div class="models-cont">

                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- 发动机/变速箱 -->
                                <div class="tabs-item">
                                    <div class="models-scroll choose-wp">
                                        <div class="models-item">
                                            <div class="models-cont">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- 生产年份 -->
                                <div class="tabs-item" >
                                    <div class="models-scroll choose-wp">
                                        <div class="models-item">
                                            <div class="models-cont">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="prompt">
                                        <div class="proCont">
                                            <h3 style="color:#F36C01;">温馨提示：</h3>
                                            <p>“生产年份”与“年款”“上路年份”未必相同，选错生产年份往往会导致配件出错无法安装。</p>
                                            <p><a href="${basePath}/help/116" style="color:#E33928;">求助如何知道生产年份？</a></p>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <!-- 浏览车型 -->
                    <div class="history_records history_records2">
                        <p>历史记录</p>
                        <ul class="view-list">

                        </ul>
                    </div>
                </div>
                <div class="choose-success">
                    <h3>
                        <i></i>车型选择成功
                    </h3>
                    <p>
                        已选车型：
                        <span id="valText"></span>
                    </p>
                </div>
            </div>


        </div>
    </div>
</div>
<script type="text/javascript">
    //选择车型
    $(function(){
        $('.carChange').click(function(){
            $("div[class^='box']").show();
            $("div.choose-dialog").show();
            $(".choose-tabs, .dia-content").show();
            $(".choose-success").hide();
            $(".chosen-items").hide();
            $(".chosen-list").html("");
            $(".ctabs02").removeClass("on");
            $(".view-item").removeClass("on");
            $(".switch-tabs").find("li").removeClass("first-ps").removeClass("last-ps").removeClass("chosen");
            $(".switch-tabs").find("li:first").addClass("first-ps").addClass("chosen").addClass("on");

            $.getJSON("${basePath}/GetAutoStyleByBrand.htm",{"carYearStr":"2013"},function(data){
                var tt = '<li id="first"><span class="back_span">热门</span></li>';
                var tab='';
                $(".brands-filter").html("");
                $(".brands-cont").html("");
                $.each(data, function(k, v) {
                    if(k==0){
                        tt+='<li class="on"><a href="javascript:;" target="_self">'+ v.key+'</a></li>';
                        tab+='<div class="brand-filter-content" style="display: block">';
                    }else{
                        tt+='<li><a href="javascript:;" target="_self">'+ v.key+'</a></li>';
                        tab+='<div class="brand-filter-content" style="display: none">';
                    }
                    var temp="";
                    for(var i=0;i< v.value.length;i++){
                        if(typeof(v.value[i].brand_logo)=="undefined"|| v.value[i].brand_logo==null){
                            temp="../images/brand/imageload.jpg"
                        }else{
                            temp=v.value[i].brand_logo;
                        }
                        tab+='<a class="brand-item" href="javascript:;" target="_self">'
                        +'<img src="'+ temp +'" alt=""/>'
                        +'<span class="choose-word">'+v.value[i].goods_brand_name+'</span>'
                        +'</a>';
                    }
                    tab+='</div>';
                })
                $(".brands-filter").html($(tt));
                $(".brands-cont").html($(tab));
            })


            /*$.getJSON("${basePath}/carstylelistByYear.htm",{},function(data){
                var tt = "";
                $.each(data, function(k, v) {
                    tt+=' <a href="javascript:;"><span class="choose-word">'+ v.auto_style_year.split(".")[0]+'</span></a>';
                })
                $(".model-year").html(tt);
            });*/
            $("div.tabs-item").find(".on").removeClass("on");
            //$("#yeartp").show().addClass("on");
            //$(".model-year").show();
            $(".tabs-wrap").find(".tabs-item:first").show();

            $("#last_li").css("background-position","0 -200px");
        });


        //关闭车型弹出框
        $('.dia-close').click(function(){
            $("div[class^='box']").hide();
            return false;
        });

        //清除当前车型
        $("i.clear-car").click(function(){

            //清除cookies数据
            //清除数据库数据
            var id = $("#carstyleID").val();
            $.get("${basePath}/DelRecord.htm",{"autoId":id},function(dat){
                debugger;
                // console.log();
                if(dat=="err"){
                    alert("删除异常...");
                }else{
                    $("#yasuoid").val("");
                    $("#carstyleID").val("");
                    $("span[id='selCarType']").text('请先选择车型，方便选择商品！');
                    changeCarTypeBtnClass("1");
                }
            },"text")
        });

        //VIN码选车型和手动选车型切换
        $(".navTop li").click(function(){
           $(".navTop li").removeClass("carHover");
           $(this).addClass("carHover");
            var obj = $(this).attr("data");
            $(".vinBox,.handBox").hide();
            $("."+obj).show();
        });

            //历史记录删除
        /*$(".history_records li i").click(function(){
           $(this).parent().remove();
        });*/
    });

    //改变选择车型按钮样式 1添加车型 2刷新车型
    function changeCarTypeBtnClass(classType) {
        if (classType == "1") {
            $(".btnSearch").removeClass("refresh-car").addClass("add-car");
        } else if (classType == "2") {
            $(".btnSearch").removeClass("add-car").addClass("refresh-car");
        }
    }

    function closeCar(ev){
        var event = ev||window.event;
        var htmStrImg = "<img src='${basePath}/images/btn-carType-bg.gif' height='27'/>";
        $(".carChange").css({"background":"none","width":"100px","padding-left":"0px"}).html(htmStrImg);
        $(".inputSearch").width("480");
      //  event.stopPropagation();
        var id = $("#carstyleID").val();
        $.get("${basePath}/DelRecord.htm",{"autoId":id},function(dat){
            debugger;
            // console.log();
            if(dat=="err"){
                alert("删除异常...");
            }else{
                $("#yasuoid").val("");
                $("#carstyleID").val("");
                $("span[id='selCarType']").text('请先选择车型，方便选择商品！');
                changeCarTypeBtnClass("1");
            }
        },"text");
        event.cancelBubble = true;
    }
</script>



