
var t_img; // 定时器
var isLoad = true; // 控制变量
var count = 40
var imageCount =0;
var onepage = 5;
var miniPageNo = 1;
var totalPageNo = 0;
var curPageNo = 0;
$(function(){
    $("#updateImgForm").validate();
    $("#uploadImgForm").validate();
    resizePageDiv();

    /* 下面是表单里面的日期时间选择相关的 */
    $('.form_datetime').datetimepicker({
        format: 'yyyy-mm-dd hh:ii:00',
        weekStart : 1,
        autoclose : true,
        language : 'zh-CN',
        pickerPosition : 'bottom-left',
        todayBtn : true,
        viewSelect : 'hour'
    });
    /* 下面是表单里面的日期时间选择相关的 END */

    /*下面是时间选择器开始时间不能大于结束时间设置  START*/
    var startTime = $("#startDate").val();
    var endTime = $("#endDate").val();
    $('#endpicker').datetimepicker('setStartDate', startTime);
    $('#startpicker').datetimepicker('setEndDate', endTime);
    $('#endpicker')
        .datetimepicker()
        .on('show', function (ev) {
            startTime = $("#startDate").val();
            endTime = $("#endDate").val();
            $('#endpicker').datetimepicker('setStartDate', startTime);
            $('#startpicker').datetimepicker('setEndDate', endTime);
        });
    $('#startpicker')
        .datetimepicker()
        .on('show', function (ev) {
            endTime = $("#endDate").val();
            startTime = $("#startDate").val();
            $('#startpicker').datetimepicker('setEndDate', endTime);
            $('#endpicker').datetimepicker('setStartDate', startTime);
        });
    /*下面是时间选择器开始时间不能大于结束时间设置  END*/
});

/**
 * 改变放置图片的div的尺寸大小
 */
function resizePageDiv() {
    var maxHeight = 0;
    for(var i=1;i<=4;i++){
        var lastImageHeight = $(".pic_list").find(".span2:eq(-"+ i +")").length>0?$(".pic_list").find(".span2:eq(-"+ i +")").height():0;
        var lastImageTop = parseInt($(".pic_list").find(".span2:eq(-"+i+")").length>0?$(".pic_list").find(".span2:eq(-"+i+")").css("top"):0);
        maxHeight = maxHeight > (lastImageHeight + lastImageTop) ? maxHeight : (lastImageHeight + lastImageTop);
    }

    $(".pic_list").height(maxHeight + 75);
    $(".main_cont").height(maxHeight + 375);
}

/**
 * 弹框显示修改图片库图片
 * @param imageManageId
 * @param classifyId
 */
function showUpdateImage(imageManageId,classifyId){
    $("#up_imageManageId").val(imageManageId);
    $("#oldClassifyId").val($("#search_classifyId").val());
    $("#up_classifyId").val(classifyId);
    $("#editImage").modal("show");
}

/**
 * 确定修改图片分类
 */
function submitUpdateImageForm() {
    $.ajax({
        url:'updateImageManageActionToFormer.htm',
        data:$("#updateImgForm").serialize(),
        type:'POST',
        success:function(result) {
            //window.location.reload();
            var imageManageId = $("#up_imageManageId").val();
            var imageCate = $("#up_classifyId").find("option:selected").text();
            $("#imageCate"+imageManageId).text(imageCate);
            $("#editImage").modal("hide");
        }
    });
}

/**
 * 判断图片加载的函数
 * @param callback
 */
function isImgLoad(callback){
    // 注意我的图片类名都是cover，因为我只需要处理cover。其它图片可以不管。
    // 查找所有封面图，迭代处理
    $('.cover').each(function(){
        // 找到为0就将isLoad设为false，并退出each
        if(this.height === 0){
            count --;
            if(count <0) {
                $(this).css("height","200px");
                isLoad = true;
                return true;
            }
            isLoad = false;
            return false;
        }
    });
    // 为true，没有发现为0的。加载完毕
    if(isLoad){
        clearTimeout(t_img); // 清除定时器
        // 回调函数
        callback();
        // 为false，因为找到了没有加载完成的图，将调用定时器递归
    }else{
        isLoad = true;
        t_img = setTimeout(function(){
            isImgLoad(callback); // 递归扫描
        },1); // 我这里设置的是1毫秒就扫描一次，可以自己调整
    }
}

