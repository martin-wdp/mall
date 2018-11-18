
//复制物流单模板
function copytemp(freightTemplateId){
	window.location.href="copyfreighttemplate.htm?freightTemplateId="+freightTemplateId+"&CSRFToken="+$("#token").val();
}  

function comeback(){
	window.location.href="freighttemplatelist.htm?CSRFToken="+$("#token").val();
}

//跳转添加物流模板
function toaddfreight(){
    window.location.href="toAddFreightTemplate.htm?CSRFToken="+$("#token").val();
}

/* 下面是关于树形菜单 */
var setting = {
  check: {
    enable: true,
    chkboxType : {"Y":"ps","N":"ps"}
  },
  data: {
    simpleData: {
      enable: true
    }
  },
	view:{
		showIcon:false
		}
  
};

var flag = 0;//标识 添加或者修改
var zTree;

/* 下面是关于树形菜单 END */
var logCode = '';
var logComId='';
$(function(){
  //选择地区方法
$(".J_AddRule").click(function(){
    //判断计价方式
    var freightMethods = $('input[name="freightMethods"]:checked').val();
    //用于 添加收货地区div显示
    if(freightMethods==1){
        $('.Weight_First').html('首重：');
        $('.Weight_Link').html('续重：');
        $('.Weight_First_cost').html('首重运费：');
        $('.Weight_Link_cost').html('续重运费：');
    }else{
        $('.Weight_First').html('首件个数：');
        $('.Weight_Link').html('续件个数：');
        $('.Weight_First_cost').html('首件运费：');
        $('.Weight_Link_cost').html('续件运费：');
    }
    flag = 0; 
    $('.city_choosed p').html('');
    $("#tempCity").val('');
    $("#viewstart").val('');
    $("#viewstartprice").val('');
    $("#viewend").val('');
    $("#viewendprice").val('');
    logCode = $(this).attr('data-logCode');
    logComId=$(this).attr('data-logComId');

        //加载省市
        $.ajax({  
            type: 'post', 
            url:'selectprovincelist.htm',
            async:false, 
            success: function(data) {
                //查询出内容加载省份
                var zNodes = new Array();
                if(data!=null){
                    var node = {
                            id : 1, pId : 0, name : '全国', open : true 
                    };
                    zNodes.push(node);
                    for(var i=0;i<data.length;i++){
                      var a=i+1+1;
                        var node = {
                                id : a, pId : 1, name : data[i].provinceName, open : false 
                            };
                          zNodes.push(node);  
                          //加载城市
                          if(data[i].cityList!=null){
                              for(var j=0;j<data[i].cityList.length;j++){
                                  var b=a+""+(j+1);
                                  var node = {
                                          id : b , pId : a, name : data[i].cityList[j].cityName,cityId: data[i].cityList[j].cityId,open : false 
                                      };
                                    zNodes.push(node);
                              }
                          }
                    }
                }
               
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);

                    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                    var nodes = treeObj.getNodes();
                    
                    for(var k=0;k<treeObj.transformToArray(nodes).length;k++){    
                       var node= treeObj.transformToArray(nodes)[k];
                       $("input[name='"+logCode+"_areas']").each(function(){
                           var $arr = new Array();
                           var $val = $(this).val();
                           if($val != undefined) {
                               $arr = $val.split(",");     
                           }
                           for(var p=0;p<$arr.length;p++){
                               if($arr[p]==node.cityId){
                                   treeObj.setChkDisabled(node, true, false, true); 
                               }
                           }
                           
                       });
                     }
            }
        });
        
     if($('input:radio[name="freightPackageMail"]:checked').val()==0){
         $("#viewstart").val(1);
         $("#viewstart").attr('readonly',false);
         $("#viewstartprice").val(0);
         $("#viewstartprice").attr('readonly',false);
         $("#viewend").val(1);
         $("#viewend").attr('readonly',false);
         $("#viewendprice").val(0);
         $("#viewendprice").attr('readonly',false);
      }else{
          $("#viewstart").val(1);
          $("#viewstart").attr('readonly',true);
          $("#viewstartprice").val(0);
          $("#viewstartprice").attr('readonly',true);
          $("#viewend").val(1);
          $("#viewend").attr('readonly',true);
          $("#viewendprice").val(0);
          $("#viewendprice").attr('readonly',true);
      }
    $('#editExpress').modal('show');
    $('#sign').html("添加收货地区");
    });
    
});

