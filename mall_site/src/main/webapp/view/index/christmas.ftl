<script src="${basePath}/js/christmas/snow.js"></script>
<script>
    $(function(){
        $.fn.snow({
            minSize: 10,		//
            maxSize: 50, 	//
            newOn: 200		//
        });
    });
</script>
<style>
    body{ overflow-x:hidden;}
    #snowbox{z-index: 99999;}
</style>

