<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加物流模板</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
      <style>
          .lab{font-weight:normal;}
      </style>
  </head>
  <body>
    <!-- 引用头 -->
   <jsp:include page="../page/header.jsp"></jsp:include>
   
    <div class="page_body container-fluid">
      <div class="row">
           	<jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
          <div class="main_cont">
             <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

            <h2 class="main_title">${pageNameChild}</h2>

            <div class="common_form common_form_lg">

              <form class="form-horizontal"  action="addFreight.htm?CSRFToken=${token}" method="post" id="freightForm">
               <input type="hidden" name="freightTemplateId" id="freightTemplateId" value="${freightTemplate.freightTemplateId }" />
                <div class="form-group">
                  <label class="control-label col-sm-4"><span class="text-danger">*</span>模板名称：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-16">
                    <input type="text" class="form-control w200 required" name="freightTemplateName" id="freightTemplateName" >
                  </div>
                  <div class="col-sm-3"></div>
                </div>
                <div class="form-group">
                  <label class="control-label col-sm-4"><span class="text-danger">*</span>发货地区：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-16">
                    <select class="inline" name="freightProvinceId" id="freightProvinceId" data-live-search="true" onchange="selectCity(this);">
                      <option value="">选择省份</option>
                   	    <c:forEach items="${provinceList }" var="province">
		  					<option value="${province.provinceId}">${province.provinceName}</option>
		  				</c:forEach>
                    </select>
                    <select class="inline" data-live-search="true"  name="freightCityId" id="freightCityId" onchange="selectDistrict(this);">
                      <option>选择城市</option>
                      <option>南京市</option>
                    </select>
                    <select class="inline" data-live-search="true" name="freightCountyId" id="freightDistrictId">
                      <option>选择区县</option>
                      <option>建邺区</option>
                    </select>
                    <span id="vtips"></span>
                  </div>
                  <div class="col-sm-3"></div>
                </div>
                <div class="form-group">
                  <label class="control-label col-sm-4"><span class="text-danger">*</span>运费承担：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-16">
                    <label class="radio-inline" style="width: 100px;">
                      <input type="radio"  name="freightPackageMail" value="0" onchange="checkMail(this);"  checked="checked"> 买家承担运费
                    </label>
                    <label class="radio-inline" style="width: 100px;">
                      <input type="radio"  name="freightPackageMail" value="1" onchange="checkMail(this);"> 卖家承担运费
                    </label>
                  
                  </div>
                  <div class="col-sm-3"></div>
                </div>
                <div class="form-group">
                  <label class="control-label col-sm-4"><span class="text-danger">*</span>计价方式：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-16">
                    <label class="radio-inline" style="width: 100px;">
                      <input type="radio" name="freightMethods"  value="0"  onchange="friehgtMethod(this);"  checked="checked"/>按件计价
                    </label>
                    <label class="radio-inline" style="width: 100px;">
                      <input type="radio" name="freightMethods"  value="1"  onchange="friehgtMethod(this);"> 按重计价
                    </label>
                  </div>
                  <div class="col-sm-3"></div>
                </div>
                <div class="form-group">
                  <label class="control-label col-sm-4"><span class="text-danger">*</span>运送方式：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-19 shipset">
                  
                  
          	<c:forEach items="${companylist}" var="company"> 
                  
                    <div class="checkbox1">
                      <label class="lab">
                        <input type="radio" name="logComId" codename=${company.code }  id="${company.logComId }" value="${company.logComId }" >
                        		${company.name }
                      </label>
                    </div>
                    
                    <div class="express_details p20" id="detail-express${company.logComId}">
                      <div class="mb10">  
                      
							 默认运费：
							    <input type="hidden" id="${company.code }_areas_n1" name="${company.code }_areas" value=""/>
						  		<input type="text" name="${company.code }_start"  value="1" id="${company.code }_start" class="w50"><span class="methods">件内，</span>
					            <input type="text" name="${company.code }_postage" value="0" id="${company.code }_postage" class="w50">元，  每增加
					            <input type="text" name="${company.code }_plus" value="1" id="${company.code }_plus" class="w50"><span class="methods">件， 增加运费</span>
					            <input type="text"  name="${company.code }_postageplus" value="0" id="${company.code }_postageplus"   class="w50">元
						   		    
                        <a href="javascript:;" class="yunsong help_tips">
                          <i class="icon iconfont">&#xe611;</i>
                        </a>
                      </div>
                      <p><button type="button" class="btn btn-info J_AddRule"  data-logComId="${company.logComId}" data-logCode="${company.code }">添加收货地区</button> </p>
                    
                      <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                          <th width="30%">收货地区</th>
                          <th class="methodslist">首件</th>
                          <th>首费(元)</th>
                          <th class="methodslist">续件</th>
                          <th>续费(元)</th>
                          <th width="120">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                       <%--  <tr data-group="n1"  data-group-value="1" data-code="${company.code }">
                          <td>
                            <div class="express_area">
                              <p>无</p>  <input type="hidden" id="${company.code }_areas_n1" name="${company.code }_areas" value="">
                            </div>
                          <td>  <input type="hidden" value="1" name="${company.code }_start" id="${company.code }_start_n1" >1</td>
                          <td>  <input type="hidden" value="0" name="${company.code }_postage" id="${company.code }_postage_n1">0</td>
                          <td>  <input type="hidden" value="1" name="${company.code }_plus" id="${company.code }_plus_n1"/>1</td>
                          <td>  <input type="hidden" value="0" name="${company.code }_postageplus" id="${company.code }_postageplus_n1">0</td>
                          <td>
                            <div class="btn-group">
                              <button type="button" class="btn btn-default">编辑</button>
                              <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <span class="caret"></span>
                                <span class="sr-only">Toggle Dropdown</span>
                              </button>
                              <ul class="dropdown-menu" role="menu">
                                <li><a href="#">删除</a></li>
                              </ul>
                            </div>
                          </td>
                        </tr> --%>
                        </tbody>
                      </table>
                    </div>
                    
                </c:forEach>    
                 <div id="logcomTips"></div>
                 
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-offset-5 col-sm-17">
                    <button type="button" class="btn btn-primary" onclick="addFreight();">保存</button>
                    <button type="button" class="btn btn-default" onclick="quxiao();">取消</button>
                  </div>
                </div>
              </form>
            </div>

          </div>
          
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="editExpress"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加收货地区</h4>
          </div>
          <div class="modal-body">
            <form role="form" class="form-horizontal" id="cityForm">
              <div class="form-group">
                <label class="control-label col-sm-5">收货地区：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-16">
                  <div class="city_area pull-left">
                    <ul id="treeDemo" class="ztree"></ul>
                  </div>
                  <div class="city_choose pull-left">
                    <button type="button" class="btn btn-default" id="getCitys">&gt;&gt;</button><br><br>
                  </div>
                  <div class="city_choosed pull-left">
                    <p></p><input type="hidden" value="" class="required" name="tempCity" id="tempCity"/>
                  </div>
                </div> 
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5 Weight_First">首件个数：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-16">
                  <input type="text" class="form-control w100 required digits" id="viewstart" name="viewstart">
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5 Weight_First_cost">首件运费：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-16">
                  <div class="input-group w100">
                    <span class="input-group-addon">￥</span>
                    <input type="text" class="form-control required  number" id="viewstartprice" name="viewstartprice">
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5 Weight_Link">续件个数：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-16">
                  <input type="text" class="form-control w100 required  digits" id="viewend" name="viewend">
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5 Weight_Link_cost">续件运费：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-16">
                  <div class="input-group w100">
                    <span class="input-group-addon">￥</span>
                    <input type="text" class="form-control required number" id="viewendprice" name="viewendprice">
                  </div>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="doaddarea();">保存</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/freight/freight.js"></script>
    <script src="<%=basePath %>/js/select2.min.js"></script>
    <script>
         var num=0;
      $(function(){
          $("#freightForm").validate();
        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END */

        /* 下面是表单里面的填写项提示相关的 */
        $('.yunsong').popover({
          content : '除指定地区外，其余地区的运费采用“默认运费”。至少要选择一项运送方式。',
          trigger : 'hover'
        });

        
     
        $('.shipset .checkbox1 input').change(function(){
            $(".express_details").slideUp('fast');
          if($(this).is(':checked')){
            $(this).parent().parent().next().slideDown('fast');
          }
          else {
            $(this).parent().parent().next().slideUp('fast');
          }
        });

       

        $('#getCitys').click(function(){
          var treeObj = $.fn.zTree.getZTreeObj('treeDemo'); 
          var nodes = treeObj.getCheckedNodes(true);
          var v = '';
          var tempCityId='';
          for(var i=0;i<nodes.length;i++){
            if(!(nodes[i].isParent)) {
              v += nodes[i].name + ',';
              tempCityId+=nodes[i].cityId+',';
            }
          }
          
          if(tempCityId!=''){
              $('.city_choosed p').html(v.substring(0,v.length-1)); 
              $('#tempCity').val(tempCityId.substring(0,tempCityId.length-1 ));
          }else{
              $('.city_choosed p').html('');               
              $('#tempCity').val('');
          }
       
          $("#cityForm").valid();
        });
      });
      
      function addFreight(){
          var b=$("#freightForm").valid();
          if($("#freightProvinceId").val()=='' || $("#freightCityId").val()=='' ||$("#freightDistrictId").val()==''){
              $("#vtips").html('<label class="error">请选择省、市、区县</label>');
              b=false&&b;
          }else{
              $("#vtips").html('');
              b=true&&b;
          }
      
        
        
              var a=0;
              $("input[name='logComId']").each(function(){
                  if(this.checked==true){
                      a++;
                  }
              });
              if(a==0){
                  $("#logcomTips").html('<label class="error">至少选择一种运送方式</label>');
                  b=false&&b;
              }else{
                  $("#logcomTips").html('');
                  b=true&&b;
              }
              
              $("input[name='logComId']").each(function(){
                  if(this.checked==true){
                    var $temp=$(this).parents(".checkbox1").next().find("div");
                    var $tempcode=$(this).attr("codename");
                      if(/^\d{1,}$/.test($temp.find("input[name='"+$tempcode+"_start']").val())){
                          $temp.find("input[name='"+$tempcode+"_start']").removeClass("error");
                          b=true&&b;
                      }else{
                          $temp.find("input[name='"+$tempcode+"_start']").addClass("error");
                          b=false&&b;
                      }
                      
                      if(/^\d{1,}$/.test($temp.find("input[name='"+$tempcode+"_postage']").val())){
                          $temp.find("input[name='"+$tempcode+"_postage']").removeClass("error");
                          b=true&&b;
                      }else{
                          $temp.find("input[name='"+$tempcode+"_postage']").addClass("error");
                          b=false&&b;
                      }
                      
                      if(/^\d{1,}$/.test($temp.find("input[name='"+$tempcode+"_plus']").val())){
                          $temp.find("input[name='"+$tempcode+"_plus']").removeClass("error");
                          b=true&&b;
                      }else{
                          $temp.find("input[name='"+$tempcode+"_plus']").addClass("error");
                          b=false&&b;
                      }
                      
                      
                      if(/^\d{1,}$/.test($temp.find("input[name='"+$tempcode+"_postageplus']").val())){
                          $temp.find("input[name='"+$tempcode+"_postageplus']").removeClass("error");
                          b=true&&b;
                      }else{
                          $temp.find("input[name='"+$tempcode+"_postageplus']").addClass("error");
                          b=false&&b;
                      }
                      
                      
                  }
              });
         
          
        
          
          if(b&&num==0){
              num+=1;
             $("#freightForm").submit();
          }
      }
      
      function quxiao(){
          window.location.href="freighttemplatelist.htm";
      }
    </script>
  </body>
</html>
