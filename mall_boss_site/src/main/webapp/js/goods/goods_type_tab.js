 /**
	*标签控件
    *功能：按Enter或Tab或失去焦点确定标签输入完毕，双击文字可以编辑该标签，单击叉叉（×）表示删除该标签
    *tabControl:function
    *参数说明：
    *initTabCount:int 一开始初始化标签输入框的数量；
    *maxTabCount:int 容器可接受最大的标签数量；
    *tabMaxLen:int 每个标签允许接受最大的字符长度；
    *tabW:int 标签输入框的宽度；
    *tabH:int 标签输入框的高度；
    *tipTOffset:int 提示信息与标签输入框的top偏移量；
    *tipLOffset:int 提示信息与标签输入框的left偏移量；
    *tags:string 初始化的标签内容，以逗号为间隔；
 **/
$.fn.extend({
	tabControl: function(options, tags) {
		var defOpt = {
			initTabCount: 1,
			maxTabCount: 100,
			tabMaxLen: 50,
			tabW: 150,
			tabH: 15,
			tipTOffset: 5,
			tipLOffset: 0
		};
		var opts = $.extend(defOpt, options);
		var _tags = [];
		if (tags) {
			tags = tags.replace(/[^A-Za-z0-9_,\u4E00-\u9FA5]+/gi, "").replace(/^,+|,+$/gi, "");//将非中英文、数字、下划丝、逗号的其他字符都去掉，且不能以逗号开头与结束
			_tags = tags.split(',');
		}
		_tags = _tags.length > opts.maxTabCount ? _tags.slice(0, opts.maxTabCount - 1) : _tags;
		opts.initTabCount = opts.maxTabCount <= _tags.length ? _tags.length: _tags.length + (opts.maxTabCount - _tags.length > opts.initTabCount ? opts.initTabCount: opts.maxTabCount - _tags.length);
		var checkReg = /[^A-Za-z0-9_\u4E00-\u9FA5]+/gi;//匹配非法字符
		var initTab = function(obj, index) {//初始化标签输入
			var textHtml = "<input class='tabinput' name='tabinput' style='width:" + opts.tabW + "px;height:" + opts.tabH + "px;' type='text'/>";
			obj.append(textHtml);
			if (_tags[index]) {
				var __inputobj = $("input[type='text'][name='tabinput']", obj).eq(index);
				__inputobj.val(_tags[index].substr(0, opts.tabMaxLen)).css("display", "none");
				compTab(obj, __inputobj, _tags[index].substr(0, opts.tabMaxLen));
			}
			$("input[type='text'][name='tabinput']:last", obj).bind("keydown blur click",
			function(event) {
				if (event.type == "click") {//这个事件处理函数会接收到一个事件对象(event)，可以通过它来阻止（浏览器）默认的行为。如果既想取消默认的行为「event.preventDefault()」，又想阻止事件起泡「event.stopPropagation()」，这个事件处理函数必须返回false。
					return false;
				}
				if (event.keyCode == 13 || event.keyCode == 9 || event.type == "blur") {
					event.preventDefault();//主要是為了tab鍵，不要讓當前元素失去焦點（即阻止（浏览器）默认的行为）
					event.stopPropagation();
					var inputObj = $(this);
					var value = $(this).val().replace(/\s+/gi, "");
					if ((event.keyCode == 13 || event.keyCode == 9) && value != "")//主要是處理IE
					 inputObj.data("isIEKeyDown", true);
					if (event.type == "blur" && inputObj.data("isIEKeyDown")) {
						inputObj.removeData("isIEKeyDown");
						return;
					}
					if (value != "") {
						$(inputObj).removeClass("error");
						if (value.length > opts.tabMaxLen) {
							showMes($(this), "请输入1到" + opts.tabMaxLen + "个字符长度的标签");
							return;
						}
						//var _match = value.match(checkReg);
                        var _match = false;
						if (!_match) {
                            initValue(obj);
							compTab(obj, inputObj, value);
							if ($("input[type='text'][name='tabinput']", obj).length < opts.maxTabCount) {
								if (!inputObj.data("isModify"))
								 initTab(obj);
								else if (!$("input[type='text'][name='tabinput']", obj).is(":hidden")) {
									initTab(obj);
								}
							}
							$("input[type='text']:last", obj).focus();
							hideErr();
						}
						 else {
							showMes(inputObj, "內容不能包含非法字符「{0}」！".replace("{0}", _match.join(" ")));
						}
					}
					 else {
						if($(inputObj).next().length>0) {
							$(inputObj).addClass("error");
						}
					}
				}
			}).bind("focus",
			function() {
				hideErr();
			});
		}
		//完成标签编写
		var compTab = function(obj, inputObj, value) {
			inputObj.next("span").remove();//删除紧跟input元素後的span
            var expandParamId = inputObj.parent().find("input[name=paramId]").val();
			var _span =
                "<span name='tab' id='radius'>" +
                '   <input type="hidden" name="expandParamId" value="'+expandParamId+'"/>' +
                '   <input type="hidden" name="expandParamValueDelFlag'+(expandParamId==undefined||expandParamId==-1?'':expandParamId)+'" value="0"/>' +
                '   <input type="hidden" name="expandParamValueId'+(expandParamId==undefined||expandParamId==-1?'':expandParamId)+'" value="-1"/> ' +
                '   <input type="hidden" name="expandvalues'+(expandParamId==undefined||expandParamId==-1?'a':expandParamId)+'" value="'+value+'"/> ' +
                '   <input type="hidden" name="expandvaluesort'+(expandParamId==undefined||expandParamId==-1?'':expandParamId)+'" value="0"/> '+
                "   <b>" + value + "</b><a id='deltab'>×</a>" +
                "</span>";
			inputObj.after(_span).hide();
			inputObj.next("span").find("a").click(function() {
				if (confirm("确定删除该标签？")) {
					inputObj.next("span").remove();
					inputObj.remove();
                    if(expandParamId!=undefined && expandParamId!=-1) {
                        initValue(obj);
                    }
					if ($("span[name='tab']", obj).length == opts.maxTabCount - 1)
					 initTab(obj);
				}

			});
			inputObj.next("span").dblclick(function() {
				inputObj.data("isModify", true).next("span").remove();
				inputObj.show().focus();
			});
		}

		return this.each(function() {
			var jqObj = $(this);
			for (var i = 0; i < opts.initTabCount; i++) {
				initTab(jqObj, i);
			}
			jqObj.data("isInit", true);
			jqObj.click(function() {
				$("input[type='text'][name='tabinput']", jqObj).each(function() {
					if ($(this).val() == "") {
						$(this).focus();
						return false;
					}
				});
			});
		});
		function showMes(inputObj, mes) {
			var _offset = inputObj.offset();
			var _mesHtml = "<div id='errormes' class='radius_shadow' style='position:absolute;left:" + (_offset.left + opts.tipLOffset) + "px;top:" + (_offset.top + opts.tabH + opts.tipTOffset) + "px;'>" + mes + "</div>";
			$("#errormes").remove();
			$("body").append(_mesHtml);
		}
		function hideErr() {
			$("#errormes").hide();
		}
		function showErr() {
			$("#errormes").show();
		}
	},
	getTabVals: function() {//获取当前容器所生成的tab值，结果是一维数组
		var obj = $(this);
		var values = [];
		obj.children("span[name=\"tab\"][id^=\"radius\"]").find("b").text(function(index, text) {
			var checkReg = /[^A-Za-z0-9_\u4E00-\u9FA5]+/gi;
			values.push(text.replace(checkReg, ""));
		});
		return values;
	}
});

 function initDefaultTab(obj, inputObj) {
     var expandParamId = $(obj).find("input[name=paramId]").val();
     inputObj.next("span").find("a").click(function() {
         if (confirm("确定删除该标签？")) {
             inputObj.next("span").hide();
             inputObj.hide();
             inputObj.next("span").find("input[name=expandParamValueDelFlag"+expandParamId+"]").val('1');
         }

     });
     inputObj.next("span").dblclick(function() {
         inputObj.data("isModify", true).next("span").hide();
         inputObj.show().focus();
     });
    $(inputObj).bind("keydown blur click",
        function(event) {
            if (event.type == "click") {//这个事件处理函数会接收到一个事件对象(event)，可以通过它来阻止（浏览器）默认的行为。如果既想取消默认的行为「event.preventDefault()」，又想阻止事件起泡「event.stopPropagation()」，这个事件处理函数必须返回false。
                return false;
            }
            if (event.keyCode == 13 || event.keyCode == 9 || event.type == "blur") {
                event.preventDefault();//主要是為了tab鍵，不要讓當前元素失去焦點（即阻止（浏览器）默认的行为）
                event.stopPropagation();

                var checkReg = /[^A-Za-z0-9_\u4E00-\u9FA5]+/gi;//匹配非法字符
                var value = $(inputObj).val();
				if(value=='') {
					if($(inputObj).next().length>0) {
						$(inputObj).addClass("error");
						return;
					}
				}
                // _match = value.match(checkReg);
                var _match = false;
                if (!_match) {
                    initUpdateValue(inputObj);
                    compDefaultTab(obj, inputObj, value);
                }
            }
        });
    }

 //完成标签编写
