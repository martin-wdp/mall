<#macro selectindex navid leftmenuid >
<script>
    $("#indextop li a").removeClass("current");
    $("#indextop li a").each(function(){
        if($(this).attr('navid') == ${navid}){
            $(this).parent().addClass("current");
            $(".menu_cont").hide();
            $(".leftmenuxx${navid}").show();
        }
    });

    $(".menu_list li").removeClass("active");
    $(".menu_list li a").each(function(){
        if($(this).attr('leftmenuid')== ${leftmenuid}){
            $(this).parent().addClass("active");
        }
    });

</script>
</#macro>