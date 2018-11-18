$(function(){

    //购物车
    $(".cartfd-new").mouseover(function(){
        $(this).find(".miniCart").show();
    }).mouseout(function(){
        $(this).find(".miniCart").hide();
    });
    $(".mcOrder").each(function(){
        var $this = $(this);
        $this.mouseover(function(){
            $this.find(".minus, .plus").css("visibility","visible");
            $this.find(".del").show();
        }).mouseout(function(){
            $this.find(".minus, .plus").css("visibility","hidden");
            $this.find(".del").hide();
        });
    });




});