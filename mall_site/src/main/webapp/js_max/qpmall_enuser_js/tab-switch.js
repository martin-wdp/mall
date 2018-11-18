/*********************************************
用途：tab标签点击切换的实现
使用：头部引用，html代码按下面规则编写
<div class="tagMenu">
   <ul class="menu">
	   <li>第一个标签</li>
	   <li>第二个标签</li>
	</ul>
</div>
<div class="content">
	<div class="layout">
		第一块内容
	</div>
	<div class="layout">
		第二块内容
	</div>
</div>
*********************************************/
$(document).ready(function(){
  //$("ul.menu li:first-child").addClass("current");
  $("div.content").find("div.layout:not(:first-child)").hide();
  $("div.content div.layout").attr("id", function(){return idNumber("No")+ $("div.content div.layout").index(this)});
  $("ul.menu li").click(function(){
	 var c = $("ul.menu li");
	 var index = c.index(this);
	 var p = idNumber("No");
	  show(c,index,p);
  });
  
  function show(controlMenu,num,prefix){
	  var content= prefix + num;
	  $('#'+content).siblings().hide();
	  $('#'+content).show();
	  controlMenu.eq(num).addClass("current").siblings().removeClass("current");
  };

  function idNumber(prefix){
	  var idNum = prefix;
	  return idNum;
  };
});