/**
 * 提示消息, msg:消息类容 type:消息类型 【succeed，warning，question，error】 title:消息标题 不填
 * 则为“消息” time:自动关闭时间,为空则不自动关闭 示例：showMessage("提示信息内容。", "", "","");
 */
function showMessage(msg, type, title, time) {
    var iconvalue;
    switch (type) {
        case 'succeed':
            mtitle = title || "成功";
            iconvalue = 'succeed';
            break;
        case 'warning':
            mtitle = title || "警示";
            iconvalue = 'warning';
            break;
        case 'question':
            mtitle = title || "提示";
            iconvalue = 'question';
            break;
        case 'error':
            mtitle = title || "错误";
            iconvalue = 'error';
            break;
        default:
            mtitle = title || "消息";
            iconvalue = 'warning';
            break;
    }
    art.dialog({
        id : 'globalMsgFrame',
        title : mtitle,
        icon : iconvalue,
        width : 320,
        height : 80,
        path:'/css',
        fixed : true,
        drag : false,
        resize : false,
        content : msg,
        ok: function () {
        }
    });
}
var dialog;
//弹层方法
function dialogPOP(url) {
    dialog = art.dialog({lock: true,background: '#000', opacity: 0.67 ,width: 600, height: 400});
    $.ajax({
        url: url,
        dataType: 'html',
        cache: false,
        success: function (data) {
            dialog.content(data);
        },
        cache: false
    });
}

(function($) {
    $.fn.tooltip = function(options) {

        var defaults = {
            cssClass: "", //CSS class or classes to style the tooltip
            delay: 0, //The number of milliseconds before displaying the tooltip
            duration: 500, //The number of milliseconds after moving the mouse cusor before removing the tooltip.
            xOffset: 15, //X offset will allow the tooltip to appear offset by x pixels.
            yOffset: 15, //Y offset will allow the tooltip to appear offset by y pixels.
            opacity: 0, //0 is completely opaque and 100 completely transparent
            fadeDuration: 400, //[toxi20090112] added fade duration in millis (default = "normal")
            overdelay: 500
        };

        var options = $.extend(defaults, options);


        return this.each(function(index) {

            var $this = $(this);

            //use just one div for all tooltips
            // [toxi20090112] allow the tooltip div to be already present (would break currently)
            $tooltip = $("#divTooltip");
            if ($tooltip.length == 0) {
                $tooltip = $('<div id="divTooltip"></div>');
                $('body').append($tooltip);
                $tooltip.hide();
            }

            //displays the tooltip
            $this.unbind('mouseover').mouseover(function(e) {
                //compatibility issue
                e = e ? e : window.event;

                //don't hide the tooltip if the mouse is over the element again
                clearTimeout($tooltip.data("hideTimeoutId"));

                //set the tooltip class
                $tooltip.removeClass($tooltip.attr("class"));
                $tooltip.css("width", "");
                $tooltip.css("height", "");
                $tooltip.addClass(options.cssClass);
                $tooltip.css("opacity", 1 - options.opacity / 100);
                $tooltip.css("position", "absolute");

                //save the title text and remove it from title to avoid showing the default tooltip
                $tooltip.data("title", $this.attr("data-title"));
                // $this.attr("data-title", "");
                $tooltip.data("alt", $this.attr("alt"));
                $this.attr("alt", "");

                //set the tooltip content
                $tooltip.html($tooltip.data("title"));
                // [toxi20090112] only use ajax if there actually is an href attrib present
                var href = $this.attr("href");
                if (href != undefined && href != "#")
                    $tooltip.html($.ajax({
                        url: $this.attr("href"),
                        async: false
                    }).responseText);

                //set the tooltip position
                winw = $(window).width();
                w = $tooltip.width();
                xOffset = options.xOffset;

                //right priority
                if (w + xOffset + 50 < winw - e.clientX)
                    $tooltip.css("left", $(document).scrollLeft() + e.clientX + xOffset);
                else if (w + xOffset + 50 < e.clientX)
                    $tooltip.css("left", $(document).scrollLeft() + e.clientX - (w + xOffset));
                else {
                    //there is more space at left, fit the tooltip there
                    if (e.clientX > winw / 2) {
                        $tooltip.width(e.clientX - 50);
                        $tooltip.css("left", $(document).scrollLeft() + 25);
                    }
                    //there is more space at right, fit the tooltip there
                    else {
                        $tooltip.width((winw - e.clientX) - 50);
                        $tooltip.css("left", $(document).scrollLeft() + e.clientX + xOffset);
                    }
                }

                winh = $(window).height();
                h = $tooltip.height();
                yOffset = options.yOffset;
                //top position priority
                if (h + yOffset + 50 < e.clientY)
                    $tooltip.css("top", $(document).scrollTop() + e.clientY - (h + yOffset));
                else if (h + yOffset + 50 < winh - e.clientY)
                    $tooltip.css("top", $(document).scrollTop() + e.clientY + yOffset);
                else
                    $tooltip.css("top", $(document).scrollTop() + 10);

                //start the timer to show the tooltip
                //[toxi20090112] modified to make use of fadeDuration option
                $tooltip.data("showTimeoutId", setTimeout("$tooltip.fadeIn(" + options.fadeDuration + ")", options.delay));
            });

            $this.unbind('mouseout').mouseout(function(e) {
                //restore the title
                // $this.attr("data-title", $tooltip.data("title"));
                $this.attr("alt", $tooltip.data("alt"));
                //don't show the tooltip if the mouse left the element before the delay time
                clearTimeout($tooltip.data("showTimeoutId"));
                //start the timer to hide the tooltip
                //[toxi20090112] modified to make use of fadeDuration option
                $tooltip.data("hideTimeoutId", setTimeout("$tooltip.fadeOut(" + options.fadeDuration + ")", options.duration));
            });

            $tooltip.hover(
                function() {
                    var _this = $(this);
                    clearTimeout($tooltip.data("hideTimeoutId"));
                    $tooltip.data("showTimeoutId", setTimeout("$tooltip.fadeIn(" + options.fadeDuration + ")", options.delay));
                },
                function() {
                    clearTimeout($tooltip.data("showTimeoutId"));
                    $tooltip.data("hideTimeoutId", setTimeout("$tooltip.fadeOut(" + options.fadeDuration + ")", options.duration));

                }
            );

            $this.click(function(e) {
                e.preventDefault();
            });

        });

    }
})(jQuery);