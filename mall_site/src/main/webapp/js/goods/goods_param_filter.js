$(function(){$(".goods_contrast").css("left",($(window).width()-1200)/2+210);$(".hide_ct").click(function(){$(".goods_contrast").hide()});$("img.lazy").lazyload({threshold:600,effect:"fadeIn",failurelimit:10,skip_invisible:false});$(".filter_item").each(function(){var _this=$(this);var f_name=_this.find("dt").text();var f_num=_this.attr("data-num");if(_this.find(".filterList ul").height()>22){_this.find(".f_more").removeClass("none")}_this.find("ul a").click(function(){var _a=$(this);var role=_a.attr("role");var f_value=_a.text();var p_value=_a.attr("param");var _selected='<li data-num="'+f_num+'">';if(role=="brand"){_selected+='<input type="hidden" name="brands" value="'+p_value+'">'}else{if(role=="eparam"){_selected+='<input type="hidden" name="params" value="'+p_value+'">'}else{if(role=="spec"){_selected+='<input type="hidden" name="spec" value="'+p_value+'">'}}}_selected+='<a href="javascript:;">'+f_name+"<em>"+f_value+"</em><b></b></a></li>";$("#selected_filter").show();$("#selected_filter ul").append(_selected);$(".more_filter:first").show().removeClass("more_filter");if(_this.find(".f_less").css("display")!="none"){_this.find(".filterList").height("");_this.find(".f_less").hide();_this.find(".f_more").show()}_this.addClass("selected").hide();if($(".filter_item:visible").length<4||($(".filter_item:visible").length==4&&$(".more_filter").length==0)){$(".show_more, .show_less").hide()}$("#pageBeanShowPage").val("1");if($("#AutoStyle").val()==""){$("#AutoStyle").val($("#carstyleID").val())}$("#searchForm").submit()})});$("#selected_filter ul a").click(function(){var _num=$(this).parent("li").attr("data-num");$(this).parent("li").remove();$(".pro_filter").find("dl[data-num="+_num+"]").show().removeClass("selected");if($(".filter_item:visible").length>4&&$(".show_less").css("display")=="none"){$(".filter_item:visible:last").addClass("more_filter").hide();$(".show_more").show()}if($("#selected_filter ul li").length==0){$("#selected_filter").hide()}$("#pageBeanShowPage").val("1");$("#AutoStyle").val("");$("#searchForm").submit()});$(".pro_filter .filter_item:eq(3)").nextAll(".filter_item").addClass("more_filter");$(".show_more").click(function(){$(".more_filter").removeClass("more_filter").show();$(this).hide();$(".show_less").show()});$(".show_less").click(function(){$(".filter_item:visible:eq(3)").nextAll(".filter_item:visible").addClass("more_filter").hide();$(this).hide();$(".show_more").show()});$(".f_more").click(function(){$(this).siblings(".filterList").height("auto");$(this).siblings(".f_less").show();$(this).hide()});$(".f_less").click(function(){$(this).siblings(".filterList").height("");$(this).siblings(".f_more").show();$(this).hide()});$(".cancel_filter").click(function(){$("#selected_filter ul li").remove();$("#selected_filter").hide();$(".filter_item").show();$(".pro_filter .filter_item:eq(3)").nextAll(".filter_item").addClass("more_filter").hide();$(".show_less").hide();$(".show_more").show();$("#pageBeanShowPage").val("1");$("#AutoStyle").val("");$("#searchForm").submit()});$(".changePages").click(function(){$("#pageBeanShowPage").val($(this).attr("pages"));if($("#AutoStyle").val()==""){$("#AutoStyle").val($("#carstyleID").val())}$("#searchForm").submit()})});function changeSearch(obj,objName,valu){if(objName=="searchSort"){var sort=$("#"+objName);if(sort.val()==""){sort.val(valu)}else{if(valu=="2D"){if(sort.val()=="2D"){sort.val("22D")}else{sort.val(valu)}}else{if(valu=="22D"){if(sort.val()=="22D"){sort.val("2D")}else{sort.val(valu)}}else{if(valu=="1D"){if(sort.val()=="1D"){sort.val("11D")}else{sort.val(valu)}}else{if(valu=="11D"){if(sort.val()=="11D"){sort.val("1D")}else{sort.val(valu)}}else{if(valu=="3D"){if(sort.val()=="3D"){sort.val("33D")}else{sort.val(valu)}}else{if(valu=="33D"){if(sort.val()=="33D"){sort.val("3D")}else{sort.val(valu)}}else{if(valu=="4D"){if(sort.val()=="4D"){sort.val("44D")}else{sort.val(valu)}}else{if($(this).attr("val")=="44D"){if(sort.val()=="44D"){sort.val("4D")}else{sort.val(valu)}}}}}}}}}}$("#pageBeanShowPage").val(1)}if(valu=="-1"){if(searchCheckShowStock.checked){$("#"+objName).val(1)}else{$("#"+objName).val(0)}$("#pageBeanShowPage").val(1)}else{if(valu=="-11"){$("#"+objName).val($(obj).val());$("#pageBeanShowPage").val(1)}else{if(objName!="searchSort"){$("#"+objName).val(valu)}else{if(!$(obj).hasClass("checked")){$("#"+objName).val(valu)}}}}var params=$(".selected");for(var i=0;i<params.length;i++){if($(params[i]).find("a").hasClass("param")){$("#searchForm").append("<input type='hidden' name=params value='"+$(params[i]).find("a").attr("id")+"' />")}}if($("#AutoStyle").val()==""){$("#AutoStyle").val($("#carstyleID").val())}$("#searchForm").submit()}function changeSearchKey(obj){window.location.href=$("#appBasePath").val()+"/searchProduct2.htm?title="+$(obj).html()}function win(){var _wd=$(window).width();var _hd=$(window).height();$(".dialog").css("top",(_hd-$(".dialog").height())/2).css("left",(_wd-$(".dialog").width())/2)}function dia(n){win();$(".mask").fadeIn();
    $(".dia"+n).fadeIn()}function cls(){$(".dialog").fadeOut();$(".mask").fadeOut()};