$(function(){

    jQuery.fn.isChildAndSelfOf = function(b){return (this.closest(b).length > 0);};

    $('.header-user .dropdown').hover(function() {
        $(this).find('.dropdown-menu').stop(true, true).delay(100).fadeIn(100);
    }, function() {
        $(this).find('.dropdown-menu').stop(true, true).delay(100).fadeOut(100);
    });

    $(".service-close").click(function(){
        $(".service-wrap").remove();
    });

    $(".back-to-top").click(function(){
        $("html, body").stop().animate({
            scrollTop: 0
        });
    });

    $(".notice-center-ring").click(function(){
        $(".notice-center-wrapper").stop().animate({
            right: 0
        });
    });
    $(".nt-close").click(function(){
        $(".notice-center-wrapper").stop().animate({
            right: -300
        });
    });
    $(document).click(function(event){
        if(!$(event.target).isChildAndSelfOf(".notice-center-wrapper, .notice-center-ring")) {
            $(".notice-center-wrapper").stop().animate({
                right: -300
            });
        }
    });

    $(".page-help-btn").click(function(){
        $(".page-help-container").stop().animate({
            right: 0
        });
    });
    $(document).click(function(event){
        if(!$(event.target).isChildAndSelfOf(".page-help-container, .page-help-btn")) {
            $(".page-help-container").stop().animate({
                right: -320
            });
        }
    });

    $(".modify-btn").click(function(){
        $(this).parents(".cont-item").find(".info-cont:visible").hide();
        $(this).parents(".cont-item").find(".info-edit").show();
    });
    $(".cancel-btn").click(function(){
        $(this).parents(".info-edit").hide().prev(".info-cont").show();
    });

    /*$(".chooseGoods").click(function(){
        $(".choose-goods").show();
    });

    $(".receive-setting").click(function(){
        $(this).parent().find(".form-control").removeClass("hide");
    });*/

    $(".role-list a").click(function(event){
        if(!$(event.target).isChildAndSelfOf(".dropdown")) {
            var $index = $(this).parent("li").index();
            $(this).parent("li").addClass("active").siblings(".active").removeClass("active");
            $(".role-item:eq("+$index+")").removeClass("hide").siblings(".role-item:visible").addClass("hide");
        };
    });
    /*$("#addRole").click(function(){
        var _cont = '<li><input type="text" placeholder="新职位"/><i class="glyphicon glyphicon-cog"></i></li>';
        $(".role-list").prepend(_cont);
    });*/

    $(".edit-it").click(function(){
        $(this).prev("textarea").focus();
    });

    $(".main-city input[type='checkbox']").change(function(){
        if($(this).prop("checked") == true) {
            $(this).parent().next(".cities").find("a").addClass("checked");
        } else {
            $(this).parent().next(".cities").find("a").removeClass("checked");
        };
    });
    $(".cities").each(function(){
        var _this = $(this);
        _this.find("a").click(function(){
            $(this).toggleClass("checked");
            if($(this).hasClass("checked")) {
                $(this).find("input").prop("checked",true);
            } else {
                $(this).find("input").prop("checked",false);
            };
            if(_this.find(".checked").length == _this.find("a").length) {
                _this.prev("label").find("input").prop("checked",true);
            } else {
                _this.prev("label").find("input").prop("checked",false);
            }
        });
    });

    //规格
    $(".spec_set input").change(function(){
        if($(this).prop("checked") == true) {
            $(this).parents(".spec_set").next(".spec_set_details").show();
        } else {
            $(this).parents(".spec_set").next(".spec_set_details").hide();
        };
    });
    $(".check_spec").change(function(){
        if($(this).prop("checked") == true) {
            $(this).parents("td").find(".spec_value_img").show();
        } else {
            $(this).parents("td").find(".spec_value_img").hide();
        };
    });

    $(".switch-wp").each(function(){
        var _this = $(this);
        _this.find("input[type='radio']").change(function(){
            var _val = _this.find("input[type='radio']:checked").attr("data-switch");
            $("#"+_val).show().siblings(".switch-item").hide();
        });
    });

    $(".choose-sorts-cont").on("click",".item-switch",function(){
        var _this = $(this);
        if(_this.hasClass("glyphicon-plus-sign")) {
            _this.removeClass("glyphicon-plus-sign").addClass("glyphicon-minus-sign");
            _this.parent().parent("li").children("ul").show();
        } else {
            _this.removeClass("glyphicon-minus-sign").addClass("glyphicon-plus-sign");
            _this.parent().parent("li").children("ul").hide();
        };
    });

    $(".main-list").on("click",".optional-item",function(event){
        var _this = $(this),
            _li = _this.parent("li");
        if(!$(event.target).isChildAndSelfOf(".item-switch")) {
            if(!_li.hasClass("selected")) {
                _li.addClass("selected");
                _this.next("ul").find("li").addClass("selected");
                selected(1);
            } else {
                _li.removeClass("selected");
                _this.next("ul").find("li").removeClass("selected");
                _this.parents(".selected").removeClass("selected");
            };
        }
    });

    $(".add-choose").click(function(){
        var $this = $(this);
        var _html = $this.parent(".add-sorts-wp").find(".optional-box .choose-sorts-cont").clone();
        _html.find("ul").each(function(){
            if($(this).find(".selected").length == 0) {
                $(this).remove();
            } else {
                $(this).children("li").each(function(){
                    if(!$(this).hasClass("selected") && $(this).find(".selected").length == 0) {
                        $(this).remove();
                    };
                });
            };
        });
        _html.find(".selected").removeClass("selected");
        _html.find("li").each(function(){
            $(this).append('<i class="glyphicon glyphicon-remove del-item"></i>');
            $(this).find(".glyphicon-plus-sign").removeClass("glyphicon-plus-sign").addClass("glyphicon-minus-sign");
        });
        $this.parent(".add-sorts-wp").find(".selected-box .choose-sorts-cont").html(_html.find(".main-list"));
    });

    $(".choose-sorts-cont").on("click",".del-item",function(){
        var _this = $(this);
        _this.parent("li").remove();
        delItem(1);
    });

    function selected(i){
        if(i < 3) {
            $(".choose-sorts-box ul").each(function(){
                if($(this).children("li").length == $(this).children(".selected").length) {
                    $(this).parent("li").addClass("selected");
                    selected(i+1);
                };
            });
        };
    };

    function delItem(i) {
        if(i < 3) {
            $(".selected-box ul").each(function(){
                if($(this).children("li").length == 0) {
                    $(this).parent("li").remove();
                    delItem(i+1);
                }
            });
        };
    };

    //促销添加商品
    $(".query-table").on("click",".add-query",function(){
        var _tr = $(this).parents("tr").clone(),
            $index = $(this).parents("tr").index();
        _tr.attr("data-index",$index);
        _tr.find(".add-query").removeClass("add-query").addClass("del-query").text("删除");
        $(".result-table tbody").append(_tr);
        $(this).attr({"class":"btn btn-default btn-sm","disabled":"true"}).text("已添加");
    });
    $(".result-table").on("click",".del-query",function(){
        var _tr = $(this).parents("tr"),
            $index = _tr.attr("data-index");
        $(".query-table tbody tr:eq("+$index+")").find(".btn").attr("class","btn btn-primary btn-sm add-query").removeAttr("disabled").text("添加");
        _tr.remove();
    });

    //添加多级促销
    var mold01 = '<li>满<input class="form-control input-xs text-center required isNumber" id="fullPrice" name="fullPrice" type="text"/>元，打<input name="fullbuyDiscount"  class="form-control input-xs text-center required zeroOne" type="text"/>折' +
                 '<button class="btn btn-default btn-sm del-mold" type="button">删除本级促销</button></li>',
        mold02 = '<li>满<input class="form-control input-xs text-center required isNumber" name="fullPrice" type="text"/>元，减<input name="reducePrice" class="form-control input-xs text-center required isNumber" type="text"/>元' +
        '<button class="btn btn-default btn-sm del-mold" type="button">删除本级促销</button></li>';
        mold03 = '<li>满<input class="form-control input-xs text-center required digits" name="packagesNo" type="text"/>件，打<input name="packagebuyDiscount" class="form-control input-xs text-center required zeroOne" type="text"/>折' +
        		'<button class="btn btn-default btn-sm del-mold" type="button">删除本级促销</button></li>',
       mold04 = '<li>满<input class="form-control input-xs text-center required digits" name="countNo" type="text"/>件，共<input name="countMoney" class="form-control input-xs text-center required isNumber" type="text"/>元' +
       		  '<button class="btn btn-default btn-sm del-mold" type="button">删除本级促销</button></li>';
   var num=0;
    $(".add-mold01").click(function(){
    	if($(this).prev("ul").find("li").size() < 3){
            num+=1;
    		$(this).prev("ul").append('<li>满<span><input class="form-control input-xs text-center required isNumber" id="fullPrice'+num+'" name="fullPrice" type="text"/></span>元，打<span><input name="fullbuyDiscount" id="fullbuyDiscount'+num+'"class="form-control input-xs text-center required zeroOne" type="text"/></span>折' +
            '<button class="btn btn-default btn-sm del-mold" type="button">删除本级促销</button></li>');
    	}
    });
    $(".add-mold02").click(function(){
    	if($(this).prev("ul").find("li").size() < 3){
            num+=1;
           $(this).prev("ul").append('<li>满<span><input class="form-control input-xs text-center required isNumber"  id="fullPrice'+num+'" name="fullPrice" type="text"/></span>元，减<span><input name="reducePrice" id="reducePrice'+num+'" class="form-control input-xs text-center required isNumber" type="text"/></span>元' +
           '<button class="btn btn-default btn-sm del-mold" type="button">删除本级促销</button></li>');
    	}
    });
    $(".add-mold03").click(function(){
    	if($(this).prev("ul").find("li").size() < 3){
    		$(this).prev("ul").append(mold03);
    	}
    });
    $(".add-mold04").click(function(){
    	if($(this).prev("ul").find("li").size() < 3){
    		$(this).prev("ul").append(mold04);
    	}
    });
    $(".mold-list").on("click",".del-mold",function(){
        $(this).parent("li").remove();
    });

});