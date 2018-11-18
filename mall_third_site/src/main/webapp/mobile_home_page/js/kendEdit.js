//富文本编辑器 
var editor;
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="content"]', {
		cssPath : 'js/kindeditor/plugins/code/prettify.css',
		uploadJson : 'js/kindeditor/jsp/upload_json.jsp',
		fileManagerJson : 'js/kindeditor/jsp/file_manager_json.jsp',
		allowFileManager : false,
		allowImageRemote : false,
		afterBlur : function() {
			this.sync();
		},
		items : [
					'source', '|', 'preview', '|', 'justifyleft', 'justifycenter', 'justifyright',
					'justifyfull', 'clearhtml', 'quickformat', '|',
					'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 
				    'hr', 'emoticons', 'anchor', 'link', 'unlink'
				]
	});
});

function refreshHtml(html){
	editor.clickToolbar('source');
	editor.html(html);
	editor.clickToolbar('source');
	
}

function clearHtml(){
	editor.html("");
}