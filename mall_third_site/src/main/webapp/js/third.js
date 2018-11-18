
emp_obj = null;
emp_id = null;
$(function(){
    /*加载显示单据名称
    $("#save_btn-sm").click(function(){
        $.ajax({
            type:'post',
            url:'toAddThirdLogisticsSingles.htm',
            success: function(data) {
                alert("张三");
                var options = "";
                for( var i=0; i<data.length; i++){
                    options +=  "<option value='"+data[i].shoreExpId+"'>"+data[i].expName+"</option>";
                }
                $("#companyId").html(options);
            }
        });
    });
     */



	jQuery.fn.isChildAndSelfOf = function(b){ return (this.closest(b).length > 0); };
	
	$(".oth_more").bind("mouseenter",function(){
		$(this).find(".mr_cont").show();
		});
	$(".oth_more").bind("mouseleave",function(){
		$(this).find(".mr_cont").hide();
		});
		
	$(".cls_tb tbody tr").each(function(){
		var cur = $(this);
		var gn = parseInt(cur.attr("class").substring(12,13));
		cur.find("td:eq(1)").css("text-align","left").css("text-indent",30*(gn-1));
		});
		
	//切换	
	function tabs(t1, t2, t3) {
		$("."+ t1).find("li:first").addClass("cur");
		$("."+ t2).find("."+ t3 +":first").fadeIn('slow').addClass("show");
		$("."+ t1 +" li").each(function(n){
			var current = $(this);
			$(this).find("a").click(function(){

					var cur = $(this);
					$("."+ t1).find("li.cur").removeClass("cur");
					$("."+ t2).find("."+ t3 +".show").hide().removeClass("show");
					current.addClass("cur");
					$("."+ t2 +" ."+ t3 +":eq("+ n +")").fadeIn('slow').addClass("show");
				});
			});
		};
		
	tabs('sp_tabs','sp_wp','sp_content');
	tabs('iq_tabs','iqy_wp','iqy_cont');
	
	$(".role_list li:first").addClass("cur");
	$(".account_cont .auth_wp:first").show().addClass("show");

	$(".cls_name").each(function(){
		var cur = $(this);
		cur.click(function(){
			cur.next("input").show().focus();
			cur.hide();
			});
		});
		$(".cls_input").blur(function(){
			var v = $(this).val();
			$(this).prev(".cls_name").text(v).show();
			$(this).hide();
			});
			
	$(".add_bt").click(function(){
		$(this).parents(".d_wp").hide();
		$(".bl_box").show();
		});
		
	//搜索选择
    $(".search_sel span").text($(".search_sel select").find("option:selected").text());
    $(".search_sel select").change(function(){
        var val = $(this).find("option:selected").text();
        $(".search_sel span").text(val);
    });
});
function ct(t){
	var n = $(t).index();
	if(!$(t).hasClass("cur")) {
		$(".role_list").find("li.cur").removeClass("cur");
		$(".account_cont").find(".auth_wp.show").hide().removeClass("show");
		$(t).addClass("cur");
		$(".account_cont .auth_wp:eq("+n+")").fadeIn("slow").addClass("show");
		};
};
	
function sec(e){
	var c_name = e.parents("tr").attr("class");
	var n_name = e.parents("tr").next("tr").attr("class");
	var cn = n_name.substring(7,13);
	var num = c_name.substring(12,13);
	var n = e.parents("tr").nextUntil("tr[class="+c_name+"]").length;
	var d = e.parents("tr").index();
	if(e.hasClass("open_sec")) {
		e.removeClass("open_sec").addClass("close_sec");
		for(var i=1;i<=n;i++) {
			var a = d+i;
			var tr = e.parents("tbody").find("tr:eq("+a+")");
			if($(tr).hasClass(cn)) {
				tr.show();
			};		
		};
	} else {
		 e.removeClass("close_sec").addClass("open_sec");
		 for(var i=1;i<=n;i++) {
				var a = d+i;
				var tr = e.parents("tbody").find("tr:eq("+a+")");
				if(tr.attr("class").substring(12,13) > num) {
					tr.hide();
					tr.find(".close_sec").removeClass("close_sec").addClass("open_sec");
				};
				
			};
	};
};

