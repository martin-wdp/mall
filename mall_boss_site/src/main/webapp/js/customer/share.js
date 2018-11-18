
var bs;//站点信息
function showShareDetail(shareId) {
    $.ajax({
        url:'sharedetailAjaxNew.htm?shareId='+shareId+'&CSRFToken='+$("#CSRFToken").val(),
        success: function (data) {
            bs=data.bs;
            var share=data.shareVo
            var shareReply=data.shareReply;
            var html =
                '<img class="good_img" alt="" src="' + share.goodsImg + '">' +
                '<div class="recommend_cont">' +
                '   <h4>' + share.goodsName + '</h4>' +
                '   <p>发表人：<em class="text-info">' + share.customerName + '</em></p>' +
                '   <p>' +
                '       <span>时间：'+format(share.createTime,"yyyy-MM-dd HH:mm:ss")+'</span>' +
                '   </p>' +
                '</div>' +
                '<div class="recommend_cont">';
            if(share.isDisplay=='0') {
                html +=
                    '<p>内容：' +
                    '   <a href="javascript:;" onclick="updateShareDisplay(this)" display="0" share_id="'+share.shareId+'" url="updateshare.htm?shareId=' + shareId + '&isDisplay=1&CSRFToken=' + $("#CSRFToken").val()+'" role="button" class="btn btn-sm btn-default">点击显示到商品页</a>' +
                    '   <a id="index_display_btn" style="display:none;" href="javascript:;" onclick="updateShareDisplay(this)" display="2" share_id="'+share.shareId+'" url="updatesharetoindexone.htm?shareId=' + shareId + '&isDisplay=2&CSRFToken=' + $("#CSRFToken").val()+'" role="button" class="btn btn-sm btn-default">点击显示到首页</a>' +
                    '</p>';
            }

            if (share.isDisplay == '1') {
                html +=
                    '<p>内容：' +
                    '   <a href="javascript:;" onclick="updateShareDisplay(this)" display="1" share_id="'+share.shareId+'" url="updateshare.htm?shareId=' + shareId + '&isDisplay=0&CSRFToken=' + $("#CSRFToken").val()+'" role="button" class="btn btn-sm btn-success">点击取消显示到商品页</a>' +
                    '   <a id="index_display_btn" href="javascript:;" onclick="updateShareDisplay(this)" display="2" share_id="'+share.shareId+'" url="updatesharetoindexone.htm?shareId=' + shareId + '&isDisplay=2&CSRFToken=' + $("#CSRFToken").val()+'" role="button" class="btn btn-sm btn-default">点击显示到首页</a>' +
                    '</p>';
            }
            if(share.isDisplay=='2') {
                html +=
                    '<p>内容：' +
                    '   <a href="javascript:;" onclick="updateShareDisplay(this)" display="1" share_id="'+share.shareId+'" url="updateshare.htm?shareId=' + shareId + '&isDisplay=0&CSRFToken=' + $("#CSRFToken").val()+'" role="button" class="btn btn-sm btn-success">点击取消显示到商品页</a>' +
                    '   <a id="index_display_btn" href="javascript:;" onclick="updateShareDisplay(this)" display="3" share_id="'+share.shareId+'" url="updateshare.htm?shareId=' + shareId + '&isDisplay=1&CSRFToken=' + $("#CSRFToken").val()+'" role="button" class="btn btn-sm btn-success">点击取消显示到首页</a>';
                    '</p>';

            }

            html +=
                '   <p>' + share.shareContent + '</p>' +
                '</div>'+
                '<div class="reply_form" style="width:90%">'+
                '<p></p>';
            if(null != shareReply && shareReply.length != 0){
                for (var i = 0; i < shareReply.length; i++) {
                    html += '   <p>'+format(shareReply[i].createTime,"yyyy-MM-dd HH:mm:ss") ;
                    if(shareReply[i].customerId==null){
                        html+=' <em class="text-info"> '+data.bs.bsetName+' </em> 回复 <em class="text-info">'+share.customerName;
                    }else{
                        html+= '<em class="text-info"> '+share.customerName+' </em> 回复 <em class="text-info">'+data.bs.bsetName;
                    }
                    html+= '</em> ：' + shareReply[i].replyContent;
                    if(shareReply[i].isDisplay == '1') {
                        html += '<a style="margin-left:8px;" href="javascript:;" onclick="updateShareRepDisplay(this)" display="0" url="updatesharerep.htm?shareId='+share.shareId+'&shareReplyId=' + shareReply[i].shareReplyId + '&isDisplay=0&CSRFToken=' + $("#CSRFToken").val()+'" role="button" class="btn btn-sm btn-success">点击取消显示</a>'
                    } else {
                        html += '<a style="margin-left:8px;" href="javascript:;" onclick="updateShareRepDisplay(this)" display="1" url="updatesharerep.htm?shareId='+share.shareId+'&shareReplyId=' + shareReply[i].shareReplyId + '&isDisplay=1&CSRFToken=' + $("#CSRFToken").val()+'" role="button" class="btn btn-sm btn-default">点击显示</a>'
                     }
                    html += '</p>';
                }
           }

            html += '   <div>' +
            '       <form id="replyForm" method="post"><input type="hidden" name="customerId" value="'+share.customerId +'"/>' +
            '       <input type="hidden" name="shareId" value="'+share.shareId +'"/>' +
            '       <input type="hidden" name="customerName" value="商城 " />'+
            '       <input type="text" class="form-control pull-left w200" name="replyContent" id="replyContent">' +
            '       <button type="button" class="btn btn-info" onclick="submitShareReplyForm(\''+share.customerName+'\','+share.shareId+')">回复</button></form>' +
            '   </div>' +
            '</div>';

            html +=
                '<div class="mt20 od_list ">'+
                '    <p>晒单：';
            for(var i=0;i<share.imageList.length;i++) {
                html += '<a href="javascript:;"  data-img='+share.imageList[i].imageName+'><img class="img-thumbnail" alt="" src="'+share.imageList[i].imageName+'" width="100px" height="80px"></a><b></b>';
            }
            html +=
                '    </p>'+
                '</div> <div class="photo_viewer"><img alt="" src="" /></div>';

            $("#share_detail").html(html);
            $('#scanAdvisory').modal('show');
            initImgClick();

        }
    });

}