var trline;
function toEditCity(obj,logCodes,logComIds){
    flag = 1;
    //判断计价方式
    var freightMethods = $('input[name="freightMethods"]:checked').val();
    //用于 添加收货地区div显示
    if(freightMethods==1){
        $('.Weight_First').html('首重：');
        $('.Weight_Link').html('续重：');
        $('.Weight_First_cost').html('首重运费：');
        $('.Weight_Link_cost').html('续重运费：');
    }else{
        $('.Weight_First').html('首件个数：');
        $('.Weight_Link').html('续件个数：');
        $('.Weight_First_cost').html('首件运费：');
        $('.Weight_Link_cost').html('续件运费：');
    }
    $('.city_choosed p').html('');
    $("#tempCity").val('');
    $("#viewstart").val('');
    $("#viewstartprice").val('');
    $("#viewend").val('');
    $("#viewendprice").val('');
    
    logCode =logCodes;
    logComId=logComIds;
    trline = $(obj).parents('tr');
    var areas=trline.find('input[name="'+logCodes+'_areas"]').val();
    var start=trline.find('input[name="'+logCodes+'_start"]').val();
    var postage=trline.find('input[name="'+logCodes+'_postage"]').val();
    var plus=trline.find('input[name="'+logCodes+'_plus"]').val();
    var postageplus=trline.find('input[name="'+logCodes+'_postageplus"]').val();
        //加载省市
        $.ajax({  
            type: 'post', 
            url:'selectprovincelist.htm',
            async:false, 
            success: function(data) {
                //查询出内容加载省份
                var zNodes = new Array();
                if(data!=null){
                    var node = {
                            id : 1, pId : 0, name : '全国', open : true 
                    };
                    zNodes.push(node);
                    for(var i=0;i<data.length;i++){
                      var a=i+1+1;
                        var node = {
                                id : a, pId : 1, name : data[i].provinceName, open : false 
                            };
                          zNodes.push(node);  
                          //加载城市
                          if(data[i].cityList!=null){
                              for(var j=0;j<data[i].cityList.length;j++){
                                  var b=a+""+(j+1);
                                  var node = {
                                          id : b , pId : a, name : data[i].cityList[j].cityName,cityId: data[i].cityList[j].cityId,open : false 
                                      };
                                    zNodes.push(node);
                              }
                          }
                    }
                }
               
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                    var nodes = treeObj.getNodes();
                    
                    for(var k=0;k<treeObj.transformToArray(nodes).length;k++){    
                       var node= treeObj.transformToArray(nodes)[k];
                       $("input[name='"+logCode+"_areas']").each(function(){
                           var $arr = new Array();
                           var $val = $(this).val();
                           if($val != undefined) {
                               $arr = $val.split(",");     
                           }
                           for(var p=0;p<$arr.length;p++){
                               if($arr[p]==node.cityId){
                                   treeObj.setChkDisabled(node, true, false, true); 
                               }
                           }
                           
                       });
                     }

                    var v = "";
                    for(var k=0;k<treeObj.transformToArray(nodes).length;k++){    
                        var node= treeObj.transformToArray(nodes)[k];
                        
                        if(areas!=null&&areas!=''){
                            $arr = areas.split(',');
                            $("#tempCity").val(areas);
                            for(var p=0;p<$arr.length;p++){
                                if($arr[p]==node.cityId){
                                    treeObj.setChkDisabled(node, false, false, true); 
                                    treeObj.checkNode(node,true,true);
                                    v+=node.name+",";
                                }
                            }
                        }
                      }  
                    if(v!=''){
                        $('.city_choosed p').html(v.substring(0,v.length-1)); 
                    }
            }
        });
        
     if($('input:radio[name="freightPackageMail"]:checked').val()==0){
         $("#viewstart").val(start);
         $("#viewstart").attr('readonly',false); 
         $("#viewstartprice").val(postage);
         $("#viewstartprice").attr('readonly',false);
         $("#viewend").val(plus);
         $("#viewend").attr('readonly',false);
         $("#viewendprice").val(postageplus);
         $("#viewendprice").attr('readonly',false);
      }else{
          $("#viewstart").val(start);
          $("#viewstart").attr('readonly',true);
          $("#viewstartprice").val(postage);
          $("#viewstartprice").attr('readonly',true);
          $("#viewend").val(plus);
          $("#viewend").attr('readonly',true);
          $("#viewendprice").val(postageplus);
          $("#viewendprice").attr('readonly',true);
      }
    $('#editExpress').modal('show');
    $('#sign').html("编辑收货地区");
}

