//店铺负责人保存按钮
function fuzeUpdateStore() {
	$(".form_fuze").submit();
}

// 店铺负责人新增
function fuzeInsertStore() {
	$(".addform_fuze").submit();
}

// 运营联系人update按钮
function yunyingUpdateStore() {
	$(".form_yunying").submit();
}

// 运营联系人添加
function yunyingInsertStore() {
	$(".addform_yunying").submit();
}

// 售后联系人提交按钮
function shouhouUpdateStore() {
	$(".form_shouhou").submit();
}

// 售后添加
function shouhouInsertStore() {
	$(".addform_shouhou").submit();
}

// 财务修改
function caiwuUpdateStore() {
	$(".form_caiwu").submit();
}
// 财务添加
function caiwuInsertStore() {
	$(".addform_caiwu").submit();
}

// 技术修改
function jishuUpdateStore() {
	$(".form_jishu").submit();
}
// 技术添加
function jishuInsertStore() {
	$(".addform_jishu").submit();
}

// 测试昵称
// 通过input 的class属性 (XXX_sell.conId)控制
function checkForm(diff) {
	// 测试昵称
	var nick = $(".nickname_" + diff).val();
	var nickname = /(?:[\u4E00-\u9FFF]{1,8}·\u4E00-\u9FFF]{1,8})|(?:[\u4E00-\u9FFF]{2,5})/;

	if (nick == null || nick == "") {
		$(".nickname_" + diff).next("label").html(" 请输入昵称！");
		return false;
	} else if (nickname.test(nick) == false) {
		$(".nickname_" + diff).next("label").html(" 请输入正确的姓名！");
		return false;
	}
	// 验证手机
	var cell = $(".pho_" + diff).val();
	var cellph = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
	if (cell == null || cell == "") {
		$(".pho_" + diff).next("label").html(" 请输入手机号码！");
	}
	if (cellph.test(cell) == false) {
		$(".pho_" + diff).next("label").html(" 请输入有效的手机号码！");
		return false;
	}

	// Email的input
		var str = $(".email_" + diff).val();
		var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		
		if (str != "") {
			if (reg.test(str) == false) {
				$(".email_" + diff).next("label").html(" 邮箱格式不正确！");
				return false;
			}
		}

	// 验证qqQQ
		var qq = $(".qqQQ_" + diff).val();
		var QQ = /^\s*[.0-9]{5,10}\s*$/;
		if (qq != "") {
			if (QQ.test(qq) == false) {
				$(".qqQQ_" + diff).next("label").html(" 请输入有效的QQ号码！");
				return false;
			}
		}
		

	// 验证固定电话
		var pho = $(".tel_" + diff).val();
		var phoZz = /^0\d{2,3}-\d{5,9}|0\d{2,3}-\d{5,9}$/;
		
		if (pho != "") {
			if (phoZz.test(pho) == false) {
				$(".tel_" + diff).next("label").html(" 请输入有效的固定电话！");
				return false;
			}
		}
	return true;
}