/*初始化晒单中的图片的点击事件*/
function initImgClick(){
    //评论晒单
    $(".od_list").each(function(){
        var _this = $(this);
        _this.find("a").click(function(){
            if($(this).hasClass("cur")) {
                $(this).removeClass("cur");
                _this.next(".photo_viewer").hide().width(0).height(0);
                _this.next(".photo_viewer").find("img").attr("src","").width(0).height(0);
            } else {
                var _src = $(this).attr("data-img");
                var img_url = _src + "?" + Date.parse(new Date());
                var _img = new Image();
                _img.src = img_url;
                var nw = _this.next(".photo_viewer").width();
                var nh = _this.next(".photo_viewer").height();
                _this.find(".cur").removeClass("cur");
                $(this).addClass("cur");
                _this.next(".photo_viewer").show().width(nw).height(nh);
                _this.next(".photo_viewer").find("img").attr("src",_src);
                _img.onload = function(){
                    var nw = _img.width + 8;
                    var nh = _img.height + 8;
                    if(nw > 490){
                        nw = 490;
                    }if(nh>430){
                        nh=430;
                    }
                    _this.next(".photo_viewer").find("img").animate({
                        width: nw - 8,
                        height: nh - 8
                    },500);
                    _this.next(".photo_viewer").animate({
                        width: nw,
                        height: nh
                    },500);
                };
            };
        });
        _this.next(".photo_viewer").click(function(){
            _this.find(".cur").removeClass("cur");
            $(this).hide().width(0).height(0);
            $(this).find("img").attr("src","").width(0).height(0);
        });
    });
}
/**
 * 晒单回复
 * @param customerNickname 用户昵称
 */