function queryNextPageImage() {
    miniPageNo ++;
    if(miniPageNo>onepage) {
        $(".table_foot").show();
        return;
    }
    $.ajax({
        url:'ajaxQueryImageForChoose.htm?pageSize=8&pageNo='+(totalPageNo+miniPageNo)+'&classifyId='+$("#search_classifyId").val()+'&startDate='+$("#startDate").val()+"&endDate="+$("#endDate").val(),
        success:function(data) {
            var imageList = data.list;
            if(imageList.length<1) {
                $(".table_foot").show();
            }
            var html = '';
            for(var i=0;i<imageList.length;i++) {
                imageCount ++;
                var image = imageList[i];
                html =
                    '<div class="span2 pcon prodcont actioninside new" >'+
                    '   <img class="media-object cover" style="display:block;width:230px;" src="'+image.imageManageUrl+'">'+
                    '   <p class="mt20">'+
                    '       <span class="pull-right" id="imageCate'+image.imageManageId+'">'+(image.imgClassify==null?"未分配":(image.imgClassify.classifyName==null?"未分配":image.imgClassify.classifyName))+'</span>'+
                    (new Date(image.imageOnlineDate)).format("yyyy-MM-dd hh:mm:ss") +
                    '   </p>'+
                    '   <div class="pull-right">'+
                    '       <button type="button" class="btn btn-default copyClass copy'+(totalPageNo+miniPageNo)+'" rot-value="'+image.imageManageUrl+'" ><i class="glyphicon glyphicon-link"></i></button>'+
                    '    </div>'+
                    '   <div class="pull-left">'+
                    '       <div class="btn-group" role="group" aria-label="...">'+
                    '           <button type="button" class="btn btn-default" onclick="showUpdateImage(\''+image.imageManageId+'\',\''+image.classifyId+'\')">修改</button>'+
                    '           <button type="button" class="btn btn-default" onclick="showDeleteOneConfirmAlert(\'deleteImageManageActionToFormer.htm?CSRFToken='+$("#CSRFToken").val()+'&infoImageManageId='+image.imageManageId+'&classifyId='+image.classifyId+'\')">删除</button>'+
                    '       </div>'+
                    '   </div>'+
                    '   <div class="clr"></div>'+
                    '</div>';
                $('#categoryProductContainer').append(html);

            }

            // 判断图片加载状况，加载完成后回调
            isImgLoad(function(){
                // 加载完成
                $('#categoryProductContainer').pinbox({subcontainer:'.actioninside'}).hide(0).fadeIn(1500);
                initCopy($(".copy"+(totalPageNo+miniPageNo)));
                resizePageDiv();
            });
        }
    });
}

/**
 * 图片分页按钮点击查询
 * @param bigPageNo
 */
