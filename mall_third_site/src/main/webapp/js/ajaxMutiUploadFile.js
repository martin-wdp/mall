/**
 * ajax上传多张文件
 * 二：相关代码
	demo
(1)前台页面源码

<form action="testAction!testMethod.ac" method="post" enctype="multipart/form-data">
    <input type="file" id="file1" name="file1" value="asdf"/><br/>
    <input type="file" id="file2" name="file2"/><br/>
    <input type="button" id="submitBtn" value="upload"/><br/>
</form>

(2)前台js

$(function () {
	$('#submitBtn').click(function(){
		$.ajaxMutiUpload({
	       url:'testAction!testMethod.ac',
	       fileElementId: "file1,file2",
	       success: function(rs){
	           if(rs.flag){
	               alert('success');
	           }else{
	               alert('faild');
	           }
	       }
	   });
	});
});
 */

jQuery.extend({
	ajaxMutiUploadConfig: {
		'iframeId': 'ajaxFileUploadIFrame',
		'formId': 'ajaxFileUploadForm'
	},
	createAjaxMutiUploadForm: function (fileElementId, data) {//--------------------------step one: create form
		var $form = $('<form/>');
		
		//设置表单属性
		$form.attr({
			'action': param.url,
			'method': 'post',
			'name': $.ajaxMutiUploadConfig.iframeId,
			'id': $.ajaxMutiUploadConfig.formId,
			'target': $.ajaxMutiUploadConfig.iframeId,
			'enctype': 'multipart/form-data',
			'encoding': 'multipart/form-data' //fix ie
		}).css({
			'display': 'none'
		});
		
		//添加普通表单
		if (data) {
			for (var i in data) {
				$form.append('<input type="hidden" name="' + i + '" value="' + data[i] + '"/>');
			}
		}
		
		//添加文件类型的表单
		var fileElementIds = fileElementId.split(",");
		var i = 0;
		for (i; i < fileElementIds.length; i++) {
			var $oldFile = $('#' + fileElementIds[i]);
			var $newFile = $oldFile.clone();
			$oldFile.before($newFile);
			$form.append($oldFile);
		}
		jQuery($form).appendTo('body');
		
		return $form;
	},
	createAjaxMutiUploadIframe: function () {//--------------------------step two: create iframe
		var $ifm = $('<iframe name="' + $.ajaxMutiUploadConfig.iframeId + '"/>'); //fix ie
		$ifm.attr({
			'id': $.ajaxMutiUploadConfig.iframeId
		}).css({
			'display':'none'
		});
		
		$($ifm).appendTo('body');
		return $ifm.get(0);
	},
	ajaxMutiUpload: function (config) {//--------------------------step zero: 入口方法
		//如果存在ajaxFileUploadIFrame和ajaxFileUploadForm则删除
		var $ifm = $('#' + $.ajaxMutiUploadConfig.formId);
		var $f = $('#' + $.ajaxMutiUploadConfig.iframeId);
		$ifm.remove();
		$f.remove();
		
		//初始化参数
		param = {
			'url': '',
			'fileElementId': '',
			'success': null,
			'data':''
		};
		
		param = jQuery.extend({}, jQuery.ajaxSettings, config);
		var $formNew = jQuery.createAjaxMutiUploadForm(param.fileElementId, param.data);
		var v_iframe = jQuery.createAjaxMutiUploadIframe();
		
		//绑定iframeonload事件
		if(v_iframe.attachEvent){
		    v_iframe.attachEvent("onload", function(){
		        $.ajaxMutiUploadCallBack();
		    });
		}else{
		    v_iframe.onload = function(){
		        $.ajaxMutiUploadCallBack();
		    };
		}
		
		$formNew.submit();
	},
	ajaxMutiUploadCallBack: function(){
		var $ajaxMutiUploadIframe = $('#' + $.ajaxMutiUploadConfig.iframeId);
		
		var v_rs = $.parseJSON($ajaxMutiUploadIframe.contents().find('body').text());
		
		//调用回调函数
		param.success(v_rs);
	}
});