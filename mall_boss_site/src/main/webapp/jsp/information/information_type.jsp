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
    <title></title>

    <!-- Bootstrap -->
   <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">
  </head>
<body>
	
   <!-- 引用头 -->
   <jsp:include page="../page/header.jsp"></jsp:include>
   
   
   <div class="page_body container-fluid">
      <div class="row">
      	<!-- 引用左侧导航 -->
    		<jsp:include page="../page/left.jsp"></jsp:include>
    		
        <div class="col-lg-20 col-md-19 col-sm-18 main">
          <div class="main_cont">
            <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

            <h2 class="main_title">${pageNameChild} <small></small></h2>

            <div class="common_data p20">
              <div class="filter_area">
                <form role="form" class="form-inline">
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon">栏目名称</span>
                      <input type="text" class="form-control" id="searchText">
                    </div>
                  </div>
                  <div class="form-group">
                    <button type="button" class="btn btn-primary" onclick="searchForm();">搜索</button>
                  </div>
                  </form>
              </div>
             
              <div class="data_ctrl_area mb20">
                <div class="data_ctrl_search pull-right"></div>
                <div class="data_ctrl_brns pull-left">
                  <button type="button" class="btn btn-info" onclick="$('#addArticleCate').modal('show')">
                    <i class="glyphicon glyphicon-plus"></i> 添加
                  </button>
               <%--   <button type="button" class="btn btn-info">
                    <i class="glyphicon glyphicon-trash"></i> 删除
                  </button>--%>
                </div>
                <div class="clr"></div>
              </div>

              <!-- 这是另一种表格，带伸缩，不带分页 -->
              <table class="treetable table table-striped table-hover" cellspacing="0" width="100%">
              <thead>
              <tr>
                <th width="44">序号</th>
                <th>栏目名称</th>
                <th>URL</th>
                <th>是否显示</th>
                <th>是否第三方公告</th>
                <th width="150">操作</th>
              </tr>
              </thead>
              <tbody id="treetable">
             
             
              </tbody>
              </table>


            </div>

          </div>
       



	   </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="addArticleCate"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">添加栏目</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" id="saveInformationType" method="post" action="saveInfoType.htm?CSRFToken=${token}">
              <div class="form-group">
                <label class="control-label col-sm-4"><span class="text-danger">*</span>栏目名称：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <input type="text" class="form-control required" name="name" id="name">
                    <label id="name_tips"></label>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-4">上级栏目：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-4">
                  <select class="form-control informationtypes" id="parentId" name="parentId">
                  </select>
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-4">是否显示：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <label class="radio-inline">
                    <input type="radio" name="isShow" checked="checked" value="1"> 是
                  </label>
                  <label class="radio-inline">
                    <input type="radio" name="isShow" value="0"> 否
                  </label>
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-4">浏览权限：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-4">
                  <select class="form-control browseable" id="browseable" name="browseable">
                  </select>
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-4">第三方公告：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <label class="radio-inline">
                    <input type="radio" name="isThirdNews" checked="checked" value="1"> 是
                  </label>
                  <label class="radio-inline">
                    <input type="radio" name="isThirdNews" value="0"> 否
                  </label>
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-4">栏目属性：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <label class="radio-inline">
                    <input type="radio" name="attribute" checked="checked" value="0"> 最终列表栏目
                  </label>
                  <label class="radio-inline">
                    <input type="radio" name="attribute" value="1"> 顶级栏目
                  </label>
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-4"><span class="text-danger">*</span>URL路径：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <input type="text" class="form-control required" name="url" id="url">
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-4">SEO关键字：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <textarea class="form-control" rows="5" name="seoKeyword" id="keyword"></textarea>
                    <label id="keyword_tips"></label>
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-4">SEO描述：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <textarea class="form-control" rows="5" name="seoDesc" id="seoDesc"></textarea>
                  <label id="seoDesc_tips"></label>
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-4"><span class="text-danger">*</span>排序：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <input type="text" class="form-control required digits" name="sort" id="sort">
                </div>
                <div class="col-sm-3"></div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="addinformationType();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

   <!-- Modal -->
   <div class="modal fade" id="updateArticleCate"  role="dialog">
       <div class="modal-dialog modal-lg">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                   <h4 class="modal-title">修改栏目</h4>
               </div>
               <div class="modal-body">
                   <form class="form-horizontal" id="updateInformationType" method="post" action="updateInfoType.htm?CSRFToken=${token}">
                       <div class="form-group">
                           <label class="control-label col-sm-4"><span class="text-danger">*</span>栏目名称：</label>
                           <div class="col-sm-1"></div>
                           <div class="col-sm-10">
                               <input type="hidden" name="infoTypeId" id="up_infoTypeId">
                               <input type="text" class="form-control required" name="name" id="up_name">
                               <label id="up_name_tips"></label>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="control-label col-sm-4">上级栏目：</label>
                           <div class="col-sm-1"></div>
                           <div class="col-sm-4">
                               <select class="form-control informationtypes" id="up_parentId" name="parentId">
                               </select>
                           </div>
                           <div class="col-sm-3"></div>
                       </div>
                       <div class="form-group">
                           <label class="control-label col-sm-4">是否显示：</label>
                           <div class="col-sm-1"></div>
                           <div class="col-sm-10">
                               <label class="radio-inline">
                                   <input type="radio" name="isShow" id="up_isShow1" checked="checked" value="1"> 是
                               </label>
                               <label class="radio-inline">
                                   <input type="radio" name="isShow" id="up_isShow0" value="0"> 否
                               </label>
                           </div>
                           <div class="col-sm-3"></div>
                       </div>
                       <div class="form-group">
                           <label class="control-label col-sm-4">浏览权限：</label>
                           <div class="col-sm-1"></div>
                           <div class="col-sm-4">
                               <select class="form-control browseable" id="up_browseable" name="browseable">
                               </select>
                           </div>
                           <div class="col-sm-3"></div>
                       </div>
                       <div class="form-group">
                           <label class="control-label col-sm-4">第三方公告：</label>
                           <div class="col-sm-1"></div>
                           <div class="col-sm-10">
                               <label class="radio-inline">
                                   <input type="radio" name="isThirdNews" id="up_isThirdNews1" checked="checked" value="1"> 是
                               </label>
                               <label class="radio-inline">
                                   <input type="radio" name="isThirdNews" id="up_isThirdNews0" value="0"> 否
                               </label>
                           </div>
                           <div class="col-sm-3"></div>
                       </div>
                       <div class="form-group">
                           <label class="control-label col-sm-4">栏目属性：</label>
                           <div class="col-sm-1"></div>
                           <div class="col-sm-10">
                               <label class="radio-inline">
                                   <input type="radio" name="attribute" id="up_attribute0" checked="checked" value="0"> 最终列表栏目
                               </label>
                               <label class="radio-inline">
                                   <input type="radio" name="attribute" id="up_attribute1" value="1"> 顶级栏目
                               </label>
                           </div>
                           <div class="col-sm-3"></div>
                       </div>
                       <div class="form-group">
                           <label class="control-label col-sm-4"><span class="text-danger">*</span>URL路径：</label>
                           <div class="col-sm-1"></div>
                           <div class="col-sm-10">
                               <input type="text" class="form-control required" name="url" id="up_url">
                           </div>

                           <div class="col-sm-3"></div>
                       </div>
                       <div class="form-group">
                           <label class="control-label col-sm-4">SEO关键字：</label>
                           <div class="col-sm-1"></div>
                           <div class="col-sm-12">
                               <textarea class="form-control" rows="5" name="seoKeyword" id="up_keyword"></textarea>
                               <label id="up_keyword_tips"></label>
                           </div>
                           <div class="col-sm-3"></div>
                       </div>
                       <div class="form-group">
                           <label class="control-label col-sm-4">SEO描述：</label>
                           <div class="col-sm-1"></div>
                           <div class="col-sm-12">
                               <textarea class="form-control" rows="5" name="seoDesc" id="up_seoDesc"></textarea>
                               <label id="up_seoDesc_tips"></label>
                           </div>
                           <div class="col-sm-3"></div>
                       </div>
                       <div class="form-group">
                           <label class="control-label col-sm-4"><span class="text-danger">*</span>排序：</label>
                           <div class="col-sm-1"></div>
                           <div class="col-sm-2">
                               <input type="text" class="form-control required digits" name="sort" id="up_sort">
                           </div>
                           <div class="col-sm-3"></div>
                       </div>
                   </form>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-primary" onclick="updateinformationType();">确定</button>
                   <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
               </div>
           </div>
       </div>
   </div>

   <!-- Modal -->
   <div class="modal fade" id="delinformation"  role="dialog">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                   <h4 class="modal-title">操作提示</h4>
               </div>
               <div class="modal-body">
                   <form class="form-horizontal">
                       <input type="hidden" id="delinfoId" />
                       <div class="form-group">
                           <div class="col-sm-1"></div>
                           <div class="col-sm-10">
                               <p>
                                   你确定要删除吗?
                               </p>
                           </div>
                       </div>

                   </form>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-primary" onclick="delinformation();">确定</button>
                   <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
               </div>
           </div>
       </div>
   </div>

    <input type="hidden" id="CSRFToken" value="${token}">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="<%=basePath %>js/jqtreetable.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath %>js/information/information_type.js"></script>
    <script src="<%=basePath %>/js/select2.min.js"></script>
    <script>
        $(function(){

            doAjaxForCate(0,15);
            /* 表单项的值点击后转换为可编辑状态 */
            $('.form_value').click(function(){
                var formItem = $(this);
                if(!$('.form_btns').is(':visible')) {
                    formItem.parent().addClass('form_open');
                    $('.form_btns').show();
                    $('.form_btns').css({
                        'left': formItem.next().offset().left + 70 + 'px',
                        'top': formItem.next().offset().top - 30 + 'px'
                    });
                    $('.form_sure,.form_cancel').click(function () {
                        $('.form_btns').hide();
                        formItem.parent().removeClass('form_open');
                    });
                }
            });

            /* 为选定的select下拉菜单开启搜索提示 */
            $('select[data-live-search="true"]').select2();
            /* 为选定的select下拉菜单开启搜索提示 END */

            /* 富文本编辑框 */
            $('.summernote').summernote({
                height: 300,
                tabsize: 2,
                lang: 'zh-CN'
            });

            /* 下面是表单里面的填写项提示相关的 */
            $('.zhuantiseokw').popover({
                content : '默认为文章名称(最大字数75)',
                trigger : 'hover'
            });
            $('.zhuantiseodesc').popover({
                content : '默认为文章名称(最大字数255)',
                trigger : 'hover'
            });

            /* 高级搜索 */
            $('.advanced_search').popover({
                html : true,
                title : '高级搜索',
                content : $('.advanced_search_cont').html(),
                trigger : 'click',
                placement : 'bottom'
            }).click(function(){
            	 $('select[data-live-search="true"]').select2();
            });

            /* 下面是表单里面的日期时间选择相关的 */
            $('.form_datetime').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:00:00',
                weekStart : 1,
                autoclose : true,
                language : 'zh-CN',
                pickerPosition : 'bottom-left',
                todayBtn : true,
                viewSelect : 'hour'
            });
            $('.form_date').datetimepicker({
                format : 'yyyy-mm-dd',
                weekStart : 1,
                autoclose : true,
                language : 'zh-CN',
                pickerPosition : 'bottom-left',
                minView : 2,
                todayBtn : true
            });

        });


        function doAjaxForCate()
        {

            var searchText = encodeURI(encodeURI($("#searchText").val()));
            /*AJAX查询分类信息*/
            $.get("queryInfoTypeVoList.htm?newFlag=0&&searchText="+ searchText+"&CSRFToken=${token}",
                    function (data)
                    {
                        /*进行递归  GO*/
                        getCateList(data.list);
                    });
        }




        /*获取AJAX返回的分类列表，并进行反递归*/
        function getCateList(data)
        {


            if(data!=null&&data.length!=0){

                /*首先把内容置空*/
                $("#treetable").html("");
                calcSonCate(data);

                var map = "";
                $(".caidan").each(function(){
                	if($(this).text()!='0'){
                		$(this).parents('tr').addClass('collapsed');
                	}
                    map+=$(this).text()+",";

                });
                var maps=map.substring(0,map.length-1).split(",");

                $("#treetable").jqTreeTable(maps, {
                    openImg: "<%=basePath %>images/TreeTable/tv-collapsable.gif",
                    shutImg: "<%=basePath %>images/TreeTable/tv-expandable.gif",
                    leafImg: "<%=basePath %>images/TreeTable/tv-item.gif",
                    lastOpenImg: "<%=basePath %>images/TreeTable/tv-collapsable-last.gif",
                    lastShutImg: "<%=basePath %>images/TreeTable/tv-expandable-last.gif",
                    lastLeafImg: "<%=basePath %>images/TreeTable/tv-item-last.gif",
                    vertLineImg: "<%=basePath %>images/TreeTable/vertline.gif",
                    blankImg: "<%=basePath %>images/TreeTable/blank.gif",
                    collapse: false,
                    column: 1,
                    striped: true,
                    highlight: true,
                    state:false
                });
            }else{
                $("#treetable").html("");
            }


        }
        /*根据子分类列表反递归 */
        var index=1;
        function calcSonCate(data)
        {
            for (var i = 0; i < data.length; i++)
            {
                var html = "";
                html +='<tr>';
                if(data[i].parentId == '0') {
                    html += '	<td>'+(i+1)+'</td>';
                }else{
                    html += '	<td></td>';
                }

                html +='	<td style="text-align:left;"><span id="ids'+data[i].infoTypeId+'" style="display:none;" class="caidan"></span>&nbsp;&nbsp;'+data[i].name+'<span id="mes'+data[i].infoTypeId+'"  style="display:none;" >'+index+'</span></td>';
                html +='	<td>'+data[i].url+'</td>';
                if(data[i].isShow == '0'){
                    html += '<td><a href="javascript:;"><span class="label label-default">否</span></a></td>';
                }else{
                    html += '<td><a href="javascript:;"><span class="label label-success">是</span></a></td>';
                }
                if(data[i].isThirdNews == '0'){
                    html += '<td><a href="javascript:;"><span class="label label-default">否</span></a></td>';
                }else{
                    html += '<td><a href="javascript:;"><span class="label label-success">是</span></a></td>';
                }
                html +=' 	<td>';
                html +='  <div class="btn-group">';
                html +='  	 <button type="button" class="btn btn-default" onclick="updateModal('+data[i].infoTypeId+')">编辑</button>';
                html +=' 		 <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">';
                html +='   	 	<span class="caret"></span>';
                html +='   	 	<span class="sr-only">Toggle Dropdown</span>';
                html +='		 </button>';
                if(data[i].temp3 == 1) {
                    html += ' 	<ul class="dropdown-menu" role="menu">';
                    html += '   	 <li><a href="javascript:void(0);" onclick="delmodal(' + data[i].infoTypeId + ');">删除</a></li>';
                    html += '  </ul>';
                }
                html +=' 	</div>';
                html +='  </td>';
                html +='</tr>';
                $("#treetable").append(html);
                index++;
                if($("#mes"+data[i].parentId).text()==''){
                    $("#ids"+data[i].infoTypeId).text('0');
                }else{
                    $("#ids"+data[i].infoTypeId).text($("#mes"+data[i].parentId).text());
                }

                if(data[i].typeList!=null&&data[i].typeList.length!=0){
                    calcSonCate(data[i].typeList);
                }
            }
        }

        function searchForm(){
            index=1;
            doAjaxForCate();
        }

    </script>
  </body>
</html>
