var basePath = "http://"+window.location.host;
$(function(){
    //加载一级分类并显示第一级分类下的第二级
    getFirstClassification();

	var isok = 0;//判断动画是否完成状态
	//初始
	var rbarheight = 0;
	rbarheight=$(window).height();
	//初始高度
	$(".rbar").height(rbarheight-170);
	//初始数据
	var barid = $(".dqsel").find(".tbar .sel").attr("barid");
	//rightwin(barid);
	
	//一级按钮单机事件
	$(".lebar").on("click"," li h2",function(){
        var n = $(this).parent().index();
        setTimeout(function(){
            $(".lebar").scrollTop(40*n);
        },300);
		if(!isok){
			isok = 1;
			$("label").html("+");
			$(this).find("label").html("-");
			var barid = $(this).parent("li").find(".tbar .sel").attr("barid");
			//rightwin(barid);
			$(".dqsel").find(".tbar").slideUp(200);
			$(this).parent("li").find(".tbar").slideDown(200,function(){
				$(this).parent("li").addClass("dqsel").siblings("li").removeClass("dqsel");
				isok = 0;
			});
            addTwoType(this,$(this).attr("pid"));
		}
        var _this = this;
        setTimeout(function(){
            $(_this).parent().children(".tbar").children("li:nth-child(1)").addClass("sel");
        },300);
	});
	//二级菜单点击时间
	$(".lebar").on("click",".tbar li",function(){
		$(this).addClass("sel").siblings("li").removeClass("sel");
		//rightwin($(this).attr("barid"))
        $(".rbar").scrollTop(0);
	});
	//三级导航点击效果
	$(".rbar ul").on("click","li",function(){
		var barid = $(this).attr("barid");
		//页面跳转链接
		//window.location.href = "aa.html？";
	})
})
/*function rightwin(barid){
	//barid 二级分类的id
	var barid = barid;
	//二级分类图片地址
	var imgsrc = "images/cates/banner.png";
	//三级菜单的数据模拟数据
	var arryTbar = [[1,"前杠缓冲器"],[1,"前杠缓冲器"],[1,"前杠缓冲器"],[1,"前杠缓冲器"],[2,"前杠装饰灯"],[3,"前杠亮条"],[4,"抢杠格兰"],[5,"前杠格兰装"],[6,"师傅当时的"],[7,"asas"],[8,"诗圣杜甫说的"],[9,"我诗圣杜甫"],[3,"前杠亮条"],[4,"抢杠格兰"],[5,"前杠格兰装"],[6,"师傅当时的"],[7,"asas"],[8,"诗圣杜甫说的"],[9,"我诗圣杜甫"]]
	//拼接字符串
	var strhtml = "";
	var imgban = new Image();
	imgban.src = imgsrc;
	$(".imgban").html(imgban);
	for(var i=0;i<arryTbar.length;i++){
		strhtml += "<li barid='"+arryTbar[i][0]+"'>"+arryTbar[i][1]+barid+"</li>";
	}
	$(".rbar ul").html(strhtml);
}*/

function getFirstClassification() {
    $.getJSON(basePath+"/getIndexClassification.htm",{},function(data){
        var tt='';
        $("#firstType").html("");
        //循环加载一二级数据
        $.each(data[0].classifyBarList,function(k,v){
            var cla='';
            tc='+';
            if(k==0){
                cla='dqsel';
                tc='-';
            }
            tt+='<li class="'+cla+'" pid="'+v.classifyBarId+'">'
            +'<h2 pid="'+v.classifyBarId+'"><img src="images/cates/'+(k+1)+'.png" />'+ v.name+'<label>'+tc+'</label></h2>'
            +'<ul class="tbar">';
            if(k==0){
                $.each(data[1].classifyBarList,function(k,v){
                    var sel='';
                    if(k==0){
                        sel='sel';
                    }
                    tt+='<li class="'+sel+'" barid="'+ v.classifyBarId+'" onclick="addThreeType(\''+v.classifyBarId+'\')" >'+ v.name+'</li>';
                })
            }
            tt+='</ul>'
            +'</li>';
        })
        $("#firstType").html($(tt));
        //循环加载三级数据
        var tsd='<div class="imgban"><img src="images/cates/banner.png"></div><ul>';
        $.each(data[2].classifyBarList,function(k,v){
            tsd+='<li barid="'+v.classifyBarId+'" onclick="javascript:window.location.href=\'list/'+ v.goodsCatId+'\'"><a>'+ v.name+'</a></li>';
        })
        tsd+='</ul>';
        $(".rbar").html($(tsd));
    })
}

//点击二级加载三级数据
function addThreeType(id){
    $.getJSON(basePath+"/getIndexClassificationByfir.htm",{"parentID":id},function(data){
        //循环加载三级数据
        var tsd='<div class="imgban"><img src="images/cates/banner.png"></div><ul>';
        $.each(data.classifyBarList,function(k,v){
            tsd+='<li barid="'+v.classifyBarId+'" onclick="javascript:window.location.href=\'list/'+ v.goodsCatId+'\'"><a>'+ v.name+'</a></li>';
        })
        tsd+='</ul>';
        $(".rbar").html($(tsd));
    })
}
//点击一级菜单加载二级数据
function addTwoType(e,id){
    $.getJSON(basePath+"/getIndexClassificationByfir.htm",{"parentID":id},function(data){
        //循环加载三级数据
        var tsd='';
        $.each(data.classifyBarList,function(k,v){
            if(k==0){
                //加载三级数据
                addThreeType(v.classifyBarId);
            }
            tsd+='<li barid="'+v.classifyBarId+'" onclick="addThreeType(\''+v.classifyBarId+'\')">'+ v.name+'</li>';
        })
        $(e).parent().find(".tbar").html($(tsd));
    })

}






