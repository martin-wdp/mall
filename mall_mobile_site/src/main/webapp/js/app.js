$(function(){



    $(".way-item").each(function(){
        var _this = $(this),
            _index = _this.find(".mui-btn.checked").index();
        _this.find(".way-intro:eq("+_index+")").show();
    });
    $(".way-btns .mui-btn").click(function(){
        var _index = $(this).index();
        $(this).addClass("checked").siblings(".mui-btn").removeClass("checked");
        $(this).parents(".way-item").find(".way-intro:eq("+_index+")").show().siblings(".way-intro").hide();
    });
    $(".since-item").click(function(){
        $(this).addClass("selected").siblings(".since-item").removeClass("selected");
    });

});
(function($){

    Zepto.fn.isChildAndSelfOf = function(b){return (this.closest(b).length > 0);};

    $(".st-operation").click(function(){
        var _obj = $(this).next(".ops-box");
        if(_obj.css("display") == "block") {
            $(this).next(".ops-box").hide();
        } else {
            $(".ops-box").hide();
            $(this).next(".ops-box").show();
        };
    });
    $(document).click(function(event){
        if(!$(event.target).isChildAndSelfOf(".st-operation, .ops-box")) {
            $(".ops-box").hide();
        };
    });

})(Zepto);
