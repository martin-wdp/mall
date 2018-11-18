/**
 * Created by Zhuoy on 2015/4/2.
 */
var CSRFToken = $("#CSRFToken").val();
/**
 * 搜索友情链接
 */
function friendlinkss(){
    $("#searchTextstr").val($("#searchText").val());
    $("#searchTextTwostr").val($("#searchTextTwo").val());
    $("#friendlinkform").submit();
}

/**
 * 添加友情链接
 */
var num=0;
function addfriendlink(){
    if($("#addform").valid()&&num==0){
        num+=1;
        $("#addform").submit();
    }
}

$(function(){
    //添加友情链接中，选择图片单击事件
    $(".choose_img_btn").click(function(){
        picId = $(this).attr("attr_id");
        art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken='+CSRFToken+'&size=1', {
            lock: true,
            opacity:0.3,
            width: '900px',
            height: '620px',
            title: '选择图片'
        });
    });
});

/**
 * 图片回调
 * @param url
 */
function saveChoooseImage(url) {
    $(".linkLogoImg").val(url);
    $(".lookImg").attr("src",url);
}

/**
 * 弹出框显示删除友情链接
 * @param linkId
 */
function dellinkYN(linkId){
    $("#dellinkId").val(linkId);
    $("#dellink").modal("show");
}

/**
 * 批量删除友情链接
 * @returns {boolean}
 */
function dellinksYN(){
    if(checkCombox()==false){
        return false;
    }
    $("#dellinks").modal("show");
}

/**
 * 确定删除友情链接
 */
function dellink(){
    var checkedList = new Array();
    checkedList.push($("#dellinkId").val());
    if(checkedList.length > 0){
        $.post('dellink.htm?CSRFToken='+$("#CSRFToken").val(),{linkIds:checkedList},function(result){
            if (result > 0){
                location.href="friendlink.htm";
            } else {
            }
        },'json');
    }
}

/**
 * 确定批量删除友情链接
 */
function dellinks(){
    var checkedList = new Array();
    $("input[name='linkId']:checked").each(function() {
        checkedList.push($(this).val());
    });
    if(checkedList.length > 0){
        $.post('dellink.htm?CSRFToken='+$("#CSRFToken").val(),{linkIds:checkedList},function(result){
            if (result > 0){
                location.href="friendlink.htm";
            } else {
            }
        },'json');
    }
}

/**
 * 判断是否选中按钮
 * @returns {boolean}
 */
function checkCombox(){
    var helpcateId = $("input[type='checkbox']:checked");
    //判断是否选择
    if(typeof(helpcateId.val())=="undefined"){
        showTipAlert("请至少选择一行!")
        return false;
    }else{
        return true;
    }
}

/**
 * 确定修改友情链接
 */
function updatelink(){
    if($("#updateform").valid()){
        $("#updateform").submit();
    }
}

/**
 * 弹框显示编辑友情链接
 * @param linkid
 */
function updatelinkmodal(linkid){
    $.ajax({
        type:"POST",
        url:"findlinkone.htm?CSRFToken="+$("#CSRFToken").val(),
        data:"linkId="+linkid,
        success:function(data){
            //填充值
            $("#up_linkid").val(data.linkId);
            $("#up_linkname").val(data.linkName);
            $("#up_linkurl").val(data.linkUrl);
            $("#up_Img").attr("src",data.linkLogo);
            $("#up_linksort").val(data.linkSort);
            $("#up_linkLogoImg").attr("src",data.linkLogo);
            if(data.isHidden==0){
                $("#isHidden0").attr("checked",'checked');
            }else{
                $("#isHidden1").attr("checked",'checked');
            }
            $( "#updateLinks" ).modal( "show" );
        }
    });
}