function doaddarea(){
    $("#addForm").validate();
    if($("#cityForm").valid()){
        if(flag==0){
        var htm = '';
        htm += ' <tr data-group="n1"  data-group-value="1" data-code="${company.code }">';
        htm += ' <td>';
        htm += '  <div class="express_area">';
        htm += '    <p>'+$('.city_choosed p').html()+'</p>  <input type="hidden" id="'+logCode+'_areas_n1" name="'+logCode+'_areas" value="'+$("#tempCity").val()+'">';
        htm += '  </div>';
        htm += ' <td>  <input type="hidden" value="'+$("#viewstart").val()+'" name="'+logCode+'_start" id="'+logCode+'_start_n1" ><span>'+$("#viewstart").val()+'</span></td>';
        htm += ' <td>  <input type="hidden" value="'+$("#viewstartprice").val()+'" name="'+logCode+'_postage" id="'+logCode+'_postage_n1"><span>'+$("#viewstartprice").val()+'</span></td>';
        htm += ' <td>  <input type="hidden" value="'+$("#viewend").val()+'" name="'+logCode+'_plus" id="'+logCode+'_plus_n1"/><span>'+$("#viewend").val()+'</span></td>';
        htm += '<td>  <input type="hidden" value="'+$("#viewendprice").val()+'" name="'+logCode+'_postageplus" id="'+logCode+'_postageplus_n1"><span>'+$("#viewendprice").val()+'</span></td>';
        htm += '<td>';
        htm += ' <div class="btn-group">';
        htm += '    <button type="button" class="btn btn-default" onclick="toEditCity(this,\''+logCode+'\',\''+logComId+'\')">编辑</button>';
        htm += '     <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">';
        htm += '      <span class="caret"></span>';
        htm += '    <span class="sr-only">Toggle Dropdown</span>';
        htm += '  </button>';
        htm += '   <ul class="dropdown-menu" role="menu">';
        htm += '     <li><a href="javascript:void(0);" onclick="removeTr(this);">删除</a></li>';
        htm += '    </ul>';
        htm += '  </div>';
        htm += ' </td>';
        htm += ' </tr>'; 
        $("#detail-express"+logComId).find(".table-bordered tbody").append(htm);
        $('#editExpress').modal('hide');
        }else{
            trline.find('input[name="'+logCode+'_areas"]').val($("#tempCity").val());
            trline.find('input[name="'+logCode+'_start"]').val($("#viewstart").val());
            trline.find('input[name="'+logCode+'_start"]').next().text($("#viewstart").val());
            trline.find('input[name="'+logCode+'_postage"]').val($("#viewstartprice").val());
            trline.find('input[name="'+logCode+'_postage"]').next().text($("#viewstartprice").val());
            trline.find('input[name="'+logCode+'_plus"]').val($("#viewend").val());  
            trline.find('input[name="'+logCode+'_plus"]').next().text($("#viewend").val()); 
            trline.find('input[name="'+logCode+'_postageplus"]').val($("#viewendprice").  val());
            trline.find('input[name="'+logCode+'_postageplus"]').next().text($("#viewendprice").val());;
            $('#editExpress').modal('hide');
        }
    }
}

