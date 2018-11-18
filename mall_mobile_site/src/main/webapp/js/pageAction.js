/**
 * Created by zqy on 15/9/18.
 * silder function
 */


$(function () {
    slider();   //执行轮播

    TipBoxPosition();   //弹窗定位

    /*on-off  按钮控制*/
    $('.on-off').click(function () {
        if ($(this).attr('on')) {
            $(this).removeClass('on').addClass('off').removeAttr('on');

        } else {
            $(this).removeClass('off').addClass('on').attr('on', 'on');
        }
    });

})

/*首页轮播*/
function slider() {
    var _slideLi = $('.slider .slide-body li');     //li数组
    var _slideLiH = _slideLi.height();      //li 高度
    $('.slider').height(_slideLiH);     //根据li高度设置slider高度
    var _slide = $('.slider .slide-body');  //轮播体
    var _slideH = $('.slider .slide-body').height();    //轮播体高度
    var slideNav = $('.slider .slide-nav li');      //轮播目录一point
    var _liFirst;

    if (_slideLi.first().get(0)) {
        _liFirst=_slideLi.first().get(0).outerHTML;
    }
    _slide.append(_liFirst);
    var curIndex = 1;
    slideNav.removeClass('cur');
    slideNav.eq(0).addClass('cur');
    setInterval(function () {
        if (curIndex > _slideH / _slideLiH) {
            _slide.css({'margin-top': '0'});
            curIndex = 1;
        }
        slideNav.removeClass('cur');
        if (curIndex == _slideLi.length || curIndex == 0) {
            slideNav.eq(0).addClass('cur');
        }
        slideNav.eq(curIndex).addClass('cur');
        _slide.animate({'margin-top': -_slideLiH * curIndex + 'px'});
        curIndex++;
    }, 2000);
}

/*弹窗定位*/
function TipBoxPosition(){
    if($('.tip-box')!=null){
        var mt=$('.tip-box').height()/2;
        $('.tip-box').css('margin-top',-mt+"px");
    }
}