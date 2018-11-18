$(function(){
    $(".caption-input").prop("placeholder",$(".wx_head span").text());
    $(".caption-input").blur(function(){
        var _text = $(this).val();
        $(".wx_head span").text(_text);
    });
});