//移除已选择的收货地区
function removeTr(obj){
    $(obj).parents('tr').remove();
}

//物流模板配置选择城市
function selectCity(obj){
    $.ajax({
        type: 'post',
        url:'selectcityuseselect.htm?provinceId='+$(obj).val(),
        async:false,
        success: function(data) {
           var html='<option value="">选择城市</option>';
              if(data!=null){
                   for(var i=0;i<data.length;i++){
                           html+='<option value="'+data[i].cityId+'">'+data[i].cityName+'</option>';
                       }
               }
               $("#freightCityId").html(html);
               $("#freightDistrictId").html('<option value="">选择区县</option>');
               $('select[data-live-search="true"]').select2(); 
           }
       });
}

//物流模板配置选择区县
function selectDistrict(obj){
        $.ajax({
         type: 'post',  
         url:'selectdistrictlistuserselect.htm?cityId='+$(obj).val(),
         async:false,
         success: function(data) {
            var html='<option value="">选择区县</option>';
               if(data!=null){
                    for(var i=0;i<data.length;i++){
                            html+='<option value="'+data[i].districtId+'">'+data[i].districtName+'</option>';
                        }
                }
                $("#freightDistrictId").html(html);
                $('select[data-live-search="true"]').select2();
            }
        });
}

    //物流模板设置 运费承担
    function checkMail(obj){
        //买家承担运费
        if($(obj).val()=='0'){
             $("input[name='logComId']").each(function(){
                var $logComCode = $(this).attr('codename');
                $("input[name='"+$logComCode+"_start']").each(function(){
                    $(this).val(1);
                    $(this).attr('readonly',false);
                });
                $("input[name='"+$logComCode+"_postage']").each(function(){
                    $(this).val(0);
                    $(this).attr('readonly',false);
                });
                $("input[name='"+$logComCode+"_plus']").each(function(){
                    $(this).val(1);
                    $(this).attr('readonly',false);
                });
                $("input[name='"+$logComCode+"_postageplus']").each(function(){
                    $(this).val(0);
                    $(this).attr('readonly',false);
                });
             });
        }else{
            //卖家承担运费
            $("input[name='logComId']").each(function(){
                var $logComCode = $(this).attr('codename');
                $("input[name='"+$logComCode+"_start']").each(function(){
                    $(this).val(1);
                    $(this).attr('readonly',true);
                });
                $("input[name='"+$logComCode+"_postage']").each(function(){
                    $(this).val(0);
                    $(this).attr('readonly',true);
                });
                $("input[name='"+$logComCode+"_plus']").each(function(){
                    $(this).val(1);
                    $(this).attr('readonly',true);
                });
                $("input[name='"+$logComCode+"_postageplus']").each(function(){
                    $(this).val(0);
                    $(this).attr('readonly',true);
                });
             });
        }
    }

    /*计价方式*/
    function friehgtMethod(obj){
        if(obj.checked==true){
            //按件计价
            if($(obj).val()==0){
                $(".methods").each(function(){   
                    $(this).html($(this).html().replace('g','件'));
                });
                $(".methodslist").each(function(){
                    $(this).html($(this).html().replace('重','件'));
                });
            }
            //按重计价
            if($(obj).val()==1){
                $(".methods").each(function(){
                    $(this).html($(this).html().replace('件','g'));
                });
                $(".methodslist").each(function(){
                    $(this).html($(this).html().replace('件','重'));
                });
            }
        }
    }
    
   