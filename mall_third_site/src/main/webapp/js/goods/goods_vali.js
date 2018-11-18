//验证特殊字符，将调试显示到页面中
function checkSpecSymb(inputobj,Tipobj){
	 var regexp=new RegExp("[`~!()=':;'\\[\\]<>/]");
	 if (regexp.test( inputobj.val() ) ) {
		 inputobj.addClass( "ui-state-error" );
         updateTips( "输入的内容包含特殊字符!", Tipobj );
         return false;
     }else {
    	 inputobj.removeClass( "ui-state-error" );
     	 updateTipsSucc(null,Tipobj);
         return true;
     }
}
function checkSpecSymbAndDialog(inputobj,dialogName,tipValue){
	var regexp=new RegExp("[`~!()=''\\[\\]<>/]");
	 if (regexp.test( $("#"+inputobj).val() ) ) {
		 $("#"+inputobj).addClass( "ui-state-error" );
		 $("#inputTipValue").text(tipValue);
		 $("#"+dialogName).dialog(
			        {
			            resizable : false, height : 150, width : 270, modal : true, autoOpen : true, buttons : {
			                "确定" : function () 
			                {
			                    $(this).dialog("close");
			                }
			            }
		});
		$("#"+dialogName).dialog("open");
        return false;
    }
    else {
    	$("#"+inputobj).removeClass( "ui-state-error" );
        return true;
    }
}
//根据传过来的对象验证
function checkSymbAndDialog(inputobj,dialogName,tipValue){
	var regexp=new RegExp("[`~!()=''\\[\\]<>/]");
	 if (regexp.test( $(inputobj).val() ) ) {
		 $(inputobj).addClass( "ui-state-error" );
		 $("#inputTipValue").text(tipValue);
		 $(dialogName).dialog(
			        {
			            resizable : false, height : 150, width : 270, modal : true, autoOpen : true, buttons : {
			                "确定" : function () 
			                {
			                    $(this).dialog("close");
			                }
			            }
		});
		$(dialogName).dialog("open");
        return false;
    }
    else {
    	$(inputobj).removeClass( "ui-state-error" );
        return true;
    }
}
//根据传过来的对象验证长度
function checkLengthAndDialog(inputobj,dialogName,tipValue){
	 if ($(inputobj).val().length<=0 || $(inputobj).val()=='' ) {
		 $(inputobj).addClass( "ui-state-error" );
		 $("#inputTipValue").text(tipValue);
		 $(dialogName).dialog(
			        {
			            resizable : false, height : 150, width : 270, modal : true, autoOpen : true, buttons : {
			                "确定" : function () 
			                {
			                    $(this).dialog("close");
			                }
			            }
		});
		$(dialogName).dialog("open");
        return false;
    }else {
    	$(inputobj).removeClass( "ui-state-error" );
        return true;
    }
}
//根据传过来的对象验证是否为数字
function checkNumAndDialog(inputobj,dialogName,tipValue){
	 if (isNaN($(inputobj).val() ) ) {
		 $(inputobj).addClass( "ui-state-error" );
		 $("#inputTipValue").text(tipValue);
		 $(dialogName).dialog(
			        {
			            resizable : false, height : 150, width : 270, modal : true, autoOpen : true, buttons : {
			                "确定" : function () 
			                {
			                    $(this).dialog("close");
			                }
			            }
		});
		$(dialogName).dialog("open");
        return false;
    }
    else {
    	$(inputobj).removeClass( "ui-state-error" );
        return true;
    }
}
//更新错误提示框的状态
function updateTips( t, tip) 
{	
	if(null != tip){
		tip .text( t ) .addClass( "ui-state-highlight" );
	}
}
//更新错误提示框的状态
function updateTipsSucc(obj,tips) 
{
    $(tips) .text("") .removeClass( "ui-state-highlight" );
    $(tips).removeClass( "ui-state-highlight");
    $(obj).removeClass("ui-state-error");
}
/*验证是否已经存在的通用方法*/
/**
 * url  验证控制器
 * objName  字段名称
 * obj   input控件
 * objTips  提示框class名称
 * flag  是新添加  还是修改
 * oldObj 如果是修改 传过来旧值 如果输入的和旧值相同 就不验证
 * */
