$();

if(Browser.Navigate()){
    $(".acquiesce input").css("top","0");
}

$("#user,#addr").blur(function(){
    if($(this).val() != ""){
        Write.Must.Ok(this);
    }
});
$("#postcode").blur(function(){
    var postcode = $(this).val();
    if(!checkPostCode(postcode)){
        Write.Must.No(this).text("邮编格式不正确！");
    }
    if(postcode != ""&&checkPostCode(postcode)){
        Write.Must.Ok(this);
    }
});

function checkPostCode(postcode){
    var reg = /^[1-9][0-9]{5}$/;
    if(reg.test(postcode)){
        return true;
    }else{
        return false;
    }
}

//提交
$("#sub").click(function(){
    Sub(function(){
        $('form[id="addNewAddress"]').submit();
    });
});

$("#area").bind("click",function(){
    $(".addrBox").show();
    $(".city ul").attr("data-count","0");
    setTimeout(function(){
        $(".district").css("bottom","0");
    },30);
    $("#line").removeAttr("style").attr("data-left","40%");
});

/*$(".addrBox").bind("click",function(){
    $(".district").css("bottom","-50%");
    $(".city ul").attr("data-count","0");
    var _this = this;
    setTimeout(function(){
        $(_this).hide();
    },600);
});*/
var beans = ["infoProvince","infoCity","infoCounty"];
//初始化省
$("#area").bind("click", function () {
    //初始化省
    initAddress("", "",beans);
});
$("#addressInit").delegate("li", "click", function () {
        var id = $(this).attr("data-id");
        var addressType = $(this).attr("data-address");
        initAddress(id, addressType,beans);
        if(addressType != "county"){
            event.stopPropagation();
        }
    }
);
$("#addressInit").delegate("li","click",function(){
    var n = parseInt($("#addressInit").attr("data-count"))+1;
    $("#addressInit").attr("data-count",n);
    var lt = $("#line").attr("data-left");
    $("#line").attr("data-left","74%").css("left",lt);//7% 40% 74%
    var name = $(this).text();
    if(n == 3){
        setTimeout(function(){
            $(".district").css("bottom","-50%");
        },30);
        setTimeout(function(){
            $(".addrBox").hide();
        },600);
    }
    switch(n){
        case 1: $("#Province").text($(this).text());break;
        case 2: $("#prefecture").text($(this).text());break;
        case 3: $("#county").text($(this).text());break;
    }
    event.stopPropagation();//阻止冒泡事件
});

$(".district p").click(function(){
    event.stopPropagation();//阻止冒泡事件
});