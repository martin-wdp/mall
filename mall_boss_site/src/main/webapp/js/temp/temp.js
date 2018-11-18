function setUsedStatus(token,tempId){
   location.href= "setUsedStatus.htm?CSRFToken="+token+"&tempId="+tempId;
}

function showTemp(url){
    $("#showTempA").attr("action",url);
    $("#showTempA").submit();
}

