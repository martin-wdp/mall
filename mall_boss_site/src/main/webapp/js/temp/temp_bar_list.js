
  
  
  $(function() {
    $( "input[type=submit],button" )
      .button()
      .click(function( event ) {
        event.preventDefault();
      });
    $( "#combobox" ).combobox();
    $( "#toggle" ).click(function() {
      $( "#combobox" ).toggle();
    });
  
  //添加按钮
  $( "#create" )
  .button()
  .click(function() {
	  //跳转到显示资讯单页
	  location.href="showTempBar.htm?tempId="+$("#tempId").val()+"&CSRFToken="+$("#CSRFToken").val();
	  
  });
  
  
  
  //修改弹出框
  $( "#update" )
  .button()
  .click(function() {
	  
	  //判断是否有按钮选中
	  if(checkCombox()==false){
		  return false;
	  }
	  
	//判断是否选中多个按钮
	  if(oneCheck("barId")==false){
		  return false;
	  }
	  
	  var barId = $("input[type='checkbox']:checked");  
	  location.href="showTempBar.htm?barId="+barId.val()+"&tempId="+$("#tempId").val()+"&CSRFToken="+$("#CSRFToken").val();
  });
  
  
  
	//删除弹出框
  $( "#delete" )
  .button()
  .click(function() {
	
	  //判断是否有按钮选中
	  if(checkCombox()==false){
		  return false;
	  }
	  
	//判断是否选中多个按钮
	  if(oneCheck("barId")==false){
		  return false;
	  }
	  
	  $("#dialog-confirm").dialog("open");
  });
	
	//批量删除弹出框
  $( "#deleteAll" )
  .button()
  .click(function() {
	
	  //判断是否有按钮选中
	  if(checkCombox()==false){
		  return false;
	  }
	  
	  $("#dialog-confirm").dialog("open");
  });
	
  $("#dialog-confirm").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				//删除数据
				var checkedList = new Array();
				$("input[name='barId']:checked").each(function() {
					checkedList.push($(this).val());
				});
		    	if(checkedList.length > 0){
		    		$.post('deleteTempBar.htm?CSRFToken='+$("#CSRFToken").val(),{barIds:checkedList},function(){  
		            	location.href="queryTempBarByPageBean.htm?tempId="+$("#tempId").val()
		            	+"&CSRFToken="+$("#CSRFToken").val();
		            });   
		        }  
	      		
			},
			'取消' : function() {
				$(this).dialog("close");
			}
		}
	});	
  });
//判断是否选中按钮
  function checkCombox(){
	  var barId = $("input[type='checkbox']:checked");  
	  //判断是否选择
	  if(typeof(barId.val())=="undefined"){
		  $("#dialog-tip").dialog(
				  {
					  resizable : false, height : 150, width : 270, modal : true, autoOpen : true, buttons : {
					      "确定" : function () 
					        {
					                $(this).dialog("close");
					        }
					 }
				 });
		  return false;
	  }else{
		  return true;
	  }
  }