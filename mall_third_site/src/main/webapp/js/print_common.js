function preview(oper){
		if (oper < 10){
			bdhtml=window.document.body.innerHTML;
		    sprnstr="<!--startprint"+oper+"-->";
			eprnstr="<!--endprint"+oper+"-->";
			prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18);
			prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
			window.document.body.innerHTML=prnhtml;
			window.print();
			window.document.body.innerHTML=bdhtml;
            $("#printNo2").val("1");
            $("#printNo").val("1");
		} else {
			window.print();
            $("#printNo2").val("1");
            $("#printNo").val("1");
        }
};

function preview1(){
	window.print();
//		var mailId=$("#expressId").find("option:selected").text();
//		var text=$("#express_No").val();
//		var orderId=$("#thirdOrderId").val();
//		bdhtml=window.document.body.innerHTML;
//		sprnstr="<!--startprint-->";
//		eprnstr="<!--endprint-->";
//		prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); 
//		prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
//		prnhtml=prnhtml.substring(0,prnhtml.indexOf('id="expressId">')+15)+'<option selected="selected" value="'+$("#expressId").find("option:selected").val()+'">'+mailId+'</option>'+prnhtml.substring(prnhtml.indexOf('</select>'));	
//	
//		prnhtml=prnhtml.substring(0,prnhtml.indexOf('<input class="iq_text" value="')+30)+text+prnhtml.substring(prnhtml.indexOf('" id="express_No"'));	
//		
//		window.document.body.innerHTML=prnhtml;
//		
//		window.print();
//		bdhtml=bdhtml.substring(0,bdhtml.indexOf('id="expressId">')+15)+'<option selected="selected" value="'+$("#expressId").find("option:selected").val()+'">'+mailId+'</option>'+bdhtml.substring(bdhtml.indexOf('</select>'));	
//		
//		bdhtml=bdhtml.substring(0,bdhtml.indexOf('<input class="iq_text" value="')+30)+text+bdhtml.substring(bdhtml.indexOf('" id="express_No"'));	
//		
//		window.document.body.innerHTML=bdhtml;
};