/**
 * 全选反选
 * @param obj
 * @param name
 */
function allunchecked(obj,name){
      if(obj.checked){
              $("input[name='"+name+"']").each(function(){
                 this.checked=true;  
              });  
      }else{
          $("input[name='"+name+"']").each(function(){
                this.checked=false;  
           });  
      }
  }
  
  /*
   * 刷新
   */
  function refresh(){
      window.location.reload();
  }
  