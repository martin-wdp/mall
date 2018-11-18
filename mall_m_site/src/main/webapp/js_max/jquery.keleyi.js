/*!
* Keleyi(jQuery Menu)
* version: 0.1.3
* Copyright (c) 2013 KeLeyi
* http://keleyi.com
* http://keleyi.com/keleyi/
*/
(function ($) {
    $.fn.keleyi = function (options) {

        var settings = $.extend({
            width: '986px',
            margin: '0px auto',
            item_background_color_hover: '#005500',
            item_background_color: 'transparent',
            item_width: 'auto',
            item_margin: '0px 0px 0px 10px',
            bar_height: 'auto',
            bar_position: 'fixed',
            bar_background_color: "#008000",
            bar_bottom: "0px"
        }, options);


        $(this).addClass("keleyi-menu");
        $(this).css({ "width": settings.width, "margin": settings.margin });

        $(this).wrap("<div class='keleyi-menubar'></div>");
        $(this).parent().css({ "background-color": settings.bar_background_color
        , "height": settings.bar_height, "position": settings.bar_position
        , "bottom": settings.bar_bottom, "min-width": settings.width
        });

        $(this).parent().append("<div style='width:100%;clear:both;height:0px;'></div>");

        $(this).find(">li").css({"background-color": settings.item_background_color, "margin": settings.item_margin });

        $(this).find(">li>a").click(function () {
			if(!$(this).parent().find('ul').is(':visible')){
			$('.keleyi-menu ul').hide();	
            $(this).parent().css({ "background-color": settings.item_background_color_hover });
            var k_ul = $(this).parent().find("ul");
            k_ul.css({ "background-color": settings.item_background_color_hover }).show();
            if (k_ul.width() < $(this).parent().width())
              k_ul.width($(this).parent().width());
			}
			else{
			  $(this).parent().find("ul").hide();
              $(this).parent().css("background-color", settings.item_background_color);
			}
        });
    }
} (jQuery));