function compDefaultTab(obj, inputObj, value) {
     //inputObj.next("span").remove();//删除紧跟input元素後的span
     //var _span = "<span name='tab' id='radius'><b>" + value + "</b><a id='deltab'>×</a></span>";

    inputObj.next("span").find("b").html(value);
    inputObj.next().find("b").html(value);
    inputObj.hide();
    inputObj.data("isModify", true).next("span").show();
    inputObj.next("span").find("a").click(function() {
        if (confirm("确定删除该标签？")) {
            inputObj.next("span").hide();
            inputObj.hide();
            inputObj.next("span").find("input[name=expandParamValueDelFlag]").val('1');
            initUpdateValue(inputObj);
        }

    });
     inputObj.next("span").dblclick(function() {
         inputObj.data("isModify", true).next("span").hide();
         inputObj.show().focus();
     });
 }

 function initValue(obj) {
	      var values = new Array();
     $(obj).parent().find("input[name=tabinput]").each(function () {
         values.push($(this).val());
     });
     var v = values.join("|");
     if((v.substr(v.length-1, v.length))=='|') {
         v = v.substr(0, v.length-1);
     };
    $(obj).parent().find("input[name=expandvalues]").val(v);
 }

 function initUpdateValue(inputObj) {
	 if($(inputObj).val()=='') {
		 $(inputObj).addClass("error");
		 return;
	 }
     var expandParamId = $(inputObj).parent().find("input[name=expandParamId]").val();
     $(inputObj).next().find("input[name=expandvalues"+expandParamId+"]").val($(inputObj).val());
 }

 function deleteExpandParamValue() {
     var update_expandParamId = document.getElementsByName("update_expandParamId");
     var paramId = document.getElementsByName("paramId");
     var expandParamValueId = document.getElementsByName("expandParamValueId");
     var expandParamValueDelFlag = document.getElementsByName("expandParamValueDelFlag");
     var expandParamValueName = document.getElementsByName("expandParamValueName");
     var expandParamValueSort = document.getElementsByName("expandParamValueSort");
     var expandParamIds = new Array();
     for (var i = 0; i < update_expandParamId.length; i++) {
         expandParamIds.push(update_expandParamId[i].value);
     };
     var paramIds = new Array();
     for (var i = 0; i < paramId.length; i++) {
         paramIds.push(paramId[i].value);
     };
     var expandParamValueIds = new Array();
     for (var i = 0; i < expandParamValueId.length; i++) {
         expandParamValueIds.push(expandParamValueId[i].value);
     }
     var expandParamValueDelFlags = new Array();
     for (var i = 0; i < expandParamValueDelFlag.length; i++) {
         expandParamValueDelFlags.push(expandParamValueDelFlag[i].value);
     }
     var expandParamValueNames = new Array();
     for (var i = 0; i < expandParamValueName.length; i++) {
         expandParamValueNames.push(expandParamValueName[i].value);
     }
     var expandParamValueSorts = new Array();
     for (var i = 0; i < expandParamValueSort.length; i++) {
         expandParamValueSorts.push(expandParamValueSort[i].value);
     }
     $.post("updateExpandParamValue.htm?",{
             'CSRFToken':$(".token_val").val(),
             'paramId' : paramIds, 'expandParamValueId' : expandParamValueIds, 'expandParamValueDelFlag' : expandParamValueDelFlags,
             'expandParamValueName' : expandParamValueNames, 'expandParamValueSort' : expandParamValueSorts,
             'expandParamId' : expandParamIds
         },
         function (data) {

         }
     );
 }
