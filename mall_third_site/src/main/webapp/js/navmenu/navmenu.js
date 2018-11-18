/**
 * 
 */


$.ajax({
	url: "loadthirdpage.htm?cid="+$("#custId").val()+"&id="+Math.random(),
	context: document.body,
	async:false,
	success: function(data){
		loadMenu(data);
		showLeftMenu(showFirstFlag);
	}
});
var parendId=0;
var showFirstFlag=0;			
function loadMenu (objs,index){
    var num=0;
    var flag="";
    var flagcheck="";
    var pageId="";
	 if(objs!=null){
           for (var i = 0; i < objs.length; i++) {
	           	var obj=objs[i];
	           	if(obj.parentId== 0){
	           		//主菜单显示区
	           		parendId=obj.id;
	           		if(i==0){ showFirstFlag=parendId;}
	           		if(obj.menuVos!=null && obj.menuVos.length != 0){

	           			var s="<li id='"+"li"+obj.id+"'><a navid='"+obj.id+"' class='nav_a' href='"+obj.menuVos[0].url+"?n="+obj.id+"&l="+obj.menuVos[0].id+"'>"+obj.designation+"</a></li>";
		           		$("#indextop").append(s);
	           		}else{
	           			var s="<li id='"+"li"+obj.id+"'><a navid='"+obj.id+"' class='nav_a' href='"+obj.url+"?n="+obj.id+"'>"+obj.designation+"</a></li>";
		           		$("#indextop").append(s);
	           		}
	           	}else{
	           		//左侧菜单显示区
	           		//var str="<div class='menu_cont leftmenuxx"+obj.parentId+"' id="+"'leftmenu"+obj.id+"'><li><a href='"+obj.url+"' ><img src='"+obj.imgUrl+"' />"+obj.designation+"</a></li></div>";
	    	        var str="<li class='menu_cont leftmenuxx"+obj.parentId+"'><a leftmenuid='"+obj.id+"' href='"+obj.url+"?n="+obj.parentId+"&l="+obj.id+"'>"+obj.designation+"</a></li>";
	    		    $(".menu_box").append(str);
	           	}
	           	//递归显示菜单
	            if(obj.menuVos!=null){
	               loadMenu(obj.menuVos,parendId);
	            }
           }

    }
}

function showLeftMenu(objId){
//		$(".show_report").hide();
	$(".left_menu").show();
	$(".menu_cont").hide();
	$(".leftmenuxx"+objId).show();
	if(showFirstFlag!=0){
		//$(".leftmenuxx"+objId+" ul").css("display","block");
		$(".leftmenuxx"+objId+" ul h3").click();
	}
}

//页面加载查询 商家设置的功能路径
var thirdstoreurl;
$(function(){
    $.ajax({
        url:"getThirdStoreURL.htm",
        success:function(data){
            thirdstoreurl = data;
        },
        async: false
    });
});
//去 店铺首页页面
function ckeckThirdIndex(thirdId){
    var a;
    $.ajax({
        url:"checkThirdIndex.htm",
        success:function(data){
            a = data;
        },
        async: false
    });
   if(a){
        //弹出一个新窗口  店铺开启
        window.open(thirdstoreurl+"/thirdstoreindex/"+thirdId);
    }else{
       $('#go_store').modal('show');
    }
}