function checkExists(url,objName,obj,objTips,flag,oldObj){
	if(flag==0 && $(obj).val()!="" && $(obj).val()!=null){
		$.get(url+".htm?"+objName+"="+$(obj).val(),function(data){
			if(data==false){
				$(obj).addClass("ui-state-error");
				updateTips("名称或编号已经存在!",$("."+objTips));
				$(".checkExistsFlag").val(0);
			}else{
				updateTipsSucc($(obj),$("."+objTips));
				$(".checkExistsFlag").val(1);
			}
		});
	}else if(flag==1 && $(obj).val()!="" && $(obj).val()!=null){
		if($("."+oldObj).val()!=$(obj).val()){
			$.get(url+".htm?"+objName+"="+$(obj).val(),function(data){
				if(data==false){
					$(obj).addClass("ui-state-error");
					updateTips("名称或编号已经存在!",$("."+objTips));
					$(".checkExistsFlag").val(0);
				}else{
					updateTipsSucc($(obj),$("."+objTips));
					$(".checkExistsFlag").val(1);
				}
			});
		}else{
			updateTipsSucc($(obj),$("."+objTips));
			$(".checkExistsFlag").val(1);
		}
	}
}
/*验证货品是否可以添加*/
function selProductParam(objTips,flag_name){
	var flag=$("."+flag_name).val();
	var params=$(".tb_select");
	/*标记为0表示新添加*/
	if(flag==0){
		var url="checkParam.htm?goodsId="+$(".pro_goods_id").val()+"&paramLength="+$(".parem_len").val();
		for(var i=0;i<params.length;i++){
			url+="&paramIds="+$(params[i]).val();
		}
		$.get(url,function(data){
			if(data){
				updateTipsSucc(null,$("."+objTips));
				$(".checkProdcutExists").val(1);
			}else{
				updateTips("所选参数已经生成货品,请重新选择!",$("."+objTips));
				$(".checkProdcutExists").val(0);
			}
		});
	}else{
		/*如果是修改*/
		/*获取参数*/
		var updateSelParam=$(".tb_select");
		var oldparams=$(".old_param");
		var oldParams=new Array(oldparams.length);
		/*循环获得的值*/
		for(var i=0;i<oldparams.length;i++){
			oldParams[i]=$(oldparams[i]).val();
		}
		/*拍戏*/
		oldParams.sort();
		/*获得选中的值*/
		var selParams=new Array(params.length);
		for(var i=0;i<updateSelParam.length;i++){
			selParams[i]=$(updateSelParam[i]).val();
		}
		/*排序*/
		selParams.sort();
		/*拼接成字符串并比较*/
		var oldParamStr="";
		var nowParamStr="";
		for(var i=0;i<oldParams.length;i++){
			oldParamStr+=oldParams[i];
		}
		for(var i=0;i<selParams.length;i++){
			nowParamStr+=selParams[i];
		}
		/*end*/
		/*如果选中的和选择的一样  就取消提示*/
		if(oldParamStr==nowParamStr){
			updateTipsSucc(null,$("."+objTips));
			$(".checkProdcutExists").val(1);
		}else{
			var url="checkParam.htm?goodsId="+$(".pro_goods_id").val()+"&paramLength="+$(".parem_len").val();
			for(var i=0;i<updateSelParam.length;i++){
				url+="&paramIds="+$(updateSelParam[i]).val();
			}
			$.get(url,function(data){
				if(data){
					updateTipsSucc(null,$("."+objTips));
					$(".checkProdcutExists").val(1);
				}else{
					updateTips("所选参数已经生成货品,请重新选择!",$("."+objTips));
					$(".checkProdcutExists").val(0);
				}
			});
		}
	}
}
/*验证长度*/
function checkLength( o, n, tip, min, max ) 
{
    if ( o.val().length > max || o.val().length < min ) 
    {
        if(min==32){
        	o.addClass( "ui-state-error" );
            updateTips( n + " 长度必须是 " + max + "字符." , tip);
	    }else{
	    	o.addClass( "ui-state-error" );
            updateTips( n + " 长度必须在 " + min + "字符 ~ " + max + "字符之间." , tip);
		}
        
        return false;
    }
    else {
    	o.removeClass( "ui-state-error" );
    	updateTipsSucc(null,tip);
        return true;
    }
}
/*正则验证*/
function checkRegexp( o, regexp, n, tip ) 
{
    if ( !( regexp.test( o.val() ) ) ) {
        o.addClass( "ui-state-error" );
        updateTips( n, tip );
        return false;
    }
    else {
    	o.removeClass( "ui-state-error" );
    	updateTipsSucc(null,tip);
        return true;
    }
}
/*验证非空*/
function checkNull( obj,objTips ) 
{
    if ( $(obj).val().trim() == null || $(obj).val().trim() == "" ) {
    	obj.addClass( "ui-state-error" );
        updateTips( "不能输入空字符!", objTips );
        return false;
    }
    else {
    	obj.removeClass( "ui-state-error" );
    	updateTipsSucc(null,objTips);
        return true;
    }
}
/*初始化排序控件*/
function spin(){
	$(".sort_spinner").spinner({
		spin : function(event, ui) {
			 if (ui.value < 0) {
				$(this).spinner("value", 0);
				return false;
			}
		},
		step: 1,
	    numberFormat: "n"
	});
}