function submitShareReplyForm(customerNickname,shareId) {
    var commentContent = $("#commentContent").val();
    var replyContent = $("#replyContent").val();
    if(containSpecial(replyContent)){
        showTipAlert("内容包含特殊字符");
        return null;
    }
    if(replyContent=='') return;
    $.ajax({
        url:'addShareReplay.htm?CSRFToken='+$("#CSRFToken").val()+'&'+$("#replyForm").serialize(),
        type:'post',
        success:function(data) {
            $(".reply_form").find("p").first().before('<p>'+format(new Date().getTime(),"yyyy-MM-dd HH:mm:ss")+' <em class="text-info"> '+bs.bsetName+' </em> 回复 <em class="text-info">'+customerNickname+ '</em> ：'+replyContent+'<a style="margin-left:8px;" href="javascript:;" onclick="updateShareRepDisplay(this)" display="1" url="updatesharerep.htm?shareId='+shareId+'&shareReplyId=' + data + '&isDisplay=1&CSRFToken=' + $("#CSRFToken").val()+'" role="button" class="btn btn-sm btn-default">点击显示</a></p>');
            $("#replyContent").val("");
        }
    });

}



function containSpecial( s ) {
    var containSpecial = RegExp(/[(\ )(\#) (\$)(\%)(\^)(\&)(\*)(\()(\))(\-)(\_)(\+)(\=) (\[)(\])(\{)(\})(\|)(\\)(\')(\")(\/) (\<)(\>)(\)]+/);
    return ( containSpecial.test(s) );
}


/**
 * 修改晒单的显示状态
 * @param obj
 */
function updateShareDisplay(obj) {
    var display = $(obj).attr("display");
    var shareId = $(obj).attr("share_id");
    $.ajax({
        url:$(obj).attr("url"),
        success:function(data) {
            if(display=='0') {
                $(obj).attr("url",$(obj).attr("url").replace("isDisplay=1","isDisplay=0"));
                $(obj).text("点击取消显示到商品页");
                $(obj).removeClass("btn-default");
                $(obj).addClass("btn-success");
                $(obj).attr("display","1");
                
                $("#share_display"+shareId).removeClass("label-default");
                $("#share_display"+shareId).addClass("label-success");
                $("#share_display"+shareId).text("是");
                
                $("#index_display_btn").show();
            } else if(display=='1') {
                $(obj).attr("url",$(obj).attr("url").replace("isDisplay=1","isDisplay=0"));
                $(obj).text("点击显示到商品页");
                $(obj).removeClass("btn-success");
                $(obj).addClass("btn-default");
                $(obj).attr("display","0");
                
                $("#index_display_btn").removeClass("btn-success");
                $("#index_display_btn").addClass("btn-default");
                $("#index_display_btn").text("点击显示到首页");
                $("#index_display_btn").attr("display","2");
                $("#index_display_btn").hide();

                $("#share_display"+shareId).removeClass("label-success");
                $("#share_display"+shareId).addClass("label-default");
                $("#share_display"+shareId).text("否");
            } else if(display=='2') {
                $(obj).attr("url",$(obj).attr("url").replace("isDisplay=2","isDisplay=1"));
                $(obj).text("点击取消显示到首页");
                $(obj).removeClass("btn-default");
                $(obj).addClass("btn-success");
                $(obj).attr("display","3");
            } else if(display=='3') {
                $(obj).attr("url",$(obj).attr("url").replace("isDisplay=0","isDisplay=1"));
                $(obj).text("点击显示到首页");
                $(obj).removeClass("btn-success");
                $(obj).addClass("btn-default");
                $(obj).attr("display","2");
            }
        }
    });
}

/**
 * 批量显示到首页
 */
function batchDisplayToIndex() {
    $("#shareListForm").attr("action","updatesharetoindex.htm")
    showAjaxDeleteBatchConfirmAlert('shareListForm','parameterIds','确实要将这些记录显示到首页吗？');
}

/**
 * 批量删除
 */
function batchDeleteShare() {
    $("#shareListForm").attr("action","deleteshare.htm")
    showAjaxDeleteBatchConfirmAlert('shareListForm','parameterIds');
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


/**
 * 修改晒单回复的显示状态
 * @param obj
 */
function updateShareRepDisplay(obj) {
    var display = $(obj).attr("display");
    $.ajax({
        url:$(obj).attr("url"),
        success:function(data) {
            if(display == '1') {
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
