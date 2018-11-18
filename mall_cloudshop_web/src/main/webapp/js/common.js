/**
 * Created by Raby Lee on 2015/1/9.
 */
$(function(){

  /*   导航弹出菜单 
    var menu1 = '<div class="nav_sub"><h4>系统管理</h4><ul><li><a href="系统_系统管理_积分设置.html">积分设置</a></li><li><a href="系统_系统管理_站点设置.html">站点设置</a></li><li><a href="系统_系统管理_订单设置.html">订单设置</a></li><li><a href="系统_系统管理_库存预警.html">库存预警</a></li><li><a href="系统_系统管理_SEO设置.html">SEO设置</a></li><li><a href="系统_系统管理_异常页面设置.html">异常页面设置</a></li><li><a href="系统_系统管理_高级设置.html">高级设置</a></li></ul><h4>配送管理</h4><ul><li><a href="系统_配送管理_配送方式设置.html">配送方式设置</a></li><li><a href="系统_配送管理_物流公司设置.html">物流公司设置</a></li><li><a href="系统_配送管理_物流模板设置.html">物流模板设置</a></li></ul><h4>接口管理</h4><ul><li><a href="系统_接口管理_支付接口设置.html">支付接口设置</a></li><li><a href="系统_接口管理_登录接口设置.html">登录接口设置</a></li><li><a href="系统_接口管理_邮箱服务设置.html">邮箱服务设置</a></li><li><a href="系统_接口管理_短信接口设置.html">短信接口设置</a></li><li><a href="系统_接口管理_支付方式设置.html">支付方式设置</a></li><li><a href="系统_接口管理_E店宝设置.html">E店宝设置</a></li></ul><h4>权限管理</h4><ul><li><a href="系统_权限管理_管理员列表.html">管理员列表</a></li><li><a href="系统_权限管理_角色管理.html">角色管理</a></li><li><a href="系统_权限管理_操作日志.html">操作日志</a></li></ul><h4>地区管理</h4><ul><li><a href="系统_地区管理_地区列表.html">地区管理</a></li><li><a href="系统_地区管理_地区设置.html">地区设置</a></li></ul><h4>图片管理</h4><ul><li><a href="系统_图片管理_又拍云设置.html">又拍云设置</a></li></ul></div>',
        menu2 = '<div class="nav_sub"><h4>商品管理</h4><ul><li><a href="商品_商品管理_商品列表.html">商品列表</a></li><li><a href="商品_商品管理_添加商品01_选择类目.html">添加商品</a></li><li><a href="商品_商品管理_商品导入.html">商品导入</a></li><li><a href="商品_商品管理_商品关注.html">商品关注</a></li><li><a href="商品_商品管理_预警货品.html">预警货品</a></li></ul><h4>商品配置</h4><ul><li><a href="商品_商品配置_商品规格.html">商品规格</a></li><li><a href="商品_商品配置_商品组合.html">商品组合</a></li><li><a href="商品_商品配置_商品类型.html">商品类型</a></li><li><a href="#">商品标签</a></li><li><a href="商品_商品配置_商品分类.html">商品分类</a></li><li><a href="商品_商品配置_商品品牌.html">商品品牌</a></li></ul><h4>仓库管理</h4><ul><li class="active"><a href="商品_仓库管理_仓库列表.html">仓库列表</a></li></ul></div>',
        menu3 = '<div class="nav_sub"><h4>营销管理</h4><ul><li><a href="营销_营销管理_营销规则.html">营销规则</a></li><li><a href="营销_营销管理_商品促销列表.html">商品促销列表</a></li><li><a href="营销_营销管理_促销分组.html">促销分组</a></li></ul><h4>优惠券</h4><ul><li><a href="营销_优惠券_添加优惠券.html">添加优惠券</a></li><li><a href="营销_优惠券_优惠券列表.html">优惠券列表</a></li></ul></div>',
        menu4 = '<div class="nav_sub"><h4>投诉管理</h4><ul><li><a href="会员_投诉管理_未处理投诉列表.html">未处理投诉列表</a></li><li><a href="会员_投诉管理_已处理投诉列表.html">已处理投诉列表</a></li></ul><h4>咨询评论</h4><ul><li><a href="会员_咨询评论_咨询列表.html">咨询列表</a></li><li><a href="会员_咨询评论_评论列表.html">评论列表</a></li><li><a href="会员_咨询评论_消息设置.html">消息设置</a></li><li><a href="会员_咨询评论_晒单列表.html">晒单列表</a></li></ul><h4>会员管理</h4><ul><li><a href="会员_会员管理_会员列表.html">会员列表</a></li><li><a href="会员_会员管理_会员等级.html">会员等级</a></li><li><a href="会员_会员管理_推广返利记录.html">推广返利记录</a></li><li><a href="会员_会员管理_会员消费查询.html">会员消费查询</a></li><li><a href="会员_会员管理_会员积分查询.html">会员积分查询</a></li><li><a href="会员_会员管理_会员充值查询.html">会员充值查询</a></li></ul></div>',
        menu5 = '<div class="nav_sub"><h4>订单管理</h4><ul><li><a href="订单_订单管理_订单列表.html">订单列表</a></li><li><a href="订单_订单管理_退单列表.html">退单列表</a></li><li><a href="订单_订单管理_团购订单.html">团购订单</a></li><li><a href="订单_订单管理_抢购订单.html">抢购订单</a></li></ul></div>',
        menu6 = '<div class="nav_sub"><h4>出库管理</h4><ul><li><a href="出库_出库管理_拣货列表.html">拣货列表</a></li><li><a href="出库_出库管理_装箱列表.html">装箱列表</a></li><li><a href="出库_出库管理_出库列表.html">出库列表</a></li><li><a href="出库_出库管理_已拣货列表.html">已拣货列表</a></li><li><a href="出库_出库管理_已装箱列表.html">已装箱列表</a></li><li><a href="出库_出库管理_已出库列表.html">已出库列表</a></li></ul></div>',
        menu7 = '<div class="nav_sub"><h4>模板管理</h4><ul><li><a href="站点_模板管理_专题列表.html">专题列表</a></li><li><a href="站点_模板管理_模板列表.html">模板设置</a></li></ul><h4>前台管理</h4><ul><li><a href="站点_前台管理_在线客服.html">在线客服</a></li><li><a href="站点_前端管理_统计代码.html">统计代码</a></li></ul><h4>文章管理</h4><ul><li><a href="站点_文章管理_文章列表.html">文章列表</a></li><li><a href="站点_文章管理_栏目列表.html">栏目列表</a></li><li><a href="站点_文章管理_文章自定义属性.html">文章自定义属性</a></li></ul><h4>单页管理</h4><ul><li><a href="站点_单页管理_单页列表.html">单页列表</a></li><li><a href="站点_单页管理_单页标签.html">单页标签</a></li></ul><h4>帮助中心</h4><ul><li><a href="站点_帮助中心_帮助分类.html">帮助分类</a></li><li><a href="站点_帮助中心_帮助列表.html">帮助列表</a></li><li><a href="站点_帮助中心_服务支持列表.html">服务支持列表</a></li></ul></div>',
        menu8 = '<div class="nav_sub"><h4>数据分析</h4><ul><li><a href="统计_数据分析_销售分析列表.html">销售分析列表</a></li><li><a href="统计_数据分析_商家对账列表.html">商家对账列表</a></li><li><a href="统计_数据分析_销售量统计.html">销售量统计</a></li><li><a href="统计_数据分析_销售额统计.html">销售额统计</a></li><li><a href="统计_数据分析_商品销售排行.html">商品销售排行</a></li><li><a href="统计_数据分析_会员消费排行.html">会员消费排行</a></li></ul><h4>会员统计</h4><ul><li><a href="统计_会员统计_会员新增统计.html">会员新增统计</a></li><li><a href="统计_会员统计_会员地区统计.html">会员地区统计</a></li><li><a href="统计_会员统计_会员等级统计.html">会员等级统计</a></li></ul></div>';


    $('.nav ul li:eq(0)').mouseenter(function(){

        var positionX =($(this).width()/2) - 218;
        $('.nav ul li .menu_down').remove();
        $(this).append('<div class="menu_down popover bottom" style="position:absolute;top:32px; left: '+ positionX +'px; display: block;"><div class="arrow" style="left: 50%;"></div><div class="popover-content">' + menu1 + '</div></div>');
        $('.nav ul li:eq(0)').mouseleave(function(){
            $('.nav ul li:eq(0) .menu_down').remove();
        });
    });

    $('.nav ul li:eq(1)').mouseenter(function(){
        var positionX =($(this).width()/2) - 218;
        $('.nav ul li .menu_down').remove();
        $(this).append('<div class="menu_down popover bottom" style="position:absolute;top:32px; left: '+ positionX +'px; display: block;"><div class="arrow" style="left: 50%;"></div><div class="popover-content">' + menu2 + '</div></div>');
        $('.nav ul li:eq(1)').mouseleave(function(){
            $('.nav ul li:eq(1) .menu_down').remove();
        });
    });
    $('.nav ul li:eq(2)').mouseenter(function(){
        var positionX =($(this).width()/2) - 218;
        $('.nav ul li .menu_down').remove();
        $(this).append('<div class="menu_down popover bottom" style="position:absolute;top:32px; left: '+ positionX +'px; display: block;"><div class="arrow" style="left: 50%;"></div><div class="popover-content">' + menu3 + '</div></div>');
        $('.nav ul li:eq(2)').mouseleave(function(){
            $('.nav ul li:eq(2) .menu_down').remove();
        });
    });

    $('.nav ul li:eq(3)').mouseenter(function(){
        var positionX =($(this).width()/2) - 218;
        $('.nav ul li .menu_down').remove();
        $(this).append('<div class="menu_down popover bottom" style="position:absolute;top:32px; left: '+ positionX +'px; display: block;"><div class="arrow" style="left: 50%;"></div><div class="popover-content">' + menu4 + '</div></div>');
        $('.nav ul li:eq(3)').mouseleave(function(){
            $('.nav ul li:eq(3) .menu_down').remove();
        });
    });
    $('.nav ul li:eq(4)').mouseenter(function(){
        var positionX =($(this).width()/2) - 218;
        $('.nav ul li .menu_down').remove();
        $(this).append('<div class="menu_down popover bottom" style="position:absolute;top:32px; left: '+ positionX +'px; display: block;"><div class="arrow" style="left: 50%;"></div><div class="popover-content">' + menu5 + '</div></div>');
        $('.nav ul li:eq(4)').mouseleave(function(){
            $('.nav ul li:eq(4) .menu_down').remove();
        });
    });
    $('.nav ul li:eq(5)').mouseenter(function(){
        var positionX =($(this).width()/2) - 218;
        $('.nav ul li .menu_down').remove();
        $(this).append('<div class="menu_down popover bottom" style="position:absolute;top:32px; left: '+ positionX +'px; display: block;"><div class="arrow" style="left: 50%;"></div><div class="popover-content">' + menu6 + '</div></div>');
        $('.nav ul li:eq(5)').mouseleave(function(){
            $('.nav ul li:eq(5) .menu_down').remove();
        });
    });
    $('.nav ul li:eq(6)').mouseenter(function(){
        var positionX =($(this).width()/2) - 218;
        $('.nav ul li .menu_down').remove();
        $(this).append('<div class="menu_down popover bottom" style="position:absolute;top:32px; left: '+ positionX +'px; display: block;"><div class="arrow" style="left: 50%;"></div><div class="popover-content">' + menu7 + '</div></div>');
        $('.nav ul li:eq(6)').mouseleave(function(){
            $('.nav ul li:eq(6) .menu_down').remove();
        });
    });
    $('.nav ul li:eq(7)').mouseenter(function(){
        var positionX =($(this).width()/2) - 218;
        $('.nav ul li .menu_down').remove();
        $(this).append('<div class="menu_down popover bottom" style="position:absolute;top:32px; left: '+ positionX +'px; display: block;"><div class="arrow" style="left: 50%;"></div><div class="popover-content">' + menu8 + '</div></div>');
        $('.nav ul li:eq(7)').mouseleave(function(){
            $('.nav ul li:eq(7) .menu_down').remove();
        });
    });
*/

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
    $('.ctrl_nav li').mouseenter(function(){
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
