$(function(){

});

function showConsultDetail(consultId) {
    $.ajax({
        url: 'queryByCommentIdAjax.htm?commentId=' + consultId + '&CSRFToken=' + $("#CSRFToken").val(),
        success: function (data) {
            var comment = data.comment;
            var replist = data.replist;

            var html =
                '<img class="good_img" alt="" src="' + comment.goodsImg + '">' +
                '<div class="recommend_cont">' +
                '   <h4>' + comment.goodsName + '</h4>' +
                '   <p>发表人：<em class="text-info">' + comment.customerNickname + '</em></p>' +
                '   <p>' +
                '       <span>时间：'+format(comment.publishTime,"yyyy-MM-dd HH:mm:ss")+'</span>' +
                '   </p>' +
                '</div>' +
                '<div class="recommend_cont">';
            if (comment.isDisplay == '1') {
                html += '   <p>内容：<a href="javascript:;" onclick="updateCommentDisplay(this)" display="0" comment_id="'+comment.commentId+'" url="updatecomment.htm?commentId=' + consultId + '&isDisplay=0&commentContent=' + comment.commentContent + '&CSRFToken=' + $("#CSRFToken").val()+'" role="button" class="btn btn-sm btn-success">点击取消显示到商品页</a></p>';
            } else {
                html += '   <p>内容：<a href="javascript:;" onclick="updateCommentDisplay(this)" display="1" comment_id="'+comment.commentId+'" url="updatecomment.htm?commentId=' + consultId + '&isDisplay=1&commentContent=' + comment.commentContent + '&CSRFToken=' + $("#CSRFToken").val()+'" role="button" class="btn btn-sm btn-default">点击显示到商品页</a></p>';
            }

            html +=
                '   <p>' + comment.commentContent + '</p>' +
                 '</div>' +
                '<div class="reply_form" style="width:90%">'+
                '<p></p>';
            for (var i = 0; i < replist.length; i++) {
                html += '   <p>'+format(replist[i].publishTime,"yyyy-MM-dd HH:mm:ss")+'回复 <em class="text-info">' + comment.customerNickname + '</em> ：' + replist[i].commentContent;
                if(replist[i].isDisplay=='1') {
                    html += '<a style="margin-left:8px;" href="javascript:;" onclick="updateCommentRepDisplay(this)" display="0" url="updatecommentrep.htm?commentId='+comment.commentId+'&replayId=' + replist[i].replayId + '&isDisplay=0&CSRFToken=' + $("#CSRFToken").val()+'" role="button" class="btn btn-sm btn-success">点击取消显示</a>'
                } else {
                    html += '<a style="margin-left:8px;" href="javascript:;" onclick="updateCommentRepDisplay(this)" display="1" url="updatecommentrep.htm?commentId='+comment.commentId+'&replayId=' + replist[i].replayId + '&isDisplay=1&CSRFToken=' + $("#CSRFToken").val()+'" role="button" class="btn btn-sm btn-default">点击显示</a>'
                }
                html += '</p>';
            }

            html += '   <div>' +
            '       <form id="replyForm"><input type="hidden" name="customerId" value="'+comment.customerId +'"/>' +
            '       <input type="hidden" name="commentId" value="'+comment.commentId +'"/>' +
            '       <input type="hidden" name="customerNickname" value="商城 " />'+
            '       <input type="text" class="form-control pull-left w200" name="commentContent" id="commentContent">' +
            '       <button type="button" class="btn btn-info" onclick="submitReplyForm(\''+comment.customerNickname+'\','+comment.commentId+')">回复</button></form>' +
            '   </div>' +
            '</div>';
            $("#consult_detail").html(html);
        }
    });
    $('#scanAdvisory').modal('show');
}
/**
 * 修改咨询的显示状态
 * @param obj
 */
