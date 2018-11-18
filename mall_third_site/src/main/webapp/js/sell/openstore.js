
$(function(){
    //保存商家公司经营信息
    $(".enga_btn").click(function() {
    	var flag = validateChkFun.formSubmit(['#companyName', '#companyAddrDel', '#companyTel', '#companyEmail', '#companyCapital', '#companyContactName']);
    	if(flag){
			//联系人电话不为空就进入验证 如果为空就跳出验证，因为不是必填项
            if($(".companyContactTel").val()!=''){
				if(checkphone()){
					$("#enga_from").submit();
					$("#enga_from").prop("action","");
				}
			}else {
				$("#enga_from").submit();
				$("#enga_from").prop("action", "");
			}
    	}
    });
    /*验证商家信息*/
    $(".apti_btn").click(function() {
    	check();
    });

	/*  联系电话 不是必填项目 填写了就验证 没填写就不验证 */
	$("input[name='companyContactTel']").blur(function(){
		var reg= /^[1][3578]\d{9}$/;
		var companyContactTel = $(".companyContactTel").val();
		if(companyContactTel != ''){
			if(reg.test(companyContactTel)){
				$('#companyContactTelTip').html('输入正确');
				$('#companyContactTelTip').css('color',"green");
				return true;
			}else{
				$('#companyContactTelTip').html('请输入有效的联系方式');
				$('#companyContactTelTip').css('color',"red");
				$('.companyContactTel').css('border',"red");
				return false;
			}
		}
	});

    //因为时间空间与失去焦点事件冲突 所以时间单独验证 不适用验证框架
    function checktime(){
        var flag =  true;
        var bussDate =   $(".bussDate").val();
        var bussDatet =  $(".bussDatet").val();
        var companyCreTime =  $(".companyCreTime").val();
        if(companyCreTime==''){
            $('#companyCreTimeTip').html("请输入成立日期");
            $('#companyCreTimeTip').css("color","red");
            flag =  false;
        }else{
            $('#companyCreTimeTip').html("输入正确");
            $('#companyCreTimeTip').css("color", "green");
        }

        if(bussDate==''){
            $('#bussDateTip').html("请输入有效期的起始日期");
            $('#bussDateTip').css("color","red");
            flag =  false;
        }else{
            $('#bussDateTip').html("输入正确");
            $('#bussDateTip').css("color", "green");
        }

        if(bussDatet==''){
            $('#bussDateTip').html("请输入有效期的结束日期");
            $('#bussDateTip').css("color","red");
            flag =  false;
        }else{
                $('#bussDateTip').html("输入正确");
                $('#bussDateTip').css("color", "green");
        }

        if(bussDate!='' && bussDatet!=''){
            if(bussDate>bussDatet){
                $('#bussDateTip').html("结束日期不能大于起始日期");
                $('#bussDateTip').css("color","red");
                flag =  false;
            }else{
                $('#bussDateTip').html("输入正确");
                $('#bussDateTip').css("color", "green");
            }
        }
        return flag;
    }



    function check(){
        var flag = true;
        if(!checktime()){
            flag = false;
        }
        var regu="^.+(.gif|.jpg|.jpeg|.GIF|.JPG|.JPEG|.png|.PNG)$";
        var re = new RegExp(regu);
        var cardUrlE = $("#cardUrlE").val();
        var bussUrlE = $("#bussUrlE").val();
        var companyResearchUrlE = $("#companyResearchUrlE").val();
        var bussTaxCredUrlE = $("#bussTaxCredUrlE").val();
        var province=$("#province").find('option:selected').val();
        var city=$("#city").find('option:selected').val();
        var district=$("#district").find('option:selected').val();
        if(province=="" || province== null  || city=="" || city==null || district=="" || district==null){
            $("#province").addClass("n_error");
            $("#city").addClass("n_error");
            $("#district").addClass("n_error");
            flag = false;
        }
        if(!re.test(bussUrlE)){
            $("#bussUrlE").next().attr("style","color:red");
            flag = false;
        }
        if(!re.test(cardUrlE)){
            $("#cardUrlE").next().attr("style","color:red");
            flag = false;
        }
        if(!re.test(companyResearchUrlE)){
            $("#companyResearchUrlE").next().attr("style","color:red");
            flag = false;
        }
       if(!re.test(bussTaxCredUrlE) && bussTaxCredUrlE!=""){
            $("#bussTaxCredUrlE").next().attr("style","color:red");
            flag = false;
        }
        //if(!re.test(cardUrlE) || !re.test(bussUrlE) || !re.test(companyResearchUrlE) || !re.test(bussTaxCredUrlE)){
        //    flag = false;
        //}
        if(!validateChkFun.formSubmit(['#bussId','#bussRange','#bussDeptNo','#bussLegalName','#bussLegalCardId','#bussAddr'])){
            flag = false
        }
        if(flag){
            $("#aptitude_from").submit();
            $("#aptitude_from").prop("action","");
        }
    }

    /*银行页面*/
    $(".tax_btn").click(function() {
        var flag = true;
        var regu="^.+(.gif|.jpg|.jpeg|.GIF|.JPG|.JPEG|.png|.PNG)$";
        var re = new RegExp(regu);
        var bankUrlE = $("#bankUrlE").val();
        var bussTaxRegistUrlE = $("#bussTaxRegistUrlE").val();
        var bp = $("#province").find('option:selected').text();
        var bc = $("#city").find('option:selected').text();
        var bd = $("#district").find('option:selected').text();
        var bankAddr=bp+','+bc+','+bd;
        if(bp != '请选择'&& bc != '请选择' && bd != '请选择'){
            $("#bankAddr").val(bankAddr);
        }
        if(!re.test(bankUrlE) && bankUrlE != ""){
            $("#bankUrlE").next().attr("style","color:red");
            flag = false;
        }
        if(!re.test(bussTaxRegistUrlE)){
            $("#bussTaxRegistUrlE").next().attr("style","color:red");
            flag = false;
        }
    	if(!validateChkFun.formSubmit([  '#bussTaxRegistId','#bussTaxPayerId'])){
            flag = false;
        }//'#bankUsername',
    	if(flag){
    		$("#aptitude_from").submit();
    		$("#aptitude_from").prop("action","");
    	}
    });


    /*店铺信息页面*/
    $(".want_btn").click(function() {
    	var flag= validateChkFun.formSubmit([ '#storeName','#cids','#bids']);
    	if($(".hasa").val().length != "0" && $(".hasb").val().length != "0" ){
    	}else{
    		if($(".hasa").val().length != "0"){
    			
    		}
    		flag=false;
    	}
    	if(flag){
            var storeName = $("#storeName").val();
            $.ajax({
                url:"checqpmallNameIsUsing.htm?storeName="+storeName,
                type:"post",
                async:false,
                success:function(data){
                    if(data > 0){
                        $('#storeNameTip').removeClass("alertTip");
                        $('#storeNameTip').css('color',"red");
                        $('#storeNameTip').find('span').html("店铺名称已存在！请重新填写");
                    }else{
                        $('#storeNameTip').css('color',"green");
                        //  $('#storeNameTip').addClass("alertTip")
                        if($("#storeName").val()!=''){
                            $('#storeNameTip').find('span').html("输入正确");
                        }
                        $("#want_from").submit();
                        $("#want_from").prop("action","");
                    }
                }
            });


    	}
    });

    /*
    $("input[name=authId]").click(function(){
    	$("#authlistTip").hide();
    });
    
    
    $("#repassword").blur(function(){
    	if($("#repassword").val().trim().length == 0 ){
    		$("#repasswordTip").find('span').html("您输入的确认密码");
    		$("#repasswordTip").addClass("alertTipError");
    		$("#repasswordTip").show();
    		return;
    	}
    	if(($("#password").val() != $("#repassword").val()) ){
    		$("#repasswordTip").find('span').html("您输入的密码不相同");
    		$("#repasswordTip").addClass("alertTipError");
    		$("#repasswordTip").show();
    		return;
    	}
    	$("#repasswordTip").find('span').html("输入正确");
		$("#repasswordTip").addClass("alertTip");
		$("#repasswordTip").show();
    });
    
    $("#addEmp").click(function() {
    	var flag= validateChkFun.formSubmit([ '#customerUsername', '#password', '#realname']);//'#bankUsername',
    	if(($("#password").val() != $("#repassword").val()) ){
    		$("#repasswordTip").find('span').html("您输入的密码不相同");
    		$("#repasswordTip").addClass("alertTipError");
    		$("#repasswordTip").show();
    		flag = false;
    	}
    	if($("#repassword").val().trim().length == 0 ){
    		$("#repasswordTip").find('span').html("您输入的确认密码");
    		$("#repasswordTip").addClass("alertTipError");
    		$("#repasswordTip").show();
    		flag = false;
    	}
    	var authId=$("input[name=authId]:checked").val();
    	if(authId == null || authId == ""){
    		$("#authlistTip").find('span').html("请选择角色");
    		$("#authlistTip").css("margin-left","50px");
    		$("#authlistTip").addClass("alertTipError");
    		$("#authlistTip").show();
    		flag = false;
    	}
    	if(flag){
    		$("#repasswordTip").hide();
    		$("#authlistTip").hide();
    		$("#empForm").submit();
    		$("#empForm").prop("action","");
    	}
    });
    
     */
	/*保存修改客服設置*/
	$("#customer_service_btn").click(function(){
		var flag= validateChkFun.formSubmit(['#serviceQq']);
		if(flag){
			$("#customer_service_form").submit();
		}
	});

    validateChkFun.init(function() {
        validateChkFun.defaultChk({
            elem: '#companyName',
            onFocus: '请输入公司名称',
            isNull: '请输入公司名称',
            onCorrect: '输入正确',
            onlyNotNullChk: true,
            regExp: validateRegExp.companyname,
            onError: {
                badFormat: "公司名称格式不正确"
            }
        });
        validateChkFun.defaultChk({
        	elem: '#companyAddrDel',
        	onFocus: '请输入公司地址',
        	isNull: '请输入公司地址',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.companyaddr,
        	onError: {
        		badFormat: "公司地址格式不正确"
        	}
        });
          validateChkFun.defaultChk({
        	elem: '#companyTel',
        	onFocus: '请输入公司电话',
        	isNull: '请输入公司电话',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.companyTel,
        	onError: {
        		badFormat: "公司电话格式不正确"
        	}
        });

      validateChkFun.defaultChk({
        	elem: '#companyEmpNum',
        	onFocus: '请输入员工数量',
        	isNull: '请输入员工数量',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.intege1,
        	onError: {
        		badFormat: "员工数量格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#companyCapital',
        	onFocus: '请输入注册资金',
        	isNull: '请输入注册资金',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.intege1,
        	onError: {
        		badFormat: "注册资金格式不正确"
        	}
        });

        validateChkFun.defaultChk({
        	elem: '#companyEmail',
        	onFocus: '请输入电子邮箱',
        	isNull: '请输入电子邮箱',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.email,
        	onError: {
        		badFormat: "电子邮箱格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#companyContactName',
        	onFocus: '请输入联系人姓名',
        	isNull: '请输入联系人姓名',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.realname,
        	onError: {
        		badFormat: "联系人姓名格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#companyContactTel',
        	onFocus: '请输入联系人电话',
        	isNull: '请输入联系人电话',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.mobile,
        	onError: {
        		badFormat: "联系人电话格式不正确"
        	}
        });
       
        validateChkFun.defaultChk({
        	elem: '#bussLegalName',
        	onFocus: '请输入法定代表人姓名',
        	isNull: '请输入法定代表人姓名',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.realname,
        	onError: {
        		badFormat: "法定代表人姓名格式不正确"
        	}
        });
        
        validateChkFun.defaultChk({
        	elem: '#bussLegalCardId',
        	onFocus: '请输入法定代表人身份证号',
        	isNull: '请输入法定代表人身份证号',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.idcard,
        	onError: {
        		badFormat: "法定代表人身份证号格式不正确"
        	}
        });
        
        
        validateChkFun.defaultChk({
        	elem: '#bussId',
        	onFocus: '请输入营业执照号',
        	isNull: '请输入营业执照号',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.bussId,
        	onError: {
        		badFormat: "营业执照号格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#bussAddr',
        	onFocus: '请输入营业执照详细地址',
        	isNull: '请输入营业执照详细地址',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.companyaddr,
        	onError: {
        		badFormat: "营业执照详细地址格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#companyCreTime',
        	onFocus: '请输入成立日期',
        	isNull: '请输入成立日期',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	//regExp: validateRegExp.bussDate,
        	onError: {
        		badFormat: "成立日期格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#bussDate',
        	onFocus: '请输入营业执照有效期',
        	isNull: '请输入营业执照有效期',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	//regExp: validateRegExp.bussDate,
        	onError: {
        		badFormat: "营业执照有效期格式不正确"
        	}
        });
        validateChkFun.bussDateChk({
        	elem: '#bussDatet',
        	elems: '#bussDate',
        	onFocus: '请输入营业执照有效期',
        	isNull: '请输入营业执照有效期',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	//regExp: validateRegExp.bussDate,
        	onError: {
        		badFormat: "营业执照有效期格式不正确",
        		overFormat: "营业执照有效期起始时间大于结束时间"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#bussRange',
        	onFocus: '请输入经营范围',
        	isNull: '请输入经营范围',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.notempty,
        	onError: {
        		badFormat: "经营范围格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#bussDeptNo',
        	onFocus: '请输入组织机构代码',
        	isNull: '请输入组织机构代码',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.bussDeptNo,
        	onError: {
        		badFormat: "组织机构代码格式不正确"
        	}
        });
       // 税务 银行
        validateChkFun.defaultChk({
        	elem: '#bankUsername',
        	onFocus: '请输入银行开户名',
        	isNull: '请输入银行开户名',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.companyname,
        	onError: {
        		badFormat: "银行开户名格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#bankCardId',
        	onFocus: '请输入公司银行账号',
        	isNull: '请输入公司银行账号',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.bankNo,
        	onError: {
        		badFormat: "公司银行账号格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#bankName',
        	onFocus: '请输入开户银行支行名称',
        	isNull: '请输入开户银行支行名称',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.companyname,
        	onError: {
        		badFormat: "开户银行支行名称格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#bankId',
        	onFocus: '请输入支行银行号',
        	isNull: '请输入支行银行号',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.num1,
        	onError: {
        		badFormat: "支行银行号格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#bussTaxRegistId',
        	onFocus: '请输入税务登记证号',
        	isNull: '请输入税务登记证号',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.fullNumber,
        	onError: {
        		badFormat: "税务登记证号格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#bussTaxPayerId',
        	onFocus: '请输入纳税人识别号',
        	isNull: '请输入纳税人识别号',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.fullNumber,
        	onError: {
        		badFormat: "纳税人识别号格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#storeName',
        	onFocus: '请输入店铺名称',
        	isNull: '请输入店铺名称',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.companyname,
        	onError: {
        		badFormat: "店铺名称格式不正确"
        	}
        });
        validateChkFun.cateChk({
        	elem: '#cids',
        	isNull: '请添加店铺分类',
        	onCorrect: '',
        	onlyNotNullChk: true,
        	onError: {
        		badFormat: "店铺名称格式不正确"
        	}
        });
        validateChkFun.cateChk({
        	elem: '#cids',
        	isNull: '请添加店铺分类',
        	onCorrect: '',
        	onlyNotNullChk: true,
        	onError: {
        		badFormat: "店铺名称格式不正确"
        	}
        });
        validateChkFun.cateChk({
        	elem: '#bids',
        	isNull: '请添加主营品牌',
        	onCorrect: '',
        	onlyNotNullChk: true,
        	onError: {
        		badFormat: "店铺名称格式不正确"
        	}
        });
        
        validateChkFun.defaultChk({
        	elem: '#customerUsername',
        	onFocus: '请输入用户名',
        	isNull: '请输入用户名',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.username1,
        	onError: {
        		badFormat: "用户名格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#password',
        	onFocus: '请输入密码',
        	isNull: '请输入密码',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.password,
        	onError: {
        		badFormat: "密码格式不正确"
        	}
        });
        validateChkFun.defaultChk({
        	elem: '#realname',
        	onFocus: '请输入员工姓名',
        	isNull: '请输入员工姓名',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: validateRegExp.realname,
        	onError: {
        		badFormat: "员工姓名格式不正确"
        	}
        });
      //验证qq以‘|’分隔
        validateChkFun.defaultChk({
        	elem: '#serviceQq',
        	onFocus: '请输入客服qq,多个qq用“|”分开，第一个将作为默认客服',
        	isNull: '请输入客服qq',
        	onCorrect: '输入正确',
        	onlyNotNullChk: true,
        	regExp: "^\\d{4,12}(\\|\\d{4,12})*$",
        	onError: {
        		badFormat: "请输入4-12位的qq"
        	}
        });
    });
});

/**
 * 开店流程 同意开店协议 弹出框
 */
function confirmOpen(){
    //验证是否勾选了 阅读并且同意以上协议
	if($("#read_pro").prop("checked")){
		location.href="engageinfo.html";
	}
    //弹出提示框
    $('#check_point').modal('show');
}

/*联系电话 不是必填项目 填写了就验证 没填写就不验证 */
function checkphone(){
    var reg= /^[1][3578]\d{9}$/;
    var companyContactTel = $(".companyContactTel").val();
    if(reg.test(companyContactTel)){
       $('#companyContactTelTip').html('输入正确');
       $('#companyContactTelTip').css('color',"green");
		return true;
    }else{
       $('#companyContactTelTip').html('请输入有效的联系方式');
       $('#companyContactTelTip').css('color',"red");
       $('.companyContactTel').css('border',"red");
		return false;
    }
}