function queryNextBigPage(bigPageNo) {
    $(".table_foot").hide();
    $('.span2').remove();
    $.ajax({
        url:'ajaxQueryImageForChoose.htm?pageSize=8&pageNo='+bigPageNo+'&classifyId='+$("#search_classifyId").val()+'&startDate='+$("#startDate").val()+"&endDate="+$("#endDate").val(),
        async:false,
        success:function(data) {
            var imageList = data.list;
            var html = '';
            for(var i=0;i<imageList.length;i++) {
                imageCount ++;
                var image = imageList[i];
                html =
                    '<div class="span2 pcon prodcont actioninside new" >'+
                    '   <img class="media-object cover" style="display:block;width:230px;" src="'+image.imageManageUrl+'">'+
                    '   <p class="mt20">'+
                    '       <span class="pull-right" id="imageCate'+image.imageManageId+'">'+(image.imgClassify==null?"未分配":(image.imgClassify.classifyName==null?"未分配":image.imgClassify.classifyName))+'</span>'+
                    (new Date(image.imageOnlineDate)).format("yyyy-MM-dd hh:mm:ss") +
                    '   </p>'+
                    '   <div class="pull-right">'+
                    '       <button type="button" class="btn btn-default copyClass copy'+(totalPageNo+miniPageNo)+'" rot-value="'+image.imageManageUrl+'" ><i class="glyphicon glyphicon-link"></i></button>'+
                    '    </div>'+
                    '   <div class="pull-left">'+
                    '       <div class="btn-group" role="group" aria-label="...">'+
                    '           <button type="button" class="btn btn-default" onclick="showUpdateImage(\''+image.imageManageId+'\',\''+image.classifyId+'\')">修改</button>'+
                    '           <button type="button" class="btn btn-default" onclick="showDeleteOneConfirmAlert(\'deleteImageManageActionToFormer.htm?CSRFToken='+$("#CSRFToken").val()+'&infoImageManageId='+image.imageManageId+'&classifyId='+image.classifyId+'\')">删除</button>'+
                    '       </div>'+
                    '   </div>'+
                    '   <div class="clr"></div>'+
                    '</div>';
                $('#categoryProductContainer').append(html);
            }
            // 判断图片加载状况，加载完成后回调
            isImgLoad(function(){
                // 加载完成
                $.fn.pinbox.staticInfo.lastScrollIndex = false;
                $.fn.pinbox.staticInfo.nextFillMatrixId = false;
                $.fn.pinbox.staticInfo.firstRun = true;
                $('#categoryProductContainer').pinbox({subcontainer:'.actioninside'}).hide(0).fadeIn(1500);
                initCopy($(".copy"+(totalPageNo+miniPageNo)));
                resizePageDiv();
            });
            loadPageDiv(data);
            //loadPageDiv(data);
            miniPageNo = 1;

        }
    });
}

function initCopy(obj) {
    $(obj).each(function() {
        $(this).zclip({
            path:'js/ZeroClipboard.swf',
            copy:function(){
                return $(this).attr("rot-value");
            },
            afterCopy:function(){
                showTipAlert("已复制到剪贴板");
            }
        });
    });
}

function loadPageDiv(page) {
    var pageDiv = '';
    var startNo = page.pageNo % onepage==10?parseInt(page.pageNo / onepage):parseInt(page.pageNo / onepage);
    var endNo = page.totalPages>(startNo + 10)?startNo+10:parseInt(page.totalPages/onepage);

    if(startNo < 6) {
        startNo = 6;
    }
    if(endNo <6) {
        endNo =7;
    } else if(endNo <16){
        endNo = 16;
    }

    var totalBigPageNo =page.totalPages%onepage==0?page.totalPages/onepage:(parseInt(page.totalPages/onepage)+1);
    var curPageNo = parseInt(page.pageNo/onepage);

    if(curPageNo>=totalBigPageNo-4) {
        endNo = totalBigPageNo+6;
        startNo = totalBigPageNo - 4;
    }
    if(totalBigPageNo<=6) {
        endNo = totalBigPageNo+6;
        startNo = 6;
    }
    totalPageNo = curPageNo;
    for(var i=startNo-5;i<endNo-5;i++) {
        if(curPageNo==i||(i==1&&curPageNo==0)) {
            pageDiv += '<span class="current"> '+i+' </span>';
        } else {
            pageDiv += '<a href="javascript:;" onclick="queryNextBigPage('+(i*onepage)+')" > '+i+' </a>';
        }
    }
    pageDiv += '<a href="javascript:;"> 共 '+(totalBigPageNo==0?1:totalBigPageNo)+' 页</a>';
    $("#pageDiv").html(pageDiv);
    resizePageDiv();
}