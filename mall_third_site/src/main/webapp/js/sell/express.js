function updateStoreInfo(str){
	$("."+str).hide();
	$("."+str+"_edit").show();
}
function cancelUpdate(str){
	$("."+str).show();
	$("."+str+"_edit").hide();
}

//控制物流默认与否，删除按钮
function modifyemptodisable(custId,flag){
	$(".exp_id_hide").val(custId);
	$(".flag_id_hide").val(flag);
	//判断默认条数
	if(flag == 0 ){
		$(".control-label").html("你确定要开启吗?");
	}else {
		$(".control-label").html("你确定要关闭吗?");
	}

}
/*更改物流状态*/
function dodisable(){
	var url="third/updatedefaultstate.htm?shoreExpId="+$(".exp_id_hide").val();
	var url2="third/updatebackstate.htm?shoreExpId="+$(".exp_id_hide").val();
	//开启物流
    if($(".flag_id_hide").val()==0  ){
		location.href = url;
	}
	//关闭物流
	if($(".flag_id_hide").val()==1){
		location.href = url2;
	}
	$(".exp_id_hide").val("");
}
/**
 * 删除单个物流信息弹出框
 * @param custId
 */
function delemp(custId){
	$(".exp_id_hide").val(custId);
}
/**
 * 删除单个物流信息
 */
function dodelemp(){
	var url="third/deleteexpress.htm?shoreExpId="+$(".exp_id_hide").val();
	location.href = url;
	$(".exp_id_hide").val("");
}
//修改控制
function updateexp(id){
	$(".exp_id_hide").val(id);
    var url="updatexpress.html?shoreExpId="+$(".exp_id_hide").val();
    $.ajax({
        type: 'post',
        url:url,
        async:true,
        success: function(data) {
            if(data!=null){
                /*设置物流属性的值*/
                $('.shoreExpId_update').val(data.shoreExpId);
                $('.expname_updatexpress').val(data.expName);
                $('.expcompany_updatexpress').val(data.expCompany);
                $('#kuaidi_updatexpress').val(data.kudi100code);
                if(data.isDefault==0){
                    $(".isDefaults_update").attr("checked","checked");
                }else{
                    $(".isDefault_update").attr("checked","checked");
                }
            }
        }

    });
}


function toaddemp(){
	location.href="addexpress.html";
}
//提交添加信息
function doaddexpmoren(){
	$("#expForm").submit();
}

function back(){
	location.href="expresslist.html";
}


/*修改验证*/
function update_express(diff){
    if(checkForm(diff)){
		var url="checkExpressNo.html?expCompany="+$(".expcompany_updatexpress").val()+"&shoreExpId="+$('.shoreExpId_update').val();
		$.ajax({
			type: 'post',
			url:url,
			async:true,
			success: function(data) {
				if(data>0){
					$(".expcompany_Tip").html("物流编号不能重复！");
					$(".expcompany_Tip").css("color","red");
				}else{
					$("#updatebutton").removeAttr("onclick");
					$("#expform_updates").submit();
				}
			}

		});

    }
}

/*保存验证*/

function add_express(diff){
    if(checkForm(diff)){
		var url="checkExpressNo.html?expCompany="+$(".expcompany_addexpress").val();
		$.ajax({
			type: 'post',
			url:url,
			async:true,
			success: function(data) {
				if(data>0){
					$(".expcompany_Tip").html("物流编号不能重复！");
					$(".expcompany_Tip").css("color","red");
				}else{
					$("#savebutton").removeAttr("onclick");
					$("#expForm_add").submit();
				}
			}

		});

    }
}
/**
 * 验证物流信息
 * @param diff
 * @returns {boolean}
 */
function checkForm(diff){
	var expname=$(".expname_"+diff).val();
	if (expname == null || expname == "") {
		$(".expname_" + diff).next("label").html(" 请输入物流名称！");
        $(".expname_" + diff).next("label").css("color","red");
		return false;
	}
	$(".expname_" + diff).next("label").html("");


	var expcompany=$(".expcompany_"+diff).val();
	if (expcompany == null || expcompany == "") {
		$(".expcompany_" + diff).next("label").html(" 请输入物流编号！");
        $(".expcompany_" + diff).next("label").css("color","red");
		return false;
	}
	$(".expcompany_" + diff).next("label").html("");

	var expkuaidi = $(".kuaidi_"+diff).val();
	var expkuaidilen =  expkuaidi.length;
	if (expkuaidilen <1 || expkuaidilen>50) {
		$(".expkuaidi").html("快递100物流公司代码 长度必须在 1 ~ 50字符之间!");
        $(".expkuaidi").css("color","red");
		return false;
	}
    return true;
}
