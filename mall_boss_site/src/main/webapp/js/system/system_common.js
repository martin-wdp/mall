// 反选
function selectAll(name,obj) {
    var checkboxs = document.getElementsByName(name);
    if($(obj).is(':checked')==true ) {
        for (var j = 0; j < checkboxs.length; j++) {
            checkboxs[j].checked = true;
        }
    } else {
        for (var j = 0; j < checkboxs.length; j++) {
            checkboxs[j].checked = false;
        }
    }
}
function scroll(bname){
    $('.'+bname).slimScroll({
        color: '#a1b2bd',
        height: $('.'+bname).attr("data-height")
    });
};