function updateCommentDisplay(obj) {
    var display = $(obj).attr("display");
    var commentId = $(obj).attr("comment_id");
    $.ajax({
        url:$(obj).attr("url"),
        success:function(data) {
            if(display=='1') {
                $(obj).attr("url",$(obj).attr("url").replace("isDisplay=1","isDisplay=0"));
                $(obj).text("点击取消显示到商品页");
                $(obj).removeClass("btn-default");
                $(obj).addClass("btn-success");
                $(obj).attr("display","0");

                $("#comment_display"+commentId).removeClass("label-default");
                $("#comment_display"+commentId).addClass("label-success");
                $("#comment_display"+commentId).text("是");
            } else {
                $(obj).attr("url",$(obj).attr("url").replace("isDisplay=0","isDisplay=1"));
                $(obj).text("点击显示到商品页");
                $(obj).removeClass("btn-success");
                $(obj).addClass("btn-default");
                $(obj).attr("display","1");

                $("#comment_display"+commentId).removeClass("label-success");
                $("#comment_display"+commentId).addClass("label-default");
                $("#comment_display"+commentId).text("否");
            }
        }
    });
}
/**
 * 修改咨询回复的显示状态
 * @param obj
 */
function updateCommentRepDisplay(obj) {
    var display = $(obj).attr("display");
    $.ajax({
        url:$(obj).attr("url"),
        success:function(data) {
            if(display=='1') {
                $(obj).attr("url",$(obj).attr("url").replace("isDisplay=1","isDisplay=0"));
                $(obj).text("点击取消显示");
                $(obj).removeClass("btn-default");
                $(obj).addClass("btn-success");
                $(obj).attr("display","0");
            } else {
                $(obj).attr("url",$(obj).attr("url").replace("isDisplay=0","isDisplay=1"));
                $(obj).text("点击显示");
                $(obj).removeClass("btn-success");
                $(obj).addClass("btn-default");
                $(obj).attr("display","1");
            }
        }
    });
}
/**
 * 咨询回复
 * @param customerNickname 用户昵称
 */
var num=0;
function submitReplyForm(customerNickname,commentId) {
    var commentContent = $("#commentContent").val();
    if(containSpecial(commentContent)){
        //modefied by luyong  注明特殊字符 2016-02-04
        showTipAlert("内容包含特殊字符(特殊字符：# , $ , %, ^ , & , * , - , _ , = , + , [ , ] , { , } , | , \\ , ' , \" , / , < , > )");
        return null;
    }
    if(commentContent!=''){
        num+=1;
        $.ajax({
            url:'addCommReplay.htm?new=1&CSRFToken='+$("#CSRFToken").val()+'&'+$("#replyForm").serialize(),
            success:function(data) {
                $(".reply_form").find("p").first().before('<p>'+format(new Date().getTime(),"yyyy-MM-dd HH:mm:ss")+'回复 <em class="text-info">' + customerNickname + '</em> ：'+commentContent+'<a style="margin-left:8px;" href="javascript:;" onclick="updateCommentRepDisplay(this)" display="1" url="updatecommentrep.htm?commentId='+commentId+'&replayId=' + data + '&isDisplay=1&CSRFToken=' + $("#CSRFToken").val()+'" role="button" class="btn btn-sm btn-default">点击显示</a></p>');
                $("#commentContent").val("");
            }
        });
    }
}


function containSpecial( s ) {
    //modefied by luyong  去掉空格和英文园括号的校验 2016-02-04
    //var containSpecial = RegExp(/[(\ )(\#) (\$)(\%)(\^)(\&)(\*)(\()(\))(\-)(\_)(\+)(\=) (\[)(\])(\{)(\})(\|)(\\)(\')(\")(\/) (\<)(\>)(\)]+/);
    var containSpecial = RegExp(/[(\#) (\$)(\%)(\^)(\&)(\*)(\-)(\_)(\+)(\=) (\[)(\])(\{)(\})(\|)(\\)(\')(\")(\/) (\<)(\>)(\)]+/);
    return ( containSpecial.test(s) );
}


var format = function(time, format){
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : '') + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        };
    });
};