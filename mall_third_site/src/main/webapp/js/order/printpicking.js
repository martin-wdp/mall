function subForm(){
    if($("#printNo").val()!="1"){
        $('#out_success').modal('show');
        return;
    }
	$.ajax({
        cache: true,
        type: "POST",
        url:"thirdchangeorderpicking.html",
        data:$('#yourformid').serialize(),// 你的formid
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
           if(data>0){
               window.opener.location.href="querythirdoutstock.html?orderCargoStatus=0";
               window.close();
           }
        }
    });
}

function subFormJh(){
    if($("#printNo").val()!="1"){
        $('#JHout_success').modal('show');
        return;
    }
    $.ajax({
        cache: true,
        type: "POST",
        url:"thirdchangeorderpicking.html",
        data:$('#yourformid').serialize(),// 你的formid
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            if(data>0){
                window.opener.location.href="querythirdoutstock.html?orderCargoStatus=0";
                window.close();
            }
        }
    });
}