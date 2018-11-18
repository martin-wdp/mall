/**
 * Created by Raby Lee on 2015/1/9.
 */
$(function(){

 
    /* 导航弹出菜单 */

    /* 页面功能简单提示说明 */
    $('.jfsz').popover({
        html : true,
        placement : 'auto top',
        content : '系统管理中包含对系统的整体设置',
        trigger : 'hover'
    });
    /* 页面功能简单提示说明 END */

    /*******************************************
     *
     * 以下是为配合云销系统修改的新版增加内容
     *
     * *****************************************/

    /* 顶部右侧导航鼠标悬停显示更多内容 */
    $('.ctrl_nav li').click(function(){
        var $that = $(this);
        var $outbox = $(this).find('.popover');
        $outbox.show().css({
            'left' : -($outbox.width()/2) + ($that.width()/2) + 'px',
            'top' : '14px'
        });
    });
    $('.ctrl_nav li').mouseleave(function(){
        var $outbox = $(this).find('.popover');
        setTimeout(function(){
            $outbox.hide();
        },200);
    });

    /* 侧边栏手风琴效果 */
    $('.menu_title').click(function(){
        $(this).next().slideToggle('fast');
    });
    /* 侧边栏手风琴效果 END */

    /* 为了自适应页面，协调相关内容的宽高 */
    $('.main_cont').css('height',$(window).height() - 84 + 'px');
    $('.sidebar').css('height',($(window).height() - 84) + 'px');
    $(window).resize(function(){
        $('.main_cont').css('hight',$(window).height() - 84 + 'px');
        $('.sidebar').css('height',($(window).height() - 84) + 'px');
    });

    /* 为了自适应页面，协调相关内容的宽高 END */

    /*******************************************
     *
     * 以上是为配合云销系统修改的新版增加内容
     *
     * *****************************************/



});
function getBrowserInfo(){//判断浏览器及版本
    var agent = navigator.userAgent.toLowerCase() ;
    var regStr_ie = /msie [\d.]+;/gi ;
    var regStr_ff = /firefox\/[\d.]+/gi;
    var regStr_chrome = /chrome\/[\d.]+/gi ;
    var regStr_saf = /safari\/[\d.]+/gi ;

    if(agent.indexOf("msie") > 0){//IE
        return agent.match(regStr_ie) ;
    }
    if(agent.indexOf("firefox") > 0){//firefox
        return agent.match(regStr_ff) ;
    }
    if(agent.indexOf("chrome") > 0){//Chrome
        return agent.match(regStr_chrome) ;
    }
    if(agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0){//Safari
        return agent.match(regStr_saf) ;
    }
}