function menu_btn(t) {
	if($(t).parent().find(".dropdown-menu").is(":visible")) {
		$(t).parent().find(".dropdown-menu").hide();
	} else {
		$(t).parent().find(".dropdown-menu").show();
	};
	$(document).click(function(event){
		if($(event.target).isChildAndSelfOf($(t).parent().find(".dropdown-toggle"))) {
		
		} else {
			$(t).parent().find(".dropdown-menu").hide();
		};
	});
};
$(function () 
{
    $(".oth_more").bind("mouseenter", function () 
    {
        $(this).find(".mr_cont").show();
    });
    $(".oth_more").bind("mouseleave", function () 
    {
        $(this).find(".mr_cont").hide();
    });
    /* yuankk */
    jQuery.fn.isChildAndSelfOf = function (b) 
    {
        return (this.closest(b).length > 0);
    };
    $(".oth_more").bind("mouseenter", function () 
    {
        $(this).find(".mr_cont").show();
    });
    $(".oth_more").bind("mouseleave", function () 
    {
        $(this).find(".mr_cont").hide();
    });
    $(".cls_tb tbody tr").each( function () 
    {
        var cur = $(this);
        var gn = parseInt(cur.attr("class").substring(12, 13));
        cur.find("td:eq(1)").css("text-align", "left").css( "text-indent", 30 * (gn - 1));
    });
   
    $(".cls_name").each(function () 
    {
        var cur = $(this);
        cur.click(function () 
        {
            cur.next("input").show().focus();
            cur.hide();
        });
    });
    $(".cls_input").blur(function () 
    {
        var v = $(this).val();
        $(this).prev(".cls_name").text(v).show();
        $(this).hide();
    });
    
    
    
  //弹窗
	function win(){
		var hg = $(document).height();
		var wg = $(document).width();
		var s_hg = $(window).height();
		$(".mask").height(hg + 115);
		$(".dialog , .add_dialog").each(function(){
			var d_hg = $(this).height();
			var d_wd = $(this).width();
			$(this).css('left',(wg-d_wd)/2);
			$(this).css('top',(s_hg-d_hg)/2);
			});
		
		//$(".add_dialog").each(function(){
			//var d_hg = $(this).height();
			//var d_wd = $(this).width();
			//$(this).css('left',(wg-d_wd)/2);
			//$(this).css('top',(s_hg-d_hg)/2);
			//});
		};
	win();
	$(window).resize(function(){
		win();
		});
	
});

function dia(n,id,obj){
	$(".mask").fadeIn();
	$(".dia"+n).fadeIn();
	$("#del_id").val(id);
	emp_obj=obj;
	emp_id=id;
	};
	
function cls(){
	//去掉审核提示语 设置为请选择状态
	$('.iq_sel.old_order_sta').val(0);
	//隐藏审核提示
	$('.backOrderOption').hide();
	$(".dialog").fadeOut();
	$(".mask").fadeOut();
	};
function sec(e) 
{
    var c_name = e.parents("tr").attr("class");
    var n_name = e.parents("tr").next("tr").attr("class");
    var cn = n_name.substring(7, 13);
    var num = c_name.substring(12, 13);
    var n = e.parents("tr").nextUntil("tr[class=" + c_name + "]").length;
    var d = e.parents("tr").index();
    if (e.hasClass("open_sec")) 
    {
        e.removeClass("open_sec").addClass("close_sec");
        for ( var i = 1; i <= n; i++) 
        {
            var a = d + i;
            var tr = e.parents("tbody").find("tr:eq(" + a + ")");
            if ($(tr).hasClass(cn)) {
                tr.show();
            };
        };
    }
    else 
    {
        e.removeClass("close_sec").addClass("open_sec");
        for ( var i = 1; i <= n; i++) 
        {
            var a = d + i;
            var tr = e.parents("tbody").find("tr:eq(" + a + ")");
            if (tr.attr("class").substring(12, 13) > num) {
                tr.hide();
                tr.find(".close_sec").removeClass("close_sec").addClass( "open_sec");
            };
        };
    };
};
