<#assign basePath=request.contextPath>
<!--start 适配车型-->
<link rel="stylesheet" href="${basePath}/css/carmodel/style.css">
<script src="${basePath}/js/carmodel/jquery.easydrag.handler.beta2.js"></script>
<script src="${basePath}/js/carmodel/autostyle.js"></script>
<!--end 适配车型-->
<div class="box">
    <div class="box2">
        <div class="choose-dialog">
            <input type="hidden" id="yasuoid" />
            <!-- VIN码查询 -->
            <div class="dia-header">
                <label>
                    通过VIN码确定车型
                </label>
                <div class="choose-search">
                    <input class="search-input" value="" type="text">
                    <button class="search-btn" type="button"></button>
                </div>
                <span id="errmsg" style="color: red"></span>
                <a href="javascript:;" class="dia-close" target="_self"></a>
            </div>

            <!-- 选项卡 -->
            <ul class="choose-tabs switch-tabs">
                <li class="ctabs01 on">
                    <a href="javascript:;" target="_self"><i></i>选择车型</a>
                </li>
                <li class="ctabs02">
                    <a href="javascript:;" target="_self"><i></i>浏览过的车型</a>
                </li>
            </ul>

            <!-- 选择车型、浏览车型 -->
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
                        <li class="last-ps">
                            <a href="javascript:;" target="_self"><i>5</i>选择年款</a>
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
                        <div class="tabs-item on" id="yeartp">
                            <ul class="brands-filter">
                            </ul>
                            <div class="brands-cont choose-wp">
                                <!--品牌tab-->
                            </div>
                        </div>

                        <!-- 车系内容 -->
                        <div class="tabs-item">
                            <div class="models-scroll choose-wp">
                                <div class="models-item">
                                    <div class="models-cont">

                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 车型内容 -->
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

                        <!-- 年款内容 -->
                        <div class="tabs-item" >
                            <div class="models-scroll choose-wp">
                                <div class="models-item">
                                    <div class="models-cont">
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <!-- 浏览车型 -->
                <div class="tabs-item view-item">
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
<script type="text/javascript">
    //选择车型
    $(function(){
        $('div.carTypeSearch button.btnSearch').click(function(){
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
                    tt+=' <a href="javascript:;" target="_self"><span class="choose-word">'+ v.auto_style_year.split(".")[0]+'</span></a>';
                })
                $(".model-year").html(tt);
            });*/
            $("div.tabs-item").find(".on").removeClass("on");
            //$("#yeartp").show().addClass("on");
            //$(".model-year").show();
            $(".tabs-wrap").find(".tabs-item:first").show();
        });

        //关闭车型弹出框
        $('.dia-close').click(function(){
            $("div[class^='box']").hide();
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
    })

    //改变选择车型按钮样式 1添加车型 2刷新车型
    function changeCarTypeBtnClass(classType){
        if(classType == "1"){
            $(".btnSearch").removeClass("refresh-car").addClass("add-car");
        }else if(classType == "2"){
            $(".btnSearch").removeClass("add-car").addClass("refresh-car");
        }
    }
</script>



