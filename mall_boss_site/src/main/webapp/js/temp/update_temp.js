$(function () {
    //验证添加表单
    $("#updateTmep").validate();
    $("#imgChoose").change(function(){
        $("#updateTmep").attr("action","uploadChannelShowImg.htm");
        $("#updateTmep").attr("target","uploadFrame");
        $("#updateTmep").submit();
    });
    $("#channelUpdate").click(function(){
        $("#updateTmep").attr("action","updateTempInfo.htm");
        $("#updateTmep").attr("target","uploadFrame");
        $("#updateTmep").submit();
    })
});
function callback(data){
    $("#preview_image").attr("src",data);
    $("#updateTmep").attr("action","updateTempInfo.htm");
}
function callReload(){
    //$("#updateTmep").attr("target","");
    //location.reload();
    location.href="queryTempByType.htm";
}